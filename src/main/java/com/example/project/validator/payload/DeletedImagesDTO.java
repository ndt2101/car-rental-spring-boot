package com.example.project.validator.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DeletedImagesDTO {
    List<String> deletedImages;
}
