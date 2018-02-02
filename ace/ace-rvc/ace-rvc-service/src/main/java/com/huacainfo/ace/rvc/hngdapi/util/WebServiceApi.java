package com.huacainfo.ace.rvc.hngdapi.util;

import com.huacainfo.ace.rvc.hngdapi.HNGDApi;
import com.huacainfo.ace.rvc.util.ResultUtil;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

import javax.xml.rpc.ParameterMode;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Arvin on 2018/2/1.
 */
public class WebServiceApi {
    private WebServiceApi() {

    }


    public static String call(String url, String methodName, Map<String, String> parameter) {
        try {
            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(new URL(url));
            // WSDL里面描述的接口名称
            call.setOperationName(methodName);
            // 接口的参数
            //设置参数名 id  第二个参数表示String类型,第三个参数表示入参
            List<String> pValues = new LinkedList<>();
            for (Map.Entry<String, String> entry : parameter.entrySet()) {
                System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());

                call.addParameter(entry.getKey(), XMLType.XSD_DATE, ParameterMode.IN);
                pValues.add(entry.getValue());
            }
            // 设置返回类型
            call.setReturnType(XMLType.XSD_STRING);
            // 给方法传递参数值，并且调用方法
            String result = (String) call.invoke(pValues.toArray());

            System.out.println("result is :" + result);
            return result;
        } catch (Exception e) {
            return ResultUtil.FAIL;
        }
    }

    public static void main(String[] args) {

        String url = "https://218.75.139.118:443/cms/services/IAuthService?wsdl";
        Map<String, String> parameter = new HashMap<>();
        parameter.put("loginAccount", HNGDApi.ACCOUNT);
        parameter.put("password", HNGDApi.PASSWORD);
        parameter.put("serviceIp", HNGDApi.PLATFORM_IP);
//        parameter.put("clientIp", "");
//        parameter.put("clientMac", "");

        call(url, "login", parameter);
    }
}
