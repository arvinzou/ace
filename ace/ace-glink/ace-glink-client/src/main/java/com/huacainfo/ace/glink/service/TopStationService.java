package com.huacainfo.ace.glink.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.glink.model.TopStation;
import com.huacainfo.ace.glink.vo.TopStationVo;
import com.huacainfo.ace.glink.vo.TopStationQVo;

import java.util.Map;
import java.util.List;

/**
 * @author: heshuang
 * @version: 2019-04-09
 * @Description: TODO(站点管理)
 */
public interface TopStationService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(站点管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <TopStationVo>
     * @author: heshuang
     * @version: 2019-04-09
     */
    PageResult
            <TopStationVo> findTopStationList(TopStationQVo condition, int start, int limit, String orderBy) throws
            Exception;

    /**
     * @throws
     * @Title:insertTopStation
     * @Description: TODO(添加站点管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-09
     */
    MessageResponse insertTopStation(TopStation obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateTopStation
     * @Description: TODO(更新站点管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-09
     */
    MessageResponse updateTopStation(TopStation obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectTopStationByPrimaryKey
     * @Description: TODO(获取站点管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<TopStation>
     * @author: heshuang
     * @version: 2019-04-09
     */
    SingleResult<TopStationVo> selectTopStationByPrimaryKey(String id) throws Exception;

    SingleResult<TopStation> selectByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteTopStationByTopStationId
     * @Description: TODO(删除站点管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-09
     */
    MessageResponse deleteTopStationByTopStationId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核站点管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-09
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
     * @author: heshuang
     * @version: 2019-04-09
     */
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:getList
     * @Description: TODO(条件查询数据)
     * @param: @param p
     * @param: @return
     * @param: @throws Exception
     * @return: ListResult
     * <Map
     * <String
     * ,Object>>
     * @author: heshuang
     * @version: 2019-04-09
     */
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception;


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map
     * <String
     * ,Object>
     * @author: heshuang
     * @version: 2019-04-09
     */
    public Map<String, Object> getListByCondition(Map<String, Object> params);


    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除站点管理 ）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-09
     */
    public MessageResponse deleteTopStationByTopStationIds(String[] id, UserProp userProp) throws
            Exception;


    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-09
     */
    MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception;
}
