package com.huacainfo.ace.rvc.kedapi.manage.model.create;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arvin on 2017/12/19.
 */
public class Vmp {

    public Vmp() {
        this.mode = 2;//2-自动画面合成；
        this.voice_hint = 1;//识别声音来源
        this.broadcast = 1;//向终端广播
        this.show_mt_name = 1;//显示别名
        this.members = new ArrayList<>();//空列表
    }

    public Vmp(int layout, List<VmpMember> members) {
        this.mode = 1;//1-定制画面合成
        this.voice_hint = 1;//识别声音来源
        this.broadcast = 1;//向终端广播
        this.show_mt_name = 1;//显示别名
        this.members = members;//
    }

    public Vmp(int mode, int layout, int voice_hint, int broadcast, int show_mt_name, List<VmpMember> members) {
        this.mode = mode;
        this.layout = layout;
        this.voice_hint = voice_hint;
        this.broadcast = broadcast;
        this.show_mt_name = show_mt_name;
        this.members = members;
    }

    /***
     * 	画面合成模式 1-定制画面合成；2-自动画面合成；
     */
    private int mode;

    /**
     * 画面合成风格
     */
    private int layout;

    /**
     * 是否识别声音来源 0-否；1-是；
     */
    private int voice_hint;

    /**
     * 是否向终端广播 0-否；1-是；
     */
    private int broadcast;

    /**
     * 是否显示别名 0-否；1-是；
     */
    private int show_mt_name;

    /**
     * 画面合成成员列表
     */
    private List<VmpMember> members;

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

    public int getVoice_hint() {
        return voice_hint;
    }

    public void setVoice_hint(int voice_hint) {
        this.voice_hint = voice_hint;
    }

    public int getBroadcast() {
        return broadcast;
    }

    public void setBroadcast(int broadcast) {
        this.broadcast = broadcast;
    }

    public int getShow_mt_name() {
        return show_mt_name;
    }

    public void setShow_mt_name(int show_mt_name) {
        this.show_mt_name = show_mt_name;
    }

    public List<VmpMember> getMembers() {
        return members;
    }

    public void setMembers(List<VmpMember> members) {
        this.members = members;
    }
}