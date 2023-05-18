package org.sportmanagement.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "teams", schema = "team_schema")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private int teamID;
    @Column(name = "team_name")
    private String teamName;
    @Column(name = "last_update")
    private Date lastUpdate;

    public Team() {
    }

    public Team(int teamID, String teamName, Date lastUpdate) {
        this.teamID = teamID;
        this.teamName = teamName;
        this.lastUpdate = lastUpdate;
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


