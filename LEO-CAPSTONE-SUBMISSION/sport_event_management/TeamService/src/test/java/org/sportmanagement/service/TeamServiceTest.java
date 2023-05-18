package org.sportmanagement.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.sportmanagement.entity.Team;
import org.sportmanagement.exception.InvalidInputException;
import org.sportmanagement.pojo.TeamPojo;
import org.sportmanagement.repository.TeamRepository;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
class TeamServiceTest {

    @InjectMocks
    private TeamService teamService;

    @Mock
    private TeamRepository teamRepository;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getTeams() {
        when(teamRepository.findAll()).thenReturn(teamListTemplate());
        List<Team> teamList = teamService.getTeams();
        assertNotNull(teamList);
    }

    @Test
    void getTeamByID() {
        when(teamRepository.findById(any(Integer.class))).thenReturn(Optional.of(teamSample()));
        Team team = teamService.getTeamByID(any(Integer.class));
        assertNotNull(team);
    }

    @Test
    void deleteTeam() {
        String result = "Delete Team Success.";
        when(teamRepository.findById(any(Integer.class))).thenReturn(Optional.of(teamSample()));
        String testResult = teamService.deleteTeam(1);
        assertEquals(result,testResult);
    }
    @Test
    void deleteTeam_invalidTeamID() {
        exception.expect(InvalidInputException.class);
        when(teamRepository.findAll()).thenReturn(teamListTemplate());
        teamService.deleteTeam(0);

    }


    @Test
    void addTeam() {
        TeamPojo teamPojo = new TeamPojo();
        teamPojo.setTeamName("test");
        when(teamRepository.findAll()).thenReturn(teamListTemplate());
        assertNotNull(teamService.addTeam(teamPojo));
    }

    @Test
    void addTeamFailed() {
        TeamPojo teamPojo = new TeamPojo();
        teamPojo.setTeamName("test1212132132!@#$%@1321321321321321321321321321321321321");
        when(teamRepository.findById(any(Integer.class))).thenReturn(Optional.of(teamSample()));
        String testResult = teamService.addTeam(teamPojo);
        assertNotNull(testResult);
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
        team.setTeamID(1);
        team.setTeamName("team1");
        team.setLastUpdate(new Date());
        return team;
    }

    private List<Object> getResult(){
        List<Object> result = new ArrayList<>();
        result.add(true);
        result.add("Insert Team Success.");
        return result;
    }
}