package com.liuzemin.server.framework.security.program.service;

import com.liuzemin.server.framework.datasource.page.Page;
import com.liuzemin.server.framework.datasource.page.PagedResult;
import com.liuzemin.server.framework.model.model.ProgramItem;

import java.util.List;
import java.util.Map;

public interface IProgramItemService {

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
    Map<String,Object> insertList(List<ProgramItem> programItems);

    /**
     * 删除数据范围列表
     * @param ids
     * @return
     */
    Map<String,Object> deleteList(List<Long> ids);

    /**
     * 增量更新
     * @param programId
     * @param programItemList
     * @return
     */
    Map<String,Object> save(Long programId, List<ProgramItem> programItemList);

    Map<String,String> findProgramMap(ProgramItem programItem);
}
