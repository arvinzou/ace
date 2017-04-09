package com.huacainfo.ace.kernel.service.impl;


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
import com.huacainfo.ace.kernel.dao.ActivityPhotosDao;
import com.huacainfo.ace.kernel.model.ActivityPhotos;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.kernel.service.ActivityPhotosService;
import com.huacainfo.ace.kernel.vo.ActivityPhotosVo;
import com.huacainfo.ace.kernel.vo.ActivityPhotosQVo;

@Service("activityPhotosService")
public class ActivityPhotosServiceImpl implements ActivityPhotosService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ActivityPhotosDao activityPhotosDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    public PageResult<ActivityPhotosVo> findActivityPhotosList(ActivityPhotosQVo condition, int start,
                                                               int limit, String orderBy) throws Exception {
        PageResult<ActivityPhotosVo> rst = new PageResult<ActivityPhotosVo>();
        List<ActivityPhotosVo> list = this.activityPhotosDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.activityPhotosDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    public MessageResponse insertActivityPhotos(ActivityPhotos o, UserProp userProp)
            throws Exception {
        o.setId(UUID.randomUUID().toString());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "主题名称不能为空！");
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
        int temp = this.activityPhotosDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "意见反馈名称重复！");
        }
        o.setCreateDate(new Date());
        o.setStatus("0");
        o.setReading(CommonUtils.getRandomNum(100));
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.activityPhotosDao.insert(o);
        this.dataBaseLogService.log("添加意见反馈", "意见反馈", "", o.getName(),
                o.getName(), userProp);
        return new MessageResponse(0, "添加意见反馈完成！");
    }

    public MessageResponse updateActivityPhotos(ActivityPhotos o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "主题名称不能为空！");
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
        this.activityPhotosDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更意见反馈", "意见反馈", "", o.getName(),
                o.getName(), userProp);
        return new MessageResponse(0, "变更意见反馈完成！");
    }

    public SingleResult<ActivityPhotos> selectActivityPhotosByPrimaryKey(String id) throws Exception {
        SingleResult<ActivityPhotos> rst = new SingleResult<ActivityPhotos>();
        rst.setValue(this.activityPhotosDao.selectByPrimaryKey(id));
        return rst;
    }

    public MessageResponse deleteActivityPhotosByActivityPhotosId(String id,
                                                                  UserProp userProp) throws Exception {
        this.activityPhotosDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除意见反馈", "意见反馈", String.valueOf(id),
                String.valueOf(id), "意见反馈", userProp);
        return new MessageResponse(0, "意见反馈删除完成！");
    }
}
