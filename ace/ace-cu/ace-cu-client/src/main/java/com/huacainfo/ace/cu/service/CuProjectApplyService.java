package com.huacainfo.ace.cu.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.cu.model.CuProjectApply;
import com.huacainfo.ace.cu.vo.CuProjectApplyQVo;
import com.huacainfo.ace.cu.vo.CuProjectApplyVo;

/**
 * @author: Arvin
 * @version: 2018-06-14
 * @Description: TODO(救急难申请表)
 */
public interface CuProjectApplyService {
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
     * <CuProjectApplyVo>
     * @author: Arvin
     * @version: 2018-06-14
     */
    PageResult<CuProjectApplyVo> findCuProjectApplyList(CuProjectApplyQVo condition,
                                                        int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertCuProjectApply
     * @Description: TODO(添加救急难申请表)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    MessageResponse insertCuProjectApply(CuProjectApply obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateCuProjectApply
     * @Description: TODO(更新救急难申请表)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    MessageResponse updateCuProjectApply(CuProjectApply obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectCuProjectApplyByPrimaryKey
     * @Description: TODO(获取救急难申请表)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CuProjectApply>
     * @author: Arvin
     * @version: 2018-06-14
     */
    SingleResult<CuProjectApplyVo> selectCuProjectApplyByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteCuProjectApplyByCuProjectApplyId
     * @Description: TODO(删除救急难申请表)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    MessageResponse deleteCuProjectApplyByCuProjectApplyId(String id, UserProp userProp) throws Exception;

    /**
     * 救急难 - 提交申请资料
     *
     * @param vo 参考 CuProjectApplyVo.java对象
     * @return
     * @throws Exception
     */
    ResultResponse applyProject(CuProjectApplyVo vo);
}
