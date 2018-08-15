package com.huacainfo.ace.portal.web.controller;
import java.util.List;
import java.util.Map;

import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.model.EvaluatGauge;
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
import com.huacainfo.ace.portal.model.EvaluatTpl;
import com.huacainfo.ace.portal.service.EvaluatTplService;
import com.huacainfo.ace.portal.vo.EvaluatTplVo;
import com.huacainfo.ace.portal.vo.EvaluatTplQVo;

@Controller
@RequestMapping("/evaluatTpl")
/**
 * @author: 陈晓克
 * @version: 2018-06-09
 * @Description:  TODO(评测)
 */
public class EvaluatTplController extends PortalBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private EvaluatTplService evaluatTplService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(评测分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<EvaluatTplVo>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	@RequestMapping(value = "/findEvaluatTplList.do")
	@ResponseBody
	public PageResult<EvaluatTplVo> findEvaluatTplList(EvaluatTplQVo condition,
													   PageParamNoChangeSord page) throws Exception {
		condition.setSyid(this.getCurUserProp().getActiveSyId());
		PageResult<EvaluatTplVo> rst = this.evaluatTplService.findEvaluatTplList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}

		return rst;
	}

	@RequestMapping(value = "/findEvaluatTplListVo.do")
	@ResponseBody
	public PageResult<EvaluatTplVo> findEvaluatTplListVo(EvaluatTplQVo condition, PageParamNoChangeSord page) throws Exception {
		condition.setSyid(this.getCurUserProp().getActiveSyId());
		PageResult<EvaluatTplVo> rst = this.evaluatTplService.findEvaluatTplListVo(condition, page.getPage(), page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
		return rst;
	}
    /**
	 *
	    * @Title:insertEvaluatTpl
	    * @Description:  TODO(添加评测)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	@RequestMapping(value = "/insertEvaluatTpl.do")
	@ResponseBody
	public MessageResponse insertEvaluatTpl(String jsons) throws Exception {
        return this.evaluatTplService.insertEvaluatTpl(jsons, this.getCurUserProp());
    }


	@RequestMapping(value = "/insertEvaluatTplVo.do")
	@ResponseBody
	public MessageResponse insertEvaluatTplVo(String jsons) throws Exception {

		JSONObject jsonObj = JSON.parseObject(jsons);
		EvaluatTpl obj = JSON.parseObject(jsonObj.getString("evaluatTpl"), EvaluatTpl.class);
		List<EvaluatGauge> lists = JSON.parseArray(jsonObj.getString("evaluatGauge"), EvaluatGauge.class);
		return this.evaluatTplService.insertEvaluatTplVo(obj, lists, this.getCurUserProp());
	}

    /**
	 *
	    * @Title:updateEvaluatTpl
	    * @Description:  TODO(更新评测)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	@RequestMapping(value = "/updateEvaluatTpl.do")
	@ResponseBody
	public MessageResponse updateEvaluatTpl(String jsons) throws Exception {
        return this.evaluatTplService.updateEvaluatTpl(jsons, this.getCurUserProp());
    }


	/**
	 * @throws
	 * @Title:updateEvaluatTpl
	 * @Description: TODO(更新评测)
	 * @param: @param jsons
	 * @param: @throws Exception
	 * @return: MessageResponse
	 * @author: 陈晓克
	 * @version: 2018-06-09
	 */
	@RequestMapping(value = "/updateEvaluatTplVo.do")
	@ResponseBody
	public MessageResponse updateEvaluatTplVo(String jsons) throws Exception {
		JSONObject jsonObj = JSON.parseObject(jsons);
		EvaluatTpl obj = JSON.parseObject(jsonObj.getString("evaluatTpl"), EvaluatTpl.class);
		List<EvaluatGauge> lists = JSON.parseArray(jsonObj.getString("evaluatGauge"), EvaluatGauge.class);
		return this.evaluatTplService.updateEvaluatTplVo(obj, lists, this.getCurUserProp());
	}





    /**
	 *
	    * @Title:selectEvaluatTplByPrimaryKey
	    * @Description:  TODO(获取评测)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<EvaluatTpl>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	@RequestMapping(value = "/selectEvaluatTplByPrimaryKey.do")
	@ResponseBody
	public SingleResult<EvaluatTplVo> selectEvaluatTplByPrimaryKey(String id)
			throws Exception {
		return this.evaluatTplService.selectEvaluatTplByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deleteEvaluatTplByEvaluatTplId
	    * @Description:  TODO(删除评测)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	@RequestMapping(value = "/deleteEvaluatTplByEvaluatTplId.do")
	@ResponseBody
	public MessageResponse deleteEvaluatTplByEvaluatTplId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.evaluatTplService.deleteEvaluatTplByEvaluatTplId(id,
				this.getCurUserProp());
	}

	@RequestMapping(value = "/deleteEvaluatTpl.do")
	@ResponseBody
	public MessageResponse deleteEvaluatTpl(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.evaluatTplService.deleteEvaluatTpl(id, this.getCurUserProp());
	}

	/**
     *
     * @Title:getList
     * @Description:  TODO(获取评测列表)
     * @param:        @throws Exception
     * @return:       Map<String,Object>
     * @throws
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @RequestMapping(value = "/getList.do")
    @ResponseBody
    public Map<String,Object> getList() throws Exception{
        Map<String,Object> params=this.getParams();
        params.put("userId",this.getCurUserProp().getUserId());
        return this.evaluatTplService.getList(params);
    }

    /**
     *
     * @Title:getById
     * @Description:  TODO(获取评测列表)
     * @param:        @throws Exception
     * @return:       Map<String,Object>
     * @throws
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @RequestMapping(value = "/getById.do")
    @ResponseBody
    public Map<String,Object> getById(String id) throws Exception{
        return this.evaluatTplService.getById(id);
    }

    /**
     * @throws
     * @Title:getDictTreeList
     * @Description: TODO(获取字典树)
     * @param: @param id
     * @param: @return
     * @param: @throws Exception
     * @return: List<Tree>
     * @author: chenxiaoke
     * @version: 2016年11月17日 下午1:54:10
     */
    @RequestMapping(value = "/getEvaluatTplTreeList.do")
    @ResponseBody
    public List<Tree> getDictTreeList(String id) throws Exception {
        if (CommonUtils.isBlank(id)) {
            id = "0";
        }
        logger.info("=================getDictTreeList===>{}", id);
        List<Tree> list = this.evaluatTplService.selectEvaluatTplTreeList(id, this.getCurUserProp().getActiveSyId());
        return list;
    }

}
