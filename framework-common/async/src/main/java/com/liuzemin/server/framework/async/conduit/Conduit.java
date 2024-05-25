package com.liuzemin.server.framework.async.conduit;

import com.liuzemin.server.framework.async.model.AsyncMessage;

/**
 * 消息管道
 * 2018-08-23 weihao
 */
public interface Conduit {

    /**
     * 类型
     * @return
     */
    Integer getType();

    /**
     *
     * @param asyncMessage
     */
    void send(AsyncMessage asyncMessage);
}
