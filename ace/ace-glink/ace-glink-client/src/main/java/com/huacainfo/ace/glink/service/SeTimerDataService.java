package com.huacainfo.ace.glink.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.glink.model.SeTimerData;
import com.huacainfo.ace.glink.vo.SeTimerDataVo;
import com.huacainfo.ace.glink.vo.SeTimerDataQVo;

import java.util.Map;
import java.util.List;

/**
 * @author: heshuang
 * @version: 2019-04-19
 * @Description: TODO(定时任务数据)
 */
public interface SeTimerDataService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(定时任务数据分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <SeTimerDataVo>
     * @author: heshuang
     * @version: 2019-04-19
     */
    PageResult
            <SeTimerDataVo> findSeTimerDataList(SeTimerDataQVo condition,
                                                int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertSeTimerData
     * @Description: TODO(添加定时任务数据)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-19
     */
    MessageResponse insertSeTimerData(SeTimerData obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateSeTimerData
     * @Description: TODO(更新定时任务数据)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-19
     */
    MessageResponse updateSeTimerData(SeTimerData obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectSeTimerDataByPrimaryKey
     * @Description: TODO(获取定时任务数据)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SeTimerData>
     * @author: heshuang
     * @version: 2019-04-19
     */
    SingleResult
            <SeTimerDataVo> selectSeTimerDataByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteSeTimerDataBySeTimerDataId
     * @Description: TODO(删除定时任务数据)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-19
     */
    MessageResponse deleteSeTimerDataBySeTimerDataId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核定时任务数据)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-19
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
     * @version: 2019-04-19
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
     * @version: 2019-04-19
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
     * @version: 2019-04-19
     */
    Map
            <String
                    , Object> getListByCondition(Map
                                                         <String
                                                                 , Object> params);


    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除定时任务数据 ）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-19
     */
    MessageResponse deleteSeTimerDataBySeTimerDataIds(String[] id, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-19
     */
    MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception;

    MessageResponse syncData(UserProp userProp);
}
