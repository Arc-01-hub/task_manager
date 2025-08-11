package com.example.task_manager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/api/user")
public class UserController {
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
        return eTaskService.getAllTasks();
    }
    @PostMapping("/mytasks/add")
    public void createTask(@RequestBody ETask request){
        eTaskService.createTask(request,authService.getCurrentUserId());
    }
    @DeleteMapping("/mytasks/delete/{id}")
    public void createTask(@PathVariable Long id){
        eTaskService.deleteTask(id);
    }
}
