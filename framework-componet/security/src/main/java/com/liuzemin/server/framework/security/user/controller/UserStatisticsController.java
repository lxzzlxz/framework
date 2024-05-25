package com.liuzemin.server.framework.security.user.controller;

import com.liuzemin.server.framework.model.annotation.Operation;
import com.liuzemin.server.framework.model.annotation.Resource;
import com.liuzemin.server.framework.model.helper.RHelper;
import com.liuzemin.server.framework.model.model.R;
import com.liuzemin.server.framework.model.utils.DateUtils;
import com.liuzemin.server.framework.security.user.service.IUserStatisticsService;
import com.liuzemin.server.framework.security.user.vo.UserTypeStatisticsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("userStatistics/v1")
@Resource(value = "userStatistics", desc = "用户统计")
@Api(tags = "用户统计")
public class UserStatisticsController {

    @Autowired
    private IUserStatisticsService userStatisticsService;

    @RequestMapping(value = "/notAuthSupplierNum", method = RequestMethod.GET)
    @ApiOperation("后台查询未认证供应商数量")
    public R<Integer> getNotAuthSupplierNum() {

        return userStatisticsService.getNotAuthSupplierNum();
    }

    @RequestMapping(value = "/allTypeUserNum", method = RequestMethod.GET)
    @ApiOperation("后台查询各类型用户数量(饼图)")
    public R<Map<String, String>> getAllTypeNum() {

        return RHelper.getSuccessR(userStatisticsService.getAllTypeNum());
    }
    @RequestMapping(value = "/allUserData", method = RequestMethod.GET)
    @ApiOperation("后台用户统计(表单)")
    @Operation(value = "find", desc = "大屏统计")
    public R<List<UserTypeStatisticsVO>> getAllUserData(UserTypeStatisticsVO userTypeStatisticsVO) {

        return RHelper.getSuccessR(userStatisticsService.getAllUserData(userTypeStatisticsVO));
    }

    @RequestMapping(value = "/distributionOfNewUsers", method = RequestMethod.GET)
    @ApiOperation("后台用户数量增长趋势图（折线图）")
    @Operation(value = "find", desc = "大屏统计")
    public R<List<UserTypeStatisticsVO>> getDistributionOfNewUsers(@RequestParam("dateType") String dateType) {
        UserTypeStatisticsVO userTypeStatisticsVO = new UserTypeStatisticsVO();
        userTypeStatisticsVO.setDateType(dateType);
        if ("1".equals(dateType)) {
            String nowMonth = DateUtils.getNowMonth();
            String lastMonth = DateUtils.getLastSixMonth();
            userTypeStatisticsVO.setStartDate(lastMonth);
            userTypeStatisticsVO.setEndDate(nowMonth);
            userTypeStatisticsVO.setDateFormatStr("'%Y-%m'");
        } else if ("2".equals(dateType)) {
            Integer nowYear = Calendar.getInstance().get(Calendar.YEAR);
            Integer lastYear = nowYear - 6;
            userTypeStatisticsVO.setStartDate(lastYear.toString());
            userTypeStatisticsVO.setEndDate(nowYear.toString());
            userTypeStatisticsVO.setDateFormatStr("'%Y'");
        } else {
            return RHelper.getSuccessR(new ArrayList<>());
        }
        List<UserTypeStatisticsVO> resultList = userStatisticsService.getNumberOfUserTypes(userTypeStatisticsVO);

        return RHelper.getSuccessR(resultList);
    }

}
