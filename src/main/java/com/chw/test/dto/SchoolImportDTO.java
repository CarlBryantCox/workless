package com.chw.test.dto;

import lombok.Data;

@Data
public class SchoolImportDTO {

    private String reason;

    /**
     * 学校名称
     */
    private String schoolName;

    /**
     * 省名
     */
    private String provinceName;

    /**
     * 市名
     */
    private String cityName;

    /**
     * 区名
     */
    private String districtName;

    /**
     * 学校管理员姓名
     */
    private String schoolManagerName;

    /**
     * 学校管理员手机号
     */
    private String schoolManagerPhone;

    private String schoolPhaseName;

    /**
     * 备注
     */
    private String remark;
}
