package org.sportmanagement.entity;

import java.util.Date;

public class PlayerDetails {

    private int playerID;
    private String firstName;
    private String lastName;
    private String country;
    private Team team;
    private Date lastUpdate;

    public PlayerDetails(int playerID, String firstName, String lastName, String country, Team team, Date lastUpdate) {
        this.playerID = playerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.team = team;
        this.lastUpdate = lastUpdate;
    }

    public PlayerDetails() {
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
