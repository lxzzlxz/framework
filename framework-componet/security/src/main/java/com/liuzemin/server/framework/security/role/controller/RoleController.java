package com.liuzemin.server.framework.security.role.controller;

import com.liuzemin.server.framework.datasource.page.Page;
import com.liuzemin.server.framework.datasource.page.PagedResult;
import com.liuzemin.server.framework.model.annotation.Operation;
import com.liuzemin.server.framework.model.annotation.Resource;
import com.liuzemin.server.framework.security.role.model.Role;
import com.liuzemin.server.framework.security.role.service.IRoleService;
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
@RequestMapping("/role/v1")
@Resource(value="role", desc="角色")
@Api(tags = "角色管理")
public class RoleController {

    public static final Logger log = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private IRoleService roleService;

    @RequestMapping(value="/findList", method = RequestMethod.GET)
    @Operation(value="find", desc="查询")
    @ApiOperation("查询")
    public List<Role> findList(@ModelAttribute Role role){

        return roleService.findList(role);
    }

    @RequestMapping(value="/findPagedList/{pageSize}/{curPage}", method = RequestMethod.GET)
    @Operation(value="find", desc="查询")
    @ApiOperation("分页查询")
    public PagedResult<Role> findPagedList(@ModelAttribute Role role, @PathParam("") Page page){

        return roleService.findPagedList(role, page);
    }

    @RequestMapping(value="/insert", method = RequestMethod.POST)
    @Operation(value="add", desc="新增")
    @ApiOperation("新增")
    public Map<String, Object> insert(@RequestBody Role role){

        return roleService.insert(role);
    }

    @RequestMapping(value="/insertList", method = RequestMethod.POST)
    @Operation(value="add", desc="新增")
    @ApiOperation("批量新增")
    public Map<String, Object> insert(@RequestBody List<Role> roles){

        return roleService.insertList(roles);
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    @Operation(value="update", desc="更新")
    @ApiOperation("更新")
    public Map<String, Object> update(@RequestBody Role role){

        return roleService.update(role);
    }

    @RequestMapping(value="/updateList", method = RequestMethod.POST)
    @Operation(value="update", desc="更新")
    @ApiOperation("批量更新")
    public Map<String, Object> updateList(@RequestBody List<Role> roles){

        return roleService.updateList(roles);
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    @Operation(value="delete", desc="删除")
    @ApiOperation("删除")
    public Map<String, Object> delete(@PathVariable("id") Long id){

        return roleService.delete(id);
    }

    @RequestMapping(value="/deleteList", method = RequestMethod.POST)
    @Operation(value="delete", desc="删除")
    @ApiOperation("批量删除")
    public Map<String, Object> delete(@RequestBody List<Long> ids){

        return roleService.deleteList(ids);
    }
}
