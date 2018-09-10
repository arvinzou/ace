package com.huacainfo.ace.common.plugins.ccb;


import com.huacainfo.ace.common.plugins.ccb.pojo.QrURLResp;
import com.huacainfo.ace.common.plugins.ccb.util.HttpClientUtil;
import com.huacainfo.ace.common.plugins.ccb.util.MD5;
import com.huacainfo.ace.common.tools.JsonUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class QrDemo {

    public static final String PUB_ALL_STR = "30819c300d06092a864886f70d010101050003818a0030818602818075975c936982556e6dbe8187c365e4a2c63e56232be670e6896f1085073d8502a1a4225b39e89f552de12bd1df5d97638106c584855eab00287c362402e44e0c8cf5c292feb9832089a3848e1b928f9cbaac73422ffa0a07b37f9a73703348599d6926d7e29f452ef7260c9796acfbd0cb831ee7d477951e6c46c575320042ad020111";


    public static void main(String[] args) throws Exception {
        String bankURL = "https://ibsbjstar.ccb.com.cn/CCBIS/ccbMain";

        //定值变量 -- 真实信息
        String MERCHANTID = "105000083980372";//商户代码
        String POSID = "023328365";//商户柜台代码
        String BRANCHID = "430000000";//分行代码
        String PUB32TR2 = "d477951e6c46c575320042ad020111";//公钥 -- 商户平台下载
        String CURCODE = "01";//币种
        String TXCODE = "530550";//交易码
        //业务数据
        String ORDERID = "30str";//订单号
        String PAYMENT = "0.01";//付款金额
        String REMARK1 = "";//备注1
        String REMARK2 = "";//备注2
        String RETURNTYPE = "3";//返回类型
        String TIMEOUT = "";//订单超时时间
        //MD5签名参数
        StringBuffer tmp = new StringBuffer();
        tmp.append("MERCHANTID=").append(MERCHANTID);//商户代码 Y   由建行统一分配
        tmp.append("&POSID=").append(POSID);//商户柜台代码    Y   由建行统一分配
        tmp.append("&BRANCHID=").append(BRANCHID);//分行代码    Y   由建行统一指定
        //订单号   Y   由商户提供，最长30位。
        //需按以下规则生成订单号：商户代码(15位)+自定义字符串(不超过15位)。字符串可包含数字、字母、下划线。
        //商户需保证订单号唯一。
        tmp.append("&ORDERID=").append(ORDERID);//todo 我方系统订单编号为32位
        tmp.append("&PAYMENT=").append(PAYMENT);//付款金额  Y   由商户提供，按实际金额给出
        tmp.append("&CURCODE=").append(CURCODE);//币种    Y   缺省为01－人民币（只支持人民币支付）
        tmp.append("&TXCODE=").append(TXCODE);//交易码 Y    由建行统一分配为530550
        tmp.append("&REMARK1=").append(REMARK1);//备注1   N
        tmp.append("&REMARK2=").append(REMARK2);//备注2   N
        /**
         * 返回类型 Y
         * 0或空：返回页面二维码
         1：返回JSON格式【二维码信息串】
         2：返回聚合扫码页面二维码
         3：返回聚合扫码JSON格式【二维码信息串】
         聚合扫码只能上送2或3
         */
        tmp.append("&RETURNTYPE=").append(RETURNTYPE);
        /**
         * 格式：
         YYYYMMDDHHMMSS如：20120214143005
         银行系统时间> TIMEOUT时拒绝交易，若送空值则不判断超时。
         */
        tmp.append("&TIMEOUT=").append(TIMEOUT);//订单超时时间    N
        tmp.append("&PUB=").append(PUB32TR2);//PUB字段为对应柜台的公钥后30位

        //post 请求参数
        Map map = new HashMap();
        map.put("CCB_IBSVersion", "V6");   //接口版本   --  定值
        map.put("MERCHANTID", MERCHANTID);
        map.put("BRANCHID", BRANCHID);
        map.put("POSID", POSID);
        map.put("ORDERID", ORDERID);
        map.put("PAYMENT", PAYMENT);
        map.put("CURCODE", CURCODE);
        map.put("TXCODE", TXCODE);
        map.put("REMARK1", REMARK1);
        map.put("REMARK2", REMARK2);
        map.put("RETURNTYPE", RETURNTYPE);
        map.put("TIMEOUT", TIMEOUT);
        map.put("MAC", MD5.md5Str(tmp.toString()));//MAC校验域 采用标准MD5算法，由商户实现
        //
        String rtnStr = HttpClientUtil.httpPost(bankURL, map);
        System.out.println("================post1====================\n" + rtnStr);

        QrURLResp qrurl = JsonUtil.toObject(rtnStr, QrURLResp.class);
        rtnStr = HttpClientUtil.httpGet(qrurl.getPAYURL(), "UTF-8");
        System.out.println("=================post2===================\n" + rtnStr);
        Map<String, Object> respMap = JsonUtil.toMap(rtnStr);
        System.out.println("=================parse2===================\n" + respMap.get("QRURL"));
        System.out.println("=================parse2-decoder===================\n"
                + urlDecoder(String.valueOf(respMap.get("QRURL")), "utf-8"));

    }


    private static String urlDecoder(String str, String charset) {
        try {
            return URLDecoder.decode(str, charset);
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }
}
