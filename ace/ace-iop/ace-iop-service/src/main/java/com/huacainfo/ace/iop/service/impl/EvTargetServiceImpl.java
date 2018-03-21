package com.huacainfo.ace.iop.service.impl;

import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.iop.vo.EvTargetQVo;
import com.huacainfo.ace.iop.vo.EvTargetVo;
import com.huacainfo.ace.iop.model.EvTarget;
import com.huacainfo.ace.iop.dao.EvTargetMapper;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.iop.service.EvTargetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("evTargetService")
public class EvTargetServiceImpl implements EvTargetService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EvTargetMapper evTargetMapper;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    @Override
    public PageResult<EvTargetVo> findEvTargetList(EvTargetQVo condition, int start,
                                                   int limit, String orderBy) throws Exception {
        PageResult<EvTargetVo> rst = new PageResult<EvTargetVo>();
        List<EvTargetVo> list = this.evTargetMapper.findList(condition, start,
                start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.evTargetMapper.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    @Override
    public MessageResponse insertEvTarget(EvTarget o, UserProp userProp)
            throws Exception {
        o.setCreateTime(new Date());
        o.setEvTargetId(GUIDUtil.getGUID());
        o.setStatus("1");
        if (CommonUtils.isBlank(o.getEvTargetId())) {
            return new MessageResponse(1, "指标序号不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvTempleteId())) {
            return new MessageResponse(1, "所属模板序号不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvTargetName())) {
            return new MessageResponse(1, "指标名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "指标类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getArgetScore())) {
            return new MessageResponse(1, "指标分值不能为空！");
        }
        if (CommonUtils.isBlank(o.getScoreType())) {
            //return new MessageResponse(1, "指标等次分值不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvPoints())) {
            //return new MessageResponse(1, "评价要点不能为空！");
        }
        if (CommonUtils.isBlank(o.getStaType())) {
            return new MessageResponse(1, "统计方式不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getCreateTime())) {
            return new MessageResponse(1, "创建时间不能为空！");
        }
        o.setCreateTime(new Date());
        int temp = this.evTargetMapper.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "已存在的数据！");
        }
        this.evTargetMapper.insert(o);
        this.dataBaseLogService.log("添加指标", "指标", "", o.getEvTargetName(),
                o.getEvTargetName(), userProp);
        return new MessageResponse(0, "添加指标完成！");
    }

    public MessageResponse updateEvTarget(EvTarget o, UserProp userProp)
            throws Exception {
        o.setStatus("1");
        o.setCreateTime(new Date());
        if (CommonUtils.isBlank(o.getEvTargetId())) {
            return new MessageResponse(1, "指标序号不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvTempleteId())) {
            return new MessageResponse(1, "所属模板序号不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvTargetName())) {
            return new MessageResponse(1, "指标名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "指标类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getArgetScore())) {
            return new MessageResponse(1, "指标分值不能为空！");
        }
        if (CommonUtils.isBlank(o.getScoreType())) {
            //return new MessageResponse(1, "指标等次分值不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvPoints())) {
            //return new MessageResponse(1, "评价要点不能为空！");
        }
        if (CommonUtils.isBlank(o.getStaType())) {
            return new MessageResponse(1, "统计方式不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getCreateTime())) {
            return new MessageResponse(1, "创建时间不能为空！");
        }
        o.setCreateTime(new Date());
        this.evTargetMapper.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更指标", "指标", "", o.getEvTargetName(),
                o.getEvTargetName(), userProp);
        return new MessageResponse(0, "变更指标完成！");
    }

    @Override
    public SingleResult<EvTargetVo> selectEvTargetByPrimaryKey(String id) throws Exception {
        SingleResult<EvTargetVo> rst = new SingleResult<EvTargetVo>();
        rst.setValue(this.evTargetMapper.selectByPrimaryKey(id));
        return rst;
    }

    @Override
    public MessageResponse deleteEvTargetByEvTargetId(String id,
                                                      UserProp userProp) throws Exception {
        MessageResponse rst = new MessageResponse();
        this.evTargetMapper.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除指标", "指标", String.valueOf(id),
                String.valueOf(id), "指标", userProp);
        return rst;
    }

    @Override
    public MessageResponse saveOrUpdateEvTarget(EvTarget o,
                                                UserProp userProp) throws Exception {


        return new MessageResponse(0, "变更指标完成！");
    }
}
