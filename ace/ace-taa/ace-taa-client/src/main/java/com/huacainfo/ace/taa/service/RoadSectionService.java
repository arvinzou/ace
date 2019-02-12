package com.huacainfo.ace.taa.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.taa.model.RoadSection;
import com.huacainfo.ace.taa.vo.RoadSectionQVo;
import com.huacainfo.ace.taa.vo.RoadSectionVo;

import java.util.List;
import java.util.Map;

/**
 * @author: 陈晓克
 * @version: 2019-01-04
 * @Description: TODO(路段)
 */
public interface RoadSectionService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(路段分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <RoadSectionVo>
     * @author: 陈晓克
     * @version: 2019-01-04
     */
    PageResult<RoadSectionVo> findRoadSectionList(RoadSectionQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertRoadSection
     * @Description: TODO(添加路段)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-04
     */
    MessageResponse insertRoadSection(RoadSection obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateRoadSection
     * @Description: TODO(更新路段)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-04
     */
    MessageResponse updateRoadSection(RoadSection obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectRoadSectionByPrimaryKey
     * @Description: TODO(获取路段)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<RoadSection>
     * @author: 陈晓克
     * @version: 2019-01-04
     */
    SingleResult<RoadSectionVo> selectRoadSectionByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionId
     * @Description: TODO(删除路段)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-04
     */
    MessageResponse deleteRoadSectionByRoadSectionId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核路段)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-04
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
     * @author: 陈晓克
     * @version: 2019-01-04
     */
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp, String roadId) throws Exception;


    /**
     * @throws
     * @Title:getList
     * @Description: TODO(条件查询数据)
     * @param: @param p
     * @param: @return
     * @param: @throws Exception
     * @return: ListResult<Map<String,Object>>
     * @author: 陈晓克
     * @version: 2019-01-04
     */
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception;


    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(删除路段)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-04
     */
    MessageResponse deleteRoadSectionByRoadSectionIds(String roadId, String[] id, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String,Object>
     * @author: chenxiaoke
     * @version: 2019年1月04日 下午1:24:14
     */
    Map<String, Object> getListByCondition(Map<String, Object> params);

}
