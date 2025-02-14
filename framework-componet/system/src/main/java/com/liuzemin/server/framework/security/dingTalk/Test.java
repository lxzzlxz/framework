package com.liuzemin.server.framework.security.dingTalk;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("webhook", "https://oapi.dingtalk.com/robot/send?access_token=517bc0916217b876cdf635fa57a1c91507793ac94c811a0fa5a24fc3ee3906c9");
        map.put("phoneNumbers", "18681817343,13259957571,18701550535,17791908839");
        map.put("content", "快出来下，要接单了");
        DingTalkPushUtil dingTalkPushUtil = new DingTalkPushUtil();
        dingTalkPushUtil.pushText(map);
    }
}