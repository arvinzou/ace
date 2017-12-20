package com.huacainfo.ace.rvc.kedapi.control;

import com.huacainfo.ace.common.tools.HttpSend;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.common.tools.StringUtils;
import com.huacainfo.ace.rvc.kedapi.authorize.AuthorizeApi;
import com.huacainfo.ace.rvc.kedapi.common.base.BaseApi;
import com.huacainfo.ace.rvc.kedapi.common.kits.CommonKit;
import com.huacainfo.ace.rvc.kedapi.common.kits.HttpKit;
import com.huacainfo.ace.rvc.kedapi.control.model.RecordListResp;
import com.huacainfo.ace.rvc.kedapi.control.model.RecordReq;
import com.huacainfo.ace.rvc.kedapi.control.model.RecordStatusResp;
import com.huacainfo.ace.rvc.kedapi.control.model.conference.VideoConfListResp;
import com.huacainfo.ace.rvc.kedapi.control.model.conference.VideoConfResp;
import com.huacainfo.ace.rvc.kedapi.control.model.terminal.TerminalResp;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 科达视讯平台会控API
 * @author: ArvinZou
 * @create: 2017-11-16 15:56
 */
public class ControlApi extends BaseApi {
    private static Logger logger = Logger.getLogger(ControlApi.class);

    /**
     * 名称	获取视频会议信息
     * URI	/api/v1/vc/confs/{conf_id}
     * 方法	GET
     * 说明	获取视频会议详细信息
     *
     * @param confId
     * @param accountToken 登陆token
     * @param cookies
     * @return List<VideoConfResp>
     */
    public static VideoConfResp get(String confId, String accountToken, String cookies) {
        String uri = AuthorizeApi.URI + "/api/v1/vc/confs" + confId;
        Map<String, Object> params = new HashMap<>();
        params.put("account_token", accountToken);

        String respStr = HttpKit.doGet(uri, "", cookies);
        logger.debug("" + respStr);

        return JsonUtil.toObject(respStr, VideoConfResp.class);

    }


    /**
     * 名称	获取视频会议列表
     * URI	/api/v1/vc/confs
     * 方法	GET
     * 说明	获取视频会议列表
     *
     * @param start        获取的视频会议列表的起始会议位置, 0表示第一个会议, 默认为0
     * @param count        获取的视频会议的个数, 即包括start在内的后count个会议, 0代表获取所有会议, 默认为10
     * @param accountToken 登陆token
     * @param cookies
     * @return List<VideoConfResp>
     */
    public static List<VideoConfResp> getList(int start, int count, String accountToken, String cookies) {
        String uri = AuthorizeApi.URI + "/api/v1/vc/confs";
        Map<String, Object> params = new HashMap<>();
        params.put("account_token", accountToken);
        params.put("start", start < 0 ? 0 : start);
        params.put("count", count < 10 ? 10 : count);

        String respStr = HttpKit.doGet(uri, "", cookies);
        logger.debug("" + respStr);

        VideoConfListResp list = JsonUtil.toObject(respStr, VideoConfListResp.class);

        return list.getConfs();
    }

    /**
     * 名称	开启录像
     * URI	/api/v1/vc/confs/{conf_id}/recorders
     * 方法	POST
     * 说明	开启录像，异步操作
     *
     * @param confId       会议ID
     * @param accountToken 授权秘钥
     * @param params       录像参数
     * @return rec_id/fail
     */
    public static String record(String confId, RecordReq params, String accountToken, String cookies) {
        String encode = CommonKit.encode(JsonUtil.toJson(params));
        if (StringUtils.isEmpty(encode)) {
            return CommonKit.fail();
        }
        Map<String, String> rtnMap = HttpKit.doPost(AuthorizeApi.URI + "/api/v1/vc/confs/" + confId + "/recorders"
                , "account_token=" + accountToken + "&params=" + encode
                , cookies);
        logger.debug("confId<" + confId + ">, ControlApi.record: " + rtnMap.toString());
//        {
//            "success": 1,
//                "rec_id": "1"
//        }
//        {
//            "success": 0,
//                "error_code": 112230
//        }

        if (JsonUtil.toMap(rtnMap.get("resp")).get("success") == 1) {
            return (String) JsonUtil.toMap(rtnMap.get("resp")).get("rec_id");
        } else {
            return CommonKit.fail();
        }
    }

    /**
     * 名称	获取录像状态
     * URI	/api/v1/vc/confs/{conf_id}/recorders/{rec_id}
     * 方法	GET
     * 说明	获取录像状态，异步操作
     *
     * @param confId       视讯会议ID
     * @param recId        录像ID
     * @param accountToken 授权token
     * @return RecordStatusResp
     */
    public static RecordStatusResp getRecordStatus(String confId, String recId, String accountToken) {
        String respStr = HttpSend.getSend(
                AuthorizeApi.URI + "/api/v1/vc/confs/" + confId + "/recorders/" + recId,
                "account_token=" + accountToken);

        return JsonUtil.toObject(respStr, RecordStatusResp.class);
    }


    /**
     * 名称	获取录像列表
     * URI	/api/v1/vc/confs/{conf_id}/recorders
     * 方法	GET
     * 说明	获取录像列表
     *
     * @param confId       视讯会议ID
     * @param accountToken 授权token
     * @return RecordListResp
     */
    public static RecordListResp getRecordList(String confId, String accountToken) {
        String respStr = HttpSend.getSend(
                AuthorizeApi.URI + "/api/v1/vc/confs/" + confId + "/recorders",
                "account_token=" + accountToken);

        return JsonUtil.toObject(respStr, RecordListResp.class);
    }

    /**
     * 名称	修改录像状态
     * URI	/api/v1/vc/confs/{conf_id}/recorders/{rec_id}/state
     * 方法	PUT
     * 说明	修改录像状态，异步操作
     *
     * @param confId       视讯会议ID
     * @param recId        录像ID
     * @param accountToken 授权token
     * @param params       请求参数 {    "value": 1,    "recorder_mode": 1    }
     * @return success/fail
     */
    public static String updateRecordStatus(String confId, String recId, String accountToken, Map<String, Integer> params) {

        String encode = CommonKit.encode(JsonUtil.toJson(params));
        if (StringUtils.isEmpty(encode)) {
            return CommonKit.fail();
        }

        String respStr = HttpSend.getSend(
                AuthorizeApi.URI + "/api/v1/vc/confs/" + confId + "/recorders/" + recId + "/state",
                "account_token=" + accountToken + "&_method=PUT&params=" + encode);

        if (JsonUtil.toMap(respStr).get("success") == 1) {
            return (String) JsonUtil.toMap(respStr).get("rec_id");
        } else {
            return CommonKit.fail();
        }
    }

    /**
     * 名称	停止录像
     * URI	/api/v1/vc/confs/{conf_id}/recorders/{rec_id}
     * 方法	DELETE
     * 说明	停止录像，异步操作
     *
     * @param confId       视讯会议ID
     * @param recId        录像ID
     * @param accountToken 授权token
     * @param recorderMode 录像模式 1-录像；2-直播；3-录像+直播；
     * @return success/fail
     */
    public static String stopRecord(String confId, String recId, String accountToken, int recorderMode) {
        Map<String, Object> params = new HashMap<>();
        params.put("recorder_mode", recorderMode);
        String encode = CommonKit.encode(JsonUtil.toJson(params));
        if (StringUtils.isEmpty(encode)) {
            return CommonKit.fail();
        }

        String respStr = HttpSend.getSend(
                AuthorizeApi.URI + "/api/v1/vc/confs/" + confId + "/recorders/" + recId,
                "account_token=" + accountToken + "&_method=DELETE&params=" + encode);

        if (JsonUtil.toMap(respStr).get("success") == 1) {
            return CommonKit.success();
        } else {
            return CommonKit.fail();
        }
    }

    /**
     * 名称	批量添加本级终端
     * URI	/api/v1/vc/confs/{conf_id}/mts
     * 方法	POST
     * 说明	批量添加本级终端，添加终端为同步操作，等待终端上线为异步操作
     *
     * @param confId  会议id
     * @param token   软件权限token
     * @param params  请求内容，以JSON形式发送，需进行UrlEncode => TerminalReq
     * @param cookies 登录授权后，携带信息
     * @return result
     */
    public static TerminalResp addMts(String confId, String params, String token, String cookies) {
        String encode = CommonKit.encode(params);
        if (StringUtils.isEmpty(encode)) {
            return null;
        }

        Map<String, String> rtnMap = HttpKit.doPost(AuthorizeApi.URI + "/api/v1/vc/confs/" + confId + "/mts",
                "account_token=" + token + "&params=" + encode, cookies);
        logger.debug("confId<" + confId + ">,ControlApi.addMts:" + rtnMap.toString());

        TerminalResp resp = JsonUtil.toObject(rtnMap.get("resp"), TerminalResp.class);
        return resp;
    }

    /**
     * 名称	批量删除终端
     * URI	/api/v1/vc/confs/{conf_id}/mts
     * 方法	DELETE
     * 说明	批量删除终端，删除本级终端为同步操作，删除下级终端为异步操作
     *
     * @param confId  会议id
     * @param params  请求内容，以JSON形式发送，需进行UrlEncode
     * @param token   软件权限token
     * @param cookies 登录授权后，携带信息
     */
    public static String deleteMts(String confId, String params, String token, String cookies) {
//        {
//            "mts": [
//            {
//                "mt_id": "1"
//            },
//            {
//                "mt_id": "2"
//            }
//  ]
//        }
        String encode = CommonKit.encode(params);
        if (StringUtils.isEmpty(encode)) {
            return CommonKit.fail();
        }

        Map<String, String> rtnMap = HttpKit.doPost(AuthorizeApi.URI + "/api/v1/vc/confs/" + confId + "/mts",
                "account_token=" + token + "&_method=DELETE&params=" + encode, cookies);
        logger.debug("ControlApi.deleteMts:" + rtnMap.toString());

        return response(rtnMap.get("resp"));
    }

    /**
     * 名称	批量呼叫终端
     * URI	/api/v1/vc/confs/{conf_id}/online_mts
     * 方法	POST
     * 说明	批量呼叫终端，异步操作
     *
     * @param confId  会议id
     * @param params  请求内容，以JSON形式发送，需进行UrlEncode
     * @param token   软件权限token
     * @param cookies 登录授权后，携带信息
     * @return fail/success
     */
    public static String callOnlineMts(String confId, String params, String token, String cookies) {
//        {
//            "mts": [
//            {
//                "mt_id": "1",
//                "forced_call": 0  //是否强制呼叫，默认是0 0-不强呼；1-强呼；
//            }
//            ]
//        }

        String encode = CommonKit.encode(params);
        if (StringUtils.isEmpty(encode)) {
            return CommonKit.fail();
        }

        Map<String, String> rtnMap = HttpKit.doPost(AuthorizeApi.URI + "/api/v1/vc/confs/" + confId + "/online_mts",
                "account_token=" + token + "&params=" + encode, cookies);
        logger.debug("ControlApi.callOnlineMts:" + rtnMap.toString());

        return response(rtnMap.get("resp"));
    }

    /**
     * 名称	批量挂断终端
     * URI	/api/v1/vc/confs/{conf_id}/online_mts
     * 方法	DELETE
     * 说明	批量挂断终端，同步操作
     *
     * @param confId  会议id
     * @param params  请求内容，以JSON形式发送，需进行UrlEncode
     * @param token   软件权限token
     * @param cookies 登录授权后，携带信息
     * @return fail/success
     */
    public static String deleteOnlineMts(String confId, String params, String token, String cookies) {
//        {
//            "mts": [
//            {
//                "mt_id": "1", //终端号 最大字符长度：48个字节
//            }
//            ]
//        }
        String encode = CommonKit.encode(params);
        if (StringUtils.isEmpty(encode)) {
            return CommonKit.fail();
        }
        Map<String, String> rtnMap = HttpKit.doPost(AuthorizeApi.URI + "/api/v1/vc/confs/" + confId + "/online_mts",
                "account_token=" + token + "&params=" + encode + "&_method=DELETE", cookies);
        logger.debug("ControlApi.deleteOnlineMts:" + rtnMap.toString());

        return response(rtnMap.get("resp"));
    }

    /**
     * 名称	会场静音操作
     * URI	/api/v1/vc/confs/{conf_id}/silence
     * 方法	PUT
     * 说明	会场静音操作，同步操作
     *
     * @param confId  会议id
     * @param params  请求内容，以JSON形式发送，需进行UrlEncode
     * @param token   软件权限token
     * @param cookies 登录授权后，携带信息
     * @return fail/success
     */
    public static String silence(String confId, String params, String token, String cookies) {
//        {
//            "value": 0    //静音状态 0-停止静音；1-静音；
//        }
        String encode = CommonKit.encode(params);
        if (StringUtils.isEmpty(encode)) {
            return CommonKit.fail();
        }
        Map<String, String> rtnMap = HttpKit.doPost(AuthorizeApi.URI + "/api/v1/vc/confs/" + confId + "/silence",
                "account_token=" + token + "&params=" + encode + "&_method=PUT", cookies);
        logger.debug("ControlApi.silence:" + rtnMap.toString());

        return response(rtnMap.get("resp"));
    }

    /**
     * 名称	会场哑音操作
     * URI	/api/v1/vc/confs/{conf_id}/mute
     * 方法	PUT
     * 说明	会场哑音操作，同步操作
     *
     * @param confId  会议id
     * @param params  请求内容，以JSON形式发送，需进行UrlEncode
     * @param token   软件权限token
     * @param cookies 登录授权后，携带信息
     * @return fail/success
     */
    public static String mute(String confId, String params, String token, String cookies) {
//        {
//            "value": 0    //静音状态 0-停止静音；1-静音；
//        }
        String encode = CommonKit.encode(params);
        if (StringUtils.isEmpty(encode)) {
            return CommonKit.fail();
        }
        Map<String, String> rtnMap = HttpKit.doPost(AuthorizeApi.URI + "/api/v1/vc/confs/" + confId + "/mute",
                "account_token=" + token + "&params=" + encode + "&_method=PUT", cookies);
        logger.debug("ControlApi.mute:" + rtnMap.toString());

        return response(rtnMap.get("resp"));
    }

    /**
     * 名称	开启会议画面合成
     * URI	/api/v1/vc/confs/{conf_id}/vmps
     * 方法	POST
     * 说明	开启会议画面合成，异步操作
     *
     * @param confId  会议id
     * @param params  请求内容，以JSON形式发送，需进行UrlEncode
     * @param token   软件权限token
     * @param cookies 登录授权后，携带信息
     * @return fail/success
     */
    public static String startVmps(String confId, String params, String token, String cookies) {
        String encode = CommonKit.encode(params);
        if (StringUtils.isEmpty(encode)) {
            return CommonKit.fail();
        }

        Map<String, String> rtnMap = HttpKit.doPost(AuthorizeApi.URI + "/api/v1/vc/confs/" + confId + "/vmps",
                "account_token=" + token + "&params=" + encode, cookies);
        logger.debug("ControlApi.startVmps:" + rtnMap.toString());

        return response(rtnMap.get("resp"));
    }

    /**
     * 名称	停止会议画面合成
     * URI	/api/v1/vc/confs/{conf_id}/vmps/{vmp_id}
     * 方法	DELETE
     * 说明	会议主画面合成，默认vmp_id为1，异步操作
     *
     * @param confId  会议id
     * @param params  请求内容，以JSON形式发送，需进行UrlEncode
     * @param token   软件权限token
     * @param cookies 登录授权后，携带信息
     * @return fail/success
     */
    public static String stopVmps(String confId, String params, String token, String cookies) {
        String encode = CommonKit.encode(params);
        if (StringUtils.isEmpty(encode)) {
            return CommonKit.fail();
        }

        Map<String, String> rtnMap = HttpKit.doPost(AuthorizeApi.URI + "/api/v1/vc/confs/" + confId + "/vmps/1",
                "account_token=" + token + "&params=" + encode, cookies);
        logger.debug("ControlApi.stopVmps:" + rtnMap.toString());

        return response(rtnMap.get("resp"));
    }

    /**
     * 名称	指定会议发言人
     * URI	/api/v1/vc/confs/{conf_id}/speaker
     * 方法	PUT
     * 说明	指定会议发言人，同步操作
     *
     * @param confId  会议id
     * @param mtId    设置发言人的终端号，取消发言人填空 最大字符长度：48个字节
     * @param token   软件权限token
     * @param cookies 登录授权后，携带信息
     * @return fail/success
     */
    public static String speaker(String confId, int mtId, String token, String cookies) {
        if (StringUtils.isEmpty(mtId + "") || StringUtils.isEmpty(confId)) {
            return CommonKit.fail();
        }
        String params = "{\"mt_id\": \"" + mtId + "\"}";
        String encode = CommonKit.encode(params);
        if (StringUtils.isEmpty(encode)) {
            return CommonKit.fail();
        }

        Map<String, String> rtnMap = HttpKit.doPost(AuthorizeApi.URI + "/api/v1/vc/confs/" + confId + "/speaker",
                "account_token=" + token + "&params=" + encode + "&_method=PUT", cookies);
        logger.debug("ControlApi.speaker:" + rtnMap.toString());

        return response(rtnMap.get("resp"));
    }
}
