package com.huacainfo.ace.portal.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.Tpl;
import com.huacainfo.ace.portal.service.TplService;
import com.huacainfo.ace.portal.vo.TplVo;
import com.huacainfo.ace.portal.vo.TplQVo;

import java.util.Map;

@Controller
@RequestMapping("/tpl")
/**
 * @author: 陈晓克
 * @version: 2018-05-04
 * @Description:  TODO(模板)
 */
public class TplController extends PortalBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TplService tplService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(模板分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<TplVo>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	@RequestMapping(value = "/findTplList.do")
	@ResponseBody
	public PageResult<TplVo> findTplList(TplQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<TplVo> rst = this.tplService
				.findTplList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
    /**
	 *
	    * @Title:insertTpl
	    * @Description:  TODO(添加模板)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	@RequestMapping(value = "/insertTpl.do")
	@ResponseBody
	public MessageResponse insertTpl(String jsons) throws Exception {
		Tpl obj = JSON.parseObject(jsons, Tpl.class);
		return this.tplService
				.insertTpl(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:updateTpl
	    * @Description:  TODO(更新模板)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	@RequestMapping(value = "/updateTpl.do")
	@ResponseBody
	public MessageResponse updateTpl(String jsons) throws Exception {
		Tpl obj = JSON.parseObject(jsons, Tpl.class);
		return this.tplService
				.updateTpl(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:selectTplByPrimaryKey
	    * @Description:  TODO(获取模板)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Tpl>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	@RequestMapping(value = "/selectTplByPrimaryKey.do")
	@ResponseBody
	public SingleResult<TplVo> selectTplByPrimaryKey(String id)
			throws Exception {
		return this.tplService.selectTplByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deleteTplByTplId
	    * @Description:  TODO(删除模板)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	@RequestMapping(value = "/deleteTplByTplId.do")
	@ResponseBody
	public MessageResponse deleteTplByTplId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.tplService.deleteTplByTplId(id,
				this.getCurUserProp());
	}


	/**
	 *
	 * @Title:getList
	 * @Description:  TODO(获取页面模板列表)
	 * @param:        @throws Exception
	 * @return:       Map<String,Object>
	 * @throws
	 * @author: 陈晓克
	 * @version: 2018-05-04
	 */
	@RequestMapping(value = "/www/getList.do")
	@ResponseBody
	public Map<String,Object> getList() throws Exception{
		return this.tplService.getList();
	}
}
