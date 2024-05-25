package com.liuzemin.server.framework.security.permission.dao;

import com.liuzemin.server.framework.datasource.page.Page;
import com.liuzemin.server.framework.datasource.page.PagedResult;
import com.liuzemin.server.framework.model.model.BaseService;

import java.util.List;

public interface IBaseServiceDao {

    /**
     * 查询列表
     * @param baseService
     * @return
     */
    List<BaseService> findList(BaseService baseService);

    /**
     * 分页查询
     * @param baseService
     * @return
     */
    PagedResult<BaseService> findPagedList(BaseService baseService, Page page);

    /**
     * 新增
     * @param baseServices
     * @return
     */
    Integer insertList(List<BaseService> baseServices);

    /**
     * 删除
     * @param ids
     * @return
     */
    Integer deleteList(List<Long> ids);
}
