package com.huacainfo.ace.iop.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.iop.vo.EvTaskUsersQVo;
import com.huacainfo.ace.iop.vo.EvTaskUsersVo;
import com.huacainfo.ace.iop.vo.EvTaskVo;
import com.huacainfo.ace.iop.model.EvTaskUsers;
import com.huacainfo.ace.iop.dao.EvTaskUsersMapper;
import com.huacainfo.ace.iop.dao.EvTaskMapper;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.iop.service.EvTaskUsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("evTaskUsersService")
public class EvTaskUsersServiceImpl implements EvTaskUsersService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EvTaskUsersMapper evTaskUsersMapper;
    @Autowired
    private EvTaskMapper evTaskMapper;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    @Override
    public PageResult<EvTaskUsersVo> findEvTaskUsersList(EvTaskUsersQVo condition,
                                                         int start, int limit, String orderBy) throws Exception {
        PageResult<EvTaskUsersVo> rst = new PageResult<EvTaskUsersVo>();
        List<EvTaskUsersVo> list = this.evTaskUsersMapper.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.evTaskUsersMapper.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    @Override
    public MessageResponse insertEvTaskUsers(String jons, UserProp userProp)
            throws Exception {
        JSONObject json = JSON.parseObject(jons);
        JSONArray list = json.getJSONArray("list");
        for (int i = 0; i < list.size(); i++) {
            JSONObject j = list.getJSONObject(i);
            EvTaskUsers o = JSON.parseObject(j.toJSONString(), EvTaskUsers.class);
            o.setCreateTime(new Date());
            o.setId(GUIDUtil.getGUID());
            o.setStatus("1");
            o.setTaskTime(new Date());
            if (CommonUtils.isBlank(o.getId())) {
                return new MessageResponse(1, "序号不能为空！");
            }
            if (CommonUtils.isBlank(o.getEvTaskId())) {
                return new MessageResponse(1, "归属任务序号不能为空！");
            }
            if (CommonUtils.isBlank(o.getUserId())) {
                return new MessageResponse(1, "评测着不能为空！");
            }
            if (CommonUtils.isBlank(o.getStatus())) {
                return new MessageResponse(1, "状态不能为空！");
            }
            if (CommonUtils.isBlank(o.getCreateTime())) {
                return new MessageResponse(1, "创建时间不能为空！");
            }
            o.setCreateTime(new Date());
            int temp = this.evTaskUsersMapper.isExit(o);
            if (temp > 0) {
                return new MessageResponse(1, "已存在的数据！");
            }
            this.evTaskUsersMapper.insert(o);
            this.dataBaseLogService.log("添加评测账号", "评测账号", "", o.getUserId(),
                    o.getUserId(), userProp);
        }

        return new MessageResponse(0, "添加评测账号完成！");
    }

    @Override
    public MessageResponse updateEvTaskUsers(EvTaskUsers o, UserProp userProp)
            throws Exception {
        o.setStatus("1");
        o.setCreateTime(new Date());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "序号不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvTaskId())) {
            return new MessageResponse(1, "归属任务序号不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "评测着不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getCreateTime())) {
            return new MessageResponse(1, "创建时间不能为空！");
        }
        o.setCreateTime(new Date());
        this.evTaskUsersMapper.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更评测账号", "评测账号", "", o.getUserId(),
                o.getUserId(), userProp);
        return new MessageResponse(0, "变更评测账号完成！");
    }

    @Override
    public SingleResult<EvTaskUsersVo> selectEvTaskUsersByPrimaryKey(String id)
            throws Exception {
        SingleResult<EvTaskUsersVo> rst = new SingleResult<EvTaskUsersVo>();
        rst.setValue(this.evTaskUsersMapper.selectByPrimaryKey(id));
        return rst;
    }

    @Override
    public MessageResponse deleteEvTaskUsersByEvTaskUsersId(String id,
                                                            UserProp userProp) throws Exception {
        MessageResponse rst = new MessageResponse();
        if (id.indexOf(",") != -1) {
            for (String ids : id.split(",")) {
                EvTaskUsers o = this.evTaskUsersMapper.selectByPrimaryKey(ids);
                if (o.getEvTime() != null) {
                    return new MessageResponse(1, "发生投票的账号不能删除！");
                }
                this.evTaskUsersMapper.deleteByPrimaryKey(ids);
                this.dataBaseLogService.log("删除评测账号", "评测账号", String.valueOf(ids),
                        String.valueOf(ids), "评测账号", userProp);
            }
        } else {
            EvTaskUsers o = this.evTaskUsersMapper.selectByPrimaryKey(id);
            if (o.getEvTime() != null) {
                return new MessageResponse(1, "发生投票的账号不能删除！");
            }
            this.evTaskUsersMapper.deleteByPrimaryKey(id);
            this.dataBaseLogService.log("删除评测账号", "评测账号", String.valueOf(id),
                    String.valueOf(id), "评测账号", userProp);
        }


        return rst;
    }

    @Override
    public MessageResponse saveOrUpdateEvTaskUsers(EvTaskUsers o,
                                                   UserProp userProp) throws Exception {


        return new MessageResponse(0, "变更评测账号完成！");
    }

    @Override
    public List<Map<String, Object>> selectUserListByDeptId(String evTaskId, int limit) {
        MessageResponse rst = new MessageResponse();
        String depId = "0105";
        EvTaskVo o = evTaskMapper.selectByPrimaryKey(evTaskId);
        if (o != null) {
            depId = o.getDeptId();
        }
        return this.evTaskUsersMapper
                .selectUserListByDeptId(evTaskId, depId, limit);
    }

    @Override
    public MessageResponse updateForReset(String id, UserProp userProp) {
        EvTaskUsers e = this.evTaskUsersMapper.selectByPrimaryKey(id);
        this.evTaskUsersMapper.updateForReset(id, e.getEvTaskId(), e.getUserId());
        this.dataBaseLogService.log("设置重新投票", "设置重新投票", String.valueOf(id),
                String.valueOf(id), "设置重新投票", userProp);
        return new MessageResponse(0, "设置重新投票完成！");
    }

    @Override
    public List<Map<String, Object>> selectPrintUserListByDeptId(String evTaskId, int limit) {
        MessageResponse rst = new MessageResponse();
        String depId = "0105";

        return this.evTaskUsersMapper
                .selectPrintUserListByDeptId(evTaskId, depId, limit);

    }
}
