package com.liuzemin.server.framework.security.permission.controller;

import com.liuzemin.server.framework.datasource.page.Page;
import com.liuzemin.server.framework.datasource.page.PagedResult;
import com.liuzemin.server.framework.model.annotation.Operation;
import com.liuzemin.server.framework.model.annotation.Resource;
import com.liuzemin.server.framework.security.permission.model.DepartmentRoleProgram;
import com.liuzemin.server.framework.security.permission.service.IDepartmentRoleProgramService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("departmentRoleProgram/v1")
@Resource(value = "departmentRoleProgram", desc="部门权限")
@Api(tags="部门权限管理")
public class DepartmentRoleProgramController {

    public static final Logger log = LoggerFactory.getLogger(DepartmentRoleProgramController.class);

    @Autowired
    private IDepartmentRoleProgramService departmentRoleProgramService;

    @RequestMapping(value="/findList", method=RequestMethod.GET)
    @Operation(value = "find", desc = "查询")
    @ApiOperation("查询")
    public List<DepartmentRoleProgram> findList(@ModelAttribute DepartmentRoleProgram groupRoleProgram){

        return departmentRoleProgramService.findList(groupRoleProgram);
    }

    @RequestMapping(value="/findPagedList/{pageSize}/{curPage}", method=RequestMethod.GET)
    @Operation(value = "find", desc = "查询")
    @ApiOperation("分页查询")
    public PagedResult<DepartmentRoleProgram> findPagedList(@ModelAttribute DepartmentRoleProgram groupRoleProgram, @PathParam("")Page page){

        return departmentRoleProgramService.findPagedList(groupRoleProgram, page);
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    @Operation(value = "update", desc = "修改")
    @ApiOperation("修改")
    public Map<String,Object> save(@RequestParam("")Long departmentId, @RequestBody List<DepartmentRoleProgram> groupRolePrograms){

        return departmentRoleProgramService.save(departmentId, groupRolePrograms);
    }
}
