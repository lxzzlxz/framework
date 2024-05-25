package com.liuzemin.server.framework.security.user.service;

import com.liuzemin.server.framework.model.model.R;
import com.liuzemin.server.framework.security.user.vo.UserTypeStatisticsVO;

import java.util.List;
import java.util.Map;

public interface IUserStatisticsService {


	R<Integer> getNotAuthSupplierNum();

	Map<String, String> getAllTypeNum();

	List<UserTypeStatisticsVO> getNumberOfUserTypes(UserTypeStatisticsVO userTypeStatisticsVO);

	List<UserTypeStatisticsVO> getAllUserData(UserTypeStatisticsVO userTypeStatisticsVO);
}

