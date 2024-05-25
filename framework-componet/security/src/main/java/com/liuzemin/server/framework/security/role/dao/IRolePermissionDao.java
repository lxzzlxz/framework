package com.liuzemin.server.framework.security.role.dao;

import com.liuzemin.server.framework.security.role.model.RolePermission;

import java.util.List;

public interface IRolePermissionDao {

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
   Integer insertList(List<RolePermission> rolePermissions);

    /**
     * 批量删除角色权限
     * @param ids
     * @return
     */
    Integer deleteList(List<Long> ids);

    /**
     * 根据权限id删除
     * @param permissionIds
     * @return
     */
    Integer deleteByPermissionIds(List<Long> permissionIds);

}
