package com.huacainfo.ace.portal.web.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.alibaba.fastjson.JSON;
import java.util.Random;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.model.TaskCmcc;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.model.WxFormid;
import com.huacainfo.ace.portal.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.redis.core.RedisOperations;

import com.huacainfo.ace.common.fastdfs.IFile;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.portal.model.Attach;
import com.huacainfo.ace.portal.vo.AttachQVo;
import com.huacainfo.ace.portal.vo.AttachVo;

@Controller
@RequestMapping("/www")
public class WWWController extends PortalBaseController {
	private static final long serialVersionUID = 1L;
	@Autowired
	private IFile fileSaver;
	@Autowired
	private FilesService filesService;
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AttachService attachService;

	@Autowired
	private UsersService usersService;


	@Autowired
	private SystemService systemService;
	@Autowired
	private WxCfgService wxCfgService;
	@Autowired
	private  TaskCmccService taskCmccService;

	@Autowired
	private WxUserService wxUserService;

	@Autowired
	private RedisOperations<String, Object> redisTemplate;

	/**
	 * 
	 * @Title:uploadFile
	 * @Description: TODO(删除附件)
	 * @param: @param
	 *             file
	 * @param: @param
	 *             noticeId
	 * @param: @param
	 *             collectionName
	 * @param: @return
	 * @param: @throws
	 *             Exception
	 * @return: ListResult<Attach>
	 * @throws @author:
	 *             chenxiaoke
	 * @version: 2016年11月17日 下午1:35:41
	 */
	@RequestMapping(value = "/uploadFile.do")
	@ResponseBody
	public ListResult<Attach> uploadFile(@RequestParam MultipartFile[] file, String id, String collectionName)
			throws Exception {

		String[] fileNames = new String[file.length];
		Attach[] files = new Attach[file.length];
		int i = 0;
		String dir = this.getRequest().getSession().getServletContext().getRealPath(File.separator) + "tmp";
		File tmp = new File(dir);
		if (!tmp.exists()) {
			tmp.mkdirs();
		}
		for (MultipartFile o : file) {
			File dest = new File(dir + File.separator + o.getName());
			o.transferTo(dest);
			fileNames[i] = this.fileSaver.saveFile(dest, o.getOriginalFilename());
			dest.delete();
			filesService.insertFiles(fileNames[i], this.getCurUserProp());
			Attach obj = new Attach();
			obj.setNoticeId(id);
			obj.setCategory(collectionName);
			obj.setCreateTime(new Date());
			obj.setFileName(file[i].getOriginalFilename());
			obj.setFileUrl(fileNames[i]);
			obj.setFileSize(String.valueOf(file[i].getSize()));
			files[i] = obj;
			i++;

		}
		return this.attachService.upload(files, id, this.getCurUserProp());
	}
	@RequestMapping(value = "/regUser.do")
	@ResponseBody
	public MessageResponse insertUsers(Users o) throws Exception {


		return this.usersService.insertReg(o);
	}
	@RequestMapping(value = "/selectDepartmentTreeList.do")
	@ResponseBody
	public List<Tree> selectDepartmentTreeList(String deptId)throws Exception {
		List<Tree>	list=this.systemService.selectDepartmentTreeList(deptId,"sys");
		return list;
	}

	@RequestMapping(value = "/saveFormId.do")
	@ResponseBody
	public MessageResponse saveFormId(String text) throws Exception {
		WxUser user=this.getCurWxUser();
		List<WxFormid> list=JSON.parseArray(text,WxFormid.class);
		for(WxFormid o:list){
			if(CommonUtils.isNotEmpty(user)){
				o.setOpenId(user.getOpenId());
			}
		}
		return this.wxCfgService.insertFormIds(list);
	}

	@RequestMapping(value = "/selectWxUser.do")
	@ResponseBody
	public List<Map<String,Object>> selectWxUser()throws Exception{
		return this.wxUserService.selectWxUser(this.getParams());
	}
	@RequestMapping(value = "/updateFaceToken.do")
	@ResponseBody
	public MessageResponse updateFaceToken(@RequestParam MultipartFile[] file, String id, String collectionName)throws Exception{
		String image_url=null;
		String unionId=null;
		String dir = this.getRequest().getSession().getServletContext().getRealPath(File.separator) + "tmp";
		File tmp = new File(dir);
		if (!tmp.exists()) {
			tmp.mkdirs();
		}
		for (MultipartFile o : file) {
			File dest = new File(dir + File.separator + o.getName());
			o.transferTo(dest);
			String fileName=this.fileSaver.saveFile(dest, o.getOriginalFilename());
			image_url="http://zx.huacainfo.com/"+fileName;
			dest.delete();
		}
		if(CommonUtils.isNotEmpty(this.getCurWxUser())){
			unionId=this.getCurWxUser().getUnionId();
		}else{
			unionId=id;
		}
		return this.wxUserService.updateFaceToken(image_url,unionId);
	}

	@RequestMapping(value = "/selectWxUserByPrimaryKey.do")
	@ResponseBody
	public SingleResult<WxUser>  selectWxUserByPrimaryKey(String id)throws Exception{
		return this.wxUserService.selectWxUserByPrimaryKey(id);
	}

	@RequestMapping(value = "/searchFace.do")
	@ResponseBody
	public SingleResult<WxUser> searchFace(@RequestParam MultipartFile[] file)throws Exception{
		String image_url=null;
		String dir = this.getRequest().getSession().getServletContext().getRealPath(File.separator) + "tmp";
		File tmp = new File(dir);
		if (!tmp.exists()) {
			tmp.mkdirs();
		}
		for (MultipartFile o : file) {
			File dest = new File(dir + File.separator + o.getName());
			o.transferTo(dest);
			String fileName=this.fileSaver.saveFile(dest, o.getOriginalFilename());
			image_url="http://zx.huacainfo.com/"+fileName;
			dest.delete();
		}
		return this.wxUserService.searchFace(image_url);
	}
	private String getRandCode() {
		Random random = new Random();
		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
		}
		// 保存进session
		this.getRequest().getSession().setAttribute("j_captcha_cmcc", sRand);
		return sRand;
	}

}
