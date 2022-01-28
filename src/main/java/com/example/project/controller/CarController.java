package com.example.project.controller;

import com.example.project.validator.payload.InputCarDTO;
import com.example.project.validator.response.OutputCarDTO;
import com.example.project.services.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Create by Tuan
 * 09:41
 * 16 Jan 2022
 */
@RestController
@RequestMapping("/api/auth")
public class CarController {

    @Autowired
    ICarService carService;
    @PostMapping("/users/{userId}/cars")
    public OutputCarDTO creatNewCar(@PathVariable(name = "userId") Long userId, @RequestBody InputCarDTO inputCarDTO) {
        return carService.save(inputCarDTO);
    }
}

