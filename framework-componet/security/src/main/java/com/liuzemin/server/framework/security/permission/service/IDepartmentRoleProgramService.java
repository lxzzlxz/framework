package com.liuzemin.server.framework.security.permission.service;

import com.liuzemin.server.framework.datasource.page.Page;
import com.liuzemin.server.framework.datasource.page.PagedResult;
import com.liuzemin.server.framework.security.permission.model.DepartmentRoleProgram;

import java.util.List;
import java.util.Map;

public interface IDepartmentRoleProgramService {

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
     *
     * @param list
     * @return
     */
    Map<String,Object> insertList(List<DepartmentRoleProgram> list);

    /**
     *
     * @param list
     * @return
     */
    Map<String,Object> deleteList(List<Long> list);

    /**
     * 批量更新
     * @param groupId
     * @param list
     * @return
     */
    Map<String,Object> save(Long groupId, List<DepartmentRoleProgram> list);
}
