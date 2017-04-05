package com.huacainfo.ace.kernel.service.impl;


import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.kernel.dao.WriterDao;
import com.huacainfo.ace.kernel.model.Writer;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.kernel.service.WriterService;
import com.huacainfo.ace.kernel.vo.WriterVo;
import com.huacainfo.ace.kernel.vo.WriterQVo;
@Service("writerService")
public class WriterServiceImpl implements WriterService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private WriterDao writerDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    public PageResult<WriterVo> findWriterList(WriterQVo condition, int start,
                                               int limit, String orderBy) throws Exception {
        PageResult<WriterVo> rst = new PageResult<WriterVo>();
        List<WriterVo> list = this.writerDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.writerDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    public MessageResponse insertWriter(Writer o, UserProp userProp)
            throws Exception {
        o.setId(UUID.randomUUID().toString());

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "姓名不能为空！");
        }
        if (CommonUtils.isBlank(o.getSex())) {
            return new MessageResponse(1, "性别不能为空！");
        }
        if (CommonUtils.isBlank(o.getBirthday())) {
            return new MessageResponse(1, "出生日期不能为空！");
        }
        if (CommonUtils.isBlank(o.getPhoto())) {
            return new MessageResponse(1, "形象照片不能为空！");
        }
        if (CommonUtils.isBlank(o.getIntro())) {
            return new MessageResponse(1, "个人简介不能为空！");
        }

        if (CommonUtils.isBlank(o.getDescri())) {
            return new MessageResponse(1, "公开发表与获奖情况不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "分类不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        int temp = this.writerDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "作家名称重复！");
        }
        o.setReading(0L);
        o.setPcode(CommonUtils.getPinYinHeadChar(o.getName()));
        o.setCreateDate(new Date());
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        o.setStatus("1");
        this.writerDao.insert(o);
        this.dataBaseLogService.log("添加作家", "作家", "", o.getName(),
                o.getName(), userProp);
        return new MessageResponse(0, "添加作家完成！");
    }

    public MessageResponse updateWriter(Writer o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "姓名不能为空！");
        }
        if (CommonUtils.isBlank(o.getSex())) {
            return new MessageResponse(1, "性别不能为空！");
        }
        if (CommonUtils.isBlank(o.getBirthday())) {
            return new MessageResponse(1, "出生日期不能为空！");
        }
        if (CommonUtils.isBlank(o.getPhoto())) {
            return new MessageResponse(1, "形象照片不能为空！");
        }
        if (CommonUtils.isBlank(o.getIntro())) {
            return new MessageResponse(1, "个人简介不能为空！");
        }
        if (CommonUtils.isBlank(o.getDescri())) {
            return new MessageResponse(1, "公开发表与获奖情况不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "分类不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        o.setPcode(CommonUtils.getPinYinHeadChar(o.getName()));
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.writerDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更作家", "作家", "", o.getName(),
                o.getName(), userProp);
        return new MessageResponse(0, "变更作家完成！");
    }

    public SingleResult<Writer> selectWriterByPrimaryKey(String id) throws Exception {
        SingleResult<Writer> rst = new SingleResult<Writer>();
        rst.setValue(this.writerDao.selectByPrimaryKey(id));
        return rst;
    }

    public MessageResponse deleteWriterByWriterId(String id,
                                                  UserProp userProp) throws Exception {
        this.writerDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除作家", "作家", String.valueOf(id),
                String.valueOf(id), "作家", userProp);
        return new MessageResponse(0, "作家删除完成！");
    }
    public Map<String, Object> selectAuthor(Map<String, Object> params) throws Exception{
        Map<String, Object> rst = new HashMap<String, Object>();
        List<Map<String, String>> list = this.writerDao.selectAuthor(params);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }
}
