package com.liuzemin.server.framework.async.model;

import com.liuzemin.server.framework.async.processor.AsyncFramework;

public class AsyncMessage {

    private Integer type;

    private Object content;

    private String processName;

    public AsyncMessage(Integer type, Object content, String processName){
        this.type = type;
        this.content = content;
        this.processName = processName;
    }

    public AsyncMessage(Object content, String processName){
        this.type = AsyncFramework.getDefaultConduitType();
        this.content =content;
        this.processName = processName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }
}
