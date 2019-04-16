package com.huacainfo.ace.glink.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.glink.model.MapCtrlBuilding;
import com.huacainfo.ace.glink.vo.MapCtrlBuildingQVo;
import com.huacainfo.ace.glink.vo.MapCtrlBuildingVo;

import java.util.List;
import java.util.Map;

/**
 * @author: Arvin
 * @version: 2019-04-16
 * @Description: TODO(控制器映射关系)
 */
public interface MapCtrlBuildingService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(控制器映射关系分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<MapCtrlBuildingVo>
     * @author: Arvin
     * @version: 2019-04-16
     */
    PageResult<MapCtrlBuildingVo> findMapCtrlBuildingList(MapCtrlBuildingQVo condition,
                                                          int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertMapCtrlBuilding
     * @Description: TODO(添加控制器映射关系)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-16
     */
    MessageResponse insertMapCtrlBuilding(MapCtrlBuilding obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateMapCtrlBuilding
     * @Description: TODO(更新控制器映射关系)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-16
     */
    MessageResponse updateMapCtrlBuilding(MapCtrlBuilding obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectMapCtrlBuildingByPrimaryKey
     * @Description: TODO(获取控制器映射关系)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<MapCtrlBuilding>
     * @author: Arvin
     * @version: 2019-04-16
     */
    SingleResult<MapCtrlBuildingVo> selectMapCtrlBuildingByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteMapCtrlBuildingByMapCtrlBuildingId
     * @Description: TODO(删除控制器映射关系)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-16
     */
    MessageResponse deleteMapCtrlBuildingByMapCtrlBuildingId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核控制器映射关系)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-16
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
     * @author: Arvin
     * @version: 2019-04-16
     */
    MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception;

}
