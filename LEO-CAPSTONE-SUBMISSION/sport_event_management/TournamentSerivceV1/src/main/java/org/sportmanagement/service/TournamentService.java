package org.sportmanagement.service;

import org.sportmanagement.entity.Team;
import org.sportmanagement.entity.TournamentDetails;
import org.sportmanagement.microservice.TeamCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sportmanagement.entity.Tournament;
import org.sportmanagement.exception.InvalidInputException;
import org.sportmanagement.pojo.TournamentPojo;
import org.sportmanagement.repository.TournamentRepository;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private TeamCall teamCall;


    Logger logger = Logger.getLogger(TournamentService.class.getName());


    public List<TournamentDetails> getAllTournaments(){
        List<Tournament>tournaments = tournamentRepository.findAll();
        return getTournamentList(tournaments);
    }

    public Tournament getTournament(Integer tournamentID){
        return tournamentRepository.findById(tournamentID).orElse(null);
    }

    public String deleteTournament(int tournamentId){
        try {
            Optional<Tournament> tournament = tournamentRepository.findById(tournamentId);
            if (tournament.isEmpty()){
                throw new InvalidInputException("The tournament id inserted does not exist.");
            }else {
                tournamentRepository.deleteById(tournamentId);
                return "Delete tournament Successful.";
            }
        } catch (Exception e){
            logger.log(Level.SEVERE, e.getMessage());
            return e.getMessage();
        }
    }

    public List<TournamentDetails> searchTournamentByParam(int tournamentId, String param){
        Tournament tournaments = tournamentRepository.findById(tournamentId).orElse(null);
        List<Tournament> tournamentList = new ArrayList<>();
        tournamentList.add(tournaments);
        tournamentList.addAll(tournamentRepository.searchTournamentByParam(param.toLowerCase()));
        return getTournamentList(tournamentList);
    }

    public List<Object> addTournament(TournamentPojo tournament) {
        Date date = new Date();
        List<Object> result = new ArrayList<>();
        try {
            if (Boolean.FALSE.equals(isTeamExist(tournament.getTeamList()))){
                throw new InvalidInputException("Inserted team id/s does not exist!");
            }
            else if(tournament.getStartDate().before(date) ||
                    tournament.getEndDate().before(tournament.getStartDate())){
                throw new InvalidInputException("Tournament start and end date are invalid time period.");
            }
            else {
                tournamentRepository.save(new Tournament(tournament.getTournamentName(),
                        tournament.getTournamentCategory(),
                        tournament.getTournamentStyle(),
                        tournament.getStartDate(),
                        tournament.getEndDate(),
                        tournament.getTeamList(),
                        date));
                result.add(true);
                result.add("Insert Tournament Success.");
                return result;
            }
        } catch (Exception e){
            logger.log(Level.SEVERE, e.getMessage());
            result.add(false);
            result.add(e.getMessage());
            return result;
        }

    }

    public List<Object> updateTournament(TournamentPojo tournament, int tournamentID) {
        Date date = new Date();
        List<Object> result = new ArrayList<>();
        try {
            Optional<Tournament> tournamentTemp = tournamentRepository.findById(tournamentID);
            if (tournamentTemp.isEmpty()) {
                throw new InvalidInputException("Inserted tournament ID does not exist!");
            } else if (Boolean.FALSE.equals(isTeamExist(tournament.getTeamList()))) {
                throw new InvalidInputException("Inserted team id/s does not exist!");
            } else if (tournament.getStartDate().before(date) ||
                    tournament.getEndDate().before(tournament.getStartDate())) {
                throw new InvalidInputException("Tournament start and end date are invalid time period.");
            } else {
                tournamentRepository.save(new Tournament(
                        tournamentID,
                        tournament.getTournamentName(),
                        tournament.getTournamentCategory(),
                        tournament.getTournamentStyle(),
                        tournament.getStartDate(),
                        tournament.getEndDate(),
                        tournament.getTeamList(),
                        date));
                result.add(true);
                result.add("Update Tournament Success.");
                return result;
            }
        } catch (Exception e){
            logger.log(Level.SEVERE, e.getMessage());
            result.add(false);
            result.add(e.getMessage());
            return result;
        }
    }

    public boolean isTeamExist(String teamIDList) {
        Set<Integer> teamIDs = Stream.of(teamIDList.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
        for (Integer teamID:teamIDs) {
            Optional<Team> teamTemp = Optional.ofNullable(teamCall.getTeam(teamID));
            if (teamTemp.isEmpty()){
                return false;
            }
        }
        return true;
    }

    public List<TournamentDetails> getTournamentList(List<Tournament> tournaments){
        return tournaments.stream().map(tournament -> {
            Set<Team> teamSet = new HashSet<>();
            List<Integer> teamIDs = Stream.of(tournament.getTeams().split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            teamIDs.forEach(teamID -> {
                Team team = teamCall.getTeam(teamID);
                teamSet.add(team);
            });
            return new TournamentDetails(tournament.getTournamentID(),
                    tournament.getTournamentName(),
                    tournament.getTournamentCategory(),
                    tournament.getTournamentStyle(),
                    tournament.getStartDate(),
                    tournament.getEndDate(),
                    teamSet,
                    tournament.getLastUpdate());
        }).collect(Collectors.toList());
    }



}
