package com.huacainfo.ace.rvc.kedapi.control.model;

import com.huacainfo.ace.rvc.kedapi.common.base.BaseModel;

import java.util.List;
import java.util.Map;

/**
 * @description: 获取录像状态, 成功回复示例
 * @author: ArvinZou
 * @create: 2017-11-16 16:30
 */
public class RecordStatusResp extends BaseModel {

//    {
//        "success": 1,
//            "video_name": "rectest",
//            "recorder_type": 2,
//            "state": 1,
//            "anonymous": 0,
//            "recorder_mode": 1,
//            "main_stream": 1,
//            "dual_stream": 1,
//            "publish_mode": 0,
//            "current_progress": 1,
//            "members": [
//        {
//            "mt_id": "1"
//        },
//        {
//            "mt_id": "2"
//        }
//  ]
//    }

    /**
     * 是否成功 1-S,0-F
     */
    private int success;

    /**
     * 录像机id 最大字符长度：48字节
     */
    private String rec_id;
    /**
     * 录像文件名 最大字符长度：128个字节
     */
    private int video_name;
    /**
     * 录像类型 1-会议录像；2-终端录像；
     */
    private int recorder_type;
    /**
     * 录像状态 0-未录像；1-正在录像；2-暂停；3-正在呼叫实体；4-准备录像；
     */
    private int state;
    /**
     * 是否支持免登陆观看直播 0-不支持；1-支持；
     */
    private int anonymous;
    /**
     * 录像模式 1-录像；2-直播；3-录像+直播；
     */
    private int recorder_mode;
    /**
     * 是否录主格式码流（视频+音频） 0-是；1-否；
     */
    private int main_stream;
    /**
     * 是否录双流 0-是；1-否；
     */
    private int dual_stream;
    /***
     * 发布类型 0-不发布；1-发布；
     */
    private int publish_mode;
    /**
     * 当前录像进度, 单位为: 秒
     */
    private int current_progress;
    /**
     * 获取录像状态终端数组
     * Map<"mt_id",obj>
     * {
     * "mt_id": "1"  终端id, 仅当开启终端录像需要 最大字符长度：48个字节
     * }
     */
    private List<Map<String, String>> members;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getVideo_name() {
        return video_name;
    }

    public void setVideo_name(int video_name) {
        this.video_name = video_name;
    }

    public int getRecorder_type() {
        return recorder_type;
    }

    public void setRecorder_type(int recorder_type) {
        this.recorder_type = recorder_type;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(int anonymous) {
        this.anonymous = anonymous;
    }

    public int getRecorder_mode() {
        return recorder_mode;
    }

    public void setRecorder_mode(int recorder_mode) {
        this.recorder_mode = recorder_mode;
    }

    public int getMain_stream() {
        return main_stream;
    }

    public void setMain_stream(int main_stream) {
        this.main_stream = main_stream;
    }

    public int getDual_stream() {
        return dual_stream;
    }

    public void setDual_stream(int dual_stream) {
        this.dual_stream = dual_stream;
    }

    public int getPublish_mode() {
        return publish_mode;
    }

    public void setPublish_mode(int publish_mode) {
        this.publish_mode = publish_mode;
    }

    public int getCurrent_progress() {
        return current_progress;
    }

    public void setCurrent_progress(int current_progress) {
        this.current_progress = current_progress;
    }

    public List<Map<String, String>> getMembers() {
        return members;
    }

    public void setMembers(List<Map<String, String>> members) {
        this.members = members;
    }

    public String getRec_id() {
        return rec_id;
    }

    public void setRec_id(String rec_id) {
        this.rec_id = rec_id;
    }
}
