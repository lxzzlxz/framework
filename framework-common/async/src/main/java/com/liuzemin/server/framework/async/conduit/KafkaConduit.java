package com.liuzemin.server.framework.async.conduit;

import com.liuzemin.server.framework.async.model.AsyncMessage;

public class KafkaConduit implements Conduit{

    @Override
    public Integer getType() {

        return ConduitType.kafka.getValue();
    }

    @Override
    public void send(AsyncMessage asyncMessage) {

    }
}
