package com.huacainfo.ace.cu.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.cu.dao.CuDonateOrderDao;
import com.huacainfo.ace.cu.dao.CuProjectDao;
import com.huacainfo.ace.cu.dao.CuUserDao;
import com.huacainfo.ace.cu.model.CuDonateOrder;
import com.huacainfo.ace.cu.service.CuDonateOrderService;
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
import java.util.Random;

@Service("cuDonateOrderService")
/**
 * @author: Arvin
 * @version: 2018-06-14
 * @Description: TODO(捐款支付订单)
 */
public class CuDonateOrderServiceImpl implements CuDonateOrderService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CuDonateOrderDao cuDonateOrderDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private CuProjectDao cuProjectDao;
    @Autowired
    private CuUserDao cuUserDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(捐款支付订单分页查询)
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
     * @Description: TODO(添加捐款支付订单)
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
     * @Description: TODO(更新捐款支付订单)
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
     * @Description: TODO(获取捐款支付订单)
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
     * @Description: TODO(删除捐款支付订单)
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
        CuProjectVo projectVo = cuProjectDao.selectVoByPrimaryKey(data.getProjectId());
        if (null == projectVo) {
            return new ResultResponse(ResultCode.FAIL, "项目信息不存在！");
        }
        CuUserVo userVo = cuUserDao.findByOpenId(data.getOpenId());
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
                        data.getCity(), data.getDistrict(), data.getAddress())) {
            return new ResultResponse(ResultCode.FAIL, "收货信息不全！");
        }

        data.setOrderNo(generateOrderNo());
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
