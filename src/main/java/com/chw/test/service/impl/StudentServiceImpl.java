package com.chw.test.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chw.test.config.ApiResponse;
import com.chw.test.dto.MenuDTO;
import com.chw.test.dto.SchoolMenuVO;
import com.chw.test.dto.SchoolVo;
import com.chw.test.dto.response.MenuResponseDTO;
import com.chw.test.dto.response.SchoolResponseDTO;
import com.chw.test.dto.response.UserResponse;
import com.chw.test.entity.Student;
import com.chw.test.enums.IpAddr;
import com.chw.test.mapper.StudentMapper;
import com.chw.test.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ChenWei
 * @since 2021-01-12
 */
@Slf4j
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    private static String insertSchoolUrl = IpAddr.IP_ADDR.getValue()+"/v1/smart/smart-school-base/api/school/insertSchool";

    private static String menuUrl = IpAddr.IP_ADDR.getValue()+"/v1/smart/smart-school-base/api/menu/getParentMenu?platformId=6";

    private static String getUserUrl = IpAddr.IP_ADDR.getValue()+"/v1/smart/smart-school-base/api/feign/user/phone/";

    private static String getSchoolUrl = IpAddr.IP_ADDR.getValue()+"/v1/smart/smart-school-base/api/school/getSchoolById?schoolId=";


    @Resource
    private RestTemplate restTemplate;

    @Override
    public String getToken() {
        MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
        params.add("username", "admin");
        params.add("password", IpAddr.Password.getValue());
        params.add("scope","all");
        params.add("grant_type","password");
        params.add("platform","2");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization","Basic c21hcnQ6MGQ1NTcwNzhiYTViNGYxYzk4NzU3ZjI2MjJmY2QyYTk=");
        ResponseEntity<String> auth = restTemplate.exchange(IpAddr.IP_ADDR.getValue() +"/v1/smart/smart-school-auth/oauth/token",
                HttpMethod.POST, new HttpEntity<>(params, httpHeaders), String.class);
        return "Bearer"+JSON.parseObject(auth.getBody()).getJSONObject("data").getString("access_token");
    }

    @Override
    public String insertSchool(SchoolVo schoolVo,String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",token);
        headers.set("Content-Type","application/json");
        HttpEntity<SchoolVo> requestEntity = new HttpEntity<>(schoolVo,headers);
        ResponseEntity<ApiResponse> exchange;
        try {
            exchange = restTemplate.exchange(insertSchoolUrl, HttpMethod.POST, requestEntity, ApiResponse.class);
        } catch (Exception e) {
            log.info("请求新增学校接口失败：{}",e);
            return "请求新增学校接口失败";
        }
        if(exchange==null || exchange.getBody()==null){
            return "请求新增学校接口失败";
        }
        ApiResponse body = exchange.getBody();
        if(body.getCode()==0){
            return "";
        }
        return body.getMsg();
    }

    @Override
    public List<SchoolMenuVO> getBaseMenuList(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",token);
        headers.set("Content-Type","application/json");
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<MenuResponseDTO> exchange;
        try {
            exchange = restTemplate.exchange(menuUrl, HttpMethod.GET, requestEntity, MenuResponseDTO.class);
        } catch (Exception e) {
            log.info("请求应用菜单接口失败：{}",e);
            return null;
        }
        if(exchange==null || exchange.getBody()==null){
            return null;
        }
        List<MenuDTO> menuList = exchange.getBody().getData();
        if(CollectionUtils.isEmpty(menuList)){
            log.info("请求应用菜单为空！code={}---msg={}",exchange.getBody().getCode(),exchange.getBody().getMsg());
            return null;
        }
        List<SchoolMenuVO> list = new ArrayList<>();
        for (MenuDTO menuDTO : menuList) {
            if("yyzx10000".equals(menuDTO.getPerms())
                    || "yyzx50000".equals(menuDTO.getPerms())
                    || "yyzx60000".equals(menuDTO.getPerms())
                    || "yyzx90000".equals(menuDTO.getPerms())
                    || "yyzx100000".equals(menuDTO.getPerms())
                    || "yyzx110000".equals(menuDTO.getPerms())){
                list.add(new SchoolMenuVO(menuDTO.getId(),menuDTO.getMenuName(),menuDTO.getPerms()));
            }
        }
        return list;
    }

    @Override
    public UserResponse getUserByPhone(String phone,String token) {
        System.out.println(token);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",token);
        headers.set("Content-Type","application/json");
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<UserResponse> exchange;
        try {
            String url = getUserUrl+phone;
            System.out.println(url);
            exchange = restTemplate.exchange(url, HttpMethod.GET, requestEntity, UserResponse.class);
        } catch (Exception e) {
            log.info("请求查询用户接口失败：{}",e);
            return null;
        }
        if(exchange==null){
            return null;
        }
        return exchange.getBody();
    }

    @Override
    public SchoolVo getSchool(Integer schoolId,String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",token);
        headers.set("Content-Type","application/json");
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<SchoolResponseDTO> exchange;
        try {
            String url = getSchoolUrl + schoolId;
            exchange = restTemplate.exchange(url, HttpMethod.GET, requestEntity, SchoolResponseDTO.class);
        } catch (Exception e) {
            log.info("请求查询学校接口失败：{}",e);
            return null;
        }
        if(exchange==null || exchange.getBody()==null){
            return null;
        }
        return exchange.getBody().getData();
    }


}
