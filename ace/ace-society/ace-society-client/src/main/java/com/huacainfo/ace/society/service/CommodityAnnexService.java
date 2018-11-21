package com.huacainfo.ace.society.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.CommodityAnnex;
import com.huacainfo.ace.society.vo.CommodityAnnexQVo;
import com.huacainfo.ace.society.vo.CommodityAnnexVo;

/**
 * @author: Arvin
 * @version: 2018-09-13
 * @Description: TODO(爱心商品附录)
 */
public interface CommodityAnnexService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(爱心商品附录分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CommodityAnnexVo>
     * @author: Arvin
     * @version: 2018-09-13
     */
    PageResult
            <CommodityAnnexVo> findCommodityAnnexList(CommodityAnnexQVo condition,
                                                      int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertCommodityAnnex
     * @Description: TODO(添加爱心商品附录)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-13
     */
    MessageResponse insertCommodityAnnex(CommodityAnnex obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateCommodityAnnex
     * @Description: TODO(更新爱心商品附录)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-13
     */
    MessageResponse updateCommodityAnnex(CommodityAnnex obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectCommodityAnnexByPrimaryKey
     * @Description: TODO(获取爱心商品附录)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CommodityAnnex>
     * @author: Arvin
     * @version: 2018-09-13
     */
    SingleResult
            <CommodityAnnexVo> selectCommodityAnnexByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteCommodityAnnexByCommodityAnnexId
     * @Description: TODO(删除爱心商品附录)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-13
     */
    MessageResponse deleteCommodityAnnexByCommodityAnnexId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核爱心商品附录)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-13
     */
    MessageResponse audit(String id, String rst, String remark, UserProp userProp) throws Exception;
}
