package com.huacainfo.ace.jxb.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.Banner;
import com.huacainfo.ace.jxb.vo.BannerVo;
import com.huacainfo.ace.jxb.vo.BannerQVo;

/**
 * @author: huacai003
 * @version: 2018-09-26
 * @Description: TODO(banner)
 */
public interface BannerService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(banner分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <BannerVo>
     * @author: huacai003
     * @version: 2018-09-26
     */
    PageResult
            <BannerVo> findBannerList(BannerQVo condition,
                                      int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertBanner
     * @Description: TODO(添加banner)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-26
     */
    MessageResponse insertBanner(Banner obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateBanner
     * @Description: TODO(更新banner)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-26
     */
    MessageResponse updateBanner(Banner obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectBannerByPrimaryKey
     * @Description: TODO(获取banner)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Banner>
     * @author: huacai003
     * @version: 2018-09-26
     */
    SingleResult
            <BannerVo> selectBannerByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteBannerByBannerId
     * @Description: TODO(删除banner)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-26
     */
    MessageResponse deleteBannerByBannerId(String id, UserProp userProp) throws Exception;
}

