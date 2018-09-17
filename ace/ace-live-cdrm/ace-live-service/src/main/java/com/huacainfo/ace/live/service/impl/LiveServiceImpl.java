package com.huacainfo.ace.live.service.impl;


import java.util.*;

import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.StringUtils;
import com.huacainfo.ace.live.dao.LiveRptDao;
import com.huacainfo.ace.live.vo.LiveRptQVo;
import com.huacainfo.ace.portal.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
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
    private LiveRptDao LiveRptDao;
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
    public PageResult<LiveVo> findLiveList(LiveQVo condition, int start, int limit, String orderBy) throws Exception {

        List<LiveVo> list = this.liveDao.findList(condition,
                start, start + limit, orderBy);
        int reportCount;
        LiveRptQVo rptQVo = new LiveRptQVo();
        for (LiveVo vo : list) {
            rptQVo.setRid(vo.getId());
            rptQVo.setStatus("2");//只统计已发布的报道
            reportCount = LiveRptDao.findCount(rptQVo);
            vo.setReportCount(reportCount);
        }


        PageResult<LiveVo> rst = new PageResult<LiveVo>();
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

        if (CommonUtils.isBlank(o.getStartTime())) {
            return new MessageResponse(1, "开始时间不能为空！");
        }
        if (o.getStartTime().before(DateUtil.getNowDate())) {
            return new MessageResponse(1, "开始时间不能在系统时间之前");
        }
        if (o.getStartTime().after(o.getEndTime())) {
            return new MessageResponse(1, "开始时间结束时间之后");
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
        o.setDeptId(userProp.getCorpId());
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

        if (CommonUtils.isBlank(o.getStartTime())) {
            return new MessageResponse(1, "开始时间不能为空！");
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

    @Override
    public MessageResponse updateLiveSelective(Live o, UserProp userProp)
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
        if (CommonUtils.isBlank(o.getStartTime())) {
            return new MessageResponse(1, "开始时间不能为空！");
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
        this.liveDao.updateByPrimaryKeySelective(o);
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

    @Override
    public MessageResponse insertLive(String openid, Live live) throws Exception {
        MessageResponse response = checkIsBandUsers(openid);
        if (1 == response.getStatus()) {
            return new MessageResponse(1, "新增直播失败,当前用户不合法[openid=" + openid + "]");
        }

        Users users = (Users) response.getOther().get("users");
        //
        UserProp userProp = new UserProp();
        userProp.setName(users.getName());
        userProp.setUserId(users.getUserId());
        userProp.setCorpId(users.getDepartmentId());
        userProp.setAreaCode(users.getAreaCode());
        userProp.setOpenId(openid);
        //
        live.setAreaCode(users.getAreaCode());
        live.setDeptId(users.getDepartmentId());

        return insertLive(live, userProp);
    }

    @Override
    public MessageResponse checkIsBandUsers(String openid) {
        if (StringUtils.isEmpty(openid)) {
            return new MessageResponse(1, "当前微信用户未绑定系统用户,请联系管理员!");
        }

        Users users = liveDao.selectSysUserByOpenid(openid);
        if (null == users) {
            return new MessageResponse(1, "当前微信用户未绑定系统用户,请联系管理员!");
        }

        Map<String, Object> data = new HashMap<>();
        data.put("users", users);

        MessageResponse response = new MessageResponse(0, "身份合法");
        response.setOther(data);
        return response;
    }


}
