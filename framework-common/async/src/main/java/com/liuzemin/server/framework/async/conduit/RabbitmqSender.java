package com.liuzemin.server.framework.async.conduit;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.handler.annotation.SendTo;

public interface RabbitmqSender {

    /**
     *
     * @return
     */
    @Output(value="rabbitmqSender")
    SubscribableChannel rabbitmqSender();
}
