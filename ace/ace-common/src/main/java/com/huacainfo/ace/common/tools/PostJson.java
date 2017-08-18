package com.huacainfo.ace.common.tools;

/**
 * Created by chenxiaoke on 2017/8/17.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.pushmsg.CommonUtil;

public class PostJson {
	public static void main(String[] args) {
		String url="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=UbiOqQ2OYa5lQ3y205Cj2oN4qt9jt3rHcQAL4AKtQYPuHtKeFQWUCxktJa-m92_-flUwL1oauaFbdpMjCMUh8Lyb5usxtwccTeBNCYb3-feGgsG-loQXGf90L1Eg1EOvNQZaAEACFO";
		String text=FileUtil.ReadFile("/Users/chenxiaoke/Documents/github/ace/ace/ace/ace-common/src/main/java/com/huacainfo/ace/common/tools/notice.json");
		JSONObject json=JSON.parseObject(text);
		JSONObject rst=CommonUtil.httpsRequest(url,"POST",json.toJSONString());
		System.out.println(rst.toString());
	}

}
