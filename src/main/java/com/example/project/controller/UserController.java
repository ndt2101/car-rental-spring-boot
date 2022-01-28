package com.example.project.controller;

import com.example.project.entity.UserEntity;
import com.example.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

//    TODO_Check role annotation
    @GetMapping("/all-users")
    public @ResponseBody Iterable <UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/{id}")
    public UserEntity getDetail(@PathVariable("id") long id) {
        return userRepository.findOneById(id);
    }
}
