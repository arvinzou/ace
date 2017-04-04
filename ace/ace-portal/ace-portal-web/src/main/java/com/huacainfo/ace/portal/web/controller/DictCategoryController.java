package com.huacainfo.ace.portal.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParam;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.portal.model.DictCategory;
import com.huacainfo.ace.portal.service.DictCategoryService;

@Controller
@RequestMapping("/dictCategory")
public class DictCategoryController extends PortalBaseController {
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DictCategoryService dictCategoryService;
	/**
	 * 
	    * @Title:findDictCategoryList 
	    * @Description:  TODO(字典类型分页查询) 
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       PageResult<DictCategory>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:48:28
	 */
	@RequestMapping(value = "/findDictCategoryList.do")
	@ResponseBody
	public PageResult<DictCategory> findDictCategoryList(DictCategory condition, PageParam page)
			throws Exception {
		condition.setSyid(this.getCurUserProp().getActiveSyId());
		PageResult<DictCategory> rst = this.dictCategoryService.findDictCategoryList(condition,
				page.getStart(), page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
		
		return rst;

	}
	/**
	 * 
	    * @Title:insertDictCategory 
	    * @Description:  TODO(添加字典类型) 
	 		* @param:        @param jsons
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:48:49
	 */
	@RequestMapping(value = "/insertDictCategory.do")
	@ResponseBody
	public MessageResponse insertDictCategory(String jsons) throws Exception {
		DictCategory obj = JSON.parseObject(jsons, DictCategory.class);
		MessageResponse rst = this.dictCategoryService.insertDictCategory(obj,
				this.getCurUserProp());
		return rst;
	}
	/**
	 * 
	    * @Title:updateDictCategory 
	    * @Description:  TODO(更新字典类型) 
	 		* @param:        @param jsons
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:49:09
	 */
	@RequestMapping(value = "/updateDictCategory.do")
	@ResponseBody
	public MessageResponse updateDictCategory(String jsons) throws Exception {

		DictCategory obj = JSON.parseObject(jsons, DictCategory.class);
		return this.dictCategoryService.updateDictCategory(obj, this.getCurUserProp());
	}

	
	/**
	 * 
	    * @Title:deleteDictCategoryByDictCategoryId 
	    * @Description:  TODO(删除字典类型) 
	 		* @param:        @param jsons
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:49:20
	 */
	@RequestMapping(value = "/deleteDictCategoryByDictCategoryId.do")
	@ResponseBody
	public MessageResponse deleteDictCategoryByDictCategoryId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.dictCategoryService.deleteDictCategoryByDictCategoryId(id, this.getCurUserProp());
	}
	/**
	 * 
	    * @Title:findDictCategoryListAll 
	    * @Description:  TODO(获取全部字典类型列表) 
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       List<DictCategory>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:49:37
	 */
	@RequestMapping(value = "/findDictCategoryListAll.do")
	@ResponseBody
	public List<DictCategory> findDictCategoryListAll() throws Exception {
		return this.dictCategoryService.findDictCategoryListAll();
	}

}
