package com.chw.test.controller;


import com.chw.test.config.ApiResponse;
import com.chw.test.dto.StudentVO;
import com.chw.test.mapper.ExamStudentMapper;
import com.chw.test.service.ExamStudentService;
import com.chw.test.service.ExamSubjectStudentService;
import com.chw.test.service.StudentService;
import com.chw.test.utils.ZipUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

/**
 * <p>
 * 学科考生表 前端控制器
 * </p>
 *
 * @author ChenWei
 * @since 2021-02-02
 */
@Slf4j
@RestController
@RequestMapping("/exam/subject/student")
public class ExamSubjectStudentController {

    @Resource
    private StudentService studentService;

    @Resource
    private ExamStudentService examStudentService;

    @Resource
    private ExamSubjectStudentService examSubjectStudentService;

    @Resource
    private ExamStudentMapper examStudentMapper;

    @GetMapping("/test")
    public ApiResponse test(@RequestParam("examId") Long examId,
                            @RequestParam("subjectId") Integer subjectId,
                            @RequestParam("schoolId") Integer schoolId,
                            @RequestParam("path") String path){
        File file = new File("D:\\upload\\"+path);
        if(file.delete()){
            System.out.println(path+"删除了临时文件夹");
        }
        if(file.mkdir()){
            System.out.println(path+"创建了临时文件夹");
        }
        String token = studentService.getToken();
//        QueryWrapper<ExamSubjectStudent> subjectStudentQueryWrapper = new QueryWrapper<>();
//        subjectStudentQueryWrapper.eq("exam_id",examId);
//        subjectStudentQueryWrapper.eq("subject_id",subjectId);
//        List<ExamSubjectStudent> subjectStudentList = examSubjectStudentService.list(subjectStudentQueryWrapper);
        List<StudentVO> subjectStudentList = examStudentMapper.getStudentList(examId, subjectId, schoolId);
        System.out.println(path+"学生数量--"+subjectStudentList.size());
        int count = 0;
        for (StudentVO studentVO : subjectStudentList) {
            if(downloadCardImg(studentVO.getRecordId(), token,studentVO.getStudentName(),path)){
                count++;
            }
        }
        System.out.println(path+"下载数量--"+count);
        return new ApiResponse();
    }

    private boolean downloadCardImg(Long recordId,String token,String studentName,String path){
        List<String> studentCardUrlList = examStudentService.getStudentCardUrlList(recordId, token);
        if(studentCardUrlList.isEmpty()){
            log.info("recordId={},studentName={}--获取答题卡信息失败",recordId,studentName);
            return false;
        }
        int i = 1;
        for (String s : studentCardUrlList) {
            try {
                BufferedImage imageBuff = ZipUtils.getImageBuff(s);
                String imgPath = "D:\\upload\\"+path+"\\"+studentName+"-"+i+".jpg";
                File outputFile = new File(imgPath);
                ImageIO.write(imageBuff, "jpg", outputFile);
            } catch (Exception e) {
                log.info("recordId={},studentName={},url={}--获取答题卡图片失败",recordId,studentName,s);
            }
            i++;
        }
        return true;
    }



}
