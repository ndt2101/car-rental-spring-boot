package com.example.project.validator.payload;

import com.example.project.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * Create by Tuan
 * 00:42
 * 16 Jan 2022
 */

@Getter
@Setter
public class InputImageDTO extends BaseDTO {

    private MultipartFile source;

//    private String description;
}
