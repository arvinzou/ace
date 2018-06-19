package com.huacainfo.ace.cu.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.cu.dao.CuDonateListDao;
import com.huacainfo.ace.cu.model.CuDonateList;
import com.huacainfo.ace.cu.model.CuDonateOrder;
import com.huacainfo.ace.cu.service.CuDonateListService;
import com.huacainfo.ace.cu.vo.CuDonateListQVo;
import com.huacainfo.ace.cu.vo.CuDonateListVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service("cuDonateListService")
/**
 * @author: Arvin
 * @version: 2018-06-14
 * @Description: TODO(慈善项目-捐献列表)
 */
public class CuDonateListServiceImpl implements CuDonateListService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CuDonateListDao cuDonateListDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(慈善项目-捐献列表分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CuDonateListVo>
     * @author: Arvin
     * @version: 2018-06-14
     */
    @Override
    public PageResult<CuDonateListVo> findCuDonateListList(CuDonateListQVo condition, int start,
                                                           int limit, String orderBy) throws Exception {
        PageResult<CuDonateListVo> rst = new PageResult<>();
        List<CuDonateListVo> list = this.cuDonateListDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.cuDonateListDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertCuDonateList
     * @Description: TODO(添加慈善项目-捐献列表)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    @Override
    public MessageResponse insertCuDonateList(CuDonateList o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getProjectId())) {
            return new MessageResponse(1, "所属项目ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "用户ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getOrderId())) {
            return new MessageResponse(1, "支付订单ID不能为空！");
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
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }


        int temp = this.cuDonateListDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "慈善项目-捐献列表名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.cuDonateListDao.insertSelective(o);
        this.dataBaseLogService.log("添加慈善项目-捐献列表", "慈善项目-捐献列表", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加慈善项目-捐献列表完成！");
    }

    /**
     * @throws
     * @Title:updateCuDonateList
     * @Description: TODO(更新慈善项目-捐献列表)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    @Override
    public MessageResponse updateCuDonateList(CuDonateList o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getProjectId())) {
            return new MessageResponse(1, "所属项目ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "用户ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getOrderId())) {
            return new MessageResponse(1, "支付订单ID不能为空！");
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
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.cuDonateListDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更慈善项目-捐献列表", "慈善项目-捐献列表", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更慈善项目-捐献列表完成！");
    }

    /**
     * @throws
     * @Title:selectCuDonateListByPrimaryKey
     * @Description: TODO(获取慈善项目-捐献列表)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CuDonateList>
     * @author: Arvin
     * @version: 2018-06-14
     */
    @Override
    public SingleResult<CuDonateListVo> selectCuDonateListByPrimaryKey(String id) throws Exception {
        SingleResult<CuDonateListVo> rst = new SingleResult<>();
        rst.setValue(this.cuDonateListDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteCuDonateListByCuDonateListId
     * @Description: TODO(删除慈善项目-捐献列表)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    @Override
    public MessageResponse deleteCuDonateListByCuDonateListId(String id, UserProp userProp) throws Exception {
        this.cuDonateListDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除慈善项目-捐献列表", "慈善项目-捐献列表",
                String.valueOf(id),
                String.valueOf(id), "慈善项目-捐献列表", userProp);
        return new MessageResponse(0, "慈善项目-捐献列表删除完成！");
    }

    /**
     * 增加捐献记录
     *
     * @param order
     * @return
     */
    @Override
    public ResultResponse addDonateList(CuDonateOrder order) {
        CuDonateList donateList = new CuDonateList();
        donateList.setId(GUIDUtil.getGUID());
        donateList.setProjectId(order.getProjectId());
        donateList.setUserId(order.getUserId());
        donateList.setOrderId(order.getId());
        donateList.setOpenId(order.getOpenId());
        donateList.setDonateAmount(order.getDonateAmount());
        donateList.setDonateDate(order.getDonateDate());

        donateList.setStatus("1");
        donateList.setCreateUserId("0000-0000");
        donateList.setCreateUserName("system");
        donateList.setCreateDate(DateUtil.getNowDate());
        donateList.setLastModifyDate(DateUtil.getNowDate());

        int count = cuDonateListDao.insertSelective(donateList);
        if (count == 1) {
            return new ResultResponse(ResultCode.SUCCESS, "新增成功", donateList);
        }

        return new ResultResponse(ResultCode.FAIL, "新增失败");
    }

    /**
     * 获取某人的累计捐款金额
     *
     * @param openId
     * @return
     */
    @Override
    public BigDecimal getAccDonateAmount(String openId) {
        return cuDonateListDao.getAccDonateAmount(openId);
    }

    /**
     * 获取某人的累计捐款次数
     *
     * @param openId
     * @return
     */
    @Override
    public int getAccDonateCount(String openId) {
        return cuDonateListDao.getAccDonateCount(openId);
    }



}
