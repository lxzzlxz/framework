package com.liuzemin.server.framework.security.dingTalk;

import cn.hutool.json.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@Slf4j
public class DingTalkPushUtil {

    /**
     * 描述：按照钉钉API处理内容格式
     * @author bgy
     * @date 2023-12-26
     * @param map
     */
    public void pushText(Map map) {
        MessageText message = new MessageText();
        message.setMsgtype("text");
        MessageContent messageContent = new MessageContent(map.get("content").toString());
        message.setText(messageContent);
        //拼接手机号
        JSONArray jsonArrayMobile = new JSONArray();
        String[] strArray = map.get("phoneNumbers").toString().split(",");
        for (int i = 0; i < strArray.length; i++) {
            jsonArrayMobile.add(strArray[i]);
        }
        At at = new At(jsonArrayMobile);

        message.setAt(at);
        message.setAtAll(false);
        push(message, map.get("webhook").toString());
    }

    /**
     * 描述：推送消息
     * @author bgy
     * @date 2023-12-26
     * @param message
     */
    private void push(MessageText message, String webhook) {
        try {
            //自定义钉钉机器人生成链接access_token钉钉自动生成
            URL url = new URL(webhook);
            //打开连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // Post 请求不能使用缓存
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("Content-Type", "application/Json; charset=UTF-8");
            conn.connect();
            OutputStream out = conn.getOutputStream();
            String jsonMessage = JSONObject.toJSONString(message);
            byte[] data = jsonMessage.getBytes();
            // 发送请求参数
            out.write(data);
            // flush输出流的缓冲
            out.flush();
            out.close();
            //开始获取数据
            InputStream in = conn.getInputStream();
            byte[] content = new byte[in.available()];
            in.read(content);
            log.info(">>>>>>>>>钉钉发送成功<<<<<<<<<");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}