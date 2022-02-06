package com.example.project.convertor;

import com.example.project.repository.CarRepository;
import com.example.project.repository.UserRepository;
import com.example.project.validator.payload.InputCarDTO;
import com.example.project.validator.response.OutputCarDTO;
import com.example.project.entity.CarEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Create by Tuan
 * 10:22
 * 16 Jan 2022
 */
@Component
public class CarConvertor {
    public OutputCarDTO toCarDTO(CarEntity carEntity) {
        OutputCarDTO outputCarDTO = new OutputCarDTO();
        outputCarDTO.setId(carEntity.getId());
        outputCarDTO.setBrand(carEntity.getBrand());
        if (carEntity.getCustomer() != null){
            outputCarDTO.setCustomerId(carEntity.getCustomer().getId());
        }
        outputCarDTO.setDescription(carEntity.getDescription());
        outputCarDTO.setName(carEntity.getName());
        outputCarDTO.setOwnerId(carEntity.getOwner().getId());
        outputCarDTO.setPlateNumber(carEntity.getPlateNumber());
        outputCarDTO.setType(carEntity.getType());
        outputCarDTO.setCreatedAt(carEntity.getCreatedAt());
        outputCarDTO.setUpdatedBy(carEntity.getUpdatedBy());
        outputCarDTO.setUpdatedAt(carEntity.getUpdatedAt());
        outputCarDTO.setCreatedBy(carEntity.getCreatedBy());
        return outputCarDTO;
    }

    public OutputCarDTO toCarDTOOnSearching(CarEntity carEntity) {
        OutputCarDTO outputCarDTO = new OutputCarDTO();
        outputCarDTO.setId(carEntity.getId());
        outputCarDTO.setBrand(carEntity.getBrand());
        if (carEntity.getCustomer() != null){
            outputCarDTO.setCustomerId(carEntity.getCustomer().getId());
        }
        outputCarDTO.setName(carEntity.getName());
        return outputCarDTO;
    }

    /**
     * cho truong hop update
     */
    public CarEntity toCarEntity(InputCarDTO carDTO, CarEntity carEntity) {
        carEntity = new CarEntity();
        if (carDTO.getId() != null) {
            carEntity.setId(carDTO.getId());
        }
        carEntity.setType(carDTO.getType());
        carEntity.setPlateNumber(carDTO.getPlateNumber());
        carEntity.setBrand(carDTO.getBrand());
        carEntity.setName(carDTO.getName());
        carEntity.setDescription(carDTO.getDescription());
        return carEntity;
    }
}
