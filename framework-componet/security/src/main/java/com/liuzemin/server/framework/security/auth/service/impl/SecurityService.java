package com.liuzemin.server.framework.security.auth.service.impl;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import com.alibaba.fastjson.JSON;
import com.google.code.kaptcha.Producer;
import com.liuzemin.server.framework.model.constant.ResponseCode;
import com.liuzemin.server.framework.model.constant.UserTypeEnum;
import com.liuzemin.server.framework.model.context.Current;
import com.liuzemin.server.framework.model.context.RequestContext;
import com.liuzemin.server.framework.model.helper.MD5Helper;
import com.liuzemin.server.framework.model.helper.RHelper;
import com.liuzemin.server.framework.model.helper.ResultMapHelper;
import com.liuzemin.server.framework.model.helper.VerifyCodeHelper;
import com.liuzemin.server.framework.model.model.*;
import com.liuzemin.server.framework.model.service.IAuthCheckService;
import com.liuzemin.server.framework.model.utils.RedisKeyType;
import com.liuzemin.server.framework.security.auth.service.ISecurityService;
import com.liuzemin.server.framework.security.feign.*;
import com.liuzemin.server.framework.security.permission.dao.IUserRoleProgramDao;
import com.liuzemin.server.framework.security.permission.service.IPermissionService;
import com.liuzemin.server.framework.security.permission.service.IUserRoleProgramService;
import com.liuzemin.server.framework.security.program.dao.IProgramItemDao;
import com.liuzemin.server.framework.security.role.dao.IRoleDao;
import com.liuzemin.server.framework.security.role.model.Role;
import com.liuzemin.server.framework.security.user.dao.IUserDao;
import com.liuzemin.server.framework.security.user.service.IUserService;
import com.liuzemin.server.framework.security.user.vo.Recovery;
import com.liuzemin.server.framework.security.auth.service.ISecurityService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class SecurityService implements ISecurityService {

    public static final Logger log = LoggerFactory.getLogger(SecurityService.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IUserRoleProgramService userRoleProgramService;

    @Autowired
    private IAuthCheckService authCheckService;

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private SitemapClient sitemapClient;

    @Autowired
    private SmsClient smsClient;

    @Autowired
    private SupplierFeignClient supplierFeignClient;
    @Autowired
    private DemandFeignClient demandFeignClient;

    @Autowired
    private SystemClient systemClient;
    @Autowired
    private IUserRoleProgramDao userRoleProgramDao;
    @Autowired
    private IRoleDao roleDao;
    @Autowired
    private IProgramItemDao programItemDao;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private RecoveryFeignClient recoveryFeignClient;

    @Value("${user.register.roleId}")
    private String roleId;

    @Value("${user.register.notAuthSocialDemanderRoleId}")
    private String notAuthSocialDemander;

    @Value("${jpush.appKey}")
    private String appKey;
    @Value("${jpush.masterSecret}")
    private String masterSecret;

    @Value("${user.register.programId}")
    private String programId;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    private final Pattern phonePattern = Pattern.compile("^(1)[\\d]{10}$");

    private final Pattern mailPattern = Pattern.compile("^[\\w]+@[\\w]+\\.(com|cn|net|org)$");

    private final Pattern userIdPattern = Pattern.compile("^(?![0-9]+$)[0-9A-Za-z]{2,18}$");

    @Override
    public Map<String, Object> adminLogin(String username, String password, String imageCode, String type) {

        Map<String, Object> isOpenImgMap = new HashMap<>(16);
        isOpenImgMap.put("isOpenImageCode", false);
        Integer errorCount = (Integer) redisTemplate.opsForValue().get(username);
        if (null == errorCount) {
            errorCount = 0;
        } else if (errorCount > 2) {
            isOpenImgMap.put("isOpenImageCode", true);
            Map<String, Object> imgValidate = validImageCode(imageCode, type);
            if (!imgValidate.get(ResponseCode.CODE).equals(ResponseCode.SUCCESS_CODE)) {
                return imgValidate;
            }
        }

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return ResultMapHelper.getLoginResultMap(ResponseCode.CUSTOM_CODE, "用户名密码不能为空", isOpenImgMap);
        }

        User param = new User();
        param.setDeleteFlag(1);
        //启用的用户才可以登录
        param.setStatus(1);
        password = MD5Helper.GetMD5Code(password);
        param.setPassword(password);
        //只有平台管理员可以登录
        param.setUserType(UserTypeEnum.platformAdmin.getUserType());
        List<User> list;
        if (phonePattern.matcher(username).matches()) {
            param.setPhone(username);
            list = userService.findList(param);
        } else if (mailPattern.matcher(username).matches()) {
            param.setMail(username);
            list = userService.findList(param);
        } else if (userIdPattern.matcher(username).matches()) {
            param.setUserId(username);
            list = userService.findList(param);
        } else {
            return ResultMapHelper.getLoginResultMap(ResponseCode.CUSTOM_CODE, "登录失败！", isOpenImgMap);
        }

        if (!CollectionUtils.isEmpty(list)) {
            User user = list.get(0);
            redisTemplate.opsForValue().set(username, 0, 8, TimeUnit.HOURS);
            Current current = RequestContext.getCurrent();
            String token = current.getToken();

            current.setUser(user);
            // 查询权限
            findPermissions(current, user);
            Map<String, Object> map = ResultMapHelper.getSuccessMap();
            //清空计数
            redisTemplate.delete(username);
            // 存入redis
            authCheckService.login(current.getDomain(), token, String.valueOf(user.getId()), JSON.toJSONString(current),
                    1800);

            // 查询web菜单
            List<Sitemap> sitemaps = sitemapClient.findUserSitemap();
            map.put("user", current.getUser());
            map.put("permissions", current.getPermissions());
            map.put("sitemaps", sitemaps);
            return map;
        }
        errorCount = errorCount + 1;
        redisTemplate.opsForValue().set(username, errorCount, 8, TimeUnit.HOURS);
        if (errorCount == 3) {
            isOpenImgMap.put("isOpenImageCode", true);
        }
        return ResultMapHelper.getLoginResultMap(ResponseCode.CUSTOM_CODE, "用户名或密码错误", isOpenImgMap);
    }


    @Override
    public Map<String, Object> login(String username, String password, String imageCode, String type, String loginModel) {
        Map<String, Object> isOpenImgMap = new HashMap<>(16);
        isOpenImgMap.put("isOpenImageCode", false);
        Integer errorCount = (Integer) redisTemplate.opsForValue().get(username);
        if (null == errorCount) {
            errorCount = 0;
        } else if (errorCount > 2) {
            isOpenImgMap.put("isOpenImageCode", true);
            Map<String, Object> imgValidate = validImageCode(imageCode, type);
            if (!imgValidate.get(ResponseCode.CODE).equals(ResponseCode.SUCCESS_CODE)) {
                imgValidate.put(ResponseCode.MAP_DATAS, isOpenImgMap);
                return imgValidate;
            }
        }
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return ResultMapHelper.getLoginResultMap(ResponseCode.CUSTOM_CODE, "用户名密码不能为空", isOpenImgMap);
        }
        List<User> list = null;
        User param = new User();
        param.setDeleteFlag(1);
        //启用的用户才可以登录
        param.setStatus(1);
        if ("1".equals(loginModel)) {
            password = MD5Helper.GetMD5Code(password);
            param.setPassword(password);
            if (phonePattern.matcher(username).matches()) {
                param.setPhone(username);
                list = userService.findList(param);
            } else if (mailPattern.matcher(username).matches()) {
                param.setMail(username);
                list = userService.findList(param);
            } else if (userIdPattern.matcher(username).matches()) {
                param.setUserId(username);
                list = userService.findList(param);
            } else {
                return ResultMapHelper.getLoginResultMap(ResponseCode.CUSTOM_CODE, "用户名格式错误", isOpenImgMap);
            }
        } else if ("2".equals(loginModel)) {
            Map<String, Object> validate = smsClient.validate(username, password);
            if (Boolean.FALSE.equals(ResultMapHelper.isSuccess(validate))) {
                errorCount = errorCount + 1;
                redisTemplate.opsForValue().set(username, errorCount, 8, TimeUnit.HOURS);
                if (errorCount == 3) {
                    isOpenImgMap.put("isOpenImageCode", true);
                    validate.put(ResponseCode.MAP_DATAS, isOpenImgMap);
                }
                return validate;
            }
            param.setPhone(username);
            list = userService.findList(param);
        }
        if (!CollectionUtils.isEmpty(list)) {
            User user = list.get(0);
            redisTemplate.opsForValue().set(username, 0, 8, TimeUnit.HOURS);
            Current current = RequestContext.getCurrent();
            String token = current.getToken();

            current.setUser(user);
            // 查询权限
            findPermissions(current, user);

            // 查询供应商
            Supplier supplierParam = new Supplier();
            supplierParam.setUserId(user.getId());
            supplierParam.setApproveStatus(2);
            supplierParam.setStatus(1);

            Map<String, Object> map = ResultMapHelper.getSuccessMap();

            getEnterpriseId(user, map, current);
            //清空计数
            redisTemplate.delete(username);
            // 存入redis
            authCheckService.login(current.getDomain(), token, String.valueOf(user.getId()), JSON.toJSONString(current),
                    7200);
            map.put("user", current.getUser());
            map.put("permissions", current.getPermissions());
            return map;
        }
        errorCount = errorCount + 1;
        redisTemplate.opsForValue().set(username, errorCount, 8, TimeUnit.HOURS);
        if (errorCount == 3) {
            isOpenImgMap.put("isOpenImageCode", true);
        }
        return ResultMapHelper.getLoginResultMap(ResponseCode.CUSTOM_CODE, "用户名或密码错误", isOpenImgMap);
    }

    @Override
    public Map<String, Object> appLogin(String username, String password, String imageCode, String type, String loginModel) {
        Map<String, Object> isOpenImgMap = new HashMap<>(16);
        isOpenImgMap.put("isOpenImageCode", false);
        Integer errorCount = (Integer) redisTemplate.opsForValue().get(username);
        if (null == errorCount) {
            errorCount = 0;
        } else if (errorCount > 2) {
            isOpenImgMap.put("isOpenImageCode", true);
            Map<String, Object> imgValidate = validImageCode(imageCode, type);
            if (!imgValidate.get(ResponseCode.CODE).equals(ResponseCode.SUCCESS_CODE)) {
                imgValidate.put(ResponseCode.MAP_DATAS, isOpenImgMap);
                return imgValidate;
            }
        }
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return ResultMapHelper.getLoginResultMap(ResponseCode.CUSTOM_CODE, "用户名密码不能为空", isOpenImgMap);
        }
        User param = new User();
        param.setDeleteFlag(1);
        //启用的用户才可以登录
        param.setStatus(1);
        List<User> list = null;
        if ("1".equals(loginModel)) {
            password = MD5Helper.GetMD5Code(password);
            param.setPassword(password);
            if (phonePattern.matcher(username).matches()) {
                param.setPhone(username);
                list = userService.findList(param);
            } else if (mailPattern.matcher(username).matches()) {
                param.setMail(username);
                list = userService.findList(param);
            } else if (userIdPattern.matcher(username).matches()) {
                param.setUserId(username);
                list = userService.findList(param);
            } else {
                return ResultMapHelper.getLoginResultMap(ResponseCode.CUSTOM_CODE, "用户名格式错误", isOpenImgMap);
            }
        } else if ("2".equals(loginModel)) {
            Map<String, Object> validate = smsClient.validate(username, password);
            // 验证验证码
            if (Boolean.FALSE.equals(ResultMapHelper.isSuccess(validate))) {
                errorCount = errorCount + 1;
                redisTemplate.opsForValue().set(username, errorCount, 8, TimeUnit.HOURS);
                if (errorCount == 3) {
                    isOpenImgMap.put("isOpenImageCode", true);
                    validate.put(ResponseCode.MAP_DATAS, isOpenImgMap);
                }
                return validate;
            }
            param.setPhone(username);
            list = userService.findList(param);
        }

        if (!CollectionUtils.isEmpty(list)) {
            //错误次数
            redisTemplate.opsForValue().set(username, 0, 8, TimeUnit.HOURS);
            Current current = RequestContext.getCurrent();
            String token = current.getToken();
            String domain = current.getDomain();
            JPushClient jpushClient = new JPushClient(masterSecret, appKey);
            try {
                //登录之前先删除该手机号和设备的绑定关系
                jpushClient.deleteAlias(username, null);
            } catch (APIConnectionException | APIRequestException e) {
                e.printStackTrace();
            }

            User user = list.get(0);
            current.setUser(user);
            // 查询权限
            findPermissions(current, user);

            Map<String, Object> map = ResultMapHelper.getSuccessMap();
            Map<String, Object> obj = new HashMap<>(16);
            obj.put("user", current.getUser());
            obj.put("permissions", current.getPermissions());

            getEnterpriseId(user, obj, current);
            //清空计数
            redisTemplate.delete(username);
            // 存入redis
            authCheckService.appLogin(domain, token, "app_" + user.getId(), JSON.toJSONString(current),
                    3600 * 24 * 30);
            map.put(ResponseCode.MAP_DATAS, obj);
            return map;
        }

        errorCount = errorCount + 1;
        redisTemplate.opsForValue().set(username, errorCount, 8, TimeUnit.HOURS);
        if (errorCount == 3) {
            isOpenImgMap.put("isOpenImageCode", true);
        }

        return ResultMapHelper.getLoginResultMap(ResponseCode.CUSTOM_CODE, "用户名或密码错误", isOpenImgMap);
    }

    private void getEnterpriseId(User user, Map<String, Object> map, Current current) {
        Integer userType = user.getUserType();
        Long id = user.getId();
        List<Long> enterpriseIds = new ArrayList<>();
        //1.供应商，2.需求方(集团公司，子分公司)， 7.区域经销商，8.经销商推荐，11.项目部，12.中介公司 16.回收商
        switch (userType) {
            case 1:
                // 查询供应商
                R<List<Supplier>> r = supplierFeignClient.findList(user.getId(), 2, 1);
                if (RHelper.isSuccessR(r) && !CollectionUtils.isEmpty(r.getDatas())) {
                    map.put("suppliers", r.getDatas());
                    Map<Long, String> collect = new HashMap<>();
                    collect.put(r.getDatas().get(0).getId(), r.getDatas().get(0).getName());
                    map.put("orgInfos", collect);
                    current.setSupplierId(r.getDatas().get(0).getId());
                    current.setSupplier(r.getDatas().get(0));
                    current.setEnterpriseName(r.getDatas().get(0).getName());
                    enterpriseIds.add(r.getDatas().get(0).getId());
                }
                break;
            case 2:
                Role role = new Role();
                role.setRoleName("集团公司管理员");
                List<Role> list1 = roleDao.findList(role);
                Long roleId = list1.get(0).getId();
                UserRoleProgram userRoleProgram = new UserRoleProgram();
                userRoleProgram.setUserId(id);
                userRoleProgram.setRoleId(roleId);
                List<UserRoleProgram> userRoleProgramList = userRoleProgramDao.findList(userRoleProgram);
                List<Long> projectUnitIds = new ArrayList<>();
                if (CollectionUtils.isEmpty(userRoleProgramList)) {
                    DemandCompanyAdmin demandCompanyAdmin = new DemandCompanyAdmin();
                    demandCompanyAdmin.setUserId(id);
                    R<List<DemandCompanyAdmin>> companyAdminResult = demandFeignClient.findCompanyAdminList(demandCompanyAdmin);
                    if (RHelper.isSuccessR(companyAdminResult) && !CollectionUtils.isEmpty(companyAdminResult.getDatas())) {
                        List<DemandCompanyAdmin> datas = companyAdminResult.getDatas();
                        projectUnitIds = datas.stream().map(DemandCompanyAdmin::getProjectUnit).collect(Collectors.toList());
                    }
                } else {
                    UserRoleProgram userRoleProgram1 = userRoleProgramList.get(0);
                    ProgramItem programItem = new ProgramItem();
                    programItem.setProgramId(userRoleProgram1.getProgramId());
                    programItem.setDimensionCode("projectUnit");
                    List<ProgramItem> list = programItemDao.findList(programItem);
                    projectUnitIds = list.stream().filter(p -> !p.getDimensionValue().equals("@All")).map(p -> Long.valueOf(p.getDimensionValue())).collect(Collectors.toList());
                }
                if (!CollectionUtils.isEmpty(projectUnitIds)) {
                    R<List<ProjectInfo>> projectUnitDatas = demandFeignClient.findProjectUnitList(projectUnitIds);
                    if (Boolean.TRUE.equals(RHelper.isSuccessR(projectUnitDatas))) {
                        List<ProjectInfo> projectInfoList = projectUnitDatas.getDatas();
                        enterpriseIds = projectInfoList.stream().map(ProjectInfo::getEnterpriseId).collect(Collectors.toList());
                        current.setEnterpriseName(projectInfoList.get(0).getName());
                        Map<Long, String> collect = new HashMap<>();
                        collect.put(enterpriseIds.get(0), projectInfoList.get(0).getName());
                        map.put("orgInfos", collect);

                    }
                }

                break;
            case 7:
            case 8:
            case 12:
                Enterprise enterprise = new Enterprise();
                enterprise.setUserId(id);
                List<Enterprise> enterpriseList = userDao.findEnterprise(enterprise);
                enterpriseIds = enterpriseList.stream().map(Enterprise::getId).collect(Collectors.toList());
                current.setEnterpriseName(enterpriseList.get(0).getName());
                Map<Long, String> collect = new HashMap<>();
                collect.put(enterpriseIds.get(0), enterpriseList.get(0).getName());
                map.put("orgInfos", collect);
                break;
            case 11:
                Project project = new Project();
                project.setUserId(id);
                R<List<Project>> projectResult = demandFeignClient.findProjectList(project);
                if (RHelper.isSuccessR(projectResult) && !CollectionUtils.isEmpty(projectResult.getDatas())) {
                    List<Project> datas = projectResult.getDatas();
                    enterpriseIds = datas.stream().map(Project::getId).collect(Collectors.toList());
                    Map<Long, String> collect2 = datas.stream().collect(Collectors.toMap(Project::getId, Project::getProjectName));
                    current.setProjectNameMap(collect2);
                    map.put("orgInfos", collect2);
                }
                break;

            case 16:
                Recovery recovery = new Recovery();
                recovery.setUserId(user.getId());
                R<List<Recovery>> recoveryList = recoveryFeignClient.findRecoveryList(recovery);
                if(recoveryList.getDatas().size()>0){

                    enterpriseIds = recoveryList.getDatas().stream().map(Recovery::getId).collect(Collectors.toList());
                    current.setEnterpriseName(recoveryList.getDatas().get(0).getName());
                }
                break;
            default:
                break;
        }
        current.setEnterpriseIds(enterpriseIds);
    }

    private void findPermissions(Current current, User user) {
        List<Permission> permissions = permissionService.findUserPermissions(user.getId());
        Set<String> permissionSet = new HashSet<>();
        for (Permission permission : permissions) {
            if (null == permission || StringUtils.isEmpty(permission.getScope())
                    || StringUtils.isEmpty(permission.getrId()) || StringUtils.isEmpty(permission.getPrId())) {
                continue;
            }
            String permission1 = permission.getScope() + "$" + permission.getrId() + "$" + permission.getPrId();
            permissionSet.add(permission1);
        }
        current.setPermissions(permissionSet);
    }

    @Override
    public Map<String, Object> logout(String sessionId) {
        if (StringUtils.isEmpty(sessionId)) {
            return ResultMapHelper.getParameterErrorMap();
        }
        Boolean flag = authCheckService.logout(RequestContext.getCurrent().getDomain(), sessionId);
        if (!flag) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, "登出失败");
        }
        return ResultMapHelper.getSuccessMap();
    }

    @Override
    public Map<String, Object> register(User user, String code, String imgCode, String type) {
        // 从REDIS中获取字典map
        if (isStopPlatformOperation()) {
            return ResultMapHelper.getPermissionDenied();
        }
        Map<String, Object> isOpenImgMap = new HashMap<>(16);

        //同一手机号注册提交超过3次开启验证码,防止恶意注册，暴力破解短信验证码
        isOpenImgMap.put("isOpenImageCode", false);
        Integer errorCount = (Integer) redisTemplate.opsForValue().get(user.getPhone());
        if (null == errorCount) {
            errorCount = 0;
        } else if (errorCount > 2) {
            isOpenImgMap.put("isOpenImageCode", true);
            Map<String, Object> imgValidate = validImageCode(imgCode, type);
            if (!imgValidate.get(ResponseCode.CODE).equals(ResponseCode.SUCCESS_CODE)) {
                imgValidate.put(ResponseCode.MAP_DATAS, isOpenImgMap);
                return imgValidate;
            }
        }

        // 验证传递过来的参数
        if (StringUtils.isBlank(user.getPhone()) || StringUtils.isBlank(user.getPassword())) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        // 验证验证码
        Map<String, Object> validate = smsClient.validate(user.getPhone(), code);
        if (CollectionUtils.isEmpty(validate)) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, "短信验证码错误");
        }
        if (!validate.get(ResponseCode.CODE).equals(ResponseCode.SUCCESS_CODE)) {
            errorCount = errorCount + 1;
            redisTemplate.opsForValue().set(user.getPhone(), errorCount, 8, TimeUnit.HOURS);
            if (3 == errorCount) {
                isOpenImgMap.put("isOpenImageCode", true);
                validate.put(ResponseCode.MAP_DATAS, isOpenImgMap);
            }
            return validate;
        }
        //同一手机号注册提交超过3次开启验证码
        errorCount = errorCount + 1;
        redisTemplate.opsForValue().set(user.getPhone(), errorCount, 8, TimeUnit.HOURS);
        // 密码加密
        user.setPassword(MD5Helper.GetMD5Code(user.getPassword()));

        // 初次注册 用户为普通用户
        user.setUserType(UserTypeEnum.notAuthSupplier.getUserType());
        user.setPasswordStatus(2);
        User query = new User();
        query.setPhone(user.getPhone());
        query.setDeleteFlag(1);
        List<User> userList = userDao.findList(query);
        if (!CollectionUtils.isEmpty(userList)) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, "手机号码已注册");
        }

        // 存入数据库
        int count = insertUserRoleProgram(user, roleId);
        if (count > 0) {
            //清空计数
            redisTemplate.delete(user.getPhone());
        }

        return ResultMapHelper.getResult(count);

    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public R<String> socialDemanderRegister(User user, String code, String imageCode, String type) {
        if (isStopPlatformOperation()) {
            return RHelper.getPermissionDenied();
        }

        // 验证传递过来的参数
        if (Objects.isNull(user) || StringUtils.isBlank(user.getPhone()) || StringUtils.isBlank(user.getPassword())
                || StringUtils.isBlank(imageCode)) {
            return RHelper.getResultR(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        // 验证图形验证码

        Map<String, Object> imgValidate = validImageCode(imageCode, type);

        if (!imgValidate.get(ResponseCode.CODE).equals(ResponseCode.SUCCESS_CODE)) {
            return RHelper.getResultR(imgValidate.get(ResponseCode.CODE).toString(), imgValidate.get(ResponseCode.MSG).toString());
        }

        // 验证验证码
        Map<String, Object> validate = smsClient.validate(user.getPhone(), code);
        if (CollectionUtils.isEmpty(validate)) {
            return RHelper.getResultR(ResponseCode.CUSTOM_CODE, "短信验证码错误");
        }
        if (!validate.get(ResponseCode.CODE).equals(ResponseCode.SUCCESS_CODE)) {
            return RHelper.getResultR(validate.get("code").toString(), validate.get("msg").toString());
        }
        // 密码加密
        user.setPassword(MD5Helper.GetMD5Code(user.getPassword()));

        // 初次注册 用户为未认证的社会需求方
        user.setUserType(UserTypeEnum.notAuthSocialDemander.getUserType());
        user.setPasswordStatus(2);

        User query = new User();
        query.setPhone(user.getPhone());
        query.setDeleteFlag(1);
        List<User> userList = userDao.findList(query);
        if (!CollectionUtils.isEmpty(userList)) {
            return RHelper.getResultR(ResponseCode.CUSTOM_CODE, "手机号码已注册");
        }
        int count = insertUserRoleProgram(user, notAuthSocialDemander);

        return RHelper.getResult(count);
    }

    @Override
    public Map<String, Object> registerAndLogin(User user, String code, String imageCode, String type) {
        if (isStopPlatformOperation()) {
            return ResultMapHelper.getPermissionDenied();
        }
        Map<String, Object> isOpenImgMap = new HashMap<>(16);
        isOpenImgMap.put("isOpenImageCode", false);
        Integer errorCount = (Integer) redisTemplate.opsForValue().get(user.getPhone());
        if (null == errorCount) {
            errorCount = 0;
        } else if (errorCount > 2) {
            isOpenImgMap.put("isOpenImageCode", true);
            Map<String, Object> imgValidate = validImageCode(imageCode, type);
            if (!imgValidate.get(ResponseCode.CODE).equals(ResponseCode.SUCCESS_CODE)) {
                imgValidate.put(ResponseCode.MAP_DATAS, isOpenImgMap);
                return imgValidate;
            }
        }
        // 验证传递过来的参数
        if (StringUtils.isBlank(user.getPhone()) || StringUtils.isBlank(user.getPassword())) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        // 验证验证码
        Map<String, Object> validate = smsClient.validate(user.getPhone(), code);
        if (CollectionUtils.isEmpty(validate)) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, "短信验证码错误");
        }
        if (!validate.get(ResponseCode.CODE).equals(ResponseCode.SUCCESS_CODE)) {
            errorCount = errorCount + 1;
            redisTemplate.opsForValue().set(user.getPhone(), errorCount, 8, TimeUnit.HOURS);
            if (3 == errorCount) {
                isOpenImgMap.put("isOpenImageCode", true);
                validate.put(ResponseCode.MAP_DATAS, isOpenImgMap);
            }
            return validate;
        }
        // 初次注册
        User query = new User();
        query.setPhone(user.getPhone());
        query.setDeleteFlag(1);
        List<User> userList = userDao.findList(query);
        if (!CollectionUtils.isEmpty(userList)) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, "手机号码已注册");
        }
        // 密码加密
        user.setPassword(MD5Helper.GetMD5Code(user.getPassword()));
        // 初次注册 用户为注册用户
        Integer userType = UserTypeEnum.registeredUser.getUserType();
        user.setUserType(userType);
        user.setPasswordStatus(2);
        String registerRoleId = "2";
        int count = insertUserRoleProgram(user, registerRoleId);
        if (count > 0) {
            Current current = RequestContext.getCurrent();
            String token = current.getToken();
            current.setUser(user);
            // 查询权限
            findPermissions(current, user);
            Map<String, Object> map = ResultMapHelper.getSuccessMap();
            //清空计数
            redisTemplate.delete(user.getPhone());
            // 存入redis
            authCheckService.login(current.getDomain(), token, String.valueOf(user.getId()), JSON.toJSONString(current),
                    7200);
            Map<String, Object> obj = new HashMap<>(16);
            obj.put("user", current.getUser());
            obj.put("permissions", current.getPermissions());
            map.put(ResponseCode.MAP_DATAS, obj);
            return map;
        }
        //同一手机号注册提交超过3次开启验证码
        errorCount = errorCount + 1;
        redisTemplate.opsForValue().set(user.getPhone(), errorCount, 8, TimeUnit.HOURS);

        return ResultMapHelper.getResult(count);
    }

    private int insertUserRoleProgram(User user, String roleId) {
        // 存入数据库
        Map<String, Object> map = userService.insert(user);
        // 授权
        int count = 0;
        if (ResultMapHelper.isSuccess(map)) {
            Long userId = (Long) map.get(ResponseCode.MAP_DATAS);
            UserRoleProgram urp = new UserRoleProgram();
            urp.setUserId(userId);
            urp.setRoleId(Long.valueOf(roleId));
            urp.setProgramId(Long.valueOf(programId));
            urp.setStatus(1);
            UserRoleProgram[] userRolePrograms = {urp};
            Map<String, Object> resultMap = userRoleProgramService.insertList(Arrays.asList(userRolePrograms));
            if (resultMap.get(ResponseCode.CODE).equals(ResponseCode.SUCCESS_CODE)) {
                count = 1;
            }
        }
        return count;
    }

    private boolean isStopPlatformOperation() {
        // 从REDIS中获取字典map
        HashOperations<String, String, String> operations = redisTemplate.opsForHash();
        Map<String, String> dictMap = operations.entries(RedisKeyType.SYSTEM_DICT_ALL.getPrefix());
        if (dictMap.isEmpty()) {
            R<Map<String, String>> result = systemClient.getAllDictByCode();
            if ("0".equals(result.getCode())) {
                dictMap = result.getDatas();
                if (!dictMap.isEmpty()) {
                    //1代表开启该接口，2代表关闭该接口
                    String value = dictMap.get("isStopPlatformOperation");
                    return "2".equals(value);
                }
            }
        } else {
            String value = String.valueOf(dictMap.get("isStopPlatformOperation"));
            return "2".equals(value);
        }
        return false;
    }


    @Override
    public Map<String, Object> forgetPassword(User user, String code, String imgCode, String type) {

        User query = new User();
        query.setPhone(user.getPhone());
        List<User> userList = userDao.findList(query);
        if (CollectionUtils.isEmpty(userList)) {
            return ResultMapHelper.getResultMap(ResponseCode.PHONE_ISNOTEXIST_CODE, ResponseCode.PHONE_ISNOTEXIST_MSG);
        }
        //同一手机号注册提交超过3次开启验证码,防止恶意注册，暴力破解短信验证码
        Map<String, Object> isOpenImgMap = new HashMap<>(16);
        isOpenImgMap.put("isOpenImageCode", false);
        Integer errorCount = (Integer) redisTemplate.opsForValue().get(user.getPhone());
        if (null == errorCount) {
            errorCount = 0;
        } else if (errorCount > 2) {
            isOpenImgMap.put("isOpenImageCode", true);
            Map<String, Object> imgValidate = validImageCode(imgCode, type);
            if (!imgValidate.get(ResponseCode.CODE).equals(ResponseCode.SUCCESS_CODE)) {
                imgValidate.put(ResponseCode.MAP_DATAS, isOpenImgMap);
                return imgValidate;
            }
        }

        // 验证参数
        if (StringUtils.isBlank(user.getPhone()) || StringUtils.isBlank(user.getPassword())) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }

        // 验证code
        Map<String, Object> validate = smsClient.validate(user.getPhone(), code);

        if (!validate.get(ResponseCode.CODE).equals(ResponseCode.SUCCESS_CODE)) {
            errorCount = errorCount + 1;
            redisTemplate.opsForValue().set(user.getPhone(), errorCount, 8, TimeUnit.HOURS);
            if (3 == errorCount) {
                isOpenImgMap.put("isOpenImageCode", true);
                validate.put(ResponseCode.MAP_DATAS, isOpenImgMap);
            }
            return validate;
        }
        user.setPassword(MD5Helper.GetMD5Code(user.getPassword()));
        user.setPasswordStatus(2);

        //清空计数
        redisTemplate.delete(user.getPhone());
        return userService.updateByPhone(user);
    }

    @Override
    public void getImageCode(String type) {
        HttpServletResponse response = RequestContext.getCurrent().getResponse();
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 生成随机字串
        String verifyCode = VerifyCodeHelper.generateVerifyCode(4);
        log.info("verifyCode---------getImageCode------------>" + verifyCode);
        // 存入会话session
        String tokenSessionId = RequestContext.getCurrent().getToken();
        redisTemplate.opsForValue().set("IMAGECODE_" + tokenSessionId + type, verifyCode.toLowerCase(), 5, TimeUnit.MINUTES);
        // 生成图片
        int w = 100, h = 30;
        try {
            OutputStream out = response.getOutputStream();
            VerifyCodeHelper.outputImage(w, h, out, verifyCode);
        } catch (Exception e) {
            StringBuilder serviceLog = RequestContext.getCurrent().getServiceLog();
            serviceLog.append("/t").append("验证码生成失败");
        }
    }

    @Override
    public void getImageCodeV2(String type) {
        HttpServletResponse response = RequestContext.getCurrent().getResponse();
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        // 生成验证码
        String capText = captchaProducerMath.createText();
        String capStr = capText.substring(0, capText.lastIndexOf("@"));
        String code = capText.substring(capText.lastIndexOf("@") + 1);
        BufferedImage image = captchaProducerMath.createImage(capStr);
        // 存入会话session
        String tokenSessionId = RequestContext.getCurrent().getToken();
        redisTemplate.opsForValue().set("IMAGECODE_" + tokenSessionId + type, code, 5, TimeUnit.MINUTES);
        // 转换流信息写出
        try {
            OutputStream out = response.getOutputStream();
            ImageIO.write(image, "jpg", out);
        } catch (IOException e) {
            StringBuilder serviceLog = RequestContext.getCurrent().getServiceLog();
            serviceLog.append("/t").append("验证码生成失败");
        }

    }


    private Map<String, Object> validImageCode(String code, String type) {
        String tokenSessionId = RequestContext.getCurrent().getToken();
        Map<String, Object> isOpenImgMap = new HashMap<>(16);
        isOpenImgMap.put("isOpenImageCode", true);
        String validCodeKey = "IMAGECODE_" + tokenSessionId + type;
        Object verCode = redisTemplate.opsForValue().get(validCodeKey);
        redisTemplate.delete(validCodeKey);
        if (null == verCode) {
            return ResultMapHelper.getLoginResultMap(ResponseCode.CUSTOM_CODE, "图形验证码已失效，请重新刷新验证码", isOpenImgMap);
        }
        String verCodeStr = verCode.toString();
        if (verCodeStr == null || code == null || code.isEmpty() || !verCodeStr.equalsIgnoreCase(code)) {

            return ResultMapHelper.getLoginResultMap(ResponseCode.CUSTOM_CODE, "图形验证码错误", isOpenImgMap);
        } else {
            return ResultMapHelper.getSuccessMap();
        }
    }

    @Override
    public Map<String, Object> getUserInformation() {
        User user = new User();
        user.setId(RequestContext.getCurrent().getUser().getId());
        Current current = RequestContext.getCurrent();
        String token = current.getToken();
        user.setUserId(null);
        List<User> userNewInfoList = userDao.findList(user);
        if (CollectionUtils.isEmpty(userNewInfoList)) {
            return ResultMapHelper.failed();
        }
        current.setUser(userNewInfoList.get(0));
        // 查询权限
        findPermissions(current, user);

        Map<String, Object> map = ResultMapHelper.getSuccessMap();
        Map<String, Object> obj = new HashMap<>(16);
        obj.put("user", current.getUser());
        obj.put("permissions", current.getPermissions());
        getEnterpriseId(userNewInfoList.get(0), obj, current);

        // 存入redis
        authCheckService.setData(current.getDomain(), token, JSON.toJSONString(current));
        map.put(ResponseCode.MAP_DATAS, obj);
        return map;
    }



/**
     * @param phone 手机号
     * @param code  图形验证码
     * @param flag  1：注册/修改接口 2：忘记密码接口
     * @return 验证结果
     */

    @Override
    public Map<String, Object> validImageCodeAndPhone(String phone, String code, String flag, String type) {
        String tokenSessionId = RequestContext.getCurrent().getToken();
        String validCodeKey = "IMAGECODE_" + tokenSessionId + type;
        Object verCode = redisTemplate.opsForValue().get(validCodeKey);
        if (null == verCode) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, "图形验证码已失效，请重新刷新验证码");
        }
        redisTemplate.delete(validCodeKey);
        String verCodeStr = verCode.toString();
        if (verCodeStr == null || code == null || code.isEmpty() || !verCodeStr.equalsIgnoreCase(code)) {

            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, "图形验证码错误");
        } else {
            // "0"代表手机号存在 “7”代表手机号不存在
            int count = userService.isExistTelphone(phone);
            if ("1".equals(flag)) {
                if (count > 0) {
                    return ResultMapHelper.getResultMap(ResponseCode.PHONE_ISNOTEXIST_CODE, "手机号码已存在");
                } else {
                    return smsClient.send(phone);
                }
            } else if ("2".equals(flag)) {
                if (count > 0) {
                    return smsClient.send(phone);
                } else {
                    return ResultMapHelper.getResultMap(ResponseCode.PHONE_ISNOTEXIST_CODE,
                            ResponseCode.PHONE_ISNOTEXIST_MSG);
                }
            } else {
                return ResultMapHelper.getErrorMap();
            }
        }
    }
}
