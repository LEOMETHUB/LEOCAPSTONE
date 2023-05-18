package org.match_service.microservice;


import org.match_service.entity.Team;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "${Team.name}",url = "${Team.URL}")
public interface TeamCall {


    @GetMapping("/getTeam/{team_id}")
    Team getTeam(@PathVariable("team_id") Integer teamID);
}
