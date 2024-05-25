package com.liuzemin.server.framework.security.program.dao;

import com.liuzemin.server.framework.datasource.page.Page;
import com.liuzemin.server.framework.datasource.page.PagedResult;
import com.liuzemin.server.framework.security.program.model.Program;

import java.util.List;
import java.util.Map;

public interface IProgramDao {

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
    Integer insert(Program program);

    /**
     * 批量新增数据范围
     * @param programs
     * @return
     */
    Integer insertList(List<Program> programs);

    /**
     * 更新数据范围
     * @param program
     * @return
     */
    Integer update(Program program);

    /**
     * 批量更新数据范围
     * @param programs
     * @return
     */
    Integer updateList(List<Program> programs);

    /**
     * 删除数据范围
     * @param id
     * @return
     */
    Integer delete(Long id);

    /**
     * 批量删除数据范围
     * @param id
     * @return
     */
    Integer deleteList(List<Long> id);

    List<Map<String, String>> findFirstLevelUnit(String dimensionValue);

    List<Program> findFirstLevel();

    Program findByIdFirstLevel(Long id);

    List<Program> findFirstLevelList(Program program);
}
