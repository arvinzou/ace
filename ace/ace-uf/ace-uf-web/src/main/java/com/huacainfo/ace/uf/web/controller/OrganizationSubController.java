package com.huacainfo.ace.uf.web.controller;

import com.huacainfo.ace.common.fastdfs.IFile;
import com.huacainfo.ace.portal.service.FilesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.vo.MongoFile;
import com.huacainfo.ace.uf.model.OrganizationSub;
import com.huacainfo.ace.uf.service.OrganizationSubService;
import com.huacainfo.ace.uf.vo.OrganizationSubQVo;
import com.huacainfo.ace.uf.vo.OrganizationSubVo;

import java.io.File;

@Controller
@RequestMapping("/organizationSub")
public class OrganizationSubController extends UfBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private OrganizationSubService organizationSubService;


	@Autowired
	private IFile fileSaver;
	@Autowired
	private FilesService filesService;

	@RequestMapping(value = "/findOrganizationSubList.do")
	@ResponseBody
	public PageResult<OrganizationSubVo> findOrganizationSubList(OrganizationSubQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<OrganizationSubVo> rst = this.organizationSubService
				.findOrganizationSubList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}

	@RequestMapping(value = "/insertOrganizationSub.do")
	@ResponseBody
	public MessageResponse insertOrganizationSub(String jsons) throws Exception {
		OrganizationSub obj = JSON.parseObject(jsons, OrganizationSub.class);
		return this.organizationSubService
				.insertOrganizationSub(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updateOrganizationSub.do")
	@ResponseBody
	public MessageResponse updateOrganizationSub(String jsons) throws Exception {
		OrganizationSub obj = JSON.parseObject(jsons, OrganizationSub.class);
		return this.organizationSubService
				.updateOrganizationSub(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectOrganizationSubByPrimaryKey.do")
	@ResponseBody
	public SingleResult<OrganizationSubVo> selectOrganizationSubByPrimaryKey(String id)
			throws Exception {
		return this.organizationSubService.selectOrganizationSubByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteOrganizationSubByOrganizationSubId.do")
	@ResponseBody
	public MessageResponse deleteOrganizationSubByOrganizationSubId(String id)
			throws Exception {

		return this.organizationSubService.deleteOrganizationSubByOrganizationSubId(id,
				this.getCurUserProp());
	}

	@RequestMapping(value = "/uploadFile.do")
	@ResponseBody
	public SingleResult<String[]> uploadFile(
			@RequestParam MultipartFile[] file, String id)
			throws Exception {
		SingleResult<String[]> rst = new SingleResult<String[]>(0, "上传成功！");
		String[] fileNames = new String[file.length];
		String dir = this.getRequest().getSession().getServletContext()
				.getRealPath(File.separator)
				+ "tmp";
		File tmp = new File(dir);
		if (!tmp.exists()) {
			tmp.mkdirs();
		}
		int i = 0;
		for (MultipartFile o : file) {
			File dest = new File(dir + File.separator + o.getName());
			o.transferTo(dest);
			fileNames[i] = this.fileSaver.saveFile(dest,
					o.getOriginalFilename());
			dest.delete();
			filesService.insertFiles(fileNames[i], this.getCurUserProp());
			OrganizationSub obj =new OrganizationSub();
			obj.setOrganizationId(id);
			obj.setUrl(fileNames[i]);
			obj.setName(o.getOriginalFilename().substring(0,o.getOriginalFilename().indexOf(".")));
			this.organizationSubService.insertOrganizationSub(obj,this.getCurUserProp());
			i++;
		}
		rst.setValue(fileNames);
		return rst;
	}
}
