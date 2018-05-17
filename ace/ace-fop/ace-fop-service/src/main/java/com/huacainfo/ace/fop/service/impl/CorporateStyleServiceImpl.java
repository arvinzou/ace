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
import com.huacainfo.ace.fop.dao.CorporateStyleDao;
import com.huacainfo.ace.fop.model.CorporateStyle;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.fop.service.CorporateStyleService;
import com.huacainfo.ace.fop.vo.CorporateStyleVo;
import com.huacainfo.ace.fop.vo.CorporateStyleQVo;

@Service("corporateStyleService")
/**
 * @author: huacai003
 * @version: 2018-05-17
 * @Description: TODO(企业风采)
 */
public class CorporateStyleServiceImpl implements CorporateStyleService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CorporateStyleDao corporateStyleDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(企业风采分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CorporateStyleVo>
     * @author: huacai003
     * @version: 2018-05-17
     */
    @Override
    public PageResult
            <CorporateStyleVo> findCorporateStyleList(CorporateStyleQVo condition, int start,
                                                      int limit, String orderBy) throws Exception {
        PageResult
                <CorporateStyleVo> rst = new PageResult
                <CorporateStyleVo>();
        List
                <CorporateStyleVo> list = this.corporateStyleDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.corporateStyleDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertCorporateStyle
     * @Description: TODO(添加企业风采)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @Override
    public MessageResponse insertCorporateStyle(CorporateStyle o, UserProp userProp) throws Exception {

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


        int temp = this.corporateStyleDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "企业风采名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.corporateStyleDao.insertSelective(o);
        this.dataBaseLogService.log("添加企业风采", "企业风采", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加企业风采完成！");
    }

    /**
     * @throws
     * @Title:updateCorporateStyle
     * @Description: TODO(更新企业风采)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @Override
    public MessageResponse updateCorporateStyle(CorporateStyle o, UserProp userProp) throws Exception {
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
        this.corporateStyleDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更企业风采", "企业风采", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更企业风采完成！");
    }

    /**
     * @throws
     * @Title:selectCorporateStyleByPrimaryKey
     * @Description: TODO(获取企业风采)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CorporateStyle>
     * @author: huacai003
     * @version: 2018-05-17
     */
    @Override
    public SingleResult
            <CorporateStyleVo> selectCorporateStyleByPrimaryKey(String id) throws Exception {
        SingleResult
                <CorporateStyleVo> rst = new SingleResult
                <CorporateStyleVo>();
        rst.setValue(this.corporateStyleDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteCorporateStyleByCorporateStyleId
     * @Description: TODO(删除企业风采)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @Override
    public MessageResponse deleteCorporateStyleByCorporateStyleId(String id, UserProp
            userProp) throws Exception {
        this.corporateStyleDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除企业风采", "企业风采",
                String.valueOf(id),
                String.valueOf(id), "企业风采", userProp);
        return new MessageResponse(0, "企业风采删除完成！");
    }

}
