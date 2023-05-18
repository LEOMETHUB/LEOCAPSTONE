package org.sportmanagement.authentication;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "${Auth.name}",url = "${Auth.URL}")
public interface JwtService {

    @PostMapping("/userAuthenticate/{token}")
    Boolean validateToken(@PathVariable("token") String token);

    @GetMapping("/getToken")
    String getToken();

}
