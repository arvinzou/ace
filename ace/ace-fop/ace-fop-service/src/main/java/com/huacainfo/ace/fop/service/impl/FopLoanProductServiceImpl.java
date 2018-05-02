package com.huacainfo.ace.fop.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.fop.dao.FopLoanProductDao;
import com.huacainfo.ace.fop.model.FopLoanProduct;
import com.huacainfo.ace.fop.service.FopLoanProductService;
import com.huacainfo.ace.fop.vo.FopLoanProductQVo;
import com.huacainfo.ace.fop.vo.FopLoanProductVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("fopLoanProductService")
/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: TODO(通知公告)
 */
public class FopLoanProductServiceImpl implements FopLoanProductService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopLoanProductDao fopLoanProductDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(通知公告分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<FopLoanProductVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public PageResult<FopLoanProductVo> findFopLoanProductList(FopLoanProductQVo condition, int start,
                                                               int limit, String orderBy) throws Exception {
        PageResult<FopLoanProductVo> rst = new PageResult<FopLoanProductVo>();
        List<FopLoanProductVo> list = this.fopLoanProductDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.fopLoanProductDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertFopLoanProduct
     * @Description: TODO(添加通知公告)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse insertFopLoanProduct(FopLoanProduct o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getCompanyId())) {
            return new MessageResponse(1, "所属公司不能为空！");
        }
        if (CommonUtils.isBlank(o.getProductName())) {
            return new MessageResponse(1, "产品名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getLoanAmount())) {
            return new MessageResponse(1, "贷款额度不能为空！");
        }
        if (CommonUtils.isBlank(o.getRateType())) {
            return new MessageResponse(1, "利率计算方式不能为空！");
        }
        if (CommonUtils.isBlank(o.getLoadRate())) {
            return new MessageResponse(1, "贷款利率不能为空！");
        }
        if (CommonUtils.isBlank(o.getLoadType())) {
            return new MessageResponse(1, "贷款类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getLoadYear())) {
            return new MessageResponse(1, "贷款年限不能为空！");
        }
        if (CommonUtils.isBlank(o.getRepaymentType())) {
            return new MessageResponse(1, "还款方式不能为空！");
        }
        if (CommonUtils.isBlank(o.getSuretyType())) {
            return new MessageResponse(1, "担保方式不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }

        int temp = this.fopLoanProductDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "通知公告名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.fopLoanProductDao.insertSelective(o);
        this.dataBaseLogService.log("添加通知公告", "通知公告", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "添加通知公告完成！");
    }

    /**
     * @throws
     * @Title:updateFopLoanProduct
     * @Description: TODO(更新通知公告)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse updateFopLoanProduct(FopLoanProduct o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCompanyId())) {
            return new MessageResponse(1, "所属公司不能为空！");
        }
        if (CommonUtils.isBlank(o.getProductName())) {
            return new MessageResponse(1, "产品名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getLoanAmount())) {
            return new MessageResponse(1, "贷款额度不能为空！");
        }
        if (CommonUtils.isBlank(o.getRateType())) {
            return new MessageResponse(1, "利率计算方式不能为空！");
        }
        if (CommonUtils.isBlank(o.getLoadRate())) {
            return new MessageResponse(1, "贷款利率不能为空！");
        }
        if (CommonUtils.isBlank(o.getLoadType())) {
            return new MessageResponse(1, "贷款类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getLoadYear())) {
            return new MessageResponse(1, "贷款年限不能为空！");
        }
        if (CommonUtils.isBlank(o.getRepaymentType())) {
            return new MessageResponse(1, "还款方式不能为空！");
        }
        if (CommonUtils.isBlank(o.getSuretyType())) {
            return new MessageResponse(1, "担保方式不能为空！");
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
        this.fopLoanProductDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更通知公告", "通知公告", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "变更通知公告完成！");
    }

    /**
     * @throws
     * @Title:selectFopLoanProductByPrimaryKey
     * @Description: TODO(获取通知公告)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopLoanProduct>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public SingleResult<FopLoanProductVo> selectFopLoanProductByPrimaryKey(String id) throws Exception {
        SingleResult<FopLoanProductVo> rst = new SingleResult<FopLoanProductVo>();
        rst.setValue(this.fopLoanProductDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteFopLoanProductByFopLoanProductId
     * @Description: TODO(删除通知公告)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse deleteFopLoanProductByFopLoanProductId(String id,
                                                                  UserProp userProp) throws Exception {
        this.fopLoanProductDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除通知公告", "通知公告", String.valueOf(id),
                String.valueOf(id), "通知公告", userProp);
        return new MessageResponse(0, "通知公告删除完成！");
    }
}
