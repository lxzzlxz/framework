package com.liuzemin.server.framework.model.model;


import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 租赁合同文件对象 crec_contract_file
 *
 * @author ruoyi
 * @date 2023-10-25
 */

@Data
public class ContractFile {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 合同id
     */
    private Long contractId;

    /**
     * 文件地址
     */
    private String contractFile;

    /**
     * id
     */
    private Long contractFileId;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("contractFile", getContractFile())
                .append("contractFileId", getContractFileId())
                .toString();
    }
}
