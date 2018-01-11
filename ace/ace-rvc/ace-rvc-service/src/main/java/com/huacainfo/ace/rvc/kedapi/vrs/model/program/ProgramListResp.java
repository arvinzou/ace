package com.huacainfo.ace.rvc.kedapi.vrs.model.program;

import com.huacainfo.ace.rvc.kedapi.common.base.BaseModel;

import java.util.List;

/**
 * Created by Arvin on 2018/1/9.
 */
public class ProgramListResp extends BaseModel {

    /**
     * 节目总个数
     */
    private int prgcount;
    /**
     * 节目信息
     */
    private List<ProgramInfo> prginfo;

    private String fileUrl;

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public int getPrgcount() {
        return prgcount;
    }

    public void setPrgcount(int prgcount) {
        this.prgcount = prgcount;
    }

    public List<ProgramInfo> getPrginfo() {
        return prginfo;
    }

    public void setPrginfo(List<ProgramInfo> prginfo) {
        this.prginfo = prginfo;
    }
}
