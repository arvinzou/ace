import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.common.tools.StringUtils;
import com.huacainfo.ace.rvc.kedapi.authorize.AuthorizeApi;
import com.huacainfo.ace.rvc.kedapi.common.kits.CommonKit;
import com.huacainfo.ace.rvc.kedapi.common.kits.HttpKit;
import com.huacainfo.ace.rvc.kedapi.control.ControlApi;
import com.huacainfo.ace.rvc.kedapi.control.model.RecordReq;
import com.huacainfo.ace.rvc.kedapi.control.model.TerminalReq;
import com.huacainfo.ace.rvc.kedapi.manage.MangeApi;
import com.huacainfo.ace.rvc.kedapi.manage.model.CreateRequest;
import com.huacainfo.ace.rvc.kedapi.vrs.VRSApi;
import com.huacainfo.ace.rvc.kedapi.vrs.model.LiveRoomResp;
import com.huacainfo.ace.rvc.kedapi.vrs.model.LocalLoginResp;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Arvin on 2017/11/20.
 */
public class HelloWorld {

    @Test
    public void test() {
//        同一登录
//        login();
        String token = "f0e6ff2bd97c4da68ab3311cd3b43b45";
        String cookies = "SSO_COOKIE_KEY=f36212d3-d225-4d7d-af66-04ef870e8166";
//        //create      7360038
//        String confId = create(token, cookies);


        //添加本级终端
//        addMts("7360038","192.168.20.230",7,token,cookies);//mt_id=1
//        addMts("7360038","192.168.0.163",7,token,cookies);//mt_id=2
//        addMts("7360038","0736110000000",5,token,cookies);//mt_id=3
        //指定发言人
//        ControlApi.speaker("7360038", "1", token, cookies);
        //开启直播
//        record("7360038", token, cookies);

        //delete
//        MangeApi.delete("bb679d2905e2f8f97187ba8c2cb8be42", "7360027", "SSO_COOKIE_KEY=aef0dc97-d4e4-403c-b181-dfb37088f1c7;");

//        String cookieStr = "eos_style_cookie=default; SSO_COOKIE_KEY=\"\"; Domain=192.168.20.240; Expires=Thu, 01-Jan-1970 00:00:10 GMT; Path=/";
//        System.out.println(CommonKit.getSsoCookieKey(cookieStr));


        //VRS
//        vrs(token, "API");


        //others
        String json = HttpKit.loadJson("http://192.168.20.243/hlsfile/2017/11/30/20171130162544_431/Readme.json"
                , StringUtils.CHARSET_NAME);

        System.out.println("============================");
        System.out.println("json:" + json);
    }

    private void record(String confId, String token, String cookies) {
        RecordReq req = new RecordReq();
        req.setVideo_name("7360038Record20171130");
        req.setRecorder_type(1);//录像类型 1-会议录像；2-终端录像；
        req.setPublish_mode(0);//发布模式 0-不发布；1-发布；
        req.setAnonymous(0);//是否支持免登陆观看直播 0-不支持；1-支持；
        req.setRecorder_mode(2);//录像模式 1-录像；2-直播；3-录像+直播；
        req.setMain_stream(1);//是否录主格式码流（视频+音频） 0-否；1-是；
        req.setDual_stream(0);//是否录双流（仅双流） 0-否；1-是；
        req.setMembers(null);//开启录像终端数组

        ControlApi.record(confId, token, req, cookies);
    }

    private void addMts(String confId, String account, int accountType, String token, String cookies) {
        //添加本级终端
        List<TerminalReq> terminals = new ArrayList<>();
        TerminalReq t1 = new TerminalReq();
        t1.setAccount(account);
        t1.setAccount_type(accountType);//IP地址
        t1.setBitrate(2048);
        t1.setProtocol(0);
        t1.setForced_call(0);
        terminals.add(t1);

        Map<String, List<TerminalReq>> params = new HashMap<>();
        params.put("mts", terminals);
        ControlApi.addMts(confId, JsonUtil.toJson(params), token, cookies);
    }

    private void vrs(String token, String keyWords) {
        Map<String, String> params = new HashMap<>();
        params.put("username", "admin");
        params.put("psd", "admin");
        LocalLoginResp loginResp = VRSApi.localLogin(token, JsonUtil.toJson(params));
        String cookies = "SSO_COOKIE_KEY=" + loginResp.getToken();
        LiveRoomResp object = VRSApi.getLiveRoomList(token, keyWords, cookies);
        System.out.println("LiveRoomResp=>" + object.toString());
//        {   "roomstate" : [      {         "elapse" : 121,         "ip" : 3232240883,         "lcastpoint" : "API接口创建会议20171130-1-20171130162544350",         "livestreampath" : "/hlsfile/2017/11/30/20171130162544_431/Readme.json",         "livetime" : 0,         "prgid" : 16777217,         "roomid" : 16777217,         "roomname" : "API接口创建会议20171130-1",         "userdomainmoid" : ""      }   ],   "success" : 1}
//        {"error_code":0,"pageid":0,"roomcount":0,"roomstate":[{"createtime":0,"elapse":121,"lcastpoint":"API接口创建会议20171130-1-20171130162544350","livestatnum":0,"livestreampath":"/hlsfile/2017/11/30/20171130162544_431/Readme.json","prgid":16777217,"roomid":16777217,"roomname":"API接口创建会议20171130-1","userdomainmoid":""}],"success":1}
//        http://192.168.20.243/hlsfile/2017/11/30/20171130162544_431/1280_720/playlist.m3u8
    }

    public void heartBeat() {
        //保活
        String sckey = "SSO_COOKIE_KEY=7472a0f4-e031-4474-af38-0f72f7f8e249";
        AuthorizeApi.hearBeat("703b7dc62df2125247ab56821b55f526", sckey);
    }

    public void login() {
        //        //login and authorize
        String token = AuthorizeApi.getToken(CommonKit.APP_KEY, CommonKit.APP_SECRET);
        System.out.println("===============token========> " + token);
        Map<String, String> map = AuthorizeApi.login("admin1", "888888", token);
        String cookies = map.get("cookies");
        System.out.println("===========: " + cookies);
    }

    public String create(String token, String cookies) {
        Map map = JsonUtil.toMap(CreateRequest.DEFAULT_PARAMS);
        map.put("name", "API接口创建会议20171130");

        return MangeApi.create(token, JsonUtil.toJson(map), cookies);
    }

}
