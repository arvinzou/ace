package com.huacainfo.ace.cu.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.cu.model.CuProcessRecord;
import com.huacainfo.ace.cu.vo.CuProcessRecordQVo;
import com.huacainfo.ace.cu.vo.CuProcessRecordVo;

import java.util.Date;

/**
 * @author: Arvin
 * @version: 2018-07-05
 * @Description: TODO(救急难流程处理记录)
 */
public interface CuProcessRecordService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(救急难流程处理记录分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CuProcessRecordVo>
     * @author: Arvin
     * @version: 2018-07-05
     */
    PageResult<CuProcessRecordVo> findCuProcessRecordList(CuProcessRecordQVo condition,
                                                          int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertCuProcessRecord
     * @Description: TODO(添加救急难流程处理记录)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-05
     */
    MessageResponse insertCuProcessRecord(CuProcessRecord obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateCuProcessRecord
     * @Description: TODO(更新救急难流程处理记录)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-05
     */
    MessageResponse updateCuProcessRecord(CuProcessRecord obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectCuProcessRecordByPrimaryKey
     * @Description: TODO(获取救急难流程处理记录)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CuProcessRecord>
     * @author: Arvin
     * @version: 2018-07-05
     */
    SingleResult<CuProcessRecordVo> selectCuProcessRecordByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteCuProcessRecordByCuProcessRecordId
     * @Description: TODO(删除救急难流程处理记录)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-05
     */
    MessageResponse deleteCuProcessRecordByCuProcessRecordId(String id, UserProp userProp) throws Exception;

    /**
     * 插入流程记录
     *
     * @param userId     cu_user.id
     * @param openId     openid
     * @param resId      cu_project_apply.id
     * @param recordDate 录入时间
     * @return
     */
    ResultResponse insertRecord(String userId, String openId, String resId,
                                int nodeIndex, String nodeDesc, Date recordDate);
}
