package com.huacainfo.ace.iop.service.impl;

import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.iop.vo.EvTempleteQVo;
import com.huacainfo.ace.iop.vo.EvTempleteVo;
import com.huacainfo.ace.iop.model.EvTemplete;
import com.huacainfo.ace.iop.dao.EvTempleteMapper;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.iop.service.EvTempleteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("evTempleteService")
public class EvTempleteServiceImpl implements EvTempleteService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EvTempleteMapper evTempleteMapper;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    @Override
    public PageResult<EvTempleteVo> findEvTempleteList(EvTempleteQVo condition, int start,
                                                       int limit, String orderBy) throws Exception {
        PageResult<EvTempleteVo> rst = new PageResult<EvTempleteVo>();
        List<EvTempleteVo> list = this.evTempleteMapper.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.evTempleteMapper.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    @Override
    public MessageResponse insertEvTemplete(EvTemplete o, UserProp userProp)
            throws Exception {
        o.setStatus("1");
        o.setCreateTime(new Date());
        if (CommonUtils.isBlank(o.getEvTempleteId())) {
            return new MessageResponse(1, "编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvName())) {
            return new MessageResponse(1, "模板名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "模板类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvDiscription())) {
            //return new MessageResponse(1, "模板描述不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getCreateTime())) {
            return new MessageResponse(1, "创建时间不能为空！");
        }
        o.setCreateTime(new Date());
        int temp = this.evTempleteMapper.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "已存在的数据！");
        }
        this.evTempleteMapper.insert(o);
        this.dataBaseLogService.log("添加评测模板", "评测模板", "", o.getEvName(),
                o.getEvName(), userProp);
        return new MessageResponse(0, "添加评测模板完成！");
    }

    @Override
    public MessageResponse updateEvTemplete(EvTemplete o, UserProp userProp)
            throws Exception {
        o.setStatus("1");
        o.setCreateTime(new Date());
        if (CommonUtils.isBlank(o.getEvTempleteId())) {
            return new MessageResponse(1, "编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvName())) {
            return new MessageResponse(1, "模板名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "模板类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvDiscription())) {
            //return new MessageResponse(1, "模板描述不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getCreateTime())) {
            return new MessageResponse(1, "创建时间不能为空！");
        }
        o.setCreateTime(new Date());
        this.evTempleteMapper.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更评测模板", "评测模板", "", o.getEvName(),
                o.getEvName(), userProp);
        return new MessageResponse(0, "变更评测模板完成！");
    }

    @Override
    public SingleResult<EvTempleteVo> selectEvTempleteByPrimaryKey(String id)
            throws Exception {
        SingleResult<EvTempleteVo> rst = new SingleResult<EvTempleteVo>();
        rst.setValue(this.evTempleteMapper.selectByPrimaryKey(id));
        return rst;
    }

    @Override
    public MessageResponse deleteEvTempleteByEvTempleteId(String id,
                                                          UserProp userProp) throws Exception {
        MessageResponse rst = new MessageResponse();
        this.evTempleteMapper.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除评测模板", "评测模板", String.valueOf(id),
                String.valueOf(id), "评测模板", userProp);
        return rst;
    }

    @Override
    public MessageResponse saveOrUpdateEvTemplete(EvTemplete o,
                                                  UserProp userProp) throws Exception {
        return new MessageResponse(0, "变更评测模板完成！");
    }
}
