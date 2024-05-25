package com.liuzemin.server.framework.security.permission.service.impl;

import com.liuzemin.server.framework.model.constant.ResponseCode;
import com.liuzemin.server.framework.model.dao.IPermissionDao;
import com.liuzemin.server.framework.model.helper.ResultMapHelper;
import com.liuzemin.server.framework.model.model.Permission;
import com.liuzemin.server.framework.security.permission.service.IPermissionService;
import com.liuzemin.server.framework.security.role.service.IRolePermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class PermissionService implements IPermissionService {

    private static final Logger log = LoggerFactory.getLogger(PermissionService.class);

    @Autowired
    private IPermissionDao permissionDao;

    @Autowired
    private IRolePermissionService rolePermissionService;


    @Override
    public List<Permission> findList(Permission permission) {

        return permissionDao.findList(permission);
    }

    @Override
    public List<Map<String, Object>> findTree(Permission permission) {
        List<Permission> permissions = permissionDao.findList(permission);

        Map<String, Map<String,List<Permission>>> map = new HashMap<>(16);
        if(CollectionUtils.isEmpty(permissions)){
            return Collections.emptyList();
        }

        for(Permission p : permissions){
            if(null == p || StringUtils.isEmpty(p.getScope()) || StringUtils.isEmpty(p.getPrName()) || StringUtils.isEmpty(p.getrId())){
                continue;
            }
            if(map.containsKey(p.getScope())){
                Map<String, List<Permission>> m1 = map.get(p.getScope());
                if(m1.containsKey(p.getrName())){
                    m1.get(p.getrName()).add(p);
                }else{
                    List<Permission> list = new ArrayList<>();
                    list.add(p);
                    m1.put(p.getrName(), list);
                }
            }else{
                Map<String, List<Permission>> m1 = new HashMap<>(16);
                List<Permission> list = new ArrayList<>();
                list.add(p);
                m1.put(p.getrName(),list);
                map.put(p.getScope(), m1);
            }
        }
        //转为前台的json格式
        List<Map<String, Object>> resultList = new ArrayList<>();

        for(String key : map.keySet()){
            Map<String, Object> m1 = new HashMap<String, Object>(16);

            Map<String, List<Permission>> mm = map.get(key);
            List<Map<String, Object>> list = new ArrayList<>();

            for(String k : mm.keySet()){
                List<Permission> permissionList = mm.get(k);
                Map<String, Object> m2 = new HashMap<String, Object>(16);
                m2.put("rName", k);
                m2.put("children", permissionList);
                list.add(m2);
            }

            m1.put("rName",key);
            m1.put("children", list);
            resultList.add(m1);
        }
        return resultList;
    }

    @Override
    public Map<String,Object> insertList(List<Permission> permissions) {
        if(CollectionUtils.isEmpty(permissions)){
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        permissionDao.insertList(permissions);
        return ResultMapHelper.getSuccessMap();
    }

    @Override
    public Map<String,Object> deleteList(List<Long> ids) {
        if(CollectionUtils.isEmpty(ids)){
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        //删除权限点
        permissionDao.deleteList(ids);
        //删除已绑定角色
        rolePermissionService.deleteList(ids);
        return ResultMapHelper.getSuccessMap();
    }

    @Override
    public Map<String, Object> save(String scope, List<Permission> permissions) {
        if(StringUtils.isEmpty(scope)){
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }

        Permission queryParam = new Permission();
        queryParam.setScope(scope);
        List<Permission> origin = findList(queryParam);
        List<Long> originIds = new ArrayList<>();

        List<Permission> temp = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for(Permission permission: permissions){
            if(StringUtils.isEmpty(permission.getrId()) || StringUtils.isEmpty(permission.getPrId())){
                continue;
            }
            String key = permission.getrId() + "$" + permission.getPrId();
            if(!set.contains(key)){
                permission.setScope(scope);
                set.add(key);
                temp.add(permission);
            }
        }
        permissions.clear();
        permissions.addAll(temp);


        if(CollectionUtils.isEmpty(origin)){
            insertList(permissions);
            return ResultMapHelper.getSuccessMap();
        }

        Map<String, Permission> originMap = new HashMap<String,Permission>(16);
        for(Permission permission : origin){
            originMap.put(permission.getrId()+"$"+permission.getPrId(), permission);
            originIds.add(permission.getId());
        }

        Map<String, Permission> newMap = new HashMap<String,Permission>(16);

        List<Long> deletes = new ArrayList<Long>();
        List<Permission> inserts = new ArrayList<Permission>();

        if(CollectionUtils.isEmpty(permissions)){
            deleteList(originIds);
            return ResultMapHelper.getSuccessMap();
        }else{
            for(Permission permission: permissions){
                permission.setScope(scope);
                String key = permission.getrId() + "$" + permission.getPrId();
                newMap.put(key, permission);
                if(!originMap.containsKey(key)){
                    inserts.add(permission);
                }
            }
        }

        for(Permission permission : origin){
            String key = permission.getrId() + "$" + permission.getPrId();
            if(!newMap.containsKey(key)){
                deletes.add(permission.getId());
            }
        }

        if(!CollectionUtils.isEmpty(deletes)){
            deleteList(deletes);
        }

        if(!CollectionUtils.isEmpty(inserts)){
            insertList(inserts);
        }

        log.info("synchronize permission success");
        return ResultMapHelper.getSuccessMap();
    }

    @Override
    public List<Permission> findUserPermissions(Long userId) {


        return permissionDao.findUserPermissions(userId);
    }

}
