package com.liuzemin.server.framework.security.program.dao;

import com.liuzemin.server.framework.datasource.page.Page;
import com.liuzemin.server.framework.datasource.page.PagedResult;
import com.liuzemin.server.framework.model.model.ProgramItem;

import java.util.List;
import java.util.Map;

public interface IProgramItemDao {

    /**
     * 查询数据范围详情列表
     * @param programItem
     * @return
     */
    List<ProgramItem> findList(ProgramItem programItem);

    /**
     * 分页查询数据范围详情列表
     * @param programItem
     * @param page
     * @return
     */
    PagedResult<ProgramItem> findPagedList(ProgramItem programItem, Page page);

    /**
     * 新增数据范围列表
     * @param programItems
     * @return
     */
    Integer insertList(List<ProgramItem> programItems);

    /**
     * 删除数据范围列表
     * @param ids
     * @return
     */
    Integer deleteList(List<Long> ids);

    /**
     *
     * @param queryMap
     * @return
     */
    List<String> findProgramItemsList(Map<String, Object> queryMap);

    /**
     *
     * @param programItem
     * @return
     */
    List<Long> findProjectUnitProgramList(ProgramItem programItem);
}
