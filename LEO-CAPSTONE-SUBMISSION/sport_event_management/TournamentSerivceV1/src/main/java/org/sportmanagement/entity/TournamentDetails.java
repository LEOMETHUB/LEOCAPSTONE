package org.sportmanagement.entity;

import java.util.Date;
import java.util.Set;

public class TournamentDetails {

    private int tournamentID;
    private String tournamentName;
    private String tournamentCategory;
    private String tournamentStyle;
    private Date startDate;
    private Date endDate;
    private Set<Team> team;
    private Date lastUpdate;



    public TournamentDetails() {
    }

    public TournamentDetails(int tournamentID, String tournamentName, String tournamentCategory, String tournamentStyle, Date startDate, Date endDate, Set<Team> team, Date lastUpdate) {
        this.tournamentID = tournamentID;
        this.tournamentName = tournamentName;
        this.tournamentCategory = tournamentCategory;
        this.tournamentStyle = tournamentStyle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.team = team;
        this.lastUpdate = lastUpdate;
    }

    public int getTournamentID() {
        return tournamentID;
    }

    public void setTournamentID(int tournamentID) {
        this.tournamentID = tournamentID;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public String getTournamentCategory() {
        return tournamentCategory;
    }

    public void setTournamentCategory(String tournamentCategory) {
        this.tournamentCategory = tournamentCategory;
    }

    public String getTournamentStyle() {
        return tournamentStyle;
    }

    public void setTournamentStyle(String tournamentStyle) {
        this.tournamentStyle = tournamentStyle;
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

    public Set<Team> getTeam() {
        return team;
    }

    public void setTeam(Set<Team> team) {
        this.team = team;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
