package com.liuzemin.server.framework.model.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class SystemMessage extends BaseModel {

    private static final long serialVersionUID = -7467625745324169402L;

    @ApiModelProperty(value = "标题 必填")
    private String subject;

    @ApiModelProperty(value = "类型，1：所有用户，2：选择用户 必填")
    private Integer category;

    @ApiModelProperty(value = "内容，必填")
    private String content;

    @ApiModelProperty(value = "选择用户id列表，选择用户时，必填")
    private List<Long> userIds;

    private List<UserPriciple> users;

    private String userStr;

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public List<UserPriciple> getUsers() {
        return users;
    }

    public void setUsers(List<UserPriciple> users) {
        this.users = users;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserStr() {
        return userStr;
    }

    public void setUserStr(String userStr) {
        this.userStr = userStr;
    }
}
