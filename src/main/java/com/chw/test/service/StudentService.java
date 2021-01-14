package com.chw.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chw.test.dto.SchoolMenuVO;
import com.chw.test.dto.SchoolVo;
import com.chw.test.dto.response.UserResponse;
import com.chw.test.entity.Student;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ChenWei
 * @since 2021-01-12
 */
public interface StudentService extends IService<Student> {

    String getToken();

    String insertSchool(SchoolVo schoolVo,String token);

    List<SchoolMenuVO> getBaseMenuList(String token);

    UserResponse getUserByPhone(String phone,String token);

    SchoolVo getSchool(Integer schoolId,String token);

}
