package com.liuzemin.server.framework.model.interceptor;

import com.liuzemin.server.framework.model.annotation.RepeatSubmit;
import com.liuzemin.server.framework.model.context.Current;
import com.liuzemin.server.framework.model.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * 判断请求url和数据是否和上一次相同，
 * 如果和上次相同，则是重复提交表单。 有效时间为30秒内。
 */
@Component
public class SameUrlDataInterceptor extends RepeatSubmitInterceptor {

    public final String SESSION_REPEAT_KEY = "repeatData";

    @Autowired
    private RedisTemplate<String, Long> redisTemplate;

    @SuppressWarnings("unchecked")
    @Override
    public boolean isRepeatSubmit(HttpServletRequest request, RepeatSubmit annotation) throws Exception {
        Long currentTime = System.currentTimeMillis();
        String url = request.getRequestURI();
        Current current = RequestContext.getCurrent();
        String key = String.valueOf(current.getUser().getId());
        key = SESSION_REPEAT_KEY + ":" + key + ":" + url;
        Long preTime = redisTemplate.opsForValue().get(key);
        if (preTime != null && (compareTime(currentTime, preTime, annotation.interval()))) {
            redisTemplate.opsForValue().set(key, currentTime, 1, TimeUnit.MINUTES);
            return true;
        }
        redisTemplate.opsForValue().set(key, currentTime, 1, TimeUnit.MINUTES);
        return false;
    }

    /**
     * 判断两次间隔时间
     */
    private boolean compareTime(Long currentTime, Long preTime, int interval) {
        if ((currentTime - preTime) < interval) {
            return true;
        }
        return false;
    }
}
