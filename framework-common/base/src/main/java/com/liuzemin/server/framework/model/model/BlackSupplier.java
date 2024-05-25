package com.liuzemin.server.framework.model.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 供应商黑名单对象 crec_black_supplier
 *
 * @author scott_xin
 * @date 2023-11-07
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class BlackSupplier extends BaseModel {
    private static final long serialVersionUID = 1L;


    /** 1集团公司 2 子分公司 3项目部 */
    @NotNull(message = " 层级 1集团公司 2 子分公司 3项目部")
    private Long level;

    /** 项目id */
    @NotNull(message = "组织id不能为空")
    private Long projectUnit;

    /** 项目名称 */
    @NotBlank(message = "组织id不能为空")
    private String projectName;

    /** 供应商id */
    @NotNull(message = "组织id不能为空")
    private Long supplierId;

    /** 供应商名称 */
    @NotBlank(message = "组织id不能为空")
    private String supplierName;

    /** 屏蔽开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "组织id不能为空")
    private Date startTime;

    /** 屏蔽结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "组织id不能为空")
    private Date endTime;

    /** 状态 0 失效，1生效 */
    private Integer status;

    /** 状态 0 失效，1生效 */
    private String remark;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("level", getLevel())
                .append("projectUnit", getProjectUnit())
                .append("projectName", getProjectName())
                .append("supplierId", getSupplierId())
                .append("supplierName", getSupplierName())
                .append("startTime", getStartTime())
                .append("endTime", getEndTime())
                .append("status", getStatus())
                .append("remark", getRemark())
                .append("creationDate", getCreationDate())
                .append("createdBy", getCreatedBy())
                .append("createUser", getCreateUser())
                .append("lastUpdateDate", getLastUpdateDate())
                .append("lastUpdatedBy", getLastUpdatedBy())
                .append("lastUpdateUser", getLastUpdateUser())
                .toString();
    }
}
