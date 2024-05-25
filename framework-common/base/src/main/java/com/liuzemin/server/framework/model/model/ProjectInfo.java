package com.liuzemin.server.framework.model.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProjectInfo extends BaseModel {

    private static final long serialVersionUID = -7171283299414307079L;

    @ApiModelProperty("单位名称")
    private String name;

    @ApiModelProperty("父级Id")
    private Long parentId;

    private List<Long> parentIds;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("是否分配（0否 1是）")
    private String isAllocate;

    //原来sql中name的别名
    @ApiModelProperty("单位名称")
    private String value;

    @ApiModelProperty(value = "企业类别:1需求方，2区域经销商,3二级经销商，4供应商，6劳务分包商")
    private Integer enterpriseCategory;

    @ApiModelProperty(value = "企业id")
    private Long enterpriseId;
    private List<Long> enterpriseIds;

    //for query
    @ApiModelProperty("子集")
    private List<ProjectInfoVO> childrenVO;
    @ApiModelProperty("实体子集")
    private List<ProjectInfo> children;
    @ApiModelProperty("id集合")
    private List<Long> proIds;

    @ApiModelProperty(value = "查询开始时间")
    private String startDate;
    @ApiModelProperty(value = "查询结束时间")
    private String endDate;
    @ApiModelProperty(value = "时间格式")
    private String dateFormatStr;
}
