package com.huacainfo.ace.uf.service.impl;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.uf.dao.ActivityDao;
import com.huacainfo.ace.uf.model.Activity;
import com.huacainfo.ace.uf.service.ActivityService;
import com.huacainfo.ace.uf.vo.ActivityQVo;
import com.huacainfo.ace.uf.vo.ActivityVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    @Override
    public PageResult<ActivityVo> findActivityList(ActivityQVo condition, int start, int limit, String orderBy)
            throws Exception {
        PageResult<ActivityVo> rst = new PageResult<ActivityVo>();
        List<ActivityVo> list = this.activityDao.findList(condition, start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.activityDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    @Override
    public MessageResponse insertActivity(Activity o, UserProp userProp) throws Exception {
        // o.setId(UUID.randomUUID().toString());
        o.setId(String.valueOf(System.currentTimeMillis()));
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "活动名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getActivityLocation())) {
            return new MessageResponse(1, "活动地点不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "分类不能为空！");
        }
        if (CommonUtils.isBlank(o.getActivityDate())) {
            return new MessageResponse(1, "活动日期不能为空！");
        }
        if (CommonUtils.isBlank(o.getDocText())) {
            return new MessageResponse(1, "活动详细情况不能为空！");
        }
        int temp = this.activityDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "活动名称重复！");
        }
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        o.setReading(CommonUtils.getRandomNum(50, 100));
        this.activityDao.insert(o);
        this.dataBaseLogService.log("添加活动", "活动", "", o.getName(), o.getName(), userProp);
        return new MessageResponse(0, "添加活动完成！");
    }

    @Override
    public MessageResponse updateActivity(Activity o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "活动名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getActivityLocation())) {
            return new MessageResponse(1, "活动地点不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "分类不能为空！");
        }
        if (CommonUtils.isBlank(o.getActivityDate())) {
            return new MessageResponse(1, "活动日期不能为空！");
        }
        if (CommonUtils.isBlank(o.getDocText())) {
            return new MessageResponse(1, "活动详细情况不能为空！");
        }
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        o.setReading(CommonUtils.getRandomNum(50, 100));
        this.activityDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更活动", "活动", "", o.getName(), o.getName(), userProp);
        return new MessageResponse(0, "变更活动完成！");
    }

    @Override
    public SingleResult<ActivityVo> selectActivityByPrimaryKey(String id) throws Exception {
        SingleResult<ActivityVo> rst = new SingleResult<ActivityVo>();
        rst.setValue(this.activityDao.selectByPrimaryKey(id));
        return rst;
    }


    @Override
    public MessageResponse deleteActivityByActivityId(String id, UserProp userProp) throws Exception {
        this.activityDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除活动", "活动", String.valueOf(id), String.valueOf(id), "活动", userProp);
        return new MessageResponse(0, "活动删除完成！");
    }

    @Override
    public List<Map<String, Object>> selectActivityPageList(Map<String, Object> p) {
        int start = Integer.valueOf((String) p.get("start"));
        int limit = Integer.valueOf((String) p.get("limit"));
        return this.activityDao.selectActivityPageList(p, start, limit);
    }

    @Override
    public List<Map<String, Object>> selectPhotoListById(String id) {
        return this.activityDao.selectPhotoListById(id);
    }

    @Override
    public Map<String, Object> selectActivityById(String id) {
        return this.activityDao.selectActivityById(id);
    }

    @Override
    public MessageResponse updateActivity(String id, String type, WxUser user) throws Exception {
        if (type.equals("reading")) {
            this.activityDao.updateReading(id);
        }
        if (type.equals("up")) {
            this.activityDao.updateUp(id);
        }
        if (type.equals("complain")) {
            this.activityDao.updateComplain(id);
        }
        return new MessageResponse(0, "变更活动完成！");
    }
}
