package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.TalentInfo;
import com.huacainfo.ace.fop.vo.TalentInfoVo;
import com.huacainfo.ace.fop.vo.TalentInfoQVo;

/**
 * @author: huacai003
 * @version: 2018-05-17
 * @Description: TODO(人才信息)
 */
public interface TalentInfoService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(人才信息分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <TalentInfoVo>
     * @author: huacai003
     * @version: 2018-05-17
     */
    PageResult
            <TalentInfoVo> findTalentInfoList(TalentInfoQVo condition, int start, int limit, String orderBy) throws
            Exception;

    /**
     * @throws
     * @Title:insertTalentInfo
     * @Description: TODO(添加人才信息)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    MessageResponse insertTalentInfo(TalentInfo obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateTalentInfo
     * @Description: TODO(更新人才信息)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    MessageResponse updateTalentInfo(TalentInfo obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectTalentInfoByPrimaryKey
     * @Description: TODO(获取人才信息)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<TalentInfo>
     * @author: huacai003
     * @version: 2018-05-17
     */
    SingleResult
            <TalentInfoVo> selectTalentInfoByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteTalentInfoByTalentInfoId
     * @Description: TODO(删除人才信息)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    MessageResponse deleteTalentInfoByTalentInfoId(String id, UserProp userProp) throws Exception;

}
