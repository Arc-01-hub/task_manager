package com.example.task_manager.dto;

import java.util.Set;

import com.example.task_manager.enums.Role;

import lombok.Data;

@Data
public class UserDto {
    private String userName;
    private int age;
    private Set<Role> roles;
}
