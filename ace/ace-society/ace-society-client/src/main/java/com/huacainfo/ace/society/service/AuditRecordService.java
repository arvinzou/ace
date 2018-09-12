package com.huacainfo.ace.society.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.AuditRecord;
import com.huacainfo.ace.society.vo.AuditRecordQVo;
import com.huacainfo.ace.society.vo.AuditRecordVo;

/**
 * @author: Arvin
 * @version: 2018-09-11
 * @Description: TODO(审核记录)
 */
public interface AuditRecordService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(审核记录分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <AuditRecordVo>
     * @author: Arvin
     * @version: 2018-09-11
     */
    PageResult<AuditRecordVo> findAuditRecordList(AuditRecordQVo condition,
                                                  int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertAuditRecord
     * @Description: TODO(添加审核记录)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-11
     */
    MessageResponse insertAuditRecord(AuditRecord obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateAuditRecord
     * @Description: TODO(更新审核记录)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-11
     */
    MessageResponse updateAuditRecord(AuditRecord obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectAuditRecordByPrimaryKey
     * @Description: TODO(获取审核记录)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<AuditRecord>
     * @author: Arvin
     * @version: 2018-09-11
     */
    SingleResult<AuditRecordVo> selectAuditRecordByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteAuditRecordByAuditRecordId
     * @Description: TODO(删除审核记录)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-11
     */
    MessageResponse deleteAuditRecordByAuditRecordId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核审核记录)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝  AuditState.java
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-11
     */
    MessageResponse audit(String bisType, String bisId, String userId,
                          String rst, String remark, UserProp userProp) throws Exception;

    /**
     * 提交审核
     *
     * @param bisType 业务类型
     * @param bisId   业务单据ID
     * @param userId  用户ID
     * @return ResultResponse
     */
    ResultResponse submit(String id, String bisType, String bisId, String userId);

}
