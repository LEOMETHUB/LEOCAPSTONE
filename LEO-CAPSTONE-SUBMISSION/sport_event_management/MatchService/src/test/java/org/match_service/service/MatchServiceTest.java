package org.match_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.match_service.entity.*;
import org.match_service.exception.InvalidInputException;
import org.match_service.microservice.FieldCall;
import org.match_service.microservice.PlayerCall;
import org.match_service.microservice.TeamCall;
import org.match_service.microservice.TournamentCall;
import org.match_service.pojo.MatchPojo;
import org.match_service.repository.MatchRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class MatchServiceTest {

    @InjectMocks
    private MatchService matchService;
    @Mock
    private MatchRepository matchRepository;
    @Mock
    private FieldCall fieldCall;
    @Mock
    private PlayerCall playerCall;
    @Mock
    private TeamCall teamCall;
    @Mock
    private TournamentCall tournamentCall;
    @Rule
    public ExpectedException exception = ExpectedException.none();



    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void getAllMatches() {
        List<MatchDetails> matches = matchService.getAllMatches();
        assertNotNull(matches);
    }

    @Test
    void getMatch() {
        when(matchRepository.findById(any(Integer.class))).thenReturn(Optional.of(getTestMatch()));
        Match match = matchService.getMatch(any(Integer.class));
        assertNotNull(match);
    }


    @Test
    void searchMatchesByParamMatchID() {
        when(matchRepository.findById(any(Integer.class))).thenReturn(Optional.of(getTestMatch()));
        when(matchRepository.searchMatchesByParam(any(Integer.class))).thenReturn(getListMatch());
        when(tournamentCall.getTournament(any(Integer.class))).thenReturn(getTestTournament());
        when(fieldCall.getField(any(Integer.class))).thenReturn(getTestField());
        when(playerCall.getPlayer(any(Integer.class))).thenReturn(getSamplePlayer());
        when(teamCall.getTeam(any(Integer.class))).thenReturn(getTestTeam());
        List<MatchDetails> matches= matchService.searchMatchesByParam(2,2);
        assertNotNull(matches);
    }

    @Test
    void deleteMatch() {
        when(matchRepository.findById(any(Integer.class))).thenReturn(Optional.of(getSample()));
        String result = matchService.deleteMatch(1);
        assertNotNull(result);
    }

    @Test
    void deleteMatchFailed() {
        exception.expect(InvalidInputException.class);
        when(matchRepository.findAll()).thenReturn(getListMatch());
        matchService.deleteMatch(0);
    }

    @Test
    void saveMatchInsert() {
        when(matchRepository.findById(any(Integer.class))).thenReturn(Optional.of(getTestMatch()));
        when(matchRepository.searchMatchesByParam(any(Integer.class))).thenReturn(getListMatch());
        when(tournamentCall.getTournament(any(Integer.class))).thenReturn(getTestTournament());
        when(fieldCall.getField(any(Integer.class))).thenReturn(getTestField());
        when(playerCall.getPlayer(any(Integer.class))).thenReturn(getSamplePlayer());
        when(teamCall.getTeam(any(Integer.class))).thenReturn(getTestTeam());
        List<Object> result = matchService.saveMatch(getTestMatchPojo(),0,"insert");
        assertNotNull(result);
    }

    @Test
    void saveMatchUpdate() {
        when(matchRepository.findById(any(Integer.class))).thenReturn(Optional.of(getTestMatch()));
        when(matchRepository.searchMatchesByParam(any(Integer.class))).thenReturn(getListMatch());
        when(tournamentCall.getTournament(any(Integer.class))).thenReturn(getTestTournament());
        when(fieldCall.getField(any(Integer.class))).thenReturn(getTestField());
        when(playerCall.getPlayer(any(Integer.class))).thenReturn(getSamplePlayer());
        when(teamCall.getTeam(any(Integer.class))).thenReturn(getTestTeam());
        List<Object> result = matchService.saveMatch(getTestMatchPojo(),1,"update");
        assertNotNull(result);
    }

    @Test
    void saveMatchInvalidIDTournament() {
        when(matchRepository.findById(any(Integer.class))).thenReturn(Optional.of(getTestMatch()));
        when(matchRepository.searchMatchesByParam(any(Integer.class))).thenReturn(getListMatch());
        when(fieldCall.getField(any(Integer.class))).thenReturn(getTestField());
        when(playerCall.getPlayer(any(Integer.class))).thenReturn(getSamplePlayer());
        when(teamCall.getTeam(any(Integer.class))).thenReturn(getTestTeam());
        List<Object> result = matchService.saveMatch(getTestMatchPojo(),1,"update");
        assertNotNull(result);
    }

    @Test
    void saveMatchInvalidField() {
        when(matchRepository.findById(any(Integer.class))).thenReturn(Optional.of(getTestMatch()));
        when(matchRepository.searchMatchesByParam(any(Integer.class))).thenReturn(getListMatch());
        when(tournamentCall.getTournament(any(Integer.class))).thenReturn(getTestTournament());
        when(playerCall.getPlayer(any(Integer.class))).thenReturn(getSamplePlayer());
        when(teamCall.getTeam(any(Integer.class))).thenReturn(getTestTeam());
        List<Object> result = matchService.saveMatch(getTestMatchPojo(),1,"update");
        assertNotNull(result);
    }

    @Test
    void saveMatchInvalidTeam() {
        when(matchRepository.findById(any(Integer.class))).thenReturn(Optional.of(getTestMatch()));
        when(matchRepository.searchMatchesByParam(any(Integer.class))).thenReturn(getListMatch());
        when(tournamentCall.getTournament(any(Integer.class))).thenReturn(getTestTournament());
        when(fieldCall.getField(any(Integer.class))).thenReturn(getTestField());
        when(playerCall.getPlayer(any(Integer.class))).thenReturn(getSamplePlayer());
        List<Object> result = matchService.saveMatch(getTestMatchPojo(),1,"update");
        assertNotNull(result);
    }

    @Test
    void saveMatchInvalidPlayer() {
        when(matchRepository.findById(any(Integer.class))).thenReturn(Optional.of(getTestMatch()));
        when(matchRepository.searchMatchesByParam(any(Integer.class))).thenReturn(getListMatch());
        when(tournamentCall.getTournament(any(Integer.class))).thenReturn(getTestTournament());
        when(fieldCall.getField(any(Integer.class))).thenReturn(getTestField());
        when(teamCall.getTeam(any(Integer.class))).thenReturn(getTestTeam());
        List<Object> result = matchService.saveMatch(getTestMatchPojo(),1,"update");
        assertNotNull(result);
    }

    @Test
    void saveMatchInvalidDate() {
        when(tournamentCall.getTournament(any(Integer.class))).thenReturn(getTestTournament());
        when(fieldCall.getField(any(Integer.class))).thenReturn(getTestField());
        when(playerCall.getPlayer(any(Integer.class))).thenReturn(getSamplePlayer());
        when(teamCall.getTeam(any(Integer.class))).thenReturn(getTestTeam());
        List<Object> result = matchService.saveMatch(getTestMatchPojo(),1,"update");
        assertNotNull(result);
    }

    @Test
    void saveMatchInvalidEndDate() {
        when(matchRepository.findById(any(Integer.class))).thenReturn(Optional.of(getTestMatch()));
        when(tournamentCall.getTournament(any(Integer.class))).thenReturn(getTestTournament());
        when(fieldCall.getField(any(Integer.class))).thenReturn(getTestField());
        when(playerCall.getPlayer(any(Integer.class))).thenReturn(getSamplePlayer());
        when(teamCall.getTeam(any(Integer.class))).thenReturn(getTestTeam());
        List<Object> result = matchService.saveMatch(getTestMatchPojoInvalidEndDate(),1,"update");
        assertNotNull(result);
    }

    @Test
    void saveMatchInvalidStartDate() {
        when(matchRepository.findById(any(Integer.class))).thenReturn(Optional.of(getTestMatch()));
        when(tournamentCall.getTournament(any(Integer.class))).thenReturn(getTestTournament());
        when(fieldCall.getField(any(Integer.class))).thenReturn(getTestField());
        when(playerCall.getPlayer(any(Integer.class))).thenReturn(getSamplePlayer());
        when(teamCall.getTeam(any(Integer.class))).thenReturn(getTestTeam());
        List<Object> result = matchService.saveMatch(getTestMatchPojoInvalidStartDate(),1,"update");
        assertNotNull(result);
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

    private Match getSample(){
        Match match = new Match();
        match.setFieldID(1);
        match.setTournamentID(1);
        match.setTeams("1,2,3,4");
        match.setPlayers("1,2,3,4");
        match.setStartDate(new Date());
        match.setEndDate(new Date());
        match.setLastUpdate(new Date());
        return match;
    }

    private List<Match> getListMatch(){
        List<Match> matchList = new ArrayList<>();
        for(int i = 1; i <= 5; i++){
            Match match = getSample();
            match.setMatchID(i);
            matchList.add(match);
        }
        return matchList;
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

    private MatchPojo getTestMatchPojoInvalidEndDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date yesterday = calendar.getTime();
        MatchPojo matchPojo = new MatchPojo();
        matchPojo.setFieldId(1);
        matchPojo.setTournamentId(1);
        matchPojo.setPlayers("1,2,3");
        matchPojo.setTeams("1,2,3");
        matchPojo.setStartDate(new Date());
        matchPojo.setEndDate(yesterday);
        return matchPojo;
    }

    private MatchPojo getTestMatchPojoInvalidStartDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date startdate = calendar.getTime();
        MatchPojo matchPojo = new MatchPojo();
        matchPojo.setFieldId(1);
        matchPojo.setTournamentId(1);
        matchPojo.setPlayers("1,2,3");
        matchPojo.setTeams("1,2,3");
        matchPojo.setStartDate(startdate);
        matchPojo.setEndDate(startdate);
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

    private Team getTestTeam(){
        Team team = new Team();
        team.setTeamID(1);
        team.setTeamName("testName");
        return team;
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

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}