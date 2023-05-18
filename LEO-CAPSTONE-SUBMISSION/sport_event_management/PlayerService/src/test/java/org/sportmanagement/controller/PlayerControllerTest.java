package org.sportmanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.sportmanagement.entity.Player;
import org.sportmanagement.entity.PlayerDetails;
import org.sportmanagement.entity.Team;
import org.sportmanagement.pojo.PlayerPojo;
import org.sportmanagement.service.PlayerService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
class PlayerControllerTest {

    @InjectMocks
    private  PlayerController playerController;
    @MockBean
    private PlayerService playerService;
    private MockMvc mockMvc;
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(playerController).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
    }

    @Test
    void getAllPlayers() throws Exception {
        List<PlayerDetails> playerDetailsList = getPlayerDetailsListTest();
        when(playerService.getAllPlayer()).thenReturn(playerDetailsList);
        mockMvc.perform(MockMvcRequestBuilders.get("/players/getPlayers")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(playerDetailsList)));
    }

    @Test
    void getPlayerByID() throws Exception {
        Player player = getPlayerTest();
        when(playerService.getPlayerByID(any(Integer.class))).thenReturn(player);
        mockMvc.perform(MockMvcRequestBuilders.get("/getPlayer/{player_id}",1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(player)));
    }

    @Test
    void addPlayer() throws Exception {
        PlayerPojo playerPojo = getPlayerPojoTest();
        List<Object> result = new ArrayList<>();
        result.add(true);
        result.add("Insert Player Success!");
        when(playerService.addPlayer(any(PlayerPojo.class))).thenReturn(result);
        mockMvc.perform(MockMvcRequestBuilders.post("/players/addPlayer")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(playerPojo))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Insert Player Success!"));
    }

    @Test
    void addPlayerFailed() throws Exception {
        PlayerPojo playerPojo = getPlayerPojoTest();
        List<Object> result = new ArrayList<>();
        result.add(false);
        result.add(null);
        when(playerService.addPlayer(any(PlayerPojo.class))).thenReturn(result);
        mockMvc.perform(MockMvcRequestBuilders.post("/players/addPlayer")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(playerPojo))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updatePlayer() throws Exception {
        PlayerPojo playerPojo = getPlayerPojoTest();
        List<Object> result = new ArrayList<>();
        result.add(true);
        result.add("Update Player Success.");
        when(playerService.updatePlayer(any(PlayerPojo.class),any(Integer.class))).thenReturn(result);
        mockMvc.perform(MockMvcRequestBuilders.put("/players/updatePlayer")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(playerPojo))
                        .param("player_id","1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Update Player Success."));
    }
    @Test
    void updatePlayerFailed() throws Exception {
        PlayerPojo playerPojo = getPlayerPojoTest();
        List<Object> result = new ArrayList<>();
        result.add(false);
        result.add(null);
        when(playerService.updatePlayer(any(PlayerPojo.class),any(Integer.class))).thenReturn(result);
        mockMvc.perform(MockMvcRequestBuilders.put("/players/updatePlayer")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(playerPojo))
                        .param("player_id","1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deletePlayer() throws Exception {
        String result = "Delete player Successful.";
        when(playerService.deletePlayer(any(Integer.class))).thenReturn(result);
        mockMvc.perform(MockMvcRequestBuilders.delete("/players/deletePlayer")
                        .param("player_id", String.valueOf(1))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(result));
    }

    @Test
    void searchPlayersByParam() throws Exception {
        List<PlayerDetails> playerDetailsList = getPlayerDetailsListTest();
        when(playerService.searchPlayersByParam(any(Integer.class),any(String.class))).thenReturn(playerDetailsList);
        mockMvc.perform(MockMvcRequestBuilders.get("/search/players")
                        .param("player_id","1")
                        .param("search","1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(playerDetailsList)));
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

    private Set<Player> getTestPlayerList(){
        Set<Player> playerList = new HashSet<>();
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

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}