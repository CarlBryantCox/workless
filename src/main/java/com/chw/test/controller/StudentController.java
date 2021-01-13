package com.chw.test.controller;


import com.chw.test.config.ApiResponse;
import com.chw.test.dto.SchoolVo;
import com.chw.test.service.StudentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ChenWei
 * @since 2021-01-12
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    @GetMapping("/getToken")
    public ApiResponse getToken(){
        return new ApiResponse(studentService.getToken());
    }

    @PostMapping("/insertSchool")
    public ApiResponse insertSchool(@RequestBody SchoolVo schoolVo){
        return new ApiResponse(studentService.insertSchool(schoolVo,studentService.getToken()));
    }

    @GetMapping("/getBaseMenuList")
    public ApiResponse getBaseMenuList(){
        return new ApiResponse(studentService.getBaseMenuList(studentService.getToken()));
    }




}
