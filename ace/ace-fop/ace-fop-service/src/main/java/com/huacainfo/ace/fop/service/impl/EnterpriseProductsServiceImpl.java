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
import com.huacainfo.ace.fop.dao.EnterpriseProductsDao;
import com.huacainfo.ace.fop.model.EnterpriseProducts;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.fop.service.EnterpriseProductsService;
import com.huacainfo.ace.fop.vo.EnterpriseProductsVo;
import com.huacainfo.ace.fop.vo.EnterpriseProductsQVo;

@Service("enterpriseProductsService")
/**
 * @author: huacai003
 * @version: 2018-05-17
 * @Description: TODO(企业产品)
 */
public class EnterpriseProductsServiceImpl implements EnterpriseProductsService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EnterpriseProductsDao enterpriseProductsDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(企业产品分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <EnterpriseProductsVo>
     * @author: huacai003
     * @version: 2018-05-17
     */
    @Override
    public PageResult
            <EnterpriseProductsVo> findEnterpriseProductsList(EnterpriseProductsQVo condition, int start,
                                                              int limit, String orderBy) throws Exception {
        PageResult
                <EnterpriseProductsVo> rst = new PageResult
                <EnterpriseProductsVo>();
        List
                <EnterpriseProductsVo> list = this.enterpriseProductsDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.enterpriseProductsDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertEnterpriseProducts
     * @Description: TODO(添加企业产品)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @Override
    public MessageResponse insertEnterpriseProducts(EnterpriseProducts o, UserProp userProp) throws Exception {

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


        int temp = this.enterpriseProductsDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "企业产品名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.enterpriseProductsDao.insertSelective(o);
        this.dataBaseLogService.log("添加企业产品", "企业产品", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加企业产品完成！");
    }

    /**
     * @throws
     * @Title:updateEnterpriseProducts
     * @Description: TODO(更新企业产品)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @Override
    public MessageResponse updateEnterpriseProducts(EnterpriseProducts o, UserProp userProp) throws Exception {
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
        this.enterpriseProductsDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更企业产品", "企业产品", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更企业产品完成！");
    }

    /**
     * @throws
     * @Title:selectEnterpriseProductsByPrimaryKey
     * @Description: TODO(获取企业产品)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<EnterpriseProducts>
     * @author: huacai003
     * @version: 2018-05-17
     */
    @Override
    public SingleResult
            <EnterpriseProductsVo> selectEnterpriseProductsByPrimaryKey(String id) throws Exception {
        SingleResult
                <EnterpriseProductsVo> rst = new SingleResult
                <EnterpriseProductsVo>();
        rst.setValue(this.enterpriseProductsDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteEnterpriseProductsByEnterpriseProductsId
     * @Description: TODO(删除企业产品)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @Override
    public MessageResponse deleteEnterpriseProductsByEnterpriseProductsId(String id, UserProp
            userProp) throws Exception {
        this.enterpriseProductsDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除企业产品", "企业产品",
                String.valueOf(id),
                String.valueOf(id), "企业产品", userProp);
        return new MessageResponse(0, "企业产品删除完成！");
    }

}
