package com.huacainfo.ace.portal.web.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.ImageCut;
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
	public SingleResult<String[]> uploadFile(@RequestParam MultipartFile[] file, String collectionName) throws Exception {
		SingleResult<String[]> rst = new SingleResult<String[]>(0, "上传成功！");
		String[] fileNames = new String[file.length];
		String dir = this.getRequest().getSession().getServletContext().getRealPath(File.separator) + "tmp";
		File tmp = new File(dir);
		if (!tmp.exists()) {
			tmp.mkdirs();
		}
		int i = 0;
		for (MultipartFile o : file) {
			File dest = new File(dir + File.separator + o.getName());
			o.transferTo(dest);
			fileNames[i] = this.fileSaver.saveFile(dest, o.getOriginalFilename());
			dest.delete();
			filesService.insertFiles(fileNames[i], this.getCurUserProp());
			i++;
		}
		rst.setValue(fileNames);
		return rst;
	}


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
	@RequestMapping(value = "/uploadFilePlus.do")
	@ResponseBody
	public SingleResult<Map<String,Object>> uploadFilePlus(@RequestParam MultipartFile[] file, String collectionName) throws Exception {
		SingleResult<Map<String,Object>> rst = new SingleResult<Map<String,Object>>(0, "上传成功！");
		String[] fileNames = new String[file.length];
		String dir = this.getRequest().getSession().getServletContext().getRealPath(File.separator) + "tmp";
		File tmp = new File(dir);
		if (!tmp.exists()) {
			tmp.mkdirs();
		}
		File dest = new File(dir + File.separator + file[0].getName());
		file[0].transferTo(dest);
		fileNames[0] = this.fileSaver.saveFile(dest, file[0].getOriginalFilename());
		dest.delete();
		filesService.insertFiles(fileNames[0], this.getCurUserProp());
		Map<String,Object> values=new HashMap<String,Object>();
		values.put("fileName",file[0].getOriginalFilename());
		values.put("fileSize",file[0].getSize());
		values.put("fileNames",fileNames[0]);
		rst.setValue(values);
		return rst;
	}


	@RequestMapping(value = "/uploadImage.do")
	@ResponseBody
	public Map<String,Object> uploadImage(@RequestParam MultipartFile[] file, String collectionName,String x, String y, String desWidth, String desHeight, String srcWidth,
										  String srcHeight)throws Exception {

		Map<String,Object> rst = new HashMap<String,Object>();
		String fastdfs_server=((Map)this.getRequest().getSession().getServletContext().getAttribute("cfg")).get("fastdfs_server").toString();
		String[] fileNames = new String[file.length];
		String dir = this.getRequest().getSession().getServletContext().getRealPath(File.separator) + "tmp";
		File tmp = new File(dir);
		if (!tmp.exists()) {
			tmp.mkdirs();
		}
		int i = 0;
		for (MultipartFile o : file) {
			File dest = new File(dir + File.separator + o.getName());
			if(CommonUtils.isNotEmpty(x)){
				ImageCut.imgCut(o.getInputStream(),Integer.valueOf(x),Integer.valueOf(y),Integer.valueOf(desWidth),Integer.valueOf(desHeight),Integer.valueOf(srcWidth),Integer.valueOf(srcHeight),dest);
			}else{
				o.transferTo(dest);
			}
			fileNames[i] = this.fileSaver.saveFile(dest,
					o.getOriginalFilename());
			dest.delete();
			filesService.insertFiles(fileNames[i], this.getCurUserProp());
			rst.put("success",true);
			rst.put("msg","成功");
			rst.put("file_path",fastdfs_server+fileNames[i]);
			i++;
		}
		return rst;
	}
}
