package com.huacainfo.ace.jxb.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.api.WeChatPayApi;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.*;
import com.huacainfo.ace.jxb.constant.AuditConstant;
import com.huacainfo.ace.jxb.constant.RegType;
import com.huacainfo.ace.jxb.dao.CounselorDao;
import com.huacainfo.ace.jxb.dao.WithdrawRecordDao;
import com.huacainfo.ace.jxb.dao.WithdrawWxLogDao;
import com.huacainfo.ace.jxb.model.WithdrawRecord;
import com.huacainfo.ace.jxb.model.WithdrawWxLog;
import com.huacainfo.ace.jxb.service.AccountFlowRecordService;
import com.huacainfo.ace.jxb.service.WithdrawRecordService;
import com.huacainfo.ace.jxb.vo.CounselorVo;
import com.huacainfo.ace.jxb.vo.WithdrawRecordQVo;
import com.huacainfo.ace.jxb.vo.WithdrawRecordVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("withdrawRecordService")
/**
 * @author: Arvin
 * @version: 2018-11-14
 * @Description: TODO(提现申请记录)
 */
public class WithdrawRecordServiceImpl implements WithdrawRecordService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private WithdrawRecordDao withdrawRecordDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private AccountFlowRecordService accountFlowRecordService;
    @Autowired
    private CounselorDao counselorDao;
    @Autowired
    private WithdrawWxLogDao withdrawWxLogDao;

    /**
     * @Description: TODO(提现申请记录分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <WithdrawRecordVo>
     * @author: Arvin
     * @version: 2018-11-14
     */
    @Override
    public PageResult<WithdrawRecordVo> findWithdrawRecordList(WithdrawRecordQVo condition, int start,
                                                               int limit, String orderBy) throws Exception {
        PageResult<WithdrawRecordVo> rst = new PageResult<>();
        List<WithdrawRecordVo> list = this.withdrawRecordDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.withdrawRecordDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @Description: TODO(添加提现申请记录)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-14
     */
    @Override
    public MessageResponse insertWithdrawRecord(WithdrawRecord o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCounselorId())) {
            return new MessageResponse(1, "咨询师编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getOpenId())) {
            return new MessageResponse(1, "咨询师openId不能为空！");
        }
        if (CommonUtils.isBlank(o.getWithdrawType())) {
            return new MessageResponse(1, "提现方式不能为空！");
        }
        if (CommonUtils.isBlank(o.getApplyAmount())) {
            return new MessageResponse(1, "申请金额不能为空！");
        }
        if (CommonUtils.isBlank(o.getAuditRst())) {
            return new MessageResponse(1, "审核状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        this.withdrawRecordDao.insert(o);
        this.dataBaseLogService.log("添加提现申请记录", "提现申请记录", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加提现申请记录完成！");
    }

    /**
     * @Description: TODO(更新提现申请记录)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-14
     */
    @Override
    public MessageResponse updateWithdrawRecord(WithdrawRecord o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCounselorId())) {
            return new MessageResponse(1, "咨询师编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getOpenId())) {
            return new MessageResponse(1, "咨询师openId不能为空！");
        }
        if (CommonUtils.isBlank(o.getWithdrawType())) {
            return new MessageResponse(1, "提现方式 1-微信提现 2-线下打款不能为空！");
        }
        if (CommonUtils.isBlank(o.getApplyAmount())) {
            return new MessageResponse(1, "申请金额不能为空！");
        }
        if (CommonUtils.isBlank(o.getAuditRst())) {
            return new MessageResponse(1, "审核状态 1-审核中 2-审核通过 3-审核失败不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }


        this.withdrawRecordDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更提现申请记录", "提现申请记录", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更提现申请记录完成！");
    }

    /**
     * @Description: TODO(获取提现申请记录)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<WithdrawRecord>
     * @author: Arvin
     * @version: 2018-11-14
     */
    @Override
    public SingleResult<WithdrawRecordVo> selectWithdrawRecordByPrimaryKey(String id) throws Exception {
        SingleResult<WithdrawRecordVo> rst = new SingleResult<>();
        rst.setValue(this.withdrawRecordDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @Description: TODO(删除提现申请记录)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-14
     */
    @Override
    public MessageResponse deleteWithdrawRecordByWithdrawRecordId(String id, UserProp userProp) throws
            Exception {
        this.withdrawRecordDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除提现申请记录", "提现申请记录", id, id,
                "提现申请记录", userProp);
        return new MessageResponse(0, "提现申请记录删除完成！");
    }


    /**
     * @Description: TODO(审核提现申请记录)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-14
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {
        WithdrawRecord record = withdrawRecordDao.selectByPrimaryKey(id);
        if (record == null) {
            return new MessageResponse(ResultCode.FAIL, "记录丢失");
        }
        //扣税操作
        BigDecimal tax = null == record.getTaxAmount() ? BigDecimal.ZERO : record.getTaxAmount();
        BigDecimal actAmount = record.getApplyAmount().subtract(tax);
        if (actAmount.compareTo(BigDecimal.ZERO) <= 0) {
            return new MessageResponse(ResultCode.FAIL, "无效提现金额");
        }
        record.setActAmount(actAmount);
        record.setAuditRst(rst);
        record.setAuditRemark(remark);
        record.setUpdateDate(DateUtil.getNowDate());
        withdrawRecordDao.updateByPrimaryKey(record);
        //调用微信企业支付接口
        if (rst.equals(AuditConstant.PASS) && "1".equals(record.getWithdrawType())) {
            Map<String, Object> apiRst;
            try {
                apiRst = callMchPay(record);
            } catch (Exception e) {
                logger.error("[" + id + "]企业付款调用异常：{}", e);
                apiRst = new HashMap<>();
                apiRst.put("err_code", "-1");
                apiRst.put("err_msg", e.getMessage());
            }
            //接口日志记录
            WithdrawWxLog log = new WithdrawWxLog();
            log.setId(GUIDUtil.getGUID());
            log.setRecordId(record.getId());
            log.setLogTxt(JsonUtil.toJson(apiRst));
            log.setStatus("1");
            log.setCreateDate(DateUtil.getNowDate());
            withdrawWxLogDao.insert(log);
        }

        dataBaseLogService.log("审核提现申请记录", "提现申请记录", id, id, "提现申请记录", userProp);
        return new MessageResponse(ResultCode.SUCCESS, "提现申请记录审核完成！");
    }

    /**
     * 调用微信企业支付接口
     *
     * @param record
     * @return
     */
    private Map<String, Object> callMchPay(WithdrawRecord record) {
        String outTradeNo = record.getId();
        String openId = record.getOpenId();//ogxN71k1hAUwgYDDIhzMplqWbo4U
        String realName = record.getRealName();//"罗灿";
        String amount = record.getActAmount().toString();// "1";
        String desc = record.getAuditRemark();//"企业付款测试";

        String mchAppId = PropertyUtil.getProperty("appid");
        String mchId = PropertyUtil.getProperty("mch_id");
        String apiKey = PropertyUtil.getProperty("api_key");
        byte[] certBytes = FileUtil.File2byte(PropertyUtil.getProperty("cert_path"));
        Map<String, Object> rst = WeChatPayApi.mchPay(outTradeNo, openId, realName, amount, desc,
                mchAppId, mchId, apiKey, certBytes);
//        if ("SUCCESS".equals(rst.get("return_code"))
//                && "200".equals(rst.get("rst_code"))) {
//            Map<String, Object> map = (Map<String, Object>) rst.get("body");
//        }

        return rst;
    }

    /**
     * 余额提现申请
     *
     * @param params 必须
     * @return ResultResponse
     */
    @Override
    public ResultResponse withdraw(WithdrawRecordVo params) throws Exception {
        ResultResponse rs;
        try {
            rs = withdrawLogic(params);
        } catch (Exception e) {
            logger.error("提现处理异常: {}", e);
            return new ResultResponse(ResultCode.FAIL, "系统异常");
        }
        //入账流水
        if (rs.getStatus() == ResultCode.SUCCESS) {
            accountFlowRecordService.insertRecord(params.getCounselorId(), RegType.TEACHER,
                    "app-withdraw", params.getApplyAmount(), params.getId(), "withdraw");
        }

        return rs;
    }

    /**
     * 提现具体实现逻辑
     */
    private ResultResponse withdrawLogic(WithdrawRecordVo params) throws Exception {
        //参数 -- 校验
        if (!StringUtil.areNotEmpty(params.getCounselorId(),
                params.getRealName(),
                params.getWithdrawType())) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }
        if (null == params.getApplyAmount() || params.getApplyAmount().compareTo(BigDecimal.ZERO) == 0) {
            return new ResultResponse(ResultCode.FAIL, "非法提现金额");
        }
        //咨询师 -- 校验
        CounselorVo counselor = counselorDao.selectVoByPrimaryKey(params.getCounselorId());
        if (null == counselor || !"1".equals(counselor.getRegAuditRst())) {
            return new ResultResponse(ResultCode.FAIL, "非法咨询师资料");
        }
        //提现规则校验
        ResultResponse ruleCheck = ruleCheck(counselor, params);
        if (ruleCheck.getStatus() == ResultCode.FAIL) {
            return ruleCheck;
        }
        //余额扣减
        BigDecimal balance = null == counselor.getAccount() ? BigDecimal.ZERO : counselor.getAccount();
        counselor.setAccount(balance.subtract(params.getApplyAmount()));
        int updCount = counselorDao.updateByPrimaryKey(counselor);
        if (updCount != 1) {
            return new ResultResponse(ResultCode.FAIL, "余额扣减失败");
        }
        //入库提现记录
        params.setId(GUIDUtil.getGUID());
        params.setStatus("1");
        params.setAuditRst(AuditConstant.TEMP);
        params.setCreateDate(DateUtil.getNowDate());
        withdrawRecordDao.insert(params);

        return new ResultResponse(ResultCode.SUCCESS, "申请提现成功");
    }

    /**
     * 提现申请，规则限制
     *
     * @param counselor 咨询师信息
     * @param params    提现参数
     * @return ResultResponse
     */
    private ResultResponse ruleCheck(CounselorVo counselor, WithdrawRecordVo params) {
        BigDecimal balance = null == counselor.getAccount() ? BigDecimal.ZERO : counselor.getAccount();//账户余额
        BigDecimal applyAmount = params.getApplyAmount();//申请金额
        BigDecimal min = new BigDecimal(50);
        BigDecimal max = new BigDecimal(5000);
        String dateTime = DateUtil.getNow();//当前系统时间
        //1.提现时间为每月1号至7号
//        int day = Integer.parseInt(dateTime.substring(8, 10));
//        if (day < 1 || day > 7) {
//            return new ResultResponse(ResultCode.FAIL, "超出提现日期（每月1号~7号可提）");
//        }
        //2.提现金额高于50元低于5000元，且低于账户可提现金额
        if (applyAmount.compareTo(min) < 0
                || applyAmount.compareTo(max) > 0) {
            return new ResultResponse(ResultCode.FAIL, "提现金额需处于50~5000之间");
        }
        if (applyAmount.compareTo(balance) > 0) {
            return new ResultResponse(ResultCode.FAIL, "账户余额不足");
        }
        //3.每月只允许一次提现(审核中记录|已通过数据，都算一次)
        WithdrawRecordQVo condition = new WithdrawRecordQVo();
        String[] qryArray = new String[]{AuditConstant.TEMP, AuditConstant.PASS};
        condition.setAuditRstArray(qryArray);//存在审核中、已通过的单据
        condition.setCounselorId(counselor.getId());//此咨询师本人的
        condition.setMontDateStr(dateTime.substring(0, 7));//只查询当月数据
        int recordCount = withdrawRecordDao.findCount(condition);
        if (recordCount >= 1) {
            return new ResultResponse(ResultCode.FAIL, "本月已达提现次数上限");
        }

        return new ResultResponse(ResultCode.SUCCESS, "ok");
    }

}
