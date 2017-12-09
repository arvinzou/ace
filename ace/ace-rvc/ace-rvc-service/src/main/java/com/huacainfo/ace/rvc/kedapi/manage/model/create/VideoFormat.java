package com.huacainfo.ace.rvc.kedapi.manage.model.create;

/**
 * 主视频格式列表 -- 必传
 * Created by Arvin on 2017/12/4.
 */
public class VideoFormat {
    public VideoFormat() {
    }

    public VideoFormat(int format, int resolution, int frame, int bitrate) {
        this.format = format;
        this.resolution = resolution;
        this.frame = frame;
        this.bitrate = bitrate;
    }

    /**
     * 主视频格式
     * 1-MPEG;
     * 2-H.261;
     * 3-H.263;
     * 4-H.264_HP;
     * 5-H.264_BP;
     * 6-H.265;
     */
    private int format;

    /**
     * 主视频分辨率
     * 1-QCIF;
     * 2-CIF;
     * 3-4CIF;
     * 12-720P;
     * 13-1080P;
     * 14-WCIF;
     * 15-W4CIF;
     * 16-4k;
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

    public void setFormat(int format) {
        this.format = format;
    }

    public int getFormat() {
        return this.format;
    }

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

    public int getResolution() {
        return this.resolution;
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    public int getFrame() {
        return this.frame;
    }

    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }

    public int getBitrate() {
        return this.bitrate;
    }
}
