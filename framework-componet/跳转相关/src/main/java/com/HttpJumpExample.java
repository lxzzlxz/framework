package com;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * HTTP 请求跳转
 */
public class HttpJumpExample {
    public static void main(String[] args) throws IOException {
        String redirectUrl = "";

        URL url = new URL(redirectUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setInstanceFollowRedirects(false);

        int responseCode = conn.getResponseCode();
        if (responseCode >= 300 && responseCode < 400) {
            String newUrl = conn.getHeaderField("Location");
            System.out.println("Redirected to: " + newUrl);
        } else {
            System.out.println("No redirection");
        }
    }
}