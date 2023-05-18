package sporteventmanagement.service;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import sporteventmanagement.entity.Field;
import sporteventmanagement.entity.Match;
import sporteventmanagement.entity.Ticket;
import sporteventmanagement.entity.TicketDetails;
import sporteventmanagement.exception.InvalidInputException;
import sporteventmanagement.microservice.FieldCall;
import sporteventmanagement.microservice.MatchCall;
import sporteventmanagement.pojo.TicketPojo;
import sporteventmanagement.repository.TicketRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
class TicketServiceTest {

    @InjectMocks
    private TicketService ticketService;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private FieldCall fieldCall;

    @Mock
    private MatchCall matchCall;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void getAllTicket() {
        List<TicketDetails> ticketDetailsList = ticketService.getAllTicket();
        assertNotNull(ticketDetailsList);
    }

    @Test
    void deleteTicket() {
        when(ticketRepository.findById(any(Integer.class))).thenReturn(Optional.of(getTicketTest()));
        String result = ticketService.deleteTicket(1);
        assertNotNull(result);
    }

    @Test
    void deleteMatchFailed() {
        exception.expect(InvalidInputException.class);
        when(ticketRepository.findAll()).thenReturn(getTicketTestList());
        ticketService.deleteTicket(0);
    }

    @Test
    void searchTicket() {
        when(ticketRepository.findById(any(Integer.class))).thenReturn(Optional.of(getTicketTest()));
        when(matchCall.getMatch(any(Integer.class))).thenReturn(getMatchTest());
        when(fieldCall.getField(any(Integer.class))).thenReturn(getTestField());
        List<TicketDetails> matches= ticketService.searchTicket(1,"test");
        assertNotNull(matches);
    }
    @Test
    void searchTicketDelete() {
        when(ticketRepository.findById(any(Integer.class))).thenReturn(Optional.of(getTicketTest()));
        when(fieldCall.getField(any(Integer.class))).thenReturn(getTestField());
        List<TicketDetails> matches= ticketService.searchTicket(1,"test");
        assertNotNull(matches);
    }


    @Test
    void addTicket() {
        when(ticketRepository.findById(any(Integer.class))).thenReturn(Optional.of(getTicketTest()));
        when(fieldCall.getField(any(Integer.class))).thenReturn(getTestField());
        when(matchCall.getMatch(any(Integer.class))).thenReturn(getMatchTest());
        List<Object> result = ticketService.addTicket(getTicketPojoTest());
        assertNotNull(result);
    }
    @Test
    void addTicketFailed() {
        when(ticketRepository.findById(any(Integer.class))).thenReturn(Optional.of(getTicketTest()));
        when(fieldCall.getField(any(Integer.class))).thenReturn(getTestField());
        List<Object> result = ticketService.addTicket(getTicketPojoTest());
        assertNotNull(result);
    }
    @Test
    void updateTicket() {
        when(ticketRepository.findById(any(Integer.class))).thenReturn(Optional.of(getTicketTest()));
        when(fieldCall.getField(any(Integer.class))).thenReturn(getTestField());
        when(matchCall.getMatch(any(Integer.class))).thenReturn(getMatchTest());
        List<Object> result = ticketService.updateTicket(getTicketPojoTest(),1);
        assertNotNull(result);
    }

    @Test
    void updateTicketInvalidTicket() {
        when(fieldCall.getField(any(Integer.class))).thenReturn(getTestField());
        when(matchCall.getMatch(any(Integer.class))).thenReturn(getMatchTest());
        List<Object> result = ticketService.updateTicket(getTicketPojoTest(),1);
        assertNotNull(result);
    }

    @Test
    void updateTicketInvalidMatch() {
        when(ticketRepository.findById(any(Integer.class))).thenReturn(Optional.of(getTicketTest()));
        when(fieldCall.getField(any(Integer.class))).thenReturn(getTestField());
        List<Object> result = ticketService.updateTicket(getTicketPojoTest(),1);
        assertNotNull(result);
    }


    private TicketDetails getTicketDetailsTest(){
        TicketDetails ticketDetails = new TicketDetails();
        ticketDetails.setTicketID(1);
        ticketDetails.setMatchID(getMatchTest());
        ticketDetails.setCustomerName("testName");
        ticketDetails.setTicketPrice(1234);
        ticketDetails.setLastUpdate(new Date());
        return ticketDetails;
    }

    private List<TicketDetails> getTicketDetailsTestList(){
        List<TicketDetails> ticketDetailsList = new ArrayList<>();
        for(int i = 0; i <= 5; i++){
            TicketDetails ticketDetails =getTicketDetailsTest();
            ticketDetails.setTicketID(i);
            ticketDetailsList.add(ticketDetails);
        }
        return ticketDetailsList;
    }

    private Ticket getTicketTest(){
        Ticket ticket = new Ticket();
        ticket.setTicketID(1);
        ticket.setTicketPrice(1234);
        ticket.setMatchID(1);
        ticket.setCustomerName("testName");
        ticket.setLastUpdate(new Date());
        return ticket;
    }

    private List<Ticket> getTicketTestList(){
        List<Ticket> ticketList = new ArrayList<>();
        for(int i =0; i <= 5; i++){
            Ticket ticket = getTicketTest();
            ticket.setTicketID(i);
            ticketList.add(ticket);
        }
        return ticketList;
    }

    private TicketPojo getTicketPojoTest(){
        TicketPojo ticket = new TicketPojo();
        ticket.setTicketPricePojo(1234);
        ticket.setCustomerNamePojo("testName");
        ticket.setMatchIDPojo(1);
        return ticket;
    }

    private Field getTestField(){
        Field field = new Field();
        field.setFieldID(1);
        field.setFieldName("testName");
        field.setFieldAddress("testAddress");
        field.setFieldCapacity(10000);
        field.setLastUpdate(new Date());
        return field;
    }

    private Match getMatchTest(){
        Match match = new Match();
        match.setFieldID(1);
        match.setTournamentID(1);
        match.setTeams("1,2,3,4");
        match.setPlayers("1,2,3,4");
        match.setStartDate(new Date());
        match.setEndDate(new Date());
        match.setLastUpdate(new Date());
        return match;
    }

}