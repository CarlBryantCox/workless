package com.chw.test.mapper;

import com.chw.test.dto.StudentVO;
import com.chw.test.entity.ExamStudent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 考试考生表 Mapper 接口
 * </p>
 *
 * @author ChenWei
 * @since 2021-02-02
 */
public interface ExamStudentMapper extends BaseMapper<ExamStudent> {

    List<StudentVO> getStudentList(@Param("examId") Long examId, @Param("subjectId") Integer subjectId, @Param("schoolId") Integer schoolId);

}
