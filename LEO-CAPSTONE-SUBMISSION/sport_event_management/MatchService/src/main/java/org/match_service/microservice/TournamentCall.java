package org.match_service.microservice;


import org.match_service.entity.Tournament;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${Tournament.name}", url = "${Tournament.URL}")
public interface TournamentCall {

    @GetMapping("/getTournament/{tournament_id}")
    Tournament getTournament(@PathVariable("tournament_id") Integer tournamentID);
}
