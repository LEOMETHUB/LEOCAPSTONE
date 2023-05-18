package org.authentication.controller;

import org.authentication.pojo.AuthRequest;
import org.authentication.pojo.UserPojo;
import org.authentication.service.JWTService;
import org.authentication.service.TokenService;
import org.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;


@RestController
@RequestMapping("/sportManagement/auth")
public class AppController {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register/form")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addNewUser(@RequestBody UserPojo userPojo) {
        return userService.addUser(userPojo);
    }

    @GetMapping("/getToken")
    public String getToken() {
        return tokenService.getToken();
    }

    @PostMapping("/userAuthenticate/{token}")
    public Boolean authenticate(@PathVariable(name = "token") String token) {
        return jwtService.getValidate(token);
    }


    @PostMapping("/login")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            if (authentication.isAuthenticated()) {
                return tokenService.saveToken(authRequest.getUsername());
            } else {
                throw new BadCredentialsException("invalid user request!");
            }
    }

    @PutMapping("/logout")
    public String tokenLogout(@RequestParam(name = "token") String token) {
       return tokenService.logout(token);
    }




}
