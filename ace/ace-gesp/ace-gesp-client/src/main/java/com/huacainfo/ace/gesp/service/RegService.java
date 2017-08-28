package com.huacainfo.ace.gesp.service;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.gesp.model.Department;
import com.huacainfo.ace.gesp.model.Users;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.SingleResult;

public interface RegService {
	
	public  MessageResponse insertDepartment(Department obj, UserProp userProp) throws Exception;
	
	public  SingleResult<String> getCheckCode(Map<String,Object> model) throws Exception;
	
	public  MessageResponse sendRegMail(String account, UserProp userProp) throws Exception;
	
	public  MessageResponse updateActivateBySeat(String seat, UserProp userProp) throws Exception;

	public MessageResponse updateByPrimaryKey(Department obj, UserProp userProp)throws Exception ;

	/**
	 * 根据主键查询
	 * @param deptId
	 * @param string 
	 * @return SingleResult<Department>
	 */
	public SingleResult<Department> selectDepartInfoByPrimaryKey(String corpId);

	/**
	 * 新增企业
	 * 
	 * @param obj
	 * @param curUserProp
	 * @param list 
	 * @return MessageResponse
	 */
	public MessageResponse insert(Department obj, UserProp curUserProp, List<Users> list) throws Exception;

	/**
	 * 添加企业联系人
	 * 
	 * @param obj
	 * @param curUserProp
	 * @return MessageResponse
	 * @throws Exception
	 */
	public MessageResponse insertUsers(Users obj, UserProp curUserProp) throws Exception;

  
 
	public Map<String, Object> selectDicBy(String period, String string) throws Exception;

	/**
	 * 将爬取到的企业信息添加到企业信息表中(默认审核通过)
	 * 
	 * 引用(企业注册)
	 * @param obj
	 * @param curUserProp
	 * @return MessageResponse
	 */
	public MessageResponse insertRegDepartment(Department obj, UserProp curUserProp) throws Exception;
	
	/**
	 * 判断账号是否唯一
	 * 
	 * 引用(企业注册)
	 * @param account 账号
	 * @return MessageResponse
	 */
	public abstract MessageResponse isExitAccount(String account)throws Exception;

	/**
	 * 王龙,获取并保存网络抓取的信息
	 * @param name
	 * @return
	 */
	public MessageResponse getSiteCompanyInfo(String name);
 
}
