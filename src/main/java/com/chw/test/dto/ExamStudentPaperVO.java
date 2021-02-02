package com.chw.test.dto;

import lombok.Data;

import java.util.List;

@Data
public class ExamStudentPaperVO {

    private String cardSourceUrl;

    private List<String> markingInfoJsons;
}
