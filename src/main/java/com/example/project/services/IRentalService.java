package com.example.project.services;

import com.example.project.entity.RentalEntity;
import com.example.project.validator.payload.RentalDTO;

public interface IRentalService {
    RentalEntity bookCar(RentalDTO inputRentalDTO);
}
