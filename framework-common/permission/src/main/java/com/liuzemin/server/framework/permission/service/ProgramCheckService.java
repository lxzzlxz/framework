package com.liuzemin.server.framework.permission.service;

import com.liuzemin.server.framework.model.helper.ResultMapHelper;
import com.liuzemin.server.framework.model.service.IProgramCheckService;
import com.liuzemin.server.framework.permission.client.SecurityFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class ProgramCheckService implements IProgramCheckService {

    public static final Logger log = LoggerFactory.getLogger(ProgramCheckService.class);

    @Autowired
    private SecurityFeignClient securityFeignClient;

    @Override
    public List<String> findProgramItems(String dimensionCode, String resourceValue, String operationValue) {
        Map<String, Object> map = securityFeignClient.findUserProgramList(dimensionCode, resourceValue, operationValue);
        if(!ResultMapHelper.isSuccess(map)){
            return Collections.EMPTY_LIST;
        }
        return (List<String>) map.get("datas");
    }

    @Override
    public List<String> findProgramItemsByUserId(String dimensionCode, String resourceValue, String operationValue, Long userId) {
        Map<String, Object> map = securityFeignClient.findProgramItemsByUserId(dimensionCode, resourceValue, operationValue,userId);
        if(!ResultMapHelper.isSuccess(map)){
            return Collections.EMPTY_LIST;
        }
        return (List<String>) map.get("datas");
    }
}
