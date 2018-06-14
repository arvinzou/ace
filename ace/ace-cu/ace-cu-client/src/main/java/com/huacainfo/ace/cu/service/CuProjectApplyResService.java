package com.huacainfo.ace.cu.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.cu.model.CuProjectApplyRes;
import com.huacainfo.ace.cu.vo.CuProjectApplyResQVo;
import com.huacainfo.ace.cu.vo.CuProjectApplyResVo;

/**
 * @author: Arvin
 * @version: 2018-06-14
 * @Description: TODO(救急难申请表)
 */
public interface CuProjectApplyResService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(救急难申请表分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CuProjectApplyResVo>
     * @author: Arvin
     * @version: 2018-06-14
     */
    PageResult<CuProjectApplyResVo> findCuProjectApplyResList(CuProjectApplyResQVo condition,
                                                              int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertCuProjectApplyRes
     * @Description: TODO(添加救急难申请表)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    MessageResponse insertCuProjectApplyRes(CuProjectApplyRes obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateCuProjectApplyRes
     * @Description: TODO(更新救急难申请表)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    MessageResponse updateCuProjectApplyRes(CuProjectApplyRes obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectCuProjectApplyResByPrimaryKey
     * @Description: TODO(获取救急难申请表)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CuProjectApplyRes>
     * @author: Arvin
     * @version: 2018-06-14
     */
    SingleResult<CuProjectApplyResVo> selectCuProjectApplyResByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteCuProjectApplyResByCuProjectApplyResId
     * @Description: TODO(删除救急难申请表)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    MessageResponse deleteCuProjectApplyResByCuProjectApplyResId(String id, UserProp userProp) throws Exception;

}
