package com.huacainfo.ace.rvc.service.impl;

import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.rvc.base.RvcBaseService;
import com.huacainfo.ace.rvc.dao.RvcConferenceResDao;
import com.huacainfo.ace.rvc.model.RvcConferenceRes;
import com.huacainfo.ace.rvc.service.ConferenceResService;
import com.huacainfo.ace.rvc.util.ResultUtil;
import com.huacainfo.ace.rvc.vo.ConferenceResVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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
        List<ConferenceResVO> voList = new LinkedList<>();
        ConferenceResVO vo;
        for (RvcConferenceRes res : dataList) {
            vo = new ConferenceResVO();
            vo.setAuthor(res.getCreateUserName());
            vo.setDate(DateUtil.format(res.getCreateDate(), DateUtil.DEFAULT_DATE_TIME_REGEX));
            vo.setFileName(res.getResName());
            vo.setFileType(res.getResType());
            vo.setFileURL(res.getResURL());
            vo.setId(res.getId());
            vo.setSize(null == res.getResSize() ? 0 : res.getResSize());

            voList.add(vo);
        }

        return ResultUtil.success(voList);
    }
}
