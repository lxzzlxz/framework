package com.liuzemin.server.framework.async.conduit;

import com.liuzemin.server.framework.async.context.AsyncContext;
import com.liuzemin.server.framework.async.model.AsyncMessage;
import com.liuzemin.server.framework.async.model.ThreadRunable;
import com.liuzemin.server.framework.async.processor.AsyncFramework;
import com.liuzemin.server.framework.async.processor.IAsyncProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.concurrent.*;

/**
 * 本地异步处理
 * weihao 2018-08-23
 */
public class ThreadConduit implements Conduit{

    public static final Logger log = LoggerFactory.getLogger(ThreadConduit.class);

    private ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 120, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10000), new RejectedExecutionHandler() {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            throw new RejectedExecutionException("Thread async QUEUE over bounds, task: " + r +" rejected activeCount: "
                    + executor.getActiveCount()+" maxPoolSize: " + executor.getMaximumPoolSize()+" corePoolSize:"
                    + executor.getCorePoolSize()+" queueSize: "+ executor.getQueue().size() + " total size: "
                    + executor.getTaskCount());
        }
    });

    @Override
    public Integer getType() {

        return ConduitType.thread.getValue();
    }

    @Override
    public void send(AsyncMessage asyncMessage) {
        IAsyncProcessor asyncProcessor = (IAsyncProcessor) AsyncContext.getApplicationContext().getBean(asyncMessage.getProcessName());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        ThreadRunable runable = new ThreadRunable(asyncProcessor, asyncMessage, attributes);
        executor.submit(runable);
    }
}
