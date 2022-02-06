package com.example.project.repository;

import com.example.project.entity.CarEntity;
import com.example.project.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Create by Tuan
 * 00:23
 * 16 Jan 2022
 */
public interface CarRepository extends JpaRepository<CarEntity, Long> {
    List<CarEntity> getCarEntitiesByName(String name, Pageable pageable);
    List<CarEntity> getCarEntitiesByBrand(String brand, Pageable pageable);
    List<CarEntity> getCarEntitiesByOwner(UserEntity owner, Pageable pageable);
    List<CarEntity> getCarEntitiesByType(String type, Pageable pageable);
    List<CarEntity> getCarEntitiesByOwnerAndBrand(UserEntity owner, String brand, Pageable pageable);
    List<CarEntity> getCarEntitiesByOwnerAndName(UserEntity owner, String name, Pageable pageable);
    List<CarEntity> getCarEntitiesByOwnerAndType(UserEntity owner, String type, Pageable pageable);
    int countCarEntitiesByName(String name);
    int countCarEntitiesByBrand(String brand);
    int countCarEntitiesByType(String type);
    int countCarEntitiesByOwner(UserEntity owner);
    int countCarEntitiesByOwnerAndBrand(UserEntity owner, String brand);
    int countCarEntitiesByOwnerAndName(UserEntity owner, String name);
    int countCarEntitiesByOwnerAndType(UserEntity owner, String type);
}
