package com.huacainfo.ace.society.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.SpaceOccupyInfo;
import com.huacainfo.ace.society.vo.SpaceOccupyInfoQVo;
import com.huacainfo.ace.society.vo.SpaceOccupyInfoVo;

import java.util.List;

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
    PageResult<SpaceOccupyInfoVo> findSpaceOccupyInfoList(SpaceOccupyInfoQVo condition,
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
    SingleResult<SpaceOccupyInfoVo> selectSpaceOccupyInfoByPrimaryKey(String id) throws Exception;

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
     * 查询场地占用情况
     *
     * @param condition 查询条件
     * @return ResultResponse
     */
    List<SpaceOccupyInfoVo> spaceOccupyInfo(SpaceOccupyInfoQVo condition);


    /***
     * 管理员场地锁定
     * @param spaceId 场地ID
     * @param dateTime 锁定日期
     * @param interval 锁定时间段
     * @return MessageResponse
     * @throws Exception
     */
    MessageResponse spaceLock(String spaceId, String dateTime, String interval, UserProp curUserProp);

    /***
     * 管理员 移除场地锁定
     * @param id 数据ID
     * @return MessageResponse
     * @throws Exception
     */
    MessageResponse removeLock(String id, UserProp curUserProp);
}
