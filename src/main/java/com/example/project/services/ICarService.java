package com.example.project.services;

import com.example.project.validator.payload.InputCarDTO;
import com.example.project.validator.response.OutputCarDTO;

/**
 * Create by Tuan
 * 00:46
 * 16 Jan 2022
 */
public interface ICarService {
    OutputCarDTO save(InputCarDTO inputCarDTO);
}
