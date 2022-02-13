package com.example.project.services.impl;

import com.example.project.entity.CarEntity;
import com.example.project.entity.RentalEntity;
import com.example.project.entity.UserEntity;
import com.example.project.repository.CarRepository;
import com.example.project.repository.RentalRepository;
import com.example.project.repository.UserRepository;
import com.example.project.services.IRentalService;
import com.example.project.validator.payload.RentalDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentalService implements IRentalService {
    @Autowired
    CarRepository carRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RentalRepository rentalRepository;


    @Autowired
    ModelMapper mapper;

    @Override
    public RentalEntity bookCar(RentalDTO inputRentalDTO) {
        RentalEntity rentalEntity = mapper.map(inputRentalDTO, RentalEntity.class);

        UserEntity owner = userRepository.findById(inputRentalDTO.getOwnerId()).get();
        rentalEntity.setOwner(owner);

        CarEntity car = carRepository.findById(inputRentalDTO.getCarId()).get();
        rentalEntity.setCar(car);

        rentalRepository.save(rentalEntity);
        return rentalEntity;
    }
}
