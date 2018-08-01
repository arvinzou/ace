package com.huacainfo.ace.jxb.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.Studio;
import com.huacainfo.ace.jxb.vo.StudioQVo;
import com.huacainfo.ace.jxb.vo.StudioVo;

import java.util.Map;

/**
 * @author: Arvin
 * @version: 2018-07-25
 * @Description: TODO(工作室)
 */
public interface StudioService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(工作室分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <StudioVo>
     * @author: Arvin
     * @version: 2018-07-25
     */
    PageResult<StudioVo> findStudioList(StudioQVo condition,
                                        int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertStudio
     * @Description: TODO(添加工作室)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    MessageResponse insertStudio(Studio obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateStudio
     * @Description: TODO(更新工作室)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    MessageResponse updateStudio(Studio obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectStudioByPrimaryKey
     * @Description: TODO(获取工作室)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Studio>
     * @author: Arvin
     * @version: 2018-07-25
     */
    SingleResult<StudioVo> selectStudioByPrimaryKey(String id) throws Exception;

    ResultResponse getMyStudioInfo(String counselorId) throws Exception;

    /**
     * @throws
     * @Title:deleteStudioByStudioId
     * @Description: TODO(删除工作室)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    MessageResponse deleteStudioByStudioId(String id, UserProp userProp) throws Exception;

    /**
     * 工作室审核
     *
     * @param studioId    工作室ID
     * @param auditRs     0- 待审核 1-审核通过 2-审核拒绝
     * @param curUserProp @return
     */
    MessageResponse audit(String studioId, String auditRs, UserProp curUserProp) throws Exception;

    /**
     * 获取我的工作室列表
     *
     * @param counselorId 咨询师主键id
     * @return List<StudioVo>
     */
    Map<String, Object> getStudioList(String counselorId) throws Exception;

    /**
     * 获取工作室详情
     *
     * @param studioId 工作室ID
     * @return StudioVo
     */
    StudioVo getStudioDetail(String studioId) throws Exception;
}
