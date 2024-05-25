package com.liuzemin.server.framework.async.conduit;

import com.liuzemin.server.framework.async.model.AsyncMessage;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

public class RabbitmqConduit implements Conduit{

    private RabbitmqSender rabbitmqSender;

    public RabbitmqConduit(AmqpTemplate amqpTemplate){

        this.rabbitmqSender = rabbitmqSender;
    }

    @Override
    public Integer getType() {

        return ConduitType.rabbitmq.getValue();
    }

    @Override
    public void send(AsyncMessage asyncMessage) {
        Message<AsyncMessage> message = MessageBuilder.withPayload(asyncMessage).build();
        this.rabbitmqSender.rabbitmqSender().send(message);
    }
}
