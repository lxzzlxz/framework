package com.liuzemin.server.framework.security.permission.service;

import com.liuzemin.server.framework.model.model.BaseService;

import java.util.List;
import java.util.Map;

/**
 * 服务
 * weihao 2018-08-18
 */
public interface IBaseServiceService {

    /**
     * 查询列表
     * @param baseService
     * @return
     */
    List<BaseService> findList(BaseService baseService);

    /**
     * 分页查询
     * @param baseService
     * @param page
     * @return
     */
    Object findPagedList(BaseService baseService, Object page);

    /**
     * 新增列表
     * @param baseServices
     * @return
     */
    Map<String,Object> insertList(List<BaseService> baseServices);

    /**
     * 删除
     * @param ids
     * @return
     */
    Map<String,Object> deleteList(List<Long> ids);
}
