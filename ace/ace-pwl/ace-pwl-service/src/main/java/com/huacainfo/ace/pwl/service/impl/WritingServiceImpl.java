package com.huacainfo.ace.pwl.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.pwl.dao.WritingDao;
import com.huacainfo.ace.pwl.model.Writing;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.pwl.service.WritingService;
import com.huacainfo.ace.pwl.vo.WritingVo;
import com.huacainfo.ace.pwl.vo.WritingQVo;

@Service("writingService")
public class WritingServiceImpl implements WritingService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private WritingDao writingDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    @Override
    public PageResult<WritingVo> findWritingList(WritingQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<WritingVo> rst = new PageResult<WritingVo>();
        List<WritingVo> list = this.writingDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.writingDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    @Override
    public PageResult<WritingVo> handleWritingList(WritingQVo condition, String reportId, int page, int limit) throws Exception {
        PageResult<WritingVo> rst = new PageResult<WritingVo>();
        List<WritingVo> list = new ArrayList<WritingVo>();
        if ("writing".equals(reportId)) {
            list = this.writingDao.findList(condition, (page - 1) * limit, limit, "");
        } else if ("loadWriting".equals(reportId)) {
            list = this.writingDao.findList(condition, 0, 1, "");
            this.writingDao.updateReading(condition);
        }
        rst.setRows(list);
        return rst;
    }

    @Override
    public MessageResponse insertWriting(Writing o, UserProp userProp)
            throws Exception {
        o.setId(UUID.randomUUID().toString());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "作品名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getAuthor())) {
            return new MessageResponse(1, "作者不能为空！");
        }
        if (CommonUtils.isBlank(o.getDateOfPublication())) {
            return new MessageResponse(1, "发表日期不能为空！");
        }
        if (CommonUtils.isBlank(o.getDocText())) {
            return new MessageResponse(1, "作品不能为空！");
        }
        int temp = this.writingDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "作品名称重复！");
        }
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setReading(CommonUtils.getRandomNum(10));
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.writingDao.insert(o);
        this.dataBaseLogService.log("添加作品", "作品", "", o.getName(),
                o.getName(), userProp);
        return new MessageResponse(0, "添加作品完成！");
    }

    @Override
    public MessageResponse updateWriting(Writing o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "作品名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getDateOfPublication())) {
            return new MessageResponse(1, "发表日期不能为空！");
        }
        if (CommonUtils.isBlank(o.getDocText())) {

            return new MessageResponse(1, "作品不能为空！");
        }
        this.logger.info(o.getDocText());
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.writingDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更作品", "作品", "", o.getName(),
                o.getName(), userProp);
        return new MessageResponse(0, "变更作品完成！");
    }

    @Override
    public SingleResult<Writing> selectWritingByPrimaryKey(String id) throws Exception {
        SingleResult<Writing> rst = new SingleResult<Writing>();
        rst.setValue(this.writingDao.selectByPrimaryKey(id));
        return rst;
    }

    @Override
    public MessageResponse deleteWritingByWritingId(String id,
                                                    UserProp userProp) throws Exception {
        this.writingDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除作品", "作品", String.valueOf(id),
                String.valueOf(id), "作品", userProp);
        return new MessageResponse(0, "作品删除完成！");
    }
}
