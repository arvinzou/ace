package com.huacainfo.ace.glink.api;

import com.huacainfo.ace.common.plugins.wechat.util.HttpKit;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.glink.api.pojo.light.HeartRst;

/**
 * @ClassName LApiToolKit
 * @Description 弱电接口--调用工具
 * @Author Arvin Zou
 * @Date 2019/4/15 9:57
 */
public class LApiToolKit {

    private static final String domain = "http://116.228.110.86:8092";

    private LApiToolKit instance;

    /**
     * 单例模式
     */
    private LApiToolKit() {

    }

    /**
     * 获取区级平台信息接口
     *
     * @return code    int	Y	返回码: 200: 成功;400: 失败
     * message	String	N	返回描述
     * data	Object	N	返回数据
     * area	String	Y	分区编号
     * status	int	Y	平台状态(1:正常 2:异常)
     * infos	ErrData数组	N	平台异常数据集合
     * type	int	Y	异常类型（1:服务器异常 2：网络异常 3：其它异常）
     * reason	String	Y	异常的具体说明
     */
    public static HeartRst heart() {
        String path = "/wh/status/control/heart";

        String rstJson = HttpKit.get(domain + path);

        return JsonUtil.toObject(rstJson, HeartRst.class);
    }

    public LApiToolKit newInstance() {
        if (instance == null) {
            return new LApiToolKit();
        }

        return instance;
    }
}
