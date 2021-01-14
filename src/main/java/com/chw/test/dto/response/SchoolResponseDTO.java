package com.chw.test.dto.response;

import com.chw.test.dto.SchoolVo;
import lombok.Data;

@Data
public class SchoolResponseDTO {

    private Integer code;
    private String msg;
    private SchoolVo data;
}
