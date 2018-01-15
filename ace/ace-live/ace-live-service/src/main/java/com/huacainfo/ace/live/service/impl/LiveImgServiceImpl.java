package com.huacainfo.ace.live.service.impl;


import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.live.dao.LiveImgDao;
import com.huacainfo.ace.live.model.LiveImg;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.live.service.LiveImgService;
import com.huacainfo.ace.live.vo.LiveImgVo;
import com.huacainfo.ace.live.vo.LiveImgQVo;

@Service("liveImgService")
/**
 * @author: 陈晓克
 * @version: 2018-01-13
 * @Description: TODO(图片)
 */
public class LiveImgServiceImpl implements LiveImgService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LiveImgDao liveImgDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:insertLiveImg
     * @Description: TODO(添加图片)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-13
     */
    @Override
    public MessageResponse insertLiveImg(LiveImg o, UserProp userProp)
            throws Exception {
        o.setId(UUID.randomUUID().toString());
        if (CommonUtils.isBlank(o.getRptId())) {
            return new MessageResponse(1, "报道编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getUrl())) {
            return new MessageResponse(1, "内容不能为空！");
        }
        this.liveImgDao.insert(o);
        return new MessageResponse(0, "添加图片完成！");
    }

    /**
     * @throws
     * @Title:deleteLiveImgByLiveImgId
     * @Description: TODO(删除图片)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-13
     */
    @Override
    public MessageResponse deleteLiveImgByLiveImgId(String id,
                                                    UserProp userProp) throws Exception {
        this.liveImgDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除图片", "图片", String.valueOf(id),
                String.valueOf(id), "图片", userProp);
        return new MessageResponse(0, "图片删除完成！");
    }
}
