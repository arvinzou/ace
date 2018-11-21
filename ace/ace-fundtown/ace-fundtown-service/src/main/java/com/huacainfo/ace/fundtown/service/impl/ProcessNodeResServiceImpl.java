package com.huacainfo.ace.fundtown.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.fundtown.dao.ProcessNodeResDao;
import com.huacainfo.ace.fundtown.model.ProcessNodeRes;
import com.huacainfo.ace.fundtown.service.ProcessNodeResService;
import com.huacainfo.ace.fundtown.vo.ProcessNodeResQVo;
import com.huacainfo.ace.fundtown.vo.ProcessNodeResVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("processNodeResService")
/**
 * @author: Arvin
 * @version: 2018-07-03
 * @Description: TODO(入驻流程节点-资源/附件)
 */
public class ProcessNodeResServiceImpl implements ProcessNodeResService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ProcessNodeResDao processNodeResDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(入驻流程节点-资源/附件分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <ProcessNodeResVo>
     * @author: Arvin
     * @version: 2018-07-03
     */
    @Override
    public PageResult<ProcessNodeResVo> findProcessNodeResList(ProcessNodeResQVo condition, int start,
                                                               int limit, String orderBy) throws Exception {
        PageResult<ProcessNodeResVo> rst = new PageResult<>();
        List<ProcessNodeResVo> list = this.processNodeResDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.processNodeResDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertProcessNodeRes
     * @Description: TODO(添加入驻流程节点-资源/附件)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-03
     */
    @Override
    public MessageResponse insertProcessNodeRes(ProcessNodeRes o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getNodeId())) {
            return new MessageResponse(1, "节点编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "资源分类不能为空！");
        }
        if (CommonUtils.isBlank(o.getResName())) {
            return new MessageResponse(1, "资源名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getResUrl())) {
            return new MessageResponse(1, "资源地址不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }


        int temp = this.processNodeResDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "入驻流程节点-资源/附件名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.processNodeResDao.insertSelective(o);
        this.dataBaseLogService.log("添加入驻流程节点-资源/附件", "入驻流程节点-资源/附件", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加入驻流程节点-资源/附件完成！");
    }

    /**
     * @throws
     * @Title:updateProcessNodeRes
     * @Description: TODO(更新入驻流程节点-资源/附件)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-03
     */
    @Override
    public MessageResponse updateProcessNodeRes(ProcessNodeRes o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getNodeId())) {
            return new MessageResponse(1, "节点编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "资源分类不能为空！");
        }
        if (CommonUtils.isBlank(o.getResName())) {
            return new MessageResponse(1, "资源名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getResUrl())) {
            return new MessageResponse(1, "资源地址不能为空！");
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
        this.processNodeResDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更入驻流程节点-资源/附件", "入驻流程节点-资源/附件", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更入驻流程节点-资源/附件完成！");
    }

    /**
     * @throws
     * @Title:selectProcessNodeResByPrimaryKey
     * @Description: TODO(获取入驻流程节点-资源/附件)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<ProcessNodeRes>
     * @author: Arvin
     * @version: 2018-07-03
     */
    @Override
    public SingleResult<ProcessNodeResVo> selectProcessNodeResByPrimaryKey(String id) throws Exception {
        SingleResult<ProcessNodeResVo> rst = new SingleResult<>();
        rst.setValue(this.processNodeResDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteProcessNodeResByProcessNodeResId
     * @Description: TODO(删除入驻流程节点-资源/附件)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-03
     */
    @Override
    public MessageResponse deleteProcessNodeResByProcessNodeResId(String id, UserProp userProp) throws
            Exception {
        this.processNodeResDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除入驻流程节点-资源/附件", "入驻流程节点-资源/附件",
                String.valueOf(id),
                String.valueOf(id), "入驻流程节点-资源/附件", userProp);
        return new MessageResponse(0, "入驻流程节点-资源/附件删除完成！");
    }

}
