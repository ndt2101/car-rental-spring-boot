package com.example.project.repository;

import com.example.project.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findOneById(Long id);

    Optional<UserEntity> findByUsername(String username);

    Boolean existsByUsername(String username);

    List<UserEntity> findByIdIn(List<Long> userIds);
}
