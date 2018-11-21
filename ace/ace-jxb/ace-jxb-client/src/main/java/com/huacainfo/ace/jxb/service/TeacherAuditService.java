package com.huacainfo.ace.jxb.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.TeacherAudit;
import com.huacainfo.ace.jxb.vo.TeacherAuditQVo;
import com.huacainfo.ace.jxb.vo.TeacherAuditVo;

/**
 * @author: Arvin
 * @version: 2018-07-21
 * @Description: TODO(咨询师审核记录)
 */
public interface TeacherAuditService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(咨询师审核记录分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <TeacherAuditVo>
     * @author: Arvin
     * @version: 2018-07-21
     */
    PageResult<TeacherAuditVo> findTeacherAuditList(TeacherAuditQVo condition,
                                                    int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertTeacherAudit
     * @Description: TODO(添加咨询师审核记录)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-21
     */
    MessageResponse insertTeacherAudit(TeacherAudit obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateTeacherAudit
     * @Description: TODO(更新咨询师审核记录)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-21
     */
    MessageResponse updateTeacherAudit(TeacherAudit obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectTeacherAuditByPrimaryKey
     * @Description: TODO(获取咨询师审核记录)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<TeacherAudit>
     * @author: Arvin
     * @version: 2018-07-21
     */
    SingleResult<TeacherAuditVo> selectTeacherAuditByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteTeacherAuditByTeacherAuditId
     * @Description: TODO(删除咨询师审核记录)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-21
     */
    MessageResponse deleteTeacherAuditByTeacherAuditId(String id, UserProp userProp) throws Exception;

    /**
     * 咨询师资格审核
     *
     * @param record 参数
     * @return
     * @throws Exception
     */
    MessageResponse audit(TeacherAudit record, UserProp curUserProp) throws Exception;
}
