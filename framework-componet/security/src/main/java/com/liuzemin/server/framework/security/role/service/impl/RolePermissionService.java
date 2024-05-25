package com.liuzemin.server.framework.security.role.service.impl;

import com.liuzemin.server.framework.model.constant.ResponseCode;
import com.liuzemin.server.framework.model.helper.ResultMapHelper;
import com.liuzemin.server.framework.security.role.dao.IRolePermissionDao;
import com.liuzemin.server.framework.security.role.model.RolePermission;
import com.liuzemin.server.framework.security.role.service.IRolePermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RolePermissionService implements IRolePermissionService {

    public static final Logger log = LoggerFactory.getLogger(RolePermissionService.class);

    @Autowired
    private IRolePermissionDao rolePermissionDao;

    @Override
    public List<RolePermission> findList(RolePermission rolePermission) {

        return rolePermissionDao.findList(rolePermission);
    }

    @Override
    public Map<String, Object> insertList(List<RolePermission> rolePermissions) {
        if(CollectionUtils.isEmpty(rolePermissions)){
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        for(RolePermission rolePermission : rolePermissions){
            rolePermission.setCreateInfo();
        }
        rolePermissionDao.insertList(rolePermissions);
        return ResultMapHelper.getSuccessMap();
    }

    @Override
    public Map<String, Object> deleteList(List<Long> ids) {
        if(CollectionUtils.isEmpty(ids)){
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        rolePermissionDao.deleteList(ids);
        return ResultMapHelper.getSuccessMap();
    }

    @Override
    public Map<String, Object> deleteByPermissionIds(List<Long> permissionIds) {
        return null;
    }

    @Override
    public Map<String, Object> save(Long roleId, List<RolePermission> rolePermissions) {
        //1.校验
        if(null == roleId){
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        //2,查询原始数据
        RolePermission queryParam = new RolePermission();
        queryParam.setRoleId(roleId);
        List<RolePermission> originList = rolePermissionDao.findList(queryParam);

        //3.
        List<RolePermission> toInsert = new ArrayList<>();
        List<Long> toDelete = new ArrayList<>();

        List<Long> originIds = new ArrayList<>();
        List<Long> originPermissionIds = new ArrayList<>();
        List<Long> newUserIds = new ArrayList<>();

        for(RolePermission rolePermission : rolePermissions){
            rolePermission.setRoleId(roleId);
        }

        //如果原始数据是空，新增新数据，返回；
        if(CollectionUtils.isEmpty(originList)){
            rolePermissionDao.insertList(rolePermissions);
            return ResultMapHelper.getSuccessMap();
        }else{
            for(RolePermission rolePermission : originList){
                originPermissionIds.add(rolePermission.getPermissionId());
                originIds.add(rolePermission.getId());
            }
        }

        //如果新数据是空，删除原始数据，返回；
        if(CollectionUtils.isEmpty(rolePermissions)){
            rolePermissionDao.deleteList(originIds);
            return ResultMapHelper.getSuccessMap();
        }else{
            for(RolePermission rolePermission : rolePermissions){
                newUserIds.add(rolePermission.getPermissionId());
                if(originPermissionIds.contains(rolePermission.getPermissionId())){
                    continue;
                }
                toInsert.add(rolePermission);
            }
        }

        for(RolePermission rolePermission : originList){
            if(newUserIds.contains(rolePermission.getPermissionId())){
                continue;
            }
            toDelete.add(rolePermission.getId());
        }

        //保存数据
        if(!CollectionUtils.isEmpty(toInsert)){
            rolePermissionDao.insertList(toInsert);
        }

        if(!CollectionUtils.isEmpty(toDelete)){
            rolePermissionDao.deleteList(toDelete);
        }

        return ResultMapHelper.getSuccessMap();
    }
}
