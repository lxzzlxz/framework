package com.liuzemin.server.framework.model.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.Instant;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Lenovo
 * 签名验证
 */

@Component
public class SignatureVerify {
    private static final String APP_SECRET = "sdafafqwfq";


    /**
     * 验证签名
     * @param request 请求
     * @return 结果
     */
    public boolean checkSignature(HttpServletRequest request) {

        String signature="signature";
        String timestamp="timestamp";
        String noncestr="noncestr";

        String headerSignature = request.getHeader(signature);
        String headerTimestamp = request.getHeader(timestamp);
        String headerNoncestr = request.getHeader(noncestr);

        if(StringUtils.isEmpty(headerSignature) || StringUtils.isEmpty(headerTimestamp) || StringUtils.isEmpty(headerNoncestr)){
            return false;
        }

        Map<String,Object> parms=new TreeMap<>();
        parms.put(noncestr,headerNoncestr);
        parms.put(timestamp,headerTimestamp);

        String s = buildSignature(parms);
        long time = Long.parseLong(headerTimestamp);

        if(Instant.now().getEpochSecond() - time > 60 * 2){
            return false;
        }

        //签名比对
        return s.equals(headerSignature);
    }

    private String buildSignature(Map<String,Object> maps){
        String s2;
        StringBuilder s = new StringBuilder();
        for (Map.Entry<String, Object> entry : maps.entrySet()) {
            s.append(entry.getValue());
        }
        //添加appSecret
        s.append(APP_SECRET);
        s2 = DigestUtils.md5DigestAsHex(s.toString().getBytes());

        return s2;
    }

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url("http://dc.api.crservice.cn/api/v1/api/device/getDeviceConfig/gantryCraneAnnexInfo")
//                .url("http://localhost:8088/api/device/getDeviceConfig/towerCraneDeviceInfo")
                .method("GET", null)
                .addHeader("noncestr", "1UD34lASUx")
                .addHeader("timestamp", "1685337657")
                .addHeader("signature", "cf0b15b786a470cf4a7049ea79c978fd")
                .addHeader("User-Agent", "apifox/1.0.0 (https://www.apifox.cn)")
                .build();
        Response response = client.newCall(request).execute();
        String s = response.body().string();
        System.out.println(s);
    }

    public void test(){
        // 时间戳
        long epochSecond = Instant.now().getEpochSecond();
        // 密钥
        String sec = "sdafafqwfq";
        // 随机字符串
        String noncestr = RandomStringUtils.randomAlphanumeric(10);

        // 签名
        String signature = DigestUtils.md5DigestAsHex((noncestr + epochSecond + sec).getBytes());
        System.out.println("noncestr: "+noncestr);
        System.out.println("timestamp: "+epochSecond);
        System.out.println("signature:"+signature);
    }

    public void test2() {
        try {


            OkHttpClient client = new OkHttpClient().newBuilder().build();
            Request request = new Request.Builder()
                    .url("http://zgy.ngrok.makalu.cc/api/realTimeLeasing/leaseContract?app_key=6BECD2CC7B187CCC3F572FF46BEE00C5&app_secret=FEE9E1A59DC08E199C3309B7FF6C3113&start_date=2023-09-10&end_date=2023-10-30")
                    .method("GET", null)
                    .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36 Edg/111.0.1661.41")
                    .addHeader("Content-Type", "application/json")
                    .build();

            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void test4(){

    }
}
