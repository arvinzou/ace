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
import com.huacainfo.ace.fop.dao.SupportPoliciesDao;
import com.huacainfo.ace.fop.model.SupportPolicies;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.fop.service.SupportPoliciesService;
import com.huacainfo.ace.fop.vo.SupportPoliciesVo;
import com.huacainfo.ace.fop.vo.SupportPoliciesQVo;

@Service("supportPoliciesService")
/**
 * @author: huacai003
 * @version: 2018-05-17
 * @Description: TODO(扶持政策)
 */
public class SupportPoliciesServiceImpl implements SupportPoliciesService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SupportPoliciesDao supportPoliciesDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(扶持政策分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <SupportPoliciesVo>
     * @author: huacai003
     * @version: 2018-05-17
     */
    @Override
    public PageResult
            <SupportPoliciesVo> findSupportPoliciesList(SupportPoliciesQVo condition, int start,
                                                        int limit, String orderBy) throws Exception {
        PageResult
                <SupportPoliciesVo> rst = new PageResult
                <SupportPoliciesVo>();
        List
                <SupportPoliciesVo> list = this.supportPoliciesDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.supportPoliciesDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertSupportPolicies
     * @Description: TODO(添加扶持政策)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @Override
    public MessageResponse insertSupportPolicies(SupportPolicies o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
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


        int temp = this.supportPoliciesDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "扶持政策名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.supportPoliciesDao.insertSelective(o);
        this.dataBaseLogService.log("添加扶持政策", "扶持政策", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加扶持政策完成！");
    }

    /**
     * @throws
     * @Title:updateSupportPolicies
     * @Description: TODO(更新扶持政策)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @Override
    public MessageResponse updateSupportPolicies(SupportPolicies o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
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
        this.supportPoliciesDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更扶持政策", "扶持政策", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更扶持政策完成！");
    }

    /**
     * @throws
     * @Title:selectSupportPoliciesByPrimaryKey
     * @Description: TODO(获取扶持政策)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SupportPolicies>
     * @author: huacai003
     * @version: 2018-05-17
     */
    @Override
    public SingleResult
            <SupportPoliciesVo> selectSupportPoliciesByPrimaryKey(String id) throws Exception {
        SingleResult
                <SupportPoliciesVo> rst = new SingleResult
                <SupportPoliciesVo>();
        rst.setValue(this.supportPoliciesDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteSupportPoliciesBySupportPoliciesId
     * @Description: TODO(删除扶持政策)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @Override
    public MessageResponse deleteSupportPoliciesBySupportPoliciesId(String id, UserProp
            userProp) throws Exception {
        this.supportPoliciesDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除扶持政策", "扶持政策",
                String.valueOf(id),
                String.valueOf(id), "扶持政策", userProp);
        return new MessageResponse(0, "扶持政策删除完成！");
    }

}
