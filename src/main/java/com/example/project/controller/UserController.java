package com.example.project.controller;

import com.example.project.entity.RoleEntity;
import com.example.project.entity.UserEntity;
import com.example.project.exception.AppException;
import com.example.project.repository.RoleRepository;
import com.example.project.repository.UserRepository;
import com.example.project.validator.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

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

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{userid}/update-role")
    public ResponseEntity<?> updateUserRole(@PathVariable("userid") long userid, @RequestBody List<Long> roleIds) {
        UserEntity user = userRepository.findOneById(userid);
        List<RoleEntity> roles = new ArrayList<>();
        roleIds.stream().forEach(roleId -> {
            RoleEntity role = roleRepository.findById(roleId)
                    .orElseThrow(() -> new AppException("Role Id is invalid."));
            roles.add(role);
        });

        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new ApiResponse(true, "Update user role successfully"));
    }
}
