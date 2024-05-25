package com.liuzemin.server.framework.security.auth.controller;

import com.liuzemin.server.framework.model.annotation.RepeatSubmit;
import com.liuzemin.server.framework.model.context.RequestContext;
import com.liuzemin.server.framework.model.helper.RHelper;
import com.liuzemin.server.framework.model.model.R;
import com.liuzemin.server.framework.model.model.User;
import com.liuzemin.server.framework.security.auth.model.TianYanChaBaseInfo;
import com.liuzemin.server.framework.security.auth.model.TianYanChaResult;
import com.liuzemin.server.framework.security.auth.util.TianYanChaUtil;
import com.liuzemin.server.framework.security.user.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tianyancha")
public class TianYanChaController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/getCompanyInfo", method = RequestMethod.GET)
    @ApiOperation("天眼查获取企业基本信息")
    @RepeatSubmit(interval = 5000,message = "请间隔5秒以上再次点击获取相关信息！")
    public R<TianYanChaBaseInfo> getCompanyInfo(@RequestParam String name) {
        if (StringUtils.isEmpty(name.trim())) {
            return RHelper.getErrorR("请填写公司名称");
        }
        Long userId = RequestContext.getCurrent().getUser().getId();
        User user = userService.queryById(userId);
        Integer authTimes = user.getAuthTimes();
        if (null != authTimes && authTimes > 2) {
            return RHelper.getErrorR("您的信息获取次数已用完，请手动输入相关信息。");
        }
        TianYanChaResult tianYanChaResult = TianYanChaUtil.getCompanyInfo(name);
        String error_code = tianYanChaResult.getError_code();
        if ("0".equals(error_code)) {
            TianYanChaBaseInfo baseInfo = tianYanChaResult.getResult();
            String regCapital = baseInfo.getRegCapital();
            if (StringUtils.isEmpty(regCapital)) {
                regCapital = "0";
            } else {
                regCapital= regCapital.replaceAll("万人民币", "");
            }
            baseInfo.setRegCapital(regCapital);
            User update =new User();
            update.setId(userId);
            if(null ==authTimes){
                authTimes=0;
            }
            authTimes+= 1;
            update.setAuthTimes(authTimes);
            userService.updateById(update);
            return RHelper.getSuccessR(baseInfo);
        }
        return RHelper.getResultR(error_code, tianYanChaResult.getReason());
    }
}
