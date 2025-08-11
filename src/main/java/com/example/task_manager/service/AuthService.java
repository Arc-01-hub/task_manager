package com.example.task_manager.service;

import java.util.HashSet;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.task_manager.enums.Role;
import com.example.task_manager.model.User;
import com.example.task_manager.repository.UserRepository;

@Service
public class AuthService  {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //REGISTER
    public String register(@RequestBody User request){
        if(userRepository.findByUserName(request.getUserName()).isPresent()){
            return "User is ulready in!";
        }
        User  newUser = new User();
        newUser.setUserName(request.getUserName());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setAge(request.getAge());
        
        Set<Role> roles = new HashSet<>();
        if("anas".equals(request.getUserName())){
            roles.add(Role.ADMIN);
            roles.add(Role.USER);        
        }
        else{
            roles.add(Role.USER);
        }
        newUser.setRoles(roles);
        userRepository.save(newUser);
        return "User created succesfully";
    }
    //LOGIN
    public String login(@RequestBody User request){
        try{
            Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserName(),request.getPassword()));

                return "Login Succesfully";
        }catch(Exception e){    
            return "Somthing Wrong";
        }
        
    }
    // GET CURRENT USER 
    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUserName(username)
            .orElseThrow(() -> new RuntimeException("User not found"));
        return user;
    }
    // GET CURRENT USER ID
    public Long getCurrentUserId() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUserName(username)
            .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getId();
    }

}

