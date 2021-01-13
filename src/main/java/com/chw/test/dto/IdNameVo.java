package com.chw.test.dto;

import lombok.Data;

@Data
public class IdNameVo {

    private Integer id;

    private String name;

    public IdNameVo() {
    }

    public IdNameVo(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
