package com.huacainfo.ace.portal.service;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.service.WebContextDictService;
import com.huacainfo.ace.portal.model.Dict;
import com.huacainfo.ace.portal.vo.DictVo;

public interface DictService extends WebContextDictService {
	/**
	 * 
	 */
	public abstract Map<String, List<Map<String, Object>>> flushJavaScriptFile(String syid);

	/**
	 * 
	 * @Title:findDictList
	 * @Description: TODO(静态字典分页查询)
	 * @param: @param condition
	 * @param: @param start
	 * @param: @param limit
	 * @param: @param orderBy
	 * @param: @return
	 * @param: @throws Exception
	 * @return: PageResult<DictVo>
	 * @throws
	 * @author: chenxiaoke
	 * @version: 2016年11月17日 上午11:25:51
	 */
	public abstract PageResult<DictVo> findDictList(Dict condition, int start,
			int limit, String orderBy) throws Exception;

	/**
	 * 
	 * @Title:insertDict
	 * @Description: TODO(添加静态字典)
	 * @param: @param obj
	 * @param: @param userProp
	 * @param: @return
	 * @param: @throws Exception
	 * @return: MessageResponse
	 * @throws
	 * @author: chenxiaoke
	 * @version: 2016年11月17日 上午11:27:05
	 */
	public abstract MessageResponse insertDict(Dict obj, UserProp userProp)
			throws Exception;

	/**
	 * 
	 * @Title:updateDict
	 * @Description: TODO(更新静态字典)
	 * @param: @param obj
	 * @param: @param userProp
	 * @param: @return
	 * @param: @throws Exception
	 * @return: MessageResponse
	 * @throws
	 * @author: chenxiaoke
	 * @version: 2016年11月17日 上午11:27:25
	 */
	public abstract MessageResponse updateDict(Dict obj, UserProp userProp)
			throws Exception;

	/**
	 * 
	 * @Title:selectDictByPrimaryKey
	 * @Description: TODO(获取静态字典)
	 * @param: @param id
	 * @param: @return
	 * @param: @throws Exception
	 * @return: SingleResult<Dict>
	 * @throws
	 * @author: chenxiaoke
	 * @version: 2016年11月17日 上午11:29:27
	 */
	public abstract SingleResult<Dict> selectDictByPrimaryKey(int id)
			throws Exception;

	/**
	 * 
	 * @Title:deleteDictByDictId
	 * @Description: TODO(删除静态字典)
	 * @param: @param id
	 * @param: @param userProp
	 * @param: @return
	 * @param: @throws Exception
	 * @return: MessageResponse
	 * @throws
	 * @author: chenxiaoke
	 * @version: 2016年11月17日 上午11:29:47
	 */
	public abstract MessageResponse deleteDictByDictId(int id, UserProp userProp)
			throws Exception;

	/**
	 * 
	 * @Title:findListByCategoryId
	 * @Description: TODO(更具类别获取静态字典列表)
	 * @param: @param categoryId
	 * @param: @param selected
	 * @param: @param params
	 * @param: @return
	 * @param: @throws Exception
	 * @return: List<Dict>
	 * @throws
	 * @author: chenxiaoke
	 * @version: 2016年11月17日 上午11:30:08
	 */
	public abstract List<Dict> findListByCategoryId(String categoryId,
			String selected, Map<String, Object> params) throws Exception;

	/**
	 * 
	 * @Title:selectDictTreeList
	 * @Description: TODO(获取静态字典树对象)
	 * @param: @param pid
	 * @param: @return
	 * @param: @throws Exception
	 * @return: List<Tree>
	 * @throws
	 * @author: chenxiaoke
	 * @version: 2016年11月17日 上午11:31:10
	 */
	public abstract List<Tree> selectDictTreeList(String pid) throws Exception;
	
	public List<Map<String,String>> selectSyidBydc() throws Exception;

	public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp)
			throws Exception;

}
