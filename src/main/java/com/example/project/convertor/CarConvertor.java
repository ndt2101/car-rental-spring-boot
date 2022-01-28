package com.example.project.convertor;

import com.example.project.validator.response.OutputCarDTO;
import com.example.project.entity.CarEntity;
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
}
