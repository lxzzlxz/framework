package com.liuzemin.server.framework.security.role.service;

import com.liuzemin.server.framework.security.role.model.RolePermission;

import java.util.List;
import java.util.Map;

/**
 * 角色权限服务
 * weihao 2018-08-18
 */
public interface IRolePermissionService {

    /**
     * 查询角色权限列表
     * @param rolePermission
     * @return
     */
    List<RolePermission> findList(RolePermission rolePermission);

    /**
     * 批量新增角色权限
     * @param rolePermissions
     * @return
     */
    Map<String, Object> insertList(List<RolePermission> rolePermissions);

    /**
     * 批量删除角色权限
     * @param ids
     * @return
     */
    Map<String, Object> deleteList(List<Long> ids);

    /**
     * 根据权限id删除
     * @param permissionIds
     * @return
     */
    Map<String, Object> deleteByPermissionIds(List<Long> permissionIds);

    /**
     * 增量更新角色
     * @param roleId
     * @param rolePermissions
     * @return
     */
    Map<String,Object> save(Long roleId, List<RolePermission> rolePermissions);
}
