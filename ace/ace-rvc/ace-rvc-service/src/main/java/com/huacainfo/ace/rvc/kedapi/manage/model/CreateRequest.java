package com.huacainfo.ace.rvc.kedapi.manage.model;

import com.huacainfo.ace.rvc.kedapi.common.base.BaseModel;

import java.util.List;

/**
 * @description: 创建会议 post 参数
 * @author: ArvinZou
 * @create: 2017-11-16 11:28
 */
public class CreateRequest extends BaseModel {

    public static final String DEFAULT_PARAMS =
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
                    "    \"auto_end\": 1,\n" +
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
     * 会议免打扰 0-关闭；1-开启；
     */
    private int closed_conf;

    /**
     * 会议安全 0-公开会议；1-隐藏会议；
     */
    private int safe_conf;

    /**
     * 会议密码
     * 1.字符限制：仅支持 英文字母(大小写) 数字 下划线（_） 小数点（.）
     * 2.最大字符长度：16个字节
     */
    private String password;

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
     * 发言人
     */
    private MemberInfo speaker;
    /**
     * 主席
     */
    private MemberInfo chairman;
    /**
     * 混音信息
     */
    private Mix mix;
    /**
     * 主视频格式列表
     */

    private int[] video_formats;
    /**
     * 主视频格式
     * 见 com.huacainfo.ace.rvc.kdapi.constant.VideoFormat.Resolution
     */
    private int format;
    /**
     * 主视频分辨率
     * 见 com.huacainfo.ace.rvc.kdapi.constant.VideoFormat.Resolution
     */
    private int resolution;
    /**
     * 帧率
     */
    private int frame;
    /**
     * 码率
     */
    private int bitrate;
    /**
     * 参会成员
     */
    private List<MemberInfo> invite_members;
    /**
     * 画面合成设置
     */
    private Vmp vmp;
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
     * name*	str	名称 最大字符长度：128个字节 仅当跟随类型为会控指定时才需要输入
     * account*	str	帐号 最大字符长度：128个字节 仅当跟随类型为会控指定时才需要输入
     * account_type*	int	帐号类型 1-moid；4-非系统邮箱；5-e164号；6-电话；7-ip地址；
     * member_type*	int	跟随类型 1-会控指定；2-发言人跟随；3-管理方跟随；4-会议轮询跟随；7-内容共享跟随；
     * chn_idx*	int	在画画合成中的位置
     */
    private List<MemberInfo> members;
    /**
     * vip成员列表
     * name*	str	名称 最大字符长度：128个字节
     * account*	str	帐号 最大字符长度：128个字节
     * account_type*	int	帐号类型 1-moid；4-非系统邮箱；5-e164号；6-电话；7-ip地址；
     */
    private List<MemberInfo> vips;
    /**
     * 轮询设置
     */
    private Poll poll;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getConf_level() {
        return conf_level;
    }

    public void setConf_level(int conf_level) {
        this.conf_level = conf_level;
    }

    public int getClosed_conf() {
        return closed_conf;
    }

    public void setClosed_conf(int closed_conf) {
        this.closed_conf = closed_conf;
    }

    public int getSafe_conf() {
        return safe_conf;
    }

    public void setSafe_conf(int safe_conf) {
        this.safe_conf = safe_conf;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEncrypted_type() {
        return encrypted_type;
    }

    public void setEncrypted_type(int encrypted_type) {
        this.encrypted_type = encrypted_type;
    }

    public int getConf_type() {
        return conf_type;
    }

    public void setConf_type(int conf_type) {
        this.conf_type = conf_type;
    }

    public int getCall_mode() {
        return call_mode;
    }

    public void setCall_mode(int call_mode) {
        this.call_mode = call_mode;
    }

    public int getCall_times() {
        return call_times;
    }

    public void setCall_times(int call_times) {
        this.call_times = call_times;
    }

    public int getCall_interval() {
        return call_interval;
    }

    public void setCall_interval(int call_interval) {
        this.call_interval = call_interval;
    }

    public int getMute() {
        return mute;
    }

    public void setMute(int mute) {
        this.mute = mute;
    }

    public int getSilence() {
        return silence;
    }

    public void setSilence(int silence) {
        this.silence = silence;
    }

    public int getVideo_quality() {
        return video_quality;
    }

    public void setVideo_quality(int video_quality) {
        this.video_quality = video_quality;
    }

    public String getEncrypted_key() {
        return encrypted_key;
    }

    public void setEncrypted_key(String encrypted_key) {
        this.encrypted_key = encrypted_key;
    }

    public int getDual_mode() {
        return dual_mode;
    }

    public void setDual_mode(int dual_mode) {
        this.dual_mode = dual_mode;
    }

    public int getVoice_activity_detection() {
        return voice_activity_detection;
    }

    public void setVoice_activity_detection(int voice_activity_detection) {
        this.voice_activity_detection = voice_activity_detection;
    }

    public int getVacinterval() {
        return vacinterval;
    }

    public void setVacinterval(int vacinterval) {
        this.vacinterval = vacinterval;
    }

    public int getCascade_mode() {
        return cascade_mode;
    }

    public void setCascade_mode(int cascade_mode) {
        this.cascade_mode = cascade_mode;
    }

    public int getCascade_upload() {
        return cascade_upload;
    }

    public void setCascade_upload(int cascade_upload) {
        this.cascade_upload = cascade_upload;
    }

    public int getCascade_return() {
        return cascade_return;
    }

    public void setCascade_return(int cascade_return) {
        this.cascade_return = cascade_return;
    }

    public int getCascade_return_para() {
        return cascade_return_para;
    }

    public void setCascade_return_para(int cascade_return_para) {
        this.cascade_return_para = cascade_return_para;
    }

    public int getPublic_conf() {
        return public_conf;
    }

    public void setPublic_conf(int public_conf) {
        this.public_conf = public_conf;
    }

    public int getMax_join_mt() {
        return max_join_mt;
    }

    public void setMax_join_mt(int max_join_mt) {
        this.max_join_mt = max_join_mt;
    }

    public int getAuto_end() {
        return auto_end;
    }

    public void setAuto_end(int auto_end) {
        this.auto_end = auto_end;
    }

    public int getPreoccpuy_resource() {
        return preoccpuy_resource;
    }

    public void setPreoccpuy_resource(int preoccpuy_resource) {
        this.preoccpuy_resource = preoccpuy_resource;
    }

    public MemberInfo getSpeaker() {
        return speaker;
    }

    public void setSpeaker(MemberInfo speaker) {
        this.speaker = speaker;
    }

    public MemberInfo getChairman() {
        return chairman;
    }

    public void setChairman(MemberInfo chairman) {
        this.chairman = chairman;
    }

    public Mix getMix() {
        return mix;
    }

    public void setMix(Mix mix) {
        this.mix = mix;
    }

    public int[] getVideo_formats() {
        return video_formats;
    }

    public void setVideo_formats(int[] video_formats) {
        this.video_formats = video_formats;
    }

    public int getFormat() {
        return format;
    }

    public void setFormat(int format) {
        this.format = format;
    }

    public int getResolution() {
        return resolution;
    }

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

    public int getFrame() {
        return frame;
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    public int getBitrate() {
        return bitrate;
    }

    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }

    public List<MemberInfo> getInvite_members() {
        return invite_members;
    }

    public void setInvite_members(List<MemberInfo> invite_members) {
        this.invite_members = invite_members;
    }

    public Vmp getVmp() {
        return vmp;
    }

    public void setVmp(Vmp vmp) {
        this.vmp = vmp;
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

    public List<MemberInfo> getMembers() {
        return members;
    }

    public void setMembers(List<MemberInfo> members) {
        this.members = members;
    }

    public List<MemberInfo> getVips() {
        return vips;
    }

    public void setVips(List<MemberInfo> vips) {
        this.vips = vips;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    /**
     * 成员信息
     */
    public static class MemberInfo {
        /**
         * 名称 最大字符长度：128个字节
         */
        private String name;

        /**
         * 帐号 最大字符长度：128个字节
         */
        private String account;

        /**
         * 帐号类型 1-moid；4-非系统邮箱；5-e164号；6-电话；7-ip地址；
         */
        private int account_type;

        /**
         * 跟随类型 1-会控指定；2-发言人跟随；3-管理方跟随；4-会议轮询跟随；7-内容共享跟随；
         */
        private int member_type;

        /**
         * 在画画合成中的位置
         */
        private int chn_idx;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public int getAccount_type() {
            return account_type;
        }

        public void setAccount_type(int account_type) {
            this.account_type = account_type;
        }

        public int getMember_type() {
            return member_type;
        }

        public void setMember_type(int member_type) {
            this.member_type = member_type;
        }

        public int getChn_idx() {
            return chn_idx;
        }

        public void setChn_idx(int chn_idx) {
            this.chn_idx = chn_idx;
        }
    }

    public static class Mix {
        /**
         * 混音模式 1-智能混音；2-定制混音；
         */
        private int mode;

        /**
         * 制定混音时的混音成员列表
         */
        private List<MemberInfo> members;

        public int getMode() {
            return mode;
        }

        public void setMode(int mode) {
            this.mode = mode;
        }

        public List<MemberInfo> getMembers() {
            return members;
        }

        public void setMembers(List<MemberInfo> members) {
            this.members = members;
        }
    }

    public static class Vmp {
        /**
         * 画面合成模式 1-定制画面合成；2-自动画面合成；
         */
        private int mode;

        /**
         * 画面合成风格:
         * 见 com.huacainfo.ace.rvc.kdapi.constant.VideoFormat.Layout
         */
        private int layout;

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
    }

    public static class Poll {
        /**
         * 轮询模式 1-视频轮询；3-音视频轮询；
         */
        private int mode;
        /**
         * 轮询次数，0无限次轮询
         */
        private int num;
        /**
         * 轮询间隔时间(秒)
         */
        private int keep_time;

        /**
         * 轮询成员列表
         * name*	str	名称 最大字符长度：128个字节
         * account*	str	帐号 最大字符长度：128个字节
         * account_type*	int	帐号类型 1-moid；4-非系统邮箱；5-e164号；6-电话；7-ip地址；
         */
        private List<MemberInfo> members;

        public int getMode() {
            return mode;
        }

        public void setMode(int mode) {
            this.mode = mode;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getKeep_time() {
            return keep_time;
        }

        public void setKeep_time(int keep_time) {
            this.keep_time = keep_time;
        }

        public List<MemberInfo> getMembers() {
            return members;
        }

        public void setMembers(List<MemberInfo> members) {
            this.members = members;
        }
    }
}
