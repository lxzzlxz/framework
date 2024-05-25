package com.liuzemin.server.framework.model.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Subsidiary extends BaseModel {

    @ApiModelProperty(value = "认证用户id")
    private Long userId;

    @ApiModelProperty(value = "子分公司全称")
    private  String subsidiaryName;

    @ApiModelProperty(value = "管理员姓名")
    private  String administratorName;

    @ApiModelProperty(value = "手机号码(账号)")
    private String telephone;
}
