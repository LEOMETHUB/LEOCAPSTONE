package org.authentication.service;


import org.authentication.entity.Token;
import org.authentication.entity.User;
import org.authentication.repository.TokenRepository;
import org.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {


    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;


    public String saveToken(String username){
        try {
            tokenRepository.logoutToken(username);
            String token = jwtService.generateToken(username);
            User user = userRepository.findByUsername(username).orElseThrow(() -> new BadCredentialsException("User not found."));
            tokenRepository.save(new Token(token,
                    user.getRole(),
                    false,
                    false,
                    username,
                    new Date()));
            return "Successfully Login \n Token:    " + token ;
        }catch (Exception e){
            return e.getMessage();
        }
    }
    public String logout(String token){
        try {
            Token tokenTemp = tokenRepository.getTokenDetails(token);
            tokenRepository.save(new Token(tokenTemp.getTokenID(),
                    token,
                    tokenTemp.getRole(),
                    true,
                    true,
                    tokenTemp.getUsername(),
                    new Date()));
            return "Successfully Logout";
        }catch (Exception e){
            return e.getMessage();
        }
    }


    public String getToken(){
            return tokenRepository.getToken().orElse(null);
    }

}
