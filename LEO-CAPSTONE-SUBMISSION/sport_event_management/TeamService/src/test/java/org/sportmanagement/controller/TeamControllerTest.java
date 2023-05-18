package org.sportmanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.sportmanagement.entity.Team;
import org.sportmanagement.exception.InvalidInputException;
import org.sportmanagement.pojo.TeamPojo;
import org.sportmanagement.repository.TeamRepository;
import org.sportmanagement.service.TeamService;
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
class TeamControllerTest {

    @InjectMocks
    private TeamController teamController;
    @MockBean
    private  TeamService teamService;
    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(teamController).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
    }

    @Test
    void getTeam() throws Exception {
        Team team = teamSample();
        when(teamService.getTeamByID(any(Integer.class))).thenReturn(team);
        mockMvc.perform(MockMvcRequestBuilders.get("/getTeam/{team_id}",1).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(team)));
    }

    @Test
    void testGetAllTeams() throws Exception {
        List<Team> teamList = teamListTemplate();
        when(teamService.getTeams()).thenReturn(teamList);
        mockMvc.perform(MockMvcRequestBuilders.get("/teams/getTeams").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(teamList)));

    }

    @Test
    void addTeam() throws Exception {
        TeamPojo teamPojo = new TeamPojo();
        teamPojo.setTeamName("test");
        String result = "Insert Team Success.";
        when(teamService.addTeam(any(TeamPojo.class))).thenReturn(result);
        mockMvc.perform(MockMvcRequestBuilders.post("/teams/addTeam")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(teamPojo))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Insert Team Success."));
    }


    @Test
    void deleteTeam() throws Exception {
        String result = "Delete Team Success.";
        when(teamService.deleteTeam(any(Integer.class))).thenReturn(result);
        mockMvc.perform(MockMvcRequestBuilders.delete("/teams/deleteTeam")
                        .param("team_id", String.valueOf(1))
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(result));


    }


    private List<Team> teamListTemplate(){
        List<Team> teamList = new ArrayList<>();
        for(int i = 0; i <= 3; i++){
            Team team = new Team();
            team.setTeamName("team"+i);
            team.setTeamID(i);
            team.setLastUpdate(new Date());
            teamList.add(team);
        }
        return teamList;
    }

    private Team teamSample(){
        Team team = new Team();
        team.setTeamName("team1");
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

    private List<Object> getResult(){
        List<Object> result = new ArrayList<>();
        result.add(true);
        result.add("Insert Team Success.");
        return result;
    }







}