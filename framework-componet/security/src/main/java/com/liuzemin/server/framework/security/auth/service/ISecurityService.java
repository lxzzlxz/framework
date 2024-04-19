package com.liuzemin.server.framework.security.auth.service;

import com.liuzemin.server.framework.model.model.R;
import com.liuzemin.server.framework.model.model.User;
import com.liuzemin.server.framework.model.model.R;
import com.liuzemin.server.framework.model.model.User;

import java.util.Map;

/**
 * 登录登出服务 weihao 2018-08-17
 */
public interface ISecurityService {

	/**
	 * 管理后台登录
	 * @param username
	 * @param password
	 * @param imageCode
	 * @param type
	 * @return
	 */
	public Map<String, Object> adminLogin(String username, String password, String imageCode, String type);

	/**
	 * 登录
	 *
	 * @param username
	 * @param password
	 * @param type
	 * @return
	 */
	Map<String, Object> login(String username, String password, String imageCode, String type, String loginModel);

	/**
	 * app端登陆
	 *
	 * @param username
	 * @param password
	 * @param imageCode
	 * @param type
	 * @return
	 */
	Map<String, Object> appLogin(String username, String password, String imageCode, String type, String loginModel);

	/**
	 * 登出
	 *
	 * @param sessionId
	 * @return
	 */
	Map<String, Object> logout(String sessionId);

	/**
	 * 注册
	 *
	 * @param user
	 * @param imgCode
	 * @param type
	 * @return
	 */
	Map<String, Object> register(User user, String code, String imgCode, String type);

	/**
	 * 忘记密码
	 *
	 * @param user
	 * @param code
	 * @param imgCode
	 * @param type
	 * @return
	 */
	Map<String, Object> forgetPassword(User user, String code, String imgCode, String type);

	/**
	 * 生成验证码
	 */
	void getImageCode(String type);
	/**
	 * 生成数学验证码
	 */
	void getImageCodeV2(String type);


	Map<String, Object> getUserInformation();

	Map<String, Object> validImageCodeAndPhone(String phone, String imageCode, String flag, String type);


    R<String> socialDemanderRegister(User user, String code, String imageCode, String type);

	/**
	 * @param user
	 * @param code
	 * @param imageCode
	 * @param  //userType 用户类型（战略需求方，区域经销商，社会需求方）
	 * @return
	 */
	Map<String, Object> registerAndLogin(User user, String code, String imageCode, String type);
}
