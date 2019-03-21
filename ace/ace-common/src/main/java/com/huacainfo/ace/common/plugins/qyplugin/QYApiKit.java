package com.huacainfo.ace.common.plugins.qyplugin;

import com.huacainfo.ace.common.plugins.ccb.util.MD5;
import com.huacainfo.ace.common.plugins.wechat.util.HttpKit;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.tools.TimestampKit;
import com.huacainfo.ace.common.tools.URLKit;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @ClassName QYApiKit
 * @Description 群英考勤机api工具类
 * @Author Arvin Zou
 * @Date 2019/3/13 21:42
 */
public class QYApiKit {

    /**
     * 测试账户资料
     * 访问地址：    yun.kqapi.com
     */
    private final String CC_ACCT = "2518276";
    private final String PWD = "123456";
    private final String NICK_NAME = "KLinda";
    private final String ERROR_JSON = "{\"error\":\"缺失接口调用参数\",\"status\":\"0\"}";
    /**
     * 接口地址
     */
    private String API_URL = "http://yun.kqapi.com/Api/Api";
    /**
     * api账号
     */
    private String apiAcct;//= "511a92a59fe40d198cb60f6bf6b25078";
    /**
     * api密钥
     */
    private String apiKey;//= "lanni123456";
    /**
     * 接口请求参数
     */
    private TreeMap<String, String> params = new TreeMap<>();

    private QYApiKit(String apiAcct, String apiKey) {
        this.apiAcct = apiAcct;
        this.apiKey = apiKey;
    }

    public static QYApiKit getInstance(String apiAcct, String apiKey) {
        return new QYApiKit(apiAcct, apiKey);
    }

    public String getAPI_URL() {
        return API_URL;
    }

    public void setAPI_URL(String API_URL) {
        this.API_URL = API_URL;
    }

    public String getApiAcct() {
        return apiAcct;
    }

    public void setApiAcct(String apiAcct) {
        this.apiAcct = apiAcct;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public TreeMap<String, String> getParams() {
        return params;
    }

    /**
     * 基础验证
     *
     * @return boolean
     */
    private boolean apiCheck() {
        if (StringUtil.isEmpty(apiAcct) || StringUtil.isEmpty(apiKey) || StringUtil.isEmpty(API_URL)) {
            return false;
        }

        return true;
    }

    /***
     * 参数加工 - 加入公共请求参数，处理MD5加密串
     * @param p 加工前参数
     * @return 加工后参数
     */
    private TreeMap<String, String> process(TreeMap<String, String> p) {
        if (p == null) {
            p = new TreeMap<>();
        }
        p.put("account", apiAcct);
        p.put("requesttime", TimestampKit.getTimestamp10() + "");
        //拼接签名串
        StringBuilder signStr = new StringBuilder();
        Iterator<Map.Entry<String, String>> entries = p.entrySet().iterator();
        Map.Entry<String, String> entry;
        while (entries.hasNext()) {
            entry = entries.next();
            signStr.append(entry.getValue());
        }
        signStr.append(apiKey);
        //md5签名
        String sign = MD5.md5Str(signStr.toString());
        p.put("sign", sign);
        return p;
    }

    /**
     * 2.1	获取公司打卡记录（/Api/Api/recordlog） ps:不支持跨越查询
     *
     * @param start       开始时间（yyyy-mm-dd）  -非必传
     * @param end         结束时间（yyyy-mm-dd）  -非必传    * 不支持跨月查询操作
     * @param page        默认1(int)                  -非必传
     * @param useraccount 不传获取所有 （int）           -非必传
     * @return rst
     */
    public String getRecordLog(String start, String end, String page, String useraccount) {
        //接口调用验证
        if (!apiCheck()) {
            return ERROR_JSON;
        }

        //参数处理
        params.clear();
        params.put("start", start);
        params.put("end", end);
        params.put("page", page);
        params.put("useraccount", useraccount);
        params = process(params);

        //http请求
        final String method = "/recordlog";
        StringBuilder sb = new StringBuilder();
        sb.append(API_URL).append(method).append("?").append(URLKit.mapToStr(params));
        return HttpKit.get(sb.toString());
    }


    /**
     * 2.2	获取公司部门(/Api/Api/getDepartment）
     *
     * @return rst
     */
    public String getDepartment() {
        //接口调用验证
        if (!apiCheck()) {
            return ERROR_JSON;
        }
        //参数处理
        params.clear();
        params = process(params);

        //http请求
        final String method = "/getDepartment";
        StringBuilder sb = new StringBuilder();
        sb.append(API_URL).append(method).append("?").append(URLKit.mapToStr(params));
        return HttpKit.get(sb.toString());
    }


    /**
     * 2.3	添加部门 (/Api/Api/addDepartment）
     *
     * @param parentid 上层部门ID(int)   --必传
     * @param deptname 部门名称    --必传
     * @return rst
     */
    public String addDepartment(String parentid, String deptname) {
        //接口调用验证
        if (!apiCheck()) {
            return ERROR_JSON;
        }
        //参数处理
        params.clear();
        params.put("parentid", parentid);
        params.put("deptname", deptname);
        params = process(params);

        //http请求
        final String method = "/addDepartment";
        StringBuilder sb = new StringBuilder();
        sb.append(API_URL).append(method).append("?").append(URLKit.mapToStr(params));
        return HttpKit.get(sb.toString());
    }

    /**
     * 2.4	修改部门 (/Api/Api/updateDepartment）
     *
     * @param deptid   部门ID       --必传
     * @param parentid 上层部门ID   --非必传
     * @param deptname 部门名称    --非必传
     * @return rst
     */
    public String updateDepartment(String deptid, String parentid, String deptname) {
        //接口调用验证
        if (!apiCheck()) {
            return ERROR_JSON;
        }
        //参数处理
        params.clear();
        params.put("deptid", deptid);
        params.put("parentid", parentid);
        params.put("deptname", deptname);
        params = process(params);

        //http请求
        final String method = "/updateDepartment";
        StringBuilder sb = new StringBuilder();
        sb.append(API_URL).append(method).append("?").append(URLKit.mapToStr(params));
        return HttpKit.get(sb.toString());
    }

    /**
     * 2.5	获取公司员工 (/Api/Api/getEmployee）
     *
     * @param page 默认1  --非必传
     * @return rst
     */
    public String getEmployee(String page) {
        //接口调用验证
        if (!apiCheck()) {
            return ERROR_JSON;
        }
        //参数处理
        params.clear();
        params.put("page", page);
        params = process(params);

        //http请求
        final String method = "/getEmployee";
        StringBuilder sb = new StringBuilder();
        sb.append(API_URL).append(method).append("?").append(URLKit.mapToStr(params));
        return HttpKit.get(sb.toString());
    }


    /**
     * 2.6	获取员工详细 (/Api/Api/getUserDetail）
     *
     * @param useraccount 员工考勤编号  --必传
     * @return rst
     */
    public String getUserDetail(String useraccount) {
        //接口调用验证
        if (!apiCheck()) {
            return ERROR_JSON;
        }
        //参数处理
        params.clear();
        params.put("useraccount", useraccount);
        params = process(params);

        //http请求
        final String method = "/getUserDetail";
        StringBuilder sb = new StringBuilder();
        sb.append(API_URL).append(method).append("?").append(URLKit.mapToStr(params));
        return HttpKit.get(sb.toString());
    }

    /**
     * 2.7	添加员工 (/Api/Api/addEmployee）
     *
     * @param realname 姓名                               --必传
     * @param password 所设密码md5加密后的字符串            --必传
     * @param mobile   手机                               --必传
     * @param email    邮箱-                              --非必传
     * @param card     员工卡号（刷卡卡号）-int             --非必传
     * @param deptid   部门id -int                        --非必传
     * @param sex      性别:1男，2女 -int                  --非必传
     * @param sn       要同步设备的SN，多个用英文逗号分隔    --非必传
     * @return rst
     */
    public String addEmployee(String realname, String password, String mobile,
                              String email, String card, String deptid, String sex, String sn) {
        //接口调用验证
        if (!apiCheck()) {
            return ERROR_JSON;
        }
        //参数处理
        params.clear();
        params.put("realname", realname);
        params.put("password", MD5.md5Str(password));
        params.put("mobile", mobile);
        params.put("email", email);
        params.put("card", card);
        params.put("deptid", deptid);
        params.put("sex", sex);
        params.put("sn", sn);
        params = process(params);

        //http请求
        final String method = "/addEmployee";
        StringBuilder sb = new StringBuilder();
        sb.append(API_URL).append(method).append("?").append(URLKit.mapToStr(params));
        return HttpKit.get(sb.toString());
    }

    /**
     * 2.8	更新员工 (/Api/Api/updateEmployee）
     *
     * @param useraccount 员工考勤编号                        --必传
     * @param realname    姓名                               --非必传
     * @param password    所设密码md5加密后的字符串            --非必传
     * @param mobile      手机                               --非必传
     * @param email       邮箱-                              --非必传
     * @param card        员工卡号（刷卡卡号）-int             --非必传
     * @param deptid      部门id -int                        --非必传
     * @param sex         性别:1男，2女 -int                  --非必传
     * @param sn          要同步设备的SN，多个用英文逗号分隔    --非必传
     * @return rst
     */
    public String updateEmployee(String useraccount, String realname, String password, String mobile,
                                 String email, String card, String deptid, String sex, String sn) {
        //接口调用验证
        if (!apiCheck()) {
            return ERROR_JSON;
        }
        //参数处理
        params.clear();
        params.put("useraccount", useraccount);
        params.put("realname", realname);
        params.put("password", MD5.md5Str(password));
        params.put("mobile", mobile);
        params.put("email", email);
        params.put("card", card);
        params.put("deptid", deptid);
        params.put("sex", sex);
        params.put("sn", sn);
        params = process(params);
        //http请求
        final String method = "/updateEmployee";
        StringBuilder sb = new StringBuilder();
        sb.append(API_URL).append(method).append("?").append(URLKit.mapToStr(params));
        return HttpKit.get(sb.toString());
    }


    /**
     * 2.9	辞退员工  (/Api/Api/layoffEmployee）
     *
     * @param useraccount 员工的CC号,多个用英文逗号隔开（不可恢复）  --必传
     * @return rst
     */
    public String layoffEmployee(String useraccount) {
        //接口调用验证
        if (!apiCheck()) {
            return ERROR_JSON;
        }
        //参数处理
        params.clear();
        params.put("useraccount", useraccount);
        params = process(params);
        //http请求
        final String method = "/layoffEmployee";
        StringBuilder sb = new StringBuilder();
        sb.append(API_URL).append(method).append("?").append(URLKit.mapToStr(params));
        return HttpKit.get(sb.toString());
    }

    /**
     * 2.10	同步员工到设备 (/Api/Api/syncEmployee）
     *
     * @param useraccount 员工的CC号,多个用英文逗号隔开（不可恢复）  --必传
     * @param sn          要同步设备的SN，多个用英文逗号分隔         --必传
     * @return rst
     */
    public String syncEmployee(String useraccount, String sn) {
        //接口调用验证
        if (!apiCheck()) {
            return ERROR_JSON;
        }
        //参数处理
        params.clear();
        params.put("useraccount", useraccount);
        params.put("sn", sn);
        params = process(params);
        //http请求
        final String method = "/syncEmployee";
        StringBuilder sb = new StringBuilder();
        sb.append(API_URL).append(method).append("?").append(URLKit.mapToStr(params));
        return HttpKit.get(sb.toString());
    }


    /**
     * 2.11	从设备删除员工(/Api/Api/removeEmployee）
     *
     * @param useraccount 员工的CC号,多个用英文逗号隔开（不可恢复）  --必传
     * @param sn          要同步设备的SN，多个用英文逗号分隔         --必传
     * @return rst
     */
    public String removeEmployee(String useraccount, String sn) {
        //接口调用验证
        if (!apiCheck()) {
            return ERROR_JSON;
        }
        //参数处理
        params.clear();
        params.put("useraccount", useraccount);
        params.put("sn", sn);
        params = process(params);
        //http请求
        final String method = "/removeEmployee";
        StringBuilder sb = new StringBuilder();
        sb.append(API_URL).append(method).append("?").append(URLKit.mapToStr(params));
        return HttpKit.get(sb.toString());
    }

    /**
     * 2.12	补签卡 (/Api/Api/reCheck）
     *
     * @param useraccount 员工的CC号,多个用英文逗号隔开（不可恢复）  --必传
     * @param time        datetime 要补签的时间 2014-12-12 09:00:00  --必传
     * @return rst
     */
    public String reCheck(String useraccount, String time) {
        //接口调用验证
        if (!apiCheck()) {
            return ERROR_JSON;
        }
        //参数处理
        params.clear();
        params.put("useraccount", useraccount);
        params.put("time", time);
        params = process(params);
        //http请求
        final String method = "/reCheck";
        StringBuilder sb = new StringBuilder();
        sb.append(API_URL).append(method).append("?").append(URLKit.mapToStr(params));
        return HttpKit.get(sb.toString());
    }

    /**
     * 2.13	添加申请单 (/Api/Api/Apply）
     *
     * @param useraccount 员工的CC号,多个用英文逗号隔开（不可恢复）  --必传
     * @param starttime   申请开始时间 2014-12-12 09:00:00  --必传
     * @param endtime     申请结束时间 2014-12-12 18:00:00  --必传
     * @param type        1加班2  调休3外出 其它代表见请假类型接口--必传
     * @return rst
     */
    public String apply(String useraccount, String starttime, String endtime, String type) {
        //接口调用验证
        if (!apiCheck()) {
            return ERROR_JSON;
        }
        //参数处理
        params.clear();
        params.put("useraccount", useraccount);
        params.put("starttime", starttime);
        params.put("endtime", endtime);
        params.put("type", type);
        params = process(params);
        //http请求
        final String method = "/Apply";
        StringBuilder sb = new StringBuilder();
        sb.append(API_URL).append(method).append("?").append(URLKit.mapToStr(params));
        System.out.println(sb);
        return HttpKit.get(sb.toString());
    }

    /**
     * 2.14	请假类型 (/Api/Api/applyType）
     *
     * @param
     * @return rst
     * {"976":"事假（3天以上）","977":"事假（1天以上3天及以下）",
     * "978":"病假（1天及1天以下）","22932":"病假（1天以上3天及3天以上）",
     * "22933":"病假（3天以上）","22935":"年假（5天以上）",
     * "22936":"婚假","22937":"丧假","22941":"工伤","23043":"事假",
     * "28698":"带薪病假","29142":"公出","30453":"产假","31563":"离职",
     * "0":"请假","1":"加班","2":"调休","3":"外出","4":"补签卡"}
     */
    public String applyType() {
        //接口调用验证
        if (!apiCheck()) {
            return ERROR_JSON;
        }
        //参数处理
        params.clear();
        params = process(params);
        //http请求
        final String method = "/applyType";
        StringBuilder sb = new StringBuilder();
        sb.append(API_URL).append(method).append("?").append(URLKit.mapToStr(params));
        return HttpKit.get(sb.toString());
    }

    /**
     * 2.16	员工日报表 (/Api/Api/dayReport）
     *
     * @param useraccount 员工的CC号,只支持单个员工            --非必传
     * @param start       申请开始时间 2014-12-12             --必传
     * @param end         申请结束时间 2014-12-12，开始与结束时间跨度不能大于31天。   --必传
     * @return rst
     */
    public String dayReport(String useraccount, String start, String end) {
        //接口调用验证
        if (!apiCheck()) {
            return ERROR_JSON;
        }
        //参数处理
        params.clear();
        params.put("useraccount", useraccount);
        params.put("start", start);
        params.put("end", end);
        params = process(params);
        //http请求
        final String method = "/dayReport";
        StringBuilder sb = new StringBuilder();
        sb.append(API_URL).append(method).append("?").append(URLKit.mapToStr(params));
        return HttpKit.get(sb.toString());
    }

    /**
     * 2.17	获取接入设备列表 (/Api/Api/getDevice）
     *
     * @param sn 序列号
     * @return rst
     */
    public String getDevice(String sn) {
        //接口调用验证
        if (!apiCheck()) {
            return ERROR_JSON;
        }
        //参数处理
        params.clear();
        params.put("sn", sn);
        params = process(params);
        //http请求
        final String method = "/getDevice";
        StringBuilder sb = new StringBuilder();
        sb.append(API_URL).append(method).append("?").append(URLKit.mapToStr(params));
        return HttpKit.get(sb.toString());
    }
}
