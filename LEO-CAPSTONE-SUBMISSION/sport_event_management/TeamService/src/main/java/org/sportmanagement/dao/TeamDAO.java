package org.sportmanagement.dao;

import org.sportmanagement.entity.Team;
import org.sportmanagement.pojo.TeamPojo;


import java.util.List;


public interface TeamDAO {

    List<Team> getTeams();
    Team getTeamByID(Integer teamID);
    String deleteTeam(int teamId);
    String addTeam(TeamPojo teamPojo);
}
