package org.sportmanagement.service;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.sportmanagement.controller.PlayerController;
import org.sportmanagement.entity.Player;
import org.sportmanagement.entity.PlayerDetails;
import org.sportmanagement.entity.Team;
import org.sportmanagement.exception.InvalidInputException;
import org.sportmanagement.microservice.TeamCall;
import org.sportmanagement.pojo.PlayerPojo;
import org.sportmanagement.repository.PlayerRepository;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class PlayerServiceTest {

    @InjectMocks
    private PlayerService playerService;
    @Mock
    private PlayerRepository playerRepository;
    @Mock
    private TeamCall teamCall;
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllPlayer() {
        List<PlayerDetails> playerDetailsList = playerService.getAllPlayer();
        assertNotNull(playerDetailsList);
    }

    @Test
    void getPlayerByID() {
        when(playerRepository.findById(any(Integer.class))).thenReturn(Optional.of(getPlayerTest()));
        Player player = playerService.getPlayerByID(any(Integer.class));
        assertNotNull(player);
    }

    @Test
    void deletePlayer() {
        when(playerRepository.findById(any(Integer.class))).thenReturn(Optional.of(getPlayerTest()));
        String result = playerService.deletePlayer(1);
        assertNotNull(result);
    }

    @Test
    void deletePlayerFailed() {
        exception.expect(InvalidInputException.class);
        when(playerRepository.findAll()).thenReturn(getTestPlayerList());
        playerService.deletePlayer(0);
    }

    @Test
    void searchPlayersByParam() {
        when(playerRepository.findById(any(Integer.class))).thenReturn(Optional.of(getPlayerTest()));
        when(teamCall.getTeam(any(Integer.class))).thenReturn(getTeamTest());
        List<PlayerDetails> playerDetailsList= playerService.searchPlayersByParam(1,"testName");
        assertNotNull(playerDetailsList);
    }

    @Test
    void addPlayer() {
        when(playerRepository.findById(any(Integer.class))).thenReturn(Optional.of(getPlayerTest()));
        when(teamCall.getTeam(any(Integer.class))).thenReturn(getTeamTest());
        List<Object> result = playerService.addPlayer(getPlayerPojoTest());
        assertNotNull(result);
    }

    @Test
    void addPlayerFailed() {
        when(teamCall.getTeam(any(Integer.class))).thenReturn(getTeamTest());
        List<Object> result = playerService.addPlayer(getPlayerPojoTest());
        assertNotNull(result);
    }

    @Test
    void addPlayerInvalidTeam() {
        when(playerRepository.findById(any(Integer.class))).thenReturn(Optional.of(getPlayerTest()));
        List<Object> result = playerService.addPlayer(getPlayerPojoTest());
        assertNotNull(result);
    }


    @Test
    void updatePlayer() {
        when(playerRepository.findById(any(Integer.class))).thenReturn(Optional.of(getPlayerTest()));
        when(teamCall.getTeam(any(Integer.class))).thenReturn(getTeamTest());
        List<Object> result = playerService.updatePlayer(getPlayerPojoTest(),1);
        assertNotNull(result);
    }

    @Test
    void updatePlayerFailed() {
        when(teamCall.getTeam(any(Integer.class))).thenReturn(getTeamTest());
        List<Object> result = playerService.updatePlayer(getPlayerPojoTest(),1);
        assertNotNull(result);
    }

    @Test
    void updatePlayerInvalidTeam() {
        when(playerRepository.findById(any(Integer.class))).thenReturn(Optional.of(getPlayerTest()));
        List<Object> result = playerService.updatePlayer(getPlayerPojoTest(),1);
        assertNotNull(result);
    }

    private Player getPlayerTest(){
        Player player = new Player();
        player.setPlayerID(1);
        player.setFirstName("firstname");
        player.setLastName("lastname");
        player.setCountry("PH");
        player.setTeam(1);
        player.setLastUpdate(new Date());
        return player;
    }

    private List<Player> getTestPlayerList(){
        List<Player> playerList = new ArrayList<>();
        for(int i = 1; i <= 5; i++){
            Player player = getPlayerTest();
            player.setPlayerID(i);
            playerList.add(player);
        }
        return playerList;
    }

    private PlayerPojo getPlayerPojoTest(){
        PlayerPojo playerPojo = new PlayerPojo();
        playerPojo.setCountry("PH");
        playerPojo.setFirstName("firstNameTest");
        playerPojo.setLastName("lastNameTest");
        playerPojo.setTeamId(1);
        return playerPojo;
    }

    private PlayerDetails getPlayerDetailsTest(){
        PlayerDetails playerDetails = new PlayerDetails();
        playerDetails.setPlayerID(1);
        playerDetails.setCountry("PH");
        playerDetails.setTeam(getTeamTest());
        playerDetails.setLastName("lastNameTest");
        playerDetails.setFirstName("firstNameTest");
        playerDetails.setLastUpdate(new Date());
        return playerDetails;
    }

    private List<PlayerDetails> getPlayerDetailsListTest(){
        List<PlayerDetails> playerDetailsList = new ArrayList<>();
        for(int i = 0; i <= 5; i++){
            PlayerDetails playerDetails = new PlayerDetails();
            playerDetails.setPlayerID(i);
            playerDetailsList.add(playerDetails);
        }
        return playerDetailsList;
    }

    private Team getTeamTest(){
        Team team = new Team();
        team.setTeamID(1);
        team.setTeamName("nameTest");
        team.setLastUpdate(new Date());
        return team;
    }
}