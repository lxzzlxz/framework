package com.liuzemin.server.framework.security.auth.util;

import com.alibaba.fastjson.JSON;
import com.liuzemin.server.framework.model.utils.HttpClientUtil;
import com.liuzemin.server.framework.security.auth.model.TianYanChaBaseInfo;
import com.liuzemin.server.framework.security.auth.model.TianYanChaResult;

import java.util.HashMap;
import java.util.Map;

public class TianYanChaUtil {


    private final static String url = "http://open.api.tianyancha.com/services/open/ic/baseinfoV2/2.0";
    private final static String token = "e495ba46-5644-4902-ab17-12a94a473f5b";


    public static TianYanChaResult getCompanyInfo(String keyword) {
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", token);
        Map<String, String> param = new HashMap<>();
        param.put("keyword", keyword);
        String s = HttpClientUtil.doGet(url, header, param);
        TianYanChaResult tianYanChaResult = JSON.parseObject(s, TianYanChaResult.class);
        return tianYanChaResult;
    }

    /**
     * 主函数
     * apiDocument:http://open.tianyancha.com/open/818
     *
     * @param args
     */
    public static void main(String[] args) {
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", token);
        Map<String, String> param = new HashMap<>();
        param.put("keyword", "绵阳市涪城区启汇机械设备租赁部");
        String s = HttpClientUtil.doGet(url, header, param);
        TianYanChaResult tianYanChaResult = JSON.parseObject(s, TianYanChaResult.class);
        TianYanChaBaseInfo result = tianYanChaResult.getResult();
        String s1 = result.toString();
        System.out.println(s1);
      /*  {
            "result":{
            "historyNames":null,
                    "cancelDate":null,
                    "regStatus":"开业",
                    "regCapital":"5500万人民币",
                    "city":"西安市",
                    "staffNumRange":"小于50人",
                    "bondNum":null,
                    "historyNameList":null,
                    "industry":"科技推广和应用服务业",
                    "bondName":null,
                    "revokeDate":null,
                    "type":1,
                    "updateTimes":1676396886000,
                    "legalPersonName":"关阿鹏",
                    "revokeReason":null,
                    "regNumber":"610133100126482",
                    "creditCode":"91610133MA6U3N7RXE",
                    "property3":"",
                    "usedBondName":null,
                    "approvedTime":1655136000000,
                    "fromTime":1491926400000,
                    "socialStaffNum":16,
                    "actualCapitalCurrency":"人民币",
                    "alias":"西安乐",
                    "companyOrgType":"有限责任公司(自然人投资或控股)",
                    "id":3030218545,
                    "cancelReason":null,
                    "orgNumber":"MA6U3N7R-X",
                    "email":"guanapeng@liuzeminyun.net",
                    "toTime":null,
                    "actualCapital":"0",
                    "estiblishTime":1491926400000,
                    "regInstitute":"西安市工商行政管理局国家民用航天产业基地分局",
                    "businessScope":"计算机软硬件、云计算、大数据领域内的技术开发、技术咨询、技术服务；计算机软硬件的销售；互联网信息服务；计算机数据处理；人工智能技术的技术开发、技术应用；电信增值业务服务；工程测量服务；地理信息系统工程的施工、技术服务；企业管理咨询；企业营销策划；系统内职（员）工培训；文化艺术交流活动的组织；会务服务；广告设计、制作、代理、发布（除医疗、药品、医疗器械、保健食品广告）。（依法须经批准的项目，经相关部门批准后方可开展经营活动）。",
                    "taxNumber":"91610133MA6U3N7RXE",
                    "regLocation":"陕西省西安市国家民用航天产业基地东长安街正衡大厦A座8楼820室",
                    "regCapitalCurrency":"人民币",
                    "tags":"开业;高新技术企业;小微企业;项目品牌:乐高云智能",
                    "websiteList":"www.liuzeminyun.net",
                    "phoneNumber":"15339230002",
                    "district":"新城区",
                    "bondType":null,
                    "name":"西安乐高云智能科技有限公司",
                    "percentileScore":8003,
                    "industryAll":{
                "categoryMiddle":"创业空间服务",
                        "categoryBig":"科技推广和应用服务业",
                        "category":"科学研究和技术服务业",
                        "categorySmall":""
            },
            "isMicroEnt":1,
                    "base":"snx"
        },
            "reason":"ok",
                "error_code":0
        }*/
    }

}
