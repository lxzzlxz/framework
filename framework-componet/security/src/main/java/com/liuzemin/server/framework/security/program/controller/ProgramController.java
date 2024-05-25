package com.liuzemin.server.framework.security.program.controller;

import com.liuzemin.server.framework.datasource.page.Page;
import com.liuzemin.server.framework.datasource.page.PagedResult;
import com.liuzemin.server.framework.model.annotation.Operation;
import com.liuzemin.server.framework.model.annotation.Resource;
import com.liuzemin.server.framework.model.helper.RHelper;
import com.liuzemin.server.framework.model.helper.ResultMapHelper;
import com.liuzemin.server.framework.model.model.R;
import com.liuzemin.server.framework.model.service.IProgramCheckService;
import com.liuzemin.server.framework.security.program.model.Program;
import com.liuzemin.server.framework.security.program.service.IProgramService;
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
@RequestMapping("/program/v1")
@Resource(value = "program", desc = "数据范围")
@Api(tags = "数据范围管理")
public class ProgramController {

    public static final Logger log = LoggerFactory.getLogger(ProgramController.class);

    @Autowired
    private IProgramService programService;

    @Autowired
    private IProgramCheckService programCheckService;

    @RequestMapping(value = "/findList", method = RequestMethod.GET)
    @Operation(value = "find", desc = "查询")
    @ApiOperation("查询")
    public List<Program> findList(@ModelAttribute Program program) {

        return programService.findList(program);
    }

    @RequestMapping(value = "/findPagedList/{pageSize}/{curPage}", method = RequestMethod.GET)
    @Operation(value = "find", desc = "查询")
    @ApiOperation("分页查询")
    public PagedResult<Program> findList(@ModelAttribute Program program, @PathParam("") Page page) {

        return programService.findPagedList(program, page);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @Operation(value = "add", desc = "新增")
    @ApiOperation("新增")
    public Map<String, Object> insert(@RequestBody Program program) {

        return programService.insert(program);
    }

    @RequestMapping(value = "/insertByService", method = RequestMethod.POST)
    @ApiOperation("新增")
    public Map<String, Object> insertByService(@RequestBody Program program) {

        return programService.insert(program);
    }

    @RequestMapping(value = "/insertList", method = RequestMethod.POST)
    @Operation(value = "add", desc = "新增")
    @ApiOperation("批量新增")
    public Map<String, Object> insertList(@RequestBody List<Program> programs) {

        return programService.insertList(programs);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @Operation(value = "update", desc = "更新")
    @ApiOperation("批量更新")
    public Map<String, Object> update(@RequestBody Program program) {

        return programService.update(program);
    }

    @RequestMapping(value = "/updateList", method = RequestMethod.POST)
    @Operation(value = "update", desc = "更新")
    @ApiOperation("批量更新")
    public Map<String, Object> updateList(@RequestBody List<Program> programs) {

        return programService.updateList(programs);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @Operation(value = "delete", desc = "删除")
    @ApiOperation("删除")
    public Map<String, Object> update(@RequestParam("id") Long id) {

        return programService.delete(id);
    }

    @RequestMapping(value = "/deleteList", method = RequestMethod.POST)
    @Operation(value = "delete", desc = "删除")
    @ApiOperation("批量删除")
    public Map<String, Object> deleteList(@RequestBody List<Long> programIds) {

        return programService.deleteList(programIds);
    }

    @RequestMapping(value = "/findUserProgramList", method = RequestMethod.GET)
    @ApiOperation("查询用户数据权限")
    public Map<String, Object> findUserProgramList(@RequestParam String dimensionCode, @RequestParam String rId, @RequestParam String prId) {

        return ResultMapHelper.getSuccessMap(programCheckService.findProgramItems(dimensionCode, rId, prId));
    }

    @RequestMapping(value = "/findProgramItemsByUserId", method = RequestMethod.GET)
    @ApiOperation("查询用户数据权限")
    public Map<String, Object> findUserProgramList(@RequestParam String dimensionCode, @RequestParam String rId, @RequestParam String prId, @RequestParam Long userId) {

        return ResultMapHelper.getSuccessMap(programCheckService.findProgramItemsByUserId(dimensionCode, rId, prId, userId));
    }

    @RequestMapping(value = "/findFirstLevelUnit", method = RequestMethod.GET)
    @ApiOperation("查询用户一级单位名称")
    public Map<String, Object> findFirstLevelUnit(@RequestParam(required = false) String dimensionValue) {
        return ResultMapHelper.getSuccessMap(programService.findFirstLevelUnit(dimensionValue));
    }

    @RequestMapping(value = "/findFirstLevel", method = RequestMethod.GET)
    @ApiOperation("筛选条件一级单位名称")
    public R<List<Program>> findFirstLevel() {
        return RHelper.getSuccessR(programService.findFirstLevel());
    }

    @RequestMapping(value = "/findByIdFirstLevel/{id}", method = RequestMethod.GET)
    public R<Program> findByIdFirstLevel(@PathVariable Long id) {
        return RHelper.getSuccessR(programService.findByIdFirstLevel(id));
    }


    @RequestMapping(value = "/findFirstLevelList", method = RequestMethod.GET)
    public R<List<Program>> findFirstLevelList(@RequestBody Program program) {
        return RHelper.getSuccessR(programService.findFirstLevelList(program));
    }

}
