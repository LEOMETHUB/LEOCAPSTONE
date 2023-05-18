package org.sportmanagement.entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "player")
@Table(name = "players", schema = "player_schema")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private int playerID;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "country")
    private String country;
    @Column(name = "team_id")
    private Integer team;
    @Column(name = "last_update")
    private Date lastUpdate;

    public Player() {
    }

    public Player(int playerID, String firstName, String lastName, String country, Integer team, Date lastUpdate) {
        this.playerID = playerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.team = team;
        this.lastUpdate = lastUpdate;
    }

    public Player(String firstName, String lastName, String country, Integer team, Date lastUpdate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.team = team;
        this.lastUpdate = lastUpdate;
    }

    public Player(int playerID, String firstName, String lastName, String country, Date lastUpdate) {
        this.playerID = playerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.lastUpdate = lastUpdate;
    }

    public Player(String firstName, String lastName, String country, Date lastUpdate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.lastUpdate = lastUpdate;
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

    public Integer getTeam() {
        return team;
    }

    public void setTeam(Integer team) {
        this.team = team;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
