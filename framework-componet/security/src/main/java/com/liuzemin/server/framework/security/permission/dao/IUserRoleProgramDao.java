package com.liuzemin.server.framework.security.permission.dao;

import com.liuzemin.server.framework.datasource.page.Page;
import com.liuzemin.server.framework.datasource.page.PagedResult;
import com.liuzemin.server.framework.model.model.User;
import com.liuzemin.server.framework.model.model.UserRoleProgram;

import java.util.List;
import java.util.Map;


public interface IUserRoleProgramDao {

    /**
     * 查询列表
     *
     * @param userRoleProgram
     * @return
     */
    List<UserRoleProgram> findList(UserRoleProgram userRoleProgram);

    /**
     * 分页查询
     *
     * @param userRoleProgram
     * @param page
     * @return
     */
    PagedResult<UserRoleProgram> findPagedList(UserRoleProgram userRoleProgram, Page page);

    /**
     * 分页查询某种权限的 用户
     *
     * @param map
     * @param page
     * @return
     */
    PagedResult<User> findPagedUserList(Map<String, Object> map, Page page);

    /**
     * 分页查询某种角色的用户列表
     *
     * @param userRoleProgram
     * @param user
     * @param page
     * @return
     */
    PagedResult<User> findPagedList(UserRoleProgram userRoleProgram, User user, Page page);

    /**
     * 新增列表
     *
     * @param userRolePrograms
     * @return
     */
    Integer insertList(List<UserRoleProgram> userRolePrograms);

    /**
     * 删除类表
     *
     * @param ids
     * @return
     */
    Integer deleteList(List<Long> ids);

    /**
     * 删除用户权限
     *
     * @param userRoleProgram
     * @return
     */
    Integer delete(UserRoleProgram userRoleProgram);

    /**
     * @param roleName
     * @return 该角色的用户id
     */
    List<UserRoleProgram> queryUserByRoleName(String roleName);

    List<String> queryDimensionValueByUserId(Long userId);

}
