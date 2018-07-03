package com.huacainfo.ace.fundtown.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fundtown.model.VipPublicity;
import com.huacainfo.ace.fundtown.vo.VipPublicityQVo;
import com.huacainfo.ace.fundtown.vo.VipPublicityVo;

/**
 * @author: Arvin
 * @version: 2018-07-03
 * @Description: TODO(入驻成员公示列表)
 */
public interface VipPublicityService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(入驻成员公示列表分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <VipPublicityVo>
     * @author: Arvin
     * @version: 2018-07-03
     */
    PageResult<VipPublicityVo> findVipPublicityList(VipPublicityQVo condition,
                                                    int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertVipPublicity
     * @Description: TODO(添加入驻成员公示列表)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-03
     */
    MessageResponse insertVipPublicity(VipPublicity obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateVipPublicity
     * @Description: TODO(更新入驻成员公示列表)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-03
     */
    MessageResponse updateVipPublicity(VipPublicity obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectVipPublicityByPrimaryKey
     * @Description: TODO(获取入驻成员公示列表)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<VipPublicity>
     * @author: Arvin
     * @version: 2018-07-03
     */
    SingleResult<VipPublicityVo> selectVipPublicityByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteVipPublicityByVipPublicityId
     * @Description: TODO(删除入驻成员公示列表)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-03
     */
    MessageResponse deleteVipPublicityByVipPublicityId(String id, UserProp userProp) throws Exception;

}
