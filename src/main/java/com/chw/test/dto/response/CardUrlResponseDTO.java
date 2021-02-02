package com.chw.test.dto.response;

import com.chw.test.dto.ExamStudentPaperVO;
import lombok.Data;

@Data
public class CardUrlResponseDTO {

    private Integer code;
    private String msg;
    private ExamStudentPaperVO data;
}
