package com.huacainfo.ace.rvc.hngdapi.util;

import com.huacainfo.ace.rvc.util.ResultUtil;
import org.apache.axis.AxisProperties;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Arvin on 2018/2/1.
 */
public class WebServiceCallUtil {
    private WebServiceCallUtil() {

    }

    public static String call(String endpoint, String namespaceURI, String methodName,
                              LinkedHashMap<String, Object> parameter) {
        AxisProperties.setProperty("axis.socketSecureFactory", "org.apache.axis.components.net.SunFakeTrustSocketFactory");

        try {
            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(endpoint);
            // WSDL里面描述的接口名称
            call.setOperationName(new QName(namespaceURI, methodName));
            // 接口的参数
            //设置参数名 id  第二个参数表示String类型,第三个参数表示入参
            List<Object> pValues = new LinkedList<>();
            String key;
            Object value;
            for (Map.Entry<String, Object> entry : parameter.entrySet()) {
                key = entry.getKey();
                value = entry.getValue();
                if (value instanceof String) {
                    call.addParameter(key, XMLType.XSD_STRING, ParameterMode.IN);
                } else if (value instanceof Integer) {
                    call.addParameter(key, XMLType.XSD_INTEGER, ParameterMode.IN);
                }

                pValues.add(value);
            }
            // 设置返回类型
            call.setReturnType(XMLType.XSD_STRING);
            // 给方法传递参数值，并且调用方法
//            Object[] objs = new Object[pValues.size()];
//            for (int i = 0; i < pValues.size(); i++) {
//                objs[i] = pValues.get(i);
//            }
            String result = (String) call.invoke(pValues.toArray());

            System.out.println("=========================result is ===============================");
            System.out.println(result);
            return result;
        } catch (Exception e) {
            return ResultUtil.FAIL;
        }
    }


}
