package com.huacainfo.ace.glink.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.glink.api.SeApiToolKit;
import com.huacainfo.ace.glink.dao.SeNodeDao;
import com.huacainfo.ace.glink.model.SeNode;
import com.huacainfo.ace.glink.service.SeNodeService;
import com.huacainfo.ace.glink.vo.SeNodeQVo;
import com.huacainfo.ace.glink.vo.SeNodeVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("seNodeService")
/**
 * @author: Arvin
 * @version: 2019-04-18
 * @Description: TODO(配电箱数据)
 */
public class SeNodeServiceImpl implements SeNodeService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeNodeDao seNodeDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(配电箱数据分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<SeNodeVo>
     * @author: Arvin
     * @version: 2019-04-18
     */
    @Override
    public PageResult<SeNodeVo> findSeNodeList(SeNodeQVo condition,
                                               int start, int limit, String orderBy) throws Exception {
        PageResult<SeNodeVo> rst = new PageResult<>();
        List<SeNodeVo> list = seNodeDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.seNodeDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertSeNode
     * @Description: TODO(添加配电箱数据)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse insertSeNode(SeNode o, UserProp userProp) throws Exception {
        String guid = StringUtil.isEmpty(o.getId()) ? GUIDUtil.getGUID() : o.getId();
        o.setId(guid);

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getNodeID())) {
            return new MessageResponse(1, "配电箱编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }


        int temp = this.seNodeDao.isExist(o);
        if (temp > 0) {
            return new MessageResponse(1, "配电箱数据名称重复！");
        }


        o.setCreateDate(new Date());
        o.setStatus("1");
        this.seNodeDao.insert(o);
        this.dataBaseLogService.log("添加配电箱数据", "配电箱数据", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateSeNode
     * @Description: TODO(更新配电箱数据)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse updateSeNode(SeNode o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getNodeID())) {
            return new MessageResponse(1, "配电箱编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }


        this.seNodeDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更配电箱数据", "配电箱数据", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectSeNodeByPrimaryKey
     * @Description: TODO(获取配电箱数据)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SeNode>
     * @author: Arvin
     * @version: 2019-04-18
     */
    @Override
    public SingleResult<SeNodeVo> selectSeNodeByPrimaryKey(String id) throws Exception {
        SingleResult<SeNodeVo> rst = new SingleResult<>();
        rst.setValue(this.seNodeDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteSeNodeBySeNodeId
     * @Description: TODO(删除配电箱数据)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse deleteSeNodeBySeNodeId(String id, UserProp userProp) throws
            Exception {
        this.seNodeDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除配电箱数据", "配电箱数据", id, id,
                "配电箱数据", userProp);
        return new MessageResponse(0, "删除成功！");
    }

    /**
     * 同步配电箱基础数据
     *
     * @param userProp 操作用户
     * @return MessageResponse
     * @throws Exception
     */
    @Override
    public MessageResponse syncData(UserProp userProp) {
        SeApiToolKit.getBaseNodeInfo();

        return null;
    }


}
