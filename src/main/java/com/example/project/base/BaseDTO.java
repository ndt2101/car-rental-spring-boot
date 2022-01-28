package com.example.project.base;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Create by Tuan
 * 23:08
 * 15 Jan 2022
 */

@Getter
@Setter
public class BaseDTO {

    private Long id;

    private String createdBy;

    private Date createdAt;

    private String updatedBy;

    private Date updatedAt;
}
