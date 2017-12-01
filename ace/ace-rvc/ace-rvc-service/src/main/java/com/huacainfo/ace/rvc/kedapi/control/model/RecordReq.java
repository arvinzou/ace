package com.huacainfo.ace.rvc.kedapi.control.model;

import com.huacainfo.ace.rvc.kedapi.common.base.BaseModel;

import java.util.List;

/**
 * @description: 开启录像参数
 * @author: ArvinZou
 * @create: 2017-11-16 15:59
 */
public class RecordReq extends BaseModel {

//    {
//        "video_name": "filename",
//            "recorder_type": 2,
//            "publish_mode": 0,
//            "anonymous": 0,
//            "recorder_mode": 1,
//            "main_stream": 1,
//            "dual_stream": 1,
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
     * 录像保存的文件名称
     * 1.字符限制：
     * a.不支持输入特殊字符 % : & * ^ ~ ' "" ? / \ < > | ` " $
     * b.首字符和尾字符，不允许输入下划线（_） 减号（-） @ 小数点（.）
     * （可输入在非首尾字符）
     * 2.最大字符长度：64个字节
     */
    private String video_name;

    /**
     * 录像类型 1-会议录像；2-终端录像；
     */
    private int recorder_type;

    /**
     * 发布模式 0-不发布；1-发布；
     */
    private int publish_mode;

    /**
     * 是否支持免登陆观看直播 0-不支持；1-支持；
     */
    private int anonymous;

    /**
     * 录像模式 1-录像；2-直播；3-录像+直播；
     */
    private int recorder_mode;

    /**
     * 是否录主格式码流（视频+音频） 0-否；1-是；
     */
    private int main_stream;

    /**
     * 是否录双流（仅双流） 0-否；1-是；
     */
    private int dual_stream;

    /**
     * 开启录像终端数组
     */
    private List<TerminalUnit> members;

    public String getVideo_name() {
        return video_name;
    }

    public void setVideo_name(String video_name) {
        this.video_name = video_name;
    }

    public int getRecorder_type() {
        return recorder_type;
    }

    public void setRecorder_type(int recorder_type) {
        this.recorder_type = recorder_type;
    }

    public int getPublish_mode() {
        return publish_mode;
    }

    public void setPublish_mode(int publish_mode) {
        this.publish_mode = publish_mode;
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

    public List<TerminalUnit> getMembers() {
        return members;
    }

    public void setMembers(List<TerminalUnit> members) {
        this.members = members;
    }

    public static class TerminalUnit {
        /**
         * 终端id, 仅当开启终端录像需要 最大字符长度：48个字节
         */
        private String mt_id;

        public String getMt_id() {
            return mt_id;
        }

        public void setMt_id(String mt_id) {
            this.mt_id = mt_id;
        }
    }
}
