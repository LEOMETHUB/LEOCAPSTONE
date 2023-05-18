package sporteventmanagement.controller;


import sporteventmanagement.entity.*;
import sporteventmanagement.exception.InvalidInputException;
import sporteventmanagement.pojo.TicketPojo;
import sporteventmanagement.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
public class TicketController {


    @Autowired
    TicketService ticketService;


    @GetMapping("/tickets/getTickets")
    public ResponseEntity<List<TicketDetails>> getTickets(){
        return new ResponseEntity<>(ticketService.getAllTicket(),HttpStatus.OK);
    }

    @GetMapping("/tickets/search")
    public ResponseEntity<List<TicketDetails>> searchPlayer
        (@RequestParam (value = "ticket_id",required = false) Integer ticketID,
                @RequestParam(value = "search", required = false) String param) {
        return new ResponseEntity<>(ticketService.searchTicket(ticketID,param),HttpStatus.OK);
    }

    @PostMapping("/tickets/addTicket")
    public ResponseEntity<String> addTicket(@RequestBody TicketPojo ticketPojo){
        List<Object> addResult = new ArrayList<>(ticketService.addTicket(ticketPojo));
        if(Boolean.TRUE.equals(addResult.get(0))){
            return new ResponseEntity<>(String.valueOf(addResult.get(1)), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(String.valueOf(addResult.get(1)), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/tickets/updateTicket")
    public ResponseEntity<String> updateTicket(@RequestBody TicketPojo ticketPojo,
                                               @RequestParam(value = "ticket_id") Integer ticketID){
        List<Object> updateResult = new ArrayList<>(ticketService.updateTicket(ticketPojo,ticketID));
        if(Boolean.TRUE.equals(updateResult.get(0))){
            return new ResponseEntity<>(String.valueOf(updateResult.get(1)), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(String.valueOf(updateResult.get(1)), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/tickets/deleteTicket")
    public ResponseEntity<String> deleteTicket(@RequestParam(value = "ticket_id") Integer ticketID){
        String response = ticketService.deleteTicket(ticketID);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
