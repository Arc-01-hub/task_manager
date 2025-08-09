package com.example.task_manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.task_manager.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    public UserRepository userRepository;

}
