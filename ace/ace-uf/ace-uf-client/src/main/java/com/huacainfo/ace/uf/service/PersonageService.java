package com.huacainfo.ace.uf.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.common.model.view.CheckTree;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.uf.model.*;
import com.huacainfo.ace.uf.vo.PersonageVo;
import com.huacainfo.ace.uf.vo.PersonageQVo;

import java.util.List;
import java.util.Map;

public interface PersonageService {
	/**
	 * 【奖惩信息】添加统战人士的奖惩信息
	 * @param obj
	 * @param userProp
	 */
	//public abstract MessageResponse insertPerJc(PerJiangCheng obj,UserProp userProp);

	/**
	 * 【奖惩信息】删除统战人士的一条奖惩信息
	 * @param id
	 * @param userProp
	 * @return
	 */
	//public abstract MessageResponse deletePerJCById(String id,UserProp userProp);

	/**
	 * 【奖惩信息】更新统战人士的奖惩信息
	 * @param obj
	 * @param userProp
	 * @return
	 */
	//public  abstract MessageResponse updatePerJC(PerJiangCheng obj,UserProp userProp);
	/**
	 * 【奖惩信息】获取统战人士的奖惩信息
	 * @param id
	 * @return
	 */
	//public abstract  List<PerJiangCheng> selectPerJCById(String id);

/**************************************************************************************************************************/

	/**
	 * 【社会职务】添加统战人士的社会职务
	 * @param obj
	 * @param userProp
	 */
	//public abstract MessageResponse insertPerZw(PerZhiWu obj, UserProp userProp);

	/**
	 * 【社会职务】删除统战人士的一条社会职务
	 * @param id
	 * @param userProp
	 * @return
	 */
	//public abstract MessageResponse deletePerZwById(String id,UserProp userProp);

	/**
	 * 【社会职务】更新统战人士的社会职务
	 * @param obj
	 * @param userProp
	 * @return
	 */
	//public  abstract MessageResponse updatePerZwById(PerZhiWu obj,UserProp userProp);
	/**
	 * 【社会职务】获取统战人士的社会职务
	 * @param id
	 * @return
	 */
	//public abstract  List<PerZhiWu> selectPerZwById(String id);

/**************************************************************************************************************************/
	/**
	 * 【人士类型】添加统战人士的人士类型
	 * @param obj
	 * @param userProp
	 */
	//public abstract MessageResponse insertPerCategory(PerCategory obj, UserProp userProp);

	/**
	 * 【人士类型】删除统战人士的一条人士类型
	 * @param id
	 * @param userProp
	 * @return
	 */
	//public abstract MessageResponse deletePerCategoryById(String id,UserProp userProp);

	/**
	 * 【人士类型】更新统战人士的人士类型
	 * @param obj
	 * @param userProp
	 * @return
	 */
	//public  abstract MessageResponse updatePerCategoryById(PerCategory obj,UserProp userProp);
	/**
	 * 【人士类型】获取统战人士的人士类型
	 * @param id
	 * @return
	 */
	//public abstract  List<PerCategory> selectPerCategoryById(String id);

/**************************************************************************************************************************/

	/**
	 * 【兴趣爱好】添加统战人士的兴趣爱好
	 * @param obj
	 * @param userProp
	 */
	//public abstract MessageResponse insertInterest(interest obj, UserProp userProp);

	/**
	 * 【兴趣爱好】删除统战人士的一条兴趣爱好
	 * @param id
	 * @param userProp
	 * @return
	 */
	//public abstract MessageResponse deleteInterestById(String id,UserProp userProp);

	/**
	 * 【兴趣爱好】更新统战人士的兴趣爱好
	 * @param obj
	 * @param userProp
	 * @return
	 */
	//public  abstract MessageResponse updateInterestById(interest obj, UserProp userProp);
	/**
	 * 【兴趣爱好】获取统战人士的兴趣爱好
	 * @param id
	 * @return
	 */
	//public abstract  List<interest> selectInterestById(String id);





	public abstract PageResult<PersonageVo> findPersonageList(PersonageQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertPersonage(Personage obj,UserProp userProp) throws Exception;
	public abstract MessageResponse updatePersonage(Personage obj,UserProp userProp) throws Exception;
	public abstract SingleResult<PersonageVo> selectPersonageByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deletePersonageByPersonageId(String id,UserProp userProp) throws Exception;
	public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp)
			throws Exception;
	public List<Map<String,Object>> selectPersonageListMap(WxUser user,String longitude,String latitude,String q);

	public  List<Map<String,Object>> selectPersonageList(String q, WxUser user) throws Exception;

	Map<String,Object> selectPersonageById(String id);

	public Map<String, Object> selectPersonage(String q);

	public  List<Tree>  selectPersonageTreeList(String q,WxUser user) throws Exception;
	public  List<CheckTree>  selectPersonageCheckTreeList() throws Exception;

	public int isExitPersonageByMobile(String mobile);

	public Map<String, Object> selectPersonageCfgById(String id);
}
