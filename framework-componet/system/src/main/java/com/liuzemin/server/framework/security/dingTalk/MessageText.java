package com.liuzemin.server.framework.security.dingTalk;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述：钉钉格式类
 * @author bgy
 * @date 2023-12-26
 */
public class MessageText {

    /**
     * 消息文本类型，目前只支持文本
     */
    private String msgtype;

    /**
     * 文本消息
     */
    private MessageContent text;

    /**
     * 手机号
     */
    private At at;

    /**
     * @所有人时：true，否则为：false
     */
    @JsonProperty("isAtAll")
    private boolean isAtAll;

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public MessageContent getText() {
        return text;
    }

    public void setText(MessageContent text) {
        this.text = text;
    }

    public At getAt() {
        return at;
    }

    public void setAt(At at) {
        this.at = at;
    }

    @JsonIgnore
    public boolean isAtAll() {
        return isAtAll;
    }

    public void setAtAll(boolean atAll) {
        isAtAll = atAll;
    }
}