package com.example.project.services.impl;

import com.example.project.convertor.CarConvertor;
import com.example.project.validator.payload.InputCarDTO;
import com.example.project.validator.payload.InputImageDTO;
import com.example.project.validator.response.OutputCarDTO;
import com.example.project.entity.CarEntity;
import com.example.project.entity.ImageEntity;
import com.example.project.entity.UserEntity;
import com.example.project.repository.CarRepository;
import com.example.project.repository.ImageRepository;
import com.example.project.repository.UserRepository;
import com.example.project.services.ICarService;
import com.example.project.utils.FileUploadUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Create by Tuan
 * 00:46
 * 16 Jan 2022
 */

@Service
public class CarService implements ICarService {

    @Autowired
    ModelMapper mapper;

    @Autowired
    CarRepository carRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    CarConvertor carConvertor;

    @Autowired
    ServletContext application;

    @Override
    public OutputCarDTO save(InputCarDTO inputCarDTO) {
        List<String> outputImageList = new  ArrayList<>();
        CarEntity carEntity = mapper.map(inputCarDTO, CarEntity.class);
        UserEntity owner = userRepository.getById(inputCarDTO.getOwnerId());
        carEntity.setOwner(owner);
        if (inputCarDTO.getCustomerId() != null) {
            UserEntity customer = userRepository.getById(inputCarDTO.getCustomerId());
            carEntity.setCustomer(customer);
        }
        List<InputImageDTO> imageList = inputCarDTO.getImages();
        if (imageList != null){
            List<ImageEntity> imageEntityList = filesHandle(imageList, carEntity);
            carEntity.setImages(imageEntityList);
            imageEntityList.forEach(imageEntity -> {
                outputImageList.add(imageEntity.getName());
            });
        }
        carEntity = carRepository.save(carEntity);
        return carConvertor.toCarDTO(carEntity); // convert image -> list image -> cho vao output car -> tra ve
    }

    private List<ImageEntity> filesHandle(List<InputImageDTO> imageList, CarEntity carEntity) {

        List<ImageEntity> imageEntities = new ArrayList<>();
        imageList.forEach(inputImageDTO -> {
            String imageName = StringUtils.cleanPath(Objects.requireNonNull(inputImageDTO.getSource().getOriginalFilename()));
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setName(imageName);
            imageEntity.setCarOfImage(carEntity); //
            imageEntity.setDescription(inputImageDTO.getDescription());
            imageEntity = imageRepository.save(imageEntity);
            imageEntities.add(imageEntity);

            String uploadDir = application.getRealPath("/") + "car-photos/" + carEntity.getId();
            try {
                FileUploadUtil.saveFile(uploadDir, imageName, inputImageDTO.getSource());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return imageEntities;
    }
}
