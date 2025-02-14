package com.liuzemin.server.framework.zuul;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.springframework.util.StringUtils;

import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test {
    public static void main(String[] args) {
        String redirectUrl = "";
        //调用第三方系统接口
        String param = "name=张三&age=18";
        OutputStream out;
        BufferedReader in = null;
        JSONObject jsonObject = null;
        CloseableHttpResponse response = null;
        String result = "";
        try {
            //请求地址
            URL realUrl = new URL("https://dev.jishizulin.com.cn/#/login");
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            // 发送POST请求必须设置下面的属性
            //设置请求头
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            conn.setRequestProperty("Content-Length", String.valueOf(param.getBytes().length));
            // 允许向服务器写出数据
            conn.setDoOutput(true);
            // 允许向服务器写入数据
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置通用的请求属性
            conn.setRequestMethod("POST");
            //设置连接超时和请求超时时间
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            // conn.connect();
            // 获取URLConnection对象对应的输出流
            out = conn.getOutputStream();
            out.write(param.getBytes());
            out.flush();
            out.close();
            int responseCode = conn.getResponseCode();
            // 读取响应数据
            InputStream inputStream = responseCode == HttpURLConnection.HTTP_OK ? conn.getInputStream() : conn.getErrorStream();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 通过默认浏览器，从桌面打开固定网页
                Desktop.getDesktop().browse(realUrl.toURI());
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder resp = new StringBuilder();
            String line;
            while (!StringUtils.isEmpty(line = reader.readLine())) {
                resp.append(line);
            }
            reader.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

