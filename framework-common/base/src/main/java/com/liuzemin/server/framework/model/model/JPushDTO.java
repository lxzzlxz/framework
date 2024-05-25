package com.liuzemin.server.framework.model.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("极光推送数据传输对象")
public class JPushDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("推送设备的别名")
    private List<String> alias;
    @ApiModelProperty("android显示的标题")
    private String title;
    @ApiModelProperty("消息内容")
    private String message;
}
