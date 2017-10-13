package com.huacainfo.ace.operana.service.impl;
import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.operana.dao.MeetingDao;
import com.huacainfo.ace.operana.service.TaskOperanaService;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.huacainfo.ace.common.kafka.KafkaProducerService;

/**
 * Created by chenxiaoke on 2017/10/9.
 */
@Service("taskOperanaService")
public class TaskOperanaServiceImpl implements TaskOperanaService{


    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private MeetingDao meetingDao;


    @Scheduled(cron="0 49 08 ? * *")
    public  void autoSendEmail() throws Exception{
        this.logger.info("autoSendEmail executed");
        List<Map<String,Object>> emails=meetingDao.selectEmailForNotice();
        List<Map<String,Object>> tasks=meetingDao.selectTaskForEmail();
        if(CommonUtils.isBlank(tasks)){
            this.logger.info("{}","没有符合条件的任务检查");
            return;
        }
        StringBuffer html=new StringBuffer("<table class='hovertable'>");
        html.append("<tr style='font-weight:800'>");
        html.append("<th>");
        html.append("序号");
        html.append("</th>");

        html.append("<th>");
        html.append("部门");
        html.append("</th>");

        html.append("<th>");
        html.append("总任务数");
        html.append("</th>");

        html.append("<th>");
        html.append("关闭任务数");
        html.append("</th>");

        html.append("<th>");
        html.append("打开任务数");
        html.append("</th>");

        html.append("</tr>");
        int i=1;
        for(Map<String,Object> e:tasks){
            html.append("<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#d4e3e5';\">");
            html.append("<td>");
            html.append(i);
            html.append("</td>");

            html.append("<td>");
            html.append(e.get("name"));
            html.append("</td>");

            html.append("<td>");
            html.append(e.get("total"));
            html.append("</td>");

            html.append("<td>");
            html.append(e.get("closed"));
            html.append("</td>");

            html.append("<td>");
            html.append(e.get("opened"));
            html.append("</td>");

            html.append("</tr>");
            i++;
        }
        html.append("</table>");
        StringBuffer userList=new StringBuffer();
        for( Map<String,Object> e:emails) {
            userList.append(e.get("name"));
            userList.append(" ");
        }
        Map<String,Object> model=new HashMap<String,Object>();
        model.put("tasklist", html.toString());
        model.put("userlist", userList.toString());
        model.put("sysDate", CommonUtils.formatDate(new Date()));
        this.logger.info("{}",model);
        String subject =CommonUtils.getStringByTemplate("email.subject.task.vm", model);
        String content=CommonUtils.getStringByTemplate("email.task.vm", model);

        Map<String, String> data = new HashMap<String, String>();
        data.put("subject", subject);
        data.put("content", content);
        for( Map<String,Object> e:emails) {
            data.put("to", (String) e.get("email"));
            this.logger.info("{}",data);
            this.kafkaProducerService.sendMsg("GESP_SYS_INFO", data);
        }

    }
}
