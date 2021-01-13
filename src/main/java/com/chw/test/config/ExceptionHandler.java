package com.chw.test.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResponse exceptionHandler(Exception e){
        e.printStackTrace();
        return ApiResponse.getFailResponse(e.getMessage());
    }
}
