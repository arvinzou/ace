package com.huacainfo.ace.fop.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.fop.common.constant.AuditResult;
import com.huacainfo.ace.fop.common.constant.FlowType;
import com.huacainfo.ace.fop.dao.FopActivityDao;
import com.huacainfo.ace.fop.model.FopActivity;
import com.huacainfo.ace.fop.model.FopFlowRecord;
import com.huacainfo.ace.fop.service.FopActivityService;
import com.huacainfo.ace.fop.service.FopFlowRecordService;
import com.huacainfo.ace.fop.vo.FopActivityQVo;
import com.huacainfo.ace.fop.vo.FopActivityVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("fopActivityService")
/**
 * @author: Arvin
 * @version: 2018-05-09
 * @Description: 企业/协会活动
 */
public class FopActivityServiceImpl implements FopActivityService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopActivityDao fopActivityDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private FopFlowRecordService fopFlowRecordService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(企业/协会活动分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <FopActivityVo>
     * @author: Arvin
     * @version: 2018-05-09
     */
    @Override
    public PageResult<FopActivityVo> findFopActivityList(FopActivityQVo condition, int start,
                                                         int limit, String orderBy) throws Exception {
        PageResult<FopActivityVo> rst = new PageResult<FopActivityVo>();
        List<FopActivityVo> list = this.fopActivityDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.fopActivityDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertFopActivity
     * @Description: TODO(添加企业/协会活动)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-09
     */
    @Override
    public MessageResponse insertFopActivity(FopActivity o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getRelationId())) {
            return new MessageResponse(1, "关联ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getRelationType())) {
            return new MessageResponse(1, "关联类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getActivityType())) {
            return new MessageResponse(1, "活动类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "活动标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getReleaseDate())) {
            return new MessageResponse(1, "发布时间不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "活动分类不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }


        int temp = this.fopActivityDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "企业/协会活动名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.fopActivityDao.insertSelective(o);
        this.dataBaseLogService.log("添加企业/协会活动", "企业/协会活动", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加企业/协会活动完成！");
    }

    /**
     * @throws
     * @Title:updateFopActivity
     * @Description: TODO(更新企业/协会活动)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-09
     */
    @Override
    public MessageResponse updateFopActivity(FopActivity o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getRelationId())) {
            return new MessageResponse(1, "关联ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getRelationType())) {
            return new MessageResponse(1, "关联类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getActivityType())) {
            return new MessageResponse(1, "活动类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "活动标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getReleaseDate())) {
            return new MessageResponse(1, "发布时间不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "活动分类不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.fopActivityDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更企业/协会活动", "企业/协会活动", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更企业/协会活动完成！");
    }

    /**
     * @throws
     * @Title:selectFopActivityByPrimaryKey
     * @Description: TODO(获取企业/协会活动)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopActivity>
     * @author: Arvin
     * @version: 2018-05-09
     */
    @Override
    public SingleResult<FopActivityVo> selectFopActivityByPrimaryKey(String id) throws Exception {
        SingleResult<FopActivityVo> rst = new SingleResult<FopActivityVo>();
        rst.setValue(this.fopActivityDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteFopActivityByFopActivityId
     * @Description: TODO(删除企业/协会活动)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-09
     */
    @Override
    public MessageResponse deleteFopActivityByFopActivityId(String id, UserProp userProp) throws Exception {

        FopActivity activity = fopActivityDao.selectByPrimaryKey(id);
        if (null == activity) {
            return new MessageResponse(ResultCode.FAIL, "数据记录丢失");
        }


        activity.setStatus("0");
        activity.setLastModifyDate(DateUtil.getNowDate());
        activity.setLastModifyUserId(userProp.getUserId());
        activity.setLastModifyUserName(userProp.getName());
        fopActivityDao.updateByPrimaryKeySelective(activity);

        dataBaseLogService.log("删除企业/协会活动", "企业/协会活动",
                String.valueOf(id), String.valueOf(id), "企业/协会活动", userProp);
        return new MessageResponse(0, "企业/协会活动删除完成！");
    }

    /**
     * 功能描述:  发布审核
     *
     * @param id
     * @param userProp
     * @param:
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/9 15:12
     */
    @Override
    public MessageResponse audit(String id, UserProp userProp) throws Exception {
        FopActivity activity = fopActivityDao.selectByPrimaryKey(id);
        if (null == activity) {
            return new MessageResponse(ResultCode.FAIL, "数据记录丢失");
        }
        //提交审核流程
        String flowId = GUIDUtil.getGUID();
        MessageResponse mr = fopFlowRecordService.submitFlowRecord(flowId, FlowType.PARTY_WORK, activity.getId(), userProp);
        if (ResultCode.FAIL == mr.getStatus()) {
            return mr;
        }
        //自动审核
        FopFlowRecord flowRecord = fopFlowRecordService.selectByPrimaryKey(flowId);
        flowRecord.setAuditResult(AuditResult.PASS);
        flowRecord.setAuditOpinion("系统自动审核");
        flowRecord.setRemark("系统自动审核");
        MessageResponse mr1 = fopFlowRecordService.audit(flowRecord, userProp);
        if (ResultCode.FAIL == mr1.getStatus()) {
            throw new CustomException(mr1.getErrorMessage());
        }

        return new MessageResponse(ResultCode.FAIL, "发布成功");
    }

    @Override
    public FopActivity selectByPrimaryKey(String id) {

        return fopActivityDao.selectByPrimaryKey(id);
    }

}
