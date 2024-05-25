package com.liuzemin.server.framework.model.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class ProgramItem extends BaseModel {

    private static final long serialVersionUID = 3863120443520541921L;

    @ApiModelProperty(value = "数据范围ID 必填", required = false)
    private Long programId;

    @ApiModelProperty(value = "纬度Code 必填", required = false)
    private String dimensionCode;

    @ApiModelProperty(value = "维度值 必填", required = false)
    private String dimensionValue;
    private List<String> dimensionValueList;

    @ApiModelProperty(value = "是否全选 必填", required = false)
    private Boolean isAll;

    private List<Long> programIds;

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public String getDimensionCode() {
        return dimensionCode;
    }

    public void setDimensionCode(String dimensionCode) {
        this.dimensionCode = dimensionCode;
    }

    public String getDimensionValue() {
        return dimensionValue;
    }

    public List<String> getDimensionValueList() {
        return dimensionValueList;
    }

    public void setDimensionValueList(List<String> dimensionValueList) {
        this.dimensionValueList = dimensionValueList;
    }

    public void setDimensionValue(String dimensionValue) {
        this.dimensionValue = dimensionValue;
    }

    public Boolean getAll() {
        return isAll;
    }

    public void setAll(Boolean all) {
        isAll = all;
    }

    public List<Long> getProgramIds() {
        return programIds;
    }

    public void setProgramIds(List<Long> programIds) {
        this.programIds = programIds;
    }
}
