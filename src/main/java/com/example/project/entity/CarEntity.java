package com.example.project.entity;

import com.example.project.base.BaseEntity;
import org.springframework.lang.Nullable;
import javax.persistence.*;
import java.util.List;

/**
 * Create by Tuan
 * 23:29
 * 15 Jan 2022
 */
@Entity
@Table(name = "cars")
public class CarEntity extends BaseEntity {

    @Column
    private String name;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "owner_id")
    private UserEntity owner;

//    @ManyToOne(cascade = CascadeType.MERGE)
//    @JoinColumn(name = "customer_Id")
//    private UserEntity customer;

    @Column
    private String brand;

    @Column
    private String type;

    @Column
    private Long price;

    @Column
    private String plateNumber;

    @Column
    private String description;

    @Column(columnDefinition = "boolean default false")
    private boolean isApproved;

    @Column(columnDefinition = "boolean default true")
    private boolean available;


    @OneToMany(mappedBy = "carOfImage",
            cascade = CascadeType.MERGE)
    private List<ImageEntity> images;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

//    public UserEntity getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(UserEntity customer) {
//        this.customer = customer;
//    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Nullable
    public List<ImageEntity> getImages() {
        return images;
    }

    public void setImages(@Nullable List<ImageEntity> images) {
        this.images = images;
    }

    public boolean getApproved() {
        return isApproved;
    }

    public void setApprove(boolean approved) {
        isApproved = approved;
    }

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
