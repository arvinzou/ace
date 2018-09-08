package com.huacainfo.ace.portal.service.impl;

import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.portal.model.TaskCmcc;
import com.huacainfo.ace.portal.service.BackendService;
import com.huacainfo.ace.portal.service.SysInfoService;
import com.huacainfo.ace.portal.service.TaskCmccService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenxiaoke on 2018/3/22.
 */
@Service("cmccBackendService")
public class CmccBackendServiceImpl implements BackendService{
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TaskCmccService taskCmccService;
    @Autowired
    private SysInfoService sysInfoService;

    @Override
    public MessageResponse service(Map<String, Object> data) throws Exception{
        logger.info("cmccBackendService ===============>{}",data);
        TaskCmcc o = new TaskCmcc();
        String mobile= (String) data.get("mobile");
        Map<String, Object> msg = new HashMap<String, Object>();
        msg.put("taskName", data.get("title"));
        msg.put("msg", data.get("title")+"【常德市工商联】");
        msg.put("tel", mobile + "," + mobile);
        CommonBeanUtils.copyMap2Bean(o, msg);
        List addr=new ArrayList<String>();
        addr.add((String) data.get("email"));
        sysInfoService.sendBatchEmail((String) data.get("title"),(String) data.get("content"),addr);
        return this.taskCmccService.insertTaskCmcc(o);
    }
}
