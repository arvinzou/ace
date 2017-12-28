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
import com.huacainfo.ace.live.dao.LiveDao;
import com.huacainfo.ace.live.model.Live;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.live.service.LiveService;
import com.huacainfo.ace.live.vo.LiveVo;
import com.huacainfo.ace.live.vo.LiveQVo;

@Service("liveService")
/**
 * @author: 陈晓克
 * @version: 2017-12-28
 * @Description: TODO(直播)
 */
public class LiveServiceImpl implements LiveService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LiveDao liveDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

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
    public PageResult<LiveVo> findLiveList(LiveQVo condition, int start,
                                           int limit, String orderBy) throws Exception {
        PageResult<LiveVo> rst = new PageResult<LiveVo>();
        List<LiveVo> list = this.liveDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.liveDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertLive
     * @Description: TODO(添加直播)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2017-12-28
     */
    @Override
    public MessageResponse insertLive(Live o, UserProp userProp)
            throws Exception {
        o.setId(UUID.randomUUID().toString());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "直播类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getDeptName())) {
            return new MessageResponse(1, "组织单位不能为空！");
        }
        if (CommonUtils.isBlank(o.getBirthday())) {
            return new MessageResponse(1, "直播时间不能为空！");
        }
        if (CommonUtils.isBlank(o.getRemark())) {
            return new MessageResponse(1, "摘要不能为空！");
        }
        if (CommonUtils.isBlank(o.getContent())) {
            return new MessageResponse(1, "活动介绍不能为空！");
        }
        if (CommonUtils.isBlank(o.getNop())) {
            return new MessageResponse(1, "参与人数不能为空！");
        }
        if (CommonUtils.isBlank(o.getPop())) {
            return new MessageResponse(1, "点赞数不能为空！");
        }
        if (CommonUtils.isBlank(o.getAddr())) {
            return new MessageResponse(1, "地点不能为空！");
        }
        if (CommonUtils.isBlank(o.getRtmpUrl())) {
            return new MessageResponse(1, "直播流地址不能为空！");
        }
        if (CommonUtils.isBlank(o.getMp4Url())) {
            return new MessageResponse(1, "回放地址不能为空！");
        }
        if (CommonUtils.isBlank(o.getImageSrc())) {
            return new MessageResponse(1, "封面不能为空！");
        }
        int temp = this.liveDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "直播名称重复！");
        }
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.liveDao.insert(o);
        this.dataBaseLogService.log("添加直播", "直播", "", o.getName(),
                o.getName(), userProp);
        return new MessageResponse(0, "添加直播完成！");
    }

    /**
     * @throws
     * @Title:updateLive
     * @Description: TODO(更新直播)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2017-12-28
     */
    @Override
    public MessageResponse updateLive(Live o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "直播类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getDeptName())) {
            return new MessageResponse(1, "组织单位不能为空！");
        }
        if (CommonUtils.isBlank(o.getBirthday())) {
            return new MessageResponse(1, "直播时间不能为空！");
        }
        if (CommonUtils.isBlank(o.getRemark())) {
            return new MessageResponse(1, "摘要不能为空！");
        }
        if (CommonUtils.isBlank(o.getContent())) {
            return new MessageResponse(1, "活动介绍不能为空！");
        }
        if (CommonUtils.isBlank(o.getNop())) {
            return new MessageResponse(1, "参与人数不能为空！");
        }
        if (CommonUtils.isBlank(o.getPop())) {
            return new MessageResponse(1, "点赞数不能为空！");
        }
        if (CommonUtils.isBlank(o.getAddr())) {
            return new MessageResponse(1, "地点不能为空！");
        }
        if (CommonUtils.isBlank(o.getRtmpUrl())) {
            return new MessageResponse(1, "直播流地址不能为空！");
        }
        if (CommonUtils.isBlank(o.getMp4Url())) {
            return new MessageResponse(1, "回放地址不能为空！");
        }
        if (CommonUtils.isBlank(o.getImageSrc())) {
            return new MessageResponse(1, "封面不能为空！");
        }
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.liveDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更直播", "直播", "", o.getName(),
                o.getName(), userProp);
        return new MessageResponse(0, "变更直播完成！");
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
    public SingleResult<LiveVo> selectLiveByPrimaryKey(String id) throws Exception {
        SingleResult<LiveVo> rst = new SingleResult<LiveVo>();
        rst.setValue(this.liveDao.selectByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteLiveByLiveId
     * @Description: TODO(删除直播)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2017-12-28
     */
    @Override
    public MessageResponse deleteLiveByLiveId(String id,
                                              UserProp userProp) throws Exception {
        this.liveDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除直播", "直播", String.valueOf(id),
                String.valueOf(id), "直播", userProp);
        return new MessageResponse(0, "直播删除完成！");
    }
}
