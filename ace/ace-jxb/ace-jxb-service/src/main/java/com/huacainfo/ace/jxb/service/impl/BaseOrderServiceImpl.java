package com.huacainfo.ace.jxb.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.jxb.dao.BaseOrderDao;
import com.huacainfo.ace.jxb.model.BaseOrder;
import com.huacainfo.ace.jxb.service.BaseOrderService;
import com.huacainfo.ace.jxb.vo.BaseOrderQVo;
import com.huacainfo.ace.jxb.vo.BaseOrderVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("baseOrderService")
/**
 * @author: Arvin
 * @version: 2018-07-25
 * @Description: TODO(统一订单)
 */
public class BaseOrderServiceImpl implements BaseOrderService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BaseOrderDao baseOrderDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(统一订单分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <BaseOrderVo>
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public PageResult<BaseOrderVo> findBaseOrderList(BaseOrderQVo condition, int start,
                                                     int limit, String orderBy) throws Exception {
        PageResult<BaseOrderVo> rst = new PageResult<>();
        List<BaseOrderVo> list = this.baseOrderDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.baseOrderDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertBaseOrder
     * @Description: TODO(添加统一订单)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public MessageResponse insertBaseOrder(BaseOrder o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getConsumerId())) {
            return new MessageResponse(1, "客户主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCommodityId())) {
            return new MessageResponse(1, "商品主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "订单类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getBusinessId())) {
            return new MessageResponse(1, "商家主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCommodityName())) {
            return new MessageResponse(1, "商品名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getBusinessName())) {
            return new MessageResponse(1, "商家名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getAmount())) {
            return new MessageResponse(1, "数量不能为空！");
        }
        if (CommonUtils.isBlank(o.getPrice())) {
            return new MessageResponse(1, "单价不能为空！");
        }
        if (CommonUtils.isBlank(o.getPayMoney())) {
            return new MessageResponse(1, "付款金额不能为空！");
        }
        if (CommonUtils.isBlank(o.getPayStatus())) {
            return new MessageResponse(1, "支付状态不能为空！");
        }


        int temp = this.baseOrderDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "统一订单名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        this.baseOrderDao.insertSelective(o);
        this.dataBaseLogService.log("添加统一订单", "统一订单", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加统一订单完成！");
    }

    /**
     * @throws
     * @Title:updateBaseOrder
     * @Description: TODO(更新统一订单)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public MessageResponse updateBaseOrder(BaseOrder o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getConsumerId())) {
            return new MessageResponse(1, "客户主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCommodityId())) {
            return new MessageResponse(1, "商品主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "订单类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getBusinessId())) {
            return new MessageResponse(1, "商家主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCommodityName())) {
            return new MessageResponse(1, "商品名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getBusinessName())) {
            return new MessageResponse(1, "商家名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getAmount())) {
            return new MessageResponse(1, "数量不能为空！");
        }
        if (CommonUtils.isBlank(o.getPrice())) {
            return new MessageResponse(1, "单价不能为空！");
        }
        if (CommonUtils.isBlank(o.getPayMoney())) {
            return new MessageResponse(1, "付款金额不能为空！");
        }
        if (CommonUtils.isBlank(o.getPayStatus())) {
            return new MessageResponse(1, "支付状态不能为空！");
        }


        this.baseOrderDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更统一订单", "统一订单", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更统一订单完成！");
    }

    /**
     * @throws
     * @Title:selectBaseOrderByPrimaryKey
     * @Description: TODO(获取统一订单)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<BaseOrder>
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public SingleResult<BaseOrderVo> selectBaseOrderByPrimaryKey(String id) throws Exception {
        SingleResult<BaseOrderVo> rst = new SingleResult<>();
        rst.setValue(this.baseOrderDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteBaseOrderByBaseOrderId
     * @Description: TODO(删除统一订单)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public MessageResponse deleteBaseOrderByBaseOrderId(String id, UserProp userProp) throws
            Exception {
        this.baseOrderDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除统一订单", "统一订单",
                String.valueOf(id),
                String.valueOf(id), "统一订单", userProp);
        return new MessageResponse(0, "统一订单删除完成！");
    }

}
