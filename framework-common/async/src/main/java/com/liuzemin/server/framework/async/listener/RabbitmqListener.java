package com.liuzemin.server.framework.async.listener;//package com.liuzemin.server.framework.async.listener;
//
//import com.liuzemin.server.framework.async.model.AsyncMessage;
//import com.liuzemin.server.framework.async.processor.AsyncFramework;
//import com.liuzemin.server.framework.async.processor.IAsyncProcessor;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import org.springframework.stereotype.Component;
//
//
//@Component
//public class RabbitmqListener {
//
//    public void recive(AsyncMessage asyncMessage){
//        IAsyncProcessor processor = AsyncFramework.getProcessor(asyncMessage);
//        processor.process(asyncMessage);
//    }
//}
