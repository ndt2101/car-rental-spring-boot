package com.example.project.validator.response;

import java.util.List;

public class OutputCar {
    private Integer page;
    private Integer totalPage;
    private List<OutputCarDTO> outputCarDTOs;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<OutputCarDTO> getOutputCarDTOs() {
        return outputCarDTOs;
    }

    public void setOutputCarDTOs(List<OutputCarDTO> outputCarDTOs) {
        this.outputCarDTOs = outputCarDTOs;
    }
}
