package org.match_service.repository;

import org.match_service.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Integer> {


    @Query(value = "select * from match_schema.matches where match_id = :param" +
            " and field_id= :param and tournament_id = :param", nativeQuery = true)
    List<Match> searchMatchesByParam(Integer param);




}
