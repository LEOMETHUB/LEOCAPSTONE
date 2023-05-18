package org.authentication.service;

import lombok.RequiredArgsConstructor;
import org.authentication.entity.User;
import org.authentication.pojo.UserPojo;
import org.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;


    private final PasswordEncoder passwordEncoder;


    public Optional<User> findUser(String username){
        return userRepository.findByUsername(username);
    }
    public String addUser(UserPojo user) {
        Optional<User> username= userRepository.findByUsername(user.getUsername());
        if(username.isPresent()){
            return "Username is already existing";
        }
         else {
            userRepository.save(new User(user.getFirstName(),
                    user.getLastName(),
                    user.getUsername(),
                    passwordEncoder.encode(user.getPassword()),
                    user.getRole().toUpperCase()));
            return "Add User Successful.";
        }
    }



}
