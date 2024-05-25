package com.liuzemin.server.framework.security.permission.service.impl;

import com.liuzemin.server.framework.datasource.page.Page;
import com.liuzemin.server.framework.datasource.page.PagedResult;
import com.liuzemin.server.framework.model.helper.ResultMapHelper;
import com.liuzemin.server.framework.model.model.BaseService;
import com.liuzemin.server.framework.security.permission.dao.IBaseServiceDao;
import com.liuzemin.server.framework.security.permission.service.IBaseServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Service
public class BaseServiceService implements IBaseServiceService {

	public static final Logger log = LoggerFactory.getLogger(BaseServiceService.class);

	@Autowired
	private IBaseServiceDao baseServiceDao;

	@Override
	public List<BaseService> findList(BaseService baseService) {

		return baseServiceDao.findList(baseService);
	}

	@Override
	public Object findPagedList(BaseService baseService, Object page) {
		if (page instanceof Page) {
			Page p = (Page) page;
			return baseServiceDao.findPagedList(baseService, p);
		}
		return new PagedResult<BaseService>();
	}

	@Override
	public Map<String, Object> insertList(List<BaseService> baseServices) {
		if (CollectionUtils.isEmpty(baseServices)) {
			return ResultMapHelper.getParameterErrorMap();
		}
		baseServiceDao.insertList(baseServices);
		return ResultMapHelper.getSuccessMap();
	}

	@Override
	public Map<String, Object> deleteList(List<Long> ids) {
		if (CollectionUtils.isEmpty(ids)) {
			return ResultMapHelper.getParameterErrorMap();
		}
		baseServiceDao.deleteList(ids);
		return ResultMapHelper.getSuccessMap();
	}
}
