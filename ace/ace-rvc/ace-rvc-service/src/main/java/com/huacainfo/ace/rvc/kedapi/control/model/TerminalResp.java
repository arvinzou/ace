package com.huacainfo.ace.rvc.kedapi.control.model;

import com.huacainfo.ace.rvc.kedapi.common.base.BaseModel;

import java.util.List;

/**
 * Created by Arvin on 2017/11/29.
 */
public class TerminalResp extends BaseModel {
//    {
//        "success": 1,
//            "mts": [
//        {
//            "account": "2322231",
//                "account_type": 5,
//                "protocol": 0,
//                "mt_id": 1
//        },
//        {
//            "account": "2322232",
//                "account_type": 5,
//                "protocol": 0,
//                "mt_id": 1
//        }
//  ]
//    }

    private List<TerminalReq> mts;

    public List<TerminalReq> getMts() {
        return mts;
    }

    public void setMts(List<TerminalReq> mts) {
        this.mts = mts;
    }
}
