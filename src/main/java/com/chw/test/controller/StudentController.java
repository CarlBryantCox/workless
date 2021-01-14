package com.chw.test.controller;


import com.chw.test.config.ApiResponse;
import com.chw.test.dto.IdNameVo;
import com.chw.test.dto.SchoolImportDTO;
import com.chw.test.dto.SchoolMenuVO;
import com.chw.test.dto.SchoolVo;
import com.chw.test.enums.SchoolPhaseEnum;
import com.chw.test.service.StudentService;
import com.chw.test.utils.ExcelUtilsChw;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/getUserByPhone")
    public ApiResponse getUserByPhone(@RequestParam("phone") String phone){
        return new ApiResponse(studentService.getUserByPhone(phone,studentService.getToken()));
    }

    @GetMapping("/getSchoolById")
    public ApiResponse getSchoolById(@RequestParam("schoolId") Integer schoolId){
        return new ApiResponse(studentService.getSchool(schoolId,studentService.getToken()));
    }

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

    @PostMapping("/imports")
    public void imports(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws Exception{
        String[] colNames= {"系统创建名称","省份","城市","乡镇","联系人","手机号","需开通学段","备注"};
        String[] properties = {"schoolName","provinceName","cityName","districtName","schoolManagerName","schoolManagerPhone","schoolPhaseName","remark"};
        Sheet firstSheet = ExcelUtilsChw.getFirstSheet(file);
        String token = studentService.getToken();
        List<SchoolMenuVO> baseMenuList = studentService.getBaseMenuList(token);
        List<SchoolImportDTO> transfer = ExcelUtilsChw.transfer(firstSheet, colNames, properties, 0, SchoolImportDTO.class);
        for (SchoolImportDTO schoolImportDTO : transfer) {
            SchoolVo schoolVo = new SchoolVo();
            schoolVo.setDistrictId("411081000000");
            schoolVo.setSchoolName(schoolImportDTO.getSchoolName());
            schoolVo.setSchoolManagerPhone(schoolImportDTO.getSchoolManagerPhone());
            schoolVo.setSchoolManagerName(schoolImportDTO.getSchoolManagerName());
            schoolVo.setSchoolManagerWorkNumber("235235");
            schoolVo.setSchoolManagerLockedFlag(1);
            schoolVo.setUseType(1);
            schoolVo.setRemark(schoolImportDTO.getRemark());
            schoolVo.setAddressDetail(schoolImportDTO.getProvinceName()+schoolImportDTO.getCityName()+schoolImportDTO.getSchoolName());
            List<IdNameVo> phaseList = new ArrayList<>();
            String[] split = schoolImportDTO.getSchoolPhaseName().split(",");
            for (String s : split) {
                phaseList.add(new IdNameVo(SchoolPhaseEnum.getIdByName(s),s));
            }
            schoolVo.setSchoolPhaseList(phaseList);
            schoolVo.setSchoolMenuList(baseMenuList);
            schoolImportDTO.setReason(studentService.insertSchool(schoolVo,token));
        }
        String[] colNamesOut= {"系统创建名称","省份","城市","乡镇","联系人","手机号","需开通学段","备注","失败原因"};
        String[] propertiesOut = {"schoolName","provinceName","cityName","districtName","schoolManagerName","schoolManagerPhone","schoolPhaseName","remark","reason"};
        ExcelUtilsChw.export(response,"result",transfer,SchoolImportDTO.class,propertiesOut,colNamesOut);
    }




}
