package com.huacainfo.ace.cu.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.cu.model.CuProjectRes;
import com.huacainfo.ace.cu.vo.CuProjectResQVo;
import com.huacainfo.ace.cu.vo.CuProjectResVo;

/**
 * @author: Arvin
 * @version: 2018-08-11
 * @Description: TODO(项目资源)
 */
public interface CuProjectResService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(项目资源分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CuProjectResVo>
     * @author: Arvin
     * @version: 2018-08-11
     */
    PageResult<CuProjectResVo> findCuProjectResList(CuProjectResQVo condition,
                                                    int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertCuProjectRes
     * @Description: TODO(添加项目资源)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-11
     */
    MessageResponse insertCuProjectRes(CuProjectRes obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateCuProjectRes
     * @Description: TODO(更新项目资源)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-11
     */
    MessageResponse updateCuProjectRes(CuProjectRes obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectCuProjectResByPrimaryKey
     * @Description: TODO(获取项目资源)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CuProjectRes>
     * @author: Arvin
     * @version: 2018-08-11
     */
    SingleResult<CuProjectResVo> selectCuProjectResByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteCuProjectResByCuProjectResId
     * @Description: TODO(删除项目资源)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-11
     */
    MessageResponse deleteCuProjectResByCuProjectResId(String id, UserProp userProp) throws Exception;

}
