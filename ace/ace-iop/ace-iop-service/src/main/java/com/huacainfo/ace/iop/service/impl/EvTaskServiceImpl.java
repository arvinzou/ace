package com.huacainfo.ace.iop.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.view.CheckTree;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.CheckTreeUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.iop.vo.EvTaskQVo;
import com.huacainfo.ace.iop.vo.EvTaskVo;
import com.huacainfo.ace.iop.model.EvTask;
import com.huacainfo.ace.iop.model.EvInsp;
import com.huacainfo.ace.iop.dao.EvTaskMapper;
import com.huacainfo.ace.iop.dao.EvInspMapper;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.CacheService;
import com.huacainfo.ace.iop.service.EvTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("evTaskService")
public class EvTaskServiceImpl implements EvTaskService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EvTaskMapper evTaskMapper;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    @Autowired
    private CacheService cacheService;
    @Autowired
    private EvInspMapper evInspMapper;

    @Override
    public PageResult<EvTaskVo> findEvTaskList(EvTaskQVo condition, int start,
                                               int limit, String orderBy) throws Exception {
        PageResult<EvTaskVo> rst = new PageResult<EvTaskVo>();
        List<EvTaskVo> list = this.evTaskMapper.findList(condition, start,
                start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.evTaskMapper.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    @Override
    public List<EvTaskVo> findMyEvTaskList(EvTaskQVo condition) throws Exception {
        return this.evTaskMapper.findMyTaskList(condition);

    }

    @Override
    public MessageResponse insertEvTask(EvTask o, UserProp userProp)
            throws Exception {
        o.setCreateTime(new Date());
        o.setEvTaskId(GUIDUtil.getGUID());
        o.setStatus("1");
        o.setAdmin(userProp.getUserId());
        if (CommonUtils.isBlank(o.getEvTaskId())) {
            return new MessageResponse(1, "序号不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "评测类别不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvTaskName())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvObj())) {
            return new MessageResponse(1, "被评测对象不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvTempleteId())) {
            return new MessageResponse(1, "测评模板不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvDiscribtion())) {
            //return new MessageResponse(1, "评测说明不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvDeadline())) {
            return new MessageResponse(1, "开始日期不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvStartDate())) {
            return new MessageResponse(1, "截止日期不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getAdmin())) {
            return new MessageResponse(1, "管理员不能为空！");
        }
        if (CommonUtils.isBlank(o.getCreateTime())) {
            return new MessageResponse(1, "创建时间不能为空！");
        }
        o.setCreateTime(new Date());
        int temp = this.evTaskMapper.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "已存在的数据！");
        }
        this.evTaskMapper.insert(o);
        if (o.getEvObj().indexOf(",") != -1) {
            String[] users = o.getEvObj().split(",");
            for (String evUserId : users) {
                EvInsp record = new EvInsp();
                record.setEvInspId(GUIDUtil.getGUID());
                record.setEvTaskId(o.getEvTaskId());
                record.setEvUserId(evUserId);
                record.setStatus("1");
                record.setCreateTime(new Date());
                if (!evUserId.startsWith("01")) {
                    evInspMapper.insert(record);
                }

            }

        }
        if (o.getCategory().equals("1462546395184")) {

        }
        this.dataBaseLogService.log("添加评测任务", "评测任务", "", o.getEvTaskName(),
                o.getEvTaskName(), userProp);
        return new MessageResponse(0, "添加评测任务完成！");
    }

    @Override
    public MessageResponse updateEvTask(EvTask o, UserProp userProp)
            throws Exception {
        o.setCreateTime(new Date());
        o.setAdmin(userProp.getUserId());
        if (CommonUtils.isBlank(o.getEvTaskId())) {
            return new MessageResponse(1, "序号不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "评测类别不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvTaskName())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvObj())) {
            return new MessageResponse(1, "被评测对象不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvTempleteId())) {
            return new MessageResponse(1, "测评模板不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvDiscribtion())) {
            //return new MessageResponse(1, "评测说明不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvDeadline())) {
            return new MessageResponse(1, "开始日期不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvStartDate())) {
            return new MessageResponse(1, "截止日期不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getAdmin())) {
            return new MessageResponse(1, "管理员不能为空！");
        }
        if (CommonUtils.isBlank(o.getCreateTime())) {
            return new MessageResponse(1, "创建时间不能为空！");
        }
        o.setCreateTime(new Date());
        this.evTaskMapper.updateByPrimaryKey(o);
        evInspMapper.deleteByTaskId(o.getEvTaskId());
        if (o.getEvObj().indexOf(",") != -1) {
            String[] users = o.getEvObj().split(",");
            for (String evUserId : users) {
                EvInsp record = new EvInsp();
                record.setEvInspId(GUIDUtil.getGUID());
                record.setEvTaskId(o.getEvTaskId());
                record.setEvUserId(evUserId);
                record.setStatus("1");
                record.setCreateTime(new Date());
                if (!evUserId.startsWith("01")) {

                    evInspMapper.insert(record);
                }

            }

        }
        this.dataBaseLogService.log("变更评测任务", "评测任务", "", o.getEvTaskName(),
                o.getEvTaskName(), userProp);
        return new MessageResponse(0, "变更评测任务完成！");
    }

    @Override
    public SingleResult<EvTaskVo> selectEvTaskByPrimaryKey(String id) throws Exception {
        SingleResult<EvTaskVo> rst = new SingleResult<EvTaskVo>();
        rst.setValue(this.evTaskMapper.selectByPrimaryKey(id));
        return rst;
    }

    @Override
    public MessageResponse deleteEvTaskByEvTaskId(String id, UserProp userProp)
            throws Exception {
        MessageResponse rst = new MessageResponse();

        this.evTaskMapper.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除评测任务", "评测任务", String.valueOf(id),
                String.valueOf(id), "评测任务", userProp);
        return rst;
    }

    @Override
    public MessageResponse saveOrUpdateEvTask(EvTask o,
                                              UserProp userProp) throws Exception {


        return new MessageResponse(0, "变更完成！");
    }

    @Override
    public MessageResponse updateByPrimaryKeySelective(EvTask o, UserProp userProp)
            throws Exception {
        this.evTaskMapper.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更评测任务", "评测任务", "", o.getEvTaskName(),
                o.getEvTaskName(), userProp);
        return new MessageResponse(0, "变更评测任务完成！");
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CheckTree> selectDepAndUsersTreeList(String id, String taskId) {
        List<Map<String, Object>> list = (List<Map<String, Object>>) cacheService.get("C0002");
        for (Map<String, Object> m : list) {
            m.put("CHECKED", "1");
        }
        CheckTreeUtils checkTreeUtils = new CheckTreeUtils(list);
        return checkTreeUtils.getCheckTreeList(id);
    }

    @Override
    public List<Map<String, Object>> selectVoteTeacherListByQr(Map<String, Object> p) throws Exception {
        return this.evTaskMapper.selectVoteTeacherListByQr(p);
    }

    @Override
    public List<Map<String, Object>> selectVoteClassesListByDeptId(Map<String, Object> p) throws Exception {
        return this.evTaskMapper.selectVoteClassesListByDeptId(p);
    }

    @Override
    public List<Map<String, Object>> selectZMTeacherBYTaskId(String takId, String orderBy) throws Exception {
        return this.evTaskMapper.selectZMTeacherBYTaskId(takId, orderBy);
    }
}
