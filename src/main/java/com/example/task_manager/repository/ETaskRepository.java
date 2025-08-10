package com.example.task_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.task_manager.model.ETask;

public interface ETaskRepository extends JpaRepository <ETask,Long> {
    
}
