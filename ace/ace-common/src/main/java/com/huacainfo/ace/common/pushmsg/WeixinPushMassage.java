package com.huacainfo.ace.common.pushmsg;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 微信推送信息
 * @author My
 * @CreateDate 2016-1-19
 * @param
 */
public class WeixinPushMassage {

public void wxTuisong(Massages massages){
		
		//微信接口
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
		Token token = headtoken ("wx9841030479d77c4c","f4bf53f5fa00658a3ad9d1e2770db545");//获取token
		url = url.replace("ACCESS_TOKEN", token.getAccessToken());//转换为响应接口模式

		//封装数据
		JSONObject json = new JSONObject();
		json.put("touser", massages.getWxName());//接收者wxName
		json.put("template_id", "gqOHkN1eezxkvU5jc_UiQTBbStbek_iMNuG7h6ne7I8");//消息模板
		//json.put("url", "http://weix");//填写url可查看详情
		
		JSONObject dd = new JSONObject();
		
		JSONObject dd2 = new JSONObject();
		dd2.put("value", "您的订单有了变动，请注意查看...");//消息提示
		dd2.put("color", "#173177");
		dd.put("first", dd2);
		
	    JSONObject cc2 = new JSONObject();
	    cc2.put("value", massages.getWayBillid());//订单号
	    cc2.put("color", "#173177");
		dd.put("order_id", cc2);
		
		JSONObject ee2 = new JSONObject();
		ee2.put("value", "33064587");//货物类型
		ee2.put("color", "#173177");
		dd.put("package_id", ee2);
	
		JSONObject gg2 = new JSONObject();
		gg2.put("value", "发货人："+massages.getSndPerson()+massages.getCellPhone()+"\n收货人："+massages.getRcvPerson()+massages.getRcvPhone()+"操作类型"+massages.getLastTime()+massages.getLastType()+"\n\n\n");
		//gg2.put("color", "#173177");
		dd.put("remark", gg2);
		
		json.put("data", dd);
		System.out.println(json.toString());
		JSONObject js = CommonUtil.httpsRequest(url, "POST", json.toString());
		System.out.println("js=="+js);
	}

/**
 * 请求token
 * @Description :
 * @param 
 * @return 
 * ---------------
 * @Author  : My
 * @CreateData : 2016-1-18
 */
public static Token headtoken (String appId,String appSrecet){
	Token token = new Token();
	token = CommonUtil.getToken(appId, appSrecet);
	return token;
}
}
