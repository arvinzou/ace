package com.huacainfo.ace.taa.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.taa.model.TraAcc;
import com.huacainfo.ace.taa.vo.TraAccVo;
import com.huacainfo.ace.taa.vo.TraAccQVo;

import java.util.Map;
import java.util.List;

/**
 * @author: 陈晓克
 * @version: 2019-01-10
 * @Description: TODO(事故)
 */
public interface TraAccService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(事故分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <TraAccVo>
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    PageResult<TraAccVo> findTraAccList(TraAccQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertTraAcc
     * @Description: TODO(添加事故)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    MessageResponse insertTraAcc(TraAcc obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateTraAcc
     * @Description: TODO(更新事故)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    MessageResponse updateTraAcc(TraAcc obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectTraAccByPrimaryKey
     * @Description: TODO(获取事故)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<TraAcc>
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    SingleResult<TraAccVo> selectTraAccByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteTraAccByTraAccId
     * @Description: TODO(删除事故)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    MessageResponse deleteTraAccByTraAccId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核事故)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-10
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
     * @version: 2019-01-10
     */
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:getList
     * @Description: TODO(条件查询数据)
     * @param: @param p
     * @param: @return
     * @param: @throws Exception
     * @return: ListResult<Map<String,Object>>
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception;


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String,Object>
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    public Map<String, Object> getListByCondition(Map<String, Object> params);


    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除事故
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    public MessageResponse deleteTraAccByTraAccIds(String[] id, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    MessageResponse updateStatus(String id,String status, UserProp userProp) throws Exception;
}
