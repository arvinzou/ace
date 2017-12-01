package com.huacainfo.ace.rvc.kedapi.control.model;

import com.huacainfo.ace.rvc.kedapi.common.base.BaseModel;

import java.util.List;

/**
 * @description: 获取录像列表 成功回复数据模型
 * @author: ArvinZou
 * @create: 2017-11-16 17:35
 */
public class RecordListResp extends BaseModel {
//    {
//        "success": 1,
//            "recorders": [
//        {
//            "rec_id": "22",
//                "video_name": "rectest",
//                "recorder_type": 2,
//                "state": 1,
//                "anonymous": 0,
//                "recorder_mode": 1,
//                "main_stream": 1,
//                "dual_stream": 1,
//                "publish_mode": 0,
//                "current_progress": 1,
//                "members": [
//            {
//                "mt_id": "1"
//            },
//            {
//                "mt_id": "2"
//            }
//      ]
//        },
//        {
//            "rec_id": "23",
//                "video_name": "recname",
//                "recorder_type": 2,
//                "state": 1,
//                "anonymous": 0,
//                "recorder_mode": 1,
//                "main_stream": 1,
//                "dual_stream": 1,
//                "publish_mode": 0,
//                "current_progress": 22,
//                "members": [
//            {
//                "mt_id": "1"
//            },
//            {
//                "mt_id": "2"
//            }
//      ]
//        }
//  ]
//    }

    /**
     * 录像列表
     */
    private List<RecordStatusResp> recorders;

}
