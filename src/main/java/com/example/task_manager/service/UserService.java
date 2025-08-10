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

    
    //Get all users
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    //Get one user
    public Optional <User> getOneUser(Long id){
        return userRepository.findById(id);
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
