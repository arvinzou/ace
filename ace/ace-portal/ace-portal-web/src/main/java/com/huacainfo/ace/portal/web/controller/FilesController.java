package com.huacainfo.ace.portal.web.controller;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.huacainfo.ace.common.fastdfs.IFile;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.service.FilesService;

@Controller
@RequestMapping("/files")
public class FilesController extends PortalBaseController {
	private static final long serialVersionUID = 1L;
	@Autowired
	private IFile fileSaver;
	@Autowired
	private FilesService filesService;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 
	    * @Title:uploadFile 
	    * @Description:  TODO(上传文件) 
	 		* @param:        @param file
	 		* @param:        @param collectionName
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       SingleResult<String[]>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:54:38
	 */
	@RequestMapping(value = "/uploadFile.do")
	@ResponseBody
	public SingleResult<String[]> uploadFile(
			@RequestParam MultipartFile[] file, String collectionName)
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
			i++;
		}
		rst.setValue(fileNames);
		return rst;
	}
}
