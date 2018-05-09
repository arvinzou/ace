package com.huacainfo.ace.fop.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.fop.dao.FopResourceDao;
import com.huacainfo.ace.fop.model.FopResource;
import com.huacainfo.ace.fop.service.FopResourceService;
import com.huacainfo.ace.fop.vo.FopResourceQVo;
import com.huacainfo.ace.fop.vo.FopResourceVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("fopResourceService")
/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: TODO(企业管理)
 */
public class FopResourceServiceImpl implements FopResourceService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopResourceDao fopResourceDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(企业管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<FopResourceVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public PageResult<FopResourceVo> findFopResourceList(FopResourceQVo condition, int start,
                                                         int limit, String orderBy) throws Exception {
        PageResult<FopResourceVo> rst = new PageResult<FopResourceVo>();
        List<FopResourceVo> list = this.fopResourceDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.fopResourceDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertFopResource
     * @Description: TODO(添加企业管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse insertFopResource(FopResource o, UserProp userProp)
            throws Exception {

        if (CommonUtils.isBlank(o.getRelationId())) {
            return new MessageResponse(1, "关联ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getRelationType())) {
            return new MessageResponse(1, "关联类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getResCategory())) {
            return new MessageResponse(1, "资源分类不能为空！");
        }
        if (CommonUtils.isBlank(o.getResType())) {
            return new MessageResponse(1, "资源类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getSequence())) {
            return new MessageResponse(1, "显示排序不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }

        int temp = this.fopResourceDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "企业管理名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.fopResourceDao.insertSelective(o);
        this.dataBaseLogService.log("添加企业管理", "企业管理", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "添加企业管理完成！");
    }

    /**
     * @throws
     * @Title:updateFopResource
     * @Description: TODO(更新企业管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse updateFopResource(FopResource o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getRelationId())) {
            return new MessageResponse(1, "关联ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getRelationType())) {
            return new MessageResponse(1, "关联类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getResCategory())) {
            return new MessageResponse(1, "资源分类不能为空！");
        }
        if (CommonUtils.isBlank(o.getResType())) {
            return new MessageResponse(1, "资源类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getSequence())) {
            return new MessageResponse(1, "显示排序不能为空！");
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
        this.fopResourceDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更企业管理", "企业管理", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "变更企业管理完成！");
    }

    /**
     * @throws
     * @Title:selectFopResourceByPrimaryKey
     * @Description: TODO(获取企业管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopResource>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public SingleResult<FopResourceVo> selectFopResourceByPrimaryKey(String id) throws Exception {
        SingleResult<FopResourceVo> rst = new SingleResult<FopResourceVo>();
        rst.setValue(this.fopResourceDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteFopResourceByFopResourceId
     * @Description: TODO(删除企业管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse deleteFopResourceByFopResourceId(String id,
                                                            UserProp userProp) throws Exception {
        this.fopResourceDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除企业管理", "企业管理", String.valueOf(id),
                String.valueOf(id), "企业管理", userProp);
        return new MessageResponse(0, "企业管理删除完成！");
    }
}
