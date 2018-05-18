package com.huacainfo.ace.portal.service;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.service.WebContextParamService;
import com.huacainfo.ace.portal.model.Resources;
import com.huacainfo.ace.portal.model.Users;

public interface SystemService extends WebContextParamService {
	/**
	 * 
	 * @Title:getTreeList
	 * @Description: TODO(加载系统菜单)
	 * @param: @param resources
	 * @param: @param id
	 * @param: @param loadButton
	 * @param: @return
	 * @return: List<Tree>
	 * @throws
	 * @author: chenxiaoke
	 * @version: 2016年11月17日 下午1:17:33
	 */

	public abstract List<Tree> getTreeList(List<Resources> resources,
			String id, boolean loadButton);

	/**
	 * 
	 * @Title:getResourcesByUserId
	 * @Description: TODO(根据用户编号获取拥有拥有的资源)
	 * @param: @param id
	 * @param: @return
	 * @return: List<Resources>
	 * @throws
	 * @author: chenxiaoke
	 * @version: 2016年11月17日 下午1:18:01
	 */
	public abstract List<Resources> getResourcesByUserId(String id);

	/**
	 * 
	 * @Title:getButtonAuthor
	 * @Description: TODO(获取页面拥有的按钮权限)
	 * @param: @param resources
	 * @param: @param id
	 * @param: @return
	 * @return: Map<String,String>
	 * @throws
	 * @author: chenxiaoke
	 * @version: 2016年11月17日 下午1:18:24
	 */
	public abstract Map<String, String> getButtonAuthor(
			List<Resources> resources, String id);

	/**
	 * 
	 * @Title:selectProvinceTreeList
	 * @Description: TODO(加载行政区划树)
	 * @param: @return
	 * @return: List<Tree>
	 * @throws
	 * @author: chenxiaoke
	 * @version: 2016年11月17日 下午1:21:55
	 */
	public abstract List<Tree> selectProvinceTreeList();

	/**
	 * 
	 * @Title:selectProvinceTreeList
	 * @Description: TODO(根据级别需求加载行政区划树)
	 * @param: @param pid
	 * @param: @param isRoot
	 * @param: @param level
	 * @param: @return
	 * @return: List<Tree>
	 * @throws
	 * @author: chenxiaoke
	 * @version: 2016年11月17日 下午1:22:27
	 */
	public abstract List<Tree> selectProvinceTreeList(String pid,
			boolean isRoot, int level);

	/**
	 * 
	 * @Title:updatePassword
	 * @Description: TODO(更新密码)
	 * @param: @param password
	 * @param: @param repassword
	 * @param: @param userProp
	 * @param: @return
	 * @param: @throws Exception
	 * @return: MessageResponse
	 * @throws
	 * @author: chenxiaoke
	 * @version: 2016年11月17日 下午1:23:09
	 */

	public abstract MessageResponse updatePassword(String password,
			String repassword, UserProp userProp) throws Exception;

	/**
	 * 
	 * @Title:selectDepartment
	 * @Description: TODO(机构查询，用于控件数据获取)
	 * @param: @param params
	 * @param: @return
	 * @return: Map<String,Object>
	 * @throws
	 * @author: chenxiaoke
	 * @version: 2016年11月17日 下午1:23:48
	 */
	public abstract Map<String, Object> selectDepartment(
			Map<String, String> params,String syid);

	/**
	 * 
	 * @Title:selectUsers
	 * @Description: TODO(系统用户查询，用于控件数据获取)
	 * @param: @param params
	 * @param: @return
	 * @return: Map<String,Object>
	 * @throws
	 * @author: chenxiaoke
	 * @version: 2016年11月17日 下午1:24:14
	 */
	public Map<String, Object> selectUsers(Map<String, Object> params,String syid);

	/**
	 * 
	 * @Title:retrievePassword
	 * @Description: TODO(获取重置的密码)
	 * @param: @param email
	 * @param: @return
	 * @param: @throws Exception
	 * @return: MessageResponse
	 * @throws
	 * @author: chenxiaoke
	 * @version: 2016年11月17日 下午1:24:46
	 */

	public abstract MessageResponse updateForRetrievePassword(String email)
			throws Exception;

	/**
	 * 
	 * @Title:selectDepartmentTreeList
	 * @Description: TODO(获取机构树，用于控件填充数据)
	 * @param: @param pid
	 * @param: @return
	 * @return: List<Tree>
	 * @throws
	 * @author: chenxiaoke
	 * @version: 2016年11月17日 下午1:25:15
	 */
	public abstract List<Tree> selectDepartmentTreeList(String pid,String syid);

	/**
	 * 加载系统参数，用于系统初始化
	 */

	public Map<String, String> loadConfig(String syid);

	/**
	 * 
	 * @Title:selectDepAndUsersTreeList
	 * @Description: TODO(获取机构与用户的树形数据，用于控件填充)
	 * @param: @param pid
	 * @param: @return
	 * @return: List<Tree>
	 * @throws
	 * @author: chenxiaoke
	 * @version: 2016年11月17日 下午1:26:09
	 */

	public abstract List<Tree> selectDepAndUsersTreeList(String pid);

	/**
	 * 
	 * @Title:selectUsersByAccount
	 * @Description: TODO(根据账号获取用户信息)
	 * @param: @param account
	 * @param: @return
	 * @return: Users
	 * @throws
	 * @author: chenxiaoke
	 * @version: 2016年11月17日 下午1:26:44
	 */
	public abstract Users selectUsersByAccount(String account);

	/**
	 * 
	 * @Title:selectRoleListByUserId
	 * @Description: TODO(获取用户拥有的角色列表)
	 * @param: @param userId
	 * @param: @return
	 * @return: List<Map<String,String>>
	 * @throws
	 * @author: chenxiaoke
	 * @version: 2016年11月17日 下午1:27:02
	 */
	public abstract List<Map<String, String>> selectRoleListByUserId(
			String userId);

	/**
	 * 
	 * @Title:selectRoleTypeListByUserId
	 * @Description: TODO(特殊业务，获取角色类型)
	 * @param: @param userId
	 * @param: @return
	 * @return: List<String>
	 * @throws
	 * @author: chenxiaoke
	 * @version: 2016年11月17日 下午1:27:24
	 */
	public abstract List<String> selectRoleTypeListByUserId(String userId);

	/**
	 * 发送验证码
	 * 
	 * @param email
	 * @return MessageResponse
	 */
	public abstract MessageResponse sendEmailCode(String email);

	/**
	 * 修改邮箱
	 * 
	 * @param email
	 * @param userProp
	 * @return MessageResponse
	 */
	public abstract MessageResponse updateEmail(String email, UserProp userProp);

	public abstract String selectProvinceNameById(String regPro);

	public abstract String[] selectSyidByUserId(String userId);

    public MessageResponse updateCurSyid(String syid, String userId);

	public Map<String,Object> selectUserCfgByUserId(String userId);

	public Map<String, Object> selectUserinfo(Map<String,Object> params)  throws Exception;

}
