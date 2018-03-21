package com.huacainfo.ace.iop.service.impl;

import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.iop.vo.EvScoreTemleteQVo;
import com.huacainfo.ace.iop.vo.EvScoreTemleteVo;
import com.huacainfo.ace.iop.model.EvScoreTemlete;
import com.huacainfo.ace.iop.dao.EvScoreTemleteMapper;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.iop.service.EvScoreTemleteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("evScoreTemleteService")
public class EvScoreTemleteServiceImpl implements EvScoreTemleteService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EvScoreTemleteMapper evScoreTemleteMapper;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    @Override
    public PageResult<EvScoreTemleteVo> findEvScoreTemleteList(EvScoreTemleteQVo condition,
                                                               int start, int limit, String orderBy) throws Exception {
        PageResult<EvScoreTemleteVo> rst = new PageResult<EvScoreTemleteVo>();
        List<EvScoreTemleteVo> list = this.evScoreTemleteMapper.findList(
                condition, start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.evScoreTemleteMapper.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    @Override
    public MessageResponse insertEvScoreTemlete(EvScoreTemlete o,
                                                UserProp userProp) throws Exception {
        o.setCreateTime(new Date());
        o.setId(GUIDUtil.getGUID());
        o.setStatus("1");
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "序号不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "分值名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getCreateTime())) {
            return new MessageResponse(1, "创建时间不能为空！");
        }
        int temp = this.evScoreTemleteMapper.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "已存在的数据！");
        }
        this.evScoreTemleteMapper.insert(o);
        this.dataBaseLogService.log("添加指标分值", "指标分值", "", o.getName(),
                o.getName(), userProp);
        return new MessageResponse(0, "添加指标分值完成！");
    }

    @Override
    public MessageResponse updateEvScoreTemlete(EvScoreTemlete o,
                                                UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "序号不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "分值名称不能为空！");
        }
        o.setCreateTime(new Date());
        this.evScoreTemleteMapper.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更指标分值", "指标分值", "", o.getName(),
                o.getName(), userProp);
        return new MessageResponse(0, "变更指标分值完成！");
    }

    @Override
    public SingleResult<EvScoreTemleteVo> selectEvScoreTemleteByPrimaryKey(String id)
            throws Exception {
        SingleResult<EvScoreTemleteVo> rst = new SingleResult<EvScoreTemleteVo>();
        rst.setValue(this.evScoreTemleteMapper.selectByPrimaryKey(id));
        return rst;
    }

    @Override
    public MessageResponse deleteEvScoreTemleteByEvScoreTemleteId(String id,
                                                                  UserProp userProp) throws Exception {
        MessageResponse rst = new MessageResponse();
        this.evScoreTemleteMapper.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除指标分值", "指标分值", String.valueOf(id),
                String.valueOf(id), "指标分值", userProp);
        return rst;
    }

    @Override
    public MessageResponse saveOrUpdateEvScoreTemlete(EvScoreTemlete o,
                                                      UserProp userProp) throws Exception {

        return new MessageResponse(0, "变更指标分值完成！");
    }

    @Override
    public List<EvScoreTemlete> selectListAll() {
        return this.evScoreTemleteMapper.selectListAll();
    }
}
