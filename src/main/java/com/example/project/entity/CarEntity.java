package com.example.project.entity;

import com.example.project.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

/**
 * Create by Tuan
 * 23:29
 * 15 Jan 2022
 */
@Getter
@Setter
@Entity
@Table(name = "cars")
public class CarEntity extends BaseEntity {

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserEntity owner;

    @ManyToOne
    @JoinColumn(name = "customer_Id")
    private UserEntity customer;

    @Column
    private String brand;

    @Column
    private String type;

    @Column
    private String plateNumber;

    @Column
    private String description;

    @Nullable
    @OneToMany(mappedBy = "carOfImage")
    private List<ImageEntity> images;
}
