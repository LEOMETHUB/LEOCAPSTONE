package org.sportmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.sportmanagement.entity.Team;


@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {

}
