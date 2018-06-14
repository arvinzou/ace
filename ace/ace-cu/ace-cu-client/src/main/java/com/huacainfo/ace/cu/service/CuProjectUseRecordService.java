package com.huacainfo.ace.cu.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.cu.model.CuProjectUseRecord;
import com.huacainfo.ace.cu.vo.CuProjectUseRecordQVo;
import com.huacainfo.ace.cu.vo.CuProjectUseRecordVo;

/**
 * @author: Arvin
 * @version: 2018-06-14
 * @Description: TODO(慈善项目-使用记录)
 */
public interface CuProjectUseRecordService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(慈善项目-使用记录分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CuProjectUseRecordVo>
     * @author: Arvin
     * @version: 2018-06-14
     */
    PageResult<CuProjectUseRecordVo> findCuProjectUseRecordList(CuProjectUseRecordQVo condition,
                                                                int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertCuProjectUseRecord
     * @Description: TODO(添加慈善项目-使用记录)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    MessageResponse insertCuProjectUseRecord(CuProjectUseRecord obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateCuProjectUseRecord
     * @Description: TODO(更新慈善项目-使用记录)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    MessageResponse updateCuProjectUseRecord(CuProjectUseRecord obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectCuProjectUseRecordByPrimaryKey
     * @Description: TODO(获取慈善项目-使用记录)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CuProjectUseRecord>
     * @author: Arvin
     * @version: 2018-06-14
     */
    SingleResult<CuProjectUseRecordVo> selectCuProjectUseRecordByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteCuProjectUseRecordByCuProjectUseRecordId
     * @Description: TODO(删除慈善项目-使用记录)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    MessageResponse deleteCuProjectUseRecordByCuProjectUseRecordId(String id, UserProp userProp) throws Exception;

}
