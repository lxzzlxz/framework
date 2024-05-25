package com.liuzemin.server.framework.model.utils;

import org.springframework.util.CollectionUtils;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public class SignUtil {

    /**
     * 通过请求参数，包装请求header信息（含签名信息）（客户端用）
     *
     * @param appId
     * @param appSecret
     * @param reqParam
     * @return {appId=000000, sign=B562FFD6FC691A42CD7F46D068B3F74A, nonce=d50e301d-ee2c-446e-8f28-013f0fee09fb, ts=1623388123195}
     * @Title:wrapperHeader
     * @Description: TODO
     * @date 2021年6月11日 下午1:52:35
     * @author fengyi
     */
    public static Map<String, String> wrapperHeader(String appId, String appSecret, Map<String, Object> reqParam) {
        Long ts = System.currentTimeMillis();
        String nonce = UUID.randomUUID().toString();
        Map<String, String> header = new HashMap<String, String>();
        header.put("ts", ts.toString());// 进行接口调用时的时间戳，即当前时间戳（毫秒），服务端会校验时间戳，例如时间差超过20分钟则认为请求无效，防止重复请求的攻击
        header.put("nonce", nonce);// 每个请求提供一个唯一的标识符，服务器能够防止请求被多次使用
        header.put("appId", appId);// 用于标识哪个三方系统发来的请求
        String sign = getSign(appId, appSecret, ts.toString(), nonce, reqParam);// 按签名算法获取sign
        header.put("sign", sign);
        return header;
    }

    /**
     * 按签名算法获取sign（客户端和服务器端算法一致，都需要用）
     *
     * @param appId
     * @param appSecret
     * @param ts        时间戳
     * @param nonce     请求唯一标识
     * @param reqParam  请求参数
     * @return
     * @Title:getSign
     * @Description: TODO
     * @date 2021年6月11日 下午1:15:13
     * @author fengyi
     */
    public static String getSign(String appId, String appSecret, String ts, String nonce, Map<String, Object> reqParam) {
        // 计算签名规则：sign = md5("ts=1623388123195&noce=d50e301d-ee2c-446e-8f28-013f0fee09fb&appSecret=9ZLEzugQHfQd11vS8pd68lxzA&param1=1&param2=2")
        // 其他说明：这个规则双方来定，也可以不把reqParam带入计算
        // 1.请求参数key升序
        Map<String, Object> treeMap = new TreeMap<>();
        treeMap.putAll(reqParam);
        // 2.待加密字符串
        StringBuffer s = new StringBuffer();
        s.append("ts=").append(ts).append("&noce=").append(nonce).append("&appSecret=").append(appSecret);
        // append : &param1=1&param2=2
        treeMap.forEach((k, v) -> s.append("&").append(k).append("=").append(v));
        // 3.对待加密字符串进行加密(对字符串md5处理，得到sign值)
        return string2MD5(s.toString());
    }

    /**
     * 验证请求是否有效(服务器端用)
     *
     * @param reqHeader
     * @param reqParam
     * @return 是否有效(方便测试我用Boolean, 可根据业务需要 ， 返回对应错误信息 ， 不一定用Boolean)
     * @Title:checkReqInfo
     * @Description: TODO
     * @date 2021年6月11日 下午1:19:21
     * @author fengyi
     */
    public static Boolean checkReqInfo(Map<String, String> reqHeader, Map<String, Object> reqParam) {
        // 1.没有header ： 无效请求
        if (CollectionUtils.isEmpty(reqHeader)) return false;
        // 2.没有ts（请求时间戳）：无效请求
        String ts = reqHeader.get("ts");
        if (ts == null) return false;
        // 3.超过20分钟：无效请求
        // if (System.currentTimeMillis() - ts > 20 * 60 * 1000) return false;
        // 4.如果带有请求唯一标识，则需要先验证此标识是否已经被处理过，防止重复请求（不一定每个项目都要求考虑此项，这里只是一个思路，需要的可以加上）；如果处理过，返回false,如果没处理过，则把这个唯一标识存到redis(随意)
        String nonce = (String) reqHeader.get("nonce");
       /* if (StringUtil.isNotBlank(nonce)) {
            // 判断是否重复请求
            Boolean isRepeat = isRepeatReq(nonce);
            if (isRepeat) {
                return false;// 4.1重复请求：无效请求
            } else {
                saveToRedis(nonce);// 4.2标记此请求正在被处理或已被处理
            }
        }*/
        // 5.appId是否存在（用户是否存在）,不存在则算无效请求
        String appId = reqHeader.get("appId");
        if (StringUtils.isBlank(appId)) return false;
        // 5.1去库中或配置中获取appId对应的appSecret  (这里方便测试，先写死)
        String appSecret = "9ZLEzugQHfQd11vS8pd68lxzA";//getAppSecretByAppId(appId);
        // 5.2没有此appId对应信息：无效请求
        if (StringUtils.isBlank(appSecret)) return false;

        // 6.sign验证
        // 6.1 没传sign：无效请求
        // String sign = MapUtil.getStr(reqHeader, "sign", "");
        String sign = reqHeader.get("sign");
        if (StringUtils.isBlank(sign)) return false;
        //6.2最后验证sign值（按约定的sign计算方式，服务器端也算出一个sign，将这里计算出的sign和请求中的sign比较，是否一致）
        String srvSign = getSign(appId, appSecret, ts, nonce, reqParam);
        System.out.println(sign);
        System.out.println(srvSign);
        // 目前能想到的安全验证就这些，或许大家还能想到其他验证，让接口更加安全
        return sign.equalsIgnoreCase(srvSign);
    }

    /**
     * MD5加码 生成32位md5码
     */
    public static String string2MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.err.println("MD5加码失败");
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuilder hexValue = new StringBuilder();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString().toUpperCase();
    }

    // 测试
    public static void main(String[] args) {
        // A.客户端：请求（header+param）
        // A.1请求参数
        Map<String, Object> reqParam = new HashMap<String, Object>();
        reqParam.put("param2", "2");
        reqParam.put("param1", "1");
        // A.2请求头(行sign值等信息)
        String appId = "000000";// AK appId相当于用户名
        String appSecret = "9ZLEzugQHfQd11vS8pd68lxzA";// SK 相当于密码,提供给对方一个随机密码，不直接体现在请求中
        Map<String, String> reqHeader = wrapperHeader(appId, appSecret, reqParam);
        // {appId=000000, sign=B562FFD6FC691A42CD7F46D068B3F74A, nonce=d50e301d-ee2c-446e-8f28-013f0fee09fb, ts=1623388123195}
        System.out.println(reqHeader);

        // ==================客户端发起请求，参数param,并把header带入请求中

        // ============================服务器端，收到请求
        // 1.验证请求信息，2处理业务逻辑，3.返回数据到客户端
        // 1.验证请求信息(方便测试，不再赘述如何获取请求的header和参数信息！直接用上文定义的reqHeader, reqParam)
        Boolean valid = checkReqInfo(reqHeader, reqParam);
        if (!valid) {
            //无效，不再处理业务信息，返回失败
            System.out.println("无效");
        }
        System.out.println("有效请求，继续处理...");

        //2处理业务逻辑，3.返回数据到客户端...省略
    }
}
