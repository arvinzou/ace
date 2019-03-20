package com.huacainfo.ace.policeschool.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.policeschool.model.QyAttRecord;
import com.huacainfo.ace.policeschool.vo.QyAttRecordVo;
import com.huacainfo.ace.policeschool.vo.QyAttRecordQVo;

import java.util.Map;
import java.util.List;

/**
 * @author: ArvinZou
 * @version: 2019-03-19
 * @Description: TODO(群英考勤数据)
 */
public interface QyAttRecordService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(群英考勤数据分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<QyAttRecordVo>
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    PageResult<QyAttRecordVo> findQyAttRecordList(QyAttRecordQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertQyAttRecord
     * @Description: TODO(添加群英考勤数据)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    MessageResponse insertQyAttRecord(QyAttRecord obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateQyAttRecord
     * @Description: TODO(更新群英考勤数据)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    MessageResponse updateQyAttRecord(QyAttRecord obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectQyAttRecordByPrimaryKey
     * @Description: TODO(获取群英考勤数据)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<QyAttRecord>
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    SingleResult<QyAttRecordVo> selectQyAttRecordByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteQyAttRecordByQyAttRecordId
     * @Description: TODO(删除群英考勤数据)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    MessageResponse deleteQyAttRecordByQyAttRecordId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核群英考勤数据)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-03-19
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
     * @author: ArvinZou
     * @version: 2019-03-19
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
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception;


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String, Object>
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    Map<String, Object> getListByCondition(Map<String, Object> params);


    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除群英考勤数据 ）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    MessageResponse deleteQyAttRecordByQyAttRecordIds(String[] id, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception;
}
