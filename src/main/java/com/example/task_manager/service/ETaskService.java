package com.example.task_manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.task_manager.model.ETask;
import com.example.task_manager.repository.ETaskRepository;

@Service
public class ETaskService {
    @Autowired
    public ETaskRepository etaskRepository;
    @Autowired
    public UserService userService;

    //Get all tasks
    public List<ETask> getAllTasks(){
        return etaskRepository.findAll();
    }
    //Get one task
    public Optional<ETask> getOneTask(@PathVariable Long id){
        return etaskRepository.findById(id);
    }
    //create task
    public void createTask(ETask task,Long id){
        task.setUser(userService.getOneUser(id).get());
        etaskRepository.save(task);
    }
    //delete task
    public void deleteTask(@PathVariable Long id){
        etaskRepository.deleteById(id);
    }
}
