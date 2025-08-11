package com.example.task_manager.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.task_manager.dto.UserDto;
import com.example.task_manager.model.ETask;
import com.example.task_manager.model.User;
import com.example.task_manager.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
    
    @Autowired
    public UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String userName) {
        User user = userRepository.findByUserName(userName)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        return new org.springframework.security.core.userdetails.User(
            user.getUserName(),
            user.getPassword(),
            user.getRoles().stream()
            .map(role->new SimpleGrantedAuthority("ROLE_"+role.name()))
            .collect(Collectors.toList())
            );
        }
        //CONVERT USER TO USERDTO
        public UserDto convertUserToDto(User user){
            UserDto userDto =new UserDto();
            userDto.setUserName(user.getUserName());
            userDto.setAge(user.getAge());
            userDto.setRoles(user.getRoles());
            return userDto;
        }

    //Get all users
    public List<UserDto> getAllUsers(){
        return userRepository.findAll().stream()
                                    .map(this::convertUserToDto).collect(Collectors.toList());
    }
    //Get one user
    public UserDto getOneUser(Long id){
        return convertUserToDto(userRepository.findById(id).get());
    }
    //Get one user by username
    public UserDto getOneUserByUserName(String userName){
        return convertUserToDto(userRepository.findByUserName(userName).get());
    }
    //create user
    public void createTask(User user){
        userRepository.save(user);
    }
    //delete user
    public void deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
    }

}
