package com.liuzemin.server.framework.model.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class Project extends BaseModel {

    @ApiModelProperty(value = "项目编码")
    private String projectCode;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目描述")
    private String description;

    @ApiModelProperty(value = "项目地址")
    private String address;

    @ApiModelProperty(value = "项目地址(导出)")
    private String projectAddress;

    @ApiModelProperty(value = "项目状态", example = "1-进行中；2-已关闭；")
    private Integer projectStatus;

    @ApiModelProperty(value = "项目状态")
    private String projectStatusName;

    @ApiModelProperty(value = "单位编码")
    private String depCode;

    @ApiModelProperty(value = "项目单位")
    private Long projectUnit;

    @ApiModelProperty(value = "项目单位projectUnits")
    private List<Long> projectUnits;

    @ApiModelProperty(value = "项目单位名称(子公司)")
    private String projectUnitDesc;

    @ApiModelProperty(value = "用户类型")
    private Integer userType;

    @ApiModelProperty(value = "管理员id")
    private Long userId;

    @ApiModelProperty(value = "项目ids")
    private List<Long> ids;

    @ApiModelProperty(value = "項目管理员id")
    private Long projectAdminId;

    @ApiModelProperty(value = "管理员姓名")
    private String userName;

    @ApiModelProperty(value = "管理员电话")
    private String phone;

    @ApiModelProperty(value = "管理员性别")
    private Integer gender;

    @ApiModelProperty(value = "管理员身份证号")
    private String idCardNo;

    @ApiModelProperty(value = "管理员分配时间")
    private Date adminCreationDate;

    @ApiModelProperty(value = "省编码 必填")
    private String provinceCode;

    @ApiModelProperty(value = "市编码 必填")
    private String cityCode;

    @ApiModelProperty(value = "区县编码 必填")
    private String countyCode;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "区县")
    private String county;
    @ApiModelProperty(value = "管理员ids")
    private List<Long> userIds;

    @ApiModelProperty(value = "查询开始时间")
    private String startDate;
    @ApiModelProperty(value = "查询结束时间")
    private String endDate;
    @ApiModelProperty(value = "时间格式")
    private String dateFormatStr;
    @ApiModelProperty("集团公司名称")
    private String companyName;

    @ApiModelProperty("信用分数")
    private Float scoreDev;
    private Float scoreDevStart;
    private Float scoreDevEnd;
    private Float scoreIncr;
}
