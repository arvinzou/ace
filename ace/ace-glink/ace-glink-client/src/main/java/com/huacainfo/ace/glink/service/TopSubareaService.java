package com.huacainfo.ace.glink.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.glink.model.TopSubarea;
import com.huacainfo.ace.glink.vo.TopSubareaVo;
import com.huacainfo.ace.glink.vo.TopSubareaQVo;

import java.util.Map;
import java.util.List;

/**
 * @author: Arvin
 * @version: 2019-04-09
 * @Description: TODO(分区管理)
 */
public interface TopSubareaService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(分区管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<TopSubareaVo>
     * @author: Arvin
     * @version: 2019-04-09
     */
    PageResult<TopSubareaVo> findTopSubareaList(TopSubareaQVo condition,
                                                int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertTopSubarea
     * @Description: TODO(添加分区管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-09
     */
    MessageResponse insertTopSubarea(TopSubarea obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateTopSubarea
     * @Description: TODO(更新分区管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-09
     */
    MessageResponse updateTopSubarea(TopSubarea obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectTopSubareaByPrimaryKey
     * @Description: TODO(获取分区管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<TopSubarea>
     * @author: Arvin
     * @version: 2019-04-09
     */
    SingleResult<TopSubareaVo> selectTopSubareaByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteTopSubareaByTopSubareaId
     * @Description: TODO(删除分区管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-09
     */
    MessageResponse deleteTopSubareaByTopSubareaId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核分区管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
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
     * @author: Arvin
     * @version: 2019-04-09
     */
    MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:getList
     * @Description: TODO(条件查询数据)
     * @param: @param p
     * @param: @return
     * @param: @throws Exception
     * @return: ListResult<Map < String, Object>>
     * @author: Arvin
     * @version: 2019-04-09
     */
    ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception;


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String, Object>
     * @author: Arvin
     * @version: 2019-04-09
     */
    Map<String, Object> getListByCondition(Map<String, Object> params);


    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除分区管理 ）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-09
     */
    MessageResponse deleteTopSubareaByTopSubareaIds(String[] id, UserProp userProp) throws
            Exception;


    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-09
     */
    MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception;
}
