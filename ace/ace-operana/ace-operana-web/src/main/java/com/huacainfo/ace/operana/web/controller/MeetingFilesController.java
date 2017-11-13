package com.huacainfo.ace.operana.web.controller;

import java.io.File;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.huacainfo.ace.common.fastdfs.IFile;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.operana.model.MeetingFiles;
import com.huacainfo.ace.operana.service.MeetingFilesService;
import com.huacainfo.ace.portal.service.FilesService;

@Controller
@RequestMapping("/meetingFiles")
public class MeetingFilesController extends OperanaBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MeetingFilesService meetingFilesService;

	@Autowired
	private IFile fileSaver;
	@Autowired
	private FilesService filesService;



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
			MeetingFiles obj =new MeetingFiles();
			obj.setMeetingId(id);
			obj.setUrl(fileNames[i]);
			obj.setStatus("1");
			obj.setName(o.getOriginalFilename().substring(0,o.getOriginalFilename().indexOf(".")));
			this.meetingFilesService.insertMeetingFiles(obj,this.getCurUserProp());
			i++;
		}
		rst.setValue(fileNames);
		return rst;
	}



	@RequestMapping(value = "/deleteMeetingFilesByMeetingFilesId.do")
	@ResponseBody
	public MessageResponse deleteMeetingFilesByMeetingFilesId(String id)
			throws Exception {
		return this.meetingFilesService.deleteMeetingFilesByMeetingFilesId(id,
				this.getCurUserProp());
	}

	@RequestMapping(value = "/selectFilesByMeetingId.do")
	@ResponseBody
	public Map<String, Object> selectFilesByMeetingId(String meetingId) throws Exception{
		return this.meetingFilesService.selectFilesByMeetingId(meetingId);
	}
}
