package com.example.project.repository;

import com.example.project.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Create by Tuan
 * 00:23
 * 16 Jan 2022
 */
public interface CarRepository extends JpaRepository<CarEntity, Long> {

}
