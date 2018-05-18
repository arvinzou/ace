package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopCallRecord;
import com.huacainfo.ace.fop.vo.FopCallRecordDetailVo;
import com.huacainfo.ace.fop.vo.FopCallRecordQVo;
import com.huacainfo.ace.fop.vo.FopCallRecordVo;

import java.util.List;
import java.util.Map;

/**
 * @author: Arvin
 * @version: 2018-05-17
 * @Description: TODO(企业/协会活动)
 */
public interface FopCallRecordService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(企业/协会活动分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <FopCallRecordVo>
     * @author: Arvin
     * @version: 2018-05-17
     */
    PageResult<FopCallRecordVo> findFopCallRecordList(FopCallRecordQVo condition,
                                                      int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertFopCallRecord
     * @Description: TODO(添加企业/协会活动)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-17
     */
    MessageResponse insertFopCallRecord(FopCallRecord obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateFopCallRecord
     * @Description: TODO(更新企业/协会活动)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-17
     */
    MessageResponse updateFopCallRecord(FopCallRecord obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectFopCallRecordByPrimaryKey
     * @Description: TODO(获取企业/协会活动)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopCallRecord>
     * @author: Arvin
     * @version: 2018-05-17
     */
    SingleResult<FopCallRecordVo> selectFopCallRecordByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteFopCallRecordByFopCallRecordId
     * @Description: TODO(催缴记录)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-17
     */
    MessageResponse deleteFopCallRecordByFopCallRecordId(String id, UserProp userProp) throws Exception;


    /**
     * 功能描述: 推送催缴通知
     *
     * @param recordId
     * @param userProp
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/18 9:39
     */
    MessageResponse sendCallNotice(String recordId, UserProp userProp) throws Exception;

    /**
     * 功能描述: 添加催缴对象
     *
     * @param:
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/18 9:39
     */
    MessageResponse insertCallRecordDetail(String recordId, List<Map<String, String>> detailList, UserProp userProp);

    /**
     * @param recordId fop_call_record.id
     * @return
     */
    List<FopCallRecordDetailVo> findCallRecordDetail(String recordId);
}
