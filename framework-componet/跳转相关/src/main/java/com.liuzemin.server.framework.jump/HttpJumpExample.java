package com.liuzemin.server.framework.jump;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * HTTP 请求跳转
 */
public class HttpJumpExample {
    public static void main(String[] args) throws IOException {
        String redirectUrl = "";
        //调用第三方系统接口
        PrintWriter out = null;
        BufferedReader in = null;
        JSONObject jsonObject = null;
        CloseableHttpResponse response = null;
        String result = "";
        try {
            //请求地址
            URL realUrl = new URL("http://127.0.0.1:8081/services/trad/findBy");
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestMethod("POST");
            // 发送POST请求必须设置下面的属性
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            //设置请求属性
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");

            conn.connect();
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(encrypt);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line = "";
            while ((line = in.readLine()) != null) {
                result += line;
            }

            //将返回结果转换为json
            JSONArray jsonArray = JSONArray.fromObject(result);

            return jsonArray;

        } catch (Exception e) {
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}