package com.liuzemin.server.framework.security.user.controller;

import com.liuzemin.server.framework.datasource.page.Page;
import com.liuzemin.server.framework.datasource.page.PagedResult;
import com.liuzemin.server.framework.model.annotation.Operation;
import com.liuzemin.server.framework.model.annotation.Resource;
import com.liuzemin.server.framework.model.constant.ResponseCode;
import com.liuzemin.server.framework.model.constant.UserTypeEnum;
import com.liuzemin.server.framework.model.context.RequestContext;
import com.liuzemin.server.framework.model.helper.MD5Helper;
import com.liuzemin.server.framework.model.helper.RHelper;
import com.liuzemin.server.framework.model.helper.ResultMapHelper;
import com.liuzemin.server.framework.model.model.R;
import com.liuzemin.server.framework.model.model.Supplier;
import com.liuzemin.server.framework.model.model.User;
import com.liuzemin.server.framework.model.model.UserPriciple;
import com.liuzemin.server.framework.model.utils.DateUtils;
import com.liuzemin.server.framework.model.utils.ExportExcelUtil;
import com.liuzemin.server.framework.security.feign.SocialDemandFeignClient;
import com.liuzemin.server.framework.security.feign.SupplierFeignClient;
import com.liuzemin.server.framework.security.permission.service.IUserRoleProgramService;
import com.liuzemin.server.framework.security.user.service.IUserService;
import com.liuzemin.server.framework.security.user.vo.UserExportExcelVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.util.*;
import java.util.stream.Collectors;


/**
 * The type User controller.
 */
@RestController
@RequestMapping("/user/v1")
@Resource(value = "user", desc = "用户管理")
@Api(tags = "用户管理")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private SupplierFeignClient supplierFeignClient;

    @Autowired
    private SocialDemandFeignClient socialDemandFeignClient;

    @Autowired
    private IUserRoleProgramService userRoleProgramService;

    public static final Logger log = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/findPagedList/{pageSize}/{curPage}", method = RequestMethod.GET)
    @Operation(value = "find", desc = "后台查询")
    @ApiOperation("后台 分页查询")
    public PagedResult<User> page(@ModelAttribute User user, @PathParam("") Page page) {
        user.setDeleteFlag(1);
        return userService.findPagedList(user, page);
    }

    @RequestMapping(value = "/findList", method = RequestMethod.GET)
    @ApiOperation("查询")
    @Operation(value = "find", desc = "后台查询")
    public Map<String, Object> list(User user) {
        user.setDeleteFlag(1);
        return ResultMapHelper.getSuccessMap(userService.findList(user));
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @Operation(value = "add", desc = "后台新增")
    @ApiOperation("新增")
    public Map<String, Object> insert(@RequestBody User user) {
        if (null == user) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        if (null == user.getUserType()) {
            user.setUserType(2);
        }
        if (!StringUtils.isEmpty(user.getPassword())) {
            user.setPassword(MD5Helper.GetMD5Code(user.getPassword()));
        }
        return userService.insert(user);
    }

    @RequestMapping(value = "/insertList", method = RequestMethod.POST)
    @Operation(value = "add", desc = "后台新增")
    @ApiOperation("批量新增")
    public Map<String, Object> insert(@RequestBody List<User> users) {
        if (CollectionUtils.isEmpty(users)) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        for (User user : users) {
            user.setUserType(1);
        }
        return userService.insertList(users);
    }

    @RequestMapping(value = "/update_other_user", method = RequestMethod.POST)
    @Operation(value = "update", desc = "后台更新")
    @ApiOperation("后台管理更新其他用户信息")
    public Map<String, Object> updateOtherUser(@RequestBody User user) {

        return userService.updateOtherUser(user);
    }

    @RequestMapping(value = "/update_demander", method = RequestMethod.POST)
    @ApiOperation("前台管理员更新下级需求方的手机号和邮箱")
    public R<String> updateDemander(@RequestBody User user) {

        return userService.updateDemander(user);
    }

    @RequestMapping(value = "/updateList", method = RequestMethod.POST)
    @Operation(value = "update", desc = "后台更新")
    @ApiOperation("批量更新")
    public Map<String, Object> updateList(@RequestBody List<User> users) {

        return userService.updateList(users);
    }


    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @Operation(value = "delete", desc = "后台删除")
    @ApiOperation("删除")
    public Map<String, Object> delete(@RequestParam("id") Long id) {

        return userService.delete(id);
    }


    @RequestMapping(value = "/deleteList", method = RequestMethod.POST)
    @Operation(value = "delete", desc = "后台删除")
    @ApiOperation("批量删除")
    public Map<String, Object> delete(@RequestBody List<Long> ids) {

        return userService.deleteList(ids);
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ApiOperation("查询")
    @Operation(value = "findOwn", desc = "用户端查询")
    public Map<String, Object> getInfo() {
        Long id = RequestContext.getCurrent().getUser().getId();
        if (null == id) {
            return ResultMapHelper.getNotLoginMap();
        }
        User user = new User();
        user.setId(id);
        user.setDeleteFlag(1);
        return ResultMapHelper.getSuccessMap(userService.findList(user));
    }

    @RequestMapping(value = "/first_approver_list", method = RequestMethod.GET)
    @ApiOperation("查询")
    public Map<String, Object> findFirstApproverList(@RequestParam(value = "roleName", defaultValue = "供应商初级审核员") String roleName) {

        return ResultMapHelper.getSuccessMap(userService.findFirstApproverList(roleName));
    }

    @RequestMapping(value = "/findListByUserIds", method = RequestMethod.POST)
    @ApiOperation("查询")
    public Map<String, Object> findListByUserIds(@RequestBody User user) {
        user.setDeleteFlag(1);
        return ResultMapHelper.getSuccessMap(userService.findList(user));
    }

    @RequestMapping(value = "/statistics_user", method = RequestMethod.GET)
    @ApiOperation("后台查询注册用户数量")
    public R<List<User>> queryRegisterUserNum() {

        return userService.queryRegisterUserNum();
    }


    @RequestMapping(value = "/updateAndInsert", method = RequestMethod.POST)
    @ApiOperation("更新和删除")
    public Map<String, Object> updateAndInsert(@RequestBody User user) {

        return userService.updateAndInsert(user);
    }

    @RequestMapping(value = "/deleteByService", method = RequestMethod.DELETE)
    @ApiOperation("删除， 后台服务调用，前台没有权限调用")
    public Map<String, Object> deleteByService(@RequestParam("id") Long id) {

        return userService.delete(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @Operation(value = "updateOwn", desc = "用户端更新用户")
    @ApiOperation("更新用户信息")
    public Map<String, Object> update(@RequestBody User user) {

        return userService.update(user);

    }


    /**
     * 验证码验证和用户修改
     *
     * @param user
     * @param otherPhone
     * @param code
     * @return
     */
    @RequestMapping(value = "/updateUserAndValidate", method = RequestMethod.POST)
    @Operation(value = "updateOwn", desc = "用户端更新用户")
    @ApiOperation("验证码验证后，修改用户手机号")
    public Map<String, Object> updateUserAndValidate(@RequestBody User user, @RequestParam("otherPhone") String otherPhone,
                                                     @RequestParam("code") String code, @RequestParam("imageCode") String imageCode, @RequestParam("type") String type) {
        UserPriciple curUser = RequestContext.getCurrent().getUser();
        user.setId(curUser.getId());
        return userService.updateUserAndValidate(user, otherPhone, code, imageCode, type);
    }

    @RequestMapping(value = "/updateUserPassword", method = RequestMethod.POST)
    @ApiOperation("修改用户密码(不需要手机验证码)")
    @Operation(value = "updateOwn", desc = "用户端更新用户")
    public Map<String, Object> updateUserPassword(@RequestBody User user) {
        user.setPasswordStatus(2);
        return userService.updateUserPassword(user);
    }

    /**
     * 个人实名认证
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/authUser", method = RequestMethod.POST)
    @ApiOperation("个人实名认证")
    public Map<String, Object> authUser(@RequestBody User user) {

        return userService.authUser(user);
    }

    @RequestMapping(value = "/getEnterpriseName", method = RequestMethod.POST)
    @ApiOperation("根据用户类型和id查询名称")
    public R<Map<Integer, Map<Long, String>>> getEnterpriseNameByUserType(@RequestBody Map<Integer, List<Long>> typeMap) {

        return RHelper.getSuccessR(userService.getEnterpriseNameByUserType(typeMap));
    }


    /**
     * Export users.
     *
     * @param user
     * @param req  the req
     */
    @RequestMapping("/export")
    @Operation(value = "export", desc = "后台导出用户列表")
    public void exportUsers(User user, HttpServletResponse req) {
        //1 表头信息 ：公司名称、用户名、姓名、性别、证件号码、手机号码、邮箱、用户类型、创建人、创建时间、备注
        String[] headers = {"手机号码#phone", "用户名#username", "性别#gender", "证件号码#idCardNo"
                , "邮箱#email", "公司名称#companyName", "用户类型#userType", "创建人#createdBy", "创建时间#creationDate"};
        //获取用户列表
        user.setDeleteFlag(1);
        List<User> list = userService.findList(user);
        //用户类型
        // 0.注册未认证供应商 3 注册未认证社会需求方  	没有公司
        // 1.提交认证供应商 		根据在crec_supplier_t 中 关联user_ID查询 name 就是公司名称
        // 2. 战略需求方   // 根据tpl_user_program_t 查询progtam_id 其中role_id = 1010 根据tpl_program 获取name
        // 4 提交认证社会需求方 // 根据crec_social_demand_admin 关联userId n拿到id 通过id 在social_demands_t 中拿到name
        Map<Long, String> userIdCompanyNameMap = new HashMap<>(16);
        //供应商类型 用户的userId 和companyName
        R<List<Supplier>> suppliersResult = supplierFeignClient.getAll();
        if (RHelper.isSuccessR(suppliersResult)) {
            List<Supplier> suppliers = suppliersResult.getDatas();
            Map<Long, String> suplierUserIdCompanyNameMap = suppliers.stream().collect(Collectors.toMap(Supplier::getUserId, Supplier::getName, (k1, k2) -> k1));
            userIdCompanyNameMap.putAll(suplierUserIdCompanyNameMap);
        }
        //社会需求方 用户的userId 和companyName
        R<Map<Long, String>> socialDemandUserIdCompanyMap = socialDemandFeignClient.getAllSocialDemandUserIdCompanyMap();
        if (RHelper.isSuccessR(socialDemandUserIdCompanyMap)) {
            Map<Long, String> datas = socialDemandUserIdCompanyMap.getDatas();
            userIdCompanyNameMap.putAll(datas);
        }
        //战略需求方
        Map<Long, String> demandUserIdCompanyNameMap = userRoleProgramService.getAllDemandUserIdCompanyMap();
        userIdCompanyNameMap.putAll(demandUserIdCompanyNameMap);
        // 表头信息 ：公司名称、用户名、姓名、性别、身份证号码、手机号码、邮箱、用户角色、创建人、创建时间
        Map<Long, String> userIdNameMap = list.stream().filter(us ->
                us.getId() != null && us.getUsername() != null
        ).collect(Collectors.toMap(User::getId, User::getUsername));
        List<UserExportExcelVo> userExportExcelVos = new ArrayList<>();
        list.forEach(l -> {
            UserExportExcelVo userExportExcelVo = new UserExportExcelVo();
            String username = l.getUsername();
            Integer gender = l.getGender();
            String idCardNumber = l.getIdCardNO();
            String phone = l.getPhone();
            String email = l.getMail();
            Long createdBy = l.getCreatedBy();
            Date creationDate = l.getCreationDate();
            String createUserName = userIdNameMap.get(createdBy);
            Integer userType = l.getUserType();
            userExportExcelVo.setUsername(username);
            userExportExcelVo.setIdCardNo(idCardNumber);
            userExportExcelVo.setGender(null == gender ? "未知" : gender == 1 ? "男" : "女");

            String userName = UserTypeEnum.getUserName(userType);
            userExportExcelVo.setUserType(userName);
            userExportExcelVo.setPhone(phone);
            userExportExcelVo.setEmail(email);
            userExportExcelVo.setCreationDate(DateUtils.dateToString(creationDate));
            userExportExcelVo.setCreatedBy(createUserName);
            userExportExcelVo.setCompanyName(userIdCompanyNameMap.get(l.getId()));
            userExportExcelVos.add(userExportExcelVo);
        });
        try {
            ExportExcelUtil.export(req, "用户信息表", headers, userExportExcelVos);
        } catch (Exception e) {
            log.error("导出文件报错");
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/updateUserType", method = RequestMethod.POST)
    @ApiOperation("更新用户类型")
    public R<Integer> updateUserType(@RequestBody User user) {
        return RHelper.getSuccessR(userService.updateById(user));
    }

    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    @ApiOperation("获取用户信息")
    public R<User> getUserInfo(Long id){
        return RHelper.getSuccessR(userService.queryById(id));
    }
}
