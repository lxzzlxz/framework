package com.liuzemin.server.framework.security.user.vo;


import lombok.Data;

@Data
public class UserExportExcelVo {
    private String companyName;
    private String username;
    private String idCardNo;
    private String gender;
    private String idCardNumber;
    private String phone;
    private String email;
    private String createdBy;
    private String creationDate;
    private String userType;
}
