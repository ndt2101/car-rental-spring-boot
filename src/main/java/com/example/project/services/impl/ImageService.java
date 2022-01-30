package com.example.project.services.impl;

import com.example.project.repository.ImageRepository;
import com.example.project.services.IImageService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Transactional
public class ImageService implements IImageService {

    @Autowired
    ImageRepository imageRepository;

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public void delete(String description) {
        imageRepository.deleteImageEntitiesByDescription(description);
        Path temp;
        try {
            temp = Paths.get(description);
            Files.delete(temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
