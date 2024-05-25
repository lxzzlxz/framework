package com.liuzemin.server.framework.model.model;

import io.swagger.annotations.ApiModelProperty;

public class SupplierApprove extends BaseModel {

    private static final long serialVersionUID = 1274100241959858416L;

    @ApiModelProperty("供应商ID。 启动流程字段，必填")
    private Long supplierId;

    @ApiModelProperty("审批人。 启动流程字段，必填")
    private Long approverId;

    @ApiModelProperty("审批结果，1：同意，2：拒绝。 审批必填")
    private Integer approveResult;

    @ApiModelProperty("原因， 审批字段")
    private String reason;

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getApproverId() {
        return approverId;
    }

    public void setApproverId(Long approverId) {
        this.approverId = approverId;
    }

    public Integer getApproveResult() {
        return approveResult;
    }

    public void setApproveResult(Integer approveResult) {
        this.approveResult = approveResult;
    }
}
