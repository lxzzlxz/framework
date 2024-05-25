package com.liuzemin.server.framework.security.program.model;

import com.liuzemin.server.framework.model.model.BaseModel;
import com.liuzemin.server.framework.model.model.ProgramItem;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class Program extends BaseModel {

    private static final long serialVersionUID = -2444799895665986800L;

    @ApiModelProperty(value="数据范围名称 必填", required = false)
    private String programName;

    private String scope;

    @ApiModelProperty(value="状态，1：启用，2：锁定 必填", required = false)
    private Integer status;

    @ApiModelProperty(value="类型，1.一级单位")
    private Integer type;

    private List<Long> programIds;

    @ApiModelProperty(value="数据范围详情 必填 可以为空数组", required = false)
    private List<ProgramItem> programItems;

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<Long> getProgramIds() {
        return programIds;
    }

    public void setProgramIds(List<Long> programIds) {
        this.programIds = programIds;
    }

    public List<ProgramItem> getProgramItems() {
        return programItems;
    }

    public void setProgramItems(List<ProgramItem> programItems) {
        this.programItems = programItems;
    }
}
