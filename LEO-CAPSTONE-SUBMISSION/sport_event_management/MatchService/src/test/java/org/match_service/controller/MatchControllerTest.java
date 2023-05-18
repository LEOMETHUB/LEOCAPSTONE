package org.match_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.match_service.entity.*;
import org.match_service.pojo.MatchPojo;
import org.match_service.service.MatchService;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
class MatchControllerTest {

    @InjectMocks
    private MatchController matchController;

    @MockBean
    private MatchService matchService;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(matchController).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
    }

    @Test
    void getAllMatches() throws Exception {
        List<MatchDetails> matchList = getTestMatchList();
        when(matchService.getAllMatches()).thenReturn(matchList);
        mockMvc.perform(MockMvcRequestBuilders.get("/matches/getMatches")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(JsonMapper(matchList)));
    }

    @Test
    void getMatchByID() throws Exception {
        Match match = getTestMatch();
        when(matchService.getMatch(any(Integer.class))).thenReturn(match);
        mockMvc.perform(MockMvcRequestBuilders.get("/getMatch/{match_id}",1)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(JsonMapper(match)));
    }

    @Test
    void searchMatchesByParam() throws Exception {
        List<MatchDetails> matchList = getTestMatchList();
        when(matchService.searchMatchesByParam(any(Integer.class),any(Integer.class))).thenReturn(matchList);
        mockMvc.perform(MockMvcRequestBuilders.get("/matches/search")
                    .param("match_id","1")
                    .param("search","1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(JsonMapper(matchList)));
    }

    @Test
    void addMatch() throws Exception {
        MatchPojo matchPojo = getTestMatchPojo();
        List<Object> result = new ArrayList<>();
        result.add(true);
        result.add("Add Match Success!");
        when(matchService.saveMatch(any(MatchPojo.class),any(Integer.class),any(String.class))).thenReturn(result);
        mockMvc.perform(MockMvcRequestBuilders.post("/matches/addMatch")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JsonMapper(matchPojo))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Add Match Success!"));
    }

    @Test
    void addMatchField() throws Exception {
        MatchPojo matchPojo = getTestMatchPojo();
        List<Object> result = new ArrayList<>();
        result.add(false);
        result.add(null);
        when(matchService.saveMatch(any(MatchPojo.class),any(Integer.class),any(String.class))).thenReturn(result);
        mockMvc.perform(MockMvcRequestBuilders.post("/matches/addMatch")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JsonMapper(matchPojo))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateMatch() throws Exception {
        MatchPojo matchPojo = getTestMatchPojo();
        List<Object> result = new ArrayList<>();
        result.add(true);
        result.add("Update Match Success!");
        when(matchService.saveMatch(any(MatchPojo.class),any(Integer.class),any(String.class))).thenReturn(result);
        mockMvc.perform(MockMvcRequestBuilders.put("/matches/updateMatch")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JsonMapper(matchPojo))
                        .param("match_id","1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Update Match Success!"));
    }

    @Test
    void updateMatchFailed() throws Exception {
        MatchPojo matchPojo = getTestMatchPojo();
        List<Object> result = new ArrayList<>();
        result.add(false);
        result.add(null);
        when(matchService.saveMatch(any(MatchPojo.class),any(Integer.class),any(String.class))).thenReturn(result);
        mockMvc.perform(MockMvcRequestBuilders.put("/matches/updateMatch")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JsonMapper(matchPojo))
                        .param("match_id","1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteMatch() throws Exception {
        String result = "Delete Match Successful!";
        when(matchService.deleteMatch(any(Integer.class))).thenReturn(result);
        mockMvc.perform(MockMvcRequestBuilders.delete("/matches/deleteMatch")
                        .param("match_id", String.valueOf(1))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(result));
    }

    private Match getTestMatch(){
        Match match = new Match();
        match.setMatchID(1);
        match.setFieldID(1);
        match.setTournamentID(1);
        match.setTeams("1,2,3,4");
        match.setPlayers("1,2,3,4");
        match.setStartDate(new Date());
        match.setEndDate(new Date());
        match.setLastUpdate(new Date());
        return match;
    }

    private MatchDetails getSampleMatch(){
        MatchDetails match = new MatchDetails();
        match.setFieldID(getTestField());
        match.setTournamentID(getTestTournament());
        match.setTeams(getTeamList());
        match.setPlayers(getTestPlayerList());
        match.setStartDate(new Date());
        match.setEndDate(new Date());
        match.setLastUpdate(new Date());
        return match;
    }

    private MatchPojo getTestMatchPojo(){
        MatchPojo matchPojo = new MatchPojo();
        matchPojo.setFieldId(1);
        matchPojo.setTournamentId(1);
        matchPojo.setPlayers("1,2,3");
        matchPojo.setTeams("1,2,3");
        matchPojo.setStartDate(new Date());
        matchPojo.setEndDate(new Date());
        return matchPojo;
    }

    private List<MatchDetails> getTestMatchList(){
        List<MatchDetails> matchList = new ArrayList<>();
        for(int i = 1; i <= 5; i++){
            MatchDetails match = getSampleMatch();
            match.setMatchID(i);
            matchList.add(match);
        }
        return matchList;
    }

    private Field getTestField(){
        Field field = new Field();
        field.setFieldID(1);
        field.setFieldName("testName");
        field.setFieldAddress("testAddress");
        field.setFieldCapacity(10000);
        field.setLastUpdate(new Date());
        return field;
    }

    private Tournament getTestTournament(){
        Tournament tournament = new Tournament();
        tournament.setTournamentID(1);
        tournament.setTournamentName("testName");
        tournament.setTournamentCategory("testCategory");
        tournament.setTournamentStyle("testStyle");
        tournament.setTeams("1,2,3,4");
        tournament.setStartDate(new Date());
        tournament.setEndDate(new Date());
        tournament.setLastUpdate(new Date());
        return tournament;
    }

    private Player getSamplePlayer(){
        Player player = new Player();
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
            Player player = getSamplePlayer();
            player.setPlayerID(i);
            playerList.add(player);
        }
        return playerList;
    }

    private Set<Team> getTeamList(){
        Set<Team> teamList = new HashSet<>();
        for(int i = 1; i <= 5; i++){
            Team team = new Team();
            team.setTeamID(i);
            team.setTeamName("testName");
            teamList.add(team);
        }
        return teamList;
    }

    private static String JsonMapper(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}