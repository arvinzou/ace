package com.huacainfo.ace.uf.service.impl;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.uf.dao.FileDao;
import com.huacainfo.ace.uf.service.FileService;
import com.huacainfo.ace.uf.vo.FileQVo;
import com.huacainfo.ace.uf.vo.FileVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author hcwy
 */
@Service("fileService")
public class FileServiceImpl implements FileService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FileDao fileDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Override
    public PageResult<FileVo> findFileList(FileQVo condition,int start, int limit, String orderBy) throws Exception {
        this.logger.info("===============================fileserviceImpl=============================");
        PageResult<FileVo> rst = new PageResult<FileVo>();
        List<FileVo> list = this.fileDao.findFileList(condition,start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.fileDao.findFileCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * 根据ID更新统战文件列表
     * @param obj
     * @param userProp
     * @return
     * @throws Exception
     */
    @Override
    public MessageResponse updateFileById(FileQVo obj, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(obj.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(obj.getName())) {
            return new MessageResponse(1, "文件名称不能为空");
        }
        if (CommonUtils.isBlank(obj.getType())) {
            return new MessageResponse(1, "统战文件类型不能为空");
        }
        String fileUrl=obj.getFile();
        if (CommonUtils.isBlank(fileUrl)) {
            return new MessageResponse(1, "文件不能为空");
        }
        int index=fileUrl.lastIndexOf(".");
        String suffix=fileUrl.substring(index+1,fileUrl.length());
        if (CommonUtils.isBlank(suffix)) {
            return new MessageResponse(1, "文件后缀不能为空");
        }
        this.logger.info(suffix.toString());
        obj.setSuffix(suffix);
        obj.setLastModifyDate(new Date());
        obj.setLastModifyUserName(userProp.getName());
        obj.setLastModifyUserId(userProp.getUserId());
        this.fileDao.updateFileById(obj);
        this.dataBaseLogService.log("变更统战文件", "统战人士", "",obj.getId(), obj.getName(), userProp);
        return new MessageResponse(0,"更新统战文件完成");
    }

    @Override
    public MessageResponse deleteFileByFileId(String id, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(id)) {
            return new MessageResponse(1, "主键不能为空！");
        }
        this.fileDao.deleteFileByFileId(id);
        this.dataBaseLogService.log("删除统战文件", "统战人士", "",id, id, userProp);
        return new MessageResponse(0,"删除统战文件完成");
    }

    @Override
    public MessageResponse insertFile(FileQVo obj, UserProp userProp) throws Exception {
        obj.setId(String.valueOf(System.currentTimeMillis()));
        if (CommonUtils.isBlank(obj.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(obj.getName())) {
            return new MessageResponse(1, "文件名称不能为空");
        }
        if (CommonUtils.isBlank(obj.getType())) {
            return new MessageResponse(1, "统战文件类型不能为空");
        }
        String fileUrl=obj.getFile();
        if (CommonUtils.isBlank(fileUrl)) {
            return new MessageResponse(1, "文件不能为空");
        }
        int index=fileUrl.lastIndexOf(".");
        String suffix=fileUrl.substring(index+1,fileUrl.length());
        if (CommonUtils.isBlank(suffix)) {
            return new MessageResponse(1, "文件后缀不能为空");
        }
        obj.setSuffix(suffix);
        obj.setCreateDate(new Date());
        obj.setCreateUserName(userProp.getName());
        obj.setCreateUserId(userProp.getUserId());
        this.fileDao.insertFile(obj);
        this.dataBaseLogService.log("添加统战人士", "统战人士", "", obj.getName(), obj.getName(), userProp);
        return new MessageResponse(0, "添加统战人士完成！");
    }
}
