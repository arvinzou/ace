package com.huacainfo.ace.cu.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.cu.model.CuProcessRecord;
import com.huacainfo.ace.cu.model.CuProject;
import com.huacainfo.ace.cu.model.CuProjectApply;
import com.huacainfo.ace.cu.vo.CuProcessRecordQVo;
import com.huacainfo.ace.cu.vo.CuProcessRecordVo;
import com.huacainfo.ace.cu.vo.CuProjectApplyVo;

import java.util.Date;
import java.util.List;

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

    /**
     * 获取“救急难” 处理详情
     *
     * @param applyId
     * @return
     */
    List<CuProcessRecord> findList(String applyId);

    /***
     * 提交申请
     * @param vo
     * @return
     */
    ResultResponse recordSubmit(CuProjectApplyVo vo);

    /**
     * 受理申请
     *
     * @param auditResult  受理结果
     * @param auditOpinion 受理意见
     * @param apply        受理资料
     * @return
     */
    ResultResponse recordAccept(String auditResult, String auditOpinion, CuProjectApply apply);

    /**
     * 项目审核
     */
    ResultResponse recordProjectAudit(String auditResult, String auditOpinion, CuProject project);

    /**
     * 项目上线
     */
    ResultResponse recordProjectSetup(CuProject project);

    /**
     * 项目关闭
     */
    ResultResponse recordProjectShutDown(String reason, CuProject project);
}
