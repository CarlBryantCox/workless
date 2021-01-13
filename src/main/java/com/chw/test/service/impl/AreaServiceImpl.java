package com.chw.test.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chw.test.entity.Area;
import com.chw.test.mapper.AreaMapper;
import com.chw.test.service.AreaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 地区表 服务实现类
 * </p>
 *
 * @author ChenWei
 * @since 2021-01-12
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements AreaService {

    @Override
    public String getDistrictId(Map<String, String> map, String districtName,String cityName) {
        String districtId = map.get(districtName);
        if(districtId!=null){
            return districtId;
        }
        QueryWrapper<Area> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",districtName);
        List<Area> list = this.list(queryWrapper);
        if(list.isEmpty()){
            return null;
        }
        if(list.size()==1){
            districtId = list.get(0).getId();
            map.put(districtName,districtId);
            return districtId;
        }
        for (Area district : list) {
            Area city = this.getById(district.getParentId());
            if(city.getName().contains(cityName)){
                districtId=district.getId();
                map.put(districtName,districtId);
                return districtId;
            }
        }
        return null;
    }
}
