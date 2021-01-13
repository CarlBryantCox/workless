package com.chw.test.dto;

import lombok.Data;

@Data
public class SchoolMenuVO {

    private Integer menuId;

    private String menuName;

    private String menuCode;

    public SchoolMenuVO() {
    }

    public SchoolMenuVO(Integer menuId) {
        this.menuId = menuId;
    }

    public SchoolMenuVO(Integer menuId, String menuName, String menuCode) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuCode = menuCode;
    }
}
