package com.liuzemin.server.framework.model.dao;

import com.liuzemin.server.framework.model.model.Permission;

import java.util.List;

/**
 * 权限点
 */
public interface IPermissionDao {

    /**
     * 查询权限点
     * @return
     */
    List<Permission> findList(Permission permission);

    /**
     * 新增权限点
     * @param permissions
     * @return
     */
    Integer insertList(List<Permission> permissions);

    /**
     * 删除权限点
     * @param ids
     * @return
     */
    Integer deleteList(List<Long> ids);

    /**
     * 查询用户权限
     * @param userId
     * @return
     */
    List<Permission> findUserPermissions(Long userId);
}
