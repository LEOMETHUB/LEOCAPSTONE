package org.sportmanagement.service;

import org.sportmanagement.dao.TeamDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sportmanagement.entity.Team;
import org.sportmanagement.exception.InvalidInputException;
import org.sportmanagement.pojo.TeamPojo;
import org.sportmanagement.repository.TeamRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class TeamService implements TeamDAO {
    Logger logger = Logger.getLogger(TeamService.class.getName());

    @Autowired
    private TeamRepository teamRepository;

    public List<Team> getTeams(){

        return teamRepository.findAll();
    }

    public Team getTeamByID(Integer teamID){
        return teamRepository.findById(teamID).orElse(null);
    }

    public String deleteTeam(int teamId){
        try {
            Optional<Team> team = teamRepository.findById(teamId);
            if (team.isEmpty()){
                throw new InvalidInputException("The team id inserted does not exist!");
            }else {
                teamRepository.deleteById(teamId);
                return "Delete Team Success.";
            }
        } catch (Exception e){
            logger.log(Level.SEVERE, e.getMessage());
            return e.getMessage();
        }
    }

    public String addTeam(TeamPojo teamPojo) {
        Date date = new Date();
        teamRepository.save(new Team(teamPojo.getTeamName(),date));
        return "Insert Team Success.";

    }
}
