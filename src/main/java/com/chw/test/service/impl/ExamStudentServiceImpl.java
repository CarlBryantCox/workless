package com.chw.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chw.test.dto.ExamStudentPaperVO;
import com.chw.test.dto.response.CardUrlResponseDTO;
import com.chw.test.entity.ExamStudent;
import com.chw.test.enums.IpAddr;
import com.chw.test.mapper.ExamStudentMapper;
import com.chw.test.service.ExamStudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 考试考生表 服务实现类
 * </p>
 *
 * @author ChenWei
 * @since 2021-02-02
 */
@Slf4j
@Service
public class ExamStudentServiceImpl extends ServiceImpl<ExamStudentMapper, ExamStudent> implements ExamStudentService {

    private static final String getCardUrl = IpAddr.IP_ADDR.getValue()+"/v1/smart/yuejuan-union-business/api/subject/achievement/getExamStudentPaper?recordId=";

    @Resource
    private RestTemplate restTemplate;

    @Override
    public List<String> getStudentCardUrlList(Long recordId,String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",token);
        headers.set("Content-Type","application/json");
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<CardUrlResponseDTO> exchange;
        try {
            String url = getCardUrl + recordId;
            exchange = restTemplate.exchange(url, HttpMethod.GET, requestEntity, CardUrlResponseDTO.class);
        } catch (Exception e) {
            log.info("recordId={},请求查询答题卡地址接口失败：{}",recordId,e);
            return Collections.emptyList();
        }
        if(exchange==null || exchange.getBody()==null){
            return Collections.emptyList();
        }
        ExamStudentPaperVO data = exchange.getBody().getData();
        if(data==null){
            return Collections.emptyList();
        }
        String cardSourceUrl = data.getCardSourceUrl();
        if(StringUtils.isEmpty(cardSourceUrl)){
            return Collections.emptyList();
        }
        String[] split = cardSourceUrl.split(",");
        List<String> list = new ArrayList<>();
        Collections.addAll(list, split);
        return list;
    }
}
