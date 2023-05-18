package org.match_service.microservice;


import org.match_service.entity.Player;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${Player.name}", url = "${Player.URL}")
public interface PlayerCall {

    @GetMapping("/getPlayer/{player_id}")
    Player getPlayer(@PathVariable("player_id") Integer playerID);
}
