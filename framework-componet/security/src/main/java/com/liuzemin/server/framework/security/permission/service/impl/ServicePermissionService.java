package com.liuzemin.server.framework.security.permission.service.impl;

import com.liuzemin.server.framework.datasource.page.Page;
import com.liuzemin.server.framework.datasource.page.PagedResult;
import com.liuzemin.server.framework.model.helper.ResultMapHelper;
import com.liuzemin.server.framework.model.model.ServicePermission;
import com.liuzemin.server.framework.security.permission.dao.IServicePermissionDao;
import com.liuzemin.server.framework.security.permission.service.IServicePermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ServicePermissionService implements IServicePermissionService {

    public static final Logger log = LoggerFactory.getLogger(ServicePermissionService.class);

    @Autowired
    private IServicePermissionDao servicePermissionDao;

    @Override
    public List<ServicePermission> findList(ServicePermission servicePermission) {
        return servicePermissionDao.findList(servicePermission);
    }

    @Override
    public Object findPagedList(ServicePermission servicePermission, Object page) {
        if(page instanceof  Page){
            Page p = (Page) page;
            return servicePermissionDao.findPagedList(servicePermission, p);
        }
        return new PagedResult<ServicePermission>();
    }

    @Override
    public Map<String, Object> insertList(List<ServicePermission> servicePermissions) {
        if(CollectionUtils.isEmpty(servicePermissions)){
            return ResultMapHelper.getParameterErrorMap();
        }
        for(ServicePermission servicePermission : servicePermissions){
            servicePermission.setCreateInfo();
        }
        servicePermissionDao.insertList(servicePermissions);
        return ResultMapHelper.getSuccessMap();
    }

    @Override
    public Map<String, Object> deleteList(List<Long> ids) {
        if(CollectionUtils.isEmpty(ids)){
            return ResultMapHelper.getParameterErrorMap();
        }
        servicePermissionDao.deleteList(ids);
        return ResultMapHelper.getSuccessMap();
    }

    @Override
    public Map<String, Object> save(String serviceName, List<ServicePermission> authorizedList) {
        //1.校验
        if(StringUtils.isEmpty(serviceName)){
            return ResultMapHelper.getParameterErrorMap();
        }

        //2,查询原始数据
        ServicePermission queryParam = new ServicePermission();
        queryParam.setServiceName(serviceName);
        List<ServicePermission> originList = servicePermissionDao.findList(queryParam);

        List<ServicePermission> toInsert = new ArrayList<>();
        List<Long> toDelete = new ArrayList<>();

        List<Long> originIds = new ArrayList<>();
        List<String> authorizeds = new ArrayList<>();
        List<String> newAuthorizeds = new ArrayList<>();

        for(ServicePermission servicePermission : authorizedList){

            servicePermission.setServiceName(serviceName);
        }


        //如果原始数据是空，新增新数据，返回；
        if(CollectionUtils.isEmpty(originList)){
            insertList(authorizedList);
            return ResultMapHelper.getSuccessMap();
        }else{
            for(ServicePermission servicePermission : originList){
                authorizeds.add(servicePermission.getAuthorized());
                originIds.add(servicePermission.getId());
            }
        }

        //如果新数据是空，删除原始数据，返回；
        if(CollectionUtils.isEmpty(authorizedList)){
            servicePermissionDao.deleteList(originIds);
            return ResultMapHelper.getSuccessMap();
        }else{
            for(ServicePermission servicePermission : authorizedList){
                newAuthorizeds.add(servicePermission.getAuthorized());
                if(authorizeds.contains(servicePermission.getAuthorized())){
                    continue;
                }
                toInsert.add(servicePermission);
            }
        }

        for(ServicePermission servicePermission : originList){
            if(newAuthorizeds.contains(servicePermission.getAuthorized())){
                continue;
            }
            toDelete.add(servicePermission.getId());
        }

        //保存数据
        if(!CollectionUtils.isEmpty(toInsert)){
            servicePermissionDao.insertList(toInsert);
        }

        if(!CollectionUtils.isEmpty(toDelete)){
            servicePermissionDao.deleteList(toDelete);
        }

        return ResultMapHelper.getSuccessMap();
    }
}
