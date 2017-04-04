package com.huacainfo.ace.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.fastdfs.IFile;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.portal.dao.AttachMapper;
import com.huacainfo.ace.portal.model.Attach;
import com.huacainfo.ace.portal.service.AttachService;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.vo.AttachQVo;
import com.huacainfo.ace.portal.vo.AttachVo;

@Service("attachService")
public class AttachServiceImpl implements AttachService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AttachMapper attachMapper;
    @Autowired
    private IFile fileSaver;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    public ListResult<AttachVo> findAttachList(AttachQVo condition) throws Exception {
        ListResult<AttachVo> rst = new ListResult<AttachVo>();
        List<AttachVo> list = this.attachMapper.findList(condition);
        rst.setValue(list);
        return rst;
    }


    public MessageResponse deleteAttachByFileName(String id, UserProp userProp) throws Exception {
        MessageResponse rst = new MessageResponse();
        Attach o = attachMapper.selectByPrimaryKey(Integer.valueOf(id));
        fileSaver.deleteFile(o.getFileUrl());
        this.attachMapper.deleteByPrimaryKey(o.getAttachId());
        this.dataBaseLogService.log("删除附件", "附件", ",附件名称：" + o.getFileName() + ",附件地址：" + o.getFileUrl() + "编号：" + String.valueOf(id), "已删除成功",
                "上传附件", userProp);
        return rst;
    }

    public ListResult<Attach> upload(Attach[] file, String noticeId, UserProp userProp) throws Exception {
        ListResult<Attach> rst = new ListResult<Attach>();
        List<Attach> list = new ArrayList<Attach>();
        for (Attach o : file) {
            list.add(o);
            this.attachMapper.insert(o);
            this.dataBaseLogService.log("附件上传", "附件上传", "", o.getFileName(), o.getFileName(), userProp);
        }
        rst.setErrorMessage("上传成功");
        rst.setValue(list);
        rst.getOther().put("noticeId", noticeId);
        return rst;
    }

}
