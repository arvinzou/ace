package com.huacainfo.ace.iop.service.impl;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.iop.vo.EvTaskDataQVo;
import com.huacainfo.ace.iop.vo.EvTaskDataVo;
import com.huacainfo.ace.iop.vo.EvTaskVo;
import com.huacainfo.ace.iop.model.EvTaskUsers;
import com.huacainfo.ace.iop.model.EvTaskData;
import com.huacainfo.ace.iop.dao.EvTaskDataMapper;
import com.huacainfo.ace.iop.dao.EvTaskUsersMapper;
import com.huacainfo.ace.iop.dao.EvTaskMapper;
import com.huacainfo.ace.iop.dao.EvTaskDataSubMapper;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.iop.service.EvTaskDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("evTaskDataService")
public class EvTaskDataServiceImpl implements EvTaskDataService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EvTaskDataMapper evTaskDataMapper;
    @Autowired
    private EvTaskUsersMapper evTaskUsersMapper;
    @Autowired
    private EvTaskMapper evTaskMapper;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private EvTaskDataSubMapper evTaskDataSubMapper;

    @Override
    public PageResult<EvTaskDataVo> findEvTaskDataList(EvTaskDataQVo condition, int start,
                                                       int limit, String orderBy) throws Exception {
        PageResult<EvTaskDataVo> rst = new PageResult<EvTaskDataVo>();
        List<EvTaskDataVo> list = this.evTaskDataMapper.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.evTaskDataMapper.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    @Override
    public MessageResponse insertEvTaskData(String jsons, UserProp userProp)
            throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        JSONArray list = json.getJSONArray("list");
        String evTaskId = json.getString("evTaskId");
        EvTaskVo evTaskVo = this.evTaskMapper.selectByPrimaryKey(evTaskId);
        if (!evTaskVo.getStatus().equals("2")) {
            if (evTaskVo.getStatus().equals("1")) {
                return new MessageResponse(1, "评测还未开始，请等待！");
            } else {
                return new MessageResponse(1, "评测已经结束，谢谢！");
            }

        }
        if (!evTaskVo.getEvStartDate().before(new Date())) {
            return new MessageResponse(1, "评测还未开始，开始时间" + CommonUtils.formatDate(evTaskVo.getEvStartDate(), "yyyy-MM-dd HH:mm") + ",谢谢！");
        }
        if (evTaskVo.getEvDeadline().before(new Date())) {
            return new MessageResponse(1, "评测已经结束，结束时间" + CommonUtils.formatDate(evTaskVo.getEvDeadline(), "yyyy-MM-dd HH:mm") + ",谢谢！");
        }
        for (int i = 0; i < list.size(); i++) {
            JSONObject j = list.getJSONObject(i);
            EvTaskData o = JSON.parseObject(j.toJSONString(), EvTaskData.class);
            o.setCreateTime(new Date());
            o.setEvTaskDataId(GUIDUtil.getGUID());
            o.setStatus("1");
            o.setEvUserId(userProp.getUserId());
            if (CommonUtils.isBlank(o.getEvTaskDataId())) {
                return new MessageResponse(1, "不能为空！");
            }
            if (CommonUtils.isBlank(o.getEvTaskId())) {
                return new MessageResponse(1, "评测任务编号不能为空！");
            }
            if (CommonUtils.isBlank(o.getEvUserId())) {
                return new MessageResponse(1, "评测者编号不能为空！");
            }
            if (CommonUtils.isBlank(o.getEvTarget())) {
                return new MessageResponse(1, "指标序号不能为空！");
            }
            if (CommonUtils.isBlank(o.getEvScoreId())) {
                return new MessageResponse(1, "选项序号不能为空！");
            }
            if (CommonUtils.isBlank(o.getStatus())) {
                return new MessageResponse(1, "状态不能为空！");
            }
            if (CommonUtils.isBlank(o.getCreateTime())) {
                return new MessageResponse(1, "创建时间不能为空！");
            }
            o.setCreateTime(new Date());
            int temp = this.evTaskDataMapper.isExit(o);
            if (temp > 0) {
                return new MessageResponse(1, "已存在的数据！");
            }
            this.evTaskDataMapper.insert(o);
            EvTaskUsers e = new EvTaskUsers();
            e.setStatus("2");
            e.setIp(userProp.getIp());
            e.setAdvise(json.getString("advise"));
            e.setGrade(json.getString("grade"));
            e.setEvTime(new Date());
            e.setUserId(userProp.getUserId());
            e.setEvTaskId(json.getString("evTaskId"));
            this.evTaskUsersMapper.updateVoteByPrimaryKey(e);
            this.dataBaseLogService.log("投票", "投票", "", o.getEvTaskDataId(),
                    o.getEvTaskDataId(), userProp);
        }
        return new MessageResponse(0, "投票完成！");
    }

    @Override
    public MessageResponse updateEvTaskData(EvTaskData o, UserProp userProp)
            throws Exception {

        o.setStatus("1");
        o.setCreateTime(new Date());
        if (CommonUtils.isBlank(o.getEvTaskDataId())) {
            return new MessageResponse(1, "不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvTaskId())) {
            return new MessageResponse(1, "评测任务编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvUserId())) {
            return new MessageResponse(1, "评测者编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvTarget())) {
            return new MessageResponse(1, "指标序号不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvScoreId())) {
            return new MessageResponse(1, "选项序号不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getCreateTime())) {
            return new MessageResponse(1, "创建时间不能为空！");
        }
        o.setCreateTime(new Date());
        this.evTaskDataMapper.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更投票", "投票", "", o.getEvTaskDataId(),
                o.getEvTaskDataId(), userProp);
        return new MessageResponse(0, "变更投票完成！");
    }

    @Override
    public SingleResult<EvTaskDataVo> selectEvTaskDataByPrimaryKey(String id)
            throws Exception {
        SingleResult<EvTaskDataVo> rst = new SingleResult<EvTaskDataVo>();
        rst.setValue(this.evTaskDataMapper.selectByPrimaryKey(id));
        return rst;
    }

    @Override
    public MessageResponse deleteEvTaskDataByEvTaskDataId(String id,
                                                          UserProp userProp) throws Exception {
        MessageResponse rst = new MessageResponse();
        this.evTaskDataMapper.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除投票", "投票", String.valueOf(id),
                String.valueOf(id), "投票", userProp);
        return rst;
    }

    @Override
    public MessageResponse saveOrUpdateEvTaskData(EvTaskData o,
                                                  UserProp userProp) throws Exception {

        return new MessageResponse(0, "变更投票完成！");
    }

    @Override
    public MessageResponse insertWWWEvTaskData(String jsons, String ip)
            throws Exception {

        return new MessageResponse(0, "投票完成！");
    }

    @Override
    public MessageResponse insertZMEvTaskData(String jsons, String mobile)
            throws Exception {

        return new MessageResponse(0, "投票完成！");
    }
}
