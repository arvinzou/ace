package com.huacainfo.ace.society.service.impl;


import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.society.constant.BisType;
import com.huacainfo.ace.society.model.Activity;
import com.huacainfo.ace.society.service.AuditRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.society.dao.ActivityReportDao;
import com.huacainfo.ace.society.model.ActivityReport;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.society.service.ActivityReportService;
import com.huacainfo.ace.society.vo.ActivityReportVo;
import com.huacainfo.ace.society.vo.ActivityReportQVo;

@Service("activityReportService")
/**
 * @author: huacai003
 * @version: 2018-09-13
 * @Description: TODO(活动报道)
 */
public class ActivityReportServiceImpl implements ActivityReportService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ActivityReportDao activityReportDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private AuditRecordService auditRecordService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(活动报道分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <ActivityReportVo>
     * @author: huacai003
     * @version: 2018-09-13
     */
    @Override
    public PageResult<ActivityReportVo> findActivityReportList(ActivityReportQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<ActivityReportVo> rst = new PageResult<>();
        List<ActivityReportVo> list = this.activityReportDao.adminFindList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.activityReportDao.adminFindCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertActivityReport
     * @Description: TODO(添加活动报道)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-13
     */
    @Override
    public MessageResponse insertActivityReport(ActivityReport o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键-GUID不能为空！");
        }
        if (CommonUtils.isBlank(o.getActivityId())) {
            return new MessageResponse(1, "活动编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "报道标题不能为空！");
        }


        int temp = this.activityReportDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "活动报道名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.activityReportDao.insertSelective(o);
        this.dataBaseLogService.log("添加活动报道", "活动报道", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加活动报道完成！");
    }

    /**
     * @throws
     * @Title:updateActivityReport
     * @Description: TODO(更新活动报道)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-13
     */
    @Override
    public MessageResponse updateActivityReport(ActivityReport o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键-GUID不能为空！");
        }
        if (CommonUtils.isBlank(o.getActivityId())) {
            return new MessageResponse(1, "活动编码不能为空！");
        }
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.activityReportDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更活动报道", "活动报道", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更活动报道完成！");
    }


    /**
     * @throws
     * @Title:updateActivityReport
     * @Description: TODO(更新活动报道)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-13
     */
    @Override
    public ResultResponse WxUpdateActivityReport(ActivityReport o, WxUser wxUser) throws Exception {
        if (CommonUtils.isBlank(o.getActivityId())) {
            return new ResultResponse(ResultCode.FAIL, "活动编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new ResultResponse(ResultCode.FAIL, "报道标题不能为空！");
        }
        ActivityReport activityReport = this.activityReportDao.selectByActivityId(o.getActivityId());
        activityReport.setContent(o.getContent());
        activityReport.setStatus(o.getStatus());
        activityReport.setTitle(o.getTitle());
        activityReport.setCoverUrl(o.getCoverUrl());
        activityReport.setLastModifyDate(new Date());
        activityReport.setLastModifyUserName(wxUser.getNickName());
        activityReport.setLastModifyUserId(wxUser.getUnionId());
        this.activityReportDao.updateByPrimaryKeySelective(activityReport);
//        this.dataBaseLogService.log("变更活动报道", "活动报道", "", activityReport.getId(), activityReport.getId(), null);
        return new ResultResponse(ResultCode.SUCCESS, "变更活动报道完成！");
    }


    /**
     * @throws
     * @Title:selectActivityReportByPrimaryKey
     * @Description: TODO(获取活动报道)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<ActivityReport>
     * @author: huacai003
     * @version: 2018-09-13
     */
    @Override
    public SingleResult<ActivityReportVo> selectActivityReportByPrimaryKey(String id) throws Exception {
        SingleResult<ActivityReportVo> rst = new SingleResult<>();
        rst.setValue(this.activityReportDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:selectActivityReportByPrimaryKey
     * @Description: TODO(获取活动报道)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<ActivityReport>
     * @author: huacai003
     * @version: 2018-09-13
     */
    @Override
    public ActivityReport selectActivityReportByActivityId(String id, WxUser wxUser) throws Exception {
        ActivityReport activityReport = this.activityReportDao.selectByActivityId(id);
        if (CommonUtils.isBlank(activityReport)) {
            activityReport = new ActivityReport();
            activityReport.setId(GUIDUtil.getGUID());
            activityReport.setActivityId(id);
            activityReport.setCreateDate(new Date());
            activityReport.setStatus("1");
            activityReport.setCreateUserName(wxUser.getNickName());
            activityReport.setCreateUserId(wxUser.getUnionId());
            activityReport.setLastModifyUserName(wxUser.getNickName());
            activityReport.setLastModifyUserId(wxUser.getUnionId());
            activityReport.setLastModifyDate(new Date());
            this.activityReportDao.insertSelective(activityReport);
            auditRecordService.submit(GUIDUtil.getGUID(), BisType.ACTIVITY_REPORT, activityReport.getId(), wxUser.getUnionId());
        }
        return activityReport;
    }

    /**
     * @throws
     * @Title:deleteActivityReportByActivityReportId
     * @Description: TODO(删除活动报道)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-13
     */
    @Override
    public MessageResponse deleteActivityReportByActivityReportId(String id, UserProp userProp) throws Exception {
        this.activityReportDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除活动报道", "活动报道",
                String.valueOf(id),
                String.valueOf(id), "活动报道", userProp);
        return new MessageResponse(0, "活动报道删除完成！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核活动报道)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-13
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark, UserProp userProp) throws Exception {
        ActivityReport activityReport = activityReportDao.selectByPrimaryKey(id);
        if (null == activityReport) {
            return new MessageResponse(ResultCode.FAIL, "活动报道信息丢失！");
        }
        MessageResponse auditRs = auditRecordService.audit(BisType.ACTIVITY_REPORT, id, id, rst, remark, userProp);
        if (ResultCode.FAIL == auditRs.getStatus()) {
            return auditRs;
        }
        activityReport.setStatus(rst);
        activityReport.setLastModifyDate(DateUtil.getNowDate());
        activityReport.setLastModifyUserId(userProp.getUserId());
        activityReport.setLastModifyUserName(userProp.getName());
        activityReportDao.updateByPrimaryKey(activityReport);
        dataBaseLogService.log("审核线下活动报道", "线下活动报道", String.valueOf(id),
                String.valueOf(id), "线下活动", userProp);
        return new MessageResponse(0, "线下活动报道审核完成！");
    }

}
