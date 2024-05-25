package com.liuzemin.server.framework.security.permission.dao;

import com.liuzemin.server.framework.datasource.page.Page;
import com.liuzemin.server.framework.datasource.page.PagedResult;
import com.liuzemin.server.framework.security.permission.model.DepartmentRoleProgram;

import java.util.List;

public interface IDepartmentRoleProgramDao {

    /**
     * 查询列表
     * @param groupRoleProgram
     * @return
     */
    List<DepartmentRoleProgram> findList(DepartmentRoleProgram groupRoleProgram);

    /**
     * 分页查询列表
     * @param groupRoleProgram
     * @param page
     * @return
     */
    PagedResult<DepartmentRoleProgram> findPagedList(DepartmentRoleProgram groupRoleProgram, Page page);

    /**
     * 新增列表
     * @param list
     * @return
     */
    Integer insertList(List<DepartmentRoleProgram> list);

    /**
     * 删除列表
     * @param list
     * @return
     */
    Integer deleteList(List<Long> list);
}
