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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.nio.file.Paths;
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

    @Override
    public OutputCarDTO save(InputCarDTO inputCarDTO) {
        CarEntity carEntity;
        List<String> outputImageList = new  ArrayList<>();
        if(inputCarDTO.getId() != null) {
            carEntity = carRepository.getById(inputCarDTO.getId());
            carEntity = carConvertor.toCarEntity(inputCarDTO, carEntity);
        } else {
            carEntity = mapper.map(inputCarDTO, CarEntity.class);
        }
        UserEntity owner = userRepository.getById(inputCarDTO.getOwnerId());
        carEntity.setOwner(owner);
        if (inputCarDTO.getCustomerId() != null) {
            UserEntity customer = userRepository.getById(inputCarDTO.getCustomerId());
            carEntity.setCustomer(customer);
        }
        List<MultipartFile> imageList = inputCarDTO.getImages();
        if (imageList != null){
            List<ImageEntity> imageEntityList = filesHandle(imageList, carEntity);
            carEntity.setImages(imageEntityList);
            imageEntityList.forEach(imageEntity -> {
                outputImageList.add(Paths.get(System.getProperty("user.dir") + "/car-photos/" + imageEntity.getCarOfImage().getId() + "/" + imageEntity.getName()).toString());
            });
        }
        carEntity = carRepository.save(carEntity);

        OutputCarDTO outputCarDTO = carConvertor.toCarDTO(carEntity);
        outputCarDTO.setImages(outputImageList);
        return outputCarDTO;
    }

    private List<ImageEntity> filesHandle(List<MultipartFile> imageList, CarEntity carEntity) {

        List<ImageEntity> imageEntities = new ArrayList<>();
        imageList.forEach(inputImageDTO -> {
            String imageName = inputImageDTO.getOriginalFilename();
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setName(imageName);
            imageEntity.setCarOfImage(carEntity);
            try {
                imageEntity = imageRepository.save(imageEntity);
            } catch (Exception e) {

            }
            imageEntities.add(imageEntity);

            String uploadDir = "car-photos/" + carEntity.getId();
            try {
                String path = FileUploadUtil.saveFile(uploadDir, imageName, inputImageDTO);
                imageEntity.setDescription(System.getProperty("user.dir") + path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return imageEntities;
    }
}
