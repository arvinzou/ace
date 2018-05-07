package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopPayRecord;
import com.huacainfo.ace.fop.vo.FopPayRecordQVo;
import com.huacainfo.ace.fop.vo.FopPayRecordVo;

/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: TODO(支付记录)
 */
public interface FopPayRecordService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(支付记录分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<FopPayRecordVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract PageResult<FopPayRecordVo> findFopPayRecordList(FopPayRecordQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertFopPayRecord
     * @Description: TODO(添加支付记录)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse insertFopPayRecord(FopPayRecord obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateFopPayRecord
     * @Description: TODO(更新支付记录)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse updateFopPayRecord(FopPayRecord obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectFopPayRecordByPrimaryKey
     * @Description: TODO(获取支付记录)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopPayRecord>
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract SingleResult<FopPayRecordVo> selectFopPayRecordByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteFopPayRecordByFopPayRecordId
     * @Description: TODO(删除支付记录)
     * @param: @param id
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse deleteFopPayRecordByFopPayRecordId(String id, UserProp userProp) throws Exception;

    /**
     * 提交缴费记录
     *
     * @param record
     * @param userProp
     * @return
     * @throws Exception
     */
    MessageResponse submitPayRecord(FopPayRecord record, UserProp userProp) throws Exception;
}
