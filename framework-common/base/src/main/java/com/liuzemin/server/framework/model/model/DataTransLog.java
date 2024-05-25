package com.liuzemin.server.framework.model.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Scott_xin
 */
@Data
public class DataTransLog {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "数据类型 1：供应商数据 2：需求数据", required = true)
    @NotNull
    private Integer type;

    @ApiModelProperty(value = "请求地址", required = true)
    @NotBlank
    private String url;

    @ApiModelProperty(value = "请求体 json格式", required = true)
    @NotBlank
    private String content;

    @ApiModelProperty(value = "请求方式 post  get", required = true)
    @NotBlank
    private String sendType;

    @ApiModelProperty(value = "传输状态 1.未传 2.已传 3.传输失败", required = true)
    private Integer status;

    @ApiModelProperty(value = "请求时间", required = true)
    private Date questTime;
}
