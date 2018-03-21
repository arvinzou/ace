package com.huacainfo.ace.iop.service.impl;

import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.iop.vo.EvScoreTemleteSubQVo;
import com.huacainfo.ace.iop.vo.EvScoreTemleteSubVo;
import com.huacainfo.ace.iop.model.EvScoreTemleteSub;
import com.huacainfo.ace.iop.dao.EvScoreTemleteSubMapper;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.iop.service.EvScoreTemleteSubService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("evScoreTemleteSubService")
public class EvScoreTemleteSubServiceImpl implements EvScoreTemleteSubService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EvScoreTemleteSubMapper evScoreTemleteSubMapper;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    @Override
    public PageResult<EvScoreTemleteSubVo> findEvScoreTemleteSubList(
            EvScoreTemleteSubQVo condition, int start, int limit, String orderBy)
            throws Exception {
        PageResult<EvScoreTemleteSubVo> rst = new PageResult<EvScoreTemleteSubVo>();
        List<EvScoreTemleteSubVo> list = this.evScoreTemleteSubMapper.findList(
                condition, start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.evScoreTemleteSubMapper.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }
    @Override
    public MessageResponse insertEvScoreTemleteSub(EvScoreTemleteSub o,
                                                   UserProp userProp) throws Exception {
        o.setCreateTime(new Date());
        o.setId(GUIDUtil.getGUID());
        o.setStatus("1");
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "序号不能为空！");
        }
        if (CommonUtils.isBlank(o.getScoreTempleId())) {
            return new MessageResponse(1, "不能为空！");
        }
        if (CommonUtils.isBlank(o.getSkey())) {
            return new MessageResponse(1, "选择标示不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getSqe())) {
            return new MessageResponse(1, "顺序不能为空！");
        }
        if (CommonUtils.isBlank(o.getScore())) {
            return new MessageResponse(1, "分值不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getCreateTime())) {
            return new MessageResponse(1, "创建时间不能为空！");
        }

        int temp = this.evScoreTemleteSubMapper.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "已存在的数据！");
        }
        this.evScoreTemleteSubMapper.insert(o);
        this.dataBaseLogService.log("添加指标项目", "指标项目", "", o.getName(),
                o.getName(), userProp);
        return new MessageResponse(0, "添加指标项目完成！");
    }

    @Override
    public MessageResponse updateEvScoreTemleteSub(EvScoreTemleteSub o,
                                                   UserProp userProp) throws Exception {
        o.setStatus("1");
        o.setCreateTime(new Date());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "序号不能为空！");
        }
        if (CommonUtils.isBlank(o.getScoreTempleId())) {
            return new MessageResponse(1, "不能为空！");
        }
        if (CommonUtils.isBlank(o.getSkey())) {
            return new MessageResponse(1, "选择标示不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getSqe())) {
            return new MessageResponse(1, "顺序不能为空！");
        }
        if (CommonUtils.isBlank(o.getScore())) {
            return new MessageResponse(1, "分值不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getCreateTime())) {
            return new MessageResponse(1, "创建时间不能为空！");
        }
        this.evScoreTemleteSubMapper.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更指标项目", "指标项目", "", o.getName(),
                o.getName(), userProp);
        return new MessageResponse(0, "变更指标项目完成！");
    }

    @Override
    public SingleResult<EvScoreTemleteSubVo> selectEvScoreTemleteSubByPrimaryKey(String id)
            throws Exception {
        SingleResult<EvScoreTemleteSubVo> rst = new SingleResult<EvScoreTemleteSubVo>();
        rst.setValue(this.evScoreTemleteSubMapper.selectByPrimaryKey(id));
        return rst;
    }

    @Override
    public MessageResponse deleteEvScoreTemleteSubByEvScoreTemleteSubId(String id,
                                                                        UserProp userProp) throws Exception {
        MessageResponse rst = new MessageResponse();
        this.evScoreTemleteSubMapper.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除指标项目", "指标项目", String.valueOf(id),
                String.valueOf(id), "指标项目", userProp);
        return rst;
    }

    @Override
    public MessageResponse saveOrUpdateEvScoreTemleteSub(EvScoreTemleteSub o,
                                                         UserProp userProp) throws Exception {

        return new MessageResponse(0, "变更指标项目完成！");
    }
}
