package com.huacainfo.ace.portal.service.impl;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.threadpool.RunThread;
import com.huacainfo.ace.common.threadpool.ThreadPool;
import com.huacainfo.ace.common.threadpool.ThreadProcess;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.HttpRuquest;
import com.huacainfo.ace.common.tools.HttpSend;
import com.huacainfo.ace.portal.dao.QueueCmccHisMapper;
import com.huacainfo.ace.portal.dao.QueueCmccWaitMapper;
import com.huacainfo.ace.portal.dao.TaskCmccMapper;
import com.huacainfo.ace.portal.model.QueueCmccHis;
import com.huacainfo.ace.portal.model.QueueCmccWait;
import com.huacainfo.ace.portal.model.TaskCmcc;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.TaskCmccService;
import com.huacainfo.ace.portal.vo.TaskCmccQVo;
import com.huacainfo.ace.portal.vo.TaskCmccVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.*;

@Service("taskCmccService")
public class TaskCmccServiceImpl implements TaskCmccService, ThreadProcess {
    Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private TaskCmccMapper taskCmccMapper;
    @Autowired
    private QueueCmccWaitMapper queueCmccWaitMapper;
    @Autowired
    private QueueCmccHisMapper queueCmccHisMapper;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    @Value("#{prop.req}")
    private String req;

    @Value("#{prop.pwd}")
    private String pwd;

    @Value("#{prop.sourceadd}")
    private String sourceadd;

    @Value("#{prop.url}")
    private String url;

    @Value("#{prop.suffix}")
    private String suffix;


    private ThreadPool threadPool;

    public TaskCmccServiceImpl() {
        logger.info("启动线程池->初始化 " + 5);
        threadPool = new ThreadPool(this, 5, 20, 1000 * 10);
        threadPool.start();
    }

    public PageResult<TaskCmccVo> findTaskCmccList(TaskCmccQVo condition, int start, int limit, String orderBy)
            throws Exception {
        PageResult<TaskCmccVo> rst = new PageResult<TaskCmccVo>();
        List<TaskCmccVo> list = this.taskCmccMapper.findList(condition, start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.taskCmccMapper.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    public MessageResponse insertTaskCmcc(TaskCmcc o, UserProp userProp) throws Exception {
        o.setCreateTime(new Date());
        o.setCreateUserId(userProp.getUserId());
        o.setStatus("0");
        o.setTaskId(String.valueOf(new Date().getTime()));
        if (CommonUtils.isBlank(o.getTaskId())) {
            return new MessageResponse(1, "任务编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getTaskName())) {
            return new MessageResponse(1, "任务名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getTel())) {
            return new MessageResponse(1, "手机号不能为空！");
        }
        if (CommonUtils.isBlank(o.getMsg())) {
            return new MessageResponse(1, "短信不能为空！");
        }

        this.taskCmccMapper.insert(o);
        this.dataBaseLogService.log("添加短信任务", "短信任务", "", o.getTaskName(), o.getTaskName(), userProp);
        return new MessageResponse(0, "添加短信任务完成！");
    }

    public MessageResponse insertTaskCmcc(TaskCmcc o) throws Exception {
        o.setCreateTime(new Date());
        o.setStatus("0");
        o.setTaskId(String.valueOf(new Date().getTime()));
        if (CommonUtils.isBlank(o.getTaskId())) {
            return new MessageResponse(1, "任务编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getTaskName())) {
            return new MessageResponse(1, "任务名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getTel())) {
            return new MessageResponse(1, "手机号不能为空！");
        }
        if (CommonUtils.isBlank(o.getMsg())) {
            return new MessageResponse(1, "短信不能为空！");
        }
        //this.taskCmccMapper.insert(o);

        String[] telArr = o.getTel().split(";");
        for (int i = 0; i < telArr.length; i++) {
            if (telArr[i] != null && telArr[i].indexOf(",") != -1) {
                QueueCmccWait q = new QueueCmccWait();
                q.setCreateTime(new Date());
                q.setMsg(o.getMsg());
                q.setQueueId(UUID.randomUUID().toString());
                q.setName(telArr[i].split(",")[1]);
                q.setTel(telArr[i].split(",")[0]);
                //queueCmccWaitMapper.insert(q);
                if (!threadPool.dataQueue.contains(o)) {
                    threadPool.addData(o);
                    this.logger.info("====================短信通道==================");
                    logger.info("add new task 1");
                    logger.info("dataQueue " + threadPool.dataQueue.procSize());
                    logger.info("threadSize " + threadPool.getThreadList().size());
                }
            }

        }

        return new MessageResponse(0, "短信已发送，请注意查收！");
    }

    public MessageResponse updateTaskCmcc(TaskCmcc o, UserProp userProp) throws Exception {
        o.setCreateTime(new Date());
        o.setCreateUserId(userProp.getUserId());
        o.setStatus("0");
        if (CommonUtils.isBlank(o.getTaskId())) {
            return new MessageResponse(1, "任务编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getTaskName())) {
            return new MessageResponse(1, "任务名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getTel())) {
            return new MessageResponse(1, "手机号不能为空！");
        }
        if (CommonUtils.isBlank(o.getMsg())) {
            return new MessageResponse(1, "短信不能为空！");
        }
        this.taskCmccMapper.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更短信任务", "短信任务", "", o.getTaskName(), o.getTaskName(), userProp);
        return new MessageResponse(0, "变更短信任务完成！");
    }

    public MessageResponse deleteTaskCmccByTaskCmccId(String id, UserProp userProp) throws Exception {
        MessageResponse rst = new MessageResponse();
        this.taskCmccMapper.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除短信任务", "短信任务", String.valueOf(id), String.valueOf(id), "短信任务", userProp);
        return rst;
    }

    public MessageResponse updateTaskStatusCmccByTaskCmccId(String id, UserProp userProp) throws Exception {
        MessageResponse rst = new MessageResponse();
        this.taskCmccMapper.updateStatusByPrimaryKey(id, "0");
        this.dataBaseLogService.log("重新发送短信", "短信任务", String.valueOf(id), String.valueOf(id), "短信任务", userProp);
        return rst;
    }

    @Scheduled(fixedDelay = 5000)
    public void queueTask() throws Exception {
        this.logger.info("queueTask executed");
        List<TaskCmcc> list = this.taskCmccMapper.selectByTask();
        for (TaskCmcc o : list) {
            String[] telArr = o.getTel().split(";");
            for (int i = 0; i < telArr.length; i++) {
                if (telArr[i] != null && telArr[i].indexOf(",") != -1) {
                    QueueCmccWait q = new QueueCmccWait();
                    q.setCreateTime(new Date());
                    q.setMsg(o.getMsg());
                    q.setQueueId(UUID.randomUUID().toString());
                    q.setName(telArr[i].split(",")[1]);
                    q.setTel(telArr[i].split(",")[0]);
                    queueCmccWaitMapper.insert(q);
                }

            }
            this.taskCmccMapper.updateStatusByPrimaryKey(o.getTaskId(), "1");
        }
    }

    @Scheduled(fixedDelay = 5000)
    public void queueTaskDetail() throws Exception {
        this.logger.info("===============================start dispatch for task queueTaskDetail=================================");
        this.logger.info("threadPool.dataSize:" + threadPool.dataSize());
        if (threadPool.dataSize() < 1) {
            List<QueueCmccWait> list = this.taskCmccMapper.selectQueueByTask();
            for (QueueCmccWait o : list) {
                if (!threadPool.dataQueue.contains(o)) {
                    threadPool.addData(o);
                }
            }
            logger.info("add new task " + list.size());
            logger.info("dataQueue " + threadPool.dataQueue.procSize());
            logger.info("threadSize " + threadPool.getThreadList().size());
        }

    }

    /**
     * 业务处理回调方法
     *
     * @param rt
     * @param obj
     * @throws UnsupportedEncodingException
     */
    public void execute(RunThread rt, Object obj) {
        this.logger.info("execute start");
        QueueCmccWait o = (QueueCmccWait) obj;
        QueueCmccHis record = new QueueCmccHis();
        record.setAddTime(o.getCreateTime());
        record.setCreateTime(new Date());
        record.setMsg(o.getMsg());
        record.setName(o.getName());
        record.setQueueId(o.getQueueId());
        record.setTel(o.getTel());
        record.setRemark("OK");
        Map<String, String> params = new HashMap<String, String>();
        params.put("req", req);
        params.put("pwd", pwd);
        params.put("sourceadd", sourceadd);
        params.put("phone", o.getTel());
        params.put("content", o.getMsg());
        String strSmsParam = null;
        try {
            strSmsParam = "reg=" + params.get("req") + "&pwd=" + params.get("pwd") + "&sourceadd="
                    + params.get("sourceadd") + "&phone=" + params.get("phone") + "&content="
                    + HttpSend.paraTo16(params.get("content"));
        } catch (UnsupportedEncodingException e) {
            this.logger.error(e);
        }
        String p = HttpRuquest.requestPost(url, strSmsParam);
        this.logger.info(url);
        this.logger.info(p);
        record.setRemark(p);

        this.queueCmccHisMapper.batchInsert(record);
    }

    public SingleResult<TaskCmcc> selectBYId(String id) throws Exception {
        SingleResult<TaskCmcc> rst = new SingleResult<TaskCmcc>();
        rst.setValue(this.taskCmccMapper.selectByPrimaryKey(id));
        return rst;
    }

    /**
     * 添加短信发送任务
     *
     * @param taskName  任务名称
     * @param message   发送给内容
     * @param mobileStr 目标号码 :nickName1,mobile1;nickName2,mobile2;
     * @return 发送结果
     */
    @Override
    public MessageResponse addTaskCmcc(String taskName, String message, String mobileStr) throws Exception {
        TaskCmcc o = new TaskCmcc();
        Map<String, Object> msg = new HashMap<String, Object>();
        msg.put("taskName", taskName);
        msg.put("msg", message);
        msg.put("tel", mobileStr);
        CommonBeanUtils.copyMap2Bean(o, msg);

        return insertTaskCmcc(o);
    }

    /**
     * 执行时间超过指定时间的回调方法
     *
     * @param rt
     */
    public void timeOut(RunThread rt) {

    }

    @Scheduled(fixedDelay = 5000)
    public void workFlowMsgTask() {
        logger.info("workFlowMsgTask");
    }

}
