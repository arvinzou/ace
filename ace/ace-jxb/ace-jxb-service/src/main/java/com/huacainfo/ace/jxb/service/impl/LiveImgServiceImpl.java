package com.huacainfo.ace.jxb.service.impl;


import java.util.List;
import java.util.UUID;

import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.jxb.vo.LiveImgQVo;
import com.huacainfo.ace.jxb.vo.LiveQVo;
import com.huacainfo.ace.jxb.vo.LiveVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.jxb.dao.LiveImgDao;
import com.huacainfo.ace.jxb.model.LiveImg;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.jxb.service.LiveImgService;
import com.huacainfo.ace.jxb.vo.LiveImgVo;

@Service("jxbImgService")
/**
 * @author: 陈晓克
 * @version: 2018-01-13
 * @Description: TODO(图片)
 */
public class LiveImgServiceImpl implements LiveImgService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LiveImgDao jxbImgDao;
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
        this.jxbImgDao.insert(o);
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
        this.jxbImgDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除图片", "图片", String.valueOf(id),
                String.valueOf(id), "图片", userProp);
        return new MessageResponse(0, "图片删除完成！");
    }

    /**
     * @throws
     * @Title:deleteLiveImgByLiveImgId
     * @Description: TODO(删除一个报道的所有图片)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-13
     */
    @Override
    public MessageResponse deleteLiveImgByRId(String id, UserProp userProp) throws Exception {
        this.jxbImgDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除图片", "图片", String.valueOf(id),
                String.valueOf(id), "图片", userProp);
        return new MessageResponse(0, "图片删除完成！");
    }

    /**
     * @throws
     * @Title:selectLiveByPrimaryKey
     * @Description: TODO(获取直播)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Live>
     * @author: 陈晓克
     * @version: 2017-12-28
     */
    @Override
    public SingleResult<LiveImgVo> selectLiveImgByPrimaryKey(String id) throws Exception {
        SingleResult rst = new SingleResult<>();
        rst.setValue(this.jxbImgDao.selectByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(直播分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<LiveVo>
     * @author: 陈晓克
     * @version: 2017-12-28
     */
    @Override
    public PageResult<LiveImgVo> findLiveImgList(LiveImgQVo condition, String orderBy) throws Exception {
        PageResult<LiveImgVo> rst = new PageResult<LiveImgVo>();
        List<LiveImgVo> list = this.jxbImgDao.findList(condition, orderBy);
        rst.setRows(list);
        return rst;
    }
}
