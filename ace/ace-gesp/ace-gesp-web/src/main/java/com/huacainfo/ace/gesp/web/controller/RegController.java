package com.huacainfo.ace.gesp.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huacainfo.ace.gesp.model.Department;
import com.huacainfo.ace.gesp.model.Users;
import com.huacainfo.ace.gesp.service.RegService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonKeys;
import com.huacainfo.ace.common.tools.CommonUtils;


@Controller
@RequestMapping("/www/reg")
public class RegController extends KernelBaseController {
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RegService regService;

	/**
	 * 
	 * reg
	 * @Description: TODO(企业注册)
	 * @param: @param jsons
	 * @param: @return
	 * @param: @throws Exception
	 * @return: MessageResponse
	 * @throws
	 * @author: chenxiaoke
	 * @version: 2016年12月6日 下午3:13:04
	 */
	@RequestMapping(value = "/reg.do")
	@ResponseBody
	public MessageResponse reg(String jsons) throws Exception {
		Department obj = JSON.parseObject(jsons, Department.class);
		/*if (!obj.getCheckCode().equals(this.getRequest().getSession().getAttribute(CommonKeys.checkCode))) {
			return new MessageResponse(1, "验证码错误！");
		}
		String checkCode = this.getRequest().getSession().getAttribute("EmailCode").toString();
		if(!obj.getCheckCode().equals(checkCode)){
			return new MessageResponse(1, "验证码错误！");
		}
		this.getRequest().getSession().setAttribute("EmailCode", "");*/
		/*//判断该企业是否存在
		int flag = this.regService.isExitDepartment(obj.getDepartmentName());
		if(flag<=0){//不存在
			return new MessageResponse(1, "企业全称错误！");
		}*/
		//存在(将爬取的数据复制到department表中)
		return this.regService.insertRegDepartment(obj, this.getCurUserProp());
	}

	/**
	 * 判断验证码是否正确
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkCodeMethod.do")
	@ResponseBody
	public MessageResponse checkCodeMethod(String email, String checkCode) throws Exception{
		if(CommonUtils.isBlank(email)){
			return new MessageResponse(1, "邮箱不能为空！");
		}
		if(CommonUtils.isBlank(checkCode)){
			return new MessageResponse(1, "验证码不能为空！");
		}
		//String code = this.getRequest().getSession().getAttribute("EmailCode")!=null?this.getRequest().getSession().getAttribute("EmailCode").toString():"";
		//String em = this.getRequest().getSession().getAttribute("Email")!=null?this.getRequest().getSession().getAttribute("Email").toString():"";
		//if(!checkCode.equals(code)||!em.equals(email)){
		String s_captcha = this.getRequest().getSession().getAttribute(CommonKeys.checkCode)!=null?this.getRequest().getSession().getAttribute(CommonKeys.checkCode).toString():"";
		if(!s_captcha.equals(checkCode)){	
			return new MessageResponse(1, "验证码错误！");
		}
		return new MessageResponse(0, "验证码正确");
	}
	
	@RequestMapping(value = "/getCheckCode.do")
	@ResponseBody
	public MessageResponse getCheckCode(String jsons) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String,Object> model= JSON.parseObject(jsons, Map.class);
		SingleResult<String> rst=this.regService.getCheckCode(model);
		this.getRequest().getSession().setAttribute(CommonKeys.checkCode, rst.getValue());
		return rst;
	}
	
	@RequestMapping(value = "/sendRegMail.do")
	@ResponseBody
	public MessageResponse sendRegMail(String account) throws Exception {
		return this.regService.sendRegMail(account,this.getCurUserProp());
	}
	
	
	@RequestMapping(value = "/updateActivateBySeat.do")
	public ModelAndView updateActivateBySeat(String id)throws Exception {
		MessageResponse rst=this.regService.updateActivateBySeat(id,this.getCurUserProp());
		ModelAndView mav=new ModelAndView("www-reg-activate");
		mav.addObject("o",rst);
		return mav;
	}

	@RequestMapping(value = "/selectInfoByPrimary.do")
	@ResponseBody
	public SingleResult<Department> selectInfoByPrimary(){
		SingleResult<Department> de= regService.selectDepartInfoByPrimaryKey(this.getCurUserProp().getCorpId());
		return de;
	}

	@RequestMapping(value = "/updateByPrimary.do")
	@ResponseBody
	public MessageResponse updateByPrimary(String jsons)throws Exception {
		Department obj = JSON.parseObject(jsons, Department.class);
		obj.setDepartmentId(this.getCurUserProp().getCorpId());
		MessageResponse me = this.regService.updateByPrimaryKey(obj,this.getCurUserProp());
		return me;
	}

	@RequestMapping(value = "/insert.do")
	@ResponseBody
	public MessageResponse insert(String jsons, String jsonTwo)throws Exception {
		if(CommonUtils.isBlank(jsonTwo)){
			return new MessageResponse(1, "请添加联系人");
		}
		List<Users> list = new ArrayList<>(); //JSON.parseArray(jsonTwo, Users.class);
		Department obj = JSON.parseObject(jsons, Department.class);
		obj.setCategory("3");
		obj.setParentDepartmentId(this.getCurUserProp().getCorpId());
		obj.setAreaCode(this.getCurUserProp().getAreaCode());
		obj.setType("3");
		MessageResponse me = this.regService.insert(obj,this.getCurUserProp(),list);
		return me;
	}
	

	/**
	 * 添加联系人
	 * 
	 * @param jsons
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertUsers.do")
	@ResponseBody
	public MessageResponse insertUsers(String jsons)throws Exception {
		Users obj = JSON.parseObject(jsons, Users.class);
		MessageResponse me = this.regService.insertUsers(obj,this.getCurUserProp());
		return me;
	}


	/**
	 * 判断该企业是否已注册
	 * 
	 * 引用(企业注册)
	 * @param departmentName 企业全称
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectBy.do")
	@ResponseBody
	public MessageResponse selectBy(String departmentName)throws Exception {
		MessageResponse me = this.regService.getSiteCompanyInfo(departmentName);
		return me;
	}

	/**
	 * 判断验证码是否正确
	 * 
	 * 引用(企业注册)
	 * @param imageCheckCode 验证码
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectByImage.do")
	@ResponseBody
	public MessageResponse selectByImage(String imageCheckCode) throws Exception {
		String s_captcha = (String) this.getRequest().getSession().getAttribute("j_captcha");
		if (!s_captcha.equals(imageCheckCode)) {
			return new MessageResponse(1, "验证码错误！");
		}
		return new MessageResponse(0, "验证码正确！");
	}
	
	
	/**
	 * 判断账号是否唯一
	 * 
	 * 引用(企业注册)
	 * @param account 账号
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectByAccount.do")
	@ResponseBody
	public MessageResponse selectByAccount(String accounts)throws Exception {
		MessageResponse me = this.regService.isExitAccount(accounts);
		return me;
	}
	
}
