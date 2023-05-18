package org.sportmanagement.entity;

import java.util.Date;




public class Team {

    private int teamID;
    private String teamName;
    private Date lastUpdate;

    public Team() {
    }

    public Team(String teamName, Date lastUpdate) {
        this.teamName = teamName;
        this.lastUpdate = lastUpdate;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

}
