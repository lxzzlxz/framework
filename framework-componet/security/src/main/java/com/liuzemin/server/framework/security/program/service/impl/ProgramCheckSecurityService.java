package com.liuzemin.server.framework.security.program.service.impl;

import com.liuzemin.server.framework.model.context.RequestContext;
import com.liuzemin.server.framework.model.service.IProgramCheckService;
import com.liuzemin.server.framework.security.program.dao.IProgramItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProgramCheckSecurityService implements IProgramCheckService {

    @Autowired
    private IProgramItemDao programItemDao;

    @Override
    public List<String> findProgramItems(String dimensionCode, String resourceValue, String operationValue) {
        Map<String, Object> queryMap = new HashMap<>(16);
        queryMap.put("rId", resourceValue);
        queryMap.put("prId", operationValue);
        queryMap.put("dimensionCode", dimensionCode);
        queryMap.put("userId", RequestContext.getCurrent().getUser().getId());
        return programItemDao.findProgramItemsList(queryMap);
    }

    @Override
    public List<String> findProgramItemsByUserId(String dimensionCode, String resourceValue, String operationValue, Long userId) {
        Map<String, Object> queryMap = new HashMap<>(16);
        queryMap.put("rId", resourceValue);
        queryMap.put("prId", operationValue);
        queryMap.put("dimensionCode", dimensionCode);
        queryMap.put("userId", userId);
        return programItemDao.findProgramItemsList(queryMap);
    }
}
