package com.huacainfo.ace.fop.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.fop.dao.FopCompanyActivityDao;
import com.huacainfo.ace.fop.model.FopCompanyActivity;
import com.huacainfo.ace.fop.service.FopCompanyActivityService;
import com.huacainfo.ace.fop.vo.FopCompanyActivityQVo;
import com.huacainfo.ace.fop.vo.FopCompanyActivityVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("fopCompanyActivityService")
/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: TODO(企业管理)
 */
public class FopCompanyActivityServiceImpl implements FopCompanyActivityService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopCompanyActivityDao fopCompanyActivityDao;
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
     * @return: PageResult<FopCompanyActivityVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public PageResult<FopCompanyActivityVo> findFopCompanyActivityList(FopCompanyActivityQVo condition, int start,
                                                                       int limit, String orderBy) throws Exception {
        PageResult<FopCompanyActivityVo> rst = new PageResult<FopCompanyActivityVo>();
        List<FopCompanyActivityVo> list = this.fopCompanyActivityDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.fopCompanyActivityDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertFopCompanyActivity
     * @Description: TODO(添加企业管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse insertFopCompanyActivity(FopCompanyActivity o, UserProp userProp)
            throws Exception {
        o.setId(GUIDUtil.getGUID());
        //o.setId(String.valueOf(new Date().getTime()));
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCompanyId())) {
            return new MessageResponse(1, "企业ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getActivityType())) {
            return new MessageResponse(1, "活动类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "活动标题不能为空！");
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


        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.fopCompanyActivityDao.insertSelective(o);
        this.dataBaseLogService.log("添加企业管理", "企业管理", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "添加企业管理完成！");
    }

    /**
     * @throws
     * @Title:updateFopCompanyActivity
     * @Description: TODO(更新企业管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse updateFopCompanyActivity(FopCompanyActivity o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCompanyId())) {
            return new MessageResponse(1, "企业ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getActivityType())) {
            return new MessageResponse(1, "活动类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "活动标题不能为空！");
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
        this.fopCompanyActivityDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更企业管理", "企业管理", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "变更企业管理完成！");
    }

    /**
     * @throws
     * @Title:selectFopCompanyActivityByPrimaryKey
     * @Description: TODO(获取企业管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopCompanyActivity>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public SingleResult<FopCompanyActivityVo> selectFopCompanyActivityByPrimaryKey(String id) throws Exception {
        SingleResult<FopCompanyActivityVo> rst = new SingleResult<FopCompanyActivityVo>();
        rst.setValue(this.fopCompanyActivityDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteFopCompanyActivityByFopCompanyActivityId
     * @Description: TODO(删除企业管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse deleteFopCompanyActivityByFopCompanyActivityId(String id,
                                                                          UserProp userProp) throws Exception {
        this.fopCompanyActivityDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除企业管理", "企业管理", String.valueOf(id),
                String.valueOf(id), "企业管理", userProp);
        return new MessageResponse(0, "企业管理删除完成！");
    }
}
