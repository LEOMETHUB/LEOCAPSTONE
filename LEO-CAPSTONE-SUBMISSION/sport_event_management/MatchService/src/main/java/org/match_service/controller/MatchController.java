package org.match_service.controller;


import org.match_service.entity.MatchDetails;
import org.match_service.pojo.MatchPojo;
import org.match_service.entity.Match;
import org.match_service.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MatchController {

    @Autowired
    MatchService matchService;

    @GetMapping("/matches/getMatches")
    public ResponseEntity<List<MatchDetails>> getAllMatches()  {
        return new ResponseEntity<>(matchService.getAllMatches(), HttpStatus.OK);
    }

    @GetMapping("/getMatch/{match_id}")
    public ResponseEntity<Match> getMatchByID(@PathVariable("match_id") Integer matchID)  {
            return new ResponseEntity<>(matchService.getMatch(matchID), HttpStatus.OK);
    }

    @GetMapping ("matches/search")
    public ResponseEntity<List<MatchDetails>> searchMatchesByParam
            (@RequestParam (value = "match_id",required = false) Integer id,
             @RequestParam(value = "search", required = false) Integer param) {
                return new ResponseEntity<>(matchService.searchMatchesByParam(id,param),HttpStatus.OK);
    }

    @PostMapping("/matches/addMatch")
    public ResponseEntity<String> addMatch(@RequestBody MatchPojo matchPojo) {
            List<Object> addResult = new ArrayList<>(matchService.saveMatch(matchPojo, 0,"insert"));
            if(Boolean.TRUE.equals(addResult.get(0))){
                return new ResponseEntity<>(String.valueOf(addResult.get(1)), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(String.valueOf(addResult.get(1)), HttpStatus.BAD_REQUEST);
            }
    }

    @PutMapping("/matches/updateMatch")
    public ResponseEntity<String> updateMatch(@RequestBody MatchPojo matchPojo, @RequestParam(value = "match_id") Integer matchId) {
            List<Object> updateResult = new ArrayList<>(matchService.saveMatch(matchPojo, matchId, "update"));
            if(Boolean.TRUE.equals(updateResult.get(0))){
                return new ResponseEntity<>(String.valueOf(updateResult.get(1)), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(String.valueOf(updateResult.get(1)), HttpStatus.BAD_REQUEST);
            }
    }

    @DeleteMapping ("/matches/deleteMatch")
    public ResponseEntity<String> deleteMatch(@RequestParam(value = "match_id" , required = false) Integer matchId) {
            return new ResponseEntity<>(matchService.deleteMatch(matchId), HttpStatus.OK);
    }



}
