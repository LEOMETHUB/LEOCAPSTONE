package org.sportmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.sportmanagement.entity.Team;
import org.sportmanagement.exception.InvalidInputException;
import org.sportmanagement.pojo.TeamPojo;
import org.sportmanagement.service.TeamService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class    TeamController {

    @Autowired
    TeamService teamService;

    @GetMapping("/teams/getTeams")
    public List<Team> getAllTeams(){
        return teamService.getTeams();
    }
    @GetMapping("/getTeam/{team_id}")
    public Team getTeam(@PathVariable("team_id") Integer teamID){
        return teamService.getTeamByID(teamID);
    }
    @PostMapping("/teams/addTeam")
    public ResponseEntity<String> addTeam(@RequestBody TeamPojo teamPojo){
        return new ResponseEntity<>(teamService.addTeam(teamPojo), HttpStatus.OK);
    }
    @DeleteMapping("/teams/deleteTeam")
    public ResponseEntity<String> deleteTeam(@RequestParam (value = "team_id", required = false) Integer teamID){
            return new ResponseEntity<>(teamService.deleteTeam(teamID), HttpStatus.OK);

    }
}
