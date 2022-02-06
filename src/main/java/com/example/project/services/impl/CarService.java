package com.example.project.services.impl;

import com.example.project.convertor.CarConvertor;
import com.example.project.validator.payload.InputCarDTO;
import com.example.project.validator.payload.InputImageDTO;
import com.example.project.validator.response.ApiResponse;
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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

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
    ImageService imageService;

    public static long totalItem = 0;

    @Override
    public OutputCarDTO save(InputCarDTO inputCarDTO) {
        CarEntity carEntity;
        List<String> outputImageList = new ArrayList<>();
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
        }
        carEntity = carRepository.save(carEntity);

        List<ImageEntity> imageEntities = imageRepository.findAllByCarOfImage(carEntity);
        imageEntities.forEach(imageEntity -> {
            outputImageList.add(imageEntity.getDescription());
        });
        OutputCarDTO outputCarDTO = carConvertor.toCarDTO(carEntity);
        outputCarDTO.setImages(outputImageList);
        return outputCarDTO;
    }

    @Override
    public ApiResponse delete(Long id) {
        ApiResponse response = new ApiResponse(true, "success");
        try {
            CarEntity carEntity = carRepository.getById(id);
            List<ImageEntity> imageEntities = imageRepository.findAllByCarOfImage(carEntity);
            try {
                imageService.delete(imageEntities, Paths.get(System.getProperty("user.dir") + "/car-photos/" + id));
            } catch (Exception e) {
                response.setMessage(e.getMessage());
                response.setSuccess(false);
                return response;
            } finally {
                imageEntities = null;
            }
            carRepository.deleteById(id);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setSuccess(false);
        }
        return response;
    }

    // get by id
    @Override
    public List<OutputCarDTO> getCars(Long carId) {
        CarEntity carEntity = carRepository.getById(carId);
        OutputCarDTO result = carConvertor.toCarDTO(carEntity);
        List<String> outputImageList = new ArrayList<>();
        List<ImageEntity> imageEntities = imageRepository.findAllByCarOfImage(carEntity);
        imageEntities.forEach(imageEntity -> {
            outputImageList.add(imageEntity.getDescription());
        });
        result.setImages(outputImageList);
        List<OutputCarDTO> resultList = new ArrayList<>();
        resultList.add(result);
        return resultList;
    }

    @Override
    public List<OutputCarDTO> getCars(Map<String, String> params, Pageable pageable) {
        List<CarEntity> carEntities = null;
        List<OutputCarDTO> outputCarDTOs = new ArrayList<>();
        if (params.size() == 2) {
             carEntities = carRepository.findAll(pageable).getContent();
             totalItem = carRepository.count();
        } else {
            if (params.size() > 3) { //tim kiem theo car cua user
                UserEntity owner = userRepository.getById(Long.parseLong(params.get("ownerId")));
                if (params.keySet().toArray()[1].equals("type")) {
                    carEntities = carRepository.getCarEntitiesByOwnerAndType(owner, params.get("type"), pageable);
                    totalItem = carRepository.countCarEntitiesByOwnerAndType(owner, params.get("type"));
                } else if (params.keySet().toArray()[1].equals("brand")) {
                    carEntities = carRepository.getCarEntitiesByOwnerAndBrand(owner, params.get("brand"), pageable);
                    totalItem = carRepository.countCarEntitiesByOwnerAndBrand(owner, params.get("brand"));
                } else {
                    carEntities = carRepository.getCarEntitiesByOwnerAndName(owner, params.get("name"), pageable);
                    totalItem = carRepository.countCarEntitiesByOwnerAndName(owner, params.get("name"));
                }
            } else if (params.size() == 3) { // tim kiem theo tat ca
                if (params.containsKey("name")) {
                    carEntities = carRepository.getCarEntitiesByName(params.get("name"), pageable);
                    totalItem = carRepository.countCarEntitiesByName(params.get("name"));
                } else if (params.containsKey("brand")) {
                    carEntities = carRepository.getCarEntitiesByBrand(params.get("brand"), pageable);
                    totalItem = carRepository.countCarEntitiesByBrand(params.get("brand"));
                } else if (params.containsKey("type")) {
                    carEntities = carRepository.getCarEntitiesByType(params.get("type"), pageable);
                    totalItem = carRepository.countCarEntitiesByType(params.get("type"));
                } else {
                    UserEntity owner = userRepository.getById(Long.parseLong(params.get("ownerId")));
                    carEntities = carRepository.getCarEntitiesByOwner(owner, pageable);
                    totalItem = carRepository.countCarEntitiesByOwner(owner);
                }
            }
        }
        if (carEntities != null) {
            carEntities.forEach(carEntity -> {
                List<String> outputImageList = new ArrayList<>();
                List<ImageEntity> imageEntities = imageRepository.findAllByCarOfImage(carEntity);
                imageEntities.forEach(imageEntity -> {
                    outputImageList.add(imageEntity.getDescription());
                });
                OutputCarDTO outputCarDTO = carConvertor.toCarDTOOnSearching(carEntity);
                if (outputImageList.size() != 0){
                    outputCarDTO.setImages(Collections.singletonList(outputImageList.get(0))); // tra ve anh dau tien de hien thi
                }
                outputCarDTOs.add(outputCarDTO);
            });
        }
        return outputCarDTOs;
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
