package com.huacainfo.ace.rvc.kedapi.control.model;

import com.huacainfo.ace.rvc.kedapi.common.base.BaseModel;

import java.util.List;

/**
 * Created by Arvin on 2017/11/29.
 */
public class MeetingViewMixReq extends BaseModel {
    /**
     * 画面合成模式 1-定制画面合成；2-自动画面合成；
     */
    private int mode;
    /**
     * 画面合成风格:
     */
    private int layout;
    /**
     * 是否广播 0-否；1-是；
     */
    private int broadcast;
    /**
     * 是否识别声音来源 0-否；1-是；
     */
    private int voice_hint;
    /**
     * 是否显示别名 0-否；1-是；
     */
    private int show_mt_name;

    /***
     * 画面合成成员数组
     */
    private List<ViewUnit> members;

    public class ViewUnit {
        /**
         * 画面合成通道索引，从0开始
         */
        private int chn_idx;
        /**
         * 成员类型 1-会控指定；2-发言人跟随；3-主席跟随；4-轮询跟随；5-自动语音激励；7-双流跟随；
         */
        private int member_type;
        /**
         * 通道终端号，仅当通道中为会控指定时需要 最大字符长度：48个字节
         */
        private String mt_id;

        public int getChn_idx() {
            return chn_idx;
        }

        public void setChn_idx(int chn_idx) {
            this.chn_idx = chn_idx;
        }

        public int getMember_type() {
            return member_type;
        }

        public void setMember_type(int member_type) {
            this.member_type = member_type;
        }

        public String getMt_id() {
            return mt_id;
        }

        public void setMt_id(String mt_id) {
            this.mt_id = mt_id;
        }
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public int getBroadcast() {
        return broadcast;
    }

    public void setBroadcast(int broadcast) {
        this.broadcast = broadcast;
    }

    public int getVoice_hint() {
        return voice_hint;
    }

    public void setVoice_hint(int voice_hint) {
        this.voice_hint = voice_hint;
    }

    public int getShow_mt_name() {
        return show_mt_name;
    }

    public void setShow_mt_name(int show_mt_name) {
        this.show_mt_name = show_mt_name;
    }

    public List<ViewUnit> getMembers() {
        return members;
    }

    public void setMembers(List<ViewUnit> members) {
        this.members = members;
    }
}


