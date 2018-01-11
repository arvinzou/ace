package com.huacainfo.ace.rvc.kedapi.vrs.model.program;

import java.util.List;

/**
 * Created by Arvin on 2018/1/9.
 */
public class ProgramInfo {
    /**
     * 节目创建时间
     */
    private int createtime;
    /**
     * 节目发布时间
     */
    private int issuetime;

    /**
     * 节目显示名称
     */
    private String prgalias;
    /**
     * 节目总时长（秒）
     */
    private int prgallduration;

    /**
     * 节目总大小（byte）
     */
    private int prgallsize;

    /**
     * 节目描述
     */
    private String prgdesc;

    /**
     * 节目详细信息
     */
    private List<ProgramDetail> prgdetail;

    /**
     * 节目gid
     */
    private int prggid;

    /**
     * 	节目状态 0-未发布 1-发布
     */
    private int prgstate;
    /***
     * 	Json文件位置
     */
    private String streamjsonpath;

    public void setCreatetime(int createtime) {
        this.createtime = createtime;
    }

    public int getCreatetime() {
        return this.createtime;
    }

    public void setIssuetime(int issuetime) {
        this.issuetime = issuetime;
    }

    public int getIssuetime() {
        return this.issuetime;
    }

    public void setPrgalias(String prgalias) {
        this.prgalias = prgalias;
    }

    public String getPrgalias() {
        return this.prgalias;
    }

    public void setPrgallduration(int prgallduration) {
        this.prgallduration = prgallduration;
    }

    public int getPrgallduration() {
        return this.prgallduration;
    }

    public void setPrgallsize(int prgallsize) {
        this.prgallsize = prgallsize;
    }

    public int getPrgallsize() {
        return this.prgallsize;
    }

    public void setPrgdesc(String prgdesc) {
        this.prgdesc = prgdesc;
    }

    public String getPrgdesc() {
        return this.prgdesc;
    }

    public void setPrgdetail(List<ProgramDetail> prgdetail) {
        this.prgdetail = prgdetail;
    }

    public List<ProgramDetail> getPrgdetail() {
        return this.prgdetail;
    }

    public void setPrggid(int prggid) {
        this.prggid = prggid;
    }

    public int getPrggid() {
        return this.prggid;
    }

    public void setPrgstate(int prgstate) {
        this.prgstate = prgstate;
    }

    public int getPrgstate() {
        return this.prgstate;
    }

    public void setStreamjsonpath(String streamjsonpath) {
        this.streamjsonpath = streamjsonpath;
    }

    public String getStreamjsonpath() {
        return this.streamjsonpath;
    }
}

