package com.example.project.controller;

import com.example.project.entity.UserEntity;
import com.example.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all-users")
    public @ResponseBody Iterable <UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(value = "/user/{id}")
    public UserEntity getDetail(@PathVariable("id") long id) {
        return userRepository.findOneById(id);
    }
}
