package com.example.project.services.impl;

import com.example.project.entity.ImageEntity;
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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

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

    // delete directory via carId
    @Override
    public void delete(List<ImageEntity> imageEntities, Path dirPath) throws IOException {
        imageEntities.forEach(imageEntity -> {
            imageRepository.deleteImageEntitiesByDescription(imageEntity.getDescription());
        });
        try {
            try (Stream<Path> walk = Files.walk(dirPath)) {
                walk
                        .sorted(Comparator.reverseOrder())
                        .forEach(ImageService::deleteDirectoryJava8Extract);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteDirectoryJava8Extract(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            System.err.printf("Unable to delete this path : %s%n%s", path, e);
        }
    }
}
