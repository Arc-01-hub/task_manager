package com.example.task_manager.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.task_manager.dto.ETaskDto;
import com.example.task_manager.dto.UserDto;
import com.example.task_manager.model.ETask;
import com.example.task_manager.model.User;
import com.example.task_manager.repository.ETaskRepository;

@Service
public class ETaskService {
    @Autowired
    public ETaskRepository etaskRepository;
    @Autowired
    public UserService userService;
    @Autowired
    public AuthService authService;

    //CONVERT Task TO TaskDTO
    public ETaskDto convertETaskToETaskDto(ETask eTask){
        ETaskDto eTaskDto =new ETaskDto();
        eTaskDto.setTitle(eTask.getTitle());
        eTaskDto.setBody(eTask.getBody());
        eTaskDto.setUserName(eTask.getUser().getUserName());
        return eTaskDto;
    }
    //Get all my tasks for /admin and /user
    public List<ETaskDto> getAllUserTasks(Long id){
        return etaskRepository.findByUserId(id).stream()
                                                .map(this::convertETaskToETaskDto).collect(Collectors.toList());
    }
    //Get all tasks for /admin
    public List<ETaskDto> getAllTasks(){
        return  etaskRepository.findAll().stream()
                                                .map(this::convertETaskToETaskDto).collect(Collectors.toList());
    }
    //Get one task
    public ETaskDto getOneTask(Long id){
        return convertETaskToETaskDto(etaskRepository.findById(id).get());
    }
    //create task
    public void createTask(ETask task,Long id){
        task.setUser(authService.getCurrentUser());
        etaskRepository.save(task);
    }
    //delete task
    public void deleteTask(Long id){
        etaskRepository.deleteById(id);
    }
}
