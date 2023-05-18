package org.sportmanagement.service;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.sportmanagement.entity.Team;
import org.sportmanagement.entity.Tournament;
import org.sportmanagement.entity.TournamentDetails;
import org.sportmanagement.microservice.TeamCall;
import org.sportmanagement.pojo.TournamentPojo;
import org.sportmanagement.repository.TournamentRepository;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class TournamentServiceTest {

    @InjectMocks
    private TournamentService tournamentService;
    @Mock
    private TournamentRepository tournamentRepository;
    @Mock
    private TeamCall teamCall;
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    void getAllTournaments() {
        List<TournamentDetails> tournaments = tournamentService.getAllTournaments();
        assertNotNull(tournaments);
    }

    @Test
    void getTournament() {
        when(tournamentRepository.findById(any(Integer.class))).thenReturn(Optional.of(getTestTournament()));
        Tournament tournament = tournamentService.getTournament(any(Integer.class));
        assertNotNull(tournament);
    }

    @Test
    void deleteTournament() {
        when(tournamentRepository.findById(any(Integer.class))).thenReturn(Optional.of(getTestTournament()));
        when(teamCall.getTeam(any(Integer.class))).thenReturn(getTeamTest());
        String result = tournamentService.deleteTournament(1);
        assertNotNull(result);
    }
    @Test
    void deleteTournamentFailed() {
        when(teamCall.getTeam(any(Integer.class))).thenReturn(getTeamTest());
        String result = tournamentService.deleteTournament(1);
        assertNotNull(result);
    }


    @Test
    void searchTournamentByParam() {
        when(tournamentRepository.findById(any(Integer.class))).thenReturn(Optional.of(getTestTournament()));
        when(teamCall.getTeam(any(Integer.class))).thenReturn(getTeamTest() );
        List<TournamentDetails> tournamentDetailsList= tournamentService.searchTournamentByParam(1,"nameTest");
        assertNotNull(tournamentDetailsList);
    }

    @Test
    void addTournament() {
        when(tournamentRepository.findById(any(Integer.class))).thenReturn(Optional.of(getTestTournament()));
        when(teamCall.getTeam(any(Integer.class))).thenReturn(getTeamTest() );
        List<Object> result = tournamentService.addTournament(getTournamentPojo());
        assertNotNull(result);
    }

    @Test
    void addTournamentInvalidStartDate() {
        when(tournamentRepository.findById(any(Integer.class))).thenReturn(Optional.of(getTestTournament()));
        when(teamCall.getTeam(any(Integer.class))).thenReturn(getTeamTest() );
        List<Object> result = tournamentService.addTournament(getTournamentPojoStartDateInvalid());
        assertNotNull(result);
    }

    @Test
    void addTournamentInvalidEndDate() {
        when(tournamentRepository.findById(any(Integer.class))).thenReturn(Optional.of(getTestTournament()));
        when(teamCall.getTeam(any(Integer.class))).thenReturn(getTeamTest() );
        List<Object> result = tournamentService.addTournament(getTournamentPojoEndDateInvalid());
        assertNotNull(result);
    }

    @Test
    void addTournamentInvalidTeam() {
        when(tournamentRepository.findById(any(Integer.class))).thenReturn(Optional.of(getTestTournament()));
        List<Object> result = tournamentService.addTournament(getTournamentPojo());
        assertNotNull(result);
    }

    @Test
    void updateTournament() {
        when(tournamentRepository.findById(any(Integer.class))).thenReturn(Optional.of(getTestTournament()));
        when(teamCall.getTeam(any(Integer.class))).thenReturn(getTeamTest() );
        List<Object> result = tournamentService.updateTournament(getTournamentPojo(),1);
        assertNotNull(result);
    }

    @Test
    void updateTournamentFailed() {
        when(teamCall.getTeam(any(Integer.class))).thenReturn(getTeamTest() );
        List<Object> result = tournamentService.updateTournament(getTournamentPojo(),1);
        assertNotNull(result);
    }
    @Test
    void updateTournamentInvalidTeam() {
        when(tournamentRepository.findById(any(Integer.class))).thenReturn(Optional.of(getTestTournament()));
        List<Object> result = tournamentService.updateTournament(getTournamentPojo(),1);
        assertNotNull(result);
    }
    @Test
    void updateTournamentInvalidStartDate() {
        when(tournamentRepository.findById(any(Integer.class))).thenReturn(Optional.of(getTestTournament()));
        when(teamCall.getTeam(any(Integer.class))).thenReturn(getTeamTest() );
        List<Object> result = tournamentService.updateTournament(getTournamentPojoStartDateInvalid(),1);
        assertNotNull(result);
    }

    @Test
    void updateTournamentInvalidEndDate() {
        when(tournamentRepository.findById(any(Integer.class))).thenReturn(Optional.of(getTestTournament()));
        when(teamCall.getTeam(any(Integer.class))).thenReturn(getTeamTest() );
        List<Object> result = tournamentService.updateTournament(getTournamentPojoEndDateInvalid(),1);
        assertNotNull(result);
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

    private TournamentPojo getTournamentPojoStartDateInvalid(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date startdate = calendar.getTime();
        TournamentPojo tournamentPojo = new TournamentPojo();
        tournamentPojo.setTournamentCategory("categoryTest");
        tournamentPojo.setTournamentStyle("styleTest");
        tournamentPojo.setEndDate(startdate);
        tournamentPojo.setStartDate(startdate);
        tournamentPojo.setTeamList("1,2,3");
        return tournamentPojo;
    }

    private TournamentPojo getTournamentPojoEndDateInvalid(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date yesterday = calendar.getTime();
        TournamentPojo tournamentPojo = new TournamentPojo();
        tournamentPojo.setTournamentCategory("categoryTest");
        tournamentPojo.setTournamentStyle("styleTest");
        tournamentPojo.setEndDate(yesterday);
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

}