package sporteventmanagement.service;

import sporteventmanagement.entity.Field;
import sporteventmanagement.entity.Match;
import sporteventmanagement.entity.Ticket;
import sporteventmanagement.entity.TicketDetails;
import sporteventmanagement.exception.InvalidInputException;
import sporteventmanagement.microservice.FieldCall;
import sporteventmanagement.microservice.MatchCall;
import sporteventmanagement.pojo.TicketPojo;
import sporteventmanagement.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class TicketService {
    Logger logger = Logger.getLogger(TicketService.class.getName());
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private FieldCall fieldCall;
    @Autowired
    private MatchCall matchCall;

    public List<TicketDetails> getAllTicket(){
        List<Ticket> tickets = ticketRepository.findAll();
        return getTicketDetails(tickets);
    }
    public String deleteTicket(int ticketID){
        try {
            Optional<Ticket> ticket = ticketRepository.findById(ticketID);
            if (ticket.isEmpty()){
                throw new InvalidInputException("The ticket id inserted does not exist!");
            }else {
                ticketRepository.deleteById(ticketID);
                return "Delete ticket Successful!";
            }
        } catch (Exception e){
            logger.log(Level.SEVERE, e.getMessage());
            return e.getMessage();
        }
    }

    public List<TicketDetails> searchTicket(int ticketId, String param){
        Ticket ticket = ticketRepository.findById(ticketId).orElse(null);
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket);
        ticketList.addAll(ticketRepository.searchTicketByParam(param.toLowerCase()));
        return getTicketDetails(ticketList);
    }

    public List<Object> addTicket(TicketPojo ticketPojo) {
        Date date = new Date();
        List<Object> result = new ArrayList<>();
        try {
            Optional<Match> match = Optional.ofNullable(matchCall.getMatch(ticketPojo.getMatchIDPojo()));
            if(match.isEmpty()){
                throw new InvalidInputException("Inserted match ID does not exist.");
            } else {
                ticketRepository.save(new Ticket(
                   ticketPojo.getCustomerNamePojo(),
                   ticketPojo.getTicketPricePojo(),
                   ticketPojo.getMatchIDPojo(),
                        match.get().getFieldID(),
                        date
                ));
                result.add(true);
                result.add("Insert Ticket Success!");
                return result;
            }
        } catch (Exception e){
            logger.log(Level.SEVERE, e.getMessage());
            result.add(false);
            result.add(e.getMessage());
            return result;
        }
    }

    public List<Object> updateTicket(TicketPojo ticketPojo, Integer ticketID) {
        Date date = new Date();
        List<Object> result = new ArrayList<>();
        try {
            Match match = matchCall.getMatch(ticketPojo.getMatchIDPojo());
            Optional<Ticket> ticketTemp = ticketRepository.findById(ticketID);
            if(match == null){
                throw new InvalidInputException("Inserted match ID does not exist.");
            }else if(ticketTemp.isEmpty()){
                throw new InvalidInputException("Inserted ticket ID does not exist.");
            }else {
                ticketRepository.save(new Ticket(
                        ticketID,
                        ticketPojo.getCustomerNamePojo(),
                        ticketPojo.getTicketPricePojo(),
                        ticketPojo.getMatchIDPojo(),
                        match.getFieldID(),
                        date
                ));
                result.add(true);
                result.add("Update Ticket Success!");
                return result;
            }
        } catch (Exception e){
            logger.log(Level.SEVERE, e.getMessage());
            result.add(false);
            result.add(e.getMessage());
            return result;
        }
    }

    public List<TicketDetails> getTicketDetails(List<Ticket> tickets){
        return tickets.stream().map(ticket -> {
            Field field = fieldCall.getField(ticket.getFieldID());
            Match match = matchCall.getMatch(ticket.getMatchID());
            return new TicketDetails(
                    ticket.getTicketID(),
                    ticket.getCustomerName(),
                    ticket.getTicketPrice(),
                    match,
                    field,
                    ticket.getLastUpdate()
            );
        }).collect(Collectors.toList());
    }



}
