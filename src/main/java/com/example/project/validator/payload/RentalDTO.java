package com.example.project.validator.payload;

import com.example.project.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RentalDTO extends BaseDTO{
    @NotBlank
    private Long ownerId;

    @NotBlank
    private Long carId;

    @NotBlank
    private long bookingTypeId;

    @NotBlank
    private String bookingTypeName;

    @NotBlank
    private String startDate;

    @NotBlank
    private String endDate;

    @NotBlank
    private long fee;

    @NotBlank
    @Size(max = 256)
    private String address;

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }
}
