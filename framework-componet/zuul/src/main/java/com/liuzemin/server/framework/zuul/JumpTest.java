package com.liuzemin.server.framework.zuul;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
// 使用HttpURLConnection微信小程序发送订阅消息,请求方式为post,并携带请求参数
/*{
    "touser": "OPENID",
    "template_id": "TEMPLATE_ID",
    "page": "index",
    "data": {
        "name01": {
           "value": "某某"
        },
        "amount01": {
            "value": "￥100"
        },
        "thing01": {
            "value": "广州至北京"
        } ,
        "date01": {
            "value": "2018-01-01"
        }
    }
}*/
public class JumpTest {
    public static void main(String[] args) {
        try {

            URL url = new URL(" https://api.weixin.qq.com/cgi-bin/message/subscribe/send?" + "access_token=" + "自己的小程序token");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setDoOutput(true);
            connection.setDoInput(true);

            //构造发送给用户的订阅消息内容
            Map<String, Object> messageContent = new HashMap<>();
            messageContent.put("character_string1", new HashMap<String, Object>() {{
                put("value", "a123456789");
            }});
            messageContent.put("amount2", new HashMap<String, Object>() {{
                put("value", "1元");
            }});
            messageContent.put("thing3", new HashMap<String, Object>() {{
                put("value", "西安大学长安学区");
            }});
            messageContent.put("time4", new HashMap<String, Object>() {{
                put("value", "2021年10月20日");
            }});
            messageContent.put("thing5", new HashMap<String, Object>() {{
                put("value", "这是备注");
            }});
            JSONObject messageContentJson = new JSONObject(messageContent);

            //构造订阅消息
            Map<String, Object> subscribeMessage = new HashMap<>();
            subscribeMessage.put("touser", " 。。。");//填写你的接收者openid
            subscribeMessage.put("template_id", " 填写你的模板ID");//填写你的模板ID
            subscribeMessage.put("data", messageContentJson);
            JSONObject subscribeMessageJson = new JSONObject(subscribeMessage);

            String s1 = subscribeMessageJson.toString();
            System.out.println("String:" + s1);
            byte[] bytes = s1.getBytes();

            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.write(bytes);
            wr.close();

            int statusCode = connection.getResponseCode();

            InputStream inputStream = statusCode == 200 ? connection.getInputStream() : connection.getErrorStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            inputStream.close();
            connection.disconnect();
            System.out.println(response);

            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
