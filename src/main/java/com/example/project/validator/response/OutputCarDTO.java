package com.example.project.validator.response;

import com.example.project.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Create by Tuan
 * 11:45
 * 16 Jan 2022
 */

@Getter
@Setter
public class OutputCarDTO extends BaseDTO {

    private String name;

    private Long ownerId;

    private Long customerId;

    private String brand;

    private String type;

    private String plateNumber;

    private List<String> images;

    private String description;
}
