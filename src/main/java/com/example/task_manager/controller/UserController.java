package com.example.task_manager.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.task_manager.model.User;
import com.example.task_manager.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    //GET one Usser
    @GetMapping("/{id}")
    public Optional<User> getOneUser(@PathVariable Long id){
        return userService.getOneUser(id);
    }
    //GET MY TASKS
    @GetMapping("/{id}")
    public Optional<User> getOneUser(@PathVariable Long id){
        return userService.getOneUser(id);
    }
}
