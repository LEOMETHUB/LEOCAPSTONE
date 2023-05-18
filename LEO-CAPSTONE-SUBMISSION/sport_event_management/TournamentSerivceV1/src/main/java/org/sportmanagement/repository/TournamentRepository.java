package org.sportmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.sportmanagement.entity.Tournament;

import java.util.List;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Integer> {

    @Query("FROM tournament WHERE " +
            "lower(tournamentName)  like %:param%")
    List<Tournament> searchTournamentByParam(String param);

    @Query(value = "SELECT tm.team_id " +
            " FROM tournament_schema.tournaments t " +
            " INNER JOIN tournament_schema.Tournament_teams tm " +
            " ON t.tournament_id = tm.tournament_id " +
            " Where t.tournament_id = :tournamentID" +
            " Order by tm.team_id", nativeQuery = true)
    List<Integer> getTournamentTeamID(int tournamentID);

    @Query(value = "INSERT INTO tournament_schema.tournament_teams(" +
            "tournament_id, team_id) " +
            "VALUES (:tournamentID, :teamID)",nativeQuery = true)
    void insertTournamentTeamRelationTable(Integer tournamentID, Integer teamID);


}
