package com.liuzemin.server.framework.security.user.service;

import com.liuzemin.server.framework.datasource.page.PagedResult;
import com.liuzemin.server.framework.model.model.R;
import com.liuzemin.server.framework.model.model.User;
import com.liuzemin.server.framework.datasource.page.Page;

import java.util.List;
import java.util.Map;

public interface IUserService {

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
     * @param id
     * @return
     */
    User queryById(Long id);

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
    Map<String, Object> insert(User user);

    /**
     * 批量新增
     *
     * @param user
     * @return
     */
    Map<String, Object> insertList(List<User> user);

    /**
     * 用户存在更新，用户不存在删除
     *
     * @param user
     * @return
     */
    Map<String, Object> updateAndInsert(User user);

    /**
     * 更新
     *
     * @param user
     * @return
     */
    Map<String, Object> update(User user);

    /**
     * 根据电话号码更新用户信息
     *
     * @param user
     * @return
     */
    Map<String, Object> updateByPhone(User user);

    /**
     * 批量更新
     *
     * @param user
     * @return
     */
    Map<String, Object> updateList(List<User> user);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    Map<String, Object> delete(Long id);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    Map<String, Object> deleteList(List<Long> ids);

    /**
     * 验证手机号并修改用户信息表单
     *
     * @param user
     * @param otherPhone
     * @param code
     * @param imageCode
     * @param type
     * @return
     */
    Map<String, Object> updateUserAndValidate(User user, String otherPhone, String code, String imageCode, String type);

    /**
     * 修改用户密码(不需要手机验证码)
     *
     * @param user
     * @return
     */
    Map<String, Object> updateUserPassword(User user);

    /**
     * 供应商认证
     *
     * @param user
     * @return
     */
    Map<String, Object> authUser(User user);

    Integer isExistTelphone(String phone);

    Map<String, Object> updateOtherUser(User user);

    List<User> findFirstApproverList(String roleName);

    /**
     * @return 注册用户数量
     */
    R<List<User>> queryRegisterUserNum();

    R<String> updateDemander(User user);

    void loginOutUserByUpdate(Long userId);

    void loginOutUserIds(List<Long> userIds);

    int updateById(User user);
    Map<Integer, Map<Long, String>> getEnterpriseNameByUserType(Map<Integer, List<Long>> typeMap);
}
