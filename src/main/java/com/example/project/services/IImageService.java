package com.example.project.services;

import com.example.project.entity.ImageEntity;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface IImageService {
    void delete(String description) throws IOException;
    void delete(List<ImageEntity> imageEntities, Path dirPath) throws IOException;
}
