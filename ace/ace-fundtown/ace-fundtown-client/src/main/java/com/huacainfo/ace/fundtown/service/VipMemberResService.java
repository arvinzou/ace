package com.huacainfo.ace.fundtown.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fundtown.model.VipMemberRes;
import com.huacainfo.ace.fundtown.vo.VipMemberResQVo;
import com.huacainfo.ace.fundtown.vo.VipMemberResVo;

import java.util.List;

/**
 * @author: Arvin
 * @version: 2018-07-03
 * @Description: TODO(入驻成员-资源/附件)
 */
public interface VipMemberResService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(入驻成员-资源/附件分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <VipMemberResVo>
     * @author: Arvin
     * @version: 2018-07-03
     */
    PageResult<VipMemberResVo> findVipMemberResList(VipMemberResQVo condition,
                                                    int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertVipMemberRes
     * @Description: TODO(添加入驻成员-资源/附件)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-03
     */
    MessageResponse insertVipMemberRes(VipMemberRes obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateVipMemberRes
     * @Description: TODO(更新入驻成员-资源/附件)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-03
     */
    MessageResponse updateVipMemberRes(VipMemberRes obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectVipMemberResByPrimaryKey
     * @Description: TODO(获取入驻成员-资源/附件)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<VipMemberRes>
     * @author: Arvin
     * @version: 2018-07-03
     */
    SingleResult<VipMemberResVo> selectVipMemberResByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteVipMemberResByVipMemberResId
     * @Description: TODO(删除入驻成员-资源/附件)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-03
     */
    MessageResponse deleteVipMemberResByVipMemberResId(String id, UserProp userProp) throws Exception;

    List<VipMemberRes> findByDeptId(String deptId);
}
