package com.liuzemin.server.framework.security.permission.service.impl;

import com.liuzemin.server.framework.datasource.page.Page;
import com.liuzemin.server.framework.datasource.page.PagedResult;
import com.liuzemin.server.framework.model.constant.ResponseCode;
import com.liuzemin.server.framework.model.helper.ResultMapHelper;
import com.liuzemin.server.framework.security.permission.dao.IGroupRoleProgramDao;
import com.liuzemin.server.framework.security.permission.model.GroupRoleProgram;
import com.liuzemin.server.framework.security.permission.service.IGroupRoleProgramService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GroupRoleProgramService implements IGroupRoleProgramService {

    public static final Logger log = LoggerFactory.getLogger(GroupRoleProgram.class);

    @Autowired
    private IGroupRoleProgramDao groupRoleProgramDao;

    @Override
    public List<GroupRoleProgram> findList(GroupRoleProgram groupRoleProgram) {

        return groupRoleProgramDao.findList(groupRoleProgram);
    }

    @Override
    public PagedResult<GroupRoleProgram> findPagedList(GroupRoleProgram groupRoleProgram, Page page) {

        return groupRoleProgramDao.findPagedList(groupRoleProgram, page);
    }

    @Override
    public Map<String, Object> insertList(List<GroupRoleProgram> list) {
        if(CollectionUtils.isEmpty(list)){
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        for(GroupRoleProgram groupRoleProgram : list){
            groupRoleProgram.setCreateInfo();
        }
        groupRoleProgramDao.insertList(list);
        return ResultMapHelper.getSuccessMap();
    }

    @Override
    public Map<String, Object> deleteList(List<Long> list) {
        if(CollectionUtils.isEmpty(list)){
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        groupRoleProgramDao.deleteList(list);
        return ResultMapHelper.getSuccessMap();
    }

    @Override
    public Map<String, Object> save(Long groupId, List<GroupRoleProgram> list) {
        //1.校验
        if(null == groupId){
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        //2,查询原始数据
        GroupRoleProgram queryParam = new GroupRoleProgram();
        queryParam.setGroupId(groupId);
        List<GroupRoleProgram> originList = groupRoleProgramDao.findList(queryParam);

        //3.
        List<GroupRoleProgram> toInsert = new ArrayList<>();
        List<Long> toDelete = new ArrayList<>();

        List<Long> originIds = new ArrayList<>();
        List<String> originUserIds = new ArrayList<>();
        List<String> newUserIds = new ArrayList<>();

        //如果原始数据是空，新增新数据，返回；
        if(CollectionUtils.isEmpty(originList)){
            groupRoleProgramDao.insertList(list);
            return ResultMapHelper.getSuccessMap();
        }else{
            for(GroupRoleProgram groupRoleProgram : originList){
                originUserIds.add(groupRoleProgram.getRoleId()+"$"+groupRoleProgram.getProgramId());
                originIds.add(groupRoleProgram.getId());
            }
        }

        //如果新数据是空，删除原始数据，返回；
        if(CollectionUtils.isEmpty(list)){
            groupRoleProgramDao.deleteList(originIds);
            return ResultMapHelper.getSuccessMap();
        }else{
            for(GroupRoleProgram groupRoleProgram : list){
                String key = groupRoleProgram.getRoleId()+"$"+groupRoleProgram.getProgramId();
                newUserIds.add(key);
                if(originUserIds.contains(key)){
                    continue;
                }
                toInsert.add(groupRoleProgram);
            }
        }

        for(GroupRoleProgram groupRoleProgram : originList){
            String key = groupRoleProgram.getRoleId()+"$"+groupRoleProgram.getProgramId();
            if(newUserIds.contains(key)){
                continue;
            }
            toDelete.add(groupRoleProgram.getId());
        }

        //保存数据
        if(!CollectionUtils.isEmpty(toInsert)){
            groupRoleProgramDao.insertList(toInsert);
        }

        if(!CollectionUtils.isEmpty(toDelete)){
            groupRoleProgramDao.deleteList(toDelete);
        }

        return ResultMapHelper.getSuccessMap();
    }
}
