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
import com.huacainfo.ace.fop.dao.IntegrityPublicityDao;
import com.huacainfo.ace.fop.model.IntegrityPublicity;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.fop.service.IntegrityPublicityService;
import com.huacainfo.ace.fop.vo.IntegrityPublicityVo;
import com.huacainfo.ace.fop.vo.IntegrityPublicityQVo;

@Service("integrityPublicityService")
/**
 * @author: huacai003
 * @version: 2018-05-28
 * @Description: TODO(诚信公示)
 */
public class IntegrityPublicityServiceImpl implements IntegrityPublicityService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IntegrityPublicityDao integrityPublicityDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(诚信公示分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <IntegrityPublicityVo>
     * @author: huacai003
     * @version: 2018-05-28
     */
    @Override
    public PageResult
            <IntegrityPublicityVo> findIntegrityPublicityList(IntegrityPublicityQVo condition, int start,
                                                              int limit, String orderBy) throws Exception {
        PageResult
                <IntegrityPublicityVo> rst = new PageResult
                <IntegrityPublicityVo>();
        List
                <IntegrityPublicityVo> list = this.integrityPublicityDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.integrityPublicityDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertIntegrityPublicity
     * @Description: TODO(添加诚信公示)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-28
     */
    @Override
    public MessageResponse insertIntegrityPublicity(IntegrityPublicity o, UserProp userProp) throws Exception {


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
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "类别不能为空！");
        }
        int temp = this.integrityPublicityDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "诚信公示名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setReleaseDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.integrityPublicityDao.insertSelective(o);
        this.dataBaseLogService.log("添加诚信公示", "诚信公示", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加诚信公示完成！");
    }

    /**
     * @throws
     * @Title:updateIntegrityPublicity
     * @Description: TODO(更新诚信公示)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-28
     */
    @Override
    public MessageResponse updateIntegrityPublicity(IntegrityPublicity o, UserProp userProp) throws Exception {
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
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "类别不能为空！");
        }
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.integrityPublicityDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更诚信公示", "诚信公示", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更诚信公示完成！");
    }

    /**
     * @throws
     * @Title:selectIntegrityPublicityByPrimaryKey
     * @Description: TODO(获取诚信公示)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<IntegrityPublicity>
     * @author: huacai003
     * @version: 2018-05-28
     */
    @Override
    public SingleResult
            <IntegrityPublicityVo> selectIntegrityPublicityByPrimaryKey(String id) throws Exception {
        SingleResult
                <IntegrityPublicityVo> rst = new SingleResult
                <IntegrityPublicityVo>();
        rst.setValue(this.integrityPublicityDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteIntegrityPublicityByIntegrityPublicityId
     * @Description: TODO(删除诚信公示)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-28
     */
    @Override
    public MessageResponse deleteIntegrityPublicityByIntegrityPublicityId(String id, UserProp
            userProp) throws Exception {
        this.integrityPublicityDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除诚信公示", "诚信公示",
                String.valueOf(id),
                String.valueOf(id), "诚信公示", userProp);
        return new MessageResponse(0, "诚信公示删除完成！");
    }

}
