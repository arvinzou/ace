package com.huacainfo.ace.fundtown.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fundtown.model.VipProcessRecord;
import com.huacainfo.ace.fundtown.vo.VipProcessRecordQVo;
import com.huacainfo.ace.fundtown.vo.VipProcessRecordVo;

/**
 * @author: Arvin
 * @version: 2018-07-03
 * @Description: TODO(入驻成员-流程节点记录)
 */
public interface VipProcessRecordService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(入驻成员-流程节点记录分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <VipProcessRecordVo>
     * @author: Arvin
     * @version: 2018-07-03
     */
    PageResult<VipProcessRecordVo> findVipProcessRecordList(VipProcessRecordQVo condition,
                                                            int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertVipProcessRecord
     * @Description: TODO(添加入驻成员-流程节点记录)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-03
     */
    MessageResponse insertVipProcessRecord(VipProcessRecord obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateVipProcessRecord
     * @Description: TODO(更新入驻成员-流程节点记录)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-03
     */
    MessageResponse updateVipProcessRecord(VipProcessRecord obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectVipProcessRecordByPrimaryKey
     * @Description: TODO(获取入驻成员-流程节点记录)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<VipProcessRecord>
     * @author: Arvin
     * @version: 2018-07-03
     */
    SingleResult<VipProcessRecordVo> selectVipProcessRecordByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteVipProcessRecordByVipProcessRecordId
     * @Description: TODO(删除入驻成员-流程节点记录)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-03
     */
    MessageResponse deleteVipProcessRecordByVipProcessRecordId(String id, UserProp userProp) throws Exception;

}
