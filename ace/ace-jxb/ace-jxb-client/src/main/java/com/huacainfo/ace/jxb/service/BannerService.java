package com.huacainfo.ace.jxb.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.Banner;
import com.huacainfo.ace.jxb.vo.BannerVo;
import com.huacainfo.ace.jxb.vo.BannerQVo;

/**
 * @author: 陈晓克
 * @version: 2018-09-26
 * @Description: TODO(轮播图)
 */
public interface BannerService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(轮播图分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <BannerVo>
     * @author: 陈晓克
     * @version: 2018-09-26
     */
    PageResult<BannerVo> findBannerList(BannerQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertBanner
     * @Description: TODO(添加轮播图)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-26
     */
    MessageResponse insertBanner(Banner obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateBanner
     * @Description: TODO(更新轮播图)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-26
     */
    MessageResponse updateBanner(Banner obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectBannerByPrimaryKey
     * @Description: TODO(获取轮播图)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Banner>
     * @author: 陈晓克
     * @version: 2018-09-26
     */
    SingleResult<BannerVo> selectBannerByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteBannerByBannerId
     * @Description: TODO(删除轮播图)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-26
     */
    MessageResponse deleteBannerByBannerId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核轮播图)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-26
     */
    MessageResponse audit(String id, String rst, String remark, UserProp userProp) throws Exception;
}