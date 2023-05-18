package org.sportmanagement.service;


import org.sportmanagement.entity.PlayerDetails;
import org.sportmanagement.entity.Team;
import org.sportmanagement.microservice.TeamCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sportmanagement.entity.Player;
import org.sportmanagement.exception.InvalidInputException;
import org.sportmanagement.pojo.PlayerPojo;
import org.sportmanagement.repository.PlayerRepository;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    private TeamCall teamCall;

    Logger logger = Logger.getLogger(PlayerService.class.getName());



    public List<PlayerDetails> getAllPlayer(){
        List<Player> players = playerRepository.findAll();
        return getPlayerDetails(players);
    }

    public Player getPlayerByID(int playerID){
        return playerRepository.findById(playerID).orElse(null);
    }

    public String deletePlayer(int playerId){
        try {
            Optional<Player> player = playerRepository.findById(playerId);
            if (player.isEmpty()){
                throw new InvalidInputException("The player id inserted does not exist.");
            }else {
                playerRepository.deleteById(playerId);
                return "Delete player Successful.";
            }
        } catch (Exception e){
            logger.log(Level.SEVERE, e.getMessage());
            return e.getMessage();
        }
    }

    public List<PlayerDetails> searchPlayersByParam(int playerId, String param){
        Player player = playerRepository.findById(playerId).orElse(null);
        List<Player> matchesList = new ArrayList<>();
        matchesList.add(player);
        matchesList.addAll(playerRepository.searchPlayersByParam(param));
        return getPlayerDetails(matchesList);
    }

    public List<Object> addPlayer(PlayerPojo player) {
        Date date = new Date();
        List<Object> result = new ArrayList<>();
        try {
            Optional<Team> team = Optional.ofNullable(teamCall.getTeam(player.getTeamId()));
            if(team.isEmpty()){
                throw new InvalidInputException("The team id inserted does not exist.");
            }else{
                playerRepository.save(new Player(player.getFirstName(),
                        player.getLastName(),player.getCountry(),player.getTeamId(),date));
                result.add(true);
                result.add("Insert Player Success!");
                return result;
                }
        } catch (Exception e){
            logger.log(Level.SEVERE, e.getMessage());
            result.add(false);
            result.add(e.getMessage());
            return result;
        }

    }

    public List<Object> updatePlayer(PlayerPojo player, int playerId) {
        Date date = new Date();
        List<Object> result = new ArrayList<>();
        try {
            Optional<Player> playerTemp = playerRepository.findById(playerId);
            Optional<Team> team = Optional.ofNullable(teamCall.getTeam(player.getTeamId()));
            if (playerTemp.isEmpty()){
                throw new InvalidInputException("The player id inserted does not exist.");
            }
            else if(team.isEmpty()){
                throw new InvalidInputException("The team inserted does not exist.");
            }
            else {
                playerRepository.save(new Player(playerId,player.getFirstName(),
                        player.getLastName(),player.getCountry(),player.getTeamId(),date));
                result.add(true);
                result.add("Update Player Success.");
                return result;
            }
        } catch (Exception e){
            logger.log(Level.SEVERE, e.getMessage());
            result.add(false);
            result.add(e.getMessage());
            return result;
        }
    }


    public List<PlayerDetails> getPlayerDetails(List<Player> playerList){
        return playerList.stream().map(player -> {
            Team team =teamCall.getTeam(player.getTeam());
            return new PlayerDetails(
                    player.getPlayerID(),
                    player.getFirstName(),
                    player.getLastName(),
                    player.getCountry(),
                    team,
                    player.getLastUpdate()
            );
        }).collect(Collectors.toList());
    }

}
