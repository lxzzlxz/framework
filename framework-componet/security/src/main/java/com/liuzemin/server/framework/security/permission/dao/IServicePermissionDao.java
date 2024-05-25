package com.liuzemin.server.framework.security.permission.dao;

import com.liuzemin.server.framework.datasource.page.Page;
import com.liuzemin.server.framework.datasource.page.PagedResult;
import com.liuzemin.server.framework.model.model.ServicePermission;

import java.util.List;
import java.util.Map;

public interface IServicePermissionDao {

    /**
     *
     * @param servicePermission
     * @return
     */
    List<ServicePermission> findList(ServicePermission servicePermission);

    /**
     *
     * @param servicePermission
     * @param page
     * @return
     */
    PagedResult<ServicePermission> findPagedList(ServicePermission servicePermission, Page page);

    /**
     *
     * @param servicePermissions
     * @return
     */
    Map<String,Object> insertList(List<ServicePermission> servicePermissions);

    /**
     *
     * @param ids
     * @return
     */
    Map<String,Object> deleteList(List<Long> ids);
}
