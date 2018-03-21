package com.huacainfo.ace.iop.service.impl;

import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.iop.vo.EvCategoryQVo;
import com.huacainfo.ace.iop.vo.EvCategoryVo;
import com.huacainfo.ace.iop.model.EvCategory;
import com.huacainfo.ace.iop.dao.EvCategoryMapper;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.iop.service.EvCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("evCategoryService")
public class EvCategoryServiceImpl implements EvCategoryService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EvCategoryMapper evCategoryMapper;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    @Override
    public PageResult<EvCategoryVo> findEvCategoryList(EvCategoryQVo condition, int start,
                                                       int limit, String orderBy) throws Exception {
        PageResult<EvCategoryVo> rst = new PageResult<EvCategoryVo>();
        List<EvCategoryVo> list = this.evCategoryMapper.findList(condition, start,
                start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.evCategoryMapper.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    @Override
    public MessageResponse insertEvCategory(EvCategory o, UserProp userProp)
            throws Exception {
        o.setCode(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getCode())) {
            return new MessageResponse(1, "编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        o.setStatus("1");
        o.setCreateTime(new Date());
        int temp = this.evCategoryMapper.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "已存在的数据！");
        }
        this.evCategoryMapper.insert(o);
        this.dataBaseLogService.log("添加评测类别", "评测类别", "", o.getName(),
                o.getName(), userProp);
        return new MessageResponse(0, "添加评测类别完成！");
    }

    @Override
    public MessageResponse updateEvCategory(EvCategory o, UserProp userProp)
            throws Exception {
        o.setStatus("1");
        if (CommonUtils.isBlank(o.getCode())) {
            return new MessageResponse(1, "编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        o.setCreateTime(new Date());
        this.evCategoryMapper.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更评测类别", "评测类别", "", o.getName(),
                o.getName(), userProp);
        return new MessageResponse(0, "变更评测类别完成！");
    }

    @Override
    public SingleResult<EvCategoryVo> selectEvCategoryByPrimaryKey(String id)
            throws Exception {
        SingleResult<EvCategoryVo> rst = new SingleResult<EvCategoryVo>();
        rst.setValue(this.evCategoryMapper.selectByPrimaryKey(id));
        return rst;
    }

    @Override
    public MessageResponse deleteEvCategoryByEvCategoryId(String id,
                                                          UserProp userProp) throws Exception {
        MessageResponse rst = new MessageResponse();
        this.evCategoryMapper.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除评测类别", "评测类别", String.valueOf(id), String.valueOf(id),
                "评测类别", userProp);
        return rst;
    }

    @Override
    public MessageResponse saveOrUpdateEvCategory(EvCategory o, UserProp userProp)
            throws Exception {
        return new MessageResponse(0, "变更评测类别完成！");
    }
}
