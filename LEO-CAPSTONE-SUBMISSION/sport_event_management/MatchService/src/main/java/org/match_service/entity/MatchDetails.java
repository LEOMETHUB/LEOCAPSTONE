package org.match_service.entity;


import java.util.Date;
import java.util.Set;

public class MatchDetails {

    private int matchID;
    private Field fieldID;
    private Tournament tournamentID;
    private Set<Player> players;
    private Set<Team> teams;
    private Date startDate;
    private Date endDate;
    private Date lastUpdate;

    public MatchDetails() {
    }

    public MatchDetails(int matchID, Field fieldID, Tournament tournamentID, Set<Player> players, Set<Team> teams, Date startDate, Date endDate, Date lastUpdate) {
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

    public Field getFieldID() {
        return fieldID;
    }

    public void setFieldID(Field fieldID) {
        this.fieldID = fieldID;
    }

    public Tournament getTournamentID() {
        return tournamentID;
    }

    public void setTournamentID(Tournament tournamentID) {
        this.tournamentID = tournamentID;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
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
