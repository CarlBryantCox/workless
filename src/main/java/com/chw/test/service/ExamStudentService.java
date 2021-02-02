package com.chw.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chw.test.entity.ExamStudent;

import java.util.List;

/**
 * <p>
 * 考试考生表 服务类
 * </p>
 *
 * @author ChenWei
 * @since 2021-02-02
 */
public interface ExamStudentService extends IService<ExamStudent> {

    List<String> getStudentCardUrlList(Long recordId,String token);

}
