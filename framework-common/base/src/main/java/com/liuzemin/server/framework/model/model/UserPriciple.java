package com.liuzemin.server.framework.model.model;

import io.swagger.annotations.ApiModelProperty;

public class UserPriciple extends BaseModel  {

    private static final long serialVersionUID = -8330298842079308477L;

    @ApiModelProperty(value="用户ID 必填", required = false)
    private String userId;

    @ApiModelProperty(value="用户名称 登录必填，新增必填", required = false)
    private String username;

    @ApiModelProperty(value="用户密码，登录必填md5加密，新增必填", required = false)
    private String password;

    @ApiModelProperty(value="用户电话", required = false)
    private String phone;

    @ApiModelProperty(value="用户邮箱", required = false)
    private String mail;

    @ApiModelProperty(value="身份证号", required = false)
    private String idCardNO;

    @ApiModelProperty(value="是否修改密码，1：是，2：否")
    private Integer passwordStatus;

    @ApiModelProperty(value = "用户类型:1.供应商，2.需求方， 7.区域经销商，8.经销商推荐，11.项目部，12.中介公司，13.个人 ,14.注册用户")
    private Integer userType;

    private String createdUser;

    private String updatedUser;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getIdCardNO() {
        return idCardNO;
    }

    public void setIdCardNO(String idCardNO) {
        this.idCardNO = idCardNO;
    }



    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public String getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(String updatedUser) {
        this.updatedUser = updatedUser;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPasswordStatus() {
        return passwordStatus;
    }

    public void setPasswordStatus(Integer passwordStatus) {
        this.passwordStatus = passwordStatus;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}
