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
import com.huacainfo.ace.portal.model.TplPage;
import com.huacainfo.ace.portal.service.TplPageService;
import com.huacainfo.ace.portal.vo.TplPageVo;
import com.huacainfo.ace.portal.vo.TplPageQVo;

@Controller
@RequestMapping("/tplPage")
/**
 * @author: 陈晓克
 * @version: 2018-05-04
 * @Description:  TODO(页面)
 */
public class TplPageController extends PortalBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TplPageService tplPageService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(页面分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<TplPageVo>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	@RequestMapping(value = "/findTplPageList.do")
	@ResponseBody
	public PageResult<TplPageVo> findTplPageList(TplPageQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<TplPageVo> rst = this.tplPageService
				.findTplPageList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
    /**
	 *
	    * @Title:insertTplPage
	    * @Description:  TODO(添加页面)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	@RequestMapping(value = "/insertTplPage.do")
	@ResponseBody
	public MessageResponse insertTplPage(String jsons) throws Exception {
		TplPage obj = JSON.parseObject(jsons, TplPage.class);
		return this.tplPageService
				.insertTplPage(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:updateTplPage
	    * @Description:  TODO(更新页面)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	@RequestMapping(value = "/updateTplPage.do")
	@ResponseBody
	public MessageResponse updateTplPage(String jsons) throws Exception {
		TplPage obj = JSON.parseObject(jsons, TplPage.class);
		return this.tplPageService
				.updateTplPage(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:selectTplPageByPrimaryKey
	    * @Description:  TODO(获取页面)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<TplPage>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	@RequestMapping(value = "/selectTplPageByPrimaryKey.do")
	@ResponseBody
	public SingleResult<TplPageVo> selectTplPageByPrimaryKey(String id)
			throws Exception {
		return this.tplPageService.selectTplPageByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deleteTplPageByTplPageId
	    * @Description:  TODO(删除页面)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	@RequestMapping(value = "/deleteTplPageByTplPageId.do")
	@ResponseBody
	public MessageResponse deleteTplPageByTplPageId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.tplPageService.deleteTplPageByTplPageId(id,
				this.getCurUserProp());
	}
}
