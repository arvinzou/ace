package com.huacainfo.ace.portal.service.impl;

import java.util.Date;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.fastdfs.IFile;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.dao.FilesMapper;
import com.huacainfo.ace.portal.model.Files;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.FilesService;
@Service("filesService")
public class FilesServiceImpl implements FilesService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FilesMapper filesMapper;
	@Autowired
	private IFile fileSaver;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	@Autowired
	private SqlSessionTemplate sqlSession;
	public FilesServiceImpl(){
		
	}

	@Override
	public MessageResponse insertFiles(String filePath, UserProp userProp)
			throws Exception {
		Files obj=new Files();
		obj.setFilePath(filePath);
		obj.setCreateUser(userProp.getUserId());
		obj.setCreateTime(new Date());
		obj.setStatus("0");
		this.filesMapper.insert(obj);
		this.dataBaseLogService.log("上传文件", "上传文件", "", obj.getCreateUser(),
				obj.getCreateUser(), userProp);
		return new MessageResponse(0,"成功！");
	}

	@Override
	public MessageResponse updateFiles(String filePath, UserProp userProp)
			throws Exception {
		Files obj=this.filesMapper.selectByPrimaryKey(filePath);
		if(!CommonUtils.isBlank(obj)){
			obj.setStatus("1");
			this.filesMapper.updateByPrimaryKeySelective(obj);
			this.dataBaseLogService.log("更新文件", "更新文件", "", obj.getCreateUser(),
					obj.getCreateUser(), userProp);
		}
		return new MessageResponse(0,"成功！");
	}

	@Override
	public MessageResponse deleteFiles() throws Exception {
		List<Files> list=this.filesMapper.selectListByStatus("0");
		for(Files o:list){
			fileSaver.deleteFile(o.getFilePath());
			this.filesMapper.deleteByPrimaryKey(o.getFilePath());
		}
		return new MessageResponse(0,"成功！");
	}
	
	public void deleteFileTimer() {
		logger.info("清除临时文件");
		List<Files> list=filesMapper.selectListByStatus("0");
		for(Files o:list){
			try {
				//fileSaver.deleteFile(o.getFilePath());
			} catch (Exception e) {
				logger.error("系统错误：", e);
			}
			filesMapper.deleteByPrimaryKey(o.getFilePath());
		}
	}

}
