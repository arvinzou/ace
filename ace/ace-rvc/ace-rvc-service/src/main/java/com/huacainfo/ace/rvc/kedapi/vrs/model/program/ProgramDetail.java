package com.huacainfo.ace.rvc.kedapi.vrs.model.program;

/**
 * Created by Arvin on 2018/1/9.
 */
public class ProgramDetail {

    /**
     * ip
     */
    private String ip;

    /**
     * 节目时长(秒)
     */
    private int prgduration;

    /**
     * 节目索引 @@未知作用@@
     */
    private int prggindex;

    /**
     * 节目id
     */
    private int prgid;

    /**
     * 节目名称
     */
    private String prgname;

    /**
     * 节目大小（byte）
     */
    private int prgsize;
    /**
     * 节目文件后缀
     */
    private String prgtype;

    /**
     * 存放相对路径
     */
    private String relativepath;

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return this.ip;
    }

    public void setPrgduration(int prgduration) {
        this.prgduration = prgduration;
    }

    public int getPrgduration() {
        return this.prgduration;
    }

    public void setPrggindex(int prggindex) {
        this.prggindex = prggindex;
    }

    public int getPrggindex() {
        return this.prggindex;
    }

    public void setPrgid(int prgid) {
        this.prgid = prgid;
    }

    public int getPrgid() {
        return this.prgid;
    }

    public void setPrgname(String prgname) {
        this.prgname = prgname;
    }

    public String getPrgname() {
        return this.prgname;
    }

    public void setPrgsize(int prgsize) {
        this.prgsize = prgsize;
    }

    public int getPrgsize() {
        return this.prgsize;
    }

    public void setPrgtype(String prgtype) {
        this.prgtype = prgtype;
    }

    public String getPrgtype() {
        return this.prgtype;
    }

    public void setRelativepath(String relativepath) {
        this.relativepath = relativepath;
    }

    public String getRelativepath() {
        return this.relativepath;
    }
}
