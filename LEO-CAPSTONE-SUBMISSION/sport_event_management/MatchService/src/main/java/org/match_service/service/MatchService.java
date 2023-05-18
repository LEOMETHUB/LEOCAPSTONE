package org.match_service.service;

import org.match_service.dao.MatchDAO;
import org.match_service.entity.*;
import org.match_service.exception.InvalidInputException;
import org.match_service.microservice.FieldCall;
import org.match_service.microservice.PlayerCall;
import org.match_service.microservice.TeamCall;
import org.match_service.microservice.TournamentCall;
import org.match_service.pojo.MatchPojo;
import org.match_service.repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@org.springframework.stereotype.Service
public class MatchService implements MatchDAO {

    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private FieldCall fieldCall;
    @Autowired
    private TournamentCall tournamentCall;
    @Autowired
    private TeamCall teamCall;
    @Autowired
    private PlayerCall playerCall;


    Logger logger = Logger.getLogger(MatchService.class.getName());


    public List<MatchDetails> getAllMatches() {
        List<Match> matches = matchRepository.findAll();
        return getMatchDetails(matches);
    }

    public Match getMatch(Integer matchID) {
        return matchRepository.findById(matchID).orElse(null);
    }

    public List<MatchDetails> searchMatchesByParam(Integer matchID, Integer param) {
            Match match = matchRepository.findById(matchID).orElse(null);
            List<Match> matchesList = new ArrayList<>();
            matchesList.add(match);
            matchesList.addAll(matchRepository.searchMatchesByParam(param));
            return getMatchDetails(matchesList);
    }

    public String deleteMatch(int matchID){
        try {
            Optional<Match> match = matchRepository.findById(matchID);
            if (match.isEmpty()){
                throw new InvalidInputException("The match id "+ matchID + " inserted does not exist!");
            }else {
                matchRepository.deleteById(matchID);
                return "Delete Match Successful!";
            }
        } catch (Exception e){
            logger.log(Level.SEVERE, e.getMessage());
            return e.getMessage();
        }
    }

    public List<Object> saveMatch(MatchPojo match, Integer matchID, String process) {
        Date date = new Date();
        List<Object> result = new ArrayList<>();
        try {
            Optional<Tournament> tournamentTemp = Optional.ofNullable(tournamentCall.getTournament(match.getTournamentId()));
            Optional<Field> fieldTemp = Optional.ofNullable(fieldCall.getField(match.getFieldId()));
            Optional<Match> matchTemp = matchRepository.findById(matchID);
            if (tournamentTemp.isEmpty()) {
                throw new InvalidInputException("Tournament ID inserted not found.");
            }
            else if (fieldTemp.isEmpty()) {
                throw new InvalidInputException("Field ID inserted not found.");
            }else if (Boolean.FALSE.equals(isTeamExist(match.getTeams()))){
                throw new InvalidInputException("Inserted team id/s does not exist!");
            }else if (Boolean.FALSE.equals(isPlayerExist(match.getPlayers()))){
                throw new InvalidInputException("Inserted player id/s does not exist!");
            }else if (match.getStartDate().before(date) ||
                    match.getEndDate().before(match.getStartDate())) {
                throw new InvalidInputException("Match start and end date are invalid time period.");
            }else if (process.equals("insert")) {
                matchRepository.save(new Match(
                        match.getFieldId(),
                        match.getTournamentId(),
                        match.getPlayers(),
                        match.getTeams(),
                        match.getStartDate(),
                        match.getEndDate(),
                        date
                        ));
                result.add(true);
                result.add("Add Match Success!");
                return result;
            }else if(matchTemp.isEmpty()) {
                throw new InvalidInputException("Match ID inserted not found.");
            }
            else {
                matchRepository.save(new Match(
                        matchID,
                        match.getFieldId(),
                        match.getTournamentId(),
                        match.getPlayers(),
                        match.getTeams(),
                        match.getStartDate(),
                        match.getEndDate(),
                        date
                ));
                result.add(true);
                result.add("Update Match Success!");
                return result;
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            result.add(false);
            result.add(e.getMessage());
            return result;
        }

    }


    public boolean isPlayerExist(String playerIDList) {
        Set<Integer> playerIDs = Stream.of(playerIDList.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
        for (Integer playerID:playerIDs) {
            Optional<Player> playerTemp = Optional.ofNullable(playerCall.getPlayer(playerID));
            if (playerTemp.isEmpty()){
                return false;
            }
        }
        return true;
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

    public List<MatchDetails> getMatchDetails(List<Match> matches){
        return matches.stream().map(match -> {
            Field field = fieldCall.getField(match.getFieldID());
            Tournament tournament = tournamentCall.getTournament(match.getTournamentID());
            Set<Player> playerSet = new HashSet<>();
            Set<Team> teamSet = new HashSet<>();
            List<Integer> teamIDs = Stream.of(match.getTeams().split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            teamIDs.forEach(teamID -> {
                Team team = teamCall.getTeam(teamID);
                teamSet.add(team);
            });
            List<Integer> playerIDs = Stream.of(match.getPlayers().split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            playerIDs.forEach(playerID -> {
                Player player = playerCall.getPlayer(playerID);
                playerSet.add(player);
            });
            return new MatchDetails(
                    match.getMatchID(),
                    field,
                    tournament,
                    playerSet,
                    teamSet,
                    match.getStartDate(),
                    match.getEndDate(),
                    match.getLastUpdate()
            );
        }).collect(Collectors.toList());
    }

}
