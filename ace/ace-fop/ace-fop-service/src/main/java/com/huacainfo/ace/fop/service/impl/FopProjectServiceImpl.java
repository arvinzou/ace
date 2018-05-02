package com.huacainfo.ace.fop.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.fop.dao.FopProjectDao;
import com.huacainfo.ace.fop.model.FopProject;
import com.huacainfo.ace.fop.service.FopProjectService;
import com.huacainfo.ace.fop.vo.FopProjectQVo;
import com.huacainfo.ace.fop.vo.FopProjectVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("fopProjectService")
/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: TODO(合作项目)
 */
public class FopProjectServiceImpl implements FopProjectService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopProjectDao fopProjectDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(合作项目分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<FopProjectVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public PageResult<FopProjectVo> findFopProjectList(FopProjectQVo condition, int start,
                                                       int limit, String orderBy) throws Exception {
        PageResult<FopProjectVo> rst = new PageResult<FopProjectVo>();
        List<FopProjectVo> list = this.fopProjectDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.fopProjectDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertFopProject
     * @Description: TODO(添加合作项目)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse insertFopProject(FopProject o, UserProp userProp)
            throws Exception {
        o.setId(GUIDUtil.getGUID());
        //o.setId(String.valueOf(new Date().getTime()));
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getProjectName())) {
            return new MessageResponse(1, "项目名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getCoopType())) {
            return new MessageResponse(1, "合作方式不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }

        int temp = this.fopProjectDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "合作项目名称重复！");
        }
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.fopProjectDao.insertSelective(o);
        this.dataBaseLogService.log("添加合作项目", "合作项目", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "添加合作项目完成！");
    }

    /**
     * @throws
     * @Title:updateFopProject
     * @Description: TODO(更新合作项目)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse updateFopProject(FopProject o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getProjectName())) {
            return new MessageResponse(1, "项目名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getCoopType())) {
            return new MessageResponse(1, "合作方式不能为空！");
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
        this.fopProjectDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更合作项目", "合作项目", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "变更合作项目完成！");
    }

    /**
     * @throws
     * @Title:selectFopProjectByPrimaryKey
     * @Description: TODO(获取合作项目)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopProject>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public SingleResult<FopProjectVo> selectFopProjectByPrimaryKey(String id) throws Exception {
        SingleResult<FopProjectVo> rst = new SingleResult<FopProjectVo>();
        rst.setValue(this.fopProjectDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteFopProjectByFopProjectId
     * @Description: TODO(删除合作项目)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse deleteFopProjectByFopProjectId(String id,
                                                          UserProp userProp) throws Exception {
        this.fopProjectDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除合作项目", "合作项目", String.valueOf(id),
                String.valueOf(id), "合作项目", userProp);
        return new MessageResponse(0, "合作项目删除完成！");
    }
}
