package com.liuzemin.server.framework.async.selector;

import com.liuzemin.server.framework.async.conduit.Conduit;
import com.liuzemin.server.framework.async.model.AsyncMessage;

public class MessageSender {

    public static void send(AsyncMessage asyncMessage){
        Conduit conduit = ConduitSelector.select(asyncMessage);
        conduit.send(asyncMessage);
    }
}
