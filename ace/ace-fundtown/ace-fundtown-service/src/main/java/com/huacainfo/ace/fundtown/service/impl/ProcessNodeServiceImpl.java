package com.huacainfo.ace.fundtown.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.fundtown.dao.ProcessNodeDao;
import com.huacainfo.ace.fundtown.model.ProcessNode;
import com.huacainfo.ace.fundtown.service.ProcessNodeService;
import com.huacainfo.ace.fundtown.vo.ProcessNodeQVo;
import com.huacainfo.ace.fundtown.vo.ProcessNodeVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("processNodeService")
/**
 * @author: Arvin
 * @version: 2018-07-03
 * @Description: TODO(入驻流程节点)
 */
public class ProcessNodeServiceImpl implements ProcessNodeService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ProcessNodeDao processNodeDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(入驻流程节点分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<ProcessNodeVo>
     * @author: Arvin
     * @version: 2018-07-03
     */
    @Override
    public PageResult<ProcessNodeVo> findProcessNodeList(ProcessNodeQVo condition, int start,
                                                         int limit, String orderBy) throws Exception {
        PageResult<ProcessNodeVo> rst = new PageResult<>();
        List<ProcessNodeVo> list = this.processNodeDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.processNodeDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertProcessNode
     * @Description: TODO(添加入驻流程节点)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-03
     */
    @Override
    public MessageResponse insertProcessNode(ProcessNode o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getCurNodeName())) {
            return new MessageResponse(1, "节点名称不能为空！");
        }
        int temp = this.processNodeDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "入驻流程节点名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        o.setLastModifyDate(DateUtil.getNowDate());
        this.processNodeDao.insertSelective(o);
        this.dataBaseLogService.log("添加入驻流程节点", "入驻流程节点", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加入驻流程节点完成！");
    }

    /**
     * @throws
     * @Title:updateProcessNode
     * @Description: TODO(更新入驻流程节点)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-03
     */
    @Override
    public MessageResponse updateProcessNode(ProcessNode o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCurNodeName())) {
            return new MessageResponse(1, "节点名称不能为空！");
        }
        int temp = this.processNodeDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "入驻流程节点名称重复！");
        }


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.processNodeDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更入驻流程节点", "入驻流程节点", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更入驻流程节点完成！");
    }

    /**
     * @throws
     * @Title:selectProcessNodeByPrimaryKey
     * @Description: TODO(获取入驻流程节点)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<ProcessNode>
     * @author: Arvin
     * @version: 2018-07-03
     */
    @Override
    public SingleResult<ProcessNodeVo> selectProcessNodeByPrimaryKey(String id) throws Exception {
        SingleResult<ProcessNodeVo> rst = new SingleResult<>();
        rst.setValue(this.processNodeDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteProcessNodeByProcessNodeId
     * @Description: TODO(删除入驻流程节点)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-03
     */
    @Override
    public MessageResponse deleteProcessNodeByProcessNodeId(String id, UserProp userProp) throws
            Exception {
        this.processNodeDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除入驻流程节点", "入驻流程节点",
                String.valueOf(id),
                String.valueOf(id), "入驻流程节点", userProp);
        return new MessageResponse(0, "入驻流程节点删除完成！");
    }

}
