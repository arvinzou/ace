package com.huacainfo.ace.rvc.hngdapi;

import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.rvc.hngdapi.pojo.response.OrganizationResp;
import com.huacainfo.ace.rvc.hngdapi.util.WebServiceCallUtil;
import com.huacainfo.ace.rvc.hngdapi.util.XmlConverUtil;

import java.util.LinkedHashMap;

/**
 * 华南光电对接工具类
 * Created by Arvin on 2018/2/1.
 */
public class HNGDApi {
    public static final String HTTP_PORT = "9001";
    public static final String HTTPS_PORT = "443";
    public static final String CLN = ":";

    public static final String PLATFORM_IP = "218.75.139.118";
    public static final String ACCOUNT = "system";
    public static final String PASSWORD = "Admin12345";
    public static final String NODE_INDEX_CODE = "001006";

    public static final String NAMESPACE_URI = "http://ws.cms.ivms6.hikvision.com";

    //https://218.75.139.118:443/cms/services/IAuthService?wsdl
    public static final String URL_TEMPLATE = "http://${ip}:${port}/cms/services/IAuthService?wsdl ";


    public static final String NODE_TYPE_VTM = "VTM";
    public static final String NODE_TYPE_VAG = "VAG";

    /**
     * 此接口获取组织ID,供后续查监控点ID使用
     *
     * @param nodeIndexCode
     * @param resType       1000
     * @return
     */
    public static OrganizationResp getAllOrganizations(String nodeIndexCode, int resType) {
        StringBuffer wsUrl = new StringBuffer();
        wsUrl.append("https://").append("218.75.139.118").append(":").append("443/")
                .append("cms/services/ICommonService?wsdl");

        LinkedHashMap<String, Object> parameter = new LinkedHashMap<>();
        parameter.put("nodeIndexCode", nodeIndexCode);//"001001"
        parameter.put("resType", resType);//1000

        String respXml = WebServiceCallUtil.call(wsUrl.toString(), NAMESPACE_URI,
                "getAllResourceDetail", parameter);

        String json = XmlConverUtil.xmltoJson(respXml).replace("@", "");
        return JsonUtil.toObject(json, OrganizationResp.class);
    }

    /**
     * 根据组织获取监控点
     * //001067为组织编码,从获取组织接口中可以拿到,根据实际需要查询的组织传参;此接口获取监控点c_index_code取流用
     *
     * @param nodeIndexCode
     * @param orgCode       指定组织的编码，20 位 ，从获取组织资源接口中可以获取
     * @param resType       3000
     * @return
     */
    public static String getAllResourceDetailByOrg(String nodeIndexCode, String orgCode, int resType) {
        StringBuffer wsUrl = new StringBuffer();
        wsUrl.append("https://").append("218.75.139.118").append(":").append("443/")
                .append("cms/services/ICommonService?wsdl");

        LinkedHashMap<String, Object> parameter = new LinkedHashMap<>();
        parameter.put("nodeIndexCode", nodeIndexCode);
        parameter.put("orgCode", orgCode);
        parameter.put("resType", resType);

        return WebServiceCallUtil.call(wsUrl.toString(), NAMESPACE_URI,
                "getAllResourceDetailByOrg", parameter);

    }

    /**
     * 获取服务信息
     * 2表示网域ID,根据实际环境需求来配置,VTM/VAG取流,用外网网域IP;此接口获取ip和web_port取流用
     *
     * @param nodeIndexCode
     * @param nodeType      节点类型
     * @param srcIndexCode  填写服务管理中 vmcc 节点的    编号
     * @param netZoneId     网域 id
     * @return
     */
    public static String getServiceByType(String nodeIndexCode, String nodeType, String srcIndexCode, int netZoneId) {
        StringBuffer wsUrl = new StringBuffer();
        wsUrl.append("https://").append("218.75.139.118").append(":").append("443/")
                .append("cms/services/ICommonService?wsdl");

        LinkedHashMap<String, Object> parameter = new LinkedHashMap<>();
        parameter.put("nodeIndexCode", nodeIndexCode);
        parameter.put("nodeType", nodeType);
        parameter.put("srcIndexCode", srcIndexCode);
        parameter.put("netZoneId", netZoneId);

        return WebServiceCallUtil.call(wsUrl.toString(), NAMESPACE_URI, "getServiceByType", parameter);
    }

    public static void main(String[] args) {

//        getAllOrganizations(NODE_INDEX_CODE, 1000);


        //提示无权限
        getAllResourceDetailByOrg("001006", "001126", 10000);

        //rows 为空
//        getServiceByType("001006", NODE_TYPE_VAG, "001146", 1);
//        getServiceByType("001006", "VAG", "001146", 1);
    }
}
