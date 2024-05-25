package com.liuzemin.server.framework.model.model;


import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 合同设备对象 crec_contract_device
 *
 * @date 2023-10-25
 */

@Data
public class ContractDevice {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 合同id
     */
    private Long contractId;
    /**
     * 设备id
     */
    private Long contractDeviceId;

    /**
     * 设备型号
     */
    private String deviceModel;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 需求编号
     */
    private String demandCode;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("contractDeviceId", getContractDeviceId())
                .append("deviceModel", getDeviceModel())
                .append("deviceName", getDeviceName())
                .append("demandCode", getDemandCode())
                .toString();
    }
}
