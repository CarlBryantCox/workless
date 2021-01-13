package com.chw.test.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 学校展示对象
 * @author ChenWei
 * @since 2020/03/14
 */
@Data
public class SchoolVo {

    /**
     * 学校id
     */
    private Integer schoolId;

    /**
     * 学校名称
     */
    private String schoolName;

    /**
     * 学校简称
     */
    private String schoolShortName;

    /**
     * 省id
     */
    private String provinceId;

    /**
     * 省名
     */
    private String provinceName;

    /**
     * 市id
     */
    private String cityId;

    /**
     * 市名
     */
    private String cityName;

    /**
     * 区/县id
     */
    private String districtId;

    /**
     * 区名
     */
    private String districtName;

    /**
     * 所属区域id
     */
    private Integer regionId;

    /**
     * 所属区域名
     */
    private String regionName;

    /**
     * 学校管理员id
     */
    private Long schoolManagerId;

    /**
     * 学校管理员姓名
     */
    private String schoolManagerName;

    /**
     * 学校管理员手机号
     */
    private String schoolManagerPhone;

    /**
     * 学校管理员账号
     */
    private String schoolManagerLoginName;

    /**
     * 学校管理员工号
     */
    private String schoolManagerWorkNumber;

    /**
     * 学校管理员账号锁定状态
     */
    private Integer schoolManagerLockedFlag;

    private String lifeLongLearningAccount;

    /**
     * 密码
     */
    private String password;

    /**
     * 开通时间
     */
    private LocalDateTime openTime;

    /**
     * 状态
     */
    private Integer lockedState;

    /**
     * 账号类型
     */
    private Integer useType;

    /**
     * 是否开通服务
     */
    private Integer openFlag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 学校详细地址
     */
    private String addressDetail;

    /**
     * 学校等级id拼接
     */
    private String schoolLevelIds;

    /**
     * 关联学段
     */
    private List<IdNameVo> schoolPhaseList;

    /**
     * 学校等级
     */
    private List<IdNameVo> schoolLevelList;

    private List<SchoolMenuVO> schoolMenuList;
}
