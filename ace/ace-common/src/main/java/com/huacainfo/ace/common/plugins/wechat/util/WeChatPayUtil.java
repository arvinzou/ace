package com.huacainfo.ace.common.plugins.wechat.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.ByteArrayInputStream;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * PackageName:com.arvin.common.plugin.wxpay<br/>
 * Descript:微信支付工具类 <br/>
 * Date: 2016-04-18 <br/>
 * User: Bovine
 * version 1.0
 */
public class WeChatPayUtil {


    protected static Logger logger = LoggerFactory.getLogger(WeChatPayUtil.class);
//    request.put("appid", APPID);//微信分配的公众账号ID（企业号corpid即为此appId）
//    request.put("mch_id", MCH_ID);//微信支付分配的商户号
//    request.put("device_info", "WEB");//终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
//    request.put("trade_type", "JSAPI");//JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付
//    request.put("fee_type", "CNY");//符合ISO 4217标准的三位字母代码，默认人民币：CNY
//    request.put("limit_pay", "no_credit");//no_credit--指定不能使用信用卡支付
//    request.put("time_start", Util.getDateAsNumberStr(Util.nowDateTimeToString()));//订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
//    request.put("time_expire", Util.getDateAsNumberStr(Util.nowAfterSecDateTimeToString(300)));//订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010   注意：最短失效时间间隔必须大于5分钟
//
//    request.put("notify_url", param.getNOTIFY_URL());//接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数
//    request.put("openid", param.getOPENID());//trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。
//    request.put("body", param.getBODY());//商品或支付单简要描述
//    request.put("detail", param.getDETAIL());//商品名称明细列表
//    request.put("attach", param.getATTACH());//附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
//    request.put("out_trade_no", param.getOUT_TRADE_NO());//商户系统内部的订单号,32个字符内、可包含字母
//    request.put("total_fee", String.valueOf((int) (Float.parseFloat(param.getTOTAL_FEE()) * 100)));//订单总金额，单位为分
//    request.put("spbill_create_ip", param.getSPBILL_CREATE_IP()); //APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP
//    request.put("goods_tag", param.getGOODS_TAG());//商品标记，代金券或立减优惠功能的参数
//    request.put("product_id", param.getPRODUCT_ID());//trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义
//
//    request.put("timeStamp", timeStamp);
//    request.put("nonce_str", RandomStringGenerator.getRandomStringByLength(32));//随机字符串，不长于32位。
//    request.put("sign",PaymentKit.createSign(request, PATERNERKEY));//签名

    public static String initXML(String appid, String deviceInfo, String mchId,
                                 String openid, String total_fee, String sign, String body,
                                 String attch, String nonceStr, String outTradeNo, String notifyUrl) {
        String moneyFen = AmountKit.changeY2F(total_fee);
        String XML = "<xml>" +
                "<appid>" + appid + "</appid>" +
                "<attach>" + attch + "</attach>" +
                "<body>" + body + "</body>" +
                "<device_info>" + deviceInfo + "</device_info>" +
                "<mch_id>" + mchId + "</mch_id>" +
                "<nonce_str>" + nonceStr + "</nonce_str>" +
                "<notify_url>" + notifyUrl + "</notify_url>" +
                "<openid>" + openid + "</openid>" +
                "<out_trade_no>" + outTradeNo + "</out_trade_no>" +
                "<spbill_create_ip>127.0.0.1</spbill_create_ip>" +
                "<total_fee>" + moneyFen + "</total_fee>" +
                "<trade_type>JSAPI</trade_type>" +
                "<sign>" + sign + "</sign>" +
                "</xml>";
        return XML;
    }

    public static String getSign(String appid, String deviceInfo, String mchId, String apiKey,
                                 String openid, String total_fee, String body, String attch,
                                 String nonceStr, String outTradeNo, String notifyUrl) {
        String moneyFen = AmountKit.changeY2F(total_fee);
        SortedMap<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("appid", appid);
        parameters.put("attach", attch);
        parameters.put("body", body);
        parameters.put("device_info", deviceInfo);
        parameters.put("mch_id", mchId);
        parameters.put("nonce_str", nonceStr);
        parameters.put("notify_url", notifyUrl);

        parameters.put("openid", openid);
        parameters.put("out_trade_no", outTradeNo);
        parameters.put("spbill_create_ip", "127.0.0.1");//手机访问IP地址
        parameters.put("total_fee", moneyFen);
        parameters.put("trade_type", "JSAPI");

        return createSign("MD5", "UTF-8", parameters, apiKey);
    }

    /**
     * JSAPI 签名
     * 注意：
     * 1.签名用的noncestr和timestamp必须与wx.config中的nonceStr和timestamp相同。
     * 签名用的url必须是调用JS接口页面的完整URL。
     *
     * @param nonceStr
     * @param ticket
     * @param timestamp
     * @param url
     * @return
     */
    public static String getJsApiSign(String nonceStr, String ticket, String timestamp, String url) {
        SortedMap<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("jsapi_ticket", ticket);
        parameters.put("noncestr", nonceStr);
        parameters.put("timestamp", timestamp);
        parameters.put("url", url);

        String characterEncoding = "UTF-8";
        String Sign = createSign("SHA1", characterEncoding, parameters, null);
        return Sign;
    }

    /**
     * ΢创建Sign
     *
     * @param characterEncoding
     * @param parameters
     * @return
     */
    public static String createSign(String signType, String characterEncoding, SortedMap<String, Object> parameters, String apiKey) {
        String sign = "";
        if (!CollectionUtils.isEmpty(parameters)) {
            StringBuffer sb = new StringBuffer();
            for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                String k = entry.getKey();
                Object v = entry.getValue();
                if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                    sb.append(k).append('=').append(v).append('&');
                }
            }

            if (signType.equals("MD5")) {
                sb.append("key=").append(apiKey);
                sign = MD5.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
            } else if (signType.equals("SHA1")) {
                //处理最后一个&符号
                //http://mp.weixin.qq.com/debug/cgi-bin/sandbox?t=jsapisign 测试链接
                String tempStr = sb.toString().substring(0, sb.length() - 1);
                logger.debug("tempStr={}", tempStr);
                sign = Sha1Util.getSha1(tempStr);//不用进行upper
            }
        }
        return sign;
    }

    public static void main(String[] args) {
        String str = "kgt8ON7yVITDhtdwci0qefheBoA_OYrLAOR5TzL64upLdPzDP0lTYPbOaqHgnIjUYTytClrGCTxB0jV3MZlWJA&noncestr=5wf9fhes1dc0oxr6taavgfjzos2vbbnl&timestamp=1476784251&url=http://www.hubeta.com/wechat-api/resources/TYZY/html/order/present.html";
        String sign = Sha1Util.getSha1(str);
        System.out.println(sign);
    }

    public static String getPayNo(String url, String xmlParam) {
        logger.debug("xml是:" + xmlParam);
        String prepay_id = "";
        try {
            String resultJson = HttpKit.post(url, xmlParam);
            logger.debug("resultJson={}", resultJson);
            if (resultJson.indexOf("FAIL") != -1) {
                return prepay_id;
            }
            Map map = MessageUtil.xmlToMap(new ByteArrayInputStream(resultJson.getBytes()));
            prepay_id = String.valueOf(map.get("prepay_id"));
        } catch (Exception e) {
            logger.error("run error：{}", e);
        }
        return prepay_id;
    }
}