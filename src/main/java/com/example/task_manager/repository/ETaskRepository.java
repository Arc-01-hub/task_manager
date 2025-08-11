package com.example.task_manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.task_manager.model.ETask;

public interface ETaskRepository extends JpaRepository <ETask,Long> {
    List<ETask> findByUserId(Long userId);
}
