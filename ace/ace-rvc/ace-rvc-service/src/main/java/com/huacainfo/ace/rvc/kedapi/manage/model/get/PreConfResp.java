package com.huacainfo.ace.rvc.kedapi.manage.model.get;

import com.huacainfo.ace.rvc.kedapi.common.base.BaseModel;
import com.huacainfo.ace.rvc.kedapi.manage.model.create.Chairman;
import com.huacainfo.ace.rvc.kedapi.manage.model.create.Speaker;
import com.huacainfo.ace.rvc.kedapi.manage.model.create.VideoFormat;

import java.util.List;

/**
 * Created by Arvin on 2017/12/13.
 */
public class PreConfResp extends BaseModel {


    /**
     * 会议名称 最大字符长度：64个字节
     */
    private String name;

    /**
     * 会议号 最大字符长度：48个字节
     */
    private String conf_id;

    private int conf_type;

    private String start_time;

    private String end_time;

    private int duration;

    private int bitrate;

    private int closed_conf;

    private int safe_conf;

    private String password;

    private int encrypted_type;

    private String encrypted_key;

    private int call_times;

    private int call_interval;

    private int mute;

    private int silence;

    private int video_quality;

    private int dual_mode;

    private int voice_activity_detection;

    private int cascade_mode;

    private int cascade_upload;

    private int cascade_return;

    private int cascade_return_para;

    private int public_conf;

    private int vmp_enable;

    private int mix_enable;

    private int poll_enable;

    private int invited_mt_num;

    private int max_join_mt;

    private Creator creator;

    private Speaker speaker;

    private Chairman chairman;

    private List<VideoFormat> video_formats;

    private int one_reforming;

    private int auto_end;

    private int preoccpuy_resource;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConf_id() {
        return this.conf_id;
    }

    public void setConf_id(String conf_id) {
        this.conf_id = conf_id;
    }

    public int getConf_type() {
        return this.conf_type;
    }

    public void setConf_type(int conf_type) {
        this.conf_type = conf_type;
    }

    public String getStart_time() {
        return this.start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return this.end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getBitrate() {
        return this.bitrate;
    }

    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }

    public int getClosed_conf() {
        return this.closed_conf;
    }

    public void setClosed_conf(int closed_conf) {
        this.closed_conf = closed_conf;
    }

    public int getSafe_conf() {
        return this.safe_conf;
    }

    public void setSafe_conf(int safe_conf) {
        this.safe_conf = safe_conf;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEncrypted_type() {
        return this.encrypted_type;
    }

    public void setEncrypted_type(int encrypted_type) {
        this.encrypted_type = encrypted_type;
    }

    public String getEncrypted_key() {
        return this.encrypted_key;
    }

    public void setEncrypted_key(String encrypted_key) {
        this.encrypted_key = encrypted_key;
    }

    public int getCall_times() {
        return this.call_times;
    }

    public void setCall_times(int call_times) {
        this.call_times = call_times;
    }

    public int getCall_interval() {
        return this.call_interval;
    }

    public void setCall_interval(int call_interval) {
        this.call_interval = call_interval;
    }

    public int getMute() {
        return this.mute;
    }

    public void setMute(int mute) {
        this.mute = mute;
    }

    public int getSilence() {
        return this.silence;
    }

    public void setSilence(int silence) {
        this.silence = silence;
    }

    public int getVideo_quality() {
        return this.video_quality;
    }

    public void setVideo_quality(int video_quality) {
        this.video_quality = video_quality;
    }

    public int getDual_mode() {
        return this.dual_mode;
    }

    public void setDual_mode(int dual_mode) {
        this.dual_mode = dual_mode;
    }

    public int getVoice_activity_detection() {
        return this.voice_activity_detection;
    }

    public void setVoice_activity_detection(int voice_activity_detection) {
        this.voice_activity_detection = voice_activity_detection;
    }

    public int getCascade_mode() {
        return this.cascade_mode;
    }

    public void setCascade_mode(int cascade_mode) {
        this.cascade_mode = cascade_mode;
    }

    public int getCascade_upload() {
        return this.cascade_upload;
    }

    public void setCascade_upload(int cascade_upload) {
        this.cascade_upload = cascade_upload;
    }

    public int getCascade_return() {
        return this.cascade_return;
    }

    public void setCascade_return(int cascade_return) {
        this.cascade_return = cascade_return;
    }

    public int getCascade_return_para() {
        return this.cascade_return_para;
    }

    public void setCascade_return_para(int cascade_return_para) {
        this.cascade_return_para = cascade_return_para;
    }

    public int getPublic_conf() {
        return this.public_conf;
    }

    public void setPublic_conf(int public_conf) {
        this.public_conf = public_conf;
    }

    public int getVmp_enable() {
        return this.vmp_enable;
    }

    public void setVmp_enable(int vmp_enable) {
        this.vmp_enable = vmp_enable;
    }

    public int getMix_enable() {
        return this.mix_enable;
    }

    public void setMix_enable(int mix_enable) {
        this.mix_enable = mix_enable;
    }

    public int getPoll_enable() {
        return this.poll_enable;
    }

    public void setPoll_enable(int poll_enable) {
        this.poll_enable = poll_enable;
    }

    public int getInvited_mt_num() {
        return this.invited_mt_num;
    }

    public void setInvited_mt_num(int invited_mt_num) {
        this.invited_mt_num = invited_mt_num;
    }

    public int getMax_join_mt() {
        return this.max_join_mt;
    }

    public void setMax_join_mt(int max_join_mt) {
        this.max_join_mt = max_join_mt;
    }

    public Creator getCreator() {
        return this.creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public Speaker getSpeaker() {
        return this.speaker;
    }

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    public Chairman getChairman() {
        return this.chairman;
    }

    public void setChairman(Chairman chairman) {
        this.chairman = chairman;
    }

    public List<VideoFormat> getVideo_formats() {
        return this.video_formats;
    }

    public void setVideo_formats(List<VideoFormat> video_formats) {
        this.video_formats = video_formats;
    }

    public int getOne_reforming() {
        return this.one_reforming;
    }

    public void setOne_reforming(int one_reforming) {
        this.one_reforming = one_reforming;
    }

    public int getAuto_end() {
        return this.auto_end;
    }

    public void setAuto_end(int auto_end) {
        this.auto_end = auto_end;
    }

    public int getPreoccpuy_resource() {
        return this.preoccpuy_resource;
    }

    public void setPreoccpuy_resource(int preoccpuy_resource) {
        this.preoccpuy_resource = preoccpuy_resource;
    }
}
