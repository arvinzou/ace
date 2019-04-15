package com.huacainfo.ace.glink.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.glink.model.AmmeterNode;
import com.huacainfo.ace.glink.vo.AmmeterNodeVo;
import com.huacainfo.ace.glink.vo.AmmeterNodeQVo;

import java.util.Map;
import java.util.List;

/**
 * @author: luocan
 * @version: 2019-04-15
 * @Description: TODO(节点能耗信息)
 */
public interface AmmeterNodeService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(节点能耗信息分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <AmmeterNodeVo>
     * @author: luocan
     * @version: 2019-04-15
     */
    PageResult<AmmeterNodeVo> findAmmeterNodeList(AmmeterNodeQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertAmmeterNode
     * @Description: TODO(添加节点能耗信息)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-15
     */
    MessageResponse insertAmmeterNode(AmmeterNode obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateAmmeterNode
     * @Description: TODO(更新节点能耗信息)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-15
     */
    MessageResponse updateAmmeterNode(AmmeterNode obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectAmmeterNodeByPrimaryKey
     * @Description: TODO(获取节点能耗信息)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<AmmeterNode>
     * @author: luocan
     * @version: 2019-04-15
     */
    SingleResult<AmmeterNodeVo> selectAmmeterNodeByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteAmmeterNodeByAmmeterNodeId
     * @Description: TODO(删除节点能耗信息)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-15
     */
    MessageResponse deleteAmmeterNodeByAmmeterNodeId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核节点能耗信息)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-15
     */
    MessageResponse audit(String id, String rst, String remark, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:importXls
     * @Description: TODO(Excel导入资源数据)
     * @param: @param list
     * @param: @param userProp
     * @param: @return
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-15
     */
    MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception;
}


