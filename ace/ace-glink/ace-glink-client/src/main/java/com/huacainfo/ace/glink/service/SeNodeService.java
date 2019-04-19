package com.huacainfo.ace.glink.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.glink.model.SeNode;
import com.huacainfo.ace.glink.model.SeNodeMonitorDeviceCh;
import com.huacainfo.ace.glink.vo.SeNodeMonitorVo;
import com.huacainfo.ace.glink.vo.SeNodeQVo;
import com.huacainfo.ace.glink.vo.SeNodeVo;

/**
 * @author: Arvin
 * @version: 2019-04-18
 * @Description: TODO(配电箱数据)
 */
public interface SeNodeService {
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
    PageResult<SeNodeVo> findSeNodeList(SeNodeQVo condition,
                                        int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertSeNode
     * @Description: TODO(添加配电箱数据)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-18
     */
    MessageResponse insertSeNode(SeNode obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateSeNode
     * @Description: TODO(更新配电箱数据)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-18
     */
    MessageResponse updateSeNode(SeNode obj, UserProp userProp) throws Exception;

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
    SingleResult<SeNodeVo> selectSeNodeByPrimaryKey(String id) throws Exception;

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
    MessageResponse deleteSeNodeBySeNodeId(String id, UserProp userProp) throws Exception;

    /**
     * 同步配电箱基础数据
     *
     * @return MessageResponse
     * @throws Exception
     */
    MessageResponse syncData(UserProp userProp);

    /**
     * 同步配电箱监测数据
     *
     * @return MessageResponse
     * @throws Exception
     */
    MessageResponse syncMonitorData(UserProp curUserProp);

    /**
     * 获取配电箱监控数据
     *
     * @param nodeID 配电箱编号
     * @return SingleResult<SeNodeMonitorVo>
     * @throws Exception
     */
    SingleResult<SeNodeMonitorVo> getMonitorData(String nodeID);

    /**
     * 获取配电箱监控数据
     *
     * @param deviceCode 模块代码
     * @param chName     回路名称
     * @return SingleResult<SeNodeMonitorDeviceCh>
     * @throws Exception
     */
    SingleResult<SeNodeMonitorDeviceCh> getMonitorDeviceCH(String deviceCode, String chName);
}
