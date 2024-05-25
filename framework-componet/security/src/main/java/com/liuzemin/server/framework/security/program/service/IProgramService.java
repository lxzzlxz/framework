package com.liuzemin.server.framework.security.program.service;

import com.liuzemin.server.framework.datasource.page.Page;
import com.liuzemin.server.framework.datasource.page.PagedResult;
import com.liuzemin.server.framework.security.program.model.Program;

import java.util.List;
import java.util.Map;

/**
 * 数据范围服务
 * weihao 2018-08-15
 */
public interface IProgramService {

    /**
     * 查询数据范围列表
     * @param program
     * @return
     */
    List<Program> findList(Program program);

    /**
     * 分页查询数据猚
     * @param program
     * @return
     */
    PagedResult<Program> findPagedList(Program program, Page page);

    /**
     * 新增数据范围
     * @param program
     * @return
     */
    Map<String,Object> insert(Program program);

    /**
     * 批量新增数据范围
     * @param programs
     * @return
     */
    Map<String,Object> insertList(List<Program> programs);

    /**
     * 更新数据范围
     * @param program
     * @return
     */
    Map<String,Object> update(Program program);

    /**
     * 批量更新数据范围
     * @param programs
     * @return
     */
    Map<String,Object> updateList(List<Program> programs);

    /**
     * 删除数据范围
     * @param id
     * @return
     */
    Map<String,Object> delete(Long id);

    /**
     * 批量删除数据范围
     * @param id
     * @return
     */
    Map<String,Object> deleteList(List<Long> id);

    Map<String,String> findFirstLevelUnit(String dimensionValue);

    List<Program> findFirstLevel();


    Program findByIdFirstLevel(Long id);

    List<Program>  findFirstLevelList(Program program);
}
