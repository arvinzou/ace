import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.rvc.hngdapi.pojo.ResourceDetail;
import com.huacainfo.ace.rvc.hngdapi.pojo.response.ResourceDetailResp;
import com.huacainfo.ace.rvc.kedapi.authorize.AuthorizeApi;
import com.huacainfo.ace.rvc.kedapi.common.constant.VideoConstant;
import com.huacainfo.ace.rvc.kedapi.control.ControlApi;
import com.huacainfo.ace.rvc.kedapi.control.model.RecordReq;
import com.huacainfo.ace.rvc.kedapi.control.model.terminal.TerminalReq;
import com.huacainfo.ace.rvc.kedapi.manage.ManageApi;
import com.huacainfo.ace.rvc.kedapi.manage.model.create.CreateRequest;
import com.huacainfo.ace.rvc.kedapi.vrs.VRSApi;
import com.huacainfo.ace.rvc.kedapi.vrs.model.LiveRoomResp;
import com.huacainfo.ace.rvc.kedapi.vrs.model.LocalLoginResp;
import com.huacainfo.ace.rvc.vo.ConferenceDTO;
import com.huacainfo.ace.rvc.vo.JoinMember;
import com.huacainfo.ace.rvc.vo.SearchCondition;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Arvin on 2017/11/20.
 */
public class HelloWorld {

    private void CreateDTO() {
        ConferenceDTO dto = new ConferenceDTO();
        dto.setTitle("常德市党建会议");
        dto.setSubtitle("会议议程：" +
                "1、议程1。" +
                "2、议程2" +
                "3、议程3");
        dto.setEmceeId("eUserID");
        dto.setEmceeName("主持人-Mir朱");
        dto.setAddressId("addres-id");
        dto.setAddressName("常德市市政协会议室");

        dto.setBeginDate(DateUtil.getNow());
        dto.setEndDate(DateUtil.getNow());

        List<JoinMember> members = new ArrayList<>();
        JoinMember vip = new JoinMember();
        vip.setUserId("");
        vip.setUserName("Mir朱");
        vip.setLevel("2");
        JoinMember normal = new JoinMember();
        normal.setUserId("id1");
        normal.setUserName("普通人1");
        normal.setLevel("1");
        JoinMember normal2 = new JoinMember();
        normal2.setUserId("id2");
        normal2.setUserName("普通人2");
        normal2.setLevel("1");
        members.add(vip);
        members.add(normal);
        members.add(normal2);

        dto.setInviteList(members);

        System.out.println(dto.toString());
    }

    private void searchCondition() {
        SearchCondition condition = new SearchCondition();
        condition.setTitle("Arvin");
        condition.setStart(0);
        condition.setLimit(10);

        condition.setSearchRegion("related");

        System.out.println(condition.toString());
    }


    @Test
    public void test() {

        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><table>\n" +
                "<head>\n" +
                "<result result_code=\"0\" message=\"成功\" size=\"2\" />\n" +
                "</head>\n" +
                "<rows>\n" +
                "<row i_id=\"134\" i_org_id=\"8\" c_device_index_code=\"001144\" c_name=\"市智慧党建办会议室\" i_channel_no=\"1\" i_camera_type=\"0\" i_record_location_set=\"null\" i_stream_link_type=\"1\" i_stream_type=\"0\" c_matrix_code=\"null\" i_pixel=\"1\" i_ptz_type=\"4\" i_is_bind_audio=\"0\" i_main_bit_rate=\"0\" i_main_rate_type=\"0\" c_stream_url=\"null\" c_management=\"null\" i_layout_type=\"null\" i_matrix_camera_index=\"null\" i_view_horangle=\"null\" i_view_angle=\"null\" i_view_length=\"null\" i_view_visible_radius=\"null\" i_view_ver_angle=\"null\" i_view_tilt=\"null\" i_view_zoom=\"null\" i_is_infrared=\"0\" i_is_auto_follow=\"0\" i_is_intelligent=\"0\" i_is_high_digital=\"0\" i_is_visual=\"0\" c_install_address=\"null\" c_install_time=\"null\" i_install_way=\"null\" i_init_pos=\"null\" i_charge_dept=\"null\" i_integration_type=\"null\" i_area_type=\"null\" i_video_build_type=\"null\" i_video_type=\"null\" i_delay_build=\"null\" c_description=\"null\" c_assign_time=\"2018-02-01 10:03:43\" i_is_online=\"1\" i_domain_id=\"3\" c_longitude=\"null\" c_latitude=\"null\" c_pinyin_code_all=\"shizhihuidangjianbanhuiyishi\" c_index_code=\"001146\" c_biz_code=\"null\" c_pinyin_code=\"szhdjbhys\" i_status=\"0\" c_create_time=\"2018-02-01 10:03:08\" c_creator=\"admin\" c_update_time=\"2018-02-06 00:06:03\" i_group_count=\"0\" c_config_param=\"null\" i_transform_bandwidth_limit=\"null\" i_key_control=\"null\" c_cascade_code=\"null\" c_cascade_privilege=\"null\" c_decode_tag=\"null\" i_play_type=\"null\" i_passageway=\"null\" i_res_type=\"10000\" i_device_res_type=\"30000\" i_type=\"1\" c_channel_identify=\"1\" i_enable=\"1\" i_sequence_idx=\"-1\" c_ezviz_encrypt=\"null\" i_is_encrypt=\"null\" i_is_share=\"null\" c_ezviz_camera_code=\"null\" c_osd_xml=\"null\" i_height=\"null\" c_device_info=\"null\" c_position=\"null\" i_is_voice_intercom=\"null\" i_channel_indexcode=\"null\" i_talk_channel_no=\"null\" c_relation_index_code=\"null\" c_org_name=\"市智慧党建办\" c_org_index_code=\"001126\" c_device_name=\"市智慧党建办\" c_device_ip=\"59.231.65.113\" i_device_port=\"9351\" i_manufacturer=\"0\" i_device_type=\"0\" i_device_detail_type=\"7\"  />\n" +
                "<row i_id=\"135\" i_org_id=\"8\" c_device_index_code=\"001144\" c_name=\"市智慧党建办走廊\" i_channel_no=\"2\" i_camera_type=\"0\" i_record_location_set=\"null\" i_stream_link_type=\"1\" i_stream_type=\"0\" c_matrix_code=\"null\" i_pixel=\"1\" i_ptz_type=\"4\" i_is_bind_audio=\"0\" i_main_bit_rate=\"0\" i_main_rate_type=\"0\" c_stream_url=\"null\" c_management=\"null\" i_layout_type=\"null\" i_matrix_camera_index=\"null\" i_view_horangle=\"null\" i_view_angle=\"null\" i_view_length=\"null\" i_view_visible_radius=\"null\" i_view_ver_angle=\"null\" i_view_tilt=\"null\" i_view_zoom=\"null\" i_is_infrared=\"0\" i_is_auto_follow=\"0\" i_is_intelligent=\"0\" i_is_high_digital=\"0\" i_is_visual=\"0\" c_install_address=\"null\" c_install_time=\"null\" i_install_way=\"null\" i_init_pos=\"null\" i_charge_dept=\"null\" i_integration_type=\"null\" i_area_type=\"null\" i_video_build_type=\"null\" i_video_type=\"null\" i_delay_build=\"null\" c_description=\"null\" c_assign_time=\"2018-02-01 10:03:43\" i_is_online=\"1\" i_domain_id=\"3\" c_longitude=\"null\" c_latitude=\"null\" c_pinyin_code_all=\"shizhihuidangjianbanzoulang\" c_index_code=\"001145\" c_biz_code=\"null\" c_pinyin_code=\"szhdjbzl\" i_status=\"0\" c_create_time=\"2018-02-01 10:03:08\" c_creator=\"admin\" c_update_time=\"2018-02-06 00:06:03\" i_group_count=\"0\" c_config_param=\"null\" i_transform_bandwidth_limit=\"null\" i_key_control=\"null\" c_cascade_code=\"null\" c_cascade_privilege=\"null\" c_decode_tag=\"null\" i_play_type=\"null\" i_passageway=\"null\" i_res_type=\"10000\" i_device_res_type=\"30000\" i_type=\"1\" c_channel_identify=\"2\" i_enable=\"1\" i_sequence_idx=\"0\" c_ezviz_encrypt=\"null\" i_is_encrypt=\"null\" i_is_share=\"null\" c_ezviz_camera_code=\"null\" c_osd_xml=\"null\" i_height=\"null\" c_device_info=\"null\" c_position=\"null\" i_is_voice_intercom=\"null\" i_channel_indexcode=\"null\" i_talk_channel_no=\"null\" c_relation_index_code=\"null\" c_org_name=\"市智慧党建办\" c_org_index_code=\"001126\" c_device_name=\"市智慧党建办\" c_device_ip=\"59.231.65.113\" i_device_port=\"9351\" i_manufacturer=\"0\" i_device_type=\"0\" i_device_detail_type=\"7\"  />\n" +
                "</rows>\n" +
                "</table>";


        String json = "";//XmlConverUtil.xmltoJson(xml).replace("@", "");
        ResourceDetailResp rsp = JsonUtil.toObject(json, ResourceDetailResp.class);

        System.out.println(json);
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


        System.out.println(VRSApi.getLiveURL(object));
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
