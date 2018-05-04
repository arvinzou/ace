package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.Role;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.vo.UsersVo;

import java.util.List;
import java.util.Map;

public interface UsersService {
    /**
     * @throws
     * @Title:findUsersSearchList
     * @Description: TODO(系统拥有搜索)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<Map<String,String>>
     * @author: chenxiaoke
     * @version: 2016年11月17日 下午1:29:18
     */
    public abstract PageResult<Map<String, String>> findUsersSearchList(Users condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:findUsersList
     * @Description: TODO(用户分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<UsersVo>
     * @author: chenxiaoke
     * @version: 2016年11月17日 下午1:29:32
     */
    public abstract PageResult<UsersVo> findUsersList(Users condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertUsers
     * @Description: TODO(添加用户)
     * @param: @param obj
     * @param: @param userProp
     * @param: @return
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: chenxiaoke
     * @version: 2016年11月17日 下午1:29:50
     */
    public abstract MessageResponse insertUsers(Users obj, UserProp userProp, String flag) throws Exception;

    /**
     * @param flag
     * @throws
     * @Title:updateUsers
     * @Description: TODO(更新用户)
     * @param: @param obj
     * @param: @param userProp
     * @param: @return
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: chenxiaoke
     * @version: 2016年11月17日 下午1:30:04
     */
    public abstract MessageResponse updateUsers(Users obj, UserProp userProp, String flag) throws Exception;

    /**
     * @throws
     * @Title:updateUsersStautsByPrimaryKey
     * @Description: TODO(更新用户状态)
     * @param: @param usersId
     * @param: @param struts
     * @param: @param userProp
     * @param: @return
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: chenxiaoke
     * @version: 2016年11月17日 下午1:30:18
     */
    public abstract MessageResponse updateUsersStautsByPrimaryKey(String usersId, String struts, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectUsersByPrimaryKey
     * @Description: TODO(获取用户信息)
     * @param: @param usersId
     * @param: @return
     * @param: @throws Exception
     * @return: SingleResult<UsersVo>
     * @author: chenxiaoke
     * @version: 2016年11月17日 下午1:30:32
     */
    public abstract SingleResult<UsersVo> selectUsersByPrimaryKey(String usersId) throws Exception;

    /**
     * @throws
     * @Title:updateUsersForInitPassword
     * @Description: TODO(初始化用户密码)
     * @param: @param usersId
     * @param: @param password
     * @param: @param userProp
     * @param: @return
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: chenxiaoke
     * @version: 2016年11月17日 下午1:31:31
     */
    public abstract MessageResponse updateUsersForInitPassword(String usersId, String password, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:insertUsersRole
     * @Description: TODO(用户分配角色)
     * @param: @param userId
     * @param: @param roleId
     * @param: @param userProp
     * @param: @return
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: chenxiaoke
     * @version: 2016年11月17日 下午1:31:50
     */
    public abstract MessageResponse insertUsersRole(String userId, String[] roleId, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectRoleList
     * @Description: TODO(获取系统所有角色列表)
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<Role>
     * @author: chenxiaoke
     * @version: 2016年11月17日 下午1:32:15
     */
    public abstract PageResult<Role> selectRoleList(UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectRoleListByUserId
     * @Description: TODO(获取用户已分配角色列表)
     * @param: @param userId
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<Role>
     * @author: chenxiaoke
     * @version: 2016年11月17日 下午1:32:34
     */
    public abstract PageResult<Role> selectRoleListByUserId(String userId) throws Exception;

    /**
     * 删除联系人信息
     *
     * @param id
     * @param userProp
     * @return MessageResponse
     * @version: 2017年02月28日 下午16:47
     */
    public abstract MessageResponse deleteUsers(String id, UserProp userProp);


    /**
     * @throws
     * @Title:updateUsersById
     * @Description: TODO(修改企业联系人信息)
     * @param: @param jsons
     * @param: @return
     * @param: @throws Exception
     * @return: MessageResponse
     * @version: 2017年02月28日 下午2:43:34
     */
    public abstract MessageResponse updateUsersById(Users obj, UserProp curUserProp);

    /**
     * 根据企业编号查询所有联系人
     *
     * @param condition
     * @param start
     * @param limit
     * @param orderBy
     * @return PageResult<Map<String,String>>
     */
    public abstract PageResult<Map<String, String>> findDeIdByUsersList(Users condition, int start, int limit,
                                                                        String orderBy);

    /**
     * 更新联系人的状态
     *
     * @param id
     * @param curUserProp
     * @return
     */
    public abstract MessageResponse deleteConUsers(String id, UserProp curUserProp) throws Exception;

    /**
     * @param list
     * @param userProp
     * @return
     * @throws Exception
     * @Description:员工数据导入
     */
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception;

    public abstract MessageResponse insertReg(Users obj) throws Exception;


    /**
     * @throws
     * @Title:deleteRoleById
     * @Description: TODO(删除微信用户的角色)
     * @param: @param id
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-02-04
     */
    public abstract MessageResponse deleteOpenIdById(String userId, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateRoleById
     * @Description: TODO(更新微信用户的角色)
     * @param: @param id
     * @param: @param role
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-02-04
     */
    public abstract MessageResponse updateOpenIdById(String userId, String openId, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectWxUser
     * @Description: TODO(组合查询微信用户)
     * @param: @param id
     * @param: @param role
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-02-04
     */
    List<Map<String, Object>> selectWxUser(Map<String, Object> condition) throws Exception;

    /**
     * @throws
     * @Title:updateUserAppOpenId
     * @Description: TODO(绑定小程序用户)
     * @param: @param userId
     * @param: @param appOpenId
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-04-25
     */
    public MessageResponse updateUserAppOpenId(String userId, String appOpenId, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectAppWxUser
     * @Description: TODO(查询已绑定的小程序用户)
     * @param: @param Map<String,Object> userId
     * @param: @throws Exception
     * @return: List<Map<String,Object>>
     * @author: 陈晓克
     * @version: 2018-04-25
     */
    public List<Map<String, Object>> selectAppWxUser(Map<String, Object> condition) throws Exception;

    /**
     * @throws
     * @Title:selectAllAppWxUserList
     * @Description: TODO(查询appId下所有小程序用户)
     * @param: @param userProp
     * @param: @throws Exception
     * @return: List<Map<String,Object>>
     * @author: 陈晓克
     * @version: 2018-04-25
     */
    public List<Map<String, Object>> selectAllAppWxUserList(Map<String, Object> p) throws Exception;

    /**
     * @throws
     * @Title:selectAllWxUserList
     * @Description: TODO(查询appId下所有公众号用户)
     * @param: @param userProp
     * @param: @throws Exception
     * @return: List<Map<String,Object>>
     * @author: 陈晓克
     * @version: 2018-04-25
     */
    public List<Map<String, Object>> selectAllWxUserList(Map<String, Object> p) throws Exception;


    /**
     * @throws
     * @Title:deleteAppOpenIdById
     * @Description: TODO(删除微信用户的角色)
     * @param: @param id
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-04-26
     */
    public abstract MessageResponse deleteAppOpenIdById(String userId, UserProp userProp) throws Exception;

    /**
     * 新增系统用户
     *
     * @param users    用户信息
     * @param userProp 操作用户信息
     * @param logTag   日志标记
     * @return
     * @throws Exception
     */
    ResultResponse insertUsersRecord(Users users, UserProp userProp, String logTag) throws Exception;

}
