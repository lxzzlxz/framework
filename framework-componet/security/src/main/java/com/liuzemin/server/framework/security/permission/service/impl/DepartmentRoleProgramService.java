package com.liuzemin.server.framework.security.permission.service.impl;

import com.liuzemin.server.framework.datasource.page.Page;
import com.liuzemin.server.framework.datasource.page.PagedResult;
import com.liuzemin.server.framework.model.constant.ResponseCode;
import com.liuzemin.server.framework.model.helper.ResultMapHelper;
import com.liuzemin.server.framework.security.permission.dao.IDepartmentRoleProgramDao;
import com.liuzemin.server.framework.security.permission.model.DepartmentRoleProgram;
import com.liuzemin.server.framework.security.permission.service.IDepartmentRoleProgramService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DepartmentRoleProgramService implements IDepartmentRoleProgramService {

    public static final Logger log = LoggerFactory.getLogger(DepartmentRoleProgramService.class);

    @Autowired
    private IDepartmentRoleProgramDao departmentRoleProgramDao;

    @Override
    public List<DepartmentRoleProgram> findList(DepartmentRoleProgram groupRoleProgram) {

        return departmentRoleProgramDao.findList(groupRoleProgram);
    }

    @Override
    public PagedResult<DepartmentRoleProgram> findPagedList(DepartmentRoleProgram groupRoleProgram, Page page) {

        return departmentRoleProgramDao.findPagedList(groupRoleProgram, page);
    }
    @Override
    public Map<String, Object> insertList(List<DepartmentRoleProgram> list) {
        if(CollectionUtils.isEmpty(list)){
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        for(DepartmentRoleProgram groupRoleProgram : list){
            groupRoleProgram.setCreateInfo();
        }
        departmentRoleProgramDao.insertList(list);
        return ResultMapHelper.getSuccessMap();
    }

    @Override
    public Map<String, Object> deleteList(List<Long> list) {
        if(CollectionUtils.isEmpty(list)){
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        departmentRoleProgramDao.deleteList(list);
        return ResultMapHelper.getSuccessMap();
    }

    @Override
    public Map<String, Object> save(Long departmentId, List<DepartmentRoleProgram> list) {
        //1.校验
        if(null == departmentId){
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        //2,查询原始数据
        DepartmentRoleProgram queryParam = new DepartmentRoleProgram();
        queryParam.setDepartmentId(departmentId);
        List<DepartmentRoleProgram> originList = departmentRoleProgramDao.findList(queryParam);

        //3.
        List<DepartmentRoleProgram> toInsert = new ArrayList<>();
        List<Long> toDelete = new ArrayList<>();

        List<Long> originIds = new ArrayList<>();
        List<String> originUserIds = new ArrayList<>();
        List<String> newUserIds = new ArrayList<>();

        //如果原始数据是空，新增新数据，返回；
        if(CollectionUtils.isEmpty(originList)){
            departmentRoleProgramDao.insertList(list);
            return ResultMapHelper.getSuccessMap();
        }else{
            for(DepartmentRoleProgram groupRoleProgram : originList){
                originUserIds.add(groupRoleProgram.getRoleId()+"$"+groupRoleProgram.getProgramId());
                originIds.add(groupRoleProgram.getId());
            }
        }

        //如果新数据是空，删除原始数据，返回；
        if(CollectionUtils.isEmpty(list)){
            departmentRoleProgramDao.deleteList(originIds);
            return ResultMapHelper.getSuccessMap();
        }else{
            for(DepartmentRoleProgram groupRoleProgram : list){
                String key = groupRoleProgram.getRoleId()+"$"+groupRoleProgram.getProgramId();
                newUserIds.add(key);
                if(originUserIds.contains(key)){
                    continue;
                }
                toInsert.add(groupRoleProgram);
            }
        }

        for(DepartmentRoleProgram groupRoleProgram : originList){
            String key = groupRoleProgram.getRoleId()+"$"+groupRoleProgram.getProgramId();
            if(newUserIds.contains(key)){
                continue;
            }
            toDelete.add(groupRoleProgram.getId());
        }

        //保存数据
        if(!CollectionUtils.isEmpty(toInsert)){
            departmentRoleProgramDao.insertList(toInsert);
        }

        if(!CollectionUtils.isEmpty(toDelete)){
            departmentRoleProgramDao.deleteList(toDelete);
        }

        return ResultMapHelper.getSuccessMap();
    }
}
