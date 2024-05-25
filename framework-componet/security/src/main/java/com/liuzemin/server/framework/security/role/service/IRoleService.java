package com.liuzemin.server.framework.security.role.service;

import com.liuzemin.server.framework.datasource.page.Page;
import com.liuzemin.server.framework.datasource.page.PagedResult;
import com.liuzemin.server.framework.security.role.model.Role;

import java.util.List;
import java.util.Map;

/**
 * 角色服务
 * weihao 2018-08-15
 */
public interface IRoleService {

    /**
     * 查询角色列表
     * @param role
     * @return
     */
    List<Role> findList(Role role);

    /**
     * 分页查询角色
     * @param role
     * @param page
     * @return
     */
    PagedResult<Role> findPagedList(Role role, Page page);

    /**
     * 新增角色
     * @param role
     * @return
     */
    Map<String, Object> insert(Role role);

    /**
     * 批量新增角色
     * @param roles
     * @return
     */
    Map<String,Object> insertList(List<Role> roles);

    /**
     * 更新角色
     * @param role
     * @return
     */
    Map<String,Object> update(Role role);

    /**
     * 批量更新角色
     * @param roles
     * @return
     */
    Map<String,Object> updateList(List<Role> roles);

    /**
     * 删除角色
     * @param id
     * @return
     */
    Map<String,Object> delete(Long id);

    /**
     * 批量删除角色
     * @param ids
     * @return
     */
    Map<String,Object> deleteList(List<Long> ids);
}
