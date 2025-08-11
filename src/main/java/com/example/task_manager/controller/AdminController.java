package com.example.task_manager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.task_manager.dto.ETaskDto;
import com.example.task_manager.dto.UserDto;
import com.example.task_manager.model.ETask;
import com.example.task_manager.model.User;
import com.example.task_manager.service.AuthService;
import com.example.task_manager.service.ETaskService;
import com.example.task_manager.service.UserService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private ETaskService eTaskService;
    @Autowired
    private AuthService authService;

    // GET MY acc info
    @GetMapping("/me")
    public UserDto getMyInfo(){
        return userService.getOneUser(authService.getCurrentUserId());
    }
    // GET MY TASKS
    @GetMapping("/mytasks")
    public List<ETaskDto> getMyTasks(){
        return eTaskService.getAllUserTasks(authService.getCurrentUserId());
    }
    @PostMapping("/mytasks/add")
    public void createTask(@RequestBody ETask request){
        eTaskService.createTask(request,authService.getCurrentUserId());
    }
    @DeleteMapping("/mytasks/delete")
    public void createTask(){
        eTaskService.deleteTask(authService.getCurrentUserId());
    }
    // GET ALL Users
    @GetMapping("/users")
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }
    // // GET ALL TASKS
    // @GetMapping("/tasks")
    // public List<ETask> getAllTasks(){
    //     return eTaskService.getAllTasks();
    // }
}
