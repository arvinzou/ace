package com.huacainfo.ace.cu.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.cu.common.constant.OrderConstant;
import com.huacainfo.ace.cu.common.constant.ProjectConstant;
import com.huacainfo.ace.cu.dao.CcbCallbackLogDao;
import com.huacainfo.ace.cu.dao.CuDonateOrderDao;
import com.huacainfo.ace.cu.dao.CuUserDao;
import com.huacainfo.ace.cu.dao.WxPayLogDao;
import com.huacainfo.ace.cu.model.CcbCallbackLog;
import com.huacainfo.ace.cu.model.CuDonateOrder;
import com.huacainfo.ace.cu.model.CuUser;
import com.huacainfo.ace.cu.model.WxPayLog;
import com.huacainfo.ace.cu.service.CuDonateListService;
import com.huacainfo.ace.cu.service.CuDonateOrderService;
import com.huacainfo.ace.cu.service.CuProjectService;
import com.huacainfo.ace.cu.service.CuUserService;
import com.huacainfo.ace.cu.vo.CuDonateOrderQVo;
import com.huacainfo.ace.cu.vo.CuDonateOrderVo;
import com.huacainfo.ace.cu.vo.CuProjectVo;
import com.huacainfo.ace.cu.vo.CuUserVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service("cuDonateOrderService")
/**
 * @author: Arvin
 * @version: 2018-06-14
 * @Description: (捐款支付订单)
 */
public class CuDonateOrderServiceImpl implements CuDonateOrderService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CuDonateOrderDao cuDonateOrderDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private CuProjectService cuProjectService;
    @Autowired
    private CuUserService cuUserService;
    @Autowired
    private CuDonateListService cuDonateListService;
    @Autowired
    private CuUserDao cuUserDao;
    @Autowired
    private WxPayLogDao wxPayLogDao;
    @Autowired
    private CcbCallbackLogDao ccbCallbackLogDao;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: (捐款支付订单分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CuDonateOrderVo>
     * @author: Arvin
     * @version: 2018-06-14
     */
    @Override
    public PageResult<CuDonateOrderVo> findCuDonateOrderList(CuDonateOrderQVo condition, int start,
                                                             int limit, String orderBy) throws Exception {
        PageResult<CuDonateOrderVo> rst = new PageResult<>();
        List<CuDonateOrderVo> list = this.cuDonateOrderDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.cuDonateOrderDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertCuDonateOrder
     * @Description: (添加捐款支付订单)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    @Override
    public MessageResponse insertCuDonateOrder(CuDonateOrder o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getProjectId())) {
            return new MessageResponse(1, "所属项目ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "用户ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getOpenId())) {
            return new MessageResponse(1, "微信用户openid不能为空！");
        }
        if (CommonUtils.isBlank(o.getDonateAmount())) {
            return new MessageResponse(1, "捐献金额不能为空！");
        }
        if (CommonUtils.isBlank(o.getDonateDate())) {
            return new MessageResponse(1, "捐献时间不能为空！");
        }
        if (CommonUtils.isBlank(o.getOrderNo())) {
            return new MessageResponse(1, "订单编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getPayType())) {
            return new MessageResponse(1, "支付方式不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }


        int temp = this.cuDonateOrderDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "捐款支付订单名称重复！");
        }


        o.setAnonymity(StringUtil.isEmpty(o.getAnonymity()) ? "0" : o.getAnonymity());
        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.cuDonateOrderDao.insertSelective(o);
        this.dataBaseLogService.log("添加捐款支付订单", "捐款支付订单", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加捐款支付订单完成！");
    }

    /**
     * @throws
     * @Title:updateCuDonateOrder
     * @Description: (更新捐款支付订单)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    @Override
    public MessageResponse updateCuDonateOrder(CuDonateOrder o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getProjectId())) {
            return new MessageResponse(1, "所属项目ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "用户ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getOpenId())) {
            return new MessageResponse(1, "微信用户openid不能为空！");
        }
        if (CommonUtils.isBlank(o.getDonateAmount())) {
            return new MessageResponse(1, "捐献金额不能为空！");
        }
        if (CommonUtils.isBlank(o.getDonateDate())) {
            return new MessageResponse(1, "捐献时间不能为空！");
        }
        if (CommonUtils.isBlank(o.getOrderNo())) {
            return new MessageResponse(1, "订单编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getPayType())) {
            return new MessageResponse(1, "支付方式不能为空！");
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
        this.cuDonateOrderDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更捐款支付订单", "捐款支付订单", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更捐款支付订单完成！");
    }

    /**
     * @throws
     * @Title:selectCuDonateOrderByPrimaryKey
     * @Description: (获取捐款支付订单)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CuDonateOrder>
     * @author: Arvin
     * @version: 2018-06-14
     */
    @Override
    public SingleResult<CuDonateOrderVo> selectCuDonateOrderByPrimaryKey(String id) throws Exception {
        SingleResult<CuDonateOrderVo> rst = new SingleResult<>();
        rst.setValue(this.cuDonateOrderDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteCuDonateOrderByCuDonateOrderId
     * @Description: (删除捐款支付订单)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    @Override
    public MessageResponse deleteCuDonateOrderByCuDonateOrderId(String id, UserProp userProp) throws Exception {
        this.cuDonateOrderDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除捐款支付订单", "捐款支付订单",
                String.valueOf(id),
                String.valueOf(id), "捐款支付订单", userProp);
        return new MessageResponse(0, "捐款支付订单删除完成！");
    }

    /**
     * 创建捐款订单
     *
     * @param data 参考 CuDonateOrderVo.java对象
     * @return
     * @throws Exception
     */
    @Override
    public ResultResponse createDonateOrder(CuDonateOrderVo data) {
        CuProjectVo projectVo = cuProjectService.selectVoByPrimaryKey(data.getProjectId());
        if (null == projectVo) {
            return new ResultResponse(ResultCode.FAIL, "项目信息不存在！");
        }
        if (!ProjectConstant.P_STATUS_PASSED.equals(projectVo.getStatus())) {
            return new ResultResponse(ResultCode.FAIL, "该项目未通过审核！");
        }
        if (projectVo.getBalanceDays() == 0) {
            return new ResultResponse(ResultCode.FAIL, "项目已结束，捐款通道关闭！");
        }
        CuUserVo userVo = cuUserService.findByOpenId(data.getOpenId());
        if (null == userVo) {
            return new ResultResponse(ResultCode.FAIL, "用户信息不存在！");
        }
        if (BigDecimal.ZERO.compareTo(data.getDonateAmount()) >= 0) {
            return new ResultResponse(ResultCode.FAIL, "捐款金额不合法！");
        }
        if (CommonUtils.isBlank(data.getPayType())) {
            return new ResultResponse(ResultCode.FAIL, "支付方式不能为空！");
        }
        if ("1".equals(data.getNeedReceipt()) &&
                !StringUtil.areNotEmpty(data.getConsigneeName(), data.getCountry(), data.getProvince(),
                        data.getCity(), data.getDistrict(), data.getAddress(), data.getConsigneeMobileNumber())) {
            return new ResultResponse(ResultCode.FAIL, "收货信息不全！");
        }

        data.setPoints(new BigDecimal(data.getDonateAmount().intValue()));//1块钱=1积分,无小数
        data.setOrderNo(generateOrderNo());
        data.setUserId(userVo.getId());
        data.setAnonymity(StringUtil.isEmpty(data.getAnonymity()) ? "0" : data.getAnonymity());
        data.setId(GUIDUtil.getGUID());
        data.setStatus("1");
        data.setLastModifyDate(new Date());
        data.setCreateDate(new Date());
        data.setCreateUserName("system");
        data.setCreateUserId("0000-0000");
        int count = cuDonateOrderDao.insertSelective(data);
        if (count == 1) {
            return new ResultResponse(ResultCode.SUCCESS, "订单创建成功", data);
        }
        return new ResultResponse(ResultCode.FAIL, "订单创建失败");
    }


    /**
     * 订单校验
     *
     * @param attach 附加数据 --  此处存放 cu_donate_order.id
     * @param fee    支付金额
     * @return
     */
    @Override
    public ResultResponse orderCheck(String attach, String fee) {
        CuDonateOrder order = cuDonateOrderDao.selectByPrimaryKey(attach);
        if (null == order) {
            return new ResultResponse(ResultCode.FAIL, "订单数据丢失");
        }
        if (!order.getStatus().equals(OrderConstant.ORDER_STATUS_NEW_ORDER)) {
            return new ResultResponse(ResultCode.FAIL, "订单状态异常");
        }
        if (order.getDonateAmount().compareTo(new BigDecimal(fee)) != 0) {
            return new ResultResponse(ResultCode.FAIL, "订单支付金额不匹配");
        }

        return new ResultResponse(ResultCode.SUCCESS, "单据合法");
    }

    /**
     * 订单支付逻辑
     *
     * @param orderId   订单ID
     * @param payType   支付方式
     * @param payAmount 支付金额
     * @return: ResultResponse
     * @auther: Arvin Zou
     * @date: 2018/9/7 16:41
     */
    @Override
    public ResultResponse pay(String orderId, String payType, BigDecimal payAmount) {
        //查询订单
//        String orderId = wxPayLog.getAttach();
        CuDonateOrder order = cuDonateOrderDao.selectByPrimaryKey(orderId);
        if (null == order) {
            return new ResultResponse(ResultCode.FAIL, "订单信息异常");
        } else if (!payType.equals(order.getPayType())) {
            return new ResultResponse(ResultCode.FAIL, "支付方式不匹配");
        } else if (!OrderConstant.ORDER_STATUS_NEW_ORDER.equals(order.getStatus())) {
            return new ResultResponse(ResultCode.FAIL, "付款状态异常");
        } else if (order.getDonateAmount().compareTo(payAmount) != 0) {
            return new ResultResponse(ResultCode.FAIL, "付款金额不符");
        }
        //订单状态修改
        order.setStatus(OrderConstant.ORDER_STATUS_PAID);
        order.setPayDate(DateUtil.getNowDate());
        order.setDonateDate(DateUtil.getNowDate());
        order.setLastModifyDate(DateUtil.getNowDate());
        //增加捐献记录
        ResultResponse rs1 = cuDonateListService.addDonateList(order);
        if (ResultCode.FAIL == rs1.getStatus()) {
            throw new CustomException(rs1.getInfo());
        }
        //调整项目属性
        ResultResponse rs2 = cuProjectService.pay(order);
        if (ResultCode.FAIL == rs2.getStatus()) {
            throw new CustomException(rs2.getInfo());
        }
        //增加个人积分
        ResultResponse rs3 = updateUserPoints(order);
        if (ResultCode.FAIL == rs3.getStatus()) {
            throw new CustomException(rs3.getInfo());
        }
        //订单更新
        cuDonateOrderDao.updateByPrimaryKey(order);

        return new ResultResponse(ResultCode.SUCCESS, "订单支付处理成功");
    }

    @Override
    public int insertWxPayLog(WxPayLog wxPayLog) {
        wxPayLog.setId(GUIDUtil.getGUID());
        wxPayLog.setStatus("1");
        wxPayLog.setCreateUserId("0000-0000");
        wxPayLog.setCreateUserName("system");
        wxPayLog.setCreateDate(DateUtil.getNowDate());
        wxPayLog.setLastModifyDate(DateUtil.getNowDate());
        return wxPayLogDao.insert(wxPayLog);
    }

    /**
     * 建行龙支付回调日志
     *
     * @param params 通知参数
     * @return 处理结果
     */
    @Override
    public ResultResponse ccbCallBack(CcbCallbackLog params) {
        //回调信息入库
        try {
            params.setId(GUIDUtil.getGUID());
            params.setCreateDate(DateUtil.getNowDate());
            ccbCallbackLogDao.insertSelective(params);
        } catch (Exception e) {
            logger.error("[慈善总会]龙支付回调日志入库异常：{}", e);
        }

        String orderId = params.getORDERID();
        BigDecimal payAmount = params.getPAYMENT();
        try {
            if ("Y".equals(params.getSUCCESS())) {
                return pay(orderId, OrderConstant.PAY_TYPE_CCB, payAmount);
            }
            return new ResultResponse(ResultCode.FAIL, "[慈善总会]支付未完成，原因: 龙支付通知结果[交易失败]");
        } catch (Exception e) {
            logger.error("[慈善总会]支付结果处理异常[orderId={}]：{}", orderId, e);
            return new ResultResponse(ResultCode.FAIL, "[慈善总会]支付未完成，原因: 支付结果处理异常[逻辑处理异常]");
        }
    }

    /**
     * 根据订单ID，查询订单捐款人，捐献报表
     *
     * @param orderId 订单ID
     * @return
     */
    @Override
    public Map<String, Object> findDonateReport(String orderId) {
        return cuDonateOrderDao.findDonateReport(orderId);
    }

    private ResultResponse updateUserPoints(CuDonateOrder order) {
        CuUser user = cuUserService.findByOpenId(order.getOpenId());
        BigDecimal hisPoints = null == user.getHisPoints() ?
                BigDecimal.ZERO.add(order.getPoints()) : user.getHisPoints().add(order.getPoints());
        BigDecimal curPoints = null == user.getCurPoints() ?
                BigDecimal.ZERO.add(order.getPoints()) : user.getCurPoints().add(order.getPoints());
        BigDecimal usedPoints = null == user.getUsedPoints() ? BigDecimal.ZERO : user.getUsedPoints();
        user.setHisPoints(hisPoints);
        user.setCurPoints(curPoints);
        user.setUsedPoints(usedPoints);
        user.setLastModifyDate(DateUtil.getNowDate());
        user.setLastModifyUserId("0000-0000");
        user.setLastModifyUserName("system");

        int count = cuUserDao.updateByPrimaryKey(user);
        if (count < 1) {
            return new ResultResponse(ResultCode.FAIL, "用户积分变更失败");
        }

        return new ResultResponse(ResultCode.SUCCESS, "用户积分变更成功");
    }

    /**
     * 生成订单号
     *
     * @return
     */
    private String generateOrderNo() {
        Random r = new Random();
        String index = "" + r.nextInt(100000000);
        while (index.length() < 8) {
            index = "0" + index;
        }

        return System.currentTimeMillis() + index;
    }
}
