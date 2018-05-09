package com.huacainfo.ace.fop.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.fop.common.constant.AuditResult;
import com.huacainfo.ace.fop.common.constant.FlowType;
import com.huacainfo.ace.fop.dao.FopPayRecordDao;
import com.huacainfo.ace.fop.model.FopFlowRecord;
import com.huacainfo.ace.fop.model.FopPayRecord;
import com.huacainfo.ace.fop.service.FopFlowRecordService;
import com.huacainfo.ace.fop.service.FopPayRecordService;
import com.huacainfo.ace.fop.vo.FopPayRecordQVo;
import com.huacainfo.ace.fop.vo.FopPayRecordVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("fopPayRecordService")
/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: (缴费记录)
 */
public class FopPayRecordServiceImpl implements FopPayRecordService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopPayRecordDao fopPayRecordDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    @Autowired
    private FopFlowRecordService fopFlowRecordService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: (缴费记录分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<FopPayRecordVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public PageResult<FopPayRecordVo> findFopPayRecordList(FopPayRecordQVo condition, int start,
                                                           int limit, String orderBy) throws Exception {
        PageResult<FopPayRecordVo> rst = new PageResult<>();
        List<FopPayRecordVo> list = this.fopPayRecordDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.fopPayRecordDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertFopPayRecord
     * @Description: (添加缴费记录)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse insertFopPayRecord(FopPayRecord o, UserProp userProp)
            throws Exception {

        if (CommonUtils.isBlank(o.getRelationId())) {
            return new MessageResponse(1, "关联ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getRelationType())) {
            return new MessageResponse(1, "关联类型不能为空！");
        }


        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("0");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.fopPayRecordDao.insertSelective(o);
        this.dataBaseLogService.log("添加缴费记录", "缴费记录", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "添加缴费记录完成！");
    }

    /**
     * @throws
     * @Title:updateFopPayRecord
     * @Description: (更新缴费记录)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse updateFopPayRecord(FopPayRecord o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getPayAmount())) {
            return new MessageResponse(1, "缴纳金额不能为空！");
        }
        if (CommonUtils.isBlank(o.getPayDate())) {
            return new MessageResponse(1, "缴费时间不能为空！");
        }
        if (CommonUtils.isBlank(o.getDendline())) {
            return new MessageResponse(1, "有效截止日期不能为空！");
        }
        if (CommonUtils.isBlank(o.getPayResult())) {
            return new MessageResponse(1, "缴费结果不能为空！");
        }


        Date payDate = o.getPayDate();
        Calendar c = Calendar.getInstance();
        c.setTime(payDate);
        int month = c.get(Calendar.MONTH) + 1;
        String quarter = getQuarter(month);
        o.setPayYear(c.get(Calendar.YEAR) + "");
        o.setPayQuarter(quarter);
        o.setPayMonth(month + "");
        o.setPayDay(c.get(Calendar.DATE) + "");
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        o.setPayLevel("0");
        this.fopPayRecordDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更缴费记录", "缴费记录", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "变更缴费记录完成！");
    }

    private String getQuarter(int month) {
        if (month >= 1 && month <= 3) {
            return "第一季度";
        }
        if (month >= 4 && month <= 6) {
            return "第二季度";
        }
        if (month >= 7 && month <= 9) {
            return "第三季度";
        }
        if (month >= 10 && month <= 12) {
            return "第四季度";
        }

        return "";
    }

    /**
     * @throws
     * @Title:selectFopPayRecordByPrimaryKey
     * @Description: (获取缴费记录)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopPayRecord>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public SingleResult<FopPayRecordVo> selectFopPayRecordByPrimaryKey(String id) throws Exception {
        SingleResult<FopPayRecordVo> rst = new SingleResult<FopPayRecordVo>();
        rst.setValue(this.fopPayRecordDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteFopPayRecordByFopPayRecordId
     * @Description: (删除缴费记录)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse deleteFopPayRecordByFopPayRecordId(String id,
                                                              UserProp userProp) throws Exception {
        this.fopPayRecordDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除缴费记录", "缴费记录", String.valueOf(id),
                String.valueOf(id), "缴费记录", userProp);
        return new MessageResponse(0, "缴费记录删除完成！");
    }


    /**
     * 功能描述: 添加缴费记录
     *
     * @param record   支付流水
     * @param userProp 操作员
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/7 16:18
     */
    @Override
    public MessageResponse submitPayRecord(FopPayRecord record, UserProp userProp) throws Exception {

        return insertFopPayRecord(record, userProp);
    }

    /**
     * 功能描述: 确认缴费审核
     *
     * @param id       fop_pay_record.id
     * @param userProp
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/8 13:56
     */
    @Override
    public MessageResponse audit(String id, UserProp userProp) throws Exception {

        FopPayRecord payRecord = fopPayRecordDao.selectByPrimaryKey(id);
        if (null == payRecord) {
            return new MessageResponse(ResultCode.FAIL, "缴费记录不存在");
        }
        if (null == payRecord.getPayDate()
                || BigDecimal.ZERO.compareTo(payRecord.getPayAmount()) > -1) {
            return new MessageResponse(ResultCode.FAIL, "缴费记录信息不全,请补充完整");
        }
        //提交流程，并审核通过
        MessageResponse rs = fopFlowRecordService.submitFlowRecord(GUIDUtil.getGUID(),
                FlowType.MEMBER_PAY, payRecord.getId(), userProp);
        if (ResultCode.FAIL == rs.getStatus()) {
            return rs;
        }
        FopFlowRecord flowRecord = fopFlowRecordService.findByFromId(payRecord.getId(), FlowType.MEMBER_PAY);
        flowRecord.setAuditResult(AuditResult.PASS);
        flowRecord.setAuditOpinion("自动审核");
        flowRecord.setRemark("系统自动审核");
        MessageResponse rs2 = fopFlowRecordService.audit(flowRecord, userProp);
        if (ResultCode.FAIL == rs2.getStatus()) {
            return rs2;
        }

        return new MessageResponse(ResultCode.SUCCESS, "审核完成");
    }

    /**
     * 功能描述: 推送催缴通知
     *
     * @param id          fop_pay_record.id
     * @param curUserProp
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/8 13:56
     */
    @Override
    public MessageResponse sendNotice(String id, UserProp curUserProp) throws Exception {
        // TODO: 2018/5/8 消息发送逻辑

        return new MessageResponse(ResultCode.SUCCESS, "消息发送成功");
    }

    @Override
    public FopPayRecord selectByPrimaryKey(String id) {
        return fopPayRecordDao.selectByPrimaryKey(id);
    }
}
