package com.huacainfo.ace.fop.service.impl;


import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.tools.GUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.fop.dao.InvestmentInfoDao;
import com.huacainfo.ace.fop.model.InvestmentInfo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.fop.service.InvestmentInfoService;
import com.huacainfo.ace.fop.vo.InvestmentInfoVo;
import com.huacainfo.ace.fop.vo.InvestmentInfoQVo;

@Service("investmentInfoService")
/**
 * @author: huacai003
 * @version: 2018-05-17
 * @Description: TODO(招商信息)
 */
public class InvestmentInfoServiceImpl implements InvestmentInfoService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private InvestmentInfoDao investmentInfoDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(招商信息分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <InvestmentInfoVo>
     * @author: huacai003
     * @version: 2018-05-17
     */
    @Override
    public PageResult
            <InvestmentInfoVo> findInvestmentInfoList(InvestmentInfoQVo condition, int start,
                                                      int limit, String orderBy) throws Exception {
        PageResult
                <InvestmentInfoVo> rst = new PageResult
                <InvestmentInfoVo>();
        List
                <InvestmentInfoVo> list = this.investmentInfoDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.investmentInfoDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertInvestmentInfo
     * @Description: TODO(添加招商信息)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @Override
    public MessageResponse insertInvestmentInfo(InvestmentInfo o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getRelationId())) {
            return new MessageResponse(1, "关联ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getRelationType())) {
            return new MessageResponse(1, "关联类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getContent())) {
            return new MessageResponse(1, "内容不能为空！");
        }
        if (CommonUtils.isBlank(o.getReleaseDate())) {
            return new MessageResponse(1, "发布时间不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }


        int temp = this.investmentInfoDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "招商信息名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.investmentInfoDao.insertSelective(o);
        this.dataBaseLogService.log("添加招商信息", "招商信息", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加招商信息完成！");
    }

    /**
     * @throws
     * @Title:updateInvestmentInfo
     * @Description: TODO(更新招商信息)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @Override
    public MessageResponse updateInvestmentInfo(InvestmentInfo o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getRelationId())) {
            return new MessageResponse(1, "关联ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getRelationType())) {
            return new MessageResponse(1, "关联类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getContent())) {
            return new MessageResponse(1, "内容不能为空！");
        }
        if (CommonUtils.isBlank(o.getReleaseDate())) {
            return new MessageResponse(1, "发布时间不能为空！");
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
        this.investmentInfoDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更招商信息", "招商信息", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更招商信息完成！");
    }

    /**
     * @throws
     * @Title:selectInvestmentInfoByPrimaryKey
     * @Description: TODO(获取招商信息)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<InvestmentInfo>
     * @author: huacai003
     * @version: 2018-05-17
     */
    @Override
    public SingleResult
            <InvestmentInfoVo> selectInvestmentInfoByPrimaryKey(String id) throws Exception {
        SingleResult
                <InvestmentInfoVo> rst = new SingleResult
                <InvestmentInfoVo>();
        rst.setValue(this.investmentInfoDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteInvestmentInfoByInvestmentInfoId
     * @Description: TODO(删除招商信息)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @Override
    public MessageResponse deleteInvestmentInfoByInvestmentInfoId(String id, UserProp
            userProp) throws Exception {
        this.investmentInfoDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除招商信息", "招商信息",
                String.valueOf(id),
                String.valueOf(id), "招商信息", userProp);
        return new MessageResponse(0, "招商信息删除完成！");
    }

}
