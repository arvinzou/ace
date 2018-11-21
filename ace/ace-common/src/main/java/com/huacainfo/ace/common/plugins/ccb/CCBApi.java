package com.huacainfo.ace.common.plugins.ccb;


import com.huacainfo.ace.common.plugins.ccb.pojo.CCBConfig;
import com.huacainfo.ace.common.plugins.ccb.pojo.CCBOrder;
import com.huacainfo.ace.common.plugins.ccb.pojo.QrURLResp;
import com.huacainfo.ace.common.plugins.ccb.util.HttpClientUtil;
import com.huacainfo.ace.common.plugins.ccb.util.MD5;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.tools.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2018/8/28 10:51
 * @Description:
 */
public class CCBApi {
    /**
     * 工商联生产环境地址
     */
    public static final String BANK_URL = "https://ibsbjstar.ccb.com.cn/CCBIS/ccbMain";
    public static final String CHARSET_ISO_8859_1 = "ISO-8859-1";
    private static Logger logger = LoggerFactory.getLogger(CCBApi.class);


    /**
     * 订单查询接口 --  按时间区间查询
     *
     * @return CCBOrder
     */
    public static CCBOrder query(String dateTime, String startDt, String endDt, CCBConfig cfg) {
        String QUPWD = cfg.getQupwd();//"7251211" //商户平台登录密码
        String MERCHANTID = cfg.getMerchantId();// "105000083980372";          //商户代码
        String BRANCHID = cfg.getBranchId();//"430000000";                 //分行代码
        String POSID = cfg.getPosId();// "023328365";                    //柜台号
        //单个查询,优先级高于时间区间 -- 商户订单号
        String ORDERID = "";//"b3b6b840f0124e4db576449bd1939d76";
        //与订单ID二选一
        //时间区间查询 -- 交易日期
        String ORDERDATE = dateTime;    //订单日期
        String BEGORDERTIME = startDt;  // "00:00:00";
        String ENDORDERTIME = endDt;    //"23:59:59";
        String BEGORDERID = "";
        String ENDORDERID = "";

        String TXCODE = "410408";//交易码TXCODE=410408，这个参数的值是固定的，不可以修改
        String SEL_TYPE = "3";//1页面形式  2文件返回形式 (提供TXT和XML格式文件的下载) 3 XML页面形式
        String TYPE = "0";//0支付流水 1退款流水
        String KIND = "0";//0 未结算流水        1 已结算流水
        String STATUS = "3";//0失败  1成功  2不确定 3全部（已结算流水查询不支持全部）
        String PAGE = "1";
        String CHANNEL = "";
        String OPERATOR = "";

        String param = "MERCHANTID=" + MERCHANTID + "&BRANCHID=" + BRANCHID + "&POSID=" + POSID
                + "&ORDERDATE=" + ORDERDATE + "&BEGORDERTIME=" + BEGORDERTIME + "&ENDORDERTIME=" + ENDORDERTIME
                + "&ORDERID=" + ORDERID
                + "&QUPWD=&TXCODE=" + TXCODE + "&TYPE=" + TYPE + "&KIND=" + KIND + "&STATUS=" + STATUS
                + "&SEL_TYPE=" + SEL_TYPE + "&PAGE=" + PAGE + "&OPERATOR=" + OPERATOR + "&CHANNEL=" + CHANNEL;
        Map map = new HashMap();
        map.put("MERCHANTID", MERCHANTID);
        map.put("BRANCHID", BRANCHID);
        map.put("POSID", POSID);
        map.put("ORDERDATE", ORDERDATE);
        map.put("BEGORDERTIME", BEGORDERTIME);
        map.put("ENDORDERTIME", ENDORDERTIME);
        map.put("BEGORDERID", BEGORDERID);
        map.put("ENDORDERID", ENDORDERID);
        map.put("QUPWD", QUPWD);
        map.put("TXCODE", TXCODE);
        map.put("TYPE", TYPE);
        map.put("KIND", KIND);
        map.put("STATUS", STATUS);
        map.put("ORDERID", ORDERID);
        map.put("PAGE", PAGE);
        map.put("CHANNEL", CHANNEL);
        map.put("SEL_TYPE", SEL_TYPE);
        map.put("OPERATOR", OPERATOR);
        map.put("MAC", MD5.md5Str(param));

        String ret = HttpClientUtil.httpPost(BANK_URL, map);

        System.out.println("=======================return=======================");
        System.out.println(ret);

        return null;
    }

    /**
     * 订单查询接口 -- 按商户订单号查询
     *
     * @return CCBOrder
     */
    public static CCBOrder query(String orderId, CCBConfig cfg) {
        String MERCHANTID = cfg.getMerchantId();// "105000083980372";          //商户代码
        String BRANCHID = cfg.getBranchId();//"430000000";                 //分行代码
        String POSID = cfg.getPosId();// "023328365";                    //柜台号
        String QUPWD = cfg.getQupwd();//"7251211" //商户平台登录密码

        //单个查询,优先级高于时间区间 -- 商户订单号
        String ORDERID = orderId;//"b3b6b840f0124e4db576449bd1939d76";
        //与订单ID二选一
        //时间区间查询 -- 交易日期
        String ORDERDATE = "";                  //订单日期
        String BEGORDERTIME = "00:00:00";
        String ENDORDERTIME = "23:59:59";
        String BEGORDERID = "";
        String ENDORDERID = "";

        String TXCODE = "410408";//交易码TXCODE=410408，这个参数的值是固定的，不可以修改
        String SEL_TYPE = "3";//1页面形式  2文件返回形式 (提供TXT和XML格式文件的下载) 3 XML页面形式
        String TYPE = "0";//0支付流水 1退款流水
        String KIND = "0";//0 未结算流水        1 已结算流水
        String STATUS = "3";//0失败  1成功  2不确定 3全部（已结算流水查询不支持全部）
        String PAGE = "1";
        String CHANNEL = "";
        String OPERATOR = "";

        String param = "MERCHANTID=" + MERCHANTID + "&BRANCHID=" + BRANCHID + "&POSID=" + POSID
                + "&ORDERDATE=" + ORDERDATE + "&BEGORDERTIME=" + BEGORDERTIME + "&ENDORDERTIME=" + ENDORDERTIME
                + "&ORDERID=" + ORDERID
                + "&QUPWD=&TXCODE=" + TXCODE + "&TYPE=" + TYPE + "&KIND=" + KIND + "&STATUS=" + STATUS
                + "&SEL_TYPE=" + SEL_TYPE + "&PAGE=" + PAGE + "&OPERATOR=" + OPERATOR + "&CHANNEL=" + CHANNEL;
        Map map = new HashMap();
        map.put("MERCHANTID", MERCHANTID);
        map.put("BRANCHID", BRANCHID);
        map.put("POSID", POSID);
        map.put("ORDERDATE", ORDERDATE);
        map.put("BEGORDERTIME", BEGORDERTIME);
        map.put("ENDORDERTIME", ENDORDERTIME);
        map.put("BEGORDERID", BEGORDERID);
        map.put("ENDORDERID", ENDORDERID);
        map.put("QUPWD", QUPWD);
        map.put("TXCODE", TXCODE);
        map.put("TYPE", TYPE);
        map.put("KIND", KIND);
        map.put("STATUS", STATUS);
        map.put("ORDERID", ORDERID);
        map.put("PAGE", PAGE);
        map.put("CHANNEL", CHANNEL);
        map.put("SEL_TYPE", SEL_TYPE);
        map.put("OPERATOR", OPERATOR);
        map.put("MAC", MD5.md5Str(param));

        String ret = HttpClientUtil.httpPost(BANK_URL, map);

        System.out.println("=======================return=======================");
        System.out.println(ret);

        return null;
    }

    /**
     * 发起支付接口
     *
     * @param payAmount 支付金额
     * @param orderId   订单编号 -- 系统唯一
     * @param config    商户配置信息
     * @return 支付地址
     */
    public static String pay(String payAmount, String orderId, CCBConfig config) {
        //定值变量 -- 真实信息
        String MERCHANTID = config.getMerchantId();//"105000083980372";//商户代码
        String POSID = config.getPosId();//"023328365";//商户柜台代码
        String BRANCHID = config.getBranchId();// "430000000";//分行代码
        String PUB32TR2 = config.getPub30Str();// "d477951e6c46c575320042ad020111";//公钥 -- 商户平台下载
        String CURCODE = "01";//币种
        String TXCODE = "530550";//交易码
        //业务数据
        String ORDERID = orderId;//"30str";//订单号
        String PAYMENT = payAmount;//"0.01";//付款金额-单位:元
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
        tmp.append("&ORDERID=").append(ORDERID);//系统订单编号需调整为32位
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
        Map params = new HashMap();
        params.put("CCB_IBSVersion", "V6");   //接口版本   --  定值
        params.put("MERCHANTID", MERCHANTID);
        params.put("BRANCHID", BRANCHID);
        params.put("POSID", POSID);
        params.put("ORDERID", ORDERID);
        params.put("PAYMENT", PAYMENT);
        params.put("CURCODE", CURCODE);
        params.put("TXCODE", TXCODE);
        params.put("REMARK1", REMARK1);
        params.put("REMARK2", REMARK2);
        params.put("RETURNTYPE", RETURNTYPE);
        params.put("TIMEOUT", TIMEOUT);
        params.put("MAC", MD5.md5Str(tmp.toString()));//MAC校验域 采用标准MD5算法，由商户实现
        //
        String rtnStr = HttpClientUtil.httpPost(BANK_URL, params);
        System.out.println("================post1====================\n" + rtnStr);
        logger.debug("===CCB-PayApi-One response==: {}", rtnStr);
        QrURLResp qrurl = JsonUtil.toObject(rtnStr, QrURLResp.class);
        if (null == qrurl || StringUtil.isEmpty(qrurl.getPAYURL())) {
            logger.error("===CCB-PayApi-One json parser==: {}", "json转换失败");
            return "";
        }

        rtnStr = HttpClientUtil.httpGet(qrurl.getPAYURL(), "UTF-8");
        logger.debug("===CCB-PayApi-Two response==: {}", rtnStr);
        Map<String, Object> respMap = JsonUtil.toMap(rtnStr);

        return null == respMap.get("QRURL") ? "" : String.valueOf(respMap.get("QRURL"));
    }


    private static String decoder(String str, String charset) {
        try {
            return URLDecoder.decode(str, charset);
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    /**
     * 调用示例
     */
    public static void main(String[] args) {
//        String a = QrDemo.PUB_ALL_STR;
//        String target = a.substring(a.length() - 30, a.length());
//        System.out.println(target.length());
//        System.out.println(target);

        //慈善总会 - 商户信息
        CCBConfig config = new CCBConfig("105000083980372",
                "023328365",
                "430000000",
                "d477951e6c46c575320042ad020111");
        String payUrl = pay("100000", "016bd49fcadc4962886a7d3af5dc084e", config);

        System.out.println(payUrl);
        System.out.println(decoder(payUrl, "utf-8"));
    }
}
