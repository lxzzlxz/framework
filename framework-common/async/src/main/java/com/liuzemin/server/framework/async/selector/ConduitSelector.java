package com.liuzemin.server.framework.async.selector;

import com.liuzemin.server.framework.async.conduit.*;
import com.liuzemin.server.framework.async.model.AsyncMessage;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.naming.ldap.InitialLdapContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息通道选择器
 * weihao
 */
@Component
public class ConduitSelector implements InitializingBean {

    private static Map<Integer, Conduit> conduitMap = new HashMap<>(16);

    //@Autowired
    private AmqpTemplate amqpTemplate;


    public static Conduit select(AsyncMessage asyncMessage){

        return conduitMap.get(asyncMessage.getType());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        conduitMap.put(ConduitType.thread.getValue(), new ThreadConduit());
        conduitMap.put(ConduitType.rabbitmq.getValue(), new RabbitmqConduit(amqpTemplate));
        conduitMap.put(ConduitType.kafka.getValue(), new KafkaConduit());
    }
}
