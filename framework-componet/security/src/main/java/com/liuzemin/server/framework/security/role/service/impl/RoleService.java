package com.liuzemin.server.framework.security.role.service.impl;

import com.liuzemin.server.framework.datasource.page.Page;
import com.liuzemin.server.framework.datasource.page.PagedResult;
import com.liuzemin.server.framework.model.constant.ResponseCode;
import com.liuzemin.server.framework.model.helper.ResultMapHelper;
import com.liuzemin.server.framework.security.role.dao.IRoleDao;
import com.liuzemin.server.framework.security.role.model.Role;
import com.liuzemin.server.framework.security.role.service.IRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Service
public class RoleService implements IRoleService {

    public static final Logger log = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> findList(Role role) {

        return roleDao.findList(role);
    }

    @Override
    public PagedResult<Role> findPagedList(Role role, Page page) {

        return roleDao.findPagedList(role, page);
    }

    @Override
    public Map<String, Object> insert(Role role) {
        if(null == role){
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        role.setCreateInfo();
        roleDao.insert(role);
        return ResultMapHelper.getSuccessMap();
    }

    @Override
    public Map<String, Object> insertList(List<Role> roles) {
        if(CollectionUtils.isEmpty(roles)){
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        for(Role role : roles){
            role.setCreateInfo();
        }
        roleDao.insertList(roles);
        return ResultMapHelper.getSuccessMap();
    }

    @Override
    public Map<String, Object> update(Role role) {
        if(null == role){
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        role.setUpdateInfo();
        roleDao.update(role);
        return ResultMapHelper.getSuccessMap();
    }

    @Override
    public Map<String, Object> updateList(List<Role> roles) {
        if(CollectionUtils.isEmpty(roles)){
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        for(Role role : roles){
            role.setUpdateInfo();
        }
        roleDao.updateList(roles);
        return ResultMapHelper.getSuccessMap();
    }

    @Override
    public Map<String, Object> delete(Long id) {
        if(null == id){
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        roleDao.delete(id);
        return ResultMapHelper.getSuccessMap();
    }

    @Override
    public Map<String, Object> deleteList(List<Long> ids) {
        if(CollectionUtils.isEmpty(ids)){
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        roleDao.deleteList(ids);
        return ResultMapHelper.getSuccessMap();
    }
}
