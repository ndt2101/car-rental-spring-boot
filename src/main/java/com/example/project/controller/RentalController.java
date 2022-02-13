package com.example.project.controller;

import com.example.project.entity.RentalEntity;
import com.example.project.services.IRentalService;
import com.example.project.validator.payload.RentalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class RentalController {
    @Autowired
    private IRentalService rentalService;

    @PostMapping(value = "/booking")
    public RentalEntity bookCar(@RequestBody RentalDTO inputRentalDTO) {
        return rentalService.bookCar(inputRentalDTO);
    }
}
