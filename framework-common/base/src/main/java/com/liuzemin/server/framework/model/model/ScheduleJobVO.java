package com.liuzemin.server.framework.model.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class ScheduleJobVO implements Serializable {

    private static final long serialVersionUID = -3964735477915850231L;

    @ApiModelProperty("定时任务名称")
    private String jobName;

    @ApiModelProperty("定时任务组名称")
    private String groupName;

    @ApiModelProperty("定时表达式")
    private String cron;

    @ApiModelProperty("访问链接")
    private String url;
    
    @ApiModelProperty("任务状态")
    private String status;


    @ApiModelProperty("描述")
    private String description;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
