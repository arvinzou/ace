package com.huacainfo.ace.society.service.impl;


import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.pushmsg.CommonUtil;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.society.constant.AuditState;
import com.huacainfo.ace.society.constant.BisType;
import com.huacainfo.ace.society.dao.ActivityDetailDao;
import com.huacainfo.ace.society.model.ActivityDetail;
import com.huacainfo.ace.society.model.SubjectIdea;
import com.huacainfo.ace.society.service.AuditRecordService;
import com.huacainfo.ace.society.vo.ActivityDetailQVo;
import com.huacainfo.ace.society.vo.ActivityDetailVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.society.dao.ActivityDao;
import com.huacainfo.ace.society.model.Activity;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.society.service.ActivityService;
import com.huacainfo.ace.society.vo.ActivityVo;
import com.huacainfo.ace.society.vo.ActivityQVo;

@Service("activityService")
/**
 * @author: huacai003
 * @version: 2018-09-11
 * @Description: TODO(线下活动)
 */
public class ActivityServiceImpl implements ActivityService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    @Autowired
    private ActivityDetailDao activityDetailDao;
    @Autowired
    private AuditRecordService auditRecordService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(线下活动分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <ActivityVo>
     * @author: huacai003
     * @version: 2018-09-11
     */
    @Override
    public PageResult<ActivityVo> findActivityList(ActivityQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<ActivityVo> rst = new PageResult<>();
        List<ActivityVo> list = this.activityDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.activityDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(线下活动分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <ActivityVo>
     * @author: huacai003
     * @version: 2018-09-11
     */
    @Override
    public PageResult<ActivityVo> findUserActivityList(ActivityQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<ActivityVo> rst = new PageResult<>();
        List<ActivityVo> list = this.activityDao.findUserList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.activityDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertActivity
     * @Description: TODO(添加线下活动)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-11
     */
    @Override
    public MessageResponse insertActivity(Activity o, WxUser wxUser) throws Exception {

        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键-GUID不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "活动名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getStartDate())) {
            return new MessageResponse(1, "开展时间不能为空！");
        }


        int temp = this.activityDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "线下活动名称重复！");
        }
        o.setCreateDate(new Date());
        o.setStatus("2");
        o.setInitiatorId(wxUser.getUnionId());
        o.setCreateUserName(wxUser.getNickName());
        o.setCreateUserId(wxUser.getUnionId());
        this.activityDao.insertSelective(o);
        auditRecordService.submit(GUIDUtil.getGUID(), BisType.ACTIVITY, o.getId(), wxUser.getUnionId());
        this.dataBaseLogService.log("添加线下活动", "线下活动", "",
                o.getId(), o.getId(), null);

        return new MessageResponse(0, "添加线下活动完成！");
    }

    @Override
    public MessageResponse insertActivity(Activity o,UserProp userProp) throws Exception {
        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键-GUID不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "活动名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getStartDate())) {
            return new MessageResponse(1, "开展时间不能为空！");
        }


        int temp = this.activityDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "线下活动名称重复！");
        }
        o.setCreateDate(new Date());
        o.setStatus("2");
        o.setInitiatorId(userProp.getUserId());
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.activityDao.insertSelective(o);
        this.dataBaseLogService.log("添加线下活动", "线下活动", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加线下活动完成！");
    }

    /**
     * @throws
     * @Title:updateActivity
     * @Description: TODO(更新线下活动)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-11
     */
    @Override
    public MessageResponse updateActivity(Activity o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键-GUID不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "活动名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getStartDate())) {
            return new MessageResponse(1, "开展时间不能为空！");
        }
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.activityDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更线下活动", "线下活动", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更线下活动完成！");
    }


    /**
     * @throws
     * @Title:updateActivity
     * @Description: TODO(更新线下活动)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-11
     */
    @Override
    public MessageResponse activitySign(String filePath,String type,String id, WxUser wxUser) throws Exception {
        if (CommonUtils.isBlank(id)){
            return new MessageResponse(ResultCode.FAIL, "主键-GUID不能为空");
        }
        if(CommonUtils.isBlank(type)){
            return new MessageResponse(ResultCode.FAIL, "缺少必要参数");
        }
        Activity activity=activityDao.selectByPrimaryKey(id);
        if(activity==null){
            return new MessageResponse(ResultCode.FAIL, "没有找到相关活动");
        }
        if("activityStart".equals(type)){
            activity.setEndSignImgUrl(filePath);
        }
        else if("activityEnd".equals(type)){
            activity.setEndSignImgUrl(filePath);
        }
        else if("selfSign".equals(type)){
            ActivityDetail activityDetail=activityDetailDao.selectPersonaldetails(id,wxUser.getUnionId());
            if(activityDetail==null){
                return new MessageResponse(ResultCode.FAIL, "在报名名单中没有找到报名记录");
            }
            activityDetail.setSignImgUrl(filePath);
            activityDetail.setSignInState("1");
            activityDetail.setSignInDate(new Date());
            activityDetail.setLastModifyDate(new Date());
            activityDetail.setLastModifyUserName(wxUser.getNickName());
            activityDetail.setLastModifyUserId(wxUser.getUnionId());
            activityDetailDao.updateByPrimaryKey(activityDetail);
            this.dataBaseLogService.log("变更线下活动", "线下活动", "",
                    activityDetail.getId(), activityDetail.getId(), null);

            return new MessageResponse(ResultCode.SUCCESS, "变更线下活动完成！");
        }else {
            return new MessageResponse(ResultCode.FAIL, "参数错误");
        }
        activity.setLastModifyDate(new Date());
        activity.setLastModifyUserName(wxUser.getNickName());
        activity.setLastModifyUserId(wxUser.getUnionId());
        this.activityDao.updateByPrimaryKeySelective(activity);
        this.dataBaseLogService.log("变更线下活动", "线下活动", "",
                activity.getId(), activity.getId(), null);
        return new MessageResponse(ResultCode.SUCCESS, "变更线下活动完成！");
    }


    /**
     * @throws
     * @Title:updateActivity
     * @Description: TODO(更新线下活动)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-11
     */
    @Override
    public MessageResponse softDelete(Activity o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键-GUID不能为空！");
        }
        o.setStatus("0");
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.activityDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("删除线下活动", "线下活动", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "删除线下活动完成！");
    }

    /**
     * @throws
     * @Title:selectActivityByPrimaryKey
     * @Description: TODO(获取线下活动)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Activity>
     * @author: huacai003
     * @version: 2018-09-11
     */
    @Override
    public SingleResult<ActivityVo> selectActivityByPrimaryKey(String id) throws Exception {
        SingleResult<ActivityVo> rst = new SingleResult<>();
        ActivityVo activityVo=this.activityDao.selectVoByPrimaryKeyVo(id);
        if(!CommonUtils.isBlank(activityVo.getEndDate())){
            ActivityDetail activityDetail=new ActivityDetail();
            activityDetail.setActivityId(id);
            List<ActivityDetailVo> list=activityDetailDao.findList(activityDetail,0,100,null);
            activityVo.setTotal(list.size());
            int i=0;
            for(ActivityDetailVo item:list){
                if("1".equals(item.getSignInState())){
                    i++;
                }
            }
            activityVo.setSignNum(i);
            activityVo.setActivityDetailVoList(list);
        }
        rst.setValue(activityVo);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteActivityByActivityId
     * @Description: TODO(删除线下活动)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-11
     */
    @Override
    public MessageResponse deleteActivityByActivityId(String id, UserProp userProp) throws Exception {
        this.activityDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除线下活动", "线下活动", String.valueOf(id),
                String.valueOf(id), "线下活动", userProp);
        return new MessageResponse(0, "线下活动删除完成！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核线下活动)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-11
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,String coinconfigId, UserProp userProp) throws Exception {
        Activity activity=activityDao.selectByPrimaryKey(id);
        if(null==activity){
            return new MessageResponse(ResultCode.FAIL, "线下活动信息丢失！");
        }
        MessageResponse auditRs = auditRecordService.audit(BisType.ACTIVITY, id,activity.getInitiatorId(), rst, remark, userProp);
        if (ResultCode.FAIL == auditRs.getStatus()) {
            return auditRs;
        }
        activity.setStatus(rst);
        if(!CommonUtils.isBlank(coinconfigId)){
            activity.setCoinconfigId(coinconfigId);
        }
        activity.setLastModifyDate(DateUtil.getNowDate());
        activity.setLastModifyUserId(userProp.getUserId());
        activity.setLastModifyUserName(userProp.getName());
        activityDao.updateByPrimaryKey(activity);
        dataBaseLogService.log("审核线下活动", "线下活动", String.valueOf(id), String.valueOf(id), "线下活动", userProp);
        return new MessageResponse(0, "线下活动审核完成！");
    }

//    /**
//     * “我的点子” 送审
//     *
//     * @param ideaId  点子ID
//     * @param unionId
//     * @return ResultResponse
//     * @throws Exception
//     */
//    @Override
//    public ResultResponse sendAudit(String id, String unionId) {
////        Activity activity=activityDao.selectByPrimaryKey(id);
////        if (null == activity) {
////            return new ResultResponse(ResultCode.FAIL, "“点子”数据丢失");
////        }
//        //
////        if (AuditState.SUBMIT_AUDIT.equals(idea.getStatus())) {
////            return new ResultResponse(ResultCode.FAIL, "请勿重复送审");
////        }
//        //提交审核
//        auditRecordService.submit(GUIDUtil.getGUID(), BisType.ACTIVITY, id, unionId);
////        //更新单据状态
////        SubjectIdea record = new SubjectIdea();
////        record.setId(id);
////        record.setStatus(AuditState.SUBMIT_AUDIT);
////        record.setLastModifyDate(DateUtil.getNowDate());
////        record.setLastModifyUserId("system");
////        record.setLastModifyUserId("8888");
////        subjectIdeaDao.updateStatus(record);
//
//        return new ResultResponse(ResultCode.SUCCESS, "送审成功");
//    }


}
