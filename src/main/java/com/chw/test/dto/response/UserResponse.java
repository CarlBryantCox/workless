package com.chw.test.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author code generator
 * @since 2020-03-16
 */
@Data
public class UserResponse {

    private Long id;

    /**
     * 租户编号
     */
    private Integer tenantId;

    /**
     * 工号/学号
     */
    private String workNumber;

    /**
     * 用户名，非空，若用户未自定义，设置为手机号
     */
    private String loginName;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 生日
     */
    private LocalDate birthday;

    /**
     * 手机号
     */
    private String phoneNum;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 用户默认身份
     */
    private Integer defaultProfession;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    private String lifeLongLearningAccount;
}
