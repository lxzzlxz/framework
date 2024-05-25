package com.liuzemin.server.framework.security.permission.service;

import com.liuzemin.server.framework.model.model.ServicePermission;

import java.util.List;
import java.util.Map;

public interface IServicePermissionService {

    /**
     * 查询
     * @param servicePermission
     * @return
     */
    List<ServicePermission> findList(ServicePermission servicePermission);

    /**
     * 分页查询
     * @param servicePermission
     * @param page
     * @return
     */
    Object findPagedList(ServicePermission servicePermission, Object page);

    /**
     * 新增
     * @param servicePermissions
     * @return
     */
    Map<String,Object> insertList(List<ServicePermission> servicePermissions);

    /**
     * 删除
     * @param ids
     * @return
     */
    Map<String,Object> deleteList(List<Long> ids);

    /**
     * 增量更新
     * @param serviceName
     * @param authorizedList
     * @return
     */
    Map<String,Object> save(String serviceName, List<ServicePermission> authorizedList);
}
