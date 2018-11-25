package com.huacainfo.ace.society.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.society.constant.AuditState;
import com.huacainfo.ace.society.constant.BisType;
import com.huacainfo.ace.society.dao.*;
import com.huacainfo.ace.society.model.*;
import com.huacainfo.ace.society.service.*;
import com.huacainfo.ace.society.vo.ActivityDetailVo;
import com.huacainfo.ace.society.vo.ActivityQVo;
import com.huacainfo.ace.society.vo.ActivityVo;
import com.huacainfo.ace.society.vo.CustomerVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
    @Autowired
    private CoinConfigService coinConfigService;
    @Autowired
    private SocietyOrgInfoDao societyOrgInfoDao;
    @Autowired
    private PersonInfoDao personInfoDao;
    @Autowired
    private PointsRecordDao pointsRecordDao;
    @Autowired
    private RegService regService;
    @Autowired
    private AuditNoticeService auditNoticeService;

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
        o.setCreateDate(new Date());
        o.setStatus("2");
        o.setCoinconfigId(o.getCategory());
        o.setInitiatorId(wxUser.getUnionId());
        o.setCreateUserName(wxUser.getNickName());
        o.setCreateUserId(wxUser.getUnionId());
        int temp = this.activityDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "线下活动名称重复！");
        }
        this.activityDao.insertSelective(o);
        StringBuffer content = new StringBuffer();
        content.append("活动发布审核通知\n")
                .append(wxUser.getNickName())
                .append(" 刚刚发布了一个活动，请及时审核.\n")
                .append("芙蓉街道智慧服务社区系统\n")
                .append(DateUtil.getNow());
        auditNoticeService.sendToAdmin(content.toString());
        auditRecordService.submit(GUIDUtil.getGUID(), BisType.ACTIVITY, o.getId(), wxUser.getUnionId());
        return new MessageResponse(0, "添加线下活动完成！");
    }


    /**
     * @throws
     * @Title:insertActivity
     * @Description: TODO(修改)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-11
     */
    @Override
    public MessageResponse updateActivity(Activity o, WxUser wxUser) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键-GUID不能为空！");
        }

        Activity activity=activityDao.selectByPrimaryKey(o.getId());
        if(activity==null){
            return new MessageResponse(1, "活动数据丢失");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "活动名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getStartDate())) {
            return new MessageResponse(1, "开展时间不能为空！");
        }
        if (!CommonUtils.isBlank(o.getClazz())){
            activity.setClazz(o.getClazz());
        }
        activity.setTitle(o.getTitle());
        activity.setLocation(o.getLocation());
        activity.setMode(o.getMode());
        activity.setPurpose(o.getPurpose());
        activity.setDendline(o.getDendline());
        activity.setStartDate(o.getStartDate());
        activity.setEndDate(o.getEndDate());
        activity.setParterNum(o.getParterNum());
        activity.setCoverUrl(o.getCoverUrl());
        activity.setStatus("2");
        activity.setLastModifyDate(new Date());
        activity.setLastModifyUserName(wxUser.getName());
        activity.setLastModifyUserId(wxUser.getUnionId());
        this.activityDao.updateByPrimaryKeySelective(activity);
        MessageResponse auditRs = auditRecordService.reaudit(BisType.ACTIVITY, activity.getId(),activity.getInitiatorId(), activity.getStatus(), "再次提交审核", wxUser);
        if (ResultCode.FAIL == auditRs.getStatus()) {
            return auditRs;
        }
        StringBuffer content = new StringBuffer();
        content.append("活动发布审核通知\n")
                .append(wxUser.getNickName())
                .append(" 刚刚发布了一个活动，请及时审核.\n")
                .append("芙蓉街道智慧服务社区系统\n")
                .append(DateUtil.getNow());
        auditNoticeService.sendToAdmin(content.toString());
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
            activity.setStartSignImgUrl(filePath);
            activity.setStatus("31");
        }
        else if("activityEnd".equals(type)){
            activity.setEndSignImgUrl(filePath);
            activity.setStatus("32");
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
            return new MessageResponse(ResultCode.SUCCESS, "变更线下活动完成！");
        }else {
            return new MessageResponse(ResultCode.FAIL, "参数错误");
        }
        activity.setLastModifyDate(new Date());
        activity.setLastModifyUserName(wxUser.getNickName());
        activity.setLastModifyUserId(wxUser.getUnionId());
        this.activityDao.updateByPrimaryKeySelective(activity);
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
    public MessageResponse deleteActivityByActivityId(String id, WxUser wxUser) throws Exception {
        if (CommonUtils.isBlank(id)) {
            return new MessageResponse(1, "主键-GUID不能为空！");
        }
        Activity activity=activityDao.selectByPrimaryKey(id);
        if(null==activity){
            return new MessageResponse(ResultCode.FAIL, "线下活动信息丢失！");
        }
        activity.setStatus("0");
        activity.setLastModifyDate(new Date());
        activity.setLastModifyUserName(wxUser.getName());
        activity.setLastModifyUserId(wxUser.getUnionId());
        this.activityDao.updateByPrimaryKeySelective(activity);
/*        this.dataBaseLogService.log("删除线下活动", "线下活动", String.valueOf(id),
                String.valueOf(id), "线下活动", userProp);*/
        return new MessageResponse(0, "线下活动删除完成！");
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
        if (CommonUtils.isBlank(id)) {
            return new MessageResponse(1, "主键-GUID不能为空！");
        }
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
        activity.setLastModifyDate(DateUtil.getNowDate());
        activity.setLastModifyUserId(userProp.getUserId());
        activity.setLastModifyUserName(userProp.getName());
        activityDao.updateByPrimaryKey(activity);
        sendToCustomer(activity, rst, remark);
        dataBaseLogService.log("审核线下活动", "线下活动", String.valueOf(id), String.valueOf(id), "线下活动", userProp);
        return new MessageResponse(0, "线下活动审核完成！");
    }


    private void sendToCustomer(Activity activity, String rst, String remark) {
        try {
            StringBuffer content = new StringBuffer();
            content.append("活动发布审核通知\n")
                    .append(activity.getTitle())
                    .append("，活动发布审核")
                    .append((AuditState.PASS.equals(rst) ? "通过" : "驳回"));
            if (StringUtil.isNotEmpty(remark)) {
                content.append("，[" + remark + "].\n");
            } else {
                content.append(".\n");
            }
            content.append("芙蓉街道智慧服务社区系统\n")
                    .append(DateUtil.getNow());

            auditNoticeService.sendToCustomer(activity.getInitiatorId(), content.toString());
        } catch (Exception e) {
            logger.error("[society]" + "【市民行为】" + "审核消息发送异常[id={}]：{}", activity.getId(), e);
        }
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
    public MessageResponse endAudit(String id, String rst, String message, List list, UserProp userProp) throws Exception {
        Activity activity=activityDao.selectByPrimaryKey(id);
        if(null==activity){
            return new MessageResponse(ResultCode.FAIL, "线下活动信息丢失！");
        }
        if(activity.getStatus().equals(rst)){
            return new MessageResponse(ResultCode.FAIL, "活动状态重复修改");
        }
        MessageResponse auditRs = auditRecordService.audit(BisType.ACTIVITY, id,activity.getInitiatorId(), rst, message, userProp);
        if (ResultCode.FAIL == auditRs.getStatus()) {
            return auditRs;
        }
        activity.setStatus(rst);
        activity.setLastModifyDate(DateUtil.getNowDate());
        activity.setLastModifyUserId(userProp.getUserId());
        activity.setLastModifyUserName(userProp.getName());
        activityDao.updateByPrimaryKey(activity);
        if ("33".equals(rst)){
            CoinConfig coinConfig=coinConfigService.selectCoinConfigByPrimaryKey(activity.getCoinconfigId()).getValue();
            int oCoin=coinConfig.getHost();
            int signNum=list.size();
            int baseNum=coinConfig.getBaseNum();
            int pCoin=coinConfig.getParticipant();
            if(baseNum<signNum){
                oCoin=oCoin+(signNum-baseNum)*coinConfig.getSubjoinNum();
            }
            if (pCoin>0){
                if(!CommonUtils.isBlank(list)){
                    personInfoDao.addCoin(list,activity.getCoinconfigId(),pCoin,pCoin);
                    for(Object item:list){
                        addPeoplePointsRecord(activity.getCategory(),pCoin,(String)item,activity.getId());
                    }
                }
            }
            CustomerVo vo=regService.findByUserId(activity.getInitiatorId());
            String orgId=vo.getSocietyOrg().getId();
            addOrgPointsRecord(activity.getCategory(),oCoin,orgId,activity.getId());
            societyOrgInfoDao.addCoin(orgId, oCoin);
        }
        sendToCustomers(activity, rst, message);
        dataBaseLogService.log("审核线下活动", "线下活动", String.valueOf(id), String.valueOf(id), "线下活动", userProp);
        return new MessageResponse(0, "线下活动审核完成！");
    }


    private void sendToCustomers(Activity activity, String rst, String remark) {
        try {
            StringBuffer content = new StringBuffer();
            content.append("活动签到审核通知\n")
                    .append(activity.getTitle())
                    .append("，活动签到审核")
                    .append("33".equals(rst) ? "通过" : "驳回");
            if (StringUtil.isNotEmpty(remark)) {
                content.append("，[" + remark + "].\n");
            } else {
                content.append(".\n");
            }
            content.append("芙蓉街道智慧服务社区系统\n")
                    .append(DateUtil.getNow());

            auditNoticeService.sendToCustomer(activity.getInitiatorId(), content.toString());
        } catch (Exception e) {
            logger.error("[society]" + "【市民行为】" + "审核消息发送异常[id={}]：{}", activity.getId(), e);
        }
    }

    public ResultResponse addOrgPointsRecord(String type,int coin,String userId,  String bisId){
        String bisType="";
        switch (type) {
            case "1":
                bisType=BisType.POINTS_WELFARE_SPONSOR;
                break;
            case "2":
                bisType=BisType.POINTS_ORDINARY_SPONSOR;
                break;
            case "3":
                bisType=BisType.POINTS_CREATIVE_SPONSOR;
                break;
            case "4":
                bisType=BisType.POINTS_PARTY_SPONSOR;
                break;
            default:
                break;
        }
        PointsRecord record = new PointsRecord();
        record.setId(GUIDUtil.getGUID());
        record.setUserId(userId);
        record.setBisType(bisType);
        record.setBisId(bisId);
        record.setPoints(coin);
        record.setStatus("1");
        record.setCreateDate(DateUtil.getNowDate());
        record.setCreateUserId("system");
        record.setCreateUserName("system");
        pointsRecordDao.insert(record);
        return new ResultResponse(ResultCode.SUCCESS, "记录成功");
    }


    public ResultResponse addPeoplePointsRecord(String type,int coin,String userId,  String bisId){
        String bisType="";
        switch (type) {
            case "1":
                bisType=BisType.POINTS_WELFARE_PARTER_ADD;
                break;
            case "2":
                bisType=BisType.POINTS_ORDINARY_PARTER_ADD;
                break;
            case "3":
                bisType=BisType.POINTS_CREATIVE_PARTER_ADD;
                break;
            case "4":
                bisType=BisType.POINTS_PARTY_PARTER_ADD;
                break;
            default:
                break;
        }
        PointsRecord record = new PointsRecord();
        record.setId(GUIDUtil.getGUID());
        record.setUserId(userId);
        record.setBisType(bisType);
        record.setBisId(bisId);
        record.setPoints(coin);
        record.setStatus("1");
        record.setCreateDate(DateUtil.getNowDate());
        record.setCreateUserId("system");
        record.setCreateUserName("system");
        pointsRecordDao.insert(record);
        return new ResultResponse(ResultCode.SUCCESS, "记录成功");
    }
}
