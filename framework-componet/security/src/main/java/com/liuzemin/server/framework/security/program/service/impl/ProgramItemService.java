package com.liuzemin.server.framework.security.program.service.impl;

import com.liuzemin.server.framework.datasource.page.Page;
import com.liuzemin.server.framework.datasource.page.PagedResult;
import com.liuzemin.server.framework.model.constant.ResponseCode;
import com.liuzemin.server.framework.model.helper.ResultMapHelper;
import com.liuzemin.server.framework.model.model.ProgramItem;
import com.liuzemin.server.framework.security.program.dao.IProgramDao;
import com.liuzemin.server.framework.security.program.dao.IProgramItemDao;
import com.liuzemin.server.framework.security.program.model.Program;
import com.liuzemin.server.framework.security.program.service.IProgramItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProgramItemService implements IProgramItemService {

    public static final Logger log = LoggerFactory.getLogger(ProgramItemService.class);

    @Autowired
    private IProgramItemDao programItemDao;
    @Autowired
    private IProgramDao programDao;

    @Override
    public List<ProgramItem> findList(ProgramItem programItem) {

        return programItemDao.findList(programItem);
    }

    @Override
    public PagedResult<ProgramItem> findPagedList(ProgramItem programItem, Page page) {

        return programItemDao.findPagedList(programItem, page);
    }

    @Override
    public Map<String, Object> insertList(List<ProgramItem> programItems) {
        if (CollectionUtils.isEmpty(programItems)) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        for (ProgramItem programItem : programItems) {
            programItem.setCreateInfo();
        }
        programItemDao.insertList(programItems);
        return ResultMapHelper.getSuccessMap();
    }

    @Override
    public Map<String, Object> deleteList(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        programItemDao.deleteList(ids);
        return ResultMapHelper.getSuccessMap();
    }

    @Override
    public Map<String, Object> save(Long programId, List<ProgramItem> programItemList) {
        //1.校验
        if (null == programId) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        //2,查询原始数据
        ProgramItem queryParam = new ProgramItem();
        queryParam.setProgramId(programId);
        List<ProgramItem> originList = programItemDao.findList(queryParam);

        //3.
        List<ProgramItem> toInsert = new ArrayList<>();
        List<Long> toDelete = new ArrayList<>();

        List<Long> originIds = new ArrayList<>();
        List<String> originKeys = new ArrayList<>();
        List<String> newKeys = new ArrayList<>();

        //如果原始数据是空，新增新数据，返回；
        if (CollectionUtils.isEmpty(originList)) {
            programItemDao.insertList(programItemList);
            return ResultMapHelper.getSuccessMap();
        } else {
            for (ProgramItem programItem : originList) {
                originKeys.add(programItem.getDimensionCode() + "$" + programItem.getDimensionValue());
                originIds.add(programItem.getId());
            }
        }

        //如果新数据是空，删除原始数据，返回；
        if (CollectionUtils.isEmpty(programItemList)) {
            programItemDao.deleteList(originIds);
            return ResultMapHelper.getSuccessMap();
        } else {
            for (ProgramItem programItem : programItemList) {
                String key = programItem.getDimensionCode() + "$" + programItem.getDimensionValue();
                newKeys.add(key);
                if (originKeys.contains(key)) {
                    continue;
                }
                toInsert.add(programItem);
            }
        }

        for (ProgramItem programItem : originList) {
            String key = programItem.getDimensionCode() + "$" + programItem.getDimensionValue();
            if (newKeys.contains(key)) {
                continue;
            }
            toDelete.add(programItem.getId());
        }

        //保存数据
        if (!CollectionUtils.isEmpty(toInsert)) {
            programItemDao.insertList(toInsert);
        }

        if (!CollectionUtils.isEmpty(toDelete)) {
            programItemDao.deleteList(toDelete);
        }

        return ResultMapHelper.getSuccessMap();
    }

    @Override
    public Map<String, String> findProgramMap(ProgramItem programItem) {
        if (null == programItem) {
            return null;
        }
        List<ProgramItem> programItemList = programItemDao.findList(programItem);
        Map<String, String> firstLevelName = new HashMap<>();
        Map<Long, Program> programMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(programItemList)) {
            Set<Long> programIdSet = programItemList.stream().map(ProgramItem::getProgramId).collect(Collectors.toSet());
            Program program = new Program();
            program.setProgramIds(new ArrayList<>(programIdSet));
            program.setType(1);
            List<Program> programList = programDao.findFirstLevelList(program);
            if (!CollectionUtils.isEmpty(programList)) {
                programMap = programList.stream().collect(Collectors.toMap(Program::getId, p -> p));
            }
            for (ProgramItem item : programItemList) {
                Long programId = item.getProgramId();
                String dimensionValue = item.getDimensionValue();
                Program program1 = programMap.get(programId);
                if (null != program1) {
                    firstLevelName.put(dimensionValue, program1.getProgramName());
                }
            }
        }
        return firstLevelName;
    }
}
