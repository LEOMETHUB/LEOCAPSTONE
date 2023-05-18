package org.sportmanagement.pojo;

public class PlayerPojo {

    private String firstName;
    private String lastName;
    private String country;
    private int teamId;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCountry() {
        return country;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}
