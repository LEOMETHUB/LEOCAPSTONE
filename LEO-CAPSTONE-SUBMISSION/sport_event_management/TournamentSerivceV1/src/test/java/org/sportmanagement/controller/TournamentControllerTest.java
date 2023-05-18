package org.sportmanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.sportmanagement.entity.Team;
import org.sportmanagement.entity.Tournament;
import org.sportmanagement.entity.TournamentDetails;
import org.sportmanagement.pojo.TournamentPojo;
import org.sportmanagement.repository.TournamentRepository;
import org.sportmanagement.service.TournamentService;
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
class TournamentControllerTest {

    @InjectMocks
    private  TournamentController tournamentController;
    @MockBean
    private TournamentService tournamentService;
    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(tournamentController).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
    }

    @Test
    void getAllTournaments() throws Exception {
        List<TournamentDetails> tournamentDetailsList = getTournamentDetailsList();
        when(tournamentService.getAllTournaments()).thenReturn(tournamentDetailsList);
        mockMvc.perform(MockMvcRequestBuilders.get("/tournaments/getTournaments")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(tournamentDetailsList)));
    }

    @Test
    void getTournament() throws Exception {
        Tournament tournament = getTestTournament();
        when(tournamentService.getTournament(any(Integer.class))).thenReturn(tournament);
        mockMvc.perform(MockMvcRequestBuilders.get("/getTournament/{tournament_id}",1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(tournament)));
    }

    @Test
    void addTournament() throws Exception {
        TournamentPojo tournamentPojo = getTournamentPojo();
        List<Object> result = new ArrayList<>();
        result.add(true);
        result.add("Insert Tournament Success.");
        when(tournamentService.addTournament(any(TournamentPojo.class))).thenReturn(result);
        mockMvc.perform(MockMvcRequestBuilders.post("/tournaments/addTournament")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(tournamentPojo))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Insert Tournament Success."));
    }

    @Test
    void addTournamentFailed() throws Exception {
        TournamentPojo tournamentPojo = getTournamentPojo();
        List<Object> result = new ArrayList<>();
        result.add(false);
        result.add(null);
        when(tournamentService.addTournament(any(TournamentPojo.class))).thenReturn(result);
        mockMvc.perform(MockMvcRequestBuilders.post("/tournaments/addTournament")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(tournamentPojo))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateTournament() throws Exception {
        TournamentPojo tournamentPojo = getTournamentPojo();
        List<Object> result = new ArrayList<>();
        result.add(true);
        result.add("Update Tournament Success.");
        when(tournamentService.updateTournament(any(TournamentPojo.class),any(Integer.class))).thenReturn(result);
        mockMvc.perform(MockMvcRequestBuilders.put("/tournaments/updateTournament")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(tournamentPojo))
                        .param("tournament_id","1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Update Tournament Success."));
    }

    @Test
    void updateTournamentFailed() throws Exception {
        TournamentPojo tournamentPojo = getTournamentPojo();
        List<Object> result = new ArrayList<>();
        result.add(false);
        result.add(null);
        when(tournamentService.updateTournament(any(TournamentPojo.class),any(Integer.class))).thenReturn(result);
        mockMvc.perform(MockMvcRequestBuilders.put("/tournaments/updateTournament")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(tournamentPojo))
                        .param("tournament_id","1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteTournament() throws Exception {
        String result = "Delete tournament Successful.";
        when(tournamentService.deleteTournament(any(Integer.class))).thenReturn(result);
        mockMvc.perform(MockMvcRequestBuilders.delete("/tournaments/deleteTournament")
                        .param("tournament_id", String.valueOf(1))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(result));
    }

    @Test
    void searchTournamentsByParam() throws Exception {
        List<TournamentDetails> tournamentDetailsList = getTournamentDetailsList();
        when(tournamentService.searchTournamentByParam(any(Integer.class),any(String.class))).thenReturn(tournamentDetailsList);
        mockMvc.perform(MockMvcRequestBuilders.get("/search/tournaments")
                        .param("tournament_id","1")
                        .param("search","1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(tournamentDetailsList)));

    }

    private TournamentPojo getTournamentPojo(){
        TournamentPojo tournamentPojo = new TournamentPojo();
        tournamentPojo.setTournamentCategory("categoryTest");
        tournamentPojo.setTournamentStyle("styleTest");
        tournamentPojo.setEndDate(new Date());
        tournamentPojo.setStartDate(new Date());
        tournamentPojo.setTeamList("1,2,3");
        return tournamentPojo;
    }

    private TournamentDetails getTournamentDetails(){
        TournamentDetails tournamentDetails = new TournamentDetails();
        tournamentDetails.setTournamentID(1);
        tournamentDetails.setTournamentCategory("testCategory");
        tournamentDetails.setTournamentStyle("testStyle");
        tournamentDetails.setTournamentName("nameTest");
        tournamentDetails.setEndDate(new Date());
        tournamentDetails.setLastUpdate(new Date());
        tournamentDetails.setTeam(getTeamList());
        return tournamentDetails;
    }

    private List<TournamentDetails> getTournamentDetailsList(){
        List<TournamentDetails> tournamentDetailsList = new ArrayList<>();
        for(int i = 1; i <= 5; i++){
            TournamentDetails tournamentDetails = new TournamentDetails();
            tournamentDetails.setTournamentID(i);
            tournamentDetailsList.add(tournamentDetails);
        }
        return tournamentDetailsList;
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

    private List<Tournament> getTestTournamentList(){
        List<Tournament> tournamentList = new ArrayList<>();
        for(int i = 0; i <= 5; i++){
            Tournament tournament = new Tournament();
            tournament.setTournamentID(i);
            tournamentList.add(tournament);
        }
        return tournamentList;
    }

    private Team getTeamTest(){
        Team team = new Team();
        team.setTeamID(1);
        team.setTeamName("nameTest");
        team.setLastUpdate(new Date());
        return team;
    }

    private Set<Team> getTeamList(){
        Set<Team> teamList = new HashSet<>();
        for(int i = 1; i <= 5; i++){
            Team team = getTeamTest();
            team.setTeamID(i);
            teamList.add(team);
        }
        return teamList;
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}