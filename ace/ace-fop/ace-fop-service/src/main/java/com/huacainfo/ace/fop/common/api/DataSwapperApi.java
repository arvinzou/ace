package com.huacainfo.ace.fop.common.api;

import com.huacainfo.ace.common.plugins.wechat.util.HttpKit;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HuaCai008 on 2018/6/12.
 */
public class DataSwapperApi {
    //    public static final String DOMAIN = "http://10.33.10.4:8080/gateway/api";//10.33.10.4|59.231.66.49
    public static final String DOMAIN = "http://59.231.66.49:8080/gateway/api";//10.33.10.4|59.231.66.49
    public static final String API_VERSION = "/1.0?";
    private static Logger logger = LoggerFactory.getLogger(DataSwapperApi.class);
    private static Map<String, String> DATA_PROVIDER = new HashMap<>();

    private static Map<String, String> API_KEY = new HashMap<>();

    static {
        DATA_PROVIDER.put("sswj", "市商务局");//2
        DATA_PROVIDER.put("szjj", "市质监局");//1
        DATA_PROVIDER.put("ssfj", "市司法局");//1
        DATA_PROVIDER.put("sgsj", "市国税局");//3
        DATA_PROVIDER.put("sjxw", "市经信委");//1
        DATA_PROVIDER.put("srsj", "市人社局");//1
        DATA_PROVIDER.put("skjj", "市科技局");//3
        DATA_PROVIDER.put("sajj", "市安监局");//1
        DATA_PROVIDER.put("sdsj", "市地税局");//3
        DATA_PROVIDER.put("sgsj", "市工商局");//6

        //市商务局_商务领域诚信经营示范企业
        API_KEY.put("sswj_swlycxjysfqy", "32303138303630363134353431353130343930302331383931656366342d316265302d346139322d393834362d3161f2f20dabf4af06cb6fcd");
        //市商务局_招商引资投产项目基本情况信息
        API_KEY.put("sswj_zsyztcxmjbqkxx", "32303138303630363134343131343130343830302331383931656366342d316265302d346139322d393834362d3161f2f20dabf4af06cb6fcd");
        //市质监局_名牌产品信息
        API_KEY.put("szjj_mpcpxx", "32303138303630363134333632393130343730302331383931656366342d316265302d346139322d393834362d3161f2f20dabf4af06cb6fcd");
        //市司法局_考核合格的律师事务所信息
        API_KEY.put("ssfj_khhgdlsswsxx", "32303138303630363134323130363130343630302331383931656366342d316265302d346139322d393834362d3161f2f20dabf4af06cb6fcd");
        //市国税局_税收违法违章公告信息
        API_KEY.put("sgsj_sswfwzggxx", "32303138303630363132303433333130343530302331383931656366342d316265302d346139322d393834362d3161f2f20dabf4af06cb6fcd");
        //市国税局_税务登记信息
        API_KEY.put("sgsj_swdjxx", "32303138303630363038353334393130343330302331383931656366342d316265302d346139322d393834362d3161f2f20dabf4af06cb6fcd");
        //市国税局_纳税人信用等级信息
        API_KEY.put("sgsj_nsrxydjxx", "32303138303630353137343930343130343230302331383931656366342d316265302d346139322d393834362d3161f2f20dabf4af06cb6fcd");
        //市经信委_常德市园区攻坚主要经济指标完成情况
        API_KEY.put("sjxw_cdsyqgjzyjjzbwcqk", "32303138303630353137303130313130343130302331383931656366342d316265302d346139322d393834362d3161f2f20dabf4af06cb6fcd");
        //市人社局_企业养老保险单位参保信息
        API_KEY.put("srsj_qyylbxdwsbxx", "32303138303630353136353735343130343030302331383931656366342d316265302d346139322d393834362d3161f2f20dabf4af06cb6fcd");
        //市科技局_有效发明专利信息
        API_KEY.put("skjj_yxfmzlxx", "32303138303630353136343833353130333930302331383931656366342d316265302d346139322d393834362d3161f2f20dabf4af06cb6fcd");
        //市科技局_科技项目申报单位产品基本信息
        API_KEY.put("skjj_kjxmsbdwcpjbxx", "32303138303630353136343433343130333830302331383931656366342d316265302d346139322d393834362d3161f2f20dabf4af06cb6fcd");
        //市科技局_科技进步奖知识产权证明信息
        API_KEY.put("skjj_kjjbjzscqzmxx", "32303138303630353136313731303130333730302331383931656366342d316265302d346139322d393834362d3161f2f20dabf4af06cb6fcd");
        //市安监局_行政处罚信息（单位）
        API_KEY.put("sajj_xzcfxx_dw", "32303138303630353135353330363130333630302331383931656366342d316265302d346139322d393834362d3161f2f20dabf4af06cb6fcd");
        //市地税局_年度纳税人信用等级信息
        API_KEY.put("sdsj_ndnsrxydjxx", "32303138303630353135353132303130333530302331383931656366342d316265302d346139322d393834362d3161f2f20dabf4af06cb6fcd");
        //市地税局_税收违法违规公告信息
        API_KEY.put("sdsj_sswfwgggxx", "32303138303630363134353731353130353030302331383931656366342d316265302d346139322d393834362d3161f2f20dabf4af06cb6fcd");
        //市地税局_税务登记信息
        API_KEY.put("sdsj_swdjxx", "32303138303630353039333234363130333330302331383931656366342d316265302d346139322d393834362d3161f2f20dabf4af06cb6fcd");
        //市工商局_失信记录信息
        API_KEY.put("sgsj_sxjlxx", "32303138303630343138303134343130333230302331383931656366342d316265302d346139322d393834362d3161f2f20dabf4af06cb6fcd");
        //市工商局_动产抵押登记信息
        API_KEY.put("sgsj_dcdydjxx", "32303138303630343137353130393130333130302331383931656366342d316265302d346139322d393834362d3161f2f20dabf4af06cb6fcd");
        //市工商局_企业变更记录信息
        API_KEY.put("sgsj_qybgjlxx", "32303138303630343137343530303130333030302331383931656366342d316265302d346139322d393834362d3161f2f20dabf4af06cb6fcd");
        //市工商局_企业经营异常名录信息
        API_KEY.put("sgsj_qyjyycmlxx", "32303138303630343137323230323130323830302331383931656366342d316265302d346139322d393834362d3161f2f20dabf4af06cb6fcd");
        //市工商局_投资人股权质押信息
        API_KEY.put("sgsj_tzrgqzyxx", "32303138303630343137313230363130323730302331383931656366342d316265302d346139322d393834362d3161f2f20dabf4af06cb6fcd");
        //市工商局_行政处罚信息
        API_KEY.put("sgsj_xzcfxx", "32303138303630343137333834313130323930302331383931656366342d316265302d346139322d393834362d3161f2f20dabf4af06cb6fcd");
    }

    /**
     * 中文url编码
     *
     * @param str
     * @return
     */
    private static String encode(String str) {
        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 转换显示内容
     *
     * @param map
     * @param keyMap
     * @return
     */
    private static List<Map<String, Object>> parse(Map<String, Object> map, Map<String, String> keyMap) {
        if ("200".equals(map.get("code"))) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("data");

            List<Map<String, Object>> rtnList = new ArrayList<>();
            String key;
            Object value;
            Map<String, Object> itemMap;
            for (Map<String, Object> item : list) {
                itemMap = new HashMap<>();
                for (Map.Entry<String, Object> entry : item.entrySet()) {
                    key = entry.getKey();
                    value = entry.getValue();
                    if (keyMap.containsKey(key)) {
                        itemMap.put(keyMap.get(key), value);
                    }
                }
                rtnList.add(itemMap);
            }
            return rtnList;
        }

        return null;
    }


    /**
     * 市工商联_申请_市商务局_商务领域诚信经营示范企业
     *
     * @param ZZJGDM 组织机构代码 等于  ZZJGDM
     * @return Map<String,Object>
     * QYMC 企业名称
     * ZZJGDM 组织机构代码
     * TYSHXYDM 统一社会信用代码
     * FDDBR 法定代表人
     * RYNR 荣誉内容
     * RYRDRQ 荣誉认定日期
     */
    public static List<Map<String, Object>> sswj_swlycxjysfqy(String ZZJGDM) {
        ZZJGDM = encode(ZZJGDM);
        if (CommonUtils.isEmpty(ZZJGDM)) {
            return null;
        }
        String uri = DOMAIN + "/sgsl_sq_sswj_swlycxjysfqy" + API_VERSION;
        StringBuilder sb = new StringBuilder();
        sb.append(uri)
                .append("api_key=" + API_KEY.get("sswj_swlycxjysfqy"));
        if (!CommonUtils.isEmpty(ZZJGDM)) {
            sb.append("&ZZJGDM=" + ZZJGDM);
        }

        String response = HttpKit.get(sb.toString());
        logger.debug("sswj_swlycxjysfqy[{}]:{}", ZZJGDM, response);

        Map<String, String> keyMap = new HashMap<>();
        keyMap.put("QYMC", "企业名称");
        keyMap.put("ZZJGDM", "组织机构代码");
        keyMap.put("TYSHXYDM", "统一社会信用代码");
        keyMap.put("FDDBR", "法定代表人");
        keyMap.put("RYNR", "荣誉内容");
        keyMap.put("RYRDRQ", "荣誉认定日期");
        if (response.startsWith("{")) {
            return parse(JsonUtil.toObject(response, Map.class), keyMap);
        } else {
            return null;
        }
    }


    /**
     * 市工商联_申请_市商务局_招商引资投产项目基本情况信息
     *
     * @param FDDBR 法定代表人           等于  FDDBR
     * @return Map
     * XMMC 项目名称
     * TZF 投资方
     * ZTZWY 总投资（万元）
     * LYNZWY 利用内资（万元）
     * LYWZWY 利用外资（万元）
     * TCSJ 投产时间
     * ZCQYMC 注册企业名称
     * ZCDZ 注册地址
     * ZCZB 注册资本(万元）
     * TYSHXYDM 统一社会信用代码
     * FDDBR 法定代表人
     * FDDBRXM 法定代表人证件名称
     * FDDBRZJHM 法定代表人证件号码
     */
    public static List<Map<String, Object>> sswj_zsyztcxmjbqkxx(String FDDBR) {
        FDDBR = encode(FDDBR);
        if (CommonUtils.isEmpty(FDDBR)) {
            return null;
        }
        String uri = DOMAIN + "/sgsl_sq_sswj_zsyztcxmjbqkxx" + API_VERSION;
        StringBuilder sb = new StringBuilder();
        sb.append(uri).append("api_key=" + API_KEY.get("sswj_zsyztcxmjbqkxx"));
        if (!CommonUtils.isEmpty(FDDBR)) {
            sb.append("&FDDBR=" + FDDBR);
        }

        String response = HttpKit.get(sb.toString());
        logger.debug("sswj_zsyztcxmjbqkxx[{}]:{}", FDDBR, response);

        Map<String, String> keyMap = new HashMap<>();
        keyMap.put("XMMC", "项目名称");
        keyMap.put("TZF", "投资方");
        keyMap.put("ZTZWY", "总投资（万元）");
        keyMap.put("LYNZWY", "利用内资（万元）");
        keyMap.put("LYWZWY", "利用外资（万元）");
        keyMap.put("TCSJ", "投产时间");
        keyMap.put("ZCQYMC", "注册企业名称");
        keyMap.put("ZCDZ", "注册地址");
        keyMap.put("ZCZB", "注册资本(万元）");
        keyMap.put("TYSHXYDM", "统一社会信用代码");
        keyMap.put("FDDBR", "法定代表人");
        keyMap.put("FDDBRXM", "法定代表人证件名称");
        keyMap.put("FDDBRZJHM", "法定代表人证件号码");
        if (response.startsWith("{")) {
            return parse(JsonUtil.toObject(response, Map.class), keyMap);
        } else {
            return null;
        }
    }

    /**
     * 市工商联_申请_市质监局_名牌产品信息
     *
     * @param DWMC 单位名称     等于  DWMC
     * @return Map
     * DWMC 单位名称
     * ZZJGDM 组织机构代码
     * SB 商标
     * XHGG 型号规格
     * CPMC 产品名称
     * MPZL 名牌种类
     */
    public static List<Map<String, Object>> szjj_mpcpxx(String DWMC) {
        DWMC = encode(DWMC);
        if (CommonUtils.isEmpty(DWMC)) {
            return null;
        }
        String uri = DOMAIN + "/sgsl_sq_szjj_mpcpxx" + API_VERSION;
        StringBuilder sb = new StringBuilder();
        sb.append(uri).append("api_key=" + API_KEY.get("szjj_mpcpxx"));
        if (!CommonUtils.isEmpty(DWMC)) {
            sb.append("&DWMC=" + DWMC);
        }

        String response = HttpKit.get(sb.toString());
        logger.debug("sgsl_sq_szjj_mpcpxx[{}]:{}", DWMC, response);

        Map<String, String> keyMap = new HashMap<>();
        keyMap.put("DWMC", "单位名称");
        keyMap.put("ZZJGDM", "组织机构代码");
        keyMap.put("ZTZWY", "总投资（万元）");
        keyMap.put("SB", "商标");
        keyMap.put("XHGG", "型号规格");
        keyMap.put("CPMC", "产品名称");
        keyMap.put("MPZL", "名牌种类");
        if (response.startsWith("{")) {
            return parse(JsonUtil.toObject(response, Map.class), keyMap);
        } else {
            return null;
        }
    }


    /**
     * 市工商联_申请_市司法局_考核合格的律师事务所信息
     *
     * @param SWSMC 律师事务所名称     等于  DWMC
     * @return Map
     * SWSMC 律师事务所名称
     * LSRS 律师人数
     * XKZH 许可证号
     * ZZXS 组织形式
     * ZR 主任
     * DH 电话
     * DZ 地址
     * YB 邮编
     */
    public static List<Map<String, Object>> ssfj_khhgdlsswsxx(String SWSMC) {
        SWSMC = encode(SWSMC);
        if (CommonUtils.isEmpty(SWSMC)) {
            return null;
        }
        String uri = DOMAIN + "/sgsl_sq_" + "ssfj_khhgdlsswsxx" + API_VERSION;
        StringBuilder sb = new StringBuilder();
        sb.append(uri).append("api_key=" + API_KEY.get("ssfj_khhgdlsswsxx"));
        if (!CommonUtils.isEmpty(SWSMC)) {
            sb.append("&SWSMC=" + SWSMC);
        }

        String response = HttpKit.get(sb.toString());
        logger.debug("sgsl_sq_szjj_mpcpxx[{}]:{}", SWSMC, response);

        Map<String, String> keyMap = new HashMap<>();
        keyMap.put("SWSMC", "律师事务所名称");
        keyMap.put("LSRS", "律师人数");
        keyMap.put("XKZH", "许可证号");
        keyMap.put("ZZXS", "组织形式");
        keyMap.put("ZR", "主任");
        keyMap.put("DH", "电话");
        keyMap.put("DZ", "地址");
        keyMap.put("YB", "邮编");
        if (response.startsWith("{")) {
            return parse(JsonUtil.toObject(response, Map.class), keyMap);
        } else {
            return null;
        }
    }

    /**
     * 市工商联_申请_市国税局_税收违法违章公告信息  -- 无数据
     *
     * @param NSRSBH 纳税人识别号 等于  NSRSBH
     * @return Map
     * QYMC 企业名称
     * NSRSBH 纳税人识别号
     * FDDBHZFZR 法定代表人或者负责人姓名
     * ANXZ 案件性质
     * ZYWFSS 主要违法事实
     * CFQK 相关法律依据及税务处理处罚情况
     * ANDGBSJ 案件的公布时间
     * SFJQSK 是否缴清税款
     * SFJQZNJ 是否缴清滞纳金
     */
    public static List<Map<String, Object>> sgsj_sswfwzggxx(String NSRSBH) {
        NSRSBH = encode(NSRSBH);
        if (CommonUtils.isEmpty(NSRSBH)) {
            return null;
        }
        String uri = DOMAIN + "/sgsl_sq_" + "sgsj_sswfwzggxx" + API_VERSION;
        StringBuilder sb = new StringBuilder();
        sb.append(uri).append("api_key=" + API_KEY.get("sgsj_sswfwzggxx"));
        if (!CommonUtils.isEmpty(NSRSBH)) {
            sb.append("&NSRSBH=" + NSRSBH);
        }
        String response = HttpKit.get(sb.toString());
        logger.debug("sgsj_sswfwzggxx[{}]:{}", NSRSBH, response);

        Map<String, String> keyMap = new HashMap<>();
        keyMap.put("QYMC", "企业名称");
        keyMap.put("NSRSBH", "纳税人识别号");
        keyMap.put("FDDBHZFZR", "法定代表人或者负责人姓名");
        keyMap.put("ANXZ", "案件性质");
        keyMap.put("ZYWFSS", "主要违法事实");
        keyMap.put("CFQK", "相关法律依据及税务处理处罚情况");
        keyMap.put("ANDGBSJ", "案件的公布时间");
        keyMap.put("SFJQSK", "是否缴清税款");
        keyMap.put("SFJQZNJ", "是否缴清滞纳金");
        if (response.startsWith("{")) {
            return parse(JsonUtil.toObject(response, Map.class), keyMap);
        } else {
            return null;
        }
    }

    /**
     * 市工商联_申请_市国税局_税务登记信息
     *
     * @param NSRSBH 纳税人识别号     等于  NSRSBH
     * @return map
     * NSRSBH 纳税人识别号
     * NSRMC 纳税人名称
     * FRDB 法人代表
     * FDDBRSFZH 法人代表身份证号
     * DJZCLX 登记注册类型
     * HY 行业
     * ZCDZ 注册地址
     * NSRZT 纳税人状态
     * KYSLRQ 开业设立日期
     * JYFW 经营范围
     * JYDZ 经营地址
     */
    public static List<Map<String, Object>> sgsj_swdjxx(String NSRSBH) {
        NSRSBH = encode(NSRSBH);
        if (CommonUtils.isEmpty(NSRSBH)) {
            return null;
        }
        String uri = DOMAIN + "/sgsl_sq_" + "sgsj_swdjxx" + API_VERSION;
        StringBuilder sb = new StringBuilder();
        sb.append(uri).append("api_key=" + API_KEY.get("sgsj_swdjxx"));
        if (!CommonUtils.isEmpty(NSRSBH)) {
            sb.append("&NSRSBH=" + NSRSBH);
        }
        String response = HttpKit.get(sb.toString());
        logger.debug("sgsj_swdjxx[{}]:{}", NSRSBH, response);


        Map<String, String> keyMap = new HashMap<>();
        keyMap.put("NSRSBH", "纳税人识别号");
        keyMap.put("NSRMC", "纳税人名称");
        keyMap.put("FRDB", "法人代表");
        keyMap.put("FDDBRSFZH", "法人代表身份证号");
        keyMap.put("DJZCLX", "登记注册类型");
        keyMap.put("HY", "行业");
        keyMap.put("ZCDZ", "注册地址");
        keyMap.put("NSRZT", "纳税人状态");
        keyMap.put("KYSLRQ", "开业设立日期");
        keyMap.put("JYFW", "经营范围");
        keyMap.put("JYDZ", "经营地址");
        if (response.startsWith("{")) {
            return parse(JsonUtil.toObject(response, Map.class), keyMap);
        } else {
            return null;
        }
    }


    /**
     * 市工商联_申请_市国税局_纳税人信用等级信息
     *
     * @param NSRSBH 纳税人识别号  等于  NSRSBH
     * @return map
     * NSRSBH 纳税人识别号
     * NSRMC 纳税人名称
     * XYDJ 信用等级
     * DJRQ 登记日期
     */
    public static List<Map<String, Object>> sgsj_nsrxydjxx(String NSRSBH) {
        NSRSBH = encode(NSRSBH);
        if (CommonUtils.isEmpty(NSRSBH)) {
            return null;
        }
        String uri = DOMAIN + "/sgsl_sq_" + "sgsj_nsrxydjxx" + API_VERSION;
        StringBuilder sb = new StringBuilder();
        sb.append(uri).append("api_key=" + API_KEY.get("sgsj_nsrxydjxx"));
        if (!CommonUtils.isEmpty(NSRSBH)) {
            sb.append("&NSRSBH=" + NSRSBH);
        }
        String response = HttpKit.get(sb.toString());
        logger.debug("sgsj_nsrxydjxx[{}]:{}", NSRSBH, response);

        Map<String, String> keyMap = new HashMap<>();
        keyMap.put("NSRSBH", "纳税人识别号");
        keyMap.put("NSRMC", "纳税人名称");
        keyMap.put("XYDJ", "信用等级");
        keyMap.put("DJRQ", "登记日期");
        if (response.startsWith("{")) {
            return parse(JsonUtil.toObject(response, Map.class), keyMap);
        } else {
            return null;
        }
    }

    /**
     * 市工商联_申请_市经信委_常德市园区攻坚主要经济指标完成情况
     *
     * @param YQMC 园区名称 等于  YQMC
     * @return map
     * TJZQ 统计周期
     * YQMC 园区名称
     * CZJDE 规模工业产值绝对额（亿元）
     * CZZF 规模工业产值增幅（%）
     * TRWCELJ 基础设施投入完成额累计（亿元）
     * CZTR 基础设施财政投入（亿元）
     * XMKGGS 固定资产投资1亿元以上新工业项目开工个数
     * XMTCGS 固定资产投资1亿元以上新工业项目投产个数
     * QYLJ 新增规模工业企业累计（户）
     * QYXRG 新增规模工业企业新入规（户）
     * QYBQ 新增规模工业企业搬迁（户）
     * CFJSMJ 标准化厂房建设面积（万平方米）
     * JCMJ 标准化厂房建成面积（万平方米）
     */
    public static List<Map<String, Object>> sjxw_cdsyqgjzyjjzbwcqk(String YQMC) {
        YQMC = encode(YQMC);
        if (CommonUtils.isEmpty(YQMC)) {
            return null;
        }
        String uri = DOMAIN + "/sgsl_sq_" + "sjxw_cdsyqgjzyjjzbwcqk" + API_VERSION;
        StringBuilder sb = new StringBuilder();
        sb.append(uri).append("api_key=" + API_KEY.get("sjxw_cdsyqgjzyjjzbwcqk"));
        if (!CommonUtils.isEmpty(YQMC)) {
            sb.append("&YQMC=" + YQMC);
        }
        String response = HttpKit.get(sb.toString());
        logger.debug("sjxw_cdsyqgjzyjjzbwcqk[{}]:{}", YQMC, response);

        Map<String, String> keyMap = new HashMap<>();
        keyMap.put("TJZQ", "统计周期");
        keyMap.put("YQMC", "园区名称");
        keyMap.put("CZJDE", "规模工业产值绝对额（亿元）");
        keyMap.put("CZZF", "规模工业产值增幅（%）");
        keyMap.put("TRWCELJ", "基础设施投入完成额累计（亿元）");
        keyMap.put("CZTR", "基础设施财政投入（亿元）");
        keyMap.put("XMKGGS", "固定资产投资1亿元以上新工业项目开工个数");
        keyMap.put("XMTCGS", "固定资产投资1亿元以上新工业项目投产个数");
        keyMap.put("QYLJ", "新增规模工业企业累计（户）");
        keyMap.put("QYXRG", "新增规模工业企业新入规（户）");
        keyMap.put("QYBQ", "新增规模工业企业搬迁（户）");
        keyMap.put("CFJSMJ", "标准化厂房建设面积（万平方米）");
        keyMap.put("JCMJ", "标准化厂房建成面积（万平方米）");
        if (response.startsWith("{")) {
            return parse(JsonUtil.toObject(response, Map.class), keyMap);
        } else {
            return null;
        }
    }

    /**
     * 市工商联_申请_市人社局_企业养老保险单位参保信息
     *
     * @param AAB001 单位序号     等于  AAB001
     * @return map
     * AAB001 单位序号
     * AAE140 险种类型
     * AAB050 参保日期
     * AAB051 参保状态
     * AAA040 缴费比例类别
     * AAE036 经办日期
     * SAE034 状态变更日期
     * SAB143 社保缴费收入户
     */
    public static List<Map<String, Object>> srsj_qyylbxdwsbxx(String AAB001) {
        AAB001 = encode(AAB001);
        if (CommonUtils.isEmpty(AAB001)) {
            return null;
        }
        String uri = DOMAIN + "/sgsl_sq_" + "srsj_qyylbxdwsbxx" + API_VERSION;
        StringBuilder sb = new StringBuilder();
        sb.append(uri).append("api_key=" + API_KEY.get("srsj_qyylbxdwsbxx"));
        if (!CommonUtils.isEmpty(AAB001)) {
            sb.append("&AAB001=" + AAB001);
        }
        String response = HttpKit.get(sb.toString());
        logger.debug("srsj_qyylbxdwsbxx[{}]:{}", AAB001, response);

        Map<String, String> keyMap = new HashMap<>();
        keyMap.put("AAB001", "单位序号");
        keyMap.put("AAE140", "险种类型");
        keyMap.put("AAB050", "参保日期");
        keyMap.put("AAB051", "参保状态");
        keyMap.put("AAA040", "缴费比例类别");
        keyMap.put("AAE036", "经办日期");
        keyMap.put("SAE034", "状态变更日期");
        keyMap.put("SAB143", "社保缴费收入户");
        if (response.startsWith("{")) {
            return parse(JsonUtil.toObject(response, Map.class), keyMap);
        } else {
            return null;
        }
    }

    /**
     * 市工商联_申请_市科技局_有效发明专利信息
     *
     * @param ZLMC 专利名称 等于  ZLMC
     * @return map
     * ZLMC 专利名称
     * ZLLX 专利类型
     * SQH 申请号
     * SQRKR 授权入库日
     * QLR 权利人
     * QLRLX 权利人类型
     * ZLDZ 专利地址（申请）
     * ZLSSQX 专利所属区县
     * DLJGMC 代理机构名称
     */
    public static List<Map<String, Object>> skjj_yxfmzlxx(String ZLMC) {
        ZLMC = encode(ZLMC);
        if (CommonUtils.isEmpty(ZLMC)) {
            return null;
        }
        String uri = DOMAIN + "/sgsl_sq_" + "skjj_yxfmzlxx" + API_VERSION;
        StringBuilder sb = new StringBuilder();
        sb.append(uri).append("api_key=" + API_KEY.get("skjj_yxfmzlxx"));
        if (!CommonUtils.isEmpty(ZLMC)) {
            sb.append("&ZLMC=" + ZLMC);
        }
        String response = HttpKit.get(sb.toString());
        logger.debug("skjj_yxfmzlxx [{}]:{}", ZLMC, response);

        Map<String, String> keyMap = new HashMap<>();
        keyMap.put("ZLMC ", "专利名称");
        keyMap.put("ZLLX ", "专利类型");
        keyMap.put("SQH", "申请号");
        keyMap.put("SQRKR", "授权入库日");
        keyMap.put("QLR", "权利人");
        keyMap.put("QLRLX", "权利人类型");
        keyMap.put("ZLDZ", "专利地址（申请）");
        keyMap.put("ZLSSQX", "专利所属区县");
        keyMap.put("DLJGMC", "代理机构名称");
        if (response.startsWith("{")) {
            return parse(JsonUtil.toObject(response, Map.class), keyMap);
        } else {
            return null;
        }
    }

    /**
     * 市工商联_申请_市科技局_科技项目申报单位产品基本信息
     *
     * @param QYID 组织机构代码  等于  QYID
     * @return map
     * QYID 组织机构代码
     * NIANDAI 年代
     * CHANPIN 主要产品名称
     * CL 产量
     * CZ 产值
     * LR 利润
     * SS 税收
     * ID ID
     */
    public static List<Map<String, Object>> skjj_kjxmsbdwcpjbxx(String QYID) {
        QYID = encode(QYID);
        if (CommonUtils.isEmpty(QYID)) {
            return null;
        }
        String uri = DOMAIN + "/sgsl_sq_" + "skjj_kjxmsbdwcpjbxx" + API_VERSION;
        StringBuilder sb = new StringBuilder();
        sb.append(uri).append("api_key=" + API_KEY.get("skjj_kjxmsbdwcpjbxx"));
        if (!CommonUtils.isEmpty(QYID)) {
            sb.append("&QYID=" + QYID);
        }
        String response = HttpKit.get(sb.toString());
        logger.debug("skjj_kjxmsbdwcpjbxx [{}]:{}", QYID, response);

        Map<String, String> keyMap = new HashMap<>();
        keyMap.put("QYID", "组织机构代码");
        keyMap.put("NIANDAI", "年代");
        keyMap.put("CHANPIN", "主要产品名称");
        keyMap.put("CL", "产量");
        keyMap.put("CZ", "产值");
        keyMap.put("LR", "利润");
        keyMap.put("SS", "税收");
        keyMap.put("ID", "ID");
        if (response.startsWith("{")) {
            return parse(JsonUtil.toObject(response, Map.class), keyMap);
        } else {
            return null;
        }
    }

    /**
     * 市工商联_申请_市科技局_科技进步奖知识产权证明信息
     *
     * @param QYID 组织机构代码  等于  QYID
     * @return map
     * XMMC 项目名称
     * QYID 组织机构代码
     * GB1 国别
     * ZLH1 专利号
     * ZLLB1 专利类别
     * ID ID
     */
    public static List<Map<String, Object>> skjj_kjjbjzscqzmxx(String QYID) {
        QYID = encode(QYID);
        if (CommonUtils.isEmpty(QYID)) {
            return null;
        }
        String uri = DOMAIN + "/sgsl_sq_" + "skjj_kjjbjzscqzmxx" + API_VERSION;
        StringBuilder sb = new StringBuilder();
        sb.append(uri).append("api_key=" + API_KEY.get("skjj_kjjbjzscqzmxx"));
        if (!CommonUtils.isEmpty(QYID)) {
            sb.append("&QYID=" + QYID);
        }
        String response = HttpKit.get(sb.toString());
        logger.debug("skjj_kjjbjzscqzmxx[{}]:{}", QYID, response);

        Map<String, String> keyMap = new HashMap<>();
        keyMap.put("XMMC", "项目名称");
        keyMap.put("QYID", "组织机构代码");
        keyMap.put("GB1", "国别");
        keyMap.put("ZLH1", "专利号");
        keyMap.put("ZLLB1", "专利类别");
        keyMap.put("ID", "ID");
        if (response.startsWith("{")) {
            return parse(JsonUtil.toObject(response, Map.class), keyMap);
        } else {
            return null;
        }
    }

    /**
     * 市工商联_申请_市安监局_行政处罚信息（单位）
     *
     * @param QYTYXYDM 企业统一信用代码\/组织机构代码 等于  QYTYXYDM
     * @return map
     * DWMC 单位名称（被处罚单位）
     * QYTYXYDM 企业统一信用代码/组织机构代码
     * DZ 地址
     * FRXM 法人姓名
     * FRZW 法人职务
     * FRLXDH 法人联系电话
     * WFSSJZJ 违法事实及证据
     * CLRQ 处罚日期
     * CLJG 处罚结果
     */
    public static List<Map<String, Object>> sajj_xzcfxx_dw(String QYTYXYDM) {
        QYTYXYDM = encode(QYTYXYDM);
        if (CommonUtils.isEmpty(QYTYXYDM)) {
            return null;
        }
        String uri = DOMAIN + "/sgsl_sq_" + "sajj_xzcfxx_dw" + API_VERSION;
        StringBuilder sb = new StringBuilder();
        sb.append(uri).append("api_key=" + API_KEY.get("sajj_xzcfxx_dw"));
        if (!CommonUtils.isEmpty(QYTYXYDM)) {
            sb.append("&QYTYXYDM=" + QYTYXYDM);
        }
        String response = HttpKit.get(sb.toString());
        logger.debug("sajj_xzcfxx_dw [{}]:{}", QYTYXYDM, response);

        Map<String, String> keyMap = new HashMap<>();
        keyMap.put("DWMC", "单位名称（被处罚单位）");
        keyMap.put("QYTYXYDM", "企业统一信用代码/组织机构代码");
        keyMap.put("DZ", "地址");
        keyMap.put("FRXM", "法人姓名");
        keyMap.put("FRZW", "法人职务");
        keyMap.put("FRLXDH", "法人联系电话");
        keyMap.put("WFSSJZJ", "违法事实及证据");
        keyMap.put("CLRQ", "处罚日期");
        keyMap.put("CLJG", "处罚结果");
        if (response.startsWith("{")) {
            return parse(JsonUtil.toObject(response, Map.class), keyMap);
        } else {
            return null;
        }
    }

    /**
     * 市工商联_申请_市地税局_年度纳税人信用等级信息
     *
     * @param TYSHXYDM 统一社会信用代码    等于  TYSHXYDM
     * @return map
     * XH 序号
     * PJND 评价年度
     * TYSHXYDM 统一社会信用代码
     * NSRSBH 纳税人识别号
     * NSRMC 纳税人名称
     * ZGSWJG 主管税务机关
     * HY 行业
     * DJZCLX 登记注册类型
     * PJJG 评价结果
     * PJFZ 评价分值
     * FBZT 发布状态
     */
    public static List<Map<String, Object>> sdsj_ndnsrxydjxx(String TYSHXYDM) {
        TYSHXYDM = encode(TYSHXYDM);
        if (CommonUtils.isEmpty(TYSHXYDM)) {
            return null;
        }
        String uri = DOMAIN + "/sgsl_sq_" + "sdsj_ndnsrxydjxx" + API_VERSION;
        StringBuilder sb = new StringBuilder();
        sb.append(uri).append("api_key=" + API_KEY.get("sdsj_ndnsrxydjxx"));
        if (!CommonUtils.isEmpty(TYSHXYDM)) {
            sb.append("&TYSHXYDM=" + TYSHXYDM);
        }
        String response = HttpKit.get(sb.toString());
        logger.debug("sdsj_ndnsrxydjxx  [{}]:{}", TYSHXYDM, response);


        Map<String, String> keyMap = new HashMap<>();
        keyMap.put("XH", "序号");
        keyMap.put("PJND ", "评价年度");
        keyMap.put("TYSHXYDM", "统一社会信用代码");
        keyMap.put("NSRSBH", "纳税人识别号");
        keyMap.put("NSRMC", "纳税人名称");
        keyMap.put("ZGSWJG", "主管税务机关");
        keyMap.put("HY", "行业");
        keyMap.put("DJZCLX", "登记注册类型");
        keyMap.put("PJJG", "评价结果");
        keyMap.put("PJFZ", "评价分值");
        keyMap.put("FBZT", "发布状态");
        if (response.startsWith("{")) {
            return parse(JsonUtil.toObject(response, Map.class), keyMap);
        } else {
            return null;
        }
    }


    /**
     * 市工商联_申请_市地税局_税收违法违规公告信息
     *
     * @param NSRSBH 纳税人识别号 等于  NSRSBH
     * @return map
     * NSRMC 纳税人名称
     * NSRSBH 纳税人识别号
     * FRDBXM 法人代表（业主）姓名
     * SFZHHQTZJH 身份证号或其他证件号
     * JYDD 经营地点
     * WFXW 违法行为
     * CFWSH 处罚文书号
     * CFDW 处罚单位
     * CFRQ 处罚日期
     */
    public static List<Map<String, Object>> sdsj_sswfwgggxx(String NSRSBH) {
        NSRSBH = encode(NSRSBH);
        if (CommonUtils.isEmpty(NSRSBH)) {
            return null;
        }
        String uri = DOMAIN + "/sgsl_sq_" + "sdsj_sswfwgggxx" + API_VERSION;
        StringBuilder sb = new StringBuilder();
        sb.append(uri).append("api_key=" + API_KEY.get("sdsj_sswfwgggxx"));
        if (!CommonUtils.isEmpty(NSRSBH)) {
            sb.append("&NSRSBH=" + NSRSBH);
        }
        String response = HttpKit.get(sb.toString());
        logger.debug("sdsj_sswfwgggxx  [{}]:{}", NSRSBH, response);


        Map<String, String> keyMap = new HashMap<>();
        keyMap.put("NSRMC", "纳税人名称");
        keyMap.put("NSRSBH", "纳税人识别号");
        keyMap.put("FRDBXM", "法人代表（业主）姓名");
        keyMap.put("SFZHHQTZJH", "身份证号或其他证件号");
        keyMap.put("JYDD", "经营地点");
        keyMap.put("WFXW", "违法行为");
        keyMap.put("CFWSH", "处罚文书号");
        keyMap.put("CFDW", "处罚单位");
        keyMap.put("CFRQ", "处罚日期");
        if (response.startsWith("{")) {
            return parse(JsonUtil.toObject(response, Map.class), keyMap);
        } else {
            return null;
        }
    }

    /**
     * 市工商联_申请_市地税局_税务登记信息
     *
     * @param SHXYDMNSR 社会信用代码（纳税人识别号）   等于  SHXYDMNSR
     * @return map
     * <p>
     * SHXYDMNSR 社会信用代码（纳税人识别号）
     * NSRMC 纳税人名称
     * XY 行业
     * FDDBRXM 法定代表人姓名
     * JYFW 经营范围
     * GDGRS 固定工人数
     * FDDBRYDDH 法定代表人移动电话
     */
    public static List<Map<String, Object>> sdsj_swdjxx(String SHXYDMNSR) {
        SHXYDMNSR = encode(SHXYDMNSR);
        if (CommonUtils.isEmpty(SHXYDMNSR)) {
            return null;
        }
        String uri = DOMAIN + "/sgsl_sq_" + "sdsj_swdjxx" + API_VERSION;
        StringBuilder sb = new StringBuilder();
        sb.append(uri).append("api_key=" + API_KEY.get("sdsj_swdjxx"));
        if (!CommonUtils.isEmpty(SHXYDMNSR)) {
            sb.append("&SHXYDMNSR=" + SHXYDMNSR);
        }
        String response = HttpKit.get(sb.toString());
        logger.debug("sdsj_swdjxx  [{}]:{}", SHXYDMNSR, response);

        Map<String, String> keyMap = new HashMap<>();
        keyMap.put("SHXYDMNSR", "社会信用代码（纳税人识别号）");
        keyMap.put("NSRMC", "纳税人名称");
        keyMap.put("XY", "行业");
        keyMap.put("FDDBRXM", "法定代表人姓名");
        keyMap.put("JYFW", "经营范围");
        keyMap.put("GDGRS", "固定工人数");
        keyMap.put("FDDBRYDDH", "法定代表人移动电话");
        if (response.startsWith("{")) {
            return parse(JsonUtil.toObject(response, Map.class), keyMap);
        } else {
            return null;
        }
    }

    /***
     *市工商联_申请_市工商局_失信记录信息
     * @param COMPANY_FZR_CARD_NUM 证件号码 等于  COMPANY_FZR_CARD_NUM
     * @return map
     *COMPANY_NAME 市场主体名称
    ZZJGDM 统一社会信用代码
    COMPANY_ZCH 注册号
    COMPANY_FZR 法定代表人/负责人
    COMPANY_FZR_CARD_TYPE 证件类型
    COMPANY_FZR_CARD_NUM 证件号码
    ABNORMAL_TYPE 失信类别
    REASON 列入原因
    DESCRIPTIONS 情形描述
    APPLICATION_UNIT 列入机关
    APPLICANT_DATE 列入时间
    OPTION_TYPE 当前状态
    OPERATION_DATE 移出（更正）时间
    MOVEDESCRIPTIONS 移出原因（更正说明）
     */
    public static List<Map<String, Object>> sgsj_sxjlxx(String COMPANY_FZR_CARD_NUM) {
        COMPANY_FZR_CARD_NUM = encode(COMPANY_FZR_CARD_NUM);
        if (CommonUtils.isEmpty(COMPANY_FZR_CARD_NUM)) {
            return null;
        }
        String uri = DOMAIN + "/sgsl_sq_" + "sgsj_sxjlxx" + API_VERSION;
        StringBuilder sb = new StringBuilder();
        sb.append(uri).append("api_key=" + API_KEY.get("sgsj_sxjlxx"));
        if (!CommonUtils.isEmpty(COMPANY_FZR_CARD_NUM)) {
            sb.append("&COMPANY_FZR_CARD_NUM=" + COMPANY_FZR_CARD_NUM);
        }
        String response = HttpKit.get(sb.toString());
        logger.debug("sgsj_sxjlxx  [{}]:{}", COMPANY_FZR_CARD_NUM, response);

        Map<String, String> keyMap = new HashMap<>();
        keyMap.put("COMPANY_NAME", "市场主体名称");
        keyMap.put("ZZJGDM", "统一社会信用代码");
        keyMap.put("COMPANY_ZCH", "注册号");
        keyMap.put("COMPANY_FZR", "法定代表人/负责人");
        keyMap.put("COMPANY_FZR_CARD_TYPE", "证件类型");
        keyMap.put("COMPANY_FZR_CARD_NUM", "证件号码");
        keyMap.put("ABNORMAL_TYPE", "失信类别");
        keyMap.put("REASON", "列入原因");
        keyMap.put("DESCRIPTIONS", "情形描述");
        keyMap.put("APPLICATION_UNIT", "列入机关");
        keyMap.put("APPLICANT_DATE", "列入时间");
        keyMap.put("OPTION_TYPE", "当前状态");
        keyMap.put("OPERATION_DATE", "移出（更正）时间");
        keyMap.put("MOVEDESCRIPTIONS", "移出原因（更正说明）");
        if (response.startsWith("{")) {
            return parse(JsonUtil.toObject(response, Map.class), keyMap);
        } else {
            return null;
        }
    }


    /**
     * 市工商联_申请_市工商局_动产抵押登记信息
     *
     * @param MORT_DOCID 抵押人证件号码 等于  MORT_DOCID
     * @return map
     * MORREG_ID 抵押登记ID
     * TYPE 状态
     * REGIDATE 登记日期
     * MORCANREA 抵押注销原因
     * CANDATE 注销日期
     * MORTGAGOR 抵押人
     * MORT_DOCID 抵押人证件号码
     * MORT_LOC 抵押人住所地
     */
    public static List<Map<String, Object>> sgsj_dcdydjxx(String MORT_DOCID) {
        MORT_DOCID = encode(MORT_DOCID);
        if (CommonUtils.isEmpty(MORT_DOCID)) {
            return null;
        }
        String uri = DOMAIN + "/sgsl_sq_" + "sgsj_dcdydjxx" + API_VERSION;
        StringBuilder sb = new StringBuilder();
        sb.append(uri).append("api_key=" + API_KEY.get("sgsj_dcdydjxx"));
        if (!CommonUtils.isEmpty(MORT_DOCID)) {
            sb.append("&MORT_DOCID=" + MORT_DOCID);
        }
        String response = HttpKit.get(sb.toString());
        logger.debug("sgsj_dcdydjxx  [{}]:{}", MORT_DOCID, response);

        Map<String, String> keyMap = new HashMap<>();
        keyMap.put("MORREG_ID", "抵押登记ID");
        keyMap.put("TYPE", "状态");
        keyMap.put("REGIDATE", "登记日期");
        keyMap.put("MORCANREA", "抵押注销原因");
        keyMap.put("CANDATE", "注销日期");
        keyMap.put("MORTGAGOR", "抵押人");
        keyMap.put("MORT_DOCID", "抵押人证件号码");
        keyMap.put("MORT_LOC", "抵押人住所地");
        if (response.startsWith("{")) {
            return parse(JsonUtil.toObject(response, Map.class), keyMap);
        } else {
            return null;
        }
    }

    /**
     * 市工商联_申请_市工商局_企业变更记录信息
     *
     * @param PRIPID 主体身份代码   等于  PRIPID
     * @return map
     * PRIPID   主体身份代码
     * ALTBE    变更前内容
     * ALTAF    变更后内容
     * ALTDATE  变更日期
     */
    public static List<Map<String, Object>> sgsj_qybgjlxx(String PRIPID) {
        PRIPID = encode(PRIPID);
        if (CommonUtils.isEmpty(PRIPID)) {
            return null;
        }
        String uri = DOMAIN + "/sgsl_sq_" + "sgsj_qybgjlxx" + API_VERSION;
        StringBuilder sb = new StringBuilder();
        sb.append(uri).append("api_key=" + API_KEY.get("sgsj_qybgjlxx"));
        if (!CommonUtils.isEmpty(PRIPID)) {
            sb.append("&PRIPID=" + PRIPID);
        }
        String response = HttpKit.get(sb.toString());
        logger.debug("sgsj_qybgjlxx  [{}]:{}", PRIPID, response);

        Map<String, String> keyMap = new HashMap<>();
        keyMap.put("PRIPID", "主体身份代码");
        keyMap.put("ALTBE", "变更前内容");
        keyMap.put("ALTAF", "变更后内容");
        keyMap.put("ALTDATE", "变更日期");
        if (response.startsWith("{")) {
            return parse(JsonUtil.toObject(response, Map.class), keyMap);
        } else {
            return null;
        }
    }

    /**
     * 市工商联_申请_市工商局_企业经营异常名录信息
     *
     * @param PRIPID 主体身份代码     等于  UNISCID
     * @return map
     * PRIPID       主体身份代码
     * ENTNAME      企业名称
     * UNISCID      统一社会信用代码
     * LEREP        法定代表人
     * CERNO        证件号码
     * SPECAUSE     列入经营异常名录原因类型
     * SPECAUSENAME 列入经营异常名录原因类型名称
     * ABNTIME      列入日期
     */
    public static List<Map<String, Object>> sgsj_qyjyycmlxx(String PRIPID) {
        PRIPID = encode(PRIPID);
        if (CommonUtils.isEmpty(PRIPID)) {
            return null;
        }
        String uri = DOMAIN + "/sgsl_sq_" + "sgsj_qyjyycmlxx" + API_VERSION;
        StringBuilder sb = new StringBuilder();
        sb.append(uri).append("api_key=" + API_KEY.get("sgsj_qyjyycmlxx"));
        if (!CommonUtils.isEmpty(PRIPID)) {
            sb.append("&PRIPID=" + PRIPID);
        }
        String response = HttpKit.get(sb.toString());
        logger.debug("sgsj_qyjyycmlxx  [{}]:{}", PRIPID, response);

        Map<String, String> keyMap = new HashMap<>();
        keyMap.put("PRIPID", "主体身份代码");
        keyMap.put("ENTNAME", "企业名称");
        keyMap.put("UNISCID", "统一社会信用代码");
        keyMap.put("LEREP", "法定代表人");
        keyMap.put("CERNO", "证件号码");
        keyMap.put("SPECAUSE", "列入经营异常名录原因类型");
        keyMap.put("SPECAUSENAME", "列入经营异常名录原因类型名称");
        keyMap.put("ABNTIME", "列入日期");
        if (response.startsWith("{")) {
            return parse(JsonUtil.toObject(response, Map.class), keyMap);
        } else {
            return null;
        }
    }

    /**
     * 市工商联_申请_市工商局_投资人股权质押信息
     *
     * @param PRIPID 主体身份代码  等于  PRIPID
     * @return map
     * PRIPID       主体身份代码
     * IIMPORG_ID   质押ID
     * IMPORG       质权人
     * IMPORGTYPE   质权人类别
     * IIMPAM       质押金额
     * IMPONRECDATE 质押备案日期
     * IMPEXAEEP    质押审批部门
     * IMPSANDATE   质押批准日期
     * IMPTO        质押截至日期
     * EXESTATE     执行状态
     */
    public static List<Map<String, Object>> sgsj_tzrgqzyxx(String PRIPID) {
        PRIPID = encode(PRIPID);
        if (CommonUtils.isEmpty(PRIPID)) {
            return null;
        }
        String uri = DOMAIN + "/sgsl_sq_" + "sgsj_tzrgqzyxx" + API_VERSION;
        StringBuilder sb = new StringBuilder();
        sb.append(uri).append("api_key=" + API_KEY.get("sgsj_tzrgqzyxx"));
        if (!CommonUtils.isEmpty(PRIPID)) {
            sb.append("&PRIPID=" + PRIPID);
        }
        String response = HttpKit.get(sb.toString());
        logger.debug("sgsj_tzrgqzyxx  [{}]:{}", PRIPID, response);


        Map<String, String> keyMap = new HashMap<>();
        keyMap.put("PRIPID", "主体身份代码");
        keyMap.put("IIMPORG_ID", "质押ID");
        keyMap.put("IMPORG", "质权人");
        keyMap.put("IMPORGTYPE", "质权人类别");
        keyMap.put("IIMPAM", "质押金额");
        keyMap.put("IMPONRECDATE", "质押备案日期");
        keyMap.put("IMPEXAEEP", "质押审批部门");
        keyMap.put("IMPSANDATE", "质押批准日期");
        keyMap.put("IMPTO", "质押截至日期");
        keyMap.put("EXESTATE", "执行状态");
        if (response.startsWith("{")) {
            return parse(JsonUtil.toObject(response, Map.class), keyMap);
        } else {
            return null;
        }
    }

    /**
     * 市工商联_申请_市工商局_行政处罚信息
     *
     * @param CERTIFICATE_NUMBER 当事人证件号码（法人为统一社会信用代码\/注册号）  等于  CERTIFICATE_NUMBER
     * @return map
     * COMPANY_NAME         姓名（市场主体名称）
     * CERTIFICATE_NUMBER   当事人证件号码（法人为统一社会信用代码/注册号）
     * WFXW                 违法行为
     * CFCS                 处罚措施
     * CFYJ                 处罚依据
     * CF_DATE              处罚决定下达日期
     */
    public static List<Map<String, Object>> sgsj_xzcfxx(String CERTIFICATE_NUMBER) {
        CERTIFICATE_NUMBER = encode(CERTIFICATE_NUMBER);
        if (CommonUtils.isEmpty(CERTIFICATE_NUMBER)) {
            return null;
        }
        String uri = DOMAIN + "/sgsl_sq_" + "sgsj_xzcfxx" + API_VERSION;
        StringBuilder sb = new StringBuilder();
        sb.append(uri).append("api_key=" + API_KEY.get("sgsj_xzcfxx"));
        if (!CommonUtils.isEmpty(CERTIFICATE_NUMBER)) {
            sb.append("&CERTIFICATE_NUMBER=" + CERTIFICATE_NUMBER);
        }
        String response = HttpKit.get(sb.toString());
        logger.debug("sgsj_xzcfxx  [{}]:{}", CERTIFICATE_NUMBER, response);

        Map<String, String> keyMap = new HashMap<>();
        keyMap.put("COMPANY_NAME", "姓名（市场主体名称）");
        keyMap.put("CERTIFICATE_NUMBER", "当事人证件号码（法人为统一社会信用代码/注册号）");
        keyMap.put("WFXW", "违法行为");
        keyMap.put("CFCS", "处罚措施");
        keyMap.put("CFYJ", "处罚依据");
        keyMap.put("CF_DATE", "处罚决定下达日期");
        if (response.startsWith("{")) {
            return parse(JsonUtil.toObject(response, Map.class), keyMap);
        } else {
            return null;
        }
    }


}
