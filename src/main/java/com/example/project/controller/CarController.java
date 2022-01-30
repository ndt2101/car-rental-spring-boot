package com.example.project.controller;

import com.example.project.services.IImageService;
import com.example.project.validator.payload.DeletedImagesDTO;
import com.example.project.validator.payload.InputCarDTO;
import com.example.project.validator.response.OutputCarDTO;
import com.example.project.services.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    IImageService imageService;

    @PostMapping(value = "/users/{userId}/cars", produces = MediaType.APPLICATION_JSON_VALUE)
    public OutputCarDTO creatNewCar(@PathVariable(name = "userId") Long userId,
                                    @ModelAttribute InputCarDTO inputCarDTO,
                                    @ModelAttribute DeletedImagesDTO deletedImages) {
        return carService.save(inputCarDTO);
    }

    @PutMapping(value = "/users/{userId}/cars", produces = MediaType.APPLICATION_JSON_VALUE)
    public OutputCarDTO updateCar(@PathVariable(name = "userId") Long userId,
                                    @ModelAttribute InputCarDTO inputCarDTO,
                                    @ModelAttribute DeletedImagesDTO deletedImages) {
        if (deletedImages.getDeletedImages() != null){
            deletedImages.getDeletedImages().forEach(description -> {
                try {
                    imageService.delete(description);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        return carService.save(inputCarDTO);
    }
}

