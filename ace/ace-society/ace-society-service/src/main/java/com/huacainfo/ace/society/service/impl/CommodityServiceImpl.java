package com.huacainfo.ace.society.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.society.constant.BisType;
import com.huacainfo.ace.society.dao.CommodityDao;
import com.huacainfo.ace.society.model.Commodity;
import com.huacainfo.ace.society.service.AuditRecordService;
import com.huacainfo.ace.society.service.CommodityService;
import com.huacainfo.ace.society.vo.CommodityQVo;
import com.huacainfo.ace.society.vo.CommodityVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("commodityService")
/**
 * @author: Arvin
 * @version: 2018-09-13
 * @Description: TODO(爱心商品)
 */
public class CommodityServiceImpl implements CommodityService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CommodityDao commodityDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private AuditRecordService auditRecordService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(爱心商品分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CommodityVo>
     * @author: Arvin
     * @version: 2018-09-13
     */
    @Override
    public PageResult<CommodityVo> findCommodityList(CommodityQVo condition, int start,
                                                     int limit, String orderBy) throws Exception {
        PageResult<CommodityVo> rst = new PageResult<>();
        List<CommodityVo> list = this.commodityDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.commodityDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertCommodity
     * @Description: TODO(添加爱心商品)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-13
     */
    @Override
    public MessageResponse insertCommodity(Commodity o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getCommodityType())) {
            return new MessageResponse(1, "商品类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getCommodityName())) {
            return new MessageResponse(1, "商品名称不能为空！");
        }

        o.setId(StringUtil.isEmpty(o.getId()) ? GUIDUtil.getGUID() : o.getId());
        int temp = this.commodityDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "名称重复！");
        }

        o.setCostPoints(null == o.getCostPoints() ? 0 : o.getCostPoints());
        o.setState("0");//商品状态0-下架1-在售 默认下架
        o.setStatus("1");
        o.setCreateDate(new Date());
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.commodityDao.insert(o);
        this.dataBaseLogService.log("添加爱心商品", "爱心商品", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加爱心商品完成！");
    }

    /**
     * @throws
     * @Title:updateCommodity
     * @Description: TODO(更新爱心商品)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-13
     */
    @Override
    public MessageResponse updateCommodity(Commodity params, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(params.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        int temp = this.commodityDao.isExit(params);
        if (temp > 0) {
            return new MessageResponse(1, "名称重复！");
        }
        //db object info
        Commodity obj = commodityDao.selectByPrimaryKey(params.getId());
        //
        obj.setCommodityName(params.getCommodityName());
        obj.setCategory(params.getCategory());
        obj.setCoverUrl(params.getCoverUrl());
        obj.setSummary(params.getSummary());
        obj.setCostPoints(params.getCostPoints());
        obj.setRemark(params.getRemark());
        obj.setLastModifyDate(new Date());
        obj.setLastModifyUserName(userProp.getName());
        obj.setLastModifyUserId(userProp.getUserId());

        commodityDao.updateByPrimaryKey(obj);
        dataBaseLogService.log("变更爱心商品", "爱心商品", "", params.getId(), params.getId(), userProp);

        return new MessageResponse(0, "变更爱心商品完成！");
    }

    /**
     * @throws
     * @Title:selectCommodityByPrimaryKey
     * @Description: TODO(获取爱心商品)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Commodity>
     * @author: Arvin
     * @version: 2018-09-13
     */
    @Override
    public SingleResult<CommodityVo> selectCommodityByPrimaryKey(String id) throws Exception {
        SingleResult
                <CommodityVo> rst = new SingleResult<>();
        rst.setValue(this.commodityDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteCommodityByCommodityId
     * @Description: TODO(删除爱心商品)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-13
     */
    @Override
    public MessageResponse deleteCommodityByCommodityId(String id, UserProp userProp) throws Exception {
        //逻辑删除
        Commodity o = commodityDao.selectByPrimaryKey(id);
        if (o == null) {
            return new MessageResponse(0, "数据记录丢失");
        }

        o.setStatus("0");
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        commodityDao.updateByPrimaryKey(o);

        dataBaseLogService.log("删除爱心商品", "爱心商品", id, id, "爱心商品", userProp);
        return new MessageResponse(0, "爱心商品删除完成！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核爱心商品)
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

        Commodity obj = commodityDao.selectByPrimaryKey(id);
        if (obj == null) {
            return new MessageResponse(ResultCode.FAIL, "爱心商品数据丢失");
        }

        //更改审核记录
        MessageResponse auditRs =
                auditRecordService.audit(BisType.PERSON_INFO, obj.getId(), obj.getId(), rst, remark, userProp);
        if (ResultCode.FAIL == auditRs.getStatus()) {
            return auditRs;
        }

        obj.setStatus(rst);
        obj.setLastModifyDate(DateUtil.getNowDate());
        obj.setLastModifyUserId(userProp.getUserId());
        obj.setLastModifyUserName(userProp.getName());
        commodityDao.updateByPrimaryKey(obj);


        dataBaseLogService.log("审核爱心商品", "爱心商品",
                String.valueOf(id), String.valueOf(id), "爱心商品", userProp);
        return new MessageResponse(0, "爱心商品审核完成！");
    }

    /**
     * 商品上/下架处理
     *
     * @param id          主键ID
     * @param state       商品状态0-下架1-在售
     * @param curUserProp
     * @return MessageResponse
     * @throws Exception
     */
    @Override
    public MessageResponse updateState(String id, String state, UserProp curUserProp) throws Exception {
        Commodity obj = commodityDao.selectByPrimaryKey(id);
        if (obj == null) {
            return new MessageResponse(ResultCode.FAIL, "数据丢失");
        }
        obj.setState(state);

        return updateCommodity(obj, curUserProp);
    }

}
