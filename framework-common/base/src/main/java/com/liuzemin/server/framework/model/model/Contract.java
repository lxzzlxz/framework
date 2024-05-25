package com.liuzemin.server.framework.model.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 租赁合同对象 crec_contract
 *
 * @author Scott_xin
 * @date 2023-10-25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Contract extends BaseModel {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 公司名称
     */
    private String deptName;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 项目简称
     */
    private String projectAbbreviation;

    /**
     * 租赁厂商名称
     */
    private String leaseFactory;

    /**
     * 纳税人识别号
     */
    private String taxpayerIdentificationNumber;

    /**
     * 合同编号
     */
    private String contractNumber;

    /**
     * 签订日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date signDate;

    /**
     * 租赁开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date leaseStartDate;

    /**
     * 租赁结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date leaseEndDate;

    /**
     * 合同金额
     */
    private String contractAmount;

    /**
     * 即时租赁ID
     */
    private Long realTimeLeasingId;

    /**
     * 创建日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    private List<ContractDevice> deviceList;
    private List<ContractFile> fileList;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("deptName", getDeptName())
                .append("projectName", getProjectName())
                .append("projectAbbreviation", getProjectAbbreviation())
                .append("leaseFactory", getLeaseFactory())
                .append("taxpayerIdentificationNumber", getTaxpayerIdentificationNumber())
                .append("contractNumber", getContractNumber())
                .append("signDate", getSignDate())
                .append("leaseStartDate", getLeaseStartDate())
                .append("leaseEndDate", getLeaseEndDate())
                .append("contractMount", getContractAmount())
                .append("realTimeLeasingId", getRealTimeLeasingId())
                .append("createDate", getCreateDate())
                .toString();
    }
}
