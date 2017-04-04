package com.huacainfo.ace.kernel.service.impl;


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
import com.huacainfo.ace.kernel.dao.WritingDao;
import com.huacainfo.ace.kernel.model.Writing;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.kernel.service.WritingService;
import com.huacainfo.ace.kernel.vo.WritingVo;
import com.huacainfo.ace.kernel.vo.WritingQVo;

@Service("writingService")
public class WritingServiceImpl implements WritingService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private WritingDao writingDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    public PageResult<WritingVo> findWritingList(WritingQVo condition, int start,
                                                 int limit, String orderBy) throws Exception {
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

    public MessageResponse insertWriting(Writing o, UserProp userProp)
            throws Exception {
        o.setId(UUID.randomUUID().toString());
        o.setAuthor(userProp.getUserId());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "作品名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "分类不能为空！");
        }
        if (CommonUtils.isBlank(o.getDateOfPublication())) {
            return new MessageResponse(1, "发表日期不能为空！");
        }
        if (CommonUtils.isBlank(o.getEverPublished())) {
            return new MessageResponse(1, "曾发表所在不能为空！");
        }
        if (CommonUtils.isBlank(o.getIntro())) {
            return new MessageResponse(1, "简介不能为空！");
        }
        if (CommonUtils.isBlank(o.getDocText())) {
            return new MessageResponse(1, "正文不能为空！");
        }
        int temp = this.writingDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "作品名称重复！");
        }
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.writingDao.insert(o);
        this.dataBaseLogService.log("添加作品", "作品", "", o.getName(),
                o.getName(), userProp);
        return new MessageResponse(0, "添加作品完成！");
    }

    public MessageResponse updateWriting(Writing o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "作品名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "分类不能为空！");
        }
        if (CommonUtils.isBlank(o.getDateOfPublication())) {
            return new MessageResponse(1, "发表日期不能为空！");
        }
        if (CommonUtils.isBlank(o.getEverPublished())) {
            return new MessageResponse(1, "曾发表所在不能为空！");
        }
        if (CommonUtils.isBlank(o.getIntro())) {
            return new MessageResponse(1, "简介不能为空！");
        }
        if (CommonUtils.isBlank(o.getDocText())) {

            return new MessageResponse(1, "正文不能为空！");
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

    public SingleResult<Writing> selectWritingByPrimaryKey(String id) throws Exception {
        SingleResult<Writing> rst = new SingleResult<Writing>();
        rst.setValue(this.writingDao.selectByPrimaryKey(id));
        return rst;
    }

    public MessageResponse deleteWritingByWritingId(String id,
                                                    UserProp userProp) throws Exception {
        this.writingDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除作品", "作品", String.valueOf(id),
                String.valueOf(id), "作品", userProp);
        return new MessageResponse(0, "作品删除完成！");
    }
}
