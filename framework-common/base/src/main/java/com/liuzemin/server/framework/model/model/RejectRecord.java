package com.liuzemin.server.framework.model.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 驳回记录
 *
 * @author Scott_xin
 */

@Data
public class RejectRecord {

    public RejectRecord() {
    }

    public RejectRecord(String reason, String rejectUser, Date rejectTime) {
        this.reason = reason;
        this.rejectUser = rejectUser;
        this.rejectTime = rejectTime;
    }

    @ApiModelProperty(name = "驳回原因")
    private String reason;

    @ApiModelProperty(name = "驳回人")
    private String rejectUser;

    @ApiModelProperty(name = "驳回时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date rejectTime;
}
