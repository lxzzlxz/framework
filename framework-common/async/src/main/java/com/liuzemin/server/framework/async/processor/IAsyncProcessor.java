package com.liuzemin.server.framework.async.processor;

import com.liuzemin.server.framework.async.model.AsyncMessage;

public interface IAsyncProcessor {

    /**
     * 处理任务
     * @param asyncMessage
     */
    void process(AsyncMessage asyncMessage);
}
