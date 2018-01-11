package com.huacainfo.ace.rvc.kedapi.vrs.model.program;

/**
 * Created by Arvin on 2018/1/9.
 */
public class FolderInfo {

    /**
     * 文件夹名字
     */
    private String foldername;
    /**
     * 文件夹id
     */
    private String folderid;
    /**
     * 文件夹中的文件数
     */
    private String prgcount;

    public String getFoldername() {
        return foldername;
    }

    public void setFoldername(String foldername) {
        this.foldername = foldername;
    }

    public String getFolderid() {
        return folderid;
    }

    public void setFolderid(String folderid) {
        this.folderid = folderid;
    }

    public String getPrgcount() {
        return prgcount;
    }

    public void setPrgcount(String prgcount) {
        this.prgcount = prgcount;
    }
}
