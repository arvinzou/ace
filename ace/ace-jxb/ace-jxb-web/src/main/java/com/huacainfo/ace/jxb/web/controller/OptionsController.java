package com.huacainfo.ace.jxb.web.controller;

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
import com.huacainfo.ace.jxb.model.Options;
import com.huacainfo.ace.jxb.service.OptionsService;
import com.huacainfo.ace.jxb.vo.OptionsVo;
import com.huacainfo.ace.jxb.vo.OptionsQVo;

@Controller
@RequestMapping("/options")
/**
 * @author: lcan
 * @version: 2018-04-08
 * @Description:  TODO(选项)
 */
public class OptionsController extends LiveBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private OptionsService optionsService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(选项分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<OptionsVo>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/findOptionsList")
	@ResponseBody
	public PageResult<OptionsVo> findOptionsList(OptionsQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<OptionsVo> rst = this.optionsService
				.findOptionsList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
    /**
	 *
	    * @Title:insertOptions
	    * @Description:  TODO(添加选项)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/insertOptions")
	@ResponseBody
	public MessageResponse insertOptions(String jsons) throws Exception {
		Options obj = JSON.parseObject(jsons, Options.class);
		return this.optionsService
				.insertOptions(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:updateOptions
	    * @Description:  TODO(更新选项)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/updateOptions")
	@ResponseBody
	public MessageResponse updateOptions(String jsons) throws Exception {
		Options obj = JSON.parseObject(jsons, Options.class);
		return this.optionsService
				.updateOptions(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:selectOptionsByPrimaryKey
	    * @Description:  TODO(获取选项)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Options>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/selectOptionsByPrimaryKey")
	@ResponseBody
	public SingleResult<OptionsVo> selectOptionsByPrimaryKey(String id)
			throws Exception {
		return this.optionsService.selectOptionsByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deleteOptionsByOptionsId
	    * @Description:  TODO(删除选项)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/deleteOptionsByOptionsId")
	@ResponseBody
	public MessageResponse deleteOptionsByOptionsId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.optionsService.deleteOptionsByOptionsId(id,
				this.getCurUserProp());
	}
}
