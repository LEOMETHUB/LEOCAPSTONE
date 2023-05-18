package org.sportmanagement.controller;


import org.sportmanagement.entity.PlayerDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.sportmanagement.entity.Player;
import org.sportmanagement.pojo.PlayerPojo;
import org.sportmanagement.service.PlayerService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PlayerController {

    @Autowired
    private PlayerService playerService;


    @GetMapping("/players/getPlayers")
    public ResponseEntity<List<PlayerDetails>> getAllPlayers(){
            return new ResponseEntity<>(playerService.getAllPlayer(), HttpStatus.OK);
    }


    @GetMapping("/getPlayer/{player_id}")
    public ResponseEntity<Player> getPlayerByID(@PathVariable("player_id") Integer playerID){
            return new ResponseEntity<>(playerService.getPlayerByID(playerID), HttpStatus.OK);
    }

    @PostMapping("/players/addPlayer")
    public ResponseEntity<String> addPlayer(@RequestBody PlayerPojo playerPojo){
            List<Object> addResult = new ArrayList<>(playerService.addPlayer(playerPojo));
            if(Boolean.TRUE.equals(addResult.get(0))){
                return new ResponseEntity<>(String.valueOf(addResult.get(1)), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(String.valueOf(addResult.get(1)), HttpStatus.BAD_REQUEST);
            }
    }
    @PutMapping("/players/updatePlayer")
    public ResponseEntity<String> updatePlayer(@RequestBody PlayerPojo playerPojo,
                                               @RequestParam(value = "player_id", required = false) Integer playerID){
            List<Object> updateResult = new ArrayList<>(playerService.updatePlayer(playerPojo, playerID));
            if(Boolean.TRUE.equals(updateResult.get(0))){
                return new ResponseEntity<>(String.valueOf(updateResult.get(1)), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(String.valueOf(updateResult.get(1)), HttpStatus.BAD_REQUEST);
            }
    }
    @DeleteMapping("/players/deletePlayer")
    public ResponseEntity<String> deletePlayer(@RequestParam (value = "player_id", required = false) Integer playerID){
            return new ResponseEntity<>(playerService.deletePlayer(playerID), HttpStatus.OK);
    }

    @GetMapping("/search/players")
    public ResponseEntity<List<PlayerDetails>> searchPlayersByParam
            (@RequestParam (value = "player_id",required = false) Integer playerID,
             @RequestParam(value = "search", required = false) String param) {
        List<PlayerDetails> playerList = playerService.searchPlayersByParam(playerID,param);
        return new ResponseEntity<>(playerList,HttpStatus.OK);
    }
}
