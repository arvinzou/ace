package com.huacainfo.ace.rvc.kedapi.vrs.model.program;

import com.huacainfo.ace.rvc.kedapi.common.base.BaseModel;

import java.util.List;

/**
 * Created by Arvin on 2018/1/9.
 */
public class FolderResp extends BaseModel {

//    {
//        "folderinfo": [
//        {
//            "folderid": 1,
//                "foldername": "ALL",
//                "prgcount": 219
//        },
//        {
//            "folderid": 2,
//                "foldername": "UnGroup",
//                "prgcount": 209
//        }
//  ],
//        "success": 1
//    }

    /**
     * 文件夹信息
     */
    private List<FolderInfo> folderinfo;

    public List<FolderInfo> getFolderinfo() {
        return folderinfo;
    }

    public void setFolderinfo(List<FolderInfo> folderinfo) {
        this.folderinfo = folderinfo;
    }
}
