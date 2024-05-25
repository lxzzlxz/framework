package com.liuzemin.server.framework.security.user.util;

public enum UserTypeEnum {
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
    platformAdmin("平台管理员",10);

    private String userTypeName;
    private Integer userType;

    UserTypeEnum(String userTypeName, Integer userType) {
        this.userTypeName = userTypeName;
        this.userType = userType;
    }

    public static String getUserName(Integer userType) {
        for (UserTypeEnum c : UserTypeEnum.values()) {
            if (c.getUserType() == userType) {
                return c.userTypeName;
            }
        }
        return null;
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public Integer getUserType() {
        return userType;
    }
}
