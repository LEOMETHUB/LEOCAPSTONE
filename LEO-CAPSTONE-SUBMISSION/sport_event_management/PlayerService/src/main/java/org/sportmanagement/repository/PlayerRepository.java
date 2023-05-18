package org.sportmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.sportmanagement.entity.Player;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    @Query("FROM player WHERE " +
            "lower(firstName)  like lower(?1)  " +
            "or lower(lastName) like lower(?1) or lower(country) like lower(?1)")
    List<Player> searchPlayersByParam(String param);



}
