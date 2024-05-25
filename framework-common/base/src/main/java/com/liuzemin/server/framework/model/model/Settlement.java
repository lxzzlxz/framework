package com.liuzemin.server.framework.model.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 租赁结算对象 crec_settlement
 *
 * @author ruoyi
 * @date 2023-10-25
 */
@Data
public class Settlement {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 不含税总金额
     */
    private String amountExcludingTax;

    /**
     * 含税总金额
     */
    private String amountIncludingTax;

    /**
     * 合同编号
     */
    private String contractNum;

    /**
     * 累计金额
     */
    private String cumulativeAmount;

    /**
     * 扣款金额
     */
    private String deductionAmount;

    /**
     * 公司名称
     */
    private String deptName;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date initiationDate;

    /**
     * 供应商名称
     */
    private String leaseFactory;

    /**
     * 项目简称
     */
    private String projectAbbreviation;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 结算金额
     */
    private String settlementAmount;

    /**
     * 结算id
     */
    private String settlementId;

    /**
     * 结算发起人
     */
    private String settlementInitiator;

    /**
     * 税金
     */
    private String taxAmount;

    /**
     * 税率
     */
    private String taxRate;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    private List<SettleDevice> deviceList;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("amountExcludingTax", getAmountExcludingTax())
                .append("amountIncludingTax", getAmountIncludingTax())
                .append("contractNum", getContractNum())
                .append("cumulativeAmount", getCumulativeAmount())
                .append("deductionAmount", getDeductionAmount())
                .append("deptName", getDeptName())
                .append("initiationDate", getInitiationDate())
                .append("leaseFactory", getLeaseFactory())
                .append("projectAbbreviation", getProjectAbbreviation())
                .append("projectName", getProjectName())
                .append("settlementAmount", getSettlementAmount())
                .append("settlementId", getSettlementId())
                .append("settlementInitiator", getSettlementInitiator())
                .append("taxAmount", getTaxAmount())
                .append("taxRate", getTaxRate())
                .append("createDate", getCreateDate())
                .toString();
    }
}
