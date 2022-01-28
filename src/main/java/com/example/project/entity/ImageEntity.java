package com.example.project.entity;

import com.example.project.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Create by Tuan
 * 00:27
 * 16 Jan 2022
 */
@Getter
@Setter
@Entity
@Table(name = "images")
public class ImageEntity extends BaseEntity {

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private CarEntity carOfImage;

    @Column
    private String description;

}
