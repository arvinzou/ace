import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.common.tools.StringUtils;
import com.huacainfo.ace.rvc.kedapi.authorize.AuthorizeApi;
import com.huacainfo.ace.rvc.kedapi.common.constant.VideoConstant;
import com.huacainfo.ace.rvc.kedapi.common.kits.HttpKit;
import com.huacainfo.ace.rvc.kedapi.control.ControlApi;
import com.huacainfo.ace.rvc.kedapi.control.model.RecordReq;
import com.huacainfo.ace.rvc.kedapi.control.model.terminal.TerminalReq;
import com.huacainfo.ace.rvc.kedapi.manage.ManageApi;
import com.huacainfo.ace.rvc.kedapi.manage.model.create.CreateRequest;
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
//        RvcConference conference = new RvcConference();
//        conference.setTitle("接口创建测试会议");
//        conference.setBeginDate(DateUtil.getNow());
//        System.out.println(JsonUtil.toJson(conference));
//                System.out.println(GUIDUtil.getGUID());
        AuthorizeApi.init();

        ControlApi.getList(0, 10,
                AuthorizeApi.ACCOUNT_TOKEN, AuthorizeApi.SSO_COOKIE_KEY);

    }


    private void meeting() {
        //        同一登录
//        login();
        String token = "92dd1035883018b7955059ff969dacda";
        String cookies = "SSO_COOKIE_KEY=28fe979d-94dc-4582-909f-13fb69fd070e;";
//        //create      7360039
//        String confId = create(token, cookies);


        //添加本级终端
//        addMts("7360039","192.168.20.230",7,token,cookies);//mt_id=1
//        addMts("7360039","192.168.0.163",7,token,cookies);//mt_id=2
//        addMts("7360039","0736110000000",5,token,cookies);//mt_id=3
        //指定发言人
//        ControlApi.speaker("7360039", 1, token, cookies);
        //开启直播
        record("7360039", token, cookies);

        //delete
//        MangeApi.delete("bb679d2905e2f8f97187ba8c2cb8be42", "7360027", "SSO_COOKIE_KEY=aef0dc97-d4e4-403c-b181-dfb37088f1c7;");

//        String cookieStr = "eos_style_cookie=default; SSO_COOKIE_KEY=\"\"; Domain=192.168.20.240; Expires=Thu, 01-Jan-1970 00:00:10 GMT; Path=/";
//        System.out.println(AuthorizeApi.getSsoCookieKey(cookieStr));


        //VRS
//        vrs(token, "接口创建");


        //others
        String json = HttpKit.loadJson("http://192.168.20.243/hlsfile/2017/12/06/20171206101528_574/Readme.json"
                , StringUtils.CHARSET_NAME);
//
        System.out.println("============================");
        System.out.println("json:" + json);
    }

    private void record(String confId, String token, String cookies) {
        RecordReq req = new RecordReq("XXX的直播", VideoConstant.RecordMode.LIVE);
        ControlApi.record(confId, req, token, cookies);
    }

    private void addMts(String confId, String account, int accountType, String token, String cookies) {
        //添加本级终端
        List<TerminalReq> terminals = new ArrayList<>();
        TerminalReq t1 = new TerminalReq(account, accountType);
        terminals.add(t1);
        Map<String, List<TerminalReq>> params = new HashMap<>();
        params.put("mts", terminals);

        ControlApi.addMts(confId, JsonUtil.toJson(params), token, cookies);
    }

    private void vrs(String token, String keyWords) {
        LocalLoginResp loginResp = VRSApi.localLogin(token, "admin", "admin");
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
        String token = AuthorizeApi.getToken(AuthorizeApi.APP_KEY, AuthorizeApi.APP_SECRET);
        System.out.println("===============token========> " + token);
        Map<String, String> map = AuthorizeApi.login("admin1", "888888", token);
        String cookies = map.get("cookies");
        System.out.println("===========: " + cookies);
    }

    public String create(String token, String cookies) {
        CreateRequest request = new CreateRequest("接口创建會議：" + DateUtil.getNow());
        return ManageApi.create(token, cookies, request);
    }

}
