package com.huacainfo.ace.jxb.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.MemberQrcode;
import com.huacainfo.ace.jxb.vo.MemberQrcodeQVo;
import com.huacainfo.ace.jxb.vo.MemberQrcodeVo;

/**
 * @author: Arvin
 * @version: 2018-07-23
 * @Description: TODO(会员二维码表)
 */
public interface MemberQrcodeService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(会员二维码表分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <MemberQrcodeVo>
     * @author: Arvin
     * @version: 2018-07-23
     */
    PageResult<MemberQrcodeVo> findMemberQrcodeList(MemberQrcodeQVo condition,
                                                    int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertMemberQrcode
     * @Description: TODO(添加会员二维码表)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-23
     */
    MessageResponse insertMemberQrcode(MemberQrcode obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateMemberQrcode
     * @Description: TODO(更新会员二维码表)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-23
     */
    MessageResponse updateMemberQrcode(MemberQrcode obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectMemberQrcodeByPrimaryKey
     * @Description: TODO(获取会员二维码表)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<MemberQrcode>
     * @author: Arvin
     * @version: 2018-07-23
     */
    SingleResult<MemberQrcodeVo> selectMemberQrcodeByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteMemberQrcodeByMemberQrcodeId
     * @Description: TODO(删除会员二维码表)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-23
     */
    MessageResponse deleteMemberQrcodeByMemberQrcodeId(String id, UserProp userProp) throws Exception;

    /**
     * 获取咨询师推广二维码
     *
     * @param studioId 工作室ID
     * @param type     二维码时效类型 0-临时 1-永久
     * @param refresh  强制刷新条件 0-正常获取 1 - 强制刷新
     * @return ResultResponse
     */
    ResultResponse getQRCode(String studioId, String type, String refresh) throws Exception;

    /**
     * 获取工作室推广二维码 -- 绘制方式
     *
     * @param studioId 工作室ID
     * @param type     二维码时效类型 0-临时 1-永久
     * @param refresh  强制刷新条件 0-正常获取 1 - 强制刷新
     * @return ResultResponse
     */
    ResultResponse drawQRCode(String studioId, String type, String refresh) throws Exception;
}
