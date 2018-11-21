package com.huacainfo.ace.fop.service.impl;


import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.fop.vo.FopQuestionVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.fop.dao.RelatedLinksDao;
import com.huacainfo.ace.fop.model.RelatedLinks;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.fop.service.RelatedLinksService;
import com.huacainfo.ace.fop.vo.RelatedLinksVo;
import com.huacainfo.ace.fop.vo.RelatedLinksQVo;

@Service("relatedLinksService")
/**
 * @author: huacai003
 * @version: 2018-05-23
 * @Description: TODO(相关链接)
 */
public class RelatedLinksServiceImpl implements RelatedLinksService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RelatedLinksDao relatedLinksDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(相关链接分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <RelatedLinksVo>
     * @author: huacai003
     * @version: 2018-05-23
     */
    @Override
    public PageResult<RelatedLinksVo> findRelatedLinksList(RelatedLinksQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<RelatedLinksVo> rst = new PageResult<RelatedLinksVo>();
        List<RelatedLinksVo> list = this.relatedLinksDao.findList(condition, start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.relatedLinksDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertRelatedLinks
     * @Description: TODO(添加相关链接)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-23
     */
    @Override
    public MessageResponse insertRelatedLinks(RelatedLinks o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getParentId())) {
            return new MessageResponse(1, "父节点ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getUrl())) {
            return new MessageResponse(1, "链接URL不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }


        int temp = this.relatedLinksDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "相关链接名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.relatedLinksDao.insertSelective(o);
        this.dataBaseLogService.log("添加相关链接", "相关链接", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加相关链接完成！");
    }

    /**
     * @throws
     * @Title:updateRelatedLinks
     * @Description: TODO(更新相关链接)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-23
     */
    @Override
    public MessageResponse updateRelatedLinks(RelatedLinks o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getParentId())) {
            return new MessageResponse(1, "父节点ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getUrl())) {
            return new MessageResponse(1, "链接URL不能为空！");
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
        this.relatedLinksDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更相关链接", "相关链接", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更相关链接完成！");
    }

    /**
     * @throws
     * @Title:selectRelatedLinksByPrimaryKey
     * @Description: TODO(获取相关链接)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<RelatedLinks>
     * @author: huacai003
     * @version: 2018-05-23
     */
    @Override
    public SingleResult
            <RelatedLinksVo> selectRelatedLinksByPrimaryKey(String id) throws Exception {
        SingleResult
                <RelatedLinksVo> rst = new SingleResult
                <RelatedLinksVo>();
        rst.setValue(this.relatedLinksDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRelatedLinksByRelatedLinksId
     * @Description: TODO(删除相关链接)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-23
     */
    @Override
    public MessageResponse deleteRelatedLinksByRelatedLinksId(String id, UserProp
            userProp) throws Exception {
        this.relatedLinksDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除相关链接", "相关链接",
                String.valueOf(id),
                String.valueOf(id), "相关链接", userProp);
        return new MessageResponse(0, "相关链接删除完成！");
    }

    @Override
    public List<RelatedLinksVo> relatedLinksTree(String parentId) throws Exception {
        List<RelatedLinksVo> list = this.relatedLinksDao.findTreeList(parentId);
        if (list.size() == 0) {
            return null;
        }
        for (RelatedLinksVo itemp : list) {
            String parent = itemp.getId();
            List<RelatedLinksVo> list1 = relatedLinksTree(parent);
            itemp.setChildren(list1);
        }
        return list;
    }
}
