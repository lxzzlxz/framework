package com.liuzemin.server.framework.model.model;

import com.liuzemin.server.framework.model.web.transfer.TransferObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(value = "项目公司返回详情对象")
public class ProjectInfoVO extends TransferObject<ProjectInfo> {
	
	private static final long serialVersionUID = 7330119303509460823L;

	@ApiModelProperty("id")
	private Long id;
	
	@ApiModelProperty("单位名称")
    private String name;
	    
    @ApiModelProperty("父级Id")
    private Long parentId;
    
    @ApiModelProperty("是否分配（0否 1是）")
	private String isAllocate;
    
    @ApiModelProperty("排序")
    private Integer sort;
    
    @ApiModelProperty("备注")
    private String remark;
	
    @ApiModelProperty("子集")
    private List<ProjectInfoVO> childrenVO;

    @ApiModelProperty(value = "企业类别:1需求方，2区域经销商,3二级经销商，4供应商")
    private Integer enterpriseCategory;

    @ApiModelProperty(value = "企业id")
    private Long enterpriseId;
    private List<Long> enterpriseIds;
}
