package com.chw.test.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 考试考生表
 * </p>
 *
 * @author ChenWei
 * @since 2021-02-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_exam_student")
public class ExamStudent extends Model<ExamStudent> {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 考试id
     */
    private Long examId;

    /**
     * 学校id
     */
    private Integer schoolId;

    /**
     * 学校名称
     */
    private String schoolName;

    /**
     * 年级ID
     */
    private Integer gradeId;

    /**
     * 年级名称
     */
    private String gradeName;

    /**
     * 行政班id
     */
    private Integer classId;

    /**
     * 行政班名称
     */
    private String className;

    /**
     * 学生id
     */
    private Long studentId;

    /**
     * 学生名称
     */
    private String studentName;

    /**
     * 学号
     */
    private String studentNumber;

    /**
     * 考号
     */
    private String examNumber;

    /**
     * 应届标签 0未填 1应届 2.往届  
     */
    private Integer studentType;

    /**
     * 选考科目 dic_select_subject主键
     */
    private Integer subjectTypeId;

    /**
     * 0普通方式1文科2理科3物理4历史5其他
     */
    private Integer typeId;

    /**
     * 创建操作人
     */
    private Long createId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改者
     */
    private Long modifyId;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
