package com.liuzemin.server.framework.model.constant;

public class ResponseCode {

    public static final String CODE = "code";

    public static final String MSG = "msg";

    public static final String DATA = "data";

    public static final String MAP_DATAS = "datas";

    /**
     * 成功
     */
    public static final String SUCCESS_CODE = "0";

    public static final String SUCCESS_MSG = "ok";
    /**
     * 失败
     */
    public static final String FAILED_CODE = "-1";

    public static final String FAILED_MSG = "failed";

    /**
     * session过期
     */
    public static final String NOT_LOGIN_CODE = "1";

    public static final String NOT_LOGIN_MSG = "请登录";

    /**
     * error
     */
    public static final String ERROR_CODE = "500";

    public static final String ERROR_MSG = "ERROR";

    /**
     * 用户自定义返回信息
     */
    public static final String CUSTOM_CODE = "2";

    public static final String PARAMETER_ERROR = "参数错误";

    /**
     * 没有权限
     */
    public static final String PERMISSION_DENIED_CODE = "3";

    public static final String PERMISSION_DENIED_MSG = "没有权限，请联系管理员申请权限";

    /**
     * 服務沒有權限
     */
    public static final String SERVICE_PERMISSION_DENIED_CODE = "4";

    public static final String SERVICE_PERMISSION_DENIED_MSG = "没有服务权限，请联系管理员";

    public static final String SERVICE_OLDPASSWORD_CODE = "5";

    public static final String SERVICE_OLDPASSWORD_MSG = "原密码输入错误，请重新输入";

    public static final String AUTH_SUPPLIER_CODE = "6";

    public static final String AUTH_SUPPLIER_MSG = "身份信息不一致，验证不通过";

    public static final String PHONE_ISNOTEXIST_CODE = "7";

    public static final String PHONE_ISNOTEXIST_MSG = "手机号码不存在";

    public static final String PAY_FAILED_CODE = "8";

    public static final String PAY_FAILED_MSG = "支付失败";

    public static final String PHONE_ISEXIST_CODE = "9";

    public static final String PHONE_ISEXIST_MSG = "手机号码已注册";

    // 支付业务提示
    public static final String PAYMENT_SUCCESS_CODE = "10001";

    public static final String PAYMENT_SUCCESS_MSG = "支付成功！";

    public static final String PAYMENT_WAIT_CODE = "10002";

    public static final String PAYMENT_WAIT_MSG = "支付等待！";

    public static final String SERVICE_ISFREE_CODE = "10003";

    public static final String SERVICE_ISFREE_MSG = "本次服务不收取费用！";

    public static final String PAYMENT_FAILED_CODE = "10004";

    public static final String PAYMENT_FAILED_MSG = "支付失败！";

    // 供应商提示
    public static final String SUPPLIER_ISNOTEXITS_CODE = "20001";

    public static final String SUPPLIER_ISNOTEXITS_MSG = "供应商不存在！";

    public static final String SUPPLIER_ISNOTAUDIT_CODE = "20002";

    public static final String SUPPLIER_ISNOTAUDIT_MSG = "供应商没有通过认证！";

    public static final String SUPPLIER_ISFIRSTAUDIT_CODE = "20003";

    public static final String SUPPLIER_ISFIRSTAUDIT_MSG = "供应商已通过初审认证！";

    public static final String SUPPLIER_ISFINALEXITS_CODE = "20004";

    public static final String SUPPLIER_ISFINALEXITS_MSG = "供应商已通过终审认证！";

    public static final String SUPPLIER_RESPOND_CODE = "20005";

    public static final String SUPPLIER_RESPOND_MSG = "响应失败！您已经响应该需求，不能重复响应！";

    // 系统提示
    public static final String SYSTEM_EVALUATE_CODE = "30001";

    public static final String SYSTEM_EVALUATE_MSG = "服务评价次数已满10次，不能继续评价";

    public static final String SYSTEM_AUTHTIMES_CODE = "30002";

    public static final String SYSTEM_AUTHTIMES_MSG = "实名认证次数以到上线，请联系管理员";

    //需求提示
    public static final String DEMANDER_ISEXIST_CODE = "40001";

    public static final String DEMANDER_ISEXIST_MSG = "管理员已存在";

    public static final String DEMANDE_PROJECTUNIT_CODE = "40002";

    public static final String DEMANDE_PROJECTUNIT_MSG = "隶属单位已分配";

    public static final String DEMANDERS_ISAUDIT_CODE = "40003";

    public static final String DEMANDERS_ISAUDIT_MSG = "社会需求方已认证";

    public static final String DEMANDERS_ISDEMANDERS_CODE = "40004";

    public static final String DEMANDERS_ISDEMANDERS_MSG = "该用户已经是社会需求方";

    public static final String DEMANDERS_ISNOTEXITS_CODE = "40001";

    public static final String DEMANDERS_ISNOTEXITS_MSG = "社会需求方不存在！";

    public static final String DEMANDERS_ISNOTAUDIT_CODE = "40002";

    public static final String DEMANDERS_ISNOTAUDIT_MSG = "社会需求方没有通过认证！";

    public static final String DEMANDERS_ISFIRSTAUDIT_CODE = "40003";

    public static final String DEMANDERS_ISFIRSTAUDIT_MSG = "社会需求方已通过初审认证！";

    public static final String DEMANDERS_ISFINALEXITS_CODE = "40004";

    public static final String DEMANDERS_ISFINALEXITS_MSG = "社会需求方已通过终审认证！";

    /**
     * 回收商提示
     */
    public static final String RECOVERY_ISNOTEXITS_CODE = "50001";

    public static final String RECOVERY_ISNOTEXITS_MSG = "回收商不存在！";

    public static final String RECOVERY_ISNOTAUDIT_CODE = "50002";

    public static final String RECOVERY_ISNOTAUDIT_MSG = "回收商没有通过认证！";

    public static final String RECOVERY_ISAUDIT_CODE = "50003";

    public static final String RECOVERY_ISAUDIT_MSG = "回收商已通过认证！";

    /**
     * 需求审核提示
     */
    public static final String RECOVERY_DEMAND_ISNOTEXITS_CODE = "60001";

    public static final String RECOVERY_DEMAND_ISNOTEXITS_MSG = "需求不存在！";

    public static final String RECOVERY_DEMAND_ISNOTAUDIT_CODE = "60002";

    public static final String RECOVERY_DEMAND_ISNOTAUDIT_MSG = "需求没有通过认证！";

    public static final String RECOVERY_DEMAND_ISAUDIT_CODE = "60003";

    public static final String RECOVERY_DEMAND_ISAUDIT_MSG = "需求已通过认证！";
}
