package com.liuzemin.server.framework.security.permission.controller;

import com.liuzemin.server.framework.datasource.page.Page;
import com.liuzemin.server.framework.datasource.page.PagedResult;
import com.liuzemin.server.framework.model.annotation.Operation;
import com.liuzemin.server.framework.model.annotation.Resource;
import com.liuzemin.server.framework.security.permission.model.GroupRoleProgram;
import com.liuzemin.server.framework.security.permission.service.IGroupRoleProgramService;
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
@RequestMapping("/groupRoleProgram/v1")
@Resource(value = "groupRoleProgram", desc="群组权限")
@Api(tags = "群组权限管理")
public class GroupRoleProgramController {

    public static final Logger log = LoggerFactory.getLogger(GroupRoleProgram.class);

    @Autowired
    private IGroupRoleProgramService groupRoleProgramService;

    @RequestMapping(value="/findList", method=RequestMethod.GET)
    @Operation(value = "find", desc = "查询")
    @ApiOperation("查询")
    public List<GroupRoleProgram> findList(@ModelAttribute GroupRoleProgram groupRoleProgram){

        return groupRoleProgramService.findList(groupRoleProgram);
    }

    @RequestMapping(value="/findPagedList/{pageSize}/{curPage}", method=RequestMethod.GET)
    @Operation(value = "find", desc = "查询")
    @ApiOperation("分页查询")
    public PagedResult<GroupRoleProgram> findPagedList(@ModelAttribute GroupRoleProgram groupRoleProgram, @PathParam("")Page page){

        return groupRoleProgramService.findPagedList(groupRoleProgram, page);
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    @Operation(value = "update", desc = "更新")
    @ApiOperation("更新")
    public Map<String,Object> save(@RequestParam("")Long groupId, @RequestBody List<GroupRoleProgram> groupRolePrograms){

        return groupRoleProgramService.save(groupId, groupRolePrograms);
    }
}
