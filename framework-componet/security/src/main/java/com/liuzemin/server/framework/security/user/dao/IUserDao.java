package com.liuzemin.server.framework.security.user.dao;

import com.liuzemin.server.framework.datasource.page.Page;
import com.liuzemin.server.framework.datasource.page.PagedResult;
import com.liuzemin.server.framework.model.model.Enterprise;
import com.liuzemin.server.framework.model.model.User;
import com.liuzemin.server.framework.security.user.vo.UserTypeStatisticsVO;

import java.util.List;
import java.util.Map;

public interface IUserDao {

	/**
	 * 分页查询
	 *
	 * @param user
	 * @param page
	 * @return
	 */
	PagedResult<User> findPagedList(User user, Page page);

	/**
	 * 查询列表
	 *
	 * @param user
	 * @return
	 */
	List<User> findList(User user);

	/**
	 * 查询详细列表
	 *
	 * @param user
	 * @return
	 */
	List<User> findDetailList(User user);

	/**
	 * 新增
	 *
	 * @param user
	 * @return
	 */
	Integer insert(User user);

	/**
	 * 批量新增
	 *
	 * @param user
	 * @return
	 */
	Integer insertList(List<User> user);

	/**
	 * 更新
	 *
	 * @param user
	 * @return
	 */
	Integer update(User user);

	/**
	 * 根据手机号码更新
	 *
	 * @param user
	 * @return
	 */
	Integer updateByPhone(User user);

	/**
	 * 批量更新
	 *
	 * @param user
	 * @return
	 */
	Integer updateList(List<User> user);

	/**
	 * 删除
	 *
	 * @param id
	 * @return
	 */
	Integer delete(Long id);

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	Integer deleteList(List<Long> ids);

	/**
	 * 根据用户id获取用户信息
	 *
	 * @param id
	 * @return
	 */
	User obtainUser(Long id);

	/**
	 * 查询用户密码
	 *
	 * @param id
	 * @return
	 */
	String findPassword(Long id);

	int isExistTelphone(String phone);

	List<User> findNameByRoleName(String roleName);

	/**
	 * @return 注册用户的数量
	 */
	List<User>  queryRegisterUserNum();

	User getInfo(User user);

    Integer getNotAuthSupplierNum();

	List<Map<String,Object>> getAllTypeNum();

    List<UserTypeStatisticsVO> getNumberOfUserTypes(UserTypeStatisticsVO userTypeStatisticsVO);

    User queryById(Long id);

    List<Enterprise> findEnterprise(Enterprise enterprise);
}
