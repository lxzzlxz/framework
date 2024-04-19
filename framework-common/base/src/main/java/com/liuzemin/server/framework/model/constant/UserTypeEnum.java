package com.liuzemin.server.framework.model.constant;

import java.util.Arrays;

public enum UserTypeEnum {
    //1.供应商，2.需求方， 7.区域经销商，8.经销商推荐，11.项目部，12.中介公司，13.个人，14注册用户, 16.回收商
    notAuthSupplier("未提交认证供应商", 0),
    supplier("已提交认证供应商", 1),
    strategicDemander("已提交认证战略需求方", 2),
    notAuthSocialDemander("未提交认证社会需求方", 3),
    socialDemander("已提交认证社会需求方", 4),
    notAuthStrategicDemander("未提交认证战略需求方", 5),
    notAuthRegionalDistributors("未提交认证区域经销商", 6),
    regionalDistributors("已提交认证区域经销商", 7),
    secondaryDistributor("需求方（经销商推荐）", 8),
    stockCompany("股份公司管理员", 9),
    platformAdmin("平台管理员", 10),
    projectAdmin("项目部", 11),
    intermediaryCompany("中介公司", 12),
    individual("个人", 13),
    registeredUser("注册用户", 14),
    notAuthRecovery("未提交认证回收商", 15),
    recovery("已提交认证回收商", 16),
    notAuthOtherDemand("未提交认证其他企业",17),
    otherDemand("已提交认证其他企业",18);


    private String userTypeName;
    private Integer userType;

    UserTypeEnum(String userTypeName, Integer userType) {
        this.userTypeName = userTypeName;
        this.userType = userType;
    }

    public static String getUserName(Integer userType) {
        return Arrays.stream(UserTypeEnum.values()).filter(c -> c.getUserType().equals(userType)).findFirst().map(c -> c.userTypeName).orElse(null);
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public Integer getUserType() {
        return userType;
    }
}
