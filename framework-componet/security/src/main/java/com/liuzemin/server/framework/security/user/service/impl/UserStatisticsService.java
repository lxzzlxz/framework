package com.liuzemin.server.framework.security.user.service.impl;

import com.liuzemin.server.framework.model.constant.UserTypeEnum;
import com.liuzemin.server.framework.model.helper.RHelper;
import com.liuzemin.server.framework.model.model.R;
import com.liuzemin.server.framework.model.utils.DateUtils;
import com.liuzemin.server.framework.security.user.dao.IUserDao;
import com.liuzemin.server.framework.security.user.dao.IUserStatisticsDao;
import com.liuzemin.server.framework.security.user.service.IUserStatisticsService;
import com.liuzemin.server.framework.security.user.vo.UserTypeStatisticsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserStatisticsService implements IUserStatisticsService {


    @Autowired
    private IUserDao userDao;

    @Autowired
    private IUserStatisticsDao userStatisticsDao;


    @Override
    public R<Integer> getNotAuthSupplierNum() {
        Integer num = userDao.getNotAuthSupplierNum();
        return RHelper.getSuccessR(num);
    }

    @Override
    public Map<String, String> getAllTypeNum() {
        List<Map<String, Object>> resultList = userDao.getAllTypeNum();

        Map<String, String> map = new HashMap<>(16);
        for (Map<String, Object> result : resultList) {
            String type = result.get("type").toString();
            String name = UserTypeEnum.getUserName(Integer.parseInt(type));
            map.put(name, result.get("num").toString());
        }
        return map;
    }


    @Override
    public List<UserTypeStatisticsVO> getNumberOfUserTypes(UserTypeStatisticsVO userTypeStatisticsVO) {
        // x轴数据
        List<String> dateList = DateUtils.getEveryDateUnit(userTypeStatisticsVO.getStartDate(), userTypeStatisticsVO.getEndDate(),
                userTypeStatisticsVO.getDateType());
        List<UserTypeStatisticsVO> list = userDao.getNumberOfUserTypes(userTypeStatisticsVO);
        int userCount = userStatisticsDao.statisticsUserCount(3);
        List<UserTypeStatisticsVO> results = new ArrayList<>();
        for (String dateName : dateList) {
            UserTypeStatisticsVO newObj = new UserTypeStatisticsVO();
            newObj.setDateTime(dateName);
            for (UserTypeStatisticsVO vo : list) {
                if (dateName.equals(vo.getDateTime())) {
                    Integer userType = vo.getUserType();
                    switch (userType) {
                        case 0:
                            newObj.setNotAuthSupplier(vo.getNumber().toString());
                            break;
                        case 1:
                            newObj.setSupplier(vo.getNumber().toString());
                            break;
                        case 2:
                            newObj.setStrategicDemanderNum(vo.getNumber().toString());
                            break;
                        case 3:
                            newObj.setNotAuthsocialDemanderNum(vo.getNumber().toString());
                            break;
                        case 4:
                            newObj.setSubmitSocialDemanderNum(vo.getNumber().toString());
                            break;
                        case 5:
                            newObj.setNotSubmitStrategicDemander(vo.getNumber().toString());
                            break;
                        case 7:
                            newObj.setRegionalDistributor(vo.getNumber().toString());
                            break;
                        case 8:
                            newObj.setSubmitedSecondaryDealer(String.valueOf(userCount));
                            break;
                        case 9:
                            newObj.setStockCompany(vo.getNumber().toString());
                            break;
                        case 10:
                            newObj.setPlatformAdmin(vo.getNumber().toString());
                            break;
                        default:
                            break;
                    }
                }
            }
            results.add(newObj);
        }

        return results;
    }

    @Override
    public List<UserTypeStatisticsVO> getAllUserData(UserTypeStatisticsVO userTypeStatisticsVO) {

        return null;
    }
}
