package com.liuzemin.server.framework.security.program.controller;

import com.liuzemin.server.framework.datasource.page.Page;
import com.liuzemin.server.framework.datasource.page.PagedResult;
import com.liuzemin.server.framework.model.annotation.Operation;
import com.liuzemin.server.framework.model.annotation.Resource;
import com.liuzemin.server.framework.model.helper.RHelper;
import com.liuzemin.server.framework.model.model.ProgramItem;
import com.liuzemin.server.framework.model.model.R;
import com.liuzemin.server.framework.security.program.service.IProgramItemService;
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
@RequestMapping("/programItem/v1")
@Resource(value="program", desc="数据范围")
@Api(tags = "数据范围详情")
public class ProgramItemController {

    public static final Logger log = LoggerFactory.getLogger(ProgramItemController.class);

    @Autowired
    private IProgramItemService programItemService;

    @RequestMapping(value="/findList", method=RequestMethod.GET)
    @Operation(value="find", desc="查询")
    @ApiOperation("查询")
    public List<ProgramItem> findList(@ModelAttribute ProgramItem programItem){

        return programItemService.findList(programItem);
    }

    @RequestMapping(value="/findListByService", method=RequestMethod.GET)
    @ApiOperation("查询")
    public List<ProgramItem> findListByService(@ModelAttribute ProgramItem programItem){

        return programItemService.findList(programItem);
    }

    @RequestMapping(value="/findPagedList/{pageSize}/{curPage}", method=RequestMethod.GET)
    @Operation(value="find", desc="查询")
    @ApiOperation("分页查询")
    public PagedResult<ProgramItem> findPagedList(@ModelAttribute ProgramItem programItem, @PathParam("")Page page){

        return programItemService.findPagedList(programItem, page);
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    @Operation(value="update", desc="更新")
    @ApiOperation("更新")
    public Map<String,Object> save(@RequestParam("programId")Long programId, @RequestBody List<ProgramItem> programItems){

        return programItemService.save(programId, programItems);
    }


    @RequestMapping(value = "/findFirstLevelList", method = RequestMethod.GET)
    public R<Map<String,String> > findProgramMap(@RequestBody ProgramItem programItem) {
        return RHelper.getSuccessR(programItemService.findProgramMap(programItem));
    }

    @RequestMapping(value="/findProgramItemList", method=RequestMethod.GET)
    public List<ProgramItem> findProgramItemList(@RequestBody ProgramItem programItem){
        return programItemService.findList(programItem);
    }

}
