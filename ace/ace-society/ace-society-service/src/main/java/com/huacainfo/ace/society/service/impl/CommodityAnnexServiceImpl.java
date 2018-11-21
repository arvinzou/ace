package com.huacainfo.ace.society.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.society.constant.BisType;
import com.huacainfo.ace.society.dao.CommodityAnnexDao;
import com.huacainfo.ace.society.model.CommodityAnnex;
import com.huacainfo.ace.society.service.AuditRecordService;
import com.huacainfo.ace.society.service.CommodityAnnexService;
import com.huacainfo.ace.society.vo.CommodityAnnexQVo;
import com.huacainfo.ace.society.vo.CommodityAnnexVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("commodityAnnexService")
/**
 * @author: Arvin
 * @version: 2018-09-13
 * @Description: TODO(爱心商品附录)
 */
public class CommodityAnnexServiceImpl implements CommodityAnnexService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CommodityAnnexDao commodityAnnexDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private AuditRecordService auditRecordService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(爱心商品附录分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CommodityAnnexVo>
     * @author: Arvin
     * @version: 2018-09-13
     */
    @Override
    public PageResult<CommodityAnnexVo> findCommodityAnnexList(CommodityAnnexQVo condition, int start,
                                                               int limit, String orderBy) throws Exception {
        PageResult<CommodityAnnexVo> rst = new PageResult<>();
        List<CommodityAnnexVo> list = this.commodityAnnexDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.commodityAnnexDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertCommodityAnnex
     * @Description: TODO(添加爱心商品附录)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-13
     */
    @Override
    public MessageResponse insertCommodityAnnex(CommodityAnnex o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCommodityId())) {
            return new MessageResponse(1, "商品编号不能为空！");
        }


        int temp = this.commodityAnnexDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "爱心商品附录名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.commodityAnnexDao.insertSelective(o);
        this.dataBaseLogService.log("添加爱心商品附录", "爱心商品附录", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加爱心商品附录完成！");
    }

    /**
     * @throws
     * @Title:updateCommodityAnnex
     * @Description: TODO(更新爱心商品附录)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-13
     */
    @Override
    public MessageResponse updateCommodityAnnex(CommodityAnnex o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCommodityId())) {
            return new MessageResponse(1, "商品编号不能为空！");
        }


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.commodityAnnexDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更爱心商品附录", "爱心商品附录", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更爱心商品附录完成！");
    }

    /**
     * @throws
     * @Title:selectCommodityAnnexByPrimaryKey
     * @Description: TODO(获取爱心商品附录)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CommodityAnnex>
     * @author: Arvin
     * @version: 2018-09-13
     */
    @Override
    public SingleResult<CommodityAnnexVo> selectCommodityAnnexByPrimaryKey(String id) throws Exception {
        SingleResult<CommodityAnnexVo> rst = new SingleResult<>();
        rst.setValue(this.commodityAnnexDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteCommodityAnnexByCommodityAnnexId
     * @Description: TODO(删除爱心商品附录)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-13
     */
    @Override
    public MessageResponse deleteCommodityAnnexByCommodityAnnexId(String id, UserProp userProp) throws
            Exception {
        this.commodityAnnexDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除爱心商品附录", "爱心商品附录",
                String.valueOf(id),
                String.valueOf(id), "爱心商品附录", userProp);
        return new MessageResponse(0, "爱心商品附录删除完成！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核爱心商品附录)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-13
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {

        CommodityAnnex obj = commodityAnnexDao.selectByPrimaryKey(id);
        if (obj == null) {
            return new MessageResponse(ResultCode.FAIL, "爱心商品附录数据丢失");
        }

        //更改审核记录
        MessageResponse auditRs =
                auditRecordService.audit(BisType.SOCIETY_ORG_INFO, obj.getId(), obj.getId(), rst, remark,
                        userProp);
        if (ResultCode.FAIL == auditRs.getStatus()) {
            return auditRs;
        }

        obj.setStatus(rst);
        obj.setLastModifyDate(DateUtil.getNowDate());
        obj.setLastModifyUserId(userProp.getUserId());
        obj.setLastModifyUserName(userProp.getName());
        commodityAnnexDao.updateByPrimaryKeySelective(obj);


        dataBaseLogService.log("审核爱心商品附录", "爱心商品附录",
                String.valueOf(id), String.valueOf(id), "爱心商品附录", userProp);
        return new MessageResponse(0, "爱心商品附录审核完成！");
    }

}
