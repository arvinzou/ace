package com.huacainfo.ace.society.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.PointsRecord;
import com.huacainfo.ace.society.vo.PointsRecordQVo;
import com.huacainfo.ace.society.vo.PointsRecordVo;

/**
 * @author: arvin
 * @version: 2018-09-28
 * @Description: TODO(积分流水)
 */
public interface PointsRecordService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(积分流水分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <PointsRecordVo>
     * @author: arvin
     * @version: 2018-09-28
     */
    PageResult<PointsRecordVo> findPointsRecordList(PointsRecordQVo condition,
                                                    int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertPointsRecord
     * @Description: TODO(添加积分流水)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: arvin
     * @version: 2018-09-28
     */
    MessageResponse insertPointsRecord(PointsRecord obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updatePointsRecord
     * @Description: TODO(更新积分流水)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: arvin
     * @version: 2018-09-28
     */
    MessageResponse updatePointsRecord(PointsRecord obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectPointsRecordByPrimaryKey
     * @Description: TODO(获取积分流水)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<PointsRecord>
     * @author: arvin
     * @version: 2018-09-28
     */
    SingleResult<PointsRecordVo> selectPointsRecordByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deletePointsRecordByPointsRecordId
     * @Description: TODO(删除积分流水)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: arvin
     * @version: 2018-09-28
     */
    MessageResponse deletePointsRecordByPointsRecordId(String id, UserProp userProp) throws Exception;

    /**
     * 增加积分变化流水
     *
     * @param userId  用户ID
     * @param bisType 业务类型
     * @param bisId   业务单据id
     * @param points  变化量
     * @return ResultResponse
     */
    ResultResponse addPointsRecord(String userId, String bisType, String bisId, int points);
}
