package com.huacainfo.ace.society.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.result.*;
import com.huacainfo.ace.society.model.PersonInfo;
import com.huacainfo.ace.society.vo.PersonInfoQVo;
import com.huacainfo.ace.society.vo.PersonInfoVo;

import java.util.List;
import java.util.Map;

/**
 * @author: Arvin
 * @version: 2018-09-11
 * @Description: TODO(个人信息)
 */
public interface PersonInfoService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(个人信息分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<PersonInfoVo>
     * @author: Arvin
     * @version: 2018-09-11
     */
    PageResult<PersonInfoVo> findPersonInfoList(PersonInfoQVo condition,
                                                int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertPersonInfo
     * @Description: TODO(添加个人信息)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-11
     */
    MessageResponse insertPersonInfo(PersonInfo obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updatePersonInfo
     * @Description: TODO(更新个人信息)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-11
     */
    MessageResponse updatePersonInfo(PersonInfo obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectPersonInfoByPrimaryKey
     * @Description: TODO(获取个人信息)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<PersonInfo>
     * @author: Arvin
     * @version: 2018-09-11
     */
    SingleResult<PersonInfoVo> selectPersonInfoByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deletePersonInfoByPersonInfoId
     * @Description: TODO(删除个人信息)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-11
     */
    MessageResponse deletePersonInfoByPersonInfoId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核个人信息)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-11
     */
    MessageResponse audit(String id, String rst, String remark, UserProp userProp) throws Exception;

    /**
     * 个人信息注册
     *
     * @param personInfo 个人信息
     * @param userinfo   微信信息
     * @return ResultResponse
     */
    ResultResponse register(PersonInfo personInfo, Userinfo userinfo);

    /**
     * 组织爱心币排行
     *
     * @param condition 查询条件
     * @return List<Map   <   String   ,       Object>>
     */
    List<Map<String, Object>> querysocietyOrg(Map<String, Object> condition);

    /**
     * 个人爱心币排行
     *
     * @param condition 查询条件
     * @return List<Map   <   String   ,       Object>>
     */
    List<Map<String, Object>> queryperson(Map<String, Object> condition);

}
