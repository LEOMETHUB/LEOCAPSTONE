package sporteventmanagement.microservice;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sporteventmanagement.entity.Match;

@FeignClient(name = "${Match.name}", url = "${Match.URL}")
public interface MatchCall {

    @GetMapping("/getMatch/{match_id}")
    Match getMatch(@PathVariable("match_id") Integer matchID);

}
