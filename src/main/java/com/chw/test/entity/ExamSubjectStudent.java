package com.chw.test.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 学科考生表
 * </p>
 *
 * @author ChenWei
 * @since 2021-02-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_exam_subject_student")
public class ExamSubjectStudent extends Model<ExamSubjectStudent> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 考生id
     */
    private Long examStudentId;

    /**
     * 考试id
     */
    private Long examId;

    /**
     * 学科id
     */
    private Integer subjectId;

    /**
     * 考场号
     */
    private String roomNumber;

    /**
     * 座位号
     */
    private String seatNumber;

    /**
     * 扫描答题卡id 为0 表示未上传 此字段暂未使用
     */
    private Long scanPaperId;

    /**
     * 试卷原图url，逗号分隔 此字段暂未使用
     */
    private String cardSourceUrl;

    /**
     * 0 未上传  1 正常 2补录 3扫描缺考 4正常标记缺考 5补录标记缺考 6未扫描标记缺考
     */
    private Integer paperType;

    /**
     * 已批阅主观题小题数 此数据不精确 仅用来减少不必要的判断
     */
    private Integer markingCount;

    /**
     * 0未阅 1已阅
     */
    private Integer markingStatus;

    /**
     * 客观题分数
     */
    private Float objectiveScore;

    /**
     * 主观题分数
     */
    private Float subjectiveScore;

    /**
     * 最后统计一个总得分
     */
    private Float correctScore;

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
