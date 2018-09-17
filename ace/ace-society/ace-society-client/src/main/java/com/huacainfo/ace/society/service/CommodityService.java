package com.huacainfo.ace.society.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.Commodity;
import com.huacainfo.ace.society.vo.CommodityQVo;
import com.huacainfo.ace.society.vo.CommodityVo;

/**
 * @author: Arvin
 * @version: 2018-09-13
 * @Description: TODO(爱心商品)
 */
public interface CommodityService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(爱心商品分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CommodityVo>
     * @author: Arvin
     * @version: 2018-09-13
     */
    PageResult<CommodityVo> findCommodityList(CommodityQVo condition,
                                              int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertCommodity
     * @Description: TODO(添加爱心商品)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-13
     */
    MessageResponse insertCommodity(Commodity obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateCommodity
     * @Description: TODO(更新爱心商品)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-13
     */
    MessageResponse updateCommodity(Commodity obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectCommodityByPrimaryKey
     * @Description: TODO(获取爱心商品)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Commodity>
     * @author: Arvin
     * @version: 2018-09-13
     */
    SingleResult<CommodityVo> selectCommodityByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteCommodityByCommodityId
     * @Description: TODO(删除爱心商品)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-13
     */
    MessageResponse deleteCommodityByCommodityId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核爱心商品)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-13
     */
    MessageResponse audit(String id, String rst, String remark, UserProp userProp) throws Exception;

    /**
     * 商品上/下架处理
     *
     * @param id    主键ID
     * @param state 商品状态0-下架1-在售
     * @return MessageResponse
     * @throws Exception
     */
    MessageResponse updateState(String id, String state, UserProp curUserProp) throws Exception;
}
