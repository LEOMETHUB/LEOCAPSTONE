package org.match_service.entity;


import javax.persistence.*;
import java.util.Date;

@Entity(name = "match")
@Table(name = "matches", schema = "match_schema")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private int matchID;
    @Column(name ="field_id")
    private int fieldID;
    @Column(name="tournament_id")
    private int tournamentID;
    @Column(name="players")
    private String players;
    @Column(name = "teams")
    private  String teams;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name ="end_date")
    private Date endDate;
    @Column
    private Date lastUpdate;

    public Match() {
    }

    public Match(int fieldID, int tournamentID, String players, String teams, Date startDate, Date endDate, Date lastUpdate) {
        this.fieldID = fieldID;
        this.tournamentID = tournamentID;
        this.players = players;
        this.teams = teams;
        this.startDate = startDate;
        this.endDate = endDate;
        this.lastUpdate = lastUpdate;
    }

    public Match(int matchID, int fieldID, int tournamentID, String players, String teams, Date startDate, Date endDate, Date lastUpdate) {
        this.matchID = matchID;
        this.fieldID = fieldID;
        this.tournamentID = tournamentID;
        this.players = players;
        this.teams = teams;
        this.startDate = startDate;
        this.endDate = endDate;
        this.lastUpdate = lastUpdate;
    }

    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public int getFieldID() {
        return fieldID;
    }

    public void setFieldID(int fieldID) {
        this.fieldID = fieldID;
    }

    public int getTournamentID() {
        return tournamentID;
    }

    public void setTournamentID(int tournamentID) {
        this.tournamentID = tournamentID;
    }

    public String getPlayers() {
        return players;
    }

    public void setPlayers(String players) {
        this.players = players;
    }

    public String getTeams() {
        return teams;
    }

    public void setTeams(String teams) {
        this.teams = teams;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
