package com.example.project.validator.payload;

import com.example.project.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Null;
import java.util.List;

/**
 * Create by Tuan
 * 23:08
 * 15 Jan 2022
 */
@Getter
@Setter
public class InputCarDTO extends BaseDTO {

    private String name;

    private Long ownerId;

    private Long customerId;

    private String brand;

    private String type;

    private String plateNumber;

    private String description;

    @Null
    private List<MultipartFile> images;
}
