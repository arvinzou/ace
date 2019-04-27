package com.huacainfo.ace.glink.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.glink.model.SeGatewayState;
import com.huacainfo.ace.glink.vo.SeGatewayStateQVo;
import com.huacainfo.ace.glink.vo.SeGatewayStateVo;

import java.util.List;
import java.util.Map;

/**
 * @author: luocan
 * @version: 2019-04-18
 * @Description: TODO(网关数据)
 */
public interface SeGatewayStateService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(网关数据分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <SeGatewayStateVo>
     * @author: luocan
     * @version: 2019-04-18
     */
    PageResult<SeGatewayStateVo> findSeGatewayStateList(SeGatewayStateQVo condition,
                                                      int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertSeGatewayState
     * @Description: TODO(添加网关数据)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-18
     */
    MessageResponse insertSeGatewayState(List<SeGatewayState> list, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateSeGatewayState
     * @Description: TODO(更新网关数据)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-18
     */
    MessageResponse updateSeGatewayState(SeGatewayState obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectSeGatewayStateByPrimaryKey
     * @Description: TODO(获取网关数据)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SeGatewayState>
     * @author: luocan
     * @version: 2019-04-18
     */
    SingleResult<SeGatewayStateVo> selectSeGatewayStateByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteSeGatewayStateBySeGatewayStateId
     * @Description: TODO(删除网关数据)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-18
     */
    MessageResponse deleteSeGatewayStateBySeGatewayStateId(String id, UserProp userProp) throws Exception;


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
     * @version: 2019-04-18
     */
    MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception;



    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除网关数据 ）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-18
     */
    MessageResponse deleteSeGatewayStateBySeGatewayStateIds(List<String> list, UserProp userProp) throws Exception;


    /**
     * 同步网关数据
     *
     * @return MessageResponse
     * @throws Exception
     */
    MessageResponse syncData(UserProp curUserProp);
}
