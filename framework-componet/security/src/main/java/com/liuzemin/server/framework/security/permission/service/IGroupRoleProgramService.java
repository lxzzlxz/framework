package com.liuzemin.server.framework.security.permission.service;

import com.liuzemin.server.framework.datasource.page.Page;
import com.liuzemin.server.framework.datasource.page.PagedResult;
import com.liuzemin.server.framework.security.permission.model.GroupRoleProgram;

import java.util.List;
import java.util.Map;

public interface IGroupRoleProgramService {

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
    Map<String,Object> insertList(List<GroupRoleProgram> list);

    /**
     * 删除列表
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
    Map<String,Object> save(Long groupId, List<GroupRoleProgram> list);
}
