package com.liuzemin.server.framework.security.auth.controller;

import com.liuzemin.server.framework.model.constant.UserTypeEnum;
import com.liuzemin.server.framework.model.context.RequestContext;
import com.liuzemin.server.framework.model.helper.RHelper;
import com.liuzemin.server.framework.model.model.R;
import com.liuzemin.server.framework.model.model.User;
import com.liuzemin.server.framework.security.auth.service.ISecurityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(tags = "用户登录接口")
@RestController
public class LoginController {

    public static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private ISecurityService securityService;

    /**
     * @param user 登录用户
     * @param imageCode 图形验证码
     * @param type 图形验证码类型
     * @param loginMode 登录方式（1，账号密码登录，2短信验证码登录）
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation("web端登录 手机号或者邮箱放入username中")
    public Map<String, Object> login(@RequestBody User user, @RequestParam String imageCode, @RequestParam String type, @RequestParam String loginMode) {
        return securityService.login(user.getUsername(), user.getPassword(), imageCode, type,loginMode);
    }

    @RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
    @ApiOperation("web后台管理系统登录 手机号或者邮箱放入username中")
    public Map<String, Object> adminLogin(@RequestBody User user, @RequestParam String imageCode, @RequestParam String type) {
        return securityService.adminLogin(user.getUsername(), user.getPassword(), imageCode, type);
    }

    /**
     * @param user 登录用户
     * @param imageCode 图形验证码
     * @param type 图形验证码类型
     * @param loginMode 登录方式（1，账号密码登录，2短信验证码登录）
     * @return
     */
    @RequestMapping(value = "/app/login", method = RequestMethod.POST)
    @ApiOperation("app端登录 手机号或者邮箱放入username中")
    public Map<String, Object> appLogin(@RequestBody User user, @RequestParam String imageCode, @RequestParam String type, @RequestParam(defaultValue = "1") String loginMode) {
        return securityService.appLogin(user.getUsername(), user.getPassword(), imageCode, type,loginMode);
    }

    @RequestMapping(value = "/user/information", method = RequestMethod.GET)
    @ApiOperation("获取最新用户权限信息")
    public Map<String, Object> getUserInformation() {

        return securityService.getUserInformation();
    }

    /**
     * @param user 注册用户信息
     * @param code 短信验证码
     * @param imageCode 图形验证码
     * @param type 图形验证码类型
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation("注册用户")
    public Map<String, Object> register(@RequestBody User user, @RequestParam String code, @RequestParam String imageCode, @RequestParam String type) {

        return securityService.registerAndLogin(user, code, imageCode, type);
    }

   // @RequestMapping(value = "/social_demander/register", method = RequestMethod.POST)
 //   @ApiOperation("社会需求方注册")
    public R<String> socialDemanderRegister(@RequestBody User user, @RequestParam String code, @RequestParam String imageCode, @RequestParam String type) {
        return RHelper.getSuccessR("该功能暂未开放，敬请期待!");
        //	return securityService.socialDemanderRegister(user,code,imageCode,type);
    }

  //  @RequestMapping(value = "/demander/register", method = RequestMethod.POST)
  //  @ApiOperation("战略需求方注册")
    public R<String> demanderRegister(@RequestBody User user, @RequestParam String code, @RequestParam String imageCode, @RequestParam String type) {
        Integer userType = UserTypeEnum.notAuthStrategicDemander.getUserType();
      //  return securityService.registerAndLogin(user, code, imageCode, userType,type);
        return RHelper.getSuccessR("该功能暂未开放，敬请期待!");
    }

  //  @RequestMapping(value = "/regionalDistributors/register", method = RequestMethod.POST)
  //  @ApiOperation(" 区域经销商注册")
    public R<String> regionalDistributorsRegister(@RequestBody User user, @RequestParam String code, @RequestParam String imageCode, @RequestParam String type) {
        Integer userType = UserTypeEnum.notAuthRegionalDistributors.getUserType();
      //  return securityService.registerAndLogin(user, code, imageCode, userType,type);
        return RHelper.getSuccessR("该功能暂未开放，敬请期待!");
    }

    @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
    @ApiOperation("忘记密码")
    public Map<String, Object> forgetPassword(@RequestBody User user, @RequestParam String code, @RequestParam String imageCode, @RequestParam String type) {
        return securityService.forgetPassword(user, code, imageCode, type);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ApiOperation("退出登录")
    public Map<String, Object> logout() {

        return securityService.logout(RequestContext.getCurrent().getToken());
    }

    /**
     * @param type 1:请求短信验证码，2：注册、修改、忘记密码、注销账号等其它请求图形验证码
     */
    @RequestMapping(value = "/getImageCode", method = RequestMethod.GET)
    @ApiOperation("生成图形验证码")
    public void getImageCode(@RequestParam String type) {
        securityService.getImageCodeV2(type);
    }


    /**
     * @param phone     手机号
     * @param imageCode 图形验证码
     * @param type      图形验证码类型
     * @param flag      1：注册/修改接口  2：忘记密码接口 注销账号
     * @return
     */
    @RequestMapping(value = "/validImageCode", method = RequestMethod.GET)
    @ApiOperation("验证图形验证码")
    public Map<String, Object> validImageCodeAndPhone(@RequestParam String phone, @RequestParam String imageCode, @RequestParam String flag, @RequestParam String type) {
        return securityService.validImageCodeAndPhone(phone, imageCode, flag, type);
    }

}
