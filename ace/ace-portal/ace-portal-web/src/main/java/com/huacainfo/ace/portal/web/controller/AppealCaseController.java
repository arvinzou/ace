package com.huacainfo.ace.portal.web.controller;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.model.AppealCaseFile;
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
import com.huacainfo.ace.portal.model.AppealCase;
import com.huacainfo.ace.portal.service.AppealCaseService;
import com.huacainfo.ace.portal.vo.AppealCaseVo;
import com.huacainfo.ace.portal.vo.AppealCaseQVo;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/www/appealCase")
/**
 * @author: 陈晓克
 * @version: 2018-05-14
 * @Description:  TODO(诉求)
 */
public class AppealCaseController extends PortalBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AppealCaseService appealCaseService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(诉求分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<AppealCaseVo>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-14
	 */
	@RequestMapping(value = "/findAppealCaseList.do")
	@ResponseBody
	public PageResult<AppealCaseVo> findAppealCaseList(AppealCaseQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<AppealCaseVo> rst = this.appealCaseService
				.findAppealCaseList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
    /**
	 *
	    * @Title:insertAppealCase
	    * @Description:  TODO(添加诉求)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-14
	 */
	@RequestMapping(value = "/insertAppealCase.do")
	@ResponseBody
	public MessageResponse insertAppealCase(String jsons) throws Exception {
		JSONObject o=JSON.parseObject(jsons);
		AppealCase obj = JSON.parseObject(o.get("o").toString(), AppealCase.class);
		obj.setSubmitOpenId(this.getCurWxUser().getOpenId());
		obj.setLatitude(this.getCurWxUser().getLatitude());
		obj.setLongitude(this.getCurWxUser().getLongitude());
		List<AppealCaseFile> list=JSON.parseArray(o.get("list").toString(),AppealCaseFile.class);
		return this.appealCaseService
				.insertAppealCase(obj,list);
	}
    /**
	 *
	    * @Title:updateAppealCase
	    * @Description:  TODO(更新诉求)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-14
	 */
	@RequestMapping(value = "/updateAppealCase.do")
	@ResponseBody
	public MessageResponse updateAppealCase(String jsons,String client) throws Exception {
		JSONObject o=JSON.parseObject(jsons);
		AppealCase obj = JSON.parseObject(o.get("o").toString(), AppealCase.class);
		obj.setAnswerOpenId(this.getCurUserProp().getOpenId());
		List<AppealCaseFile> list=JSON.parseArray(o.get("list").toString(),AppealCaseFile.class);

		if(CommonUtils.isNotEmpty(client)){
			SingleResult<UserProp> srt=this.getCurUserPropByOpenId();
			if(srt.getStatus()==1){
				return srt;
			}
			return this.appealCaseService.updateAppealCase(obj,list,srt.getValue());
		}
		return this.appealCaseService.updateAppealCase(obj,list,this.getCurUserProp());
	}
    /**
	 *
	    * @Title:selectAppealCaseByPrimaryKey
	    * @Description:  TODO(获取诉求)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<AppealCase>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-14
	 */
	@RequestMapping(value = "/selectAppealCaseByPrimaryKey.do")
	@ResponseBody
	public SingleResult<AppealCaseVo> selectAppealCaseByPrimaryKey(String id)
			throws Exception {
		return this.appealCaseService.selectAppealCaseByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deleteAppealCaseByAppealCaseId
	    * @Description:  TODO(删除诉求)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-14
	 */
	@RequestMapping(value = "/deleteAppealCaseByAppealCaseId.do")
	@ResponseBody
	public MessageResponse deleteAppealCaseByAppealCaseId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.appealCaseService.deleteAppealCaseByAppealCaseId(id,
				this.getCurUserProp());
	}

	/**
	 *
	 * @Title:getList
	 * @Description:  TODO(获取列表)
	 * @param:        @throws Exception
	 * @return:       Map<String,Object>
	 * @throws
	 * @author: 陈晓克
	 * @version: 2018-05-04
	 */
	@RequestMapping(value = "/getList.do")
	@ResponseBody
	public Map<String,Object> getList() throws Exception{
		return this.appealCaseService.getList(this.getParams());
	}
	/**
	 *
	 * @Title:updateAccept
	 * @Description:  TODO(接受处理诉求)
	 * @param:        @param id
	 * @param:        @param answerDept
	 * @param:        @param  userProp
	 * @param:        @throws Exception
	 * @return:       MessageResponse
	 * @throws
	 * @author: 陈晓克
	 * @version: 2018-05-16
	 */
	@RequestMapping(value = "/updateAccept.do")
	@ResponseBody
	public  MessageResponse updateAccept(String id,String answerDept,String client) throws Exception{
		if(CommonUtils.isNotEmpty(client)){
			SingleResult<UserProp> srt=this.getCurUserPropByOpenId();
			if(srt.getStatus()==1){
				return srt;
			}
			return this.appealCaseService.updateAccept(id,answerDept,srt.getValue());
		}
		return this.appealCaseService.updateAccept(id,answerDept,this.getCurUserProp());
	}
	/**
	 *
	 * @Title:updateDetailsOfProgress
	 * @Description:  TODO(诉求进展情况更新)
	 * @param:        @param id
	 * @param:        @param detailsOfProgress
	 * @param:        @param  userProp
	 * @param:        @throws Exception
	 * @return:       MessageResponse
	 * @throws
	 * @author: 陈晓克
	 * @version: 2018-05-16
	 */
	@RequestMapping(value = "/updateDetailsOfProgress.do")
	@ResponseBody
	public  MessageResponse updateDetailsOfProgress(String id,String detailsOfProgress,String client) throws Exception{
		if(CommonUtils.isNotEmpty(client)){
			SingleResult<UserProp> srt=this.getCurUserPropByOpenId();
			if(srt.getStatus()==1){
				return srt;
			}
			return this.appealCaseService.updateDetailsOfProgress(id,detailsOfProgress,srt.getValue());
		}
		return this.appealCaseService.updateDetailsOfProgress(id,detailsOfProgress,this.getCurUserProp());
	}
}
