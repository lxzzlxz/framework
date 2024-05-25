package com.liuzemin.server.framework.security.permission.service;

import com.liuzemin.server.framework.datasource.page.Page;
import com.liuzemin.server.framework.datasource.page.PagedResult;
import com.liuzemin.server.framework.model.model.R;
import com.liuzemin.server.framework.model.model.UserRoleProgram;

import java.util.List;
import java.util.Map;

public interface IUserRoleProgramService {

    /**
     * 查询列表
     * @param userRoleProgram
     * @return
     */
    List<UserRoleProgram> findList(UserRoleProgram userRoleProgram);

    /**
     * 分页查询
     * @param userRoleProgram
     * @param page
     * @return
     */
    PagedResult<UserRoleProgram> findPagedList(UserRoleProgram userRoleProgram, Page page);

    /**
     * 新增列表
     * @param userRolePrograms
     * @return
     */
    Map<String,Object> insertList(List<UserRoleProgram> userRolePrograms);

    /**
     * 删除
     * @param userRoleProgram
     * @return
     */
    Map<String, Object> delete(UserRoleProgram userRoleProgram);

    /**
     * 删除类表
     * @param ids
     * @return
     */
    Map<String,Object> deleteList(List<Long> ids);

    /**
     *  增量更新
     * @param userId
     * @return
     */
    Map<String,Object> save(Long userId, List<UserRoleProgram> userRolePrograms);

    /**
     * 更新或者更新，存在更新，不存在新增
     * @param userRoleProgram
     * @return
     */
    Map<String, Object> updateAndInsert(UserRoleProgram userRoleProgram);

	List<UserRoleProgram> queryUserByRoleName(String roleName);

	List<String> queryDimensionValueByUserId(Long userId);

    Map<Long, String> getAllDemandUserIdCompanyMap();

    R<String> deleteByUserId(UserRoleProgram userRoleProgram);

   void refresh(Long roleId);

    R<String> deletRroleProgram(UserRoleProgram userRoleProgram);

}
