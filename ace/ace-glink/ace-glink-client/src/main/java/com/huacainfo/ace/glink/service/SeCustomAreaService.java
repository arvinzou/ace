package com.huacainfo.ace.glink.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.glink.model.SeCustomArea;
import com.huacainfo.ace.glink.vo.SeCustomAreaVo;
import com.huacainfo.ace.glink.vo.SeCustomAreaQVo;

import java.util.Map;
import java.util.List;

/**
 * @author: heshuang
 * @version: 2019-04-18
 * @Description: TODO(逻辑区数据)
 */
public interface SeCustomAreaService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(逻辑区数据分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <SeCustomAreaVo>
     * @author: heshuang
     * @version: 2019-04-18
     */
    PageResult
            <SeCustomAreaVo> findSeCustomAreaList(SeCustomAreaQVo condition,
                                                  int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertSeCustomArea
     * @Description: TODO(添加逻辑区数据)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-18
     */
    MessageResponse insertSeCustomArea(SeCustomArea obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateSeCustomArea
     * @Description: TODO(更新逻辑区数据)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-18
     */
    MessageResponse updateSeCustomArea(SeCustomArea obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectSeCustomAreaByPrimaryKey
     * @Description: TODO(获取逻辑区数据)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SeCustomArea>
     * @author: heshuang
     * @version: 2019-04-18
     */
    SingleResult
            <SeCustomAreaVo> selectSeCustomAreaByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteSeCustomAreaBySeCustomAreaId
     * @Description: TODO(删除逻辑区数据)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-18
     */
    MessageResponse deleteSeCustomAreaBySeCustomAreaId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核逻辑区数据)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-18
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
     * @version: 2019-04-18
     */
    MessageResponse importXls(List
                                      <Map
                                              <String
                                                      , Object>> list, UserProp userProp) throws Exception;


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
     * @version: 2019-04-18
     */
    ListResult
            <Map
                    <String
                            , Object>> getList(Map
                                                       <String
                                                               , Object> p) throws Exception;


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
     * @version: 2019-04-18
     */
    Map
            <String
                    , Object> getListByCondition(Map
                                                         <String
                                                                 , Object> params);


    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除逻辑区数据 ）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-18
     */
    MessageResponse deleteSeCustomAreaBySeCustomAreaIds(String[] id, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-18
     */
    MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception;

    /**
     * 同步数据
     *
     * @param userProp
     * @return
     */
    MessageResponse syncCustomData(List<SeCustomArea> obj, UserProp userProp);
}
