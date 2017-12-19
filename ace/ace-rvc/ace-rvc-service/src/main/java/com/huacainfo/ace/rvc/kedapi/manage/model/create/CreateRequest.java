package com.huacainfo.ace.rvc.kedapi.manage.model.create;

import com.huacainfo.ace.rvc.kedapi.common.base.BaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 创建会议 post 参数
 * @author: ArvinZou
 * @create: 2017-11-16 11:28
 */
public class CreateRequest extends BaseModel {

    public static final String DEFAULT =
            "{\n" +
                    "    \"name\": \"text\",\n" +
                    "    \"call_mode\": 0,\n" +
                    "    \"conf_level\": 10,\n" +
                    "    \"duration\": 240,\n" +
                    "    \"bitrate\": 1024,\n" +
                    "    \"closed_conf\": 0,\n" +
                    "    \"safe_conf\": 0,\n" +
                    "    \"encrypted_type\": 0,\n" +
                    "    \"conf_type\": 1,\n" +
                    "    \"call_times\": 0,\n" +
                    "    \"call_interval\": 20,\n" +
                    "    \"mute\": 0,\n" +
                    "    \"silence\": 0,\n" +
                    "    \"video_quality\": 0,\n" +
                    "    \"encrypted_key\": \"\",\n" +
                    "    \"dual_mode\": 1,\n" +
                    "    \"voice_activity_detection\": 0,\n" +
                    "    \"cascade_mode\": 1,\n" +
                    "    \"cascade_upload\": 1,\n" +
                    "    \"cascade_return\": 0,\n" +
                    "    \"cascade_return_para\": 0,\n" +
                    "    \"max_join_mt\": 192,\n" +
                    "    \"auto_end\": 0,\n" +
                    "    \"preoccpuy_resource\": 1,\n" +
                    "    \"public_conf\": 0,\n" +
                    "    \"video_formats\": [\n" +
                    "        {\n" +
                    "            \"format\": 5,\n" +
                    "            \"resolution\": 12,\n" +
                    "            \"frame\": 30,\n" +
                    "            \"bitrate\": 1024\n" +
                    "        }\n" +
                    "    ]\n" +
                    "}";

    public static final String DEFAULT_2 = "{\n" +
            "  \"name\": \"name\",\n" +
            "  \"duration\": 240,\n" +
            "  \"conf_level\": 10,\n" +
            "  \"bitrate\": 8128,\n" +
            "  \"closed_conf\": 1,\n" +
            "  \"safe_conf\": 0,\n" +
            "  \"encrypted_type\": 0,\n" +
            "  \"conf_type\": 0,\n" +
            "  \"call_mode\": 0,\n" +
            "  \"call_times\": 0,\n" +
            "  \"call_interval\": 20,\n" +
            "  \"mute\": 1,\n" +
            "  \"silence\": 1,\n" +
            "  \"video_quality\": 1,\n" +
            "  \"encrypted_key\": \"\",\n" +
            "  \"dual_mode\": 0,\n" +
            "  \"voice_activity_detection\": 1,\n" +
            "  \"vacinterval\": 5,\n" +
            "  \"cascade_mode\": 1,\n" +
            "  \"cascade_upload\": 1,\n" +
            "  \"cascade_return\": 0,\n" +
            "  \"cascade_return_para\": 0,\n" +
            "  \"public_conf\": 1,\n" +
            "  \"max_join_mt\": 192,\n" +
            "  \"auto_end\": 0,\n" +
            "  \"preoccpuy_resource\": 1,\n" +
            "  \"speaker\": {\n" +
            "    \"name\": \"mxm\",\n" +
            "    \"account\": \"c69da1ef-5930-47e0-9a79-dfa083de0208\",\n" +
            "    \"account_type\": 1\n" +
            "  },\n" +
            "  \"chairman\": {\n" +
            "    \"name\": \"mxm\",\n" +
            "    \"account\": \"c69da1ef-5930-47e0-9a79-dfa083de0208\",\n" +
            "    \"account_type\": 1\n" +
            "  },\n" +
            "  \"video_formats\": [\n" +
            "    {\n" +
            "      \"format\": 4,\n" +
            "      \"resolution\": 3,\n" +
            "      \"frame\": 30,\n" +
            "      \"bitrate\": 8128\n" +
            "    },\n" +
            "    {\n" +
            "      \"format\": 4,\n" +
            "      \"resolution\": 2,\n" +
            "      \"frame\": 30,\n" +
            "      \"bitrate\": 4096\n" +
            "    },\n" +
            "    {\n" +
            "      \"format\": 4,\n" +
            "      \"resolution\": 12,\n" +
            "      \"frame\": 30,\n" +
            "      \"bitrate\": 2048\n" +
            "    },\n" +
            "    {\n" +
            "      \"format\": 5,\n" +
            "      \"resolution\": 3,\n" +
            "      \"frame\": 25,\n" +
            "      \"bitrate\": 512\n" +
            "    }\n" +
            "  ]\n" +
            "}";
    /**
     * 会议名称
     * 1.字符限制：
     * a.不支持输入特殊字符：% & * ^ ~ ' " " ? / \ < > | ` " $
     * b.且首字符和尾字符不支持输入，下划线（_） 减号（-） 小数点（.） @
     * （除首尾字符可以输入）
     * 2.最大字符长度：64个字节
     */
    private String name;
    /**
     * 会议时长 0为永久会议
     */
    private int duration;
    /**
     * 会议等级 0—16 数字越大会议等级越高，默认为0
     */
    private int conf_level;
    /**
     * 会议码率，目前以主视频格式中的码率为准
     */
    private int bitrate;
    /**
     * 会议免打扰 0-关闭；1-开启；
     */
    private int closed_conf;
    /**
     * 会议安全 0-公开会议；1-隐藏会议；
     */
    private int safe_conf;
    /**
     * 传输加密类型 0-不加密；2-AES加密；
     */
    private int encrypted_type;
    /**
     * 会议类型 0-传统会议；1-端口会议；
     */
    private int conf_type;
    /**
     * 呼叫模式，不填则默认为2 0-手动；2-自动；
     */
    private int call_mode;
    /**
     * 呼叫次数
     */
    private int call_times;
    /**
     * 呼叫间隔(秒)
     */
    private int call_interval;
    /**
     * 是否开启初始化哑音 0-否；1-是；
     */
    private int mute;
    /**
     * 是否开启初始化静音 0-否；1-是；
     */
    private int silence;
    /**
     * 视频质量,其中租赁环境默认设为质量优先，自建环境以api下参为准 0-质量优先；1-速度优先；
     */
    private int video_quality;
    /**
     * 传输加密AES加密密钥
     * 1.字符限制：仅支持 英文字母(大小写) 数字 下划线（_） 小数点（.）
     * 2.最大字符长度：16个字节
     */
    private String encrypted_key;
    /**
     * 双流权限 0-发言会场；1-任意会场；2-指定会场；
     */
    private int dual_mode;
    /**
     * 是否开启语音激励 0-否；1-是；
     */
    private int voice_activity_detection;
    /**
     * 语音激励敏感度(s),支持5、15、30、60
     */
    private int vacinterval;
    /**
     * 级联模式 0-简单级联；1-合并级联；
     */
    private int cascade_mode;
    /**
     * 是否级联上传 0-否；1-是；
     */
    private int cascade_upload;
    /**
     * 是否级联回传 0-否；1-是；
     */
    private int cascade_return;
    /**
     * 级联回传带宽参数
     */
    private int cascade_return_para;
    /**
     * 是否公共会议室 0-否；1-是；
     */
    private int public_conf;
    /**
     * 最大与会终端数 8-8方；192-192方；
     */
    private int max_join_mt;
    /**
     * 会议中无终端时，是否自动结会 0-否；1-是；
     */
    private int auto_end;
    /**
     * 预占资源 0-否；1-是；
     */
    private int preoccpuy_resource;
    /**
     * 发言人 -- 可选
     */
    private Speaker speaker;
    /**
     * 主席 -- 可選
     */
    private Chairman chairman;
    /**
     * 主视频格式列表
     */
    private List<VideoFormat> video_formats;
    /**
     * 参会成员
     */
    private List<InviteMember> invite_members;

    public CreateRequest() {

    }

    public CreateRequest(String name) {
        this.name = name;
        this.duration = 0;
        this.conf_level = 10;
        this.bitrate = 8128;
        this.closed_conf = 1;
        this.safe_conf = 0;
        this.encrypted_type = 0;
        this.conf_type = 0;
        this.call_mode = 0;
        this.call_times = 0;
        this.call_interval = 20;
        this.mute = 1;
        this.silence = 1;
        this.video_quality = 1;
        this.encrypted_key = "";
        this.dual_mode = 0;
        this.voice_activity_detection = 0;
        this.cascade_mode = 1;
        this.cascade_upload = 1;
        this.cascade_return_para = 0;
        this.public_conf = 0;
        this.max_join_mt = 192;
        this.auto_end = 0;//	会议中无终端时，是否自动结会 0-否；1-是；
        this.preoccpuy_resource = 1;
        List<VideoFormat> vfList = new ArrayList<>();
        VideoFormat vf1 = new VideoFormat(5, 12, 30, 1024);
        VideoFormat vf2 = new VideoFormat(4, 3, 30, 8128);

        vfList.add(vf1);
        vfList.add(vf2);
        this.video_formats = vfList;
    }

    public CreateRequest(String name, Speaker speaker, Chairman chairman) {
        this.name = name;
        this.duration = 0;
        this.conf_level = 10;
        this.bitrate = 8128;
        this.closed_conf = 1;
        this.safe_conf = 0;
        this.encrypted_type = 0;
        this.conf_type = 0;
        this.call_mode = 0;
        this.call_times = 0;
        this.call_interval = 20;
        this.mute = 1;
        this.silence = 1;
        this.video_quality = 1;
        this.encrypted_key = "";
        this.dual_mode = 0;
        this.voice_activity_detection = 0;
        this.cascade_mode = 1;
        this.cascade_upload = 1;
        this.cascade_return_para = 0;
        this.public_conf = 0;
        this.max_join_mt = 192;
        this.auto_end = 0;//	会议中无终端时，是否自动结会 0-否；1-是；
        this.preoccpuy_resource = 1;
        List<VideoFormat> vfList = new ArrayList<>();
        VideoFormat vf1 = new VideoFormat(5, 12, 30, 1024);
        VideoFormat vf2 = new VideoFormat(4, 3, 30, 8128);

        vfList.add(vf1);
        vfList.add(vf2);
        this.video_formats = vfList;

        this.speaker = speaker;
        this.chairman = chairman;
    }

    public CreateRequest(String name, Speaker speaker, Chairman chairman, List<InviteMember> invite_members) {
        this.name = name;
        this.duration = 0;
        this.conf_level = 10;
        this.bitrate = 8128;
        this.closed_conf = 1;
        this.safe_conf = 0;
        this.encrypted_type = 0;
        this.conf_type = 0;
        this.call_mode = 0;
        this.call_times = 0;
        this.call_interval = 20;
        this.mute = 1;
        this.silence = 1;
        this.video_quality = 1;
        this.encrypted_key = "";
        this.dual_mode = 0;
        this.voice_activity_detection = 0;
        this.cascade_mode = 1;
        this.cascade_upload = 1;
        this.cascade_return_para = 0;
        this.public_conf = 0;
        this.max_join_mt = 192;
        this.auto_end = 0;//	会议中无终端时，是否自动结会 0-否；1-是；
        this.preoccpuy_resource = 1;
        List<VideoFormat> vfList = new ArrayList<>();
        VideoFormat vf1 = new VideoFormat(5, 12, 30, 1024);
        VideoFormat vf2 = new VideoFormat(4, 3, 30, 8128);

        vfList.add(vf1);
        vfList.add(vf2);
        this.video_formats = vfList;

        this.speaker = speaker;
        this.chairman = chairman;
        this.invite_members = invite_members;
    }

    public List<InviteMember> getInvite_members() {
        return invite_members;
    }

    public void setInvite_members(List<InviteMember> invite_members) {
        this.invite_members = invite_members;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getConf_level() {
        return this.conf_level;
    }

    public void setConf_level(int conf_level) {
        this.conf_level = conf_level;
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

    public int getEncrypted_type() {
        return this.encrypted_type;
    }

    public void setEncrypted_type(int encrypted_type) {
        this.encrypted_type = encrypted_type;
    }

    public int getConf_type() {
        return this.conf_type;
    }

    public void setConf_type(int conf_type) {
        this.conf_type = conf_type;
    }

    public int getCall_mode() {
        return this.call_mode;
    }

    public void setCall_mode(int call_mode) {
        this.call_mode = call_mode;
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

    public String getEncrypted_key() {
        return this.encrypted_key;
    }

    public void setEncrypted_key(String encrypted_key) {
        this.encrypted_key = encrypted_key;
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

    public int getVacinterval() {
        return this.vacinterval;
    }

    public void setVacinterval(int vacinterval) {
        this.vacinterval = vacinterval;
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

    public int getMax_join_mt() {
        return this.max_join_mt;
    }

    public void setMax_join_mt(int max_join_mt) {
        this.max_join_mt = max_join_mt;
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

}
