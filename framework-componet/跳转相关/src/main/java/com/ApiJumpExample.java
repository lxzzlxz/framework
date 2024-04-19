package com;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiJumpExample {
    public static void main(String[] args) throws IOException {
        String apiUrl = "";

        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");

        // 设置请求参数
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "Bearer token");

        int responseCode = conn.getResponseCode();
        if (responseCode == 200) {
            System.out.println("Jump success");
        } else {
            //System.out
        }
    }
}