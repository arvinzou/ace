package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.WxAdmin;
import com.huacainfo.ace.portal.vo.WxAdminVo;
import com.huacainfo.ace.portal.vo.WxAdminQVo;
import java.util.Map;
import java.util.List;
/**
 * @author: 陈晓克
 * @version: 2018-11-26
 * @Description: TODO(管理员列表)
 */
public interface WxAdminService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(管理员列表分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <WxAdminVo>
     * @author: 陈晓克
     * @version: 2018-11-26
     */
    PageResult<WxAdminVo> findWxAdminList(WxAdminQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertWxAdmin
     * @Description: TODO(添加管理员列表)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-11-26
     */
    MessageResponse insertWxAdmin(WxAdmin obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateWxAdmin
     * @Description: TODO(更新管理员列表)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-11-26
     */
    MessageResponse updateWxAdmin(WxAdmin obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectWxAdminByPrimaryKey
     * @Description: TODO(获取管理员列表)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<WxAdmin>
     * @author: 陈晓克
     * @version: 2018-11-26
     */
    SingleResult<WxAdminVo> selectWxAdminByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteWxAdminByWxAdminId
     * @Description: TODO(删除管理员列表)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-11-26
     */
    MessageResponse deleteWxAdminByWxAdminId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(查询微信用户)
     * @param: @param nickname
     * @param: @throws Exception
     * @return: List<Map<String,Object>>
     * @author: 陈晓克
     * @version: 2018-11-26
     */

    List<Map<String,Object>> selectList(String nickname) throws Exception;
}
