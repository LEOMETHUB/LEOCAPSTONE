package org.sportmanagement.entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "tournament")
@Table(name = "tournaments", schema = "tournament_schema")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tournament_id")
    private int tournamentID;
    @Column(name = "tournament_name")
    private String tournamentName;
    @Column(name = "sport_category")
    private String tournamentCategory;
    @Column(name = "tournament_style")
    private String tournamentStyle;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name ="end_date")
    private Date endDate;
    @Column(name = "teams")
    private String teams;
    @Column(name = "last_update")
    private Date lastUpdate;


    public Tournament() {


    }

    public Tournament(int tournamentID, String tournamentName, String tournamentCategory, String tournamentStyle, Date startDate, Date endDate, String teams, Date lastUpdate) {
        this.tournamentID = tournamentID;
        this.tournamentName = tournamentName;
        this.tournamentCategory = tournamentCategory;
        this.tournamentStyle = tournamentStyle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.teams = teams;
        this.lastUpdate = lastUpdate;
    }

    public Tournament(String tournamentName, String tournamentCategory, String tournamentStyle, Date startDate, Date endDate, String teams, Date lastUpdate) {
        this.tournamentName = tournamentName;
        this.tournamentCategory = tournamentCategory;
        this.tournamentStyle = tournamentStyle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.teams = teams;
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

    public String getTeams() {
        return teams;
    }

    public void setTeams(String teams) {
        this.teams = teams;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}

