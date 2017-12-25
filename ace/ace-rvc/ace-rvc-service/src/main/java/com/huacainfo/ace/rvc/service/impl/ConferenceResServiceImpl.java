package com.huacainfo.ace.rvc.service.impl;

import com.huacainfo.ace.rvc.base.RvcBaseService;
import com.huacainfo.ace.rvc.dao.RvcConferenceResDao;
import com.huacainfo.ace.rvc.model.RvcConferenceRes;
import com.huacainfo.ace.rvc.service.ConferenceResService;
import com.huacainfo.ace.rvc.util.ResultUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Arvin on 2017/12/22.
 */
@Service("conferenceResServiceImpl")
public class ConferenceResServiceImpl extends RvcBaseService implements ConferenceResService {

    @Resource
    private RvcConferenceResDao rvcConferenceResDao;

    /**
     * 获取会议资源
     *
     * @param resType 资源类型
     *                1-文本内容，2-图片，3-文件，4-视频，5-会议纪要,可多选，多状态间","隔开
     * @param userId  操作人用户id
     * @param confId  会议ID
     * @return
     */
    @Override
    public Map<String, Object> get(String resType, String userId, String confId) {

        String[] strArray = resType.split(",");
        List<Integer> typeArray = new ArrayList<>();
        for (String str : strArray) {
            typeArray.add(Integer.parseInt(str));
        }

        Map<String, Object> condition = new HashMap<>();
        condition.put("typeArray", typeArray);
        condition.put("conferenceId", confId);

        List<RvcConferenceRes> dataList = rvcConferenceResDao.getByType(condition);

        return ResultUtil.success(dataList);
    }
}
