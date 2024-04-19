package com.liuzemin.server.framework.model.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class Merchanical extends BaseModel {

    private static final long serialVersionUID = 4029039389348569096L;

    @ApiModelProperty(value = "标题 必填", required = true)
    private String code;

    @ApiModelProperty(value = "标题 必填", required = true)
    private String subject;

    @ApiModelProperty(value = "描述 非必填")
    private String description;
//
//    @ApiModelProperty(value="缩略图片 必填")
//    private String headImage;
//
//    @ApiModelProperty(value="设备详情图片 必填")
//    private String detailImage;

    @ApiModelProperty(value = "整机照片 必填", required = true)
    private String wholeImage;

    @ApiModelProperty(value = "产品铭牌 必填", required = true)
    private String idLabelImage;

    @ApiModelProperty(value = "合格证照片")
    private String certificateImage;

    @ApiModelProperty(value = "背面照片")
    private String backImage;

    @ApiModelProperty(value = "侧面照片")
    private String sideImage;

    @ApiModelProperty(value = "设备类型ID 必填，条件查询是，null和-1都是所有")
    private Long categoryId;

    @ApiModelProperty(value = "设备类型 非必填")
    private String category;

    @ApiModelProperty(value = "父设备类型ID 非必填")
    private Long fatherCategoryId;

    @ApiModelProperty(value = "设备类型 非必填")
    private String fatherCategory;

    @ApiModelProperty(value = "品牌 非必填")
    private String brand;

    @ApiModelProperty(value = "型号 非必填")
    private String model;

    @ApiModelProperty(value = "使用状态 1.已租 2.待租，必填")
    private Integer usingStatus;

    @ApiModelProperty(value = "经度，非必填")
    private String longitude;

    @ApiModelProperty(value = "纬度，非必填")
    private String latitude;

    @ApiModelProperty(value = "省，必填", required = true)
    private String provinceCode;

    @ApiModelProperty(value = "省，非必填")
    private String province;

    @ApiModelProperty(value = "市，必填")
    private String cityCode;

    @ApiModelProperty(value = "市，非必填")
    private String city;

    @ApiModelProperty(value = "区县，必填")
    private String countyCode;

    @ApiModelProperty(value = "区县，非必填")
    private String county;

    @ApiModelProperty(value = "详细地址， 必填", required = true)
    private String address;

    @ApiModelProperty(value = "出厂日期， 必填", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date factoryDate;

    @ApiModelProperty(value = "购买日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date purchaseDate;

    @ApiModelProperty(value = "设备铭牌编号， 必填", required = true)
    private String nameplate;

//    @ApiModelProperty(value = "租赁价格1， 必填", required = true)
//    private String priceOne;
//
//    @ApiModelProperty(value = "租赁价格2， 必填", required = true)
//    private String priceTwo;
//
//    @ApiModelProperty(value = "租赁价格3， 必填", required = true)
//    private String priceThree;
//
//    @ApiModelProperty(value = "计价单位， 必填", required = true)
//    private String unitOne;
//
//    @ApiModelProperty(value = "计价单位， 必填", required = true)
//    private String unitTwo;
//
//    @ApiModelProperty(value = "计价单位， 必填", required = true)
//    private String unitThree;

    @ApiModelProperty(value = "联系电话， 必填", required = true)
    private String phone;

    @ApiModelProperty(value = "联系人， 必填", required = true)
    private String contact;

    @ApiModelProperty(value = "供应商信息", required = true)
    private Supplier supplier;

    @ApiModelProperty(value = "供应商ID，供应商添加设备时，必填")
    private Long supplierId;

    @ApiModelProperty(value = "规格单位")
    private String specificationUnit;

    @ApiModelProperty(value = "规格")
    private String specification;

    @ApiModelProperty(value = "其它照片1")
    private String otherImageOne;

    @ApiModelProperty(value = "其它照片2")
    private String otherImageTwo;

    @ApiModelProperty("设备识别码")
    private String equIdentCode;

    @ApiModelProperty("审批状态：1：待审批,2：审批通过，3：审批不通过 ")
    private Integer approveStatus;

    @ApiModelProperty("审批状态：1：待审批,2：审批通过，3：审批不通过 ")
    private String approveStatusName;

    @ApiModelProperty("审批时间")
    private Date approveDate;

    @ApiModelProperty("审核通过时间")
    private String approveTime;

    @ApiModelProperty("设备发布时间")
    private String publishDate;

    @ApiModelProperty("审批人")
    private Long approver;

    @ApiModelProperty("审批人姓名")
    private String approverName;

    //id集合
    private List<Long> merchanicalIds;

    @ApiModelProperty(value = "供应商名称")
    private String supplierName;

    @ApiModelProperty(value = "类型id集合")
    private List<Long> categoryIds;

    @ApiModelProperty("审批人id集合")
    private List<Long> approvers;

    @ApiModelProperty(value = "供应商ID集合")
    private List<Long> supplierIds;

    @ApiModelProperty("时间类型（1月2年）")
    private String dateType;

    @ApiModelProperty("开始时间")
    private String startDate;

    @ApiModelProperty("结束时间")
    private String endDate;

    @ApiModelProperty("时间格式")
    private String dateFormatStr;

    @ApiModelProperty("浏览次数")
    private Long viewsNumber;

    @ApiModelProperty("是否首页展示（1是2否）")
    private Integer homePage;

    @ApiModelProperty(value = "是否VIP：0否 1是")
    private Integer vip;

    private String isVip;
}
