package com.huacainfo.ace.society.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.AdmireRecord;
import com.huacainfo.ace.society.vo.AdmireRecordQVo;
import com.huacainfo.ace.society.vo.AdmireRecordVo;

/**
 * @author: ArvinZou
 * @version: 2018-10-24
 * @Description: TODO(点赞管理)
 */
public interface AdmireRecordService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(点赞管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <AdmireRecordVo>
     * @author: ArvinZou
     * @version: 2018-10-24
     */
    PageResult<AdmireRecordVo> findAdmireRecordList(AdmireRecordQVo condition,
                                                    int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertAdmireRecord
     * @Description: TODO(添加点赞管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2018-10-24
     */
    MessageResponse insertAdmireRecord(AdmireRecord obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateAdmireRecord
     * @Description: TODO(更新点赞管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2018-10-24
     */
    MessageResponse updateAdmireRecord(AdmireRecord obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectAdmireRecordByPrimaryKey
     * @Description: TODO(获取点赞管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<AdmireRecord>
     * @author: ArvinZou
     * @version: 2018-10-24
     */
    SingleResult<AdmireRecordVo> selectAdmireRecordByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteAdmireRecordByAdmireRecordId
     * @Description: TODO(删除点赞管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2018-10-24
     */
    MessageResponse deleteAdmireRecordByAdmireRecordId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核点赞管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2018-10-24
     */
    MessageResponse audit(String id, String rst, String remark, UserProp userProp) throws Exception;


    /**
     * 提交点赞
     *
     * @param params 表单参数 ：   bisType  bisId ;
     * @return ResultResponse
     * @throws Exception
     */
    ResultResponse submit(AdmireRecordVo params);

    /**
     * 取消点赞
     * <p>
     * 表单参数 ：   bisType  bisId ;
     *
     * @param userId 可选
     * @return ResultResponse
     * @throws Exception
     */
    ResultResponse cancelAdmire(String bisType, String bisId, String userId);

    /**
     * 获取点赞数量
     * <p>
     * 表单参数 ：   bisType  bisId ;
     *
     * @return ResultResponse
     * @throws Exception
     */
    int getAdmireNum(String bisType, String bisId);
}
