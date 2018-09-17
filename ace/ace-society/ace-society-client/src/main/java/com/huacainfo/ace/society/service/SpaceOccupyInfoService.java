package com.huacainfo.ace.society.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.SpaceOccupyInfo;
import com.huacainfo.ace.society.vo.SpaceOccupyInfoQVo;
import com.huacainfo.ace.society.vo.SpaceOccupyInfoVo;

/**
 * @author: Arvin
 * @version: 2018-09-14
 * @Description: TODO(场地占用情况)
 */
public interface SpaceOccupyInfoService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(场地占用情况分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <SpaceOccupyInfoVo>
     * @author: Arvin
     * @version: 2018-09-14
     */
    PageResult
            <SpaceOccupyInfoVo> findSpaceOccupyInfoList(SpaceOccupyInfoQVo condition,
                                                        int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertSpaceOccupyInfo
     * @Description: TODO(添加场地占用情况)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-14
     */
    MessageResponse insertSpaceOccupyInfo(SpaceOccupyInfo obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateSpaceOccupyInfo
     * @Description: TODO(更新场地占用情况)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-14
     */
    MessageResponse updateSpaceOccupyInfo(SpaceOccupyInfo obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectSpaceOccupyInfoByPrimaryKey
     * @Description: TODO(获取场地占用情况)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SpaceOccupyInfo>
     * @author: Arvin
     * @version: 2018-09-14
     */
    SingleResult
            <SpaceOccupyInfoVo> selectSpaceOccupyInfoByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteSpaceOccupyInfoBySpaceOccupyInfoId
     * @Description: TODO(删除场地占用情况)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-14
     */
    MessageResponse deleteSpaceOccupyInfoBySpaceOccupyInfoId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核场地占用情况)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-14
     */
    MessageResponse audit(String id, String rst, String remark, UserProp userProp) throws Exception;
}
