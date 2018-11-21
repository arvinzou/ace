package com.huacainfo.ace.jxb.service.impl;

import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.jxb.dao.*;
import com.huacainfo.ace.jxb.model.BaseOrder;
import com.huacainfo.ace.jxb.model.Counselor;
import com.huacainfo.ace.jxb.model.OrderCalculation;
import com.huacainfo.ace.jxb.model.Studio;
import com.huacainfo.ace.jxb.service.AccountFlowRecordService;
import com.huacainfo.ace.jxb.service.OrderCalculationService;
import com.huacainfo.ace.jxb.vo.CounselorPostLevelVo;
import com.huacainfo.ace.jxb.vo.OrderCalculationQVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: Arvin
 * @Date: 2018/8/7 11:44
 * @Description:
 */
@Service("orderCalculationService")
public class OrderCalculationServiceImpl implements OrderCalculationService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderCalculationDao orderCalculationDao;

    @Autowired
    private BaseOrderDao baseOrderDao;

    @Autowired
    private CounselorPostLevelDao counselorPostLevelDao;
    @Autowired
    private CounselorDao counselorDao;
    @Autowired
    private AccountFlowRecordService accountFlowRecordService;
    @Autowired
    private StudioDao studioDao;

    /**
     * 查询目标列表
     *
     * @param condition 查询条件
     * @param start     页码
     * @param limit     页数
     * @param orderBy   排序条件
     * @return list
     */
    @Override
    public List<OrderCalculation> findList(OrderCalculationQVo condition, int start, int limit, String orderBy) {
        return orderCalculationDao.findList(condition, start, limit, orderBy);
    }

    /**
     * 插入计算记录
     *
     * @param orderId     订单id
     * @param counselorId 咨询师id
     * @return count
     */
    @Override
    public int insertOrderCalculation(String orderId, String counselorId) {
        OrderCalculation record = new OrderCalculation();
        record.setId(GUIDUtil.getGUID());
        record.setOrderId(orderId);
        record.setCounselorId(counselorId);
        record.setCpuTag("0");
        record.setGrantTag("0");
        record.setStatus("1");
        record.setCreateDate(DateUtil.getNowDate());

        return orderCalculationDao.insertSelective(record);
    }

    /**
     * 计算分成金额
     *
     * @param record 分配记录
     */
    @Override
    public void compute(OrderCalculation record) {
        if (null == record) {
            return;
        }
        if ("0".equals(record.getCpuTag())) {
            BaseOrder order = baseOrderDao.selectByPrimaryKey(record.getOrderId());
            if (null == order) {
                record.setRemark("订单计算 - 订单数据异常");
                record.setCpuTag("1");
                record.setGrantTag("1");
                record.setUpdateDate(DateUtil.getNowDate());
                orderCalculationDao.updateByPrimaryKeySelective(record);
                return;
            }
            CounselorPostLevelVo cplVo = counselorPostLevelDao.findByCounselorId(record.getCounselorId());
            if (null == cplVo) {
                record.setRemark("订单计算 - 咨询师岗位数据异常");
                record.setCpuTag("1");
                record.setGrantTag("1");
                record.setUpdateDate(DateUtil.getNowDate());
                orderCalculationDao.updateByPrimaryKeySelective(record);
                return;
            }
            Counselor counselor = counselorDao.selectByPrimaryKey(record.getCounselorId());
            if (null == counselor) {
                record.setRemark("订单计算 - 咨询师数据异常");
                record.setCpuTag("1");
                record.setGrantTag("1");
                record.setUpdateDate(DateUtil.getNowDate());
                orderCalculationDao.updateByPrimaryKeySelective(record);
                return;
            }
            //订单支付金额
            BigDecimal payMoney = order.getPayMoney();
            //分成算法规则 ： 工作室先抽水；平台与卖家（咨询师），按咨询师分成比例，分配剩余金额
            //工作室抽成比例：目前固定 10%
            BigDecimal studioRatio = BigDecimal.ZERO;
            //工作室获得金额
            BigDecimal studioAmount = BigDecimal.ZERO;
            if (StringUtil.isNotEmpty(counselor.getStudioId())) {
                studioRatio = new BigDecimal(0.1);
                studioAmount = payMoney.multiply(studioRatio);
            }
            //抽水后，剩余金额
            BigDecimal balanceAmount = payMoney.subtract(studioAmount);
            //卖家分成比例
            BigDecimal ratio = cplVo.getRatio();
            //卖家获得金额
            BigDecimal amount = balanceAmount.multiply(ratio);
            //平台获得金额
            BigDecimal platformAmount = balanceAmount.subtract(amount);
            //工作室收益
            record.setStudioRatio(studioRatio);
            record.setStudioAmount(studioAmount);
            //卖家收益
            record.setRatio(ratio);
            record.setAmount(amount);
            //平台收益
            record.setPlatformAmount(platformAmount);
            //标记数据
            record.setCpuTag("1");
            record.setRemark("订单计算完成");
            record.setUpdateDate(DateUtil.getNowDate());
            orderCalculationDao.updateByPrimaryKeySelective(record);
            // TODO: 2018/8/7 卖家将获得收益 -- 消息通知
            //工作室将获得收益 -- 消息通知
            if (studioAmount.compareTo(BigDecimal.ZERO) > 0) {
                // TODO: 2018/8/14 工作室将获得收益 -- 消息通知
            }
        }
    }

    @Override
    public int updateByPrimaryKeySelective(OrderCalculation data) {
        return orderCalculationDao.updateByPrimaryKeySelective(data);
    }

    /**
     * 发放分成金额
     *
     * @param record 分配记录
     */
    @Override
    public void grant(OrderCalculation record) throws Exception {
        if (null == record) {
            return;
        }
        //操作 "计算完成" 且 "未发放" 的数据
        if ("1".equals(record.getCpuTag()) && "0".equals(record.getGrantTag())) {
            //1、发放卖家所获收益
            Counselor counselor = counselorDao.selectByPrimaryKey(record.getCounselorId());
            if (null == counselor) {
                record.setRemark("订单发放 - 咨询师数据异常");
                record.setGrantTag("1");
                record.setUpdateDate(DateUtil.getNowDate());
                orderCalculationDao.updateByPrimaryKeySelective(record);
                return;
            }
            BigDecimal income = null == counselor.getIncome() ? BigDecimal.ZERO : counselor.getIncome();
            BigDecimal account = null == counselor.getAccount() ? BigDecimal.ZERO : counselor.getAccount();
            BigDecimal amount = null == record.getAmount() ? BigDecimal.ZERO : record.getAmount();
            //增加累计收益&账户余额
            counselor.setIncome(income.add(amount));
            counselor.setAccount(account.add(amount));
            //2、发放工作室负责人所获收益
            if (StringUtil.isNotEmpty(counselor.getStudioId())
                    && record.getStudioAmount().compareTo(BigDecimal.ZERO) > 0) {
                //工作室信息
                Studio studio = studioDao.selectByPrimaryKey(counselor.getStudioId());
                if (null == studio) {
                    record.setRemark("订单发放 - 工作室数据异常");
                    record.setGrantTag("1");
                    record.setUpdateDate(DateUtil.getNowDate());
                    orderCalculationDao.updateByPrimaryKeySelective(record);
                    return;
                }
                //工作室责任人信息
                Counselor studioC = counselorDao.selectByPrimaryKey(studio.getCounselorId());
                if (null == studioC) {
                    record.setRemark("订单发放 - 工作室责任人数据异常");
                    record.setGrantTag("1");
                    record.setUpdateDate(DateUtil.getNowDate());
                    orderCalculationDao.updateByPrimaryKeySelective(record);
                    return;
                }
                BigDecimal studioIncome = null == studioC.getIncome() ? BigDecimal.ZERO : studioC.getIncome();
                BigDecimal studioAccount = null == studioC.getAccount() ? BigDecimal.ZERO : studioC.getAccount();
                BigDecimal studioAmount = null == record.getStudioAmount() ? BigDecimal.ZERO : record.getStudioAmount();
                //增加累计收益&账户余额
                studioC.setIncome(studioIncome.add(studioAmount));
                studioC.setAccount(studioAccount.add(studioAmount));
                //工作室责任人 -- 收益确认
                counselorDao.updateByPrimaryKeySelective(studioC);
                //工作室责任人 -- 账户流水记录
                accountFlowRecordService.insertRecord(studioC.getId(), "1", "1",
                        studioAmount, record.getOrderId(), "1");
                // TODO: 2018/8/7 工作室责任人到账消息通知
            }
            //卖家数据更新
            counselorDao.updateByPrimaryKeySelective(counselor);
            //卖家账户流水记录
            accountFlowRecordService.insertRecord(counselor.getId(), "1", "1",
                    amount, record.getOrderId(), "1");
            // TODO: 2018/8/7 卖家到账消息通知
        }
    }

}
