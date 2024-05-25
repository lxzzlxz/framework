package com.liuzemin.server.framework.security.permission.dao;

import com.liuzemin.server.framework.datasource.page.Page;
import com.liuzemin.server.framework.datasource.page.PagedResult;
import com.liuzemin.server.framework.security.permission.model.GroupRoleProgram;

import java.util.List;

public interface IGroupRoleProgramDao {

    /**
     * 查询列表
     * @param groupRoleProgram
     * @return
     */
    List<GroupRoleProgram> findList(GroupRoleProgram groupRoleProgram);

    /**
     * 分页查询列表
     * @param groupRoleProgram
     * @param page
     * @return
     */
    PagedResult<GroupRoleProgram> findPagedList(GroupRoleProgram groupRoleProgram, Page page);

    /**
     * 新增列表
     * @param list
     * @return
     */
    Integer insertList(List<GroupRoleProgram> list);

    /**
     * 删除列表
     * @param list
     * @return
     */
    Integer deleteList(List<Long> list);
}
