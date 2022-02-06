package com.example.project.controller;

import com.example.project.services.IImageService;
import com.example.project.services.impl.CarService;
import com.example.project.validator.payload.DeletedImagesDTO;
import com.example.project.validator.payload.InputCarDTO;
import com.example.project.validator.response.ApiResponse;
import com.example.project.validator.response.OutputCar;
import com.example.project.validator.response.OutputCarDTO;
import com.example.project.services.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping(value = "/cars")
    public OutputCar getCars(@RequestParam Map<String, String> params) {
        OutputCar result = new OutputCar();
        if (!params.containsKey("carId")) {
            result.setPage(Integer.valueOf(params.get("page")));
            Pageable pageable = PageRequest.of(Integer.parseInt(params.get("page")) - 1, Integer.parseInt(params.get("limit")), Sort.by("updatedAt").descending());
            result.setOutputCarDTOs(carService.getCars(params, pageable));
            result.setTotalPage((int) Math.ceil((double) (CarService.totalItem) / Integer.parseInt(params.get("limit"))));
        } else {
            result.setPage(1);
            result.setTotalPage(1);
            result.setOutputCarDTOs(carService.getCars(Long.valueOf(params.get("carId"))));
        }
        return result;
    }

    @DeleteMapping(value = "/cars")
    public ApiResponse deleteCar(@ModelAttribute(name = "carId") Long carId) {
        return carService.delete(carId);
    }
}

