package com.liuzemin.server.framework.security.user.service.impl;

import com.liuzemin.server.framework.datasource.page.PagedResult;
import com.liuzemin.server.framework.model.constant.ResponseCode;
import com.liuzemin.server.framework.model.constant.UserTypeEnum;
import com.liuzemin.server.framework.model.context.RequestContext;
import com.liuzemin.server.framework.model.helper.MD5Helper;
import com.liuzemin.server.framework.model.helper.RHelper;
import com.liuzemin.server.framework.model.helper.ResultMapHelper;
import com.liuzemin.server.framework.model.model.*;
import com.liuzemin.server.framework.model.service.IAuthCheckService;
import com.liuzemin.server.framework.security.feign.*;
import com.liuzemin.server.framework.security.user.dao.IUserDao;
import com.liuzemin.server.framework.security.user.service.IUserService;
import com.liuzemin.server.framework.datasource.page.Page;
import com.liuzemin.server.framework.security.user.vo.Recovery;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Value("${user.origin.password}")
    private String password;

    @Autowired
    private SmsClient smsClient;

    @Autowired
    private DemandFeignClient demandFeignClient;

    @Autowired
    private SupplierFeignClient supplierFeignClient;
    @Autowired
    private RecoveryFeignClient recoveryFeignClient;
    @Autowired
    private SystemClient systemClient;

    @Autowired
    private IAuthCheckService authCheckService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String USER_ID_PREFIX = "liuzemin_userid_";
    private static final String SESSION_PREFIX = "liuzemin_session_";

    private static final String SESSION_DATA_PREFIX = "liuzemin_session_data_";

    private static final String SESSION_EXPIRE_PREFIX = "liuzemin_expire_";

    @Override
    public PagedResult<User> findPagedList(User user, Page page) {
        String dateType = user.getDateType();
        if (!StringUtils.isEmpty(dateType)) {
            if (StringUtils.isEmpty(user.getStartDate()) || StringUtils.isEmpty(user.getEndDate())) {
                return new PagedResult<>(page);
            }
            //分别按照月年查询0日1月2年
            String dateFormatStr = "";
            switch (user.getDateType()) {
                case "0":
                    dateFormatStr = "'%Y-%m-%d'";
                    break;
                case "1":
                    dateFormatStr = "'%Y-%m'";
                    break;
                case "2":
                    dateFormatStr = "'%Y'";
                    break;
                default:
                    break;
            }
            user.setDateFormatStr(dateFormatStr);
        }
        return userDao.findPagedList(user, page);
    }

    @Override
    public User queryById(Long id) {

        return userDao.queryById(id);
    }

    @Override
    public List<User> findList(User user) {
        String dateType = user.getDateType();
        if (StringUtils.isNotBlank(dateType)) {
            String dateFormatStr = "";
            // 分别按照月年查询1日2月3年
            switch (dateType) {
                case "0":
                    dateFormatStr = "'%Y-%m-%d'";
                    break;
                case "1":
                    dateFormatStr = "'%Y-%m'";
                    break;
                case "2":
                    dateFormatStr = "'%Y'";
                    break;
                default:
                    break;
            }
            user.setDateFormatStr(dateFormatStr);
        }
        return userDao.findList(user);
    }

    @Override
    public List<User> findDetailList(User user) {
        if (null == user) {
            user = new User();
        }
        user.setDeleteFlag(1);
        return userDao.findDetailList(user);
    }

    @Override
    public Map<String, Object> insert(User user) {
        if (null == user) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            user.setPasswordStatus(1);
            user.setPassword(MD5Helper.GetMD5Code(MD5Helper.GetMD5Code(password)));
        }

        User queryParam = new User();
        queryParam.setPhone(user.getPhone());
        List<User> userList = findList(queryParam);
        if (!CollectionUtils.isEmpty(userList)) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, "手机号已注册");
        }
        user.setStatus(1);
        user.setCreateInfo();
        userDao.insert(user);
        return ResultMapHelper.getSuccessMap(user.getId());
    }

    @Override
    public Map<String, Object> insertList(List<User> users) {
        if (CollectionUtils.isEmpty(users)) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        UserPriciple curUser = RequestContext.getCurrent().getUser();
        for (User user : users) {
            // password
            if (StringUtils.isEmpty(user.getPassword())) {
                user.setPassword(MD5Helper.GetMD5Code(password));
            }
            user.setPassword(MD5Helper.GetMD5Code(user.getPassword()));

            // 创建人 创建时间
            if (null != curUser) {
                user.setCreatedBy(curUser.getId());
                user.setLastUpdatedBy(curUser.getId());
            }
            user.setCreationDate(new Date());
            user.setLastUpdateDate(new Date());
        }
        int count = userDao.insertList(users);
        return ResultMapHelper.getResult(count);
    }

    @Override
    public Map<String, Object> updateAndInsert(User user) {
        if (null == user || StringUtils.isEmpty(user.getPhone())) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, "参数错误");
        }
        User userQuery = new User();
        userQuery.setPhone(user.getPhone());
        List<User> userList = findList(userQuery);
        if (!CollectionUtils.isEmpty(userList)) {
            return ResultMapHelper.getResultMap(ResponseCode.PHONE_ISEXIST_CODE, ResponseCode.PHONE_ISEXIST_MSG);
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            user.setPasswordStatus(1);
            user.setPassword(MD5Helper.GetMD5Code(MD5Helper.GetMD5Code(password)));
        }
        user.setStatus(1);
        user.setCreateInfo();
        userDao.insert(user);
        return ResultMapHelper.getSuccessMap(user.getId());

    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Map<String, Object> update(User user) {
        if (null == user) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        List<KeyValueModel> modelList = new ArrayList<>();
        modelList.add(new KeyValueModel(user.getUsername(), "用户名"));
        modelList.add(new KeyValueModel(user.getMail(), "电子邮箱"));
        R<String> checkResult = systemClient.check(modelList);
        if (!RHelper.isSuccessR(checkResult)) {
            return ResultMapHelper.getResultMap(checkResult.getCode(), checkResult.getMsg());
        }
        // 更新人，更新时间
        UserPriciple curUser = RequestContext.getCurrent().getUser();
        if (null != user.getPhone()) {
            User query = new User();
            query.setPhone(user.getPhone());
            List<User> userList = userDao.findList(query);
            if (!CollectionUtils.isEmpty(userList)) {
                return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, "手机号码已注册");
            }
        }
        // 更改邮箱时，需要输入密码
        if (StringUtils.isEmpty(user.getPassword()) && StringUtils.isNotEmpty(user.getMail())) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, "密码不能为空");
        } else if (StringUtils.isNotEmpty(user.getPassword()) && StringUtils.isNotEmpty(user.getMail())) {
            String password = userDao.findPassword(curUser.getId());
            if (password.equals(MD5Helper.GetMD5Code(user.getPassword()))) {
                user.setLastUpdatedBy(curUser.getId());
                user.setId(curUser.getId());
                user.setPassword(null);
                user.setLastUpdateDate(new Date());
                int count = userDao.update(user);
                return ResultMapHelper.getResult(count);
            } else {
                return ResultMapHelper.getResultMap(ResponseCode.SERVICE_OLDPASSWORD_CODE, "密码错误");
            }
        } else {
            // 验证旧密码
            if (StringUtils.isNotBlank(user.getOldPassword())) {
                String password = userDao.findPassword(curUser.getId());
                if (!StringUtils.isEmpty(password) && password.equals(MD5Helper.GetMD5Code(user.getOldPassword()))) {
                    user.setPassword(MD5Helper.GetMD5Code(user.getPassword()));
                } else {
                    return ResultMapHelper.getResultMap(ResponseCode.SERVICE_OLDPASSWORD_CODE,
                            ResponseCode.SERVICE_OLDPASSWORD_MSG);
                }
            }
            user.setId(curUser.getId());
            user.setLastUpdatedBy(curUser.getId());
            user.setLastUpdateDate(new Date());
            int count = userDao.update(user);
            return ResultMapHelper.getResult(count);
        }

    }

    // }

    @Override
    public Map<String, Object> updateByPhone(User user) {
        if (null == user || StringUtils.isEmpty(user.getPhone())) {
            return ResultMapHelper.getParameterErrorMap();
        }
        int count = userDao.updateByPhone(user);
        return ResultMapHelper.getResult(count);
    }

    @Override
    public Map<String, Object> updateList(List<User> users) {
        if (CollectionUtils.isEmpty(users)) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }

        UserPriciple curUser = RequestContext.getCurrent().getUser();
        for (User user : users) {
            if (null != curUser) {
                user.setLastUpdatedBy(curUser.getId());
            }
            user.setLastUpdateDate(new Date());
        }
        int count = userDao.updateList(users);
        return ResultMapHelper.getResult(count);
    }

    @Override
    public Map<String, Object> delete(Long id) {
        User userQuery = new User();
        userQuery.setId(id);
        User user = userDao.getInfo(userQuery);
        if (null == user) {
            return ResultMapHelper.getParameterErrorMap();
        }
        int userType = user.getUserType();
        Integer userType0 = UserTypeEnum.notAuthSupplier.getUserType();
        Integer userType1 = UserTypeEnum.supplier.getUserType();
        Integer userType3 = UserTypeEnum.notAuthSocialDemander.getUserType();
        Integer userType4 = UserTypeEnum.socialDemander.getUserType();
        Integer userType5 = UserTypeEnum.notAuthStrategicDemander.getUserType();
        Integer userType6 = UserTypeEnum.notAuthRegionalDistributors.getUserType();
        Integer userType7 = UserTypeEnum.regionalDistributors.getUserType();
        Integer userType10 = UserTypeEnum.platformAdmin.getUserType();
        if (userType0 == userType || userType3 == userType || userType5 == userType || userType6 == userType || userType10 == userType) {
            // 注册供应商和注册社会需求方的删除
            int count = userDao.delete(id);
            if (count > 0) {
                loginOutUserByUpdate(id);
            }
            return ResultMapHelper.getResult(count);
        } else if (userType1 == userType) {
            //提交认证但未审核通过供应商删除
            R<String> supplierResult = supplierFeignClient.deleteSupplierByUserId(id);
            if (RHelper.isSuccessR(supplierResult)) {
                int count = userDao.delete(id);
                if (count > 0) {
                    loginOutUserByUpdate(id);
                    return ResultMapHelper.getResult(count);
                }
            } else {
                return ResultMapHelper.getResultMap(supplierResult.getCode(), supplierResult.getMsg());

            }
        } else if (userType4 == userType) {
            //提交认证但未审核通过社会需求方删除
            R<String> demandResult = demandFeignClient.deleteSocialDemandersByUserId(id);
            if (RHelper.isSuccessR(demandResult)) {
                int count = userDao.delete(id);
                if (count > 0) {
                    loginOutUserByUpdate(id);
                    return ResultMapHelper.getResult(count);
                }
            } else {
                return ResultMapHelper.getResultMap(demandResult.getCode(), demandResult.getMsg());
            }
        } else if (userType7 == userType) {
            //提交认证但未审核通过社会需求方删除
            R<String> enterpriseResult = systemClient.deleteByUserId(id);
            if (RHelper.isSuccessR(enterpriseResult)) {
                int count = userDao.delete(id);
                if (count > 0) {
                    loginOutUserByUpdate(id);
                    return ResultMapHelper.getResult(count);
                }
            } else {
                return ResultMapHelper.getResultMap(enterpriseResult.getCode(), enterpriseResult.getMsg());
            }
        } else {
            return ResultMapHelper.getCustomMap("该用户的删除请联系管理员！");
        }
        return ResultMapHelper.failed();
    }

    @Override
    public Map<String, Object> deleteList(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        int count = userDao.deleteList(ids);
        return ResultMapHelper.getResult(count);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Map<String, Object> updateUserAndValidate(User user, String otherPhone, String code, String imageCode, String type) {
        if (StringUtils.isBlank(otherPhone) || StringUtils.isBlank(code) || StringUtils.isBlank(imageCode)) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        User query = new User();
        query.setPhone(otherPhone);
        List<User> userList = userDao.findList(query);
        if (!CollectionUtils.isEmpty(userList)) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, "手机号码已注册");
        }
        Map<String, Object> imgValidate = validImageCode(imageCode, type);
        if (!imgValidate.get(ResponseCode.CODE).equals(ResponseCode.SUCCESS_CODE)) {
            return imgValidate;
        }
        Map<String, Object> validate = smsClient.validate(otherPhone, code);
        if (CollectionUtils.isEmpty(validate)) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        if (!validate.get(ResponseCode.CODE).equals(ResponseCode.SUCCESS_CODE)) {
            return validate;
        }
        Long userId = RequestContext.getCurrent().getUser().getId();
        user.setLastUpdatedBy(userId);
        String password = userDao.findPassword(userId);
        user.setLastUpdateDate(new Date());
        if (password.equals(MD5Helper.GetMD5Code(user.getPassword()))) {
            user.setPassword(null);
            int count = userDao.update(user);
            if (count > 0) {
                loginOutUserByUpdate(userId);
            }
            return ResultMapHelper.getResult(count);
        } else {
            return ResultMapHelper.getResultMap(ResponseCode.SERVICE_OLDPASSWORD_CODE, "密码错误");
        }

    }

    private Map<String, Object> validImageCode(String code, String type) {
        String tokenSessionId = RequestContext.getCurrent().getToken();
        System.out.println(
                "tokenSessionId-----++++++++++++++++++++---validImageCode------++++++++---->" + tokenSessionId);
        String validCodeKey = "IMAGECODE_" + tokenSessionId + type;
        Object verCode = redisTemplate.opsForValue().get(validCodeKey);

        redisTemplate.delete(validCodeKey);
        if (null == verCode) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, "图形验证码已失效，请重新刷新验证码");
        }
        String verCodeStr = verCode.toString();
        if (verCodeStr == null || code == null || code.isEmpty() || !verCodeStr.equalsIgnoreCase(code)) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, "图形验证码错误");
        } else {
            return ResultMapHelper.getSuccessMap();
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Map<String, Object> updateUserPassword(User user) {
        UserPriciple curUser = RequestContext.getCurrent().getUser();
        user.setId(curUser.getId());
        return update(user);
    }

    @Override
    public Map<String, Object> authUser(User user) {
        if (Objects.isNull(user) || StringUtils.isBlank(user.getRealName()) || StringUtils.isEmpty(user.getIdCardNO())) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        if (null == user.getId()) {
            user.setId(RequestContext.getCurrent().getUser().getId());
        }
//        User u = new User();
//        u.setId(user.getId());
//        List<User> uList = userDao.findList(u);
//        Integer times = uList.get(0).getAuthTimes();
//        if (null == times) {
//            times = 1;
//        } else if (times > 50) {
//            return ResultMapHelper.getResultMap(ResponseCode.SYSTEM_AUTHTIMES_CODE, ResponseCode.SYSTEM_AUTHTIMES_MSG);
//        } else {
//            times = times + 1;
//        }

        // 调用验证接口 TODO
        //  Map<String, Object> authMap = userAuthenticateClient.authenticate(user.getRealName(), user.getIdCardNO());
        //  if (ResponseCode.SUCCESS_CODE.equals(authMap.get(ResponseCode.CODE))) {
        // 认证成功, 更新用户认证状态
        //  String genderStr = (String) authMap.get("gender");
        String idCardNo = user.getIdCardNO();
        Integer genderStr = null;
        if (idCardNo.length() == 18) {
            genderStr = Integer.parseInt(idCardNo.substring(idCardNo.length() - 2, idCardNo.length() - 1));
        }
        if (null != genderStr) {
            if (genderStr % 2 != 0) {
                user.setGender(1);
            } else {
                user.setGender(2);
            }
        }
        user.setAuthStatus(1);
        user.setPhone(null);
        //   user.setAuthTimes(times);
        int count = userDao.update(user);
        return ResultMapHelper.getResult(count);
        // }
        //   return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, "认证失败");
    }

    @Override
    public Integer isExistTelphone(String phone) {

        return userDao.isExistTelphone(phone);

    }


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Map<String, Object> updateOtherUser(User user) {
        if (null == user) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        if (null != user.getPhone()) {
            User query = new User();
            query.setPhone(user.getPhone());
            List<User> userList = userDao.findList(query);
            if (!CollectionUtils.isEmpty(userList)) {
                if (userList.get(0).getId().compareTo(user.getId()) != 0) {
                    return ResultMapHelper.getResultMap(ResponseCode.PHONE_ISEXIST_CODE, ResponseCode.PHONE_ISEXIST_MSG);
                }
            }
        }
        if (!StringUtils.isEmpty(user.getPassword())) {
            user.setPassword(MD5Helper.GetMD5Code(user.getPassword()));
        }
        user.setId(user.getId());
        user.setLastUpdatedBy(RequestContext.getCurrent().getUser().getId());
        user.setLastUpdateDate(new Date());
        int count = userDao.update(user);
        if (count > 0) {
            loginOutUserByUpdate(user.getId());
        }
        return ResultMapHelper.getResult(count);
    }

    @Override
    public List<User> findFirstApproverList(String roleName) {
        // String roleName = "供应商初级审核员";
        return userDao.findNameByRoleName(roleName);
    }

    @Override
    public R<List<User>> queryRegisterUserNum() {
        // TODO Auto-generated method stub
        return RHelper.getSuccessR(userDao.queryRegisterUserNum());
    }

    @Override
    public R<String> updateDemander(User user) {
        User u = userDao.getInfo(user);
        if (null == u) {
            return RHelper.getResultR(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        if (!u.getPhone().equals(user.getPhone())) {
            int count = userDao.isExistTelphone(user.getPhone());
            if (count > 0) {
                return RHelper.getResultR(ResponseCode.PHONE_ISEXIST_CODE, ResponseCode.PHONE_ISEXIST_MSG);
            }
            int resultNum = userDao.update(user);
            if (resultNum > 0) {
                loginOutUserByUpdate(user.getId());
                return RHelper.getResultR(ResponseCode.SUCCESS_CODE, "密码恢复为初始密码！");
            } else {
                return RHelper.getResultR(ResponseCode.ERROR_CODE, ResponseCode.ERROR_MSG);
            }
        }

        int resultNum = userDao.update(user);
        return RHelper.getResult(resultNum);
    }

    public void loginOutUserByUpdate(Long userId) {
        String crecKey = USER_ID_PREFIX + "liuzemin.crec_" + userId;
        String crecSession = String.valueOf(redisTemplate.opsForValue().get(crecKey));
        if (StringUtils.isNotEmpty(crecSession)) {
            authCheckService.logout("liuzemin.crec", crecSession);
        }
        String crecAppKey = USER_ID_PREFIX + "liuzemin.crec_app_" + userId;
        String crecAppSession = String.valueOf(redisTemplate.opsForValue().get(crecAppKey));
        if (StringUtils.isNotEmpty(crecAppSession)) {
            authCheckService.logout("liuzemin.crec", crecAppSession);
        }
        String adminKey = USER_ID_PREFIX + "liuzemin.admin_" + userId;
        String adminSession = String.valueOf(redisTemplate.opsForValue().get(adminKey));
        if (StringUtils.isNotEmpty(adminSession)) {
            authCheckService.logout("liuzemin.admin", adminSession);
        }
    }

    @Override
    public void loginOutUserIds(List<Long> userIds) {
        List<List<Long>> newList = ListUtils.partition(userIds, 1000);
        newList.forEach(newUserIds -> {
            String crecKey = USER_ID_PREFIX + "liuzemin.crec_";
            String crecAppKey = USER_ID_PREFIX + "liuzemin.crec_app_";
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            List<Object> objects = redisTemplate.executePipelined((RedisCallback<String>) redisConnection -> {
                for (Long userId : newUserIds) {
                    String key = crecKey + userId;
                    redisConnection.get(Objects.requireNonNull(serializer.serialize(key)));
                    String appKey = crecAppKey + userId;
                    redisConnection.get(Objects.requireNonNull(serializer.serialize(appKey)));
                    String userKey = USER_ID_PREFIX + "liuzemin.crec" + "_" + userId;
                    String appUserKey = USER_ID_PREFIX + "liuzemin.crec_app_" + "_" + userId;
                    redisConnection.del(serializer.serialize(userKey), serializer.serialize(appUserKey));
                }
                return null;
            });
            objects.removeAll(Collections.singleton(null));
            objects.removeAll(Collections.singleton(0L));
            objects.removeAll(Collections.singleton(1L));
            if (!CollectionUtils.isEmpty(objects)) {
                redisTemplate.executePipelined((RedisCallback<String>) redisConnection -> {
                    objects.forEach(session -> {
                        String s = session.toString();
                        String sessionKey = SESSION_PREFIX + "liuzemin.crec" + "_" + s;
                        String dataKey = SESSION_DATA_PREFIX + "liuzemin.crec" + "_" + s;
                        String expireKey = SESSION_EXPIRE_PREFIX + "liuzemin.crec" + "_" + s;
                        String appSessionKey = SESSION_PREFIX + "liuzemin.crec_app_" + "_" + s;
                        String appDataKey = SESSION_DATA_PREFIX + "liuzemin.crec_app_" + "_" + s;
                        String appExpireKey = SESSION_EXPIRE_PREFIX + "liuzemin.crec_app_" + "_" + s;
                        redisConnection.del(serializer.serialize(sessionKey), serializer.serialize(dataKey), serializer.serialize(expireKey), serializer.serialize(appSessionKey), serializer.serialize(appDataKey), serializer.serialize(appExpireKey));
                    });
                    return null;
                });
            }
        });
    }

    @Override
    public int updateById(User user) {

        return userDao.update(user);
    }


    @Override
    public Map<Integer, Map<Long, String>> getEnterpriseNameByUserType(Map<Integer, List<Long>> typeMap) {
        Map<Integer, Map<Long, String>> resultMap = new HashMap<>();
        //1.供应商，2.需求方(集团公司，子分公司)， 7.区域经销商，8.经销商推荐，11.项目部，12.中介公司 ,13个人
        for (Map.Entry<Integer, List<Long>> entry : typeMap.entrySet()) {
            Integer userType = entry.getKey();
            List<Long> enterpriseIdList = entry.getValue();
            List<Long> enterpriseIds = enterpriseIdList.stream().distinct().collect(Collectors.toList());
            switch (userType) {
                case 1:
                    // 查询供应商
                    Supplier supplier = new Supplier();
                    supplier.setIds(enterpriseIds);
                    R<List<Supplier>> r = supplierFeignClient.findSupplierList(supplier);
                    if (RHelper.isSuccessR(r) && !CollectionUtils.isEmpty(r.getDatas())) {
                        List<Supplier> datas = r.getDatas();
                        Map<Long, String> collect = datas.stream().collect(Collectors.toMap(Supplier::getId, Supplier::getName));
                        resultMap.put(userType, collect);
                    }
                    break;
                case 2:
                case 7:
                case 8:
                case 12:
                    Enterprise enterprise = new Enterprise();
                    enterprise.setIds(enterpriseIds);
                    List<Enterprise> enterpriseList = userDao.findEnterprise(enterprise);
                    Map<Long, String> nameMap = enterpriseList.stream().collect(Collectors.toMap(Enterprise::getId, Enterprise::getName));
                    resultMap.put(userType, nameMap);
                    break;
                case 11:
                    Project project = new Project();
                    project.setIds(enterpriseIds);
                    R<List<Project>> projectResult = demandFeignClient.findProjectList(project);
                    if (RHelper.isSuccessR(projectResult) && !CollectionUtils.isEmpty(projectResult.getDatas())) {
                        List<Project> datas = projectResult.getDatas();
                        Map<Long, String> collect = datas.stream().collect(Collectors.toMap(Project::getId, Project::getProjectName));
                        resultMap.put(userType, collect);
                    }
                    break;
                case 13:
                    User user = new User();
                    user.setUserIds(enterpriseIds);
                    List<User> list = userDao.findList(user);
                    Map<Long, String> collect = list.stream().filter(u -> !StringUtils.isEmpty(u.getUsername())).collect(Collectors.toMap(User::getId, User::getUsername));
                    resultMap.put(userType, collect);
                    break;
                case 16:
                    // 查询回收商
                    Recovery recovery = new Recovery();
                    recovery.setIds(enterpriseIds);
                    R<List<Recovery>> recoveryList = recoveryFeignClient.findRecoveryList(recovery);
                    if (RHelper.isSuccessR(recoveryList) && !CollectionUtils.isEmpty(recoveryList.getDatas())) {
                        List<Recovery> datas = recoveryList.getDatas();
                        Map<Long, String> collectRecovery = datas.stream().collect(Collectors.toMap(Recovery::getId, Recovery::getName));
                        resultMap.put(userType, collectRecovery);
                    }
                    break;
                default:
                    break;
            }
        }
        return resultMap;
    }
}
