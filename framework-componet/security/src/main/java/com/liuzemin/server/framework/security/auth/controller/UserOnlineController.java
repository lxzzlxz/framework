package com.liuzemin.server.framework.security.auth.controller;

import com.legao.server.framework.model.helper.RHelper;
import com.legao.server.framework.model.model.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/online")
@Api(tags = "在线用户")
public class UserOnlineController {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private static final String SESSION_PREFIX = "legao_userid_legao.crec_";

    @GetMapping("/number")
    @ApiOperation("统计前台在线人数")
    public R<Integer> list() {
        Set<String> keys = redisTemplate.keys(SESSION_PREFIX + "*");
        //不管多端登录，只按前台登录人进行统计，1人多端登录只算1人
        Set<String> userIds = new HashSet<>();
        int start = SESSION_PREFIX.length();
        keys.forEach(key -> {
            boolean isApp = key.contains("app_");
            String userId;
            if (isApp) {
                userId = key.substring(start + 4);
            } else {
                userId = key.substring(start);
            }
            userIds.add(userId);
        });
        return RHelper.getSuccessR(userIds.size());
    }
}
