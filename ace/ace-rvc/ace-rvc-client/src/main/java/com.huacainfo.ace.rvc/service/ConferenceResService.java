package com.huacainfo.ace.rvc.service;

import java.util.Map;

/**
 * Created by Arvin on 2017/12/22.
 */
public interface ConferenceResService {


    /**
     * 获取会议资源
     *
     * @param resType 资源类型
     *                 1-文本内容，2-图片，3-文件，4-视频，5-会议纪要,可多选，多状态间","隔开
     * @param userId  操作人用户id
     * @param confId  会议ID
     * @return
     */
    Map<String, Object> get(String resType, String userId, String confId);
}
