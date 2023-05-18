package sporteventmanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import sporteventmanagement.entity.Field;
import sporteventmanagement.entity.Match;
import sporteventmanagement.entity.Ticket;
import sporteventmanagement.entity.TicketDetails;
import sporteventmanagement.pojo.TicketPojo;
import sporteventmanagement.service.TicketService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class TicketControllerTest {

    @InjectMocks
    private TicketController ticketController;

    @MockBean
    private TicketService ticketService;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(ticketController).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
    }


    @Test
    void getTickets() throws Exception {
        List<TicketDetails> ticketDetailsList = getTicketDetailsList();
        when(ticketService.getAllTicket()).thenReturn(ticketDetailsList);
        mockMvc.perform(MockMvcRequestBuilders.get("/tickets/getTickets")
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(ticketDetailsList)));
    }

    @Test
    void searchPlayer() throws Exception {
        List<TicketDetails> ticketDetailsList = getTicketDetailsList();
        when(ticketService.searchTicket(any(Integer.class),any(String.class))).thenReturn(ticketDetailsList);
        mockMvc.perform(MockMvcRequestBuilders.get("/tickets/search")
                        .param("ticket_id","1")
                        .param("search","1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(ticketDetailsList)));
    }

    @Test
    void addTicket() throws Exception {
        List<Object> result = new ArrayList<>();
        result.add(true);
        result.add("Insert Ticket Success!");
        when(ticketService.addTicket(any(TicketPojo.class))).thenReturn(result);
        mockMvc.perform(MockMvcRequestBuilders.post("/tickets/addTicket")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(getTicketPojoTest()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Insert Ticket Success!"));
    }

    @Test
    void addTicketFailed() throws Exception {
        List<Object> result = new ArrayList<>();
        result.add(false);
        result.add(null);
        when(ticketService.addTicket(any(TicketPojo.class))).thenReturn(result);
        mockMvc.perform(MockMvcRequestBuilders.post("/tickets/addTicket")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(getTicketPojoTest()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateTicket() throws Exception {
        List<Object> result = new ArrayList<>();
        result.add(true);
        result.add("Update Ticket Success!");
        when(ticketService.updateTicket(any(TicketPojo.class),any(Integer.class))).thenReturn(result);
        mockMvc.perform(MockMvcRequestBuilders.put("/tickets/updateTicket")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(getTicketPojoTest()))
                        .param("ticket_id","1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Update Ticket Success!"));
    }

    @Test
    void updateTicketFailed() throws Exception {
        List<Object> result = new ArrayList<>();
        result.add(false);
        result.add(null);
        when(ticketService.updateTicket(any(TicketPojo.class),any(Integer.class))).thenReturn(result);
        mockMvc.perform(MockMvcRequestBuilders.put("/tickets/updateTicket")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(getTicketPojoTest()))
                        .param("ticket_id","1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteTicket() throws Exception {
        String result = "Delete ticket Successful!";
        when(ticketService.deleteTicket(any(Integer.class))).thenReturn(result);
        mockMvc.perform(MockMvcRequestBuilders.delete("/tickets/deleteTicket")
                        .param("ticket_id", String.valueOf(1))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(result));
    }


    public Ticket getSample(){
        Ticket ticket = new Ticket();
        ticket.setCustomerName("testName");
        ticket.setTicketPrice(1234);
        ticket.setFieldID(1);
        ticket.setMatchID(1);
        ticket.setLastUpdate(new Date());
        return ticket;
    }

    public List<Ticket> getTestTicketList(){
        List<Ticket> tickets = new ArrayList<>();
        for(int i = 1; i <=5; i++){
            Ticket ticket = getSample();
            ticket.setTicketID(i);
            tickets.add(ticket);
        }
        return tickets;
    }

    public TicketDetails getTicketDetails(){
        TicketDetails ticketDetails = new TicketDetails();
        ticketDetails.setTicketID(1);
        ticketDetails.setTicketPrice(1234);
        ticketDetails.setCustomerName("testName");
        ticketDetails.setFieldID(getTestField());
        ticketDetails.setMatchID(getMatchTest());
        return  ticketDetails;
    }

    public List<TicketDetails> getTicketDetailsList(){
        List<TicketDetails> ticketDetailsList = new ArrayList<>();
        for(int i = 0; i <= 5; i++){
            TicketDetails ticketDetails= getTicketDetails();
            ticketDetails.setTicketID(i);
            ticketDetailsList.add(ticketDetails);
        }
        return  ticketDetailsList;
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

    private TicketPojo getTicketPojoTest(){
        TicketPojo ticket = new TicketPojo();
        ticket.setTicketPricePojo(1234);
        ticket.setCustomerNamePojo("testName");
        ticket.setMatchIDPojo(1);
        return ticket;
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}