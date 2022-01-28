package com.example.project.controller;

import com.example.project.validator.payload.InputCarDTO;
import com.example.project.validator.response.OutputCarDTO;
import com.example.project.services.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    @PostMapping(value = "/users/{userId}/cars", produces = MediaType.APPLICATION_JSON_VALUE)
    public OutputCarDTO creatNewCar(@PathVariable(name = "userId") Long userId, @ModelAttribute InputCarDTO inputCarDTO) {
        return carService.save(inputCarDTO);
    }
}

