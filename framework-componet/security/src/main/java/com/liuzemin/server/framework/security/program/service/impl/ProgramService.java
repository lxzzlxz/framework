package com.liuzemin.server.framework.security.program.service.impl;

import com.liuzemin.server.framework.datasource.page.Page;
import com.liuzemin.server.framework.datasource.page.PagedResult;
import com.liuzemin.server.framework.model.constant.ResponseCode;
import com.liuzemin.server.framework.model.helper.ResultMapHelper;
import com.liuzemin.server.framework.model.model.ProgramItem;
import com.liuzemin.server.framework.security.program.dao.IProgramDao;
import com.liuzemin.server.framework.security.program.model.Program;
import com.liuzemin.server.framework.security.program.service.IProgramItemService;
import com.liuzemin.server.framework.security.program.service.IProgramService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProgramService implements IProgramService {

    public static final Logger log = LoggerFactory.getLogger(ProgramService.class);

    @Autowired
    private IProgramDao programDao;

    @Autowired
    private IProgramItemService programItemService;

    @Override
    public List<Program> findList(Program program) {
        List<Program> resultList = programDao.findList(program);
        if (CollectionUtils.isEmpty(resultList)) {
            return resultList;
        }
        List<Long> programIds = new ArrayList<>();
        for (Program p : resultList) {
            programIds.add(p.getId());
        }
        ProgramItem programItem = new ProgramItem();
        programItem.setProgramIds(programIds);
        List<ProgramItem> items = programItemService.findList(programItem);
        Map<Long, List<ProgramItem>> map = new HashMap<>(16);
        for (ProgramItem item : items) {
            if ("@All".equals(item.getDimensionValue())) {
                item.setAll(true);
            }
            if (map.containsKey(item.getProgramId())) {
                map.get(item.getProgramId()).add(item);
            } else {
                List<ProgramItem> list = new ArrayList<>();
                list.add(item);
                map.put(item.getProgramId(), list);
            }
        }
        for (Program p : resultList) {
            p.setProgramItems(map.get(p.getId()));
        }
        return resultList;
    }

    @Override
    public PagedResult<Program> findPagedList(Program program, Page page) {

        return programDao.findPagedList(program, page);
    }


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Map<String, Object> insert(Program program) {
        if (null == program) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        program.setCreateInfo();
        programDao.insert(program);
        Long programId = program.getId();
        if (!CollectionUtils.isEmpty(program.getProgramItems())) {
            for (ProgramItem programItem : program.getProgramItems()) {
                programItem.setProgramId(programId);
                if (null != programItem.getAll() && programItem.getAll()) {
                    programItem.setDimensionValue("@All");
                }
            }
        }
        if (!CollectionUtils.isEmpty(program.getProgramItems())) {
            programItemService.save(programId, program.getProgramItems());
        }
        return ResultMapHelper.getSuccessMap(programId);
    }

    @Override
    public Map<String, Object> insertList(List<Program> programs) {
        if (CollectionUtils.isEmpty(programs)) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        for (Program program : programs) {
            program.setCreateInfo();
        }
        programDao.insertList(programs);
        return ResultMapHelper.getSuccessMap();
    }


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Map<String, Object> update(Program program) {
        if (null == program) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        program.setUpdateInfo();
        programDao.update(program);
        if (!CollectionUtils.isEmpty(program.getProgramItems())) {
            for (ProgramItem programItem : program.getProgramItems()) {
                programItem.setProgramId(program.getId());
                if (null != programItem.getAll() && programItem.getAll()) {
                    programItem.setDimensionValue("@All");
                }
            }
        }
        programItemService.save(program.getId(), program.getProgramItems());
        return ResultMapHelper.getSuccessMap();
    }

    @Override
    public Map<String, Object> updateList(List<Program> programs) {
        if (CollectionUtils.isEmpty(programs)) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        for (Program program : programs) {
            program.setUpdateInfo();
        }
        programDao.updateList(programs);
        return ResultMapHelper.getSuccessMap();
    }

    @Override
    public Map<String, Object> delete(Long id) {
        if (null == id) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        programDao.delete(id);
        return ResultMapHelper.getSuccessMap();
    }

    @Override
    public Map<String, Object> deleteList(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        programDao.deleteList(ids);
        return ResultMapHelper.getSuccessMap();
    }

    @Override
    public Map<String, String> findFirstLevelUnit(String dimensionValue) {
        List<Map<String, String>> resultList = programDao.findFirstLevelUnit(dimensionValue);
        Map<String, String> result = new HashMap<>();
        resultList.forEach(vo -> {
            result.put(vo.get("dimension_value"), vo.get("program_name"));
        });
        return result;
    }

    @Override
    public List<Program> findFirstLevel() {
        return programDao.findFirstLevel();
    }

    @Override
    public Program findByIdFirstLevel(Long id) {

        return programDao.findByIdFirstLevel(id);
    }

    @Override
    public List<Program> findFirstLevelList(Program program) {
        return programDao.findFirstLevelList(program);
    }
}
