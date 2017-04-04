package com.huacainfo.ace.portal.web.controller;

import java.io.File;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.huacainfo.ace.common.fastdfs.IFile;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.portal.model.Attach;
import com.huacainfo.ace.portal.service.AttachService;
import com.huacainfo.ace.portal.service.FilesService;
import com.huacainfo.ace.portal.vo.AttachQVo;
import com.huacainfo.ace.portal.vo.AttachVo;
@Controller
@RequestMapping("/attach")
public class AttachController extends PortalBaseController{
	private static final long serialVersionUID = 1L;
	@Autowired
	private IFile fileSaver;
	@Autowired
	private FilesService filesService;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AttachService attachService;
	/**
	 * 
	    * @Title:findAttachList 
	    * @Description:  TODO(附件分页查询) 
	 		* @param:        @param condition
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       ListResult<AttachVo>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:34:36
	 */
	@RequestMapping(value = "/findAttachList.do")
	@ResponseBody
	public  ListResult<AttachVo>  findAttachList(AttachQVo condition) throws Exception{
		return this.attachService.findAttachList(condition);
	}
	/**
	 * 
	    * @Title:deleteAttachByFileName 
	    * @Description:  TODO(附件删除) 
	 		* @param:        @param fileName
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:34:52
	 */
	@RequestMapping(value = "/deleteAttachByFileName.do")
	@ResponseBody
	public  MessageResponse deleteAttachByFileName(String fileName)  throws Exception{
		return this.attachService.deleteAttachByFileName(fileName,this.getCurUserProp());
	}
	/**
	 * 
	    * @Title:uploadFile 
	    * @Description:  TODO(删除附件) 
	 		* @param:        @param file
	 		* @param:        @param noticeId
	 		* @param:        @param collectionName
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       ListResult<Attach>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:35:41
	 */
	@RequestMapping(value = "/uploadFile.do")
	@ResponseBody
	public ListResult<Attach>  uploadFile(@RequestParam MultipartFile[] file,String noticeId, String collectionName)
			throws Exception {
		String[] fileNames=new String[file.length];
		Attach[] files=new Attach[file.length];
		int i=0;
		String dir=this.getRequest().getSession().getServletContext().getRealPath(File.separator)+"tmp";
		File tmp=new File(dir);
		if(!tmp.exists()){
			tmp.mkdirs();
		}
		for(MultipartFile o:file){
	        File dest = new File(dir+File.separator+o.getName());
	        o.transferTo(dest);
	        fileNames[i]=this.fileSaver.saveFile(dest,o.getOriginalFilename());
	        dest.delete();
	        filesService.insertFiles(fileNames[i], this.getCurUserProp());
	        Attach obj = new Attach();
	        obj.setNoticeId(noticeId);
	        obj.setCategory(collectionName);
	        obj.setCreateTime(new Date());
	        obj.setFileName(file[i].getOriginalFilename());
	        obj.setFileUrl(fileNames[i]);
	        obj.setFileSize(String.valueOf(file[i].getSize()));
	        files[i]=obj;
			i++;
		}
		return this.attachService.upload(files,noticeId, this.getCurUserProp());
	}
	
	
}
