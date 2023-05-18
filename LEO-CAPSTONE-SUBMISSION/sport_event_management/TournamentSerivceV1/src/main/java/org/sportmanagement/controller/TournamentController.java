package org.sportmanagement.controller;

import org.sportmanagement.entity.TournamentDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.sportmanagement.entity.Tournament;
import org.sportmanagement.pojo.TournamentPojo;
import org.sportmanagement.service.TournamentService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TournamentController {

    @Autowired
    TournamentService tournamentService;

    @GetMapping("/tournaments/getTournaments")
    public List<TournamentDetails> getAllTournaments(){
        return tournamentService.getAllTournaments();
    }

    @GetMapping("/getTournament/{tournament_id}")
    public ResponseEntity<Tournament> getTournament(@PathVariable("tournament_id") Integer tournamentID){
        return new ResponseEntity<>(tournamentService.getTournament(tournamentID), HttpStatus.OK);
    }

    @PostMapping("/tournaments/addTournament")
    public ResponseEntity<String> addTournament(@RequestBody TournamentPojo tournament){
            List<Object> addResult = new ArrayList<>(tournamentService.addTournament(tournament));
            if(Boolean.TRUE.equals(addResult.get(0))){
                return new ResponseEntity<>(String.valueOf(addResult.get(1)), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(String.valueOf(addResult.get(1)), HttpStatus.BAD_REQUEST);
            }
    }
    @PutMapping("/tournaments/updateTournament")
    public ResponseEntity<String> updateTournament(@RequestBody TournamentPojo tournament,
                                                   @RequestParam (value = "tournament_id",required = false) Integer tournamentID){
        List<Object> updateResult = new ArrayList<>(tournamentService.updateTournament(tournament,tournamentID));
        if(Boolean.TRUE.equals(updateResult.get(0))){
            return new ResponseEntity<>(String.valueOf(updateResult.get(1)), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(String.valueOf(updateResult.get(1)), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/tournaments/deleteTournament")
        public ResponseEntity<String> deleteTournament(@RequestParam (value = "tournament_id",required = false) Integer tournamentID){
            return new ResponseEntity<>(tournamentService.deleteTournament(tournamentID), HttpStatus.OK);
    }

    @GetMapping("/search/tournaments")
    public ResponseEntity<List<TournamentDetails>> searchTournamentsByParam
            (@RequestParam (value = "tournament_id",required = false) Integer tournamentID,
             @RequestParam(value = "search", required = false) String param) {
        List<TournamentDetails> tournamentList = tournamentService.searchTournamentByParam(tournamentID,param);
        return new ResponseEntity<>(tournamentList,HttpStatus.OK);
    }
}
