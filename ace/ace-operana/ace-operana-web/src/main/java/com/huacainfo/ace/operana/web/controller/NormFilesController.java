package com.huacainfo.ace.operana.web.controller;

import com.huacainfo.ace.common.fastdfs.IFile;
import com.huacainfo.ace.operana.model.MeetingFiles;
import com.huacainfo.ace.portal.service.FilesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.operana.model.NormFiles;
import com.huacainfo.ace.operana.service.NormFilesService;
import com.huacainfo.ace.operana.vo.NormFilesVo;
import com.huacainfo.ace.operana.vo.NormFilesQVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

@Controller
@RequestMapping("/normFiles")
public class NormFilesController extends OperanaBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private NormFilesService normFilesService;

	@Autowired
	private IFile fileSaver;
	@Autowired
	private FilesService filesService;


	@RequestMapping(value = "/uploadFile.do")
	@ResponseBody
	public SingleResult<String[]> uploadFile(
			@RequestParam MultipartFile[] file, String meetingId, String topicId, String normId)
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
			NormFiles obj =new NormFiles();
			obj.setMeetingId(meetingId);
			obj.setTopicId(topicId);
			obj.setNormId(normId);
			obj.setUrl(fileNames[i]);
			obj.setStatus("1");
			obj.setName(o.getOriginalFilename().substring(0,o.getOriginalFilename().indexOf(".")));
			this.normFilesService.insertNormFiles(obj,this.getCurUserProp());
			i++;
		}
		rst.setValue(fileNames);
		return rst;
	}



	@RequestMapping(value = "/deleteNormFilesByNormFilesId.do")
	@ResponseBody
	public MessageResponse deleteNormFilesByNormFilesId(String id)
			throws Exception {
		return this.normFilesService.deleteNormFilesByNormFilesId(id,
				this.getCurUserProp());
	}
	@RequestMapping(value = "/selectFilesByMeetingTopicNormId.do")
	@ResponseBody
	public Map<String, Object> selectFilesByMeetingTopicNormId(String meetingId, String topicId, String normId) throws Exception{
		return this.normFilesService.selectFilesByMeetingTopicNormId( meetingId,  topicId,  normId);
	}
}
