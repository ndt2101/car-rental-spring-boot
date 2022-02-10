package com.example.project.services;

import com.example.project.validator.payload.InputCarDTO;
import com.example.project.validator.response.ApiResponse;
import com.example.project.validator.response.OutputCarDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Map;

/**
 * Create by Tuan
 * 00:46
 * 16 Jan 2022
 */
public interface ICarService {
    OutputCarDTO save(InputCarDTO inputCarDTO);
    ApiResponse save(long carId);
    List<OutputCarDTO> getCars(@Nullable Map<String, String> params, Pageable pageable);
    ApiResponse delete(Long id);
    List<OutputCarDTO> getCars(Long carId);

}
