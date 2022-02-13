package com.example.project.entity;

import com.example.project.base.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "rental")
public class RentalEntity extends BaseEntity {

    @Column
    private long bookingTypeId;

    @Column
    private String bookingTypeName;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "car_id")
    private CarEntity car;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "owner_id")
    private UserEntity owner;

    @Column
    private String startDate;

    @Column
    private String endDate;

    @Column
    private long fee;

    @Column
    private String address;

    public RentalEntity() {
    }

    public RentalEntity(long bookingTypeId, String bookingTypeName, CarEntity car, UserEntity owner, String startDate, String endDate, long fee, String address) {
        this.bookingTypeId = bookingTypeId;
        this.bookingTypeName = bookingTypeName;
        this.car = car;
        this.owner = owner;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fee = fee;
        this.address = address;
    }

    public long getBookingTypeId() {
        return bookingTypeId;
    }

    public void setBookingTypeId(long bookingTypeId) {
        this.bookingTypeId = bookingTypeId;
    }

    public String getBookingTypeName() {
        return bookingTypeName;
    }

    public void setBookingTypeName(String bookingTypeName) {
        this.bookingTypeName = bookingTypeName;
    }

    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public long getFee() {
        return fee;
    }

    public void setFee(long fee) {
        this.fee = fee;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
