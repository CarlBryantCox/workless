package com.chw.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chw.test.entity.Area;

import java.util.Map;

/**
 * <p>
 * 地区表 服务类
 * </p>
 *
 * @author ChenWei
 * @since 2021-01-12
 */
public interface AreaService extends IService<Area> {

    String getDistrictId(Map<String,String> map, String districtName,String cityName);

}
