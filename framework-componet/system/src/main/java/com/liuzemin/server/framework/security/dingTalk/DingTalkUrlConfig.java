package com.liuzemin.server.framework.security.dingTalk;

/**
 * 钉钉机器人配置webhook
 * @author bgy
 * @date：2023-12-26
 */
public class DingTalkUrlConfig {
    //机器人1号，测试环境
    public static String Dev_GROUP_URL = "https://oapi.dingtalk.com/robot/send?access_token=1353a84d92f8d1ececc5375e886b8b2b11b9c113102637ed78f8d1f28275e871";
    //机器人1号，生产环境
    public static String Prod_GROUP_URL = "https://oapi.dingtalk.com/robot/send?access_token=36de9638d6a91070b96c953455f04ca6bae2a7cd1d542eccfbceb71079e165ee";
    public static String PHONE_NO = "15229574792";
}
