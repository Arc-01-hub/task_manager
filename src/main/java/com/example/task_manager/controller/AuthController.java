package com.example.task_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.task_manager.model.User;
import com.example.task_manager.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    public AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody User request){
        return authService.register(request);
    }
    @PostMapping("/login")
    public String login(@RequestBody User request){
        return authService.login(request);
    }
}
