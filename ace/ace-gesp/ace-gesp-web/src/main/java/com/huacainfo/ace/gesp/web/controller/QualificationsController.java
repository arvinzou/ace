package com.huacainfo.ace.gesp.web.controller;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.gesp.service.QualificationsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.fastdfs.IFile;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.gesp.model.Qualifications;
import com.huacainfo.ace.gesp.vo.QualificationsQVo;

@Controller
@RequestMapping("/qualifications")
public class QualificationsController extends KernelBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private QualificationsService qualificationsService;
	
	@Autowired
	private IFile fileSaver;

	@RequestMapping(value = "/findQualificationsList.do")
	@ResponseBody
	public PageResult<Map<String,Object>> findQualificationsList(
			QualificationsQVo condition, PageParamNoChangeSord page)
			throws Exception {
		if(CommonUtils.isBlank(condition.getDeptId())){
			condition.setDeptId(this.getCurUserProp().getCorpId());
		}
		PageResult<Map<String,Object>> rst = this.qualificationsService
				.findQualificationsList(condition, page.getStart(),
						page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}

		return rst;
	}

	@RequestMapping(value = "/insertQualifications.do")
	@ResponseBody
	public MessageResponse insertQualifications(String jsons) throws Exception {
		Qualifications obj = JSON.parseObject(jsons, Qualifications.class);
		return this.qualificationsService.insertQualifications(obj,
				this.getCurUserProp());
	}

	@RequestMapping(value = "/updateQualifications.do")
	@ResponseBody
	public MessageResponse updateQualifications(String jsons) throws Exception {
		Qualifications obj = JSON.parseObject(jsons, Qualifications.class);
		return this.qualificationsService.updateQualifications(obj,
				this.getCurUserProp());
	}

	@RequestMapping(value = "/selectQualificationsByPrimaryKey.do")
	@ResponseBody
	public SingleResult<Qualifications> selectQualificationsByPrimaryKey(
			String id) throws Exception {
		return this.qualificationsService.selectQualificationsByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteQualificationsByQualificationsId.do")
	@ResponseBody
	public MessageResponse deleteQualificationsByQualificationsId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.qualificationsService
				.deleteQualificationsByQualificationsId(id,
						this.getCurUserProp());
	}
	@RequestMapping(value = "/selectQualificationsList.do")
	@ResponseBody
	ListResult<Map<String, Object>> selectQualificationsList(String deptId,String pdeptId,String flag) throws Exception {
		if(CommonUtils.isBlank(pdeptId)){
			pdeptId = this.getCurUserProp().getParentCorpId();
		}
		if(CommonUtils.isBlank(deptId)){
			deptId = this.getCurUserProp().getCorpId();
		}
		return this.qualificationsService.selectQualificationsList(deptId,pdeptId,flag);
	}
	
	@RequestMapping(value = "/updateAudit.do")
	@ResponseBody
	public MessageResponse updateAudit(String jsons) throws Exception {
		List<Qualifications> list = JSON.parseArray(jsons, Qualifications.class);
		return this.qualificationsService.updateAudit(list,
				this.getCurUserProp());
		/*Qualifications list = JSON.parseObject(jsons, Qualifications.class);
		if(CommonUtils.isBlank(flag)&&flag.equals("1")){
			list.setId(" ");
		}
		return this.qualificationsService.updateAudit(list,flag,this.getCurUserProp());*/
	}
	
	/**
	 * 提交审核认证
	 * 
	 * @return MessageResponse
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateQualifi.do")
	@ResponseBody
	public MessageResponse updateQualifi() throws Exception {
		return this.qualificationsService.updateQualifi(this.getCurUserProp());
	}
	
	/**
	 * 删除文件
	 * 
	 * @return MessageResponse
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteByFile.do")
	@ResponseBody
	public MessageResponse deleteByFile(String filePath, String id) throws Exception {
		Qualifications q = new Qualifications();
		q.setId(id);
		q.setFileSrc("  ");
		MessageResponse m = this.qualificationsService.updateQualiByFileSrc(q, this.getCurUserProp());
		if(m.getStatus()==0){
			fileSaver.deleteFile(filePath);
		}
		return m;
	}
}
