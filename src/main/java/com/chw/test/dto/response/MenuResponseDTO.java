package com.chw.test.dto.response;

import com.chw.test.dto.MenuDTO;
import lombok.Data;

import java.util.List;

@Data
public class MenuResponseDTO {

    private Integer code;
    private String msg;
    private List<MenuDTO> data;
}
