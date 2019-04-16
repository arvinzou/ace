package com.huacainfo.ace.glink.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.glink.model.LtLnkObject;
import com.huacainfo.ace.glink.model.LtStrategy;
import com.huacainfo.ace.glink.vo.LtStrategyVo;
import com.huacainfo.ace.glink.vo.LtStrategyQVo;

import java.util.Map;
import java.util.List;

/**
 * @author: huacai003
 * @version: 2019-04-10
 * @Description: TODO(策略管理)
 */
public interface LtStrategyService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(策略管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <LtStrategyVo>
     * @author: huacai003
     * @version: 2019-04-10
     */
    PageResult
            <LtStrategyVo> findLtStrategyList(LtStrategyQVo condition,
                                              int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertLtStrategy
     * @Description: TODO(添加策略管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-10
     */
    MessageResponse insertLtStrategy(LtStrategy obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateLtStrategy
     * @Description: TODO(更新策略管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-10
     */
    MessageResponse updateLtStrategy(LtStrategy obj, UserProp userProp) throws Exception;

    MessageResponse updateLtStrategyVo(LtStrategy obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectLtStrategyByPrimaryKey
     * @Description: TODO(获取策略管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<LtStrategy>
     * @author: huacai003
     * @version: 2019-04-10
     */
    SingleResult
            <LtStrategyVo> selectLtStrategyByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteLtStrategyByLtStrategyId
     * @Description: TODO(删除策略管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-10
     */
    MessageResponse deleteLtStrategyByLtStrategyId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核策略管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-10
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
     * @author: huacai003
     * @version: 2019-04-10
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
     * @author: huacai003
     * @version: 2019-04-10
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
     * @author: huacai003
     * @version: 2019-04-10
     */
    Map
            <String
                    , Object> getListByCondition(Map
                                                         <String
                                                                 , Object> params);


    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除策略管理）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-10
     */
    MessageResponse deleteLtStrategyByLtStrategyIds(String[] id, UserProp userProp) throws
            Exception;


    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-10
     */
    MessageResponse updateStatus(String id, LtLnkObject ltLnkObject, UserProp userProp) throws Exception;
}
