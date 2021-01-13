package com.chw.test.enums;

import com.chw.test.dto.IdNameVo;

import java.util.LinkedList;
import java.util.List;

/**
 * 学段枚举
 * @author ChenWei
 * @since 2020/03/14
 */
public enum SchoolPhaseEnum {

    Kindergarten(1,"幼儿园"),
    Primary_School(2,"小学"),
    Junior_High_School(3,"初中"),
    High_School(4,"高中"),
    University(5,"大学");

    private Integer id;

    private String name;

    SchoolPhaseEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<IdNameVo> getList(){
        List<IdNameVo> list = new LinkedList<>();
        for (SchoolPhaseEnum schoolPhaseEnum : SchoolPhaseEnum.values()) {
            list.add(new IdNameVo(schoolPhaseEnum.getId(),schoolPhaseEnum.getName()));
        }
        return list;
    }

    public static String getNameById(Integer id){
        for (SchoolPhaseEnum schoolPhaseEnum : SchoolPhaseEnum.values()) {
            if(schoolPhaseEnum.getId().equals(id)){
                return schoolPhaseEnum.getName();
            }
        }
        return "";
    }

    public static Integer getIdByName(String name){
        for (SchoolPhaseEnum schoolPhaseEnum : SchoolPhaseEnum.values()) {
            if(schoolPhaseEnum.getName().equals(name)){
                return schoolPhaseEnum.getId();
            }
        }
        return 0;
    }
}
