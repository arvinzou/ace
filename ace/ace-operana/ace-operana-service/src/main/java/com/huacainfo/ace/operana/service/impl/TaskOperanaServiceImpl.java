package com.huacainfo.ace.operana.service.impl;
import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.operana.dao.MeetingDao;
import com.huacainfo.ace.operana.dao.TpaDao;
import com.huacainfo.ace.operana.service.TaskOperanaService;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.huacainfo.ace.common.kafka.KafkaProducerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
/**
 * Created by chenxiaoke on 2017/10/9.
 */

@PropertySource("classpath:config.properties")
@Service("taskOperanaService")
public class TaskOperanaServiceImpl implements TaskOperanaService{


    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private MeetingDao meetingDao;

    @Autowired
    private TpaDao tpaDao;


    @Value("#{config[tasktime]}")
    private Integer tasktime;




    @Scheduled(cron="${jobs.autoSendEmailLeader}")
    public  void autoSendEmailLeader() throws Exception{
        this.logger.info("autoSendEmail executed");
        List<Map<String,Object>> emails=meetingDao.selectEmailForNotice();
        List<Map<String,Object>> tasks=meetingDao.selectTaskForEmail(this.tasktime);
        if(CommonUtils.isBlank(tasks)){
            this.logger.info("{}","没有符合条件的任务检查");
            return;
        }
        StringBuffer html=new StringBuffer("<table class='hovertable'>");

       html.append("<tr>");
       html.append("<td colspan='6' style='text-align:center;font-weight:800'>");
       html.append("电装会议纪要进度跟踪报告");
       html.append("</td>");
       html.append("</tr>");

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

        html.append("<th>");
        html.append("KPI");
        html.append("</th>");

        html.append("</tr>");


        int i=1;
        for(Map<String,Object> e:tasks){
            html.append("<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#fff';\">");
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

            html.append("<td>");
            html.append(e.get("normName"));
            html.append("</td>");

            html.append("</tr>");
            i++;
        }

        StringBuffer userList=new StringBuffer();
        for( Map<String,Object> e:emails) {
            userList.append(e.get("name"));
            userList.append(" ");
        }

       html.append("<tr>");
       html.append("<td colspan='6' style='text-align:left;font-weight:800'>");
       html.append("抄送：");
       html.append(userList.toString());
       html.append("</td>");
       html.append("</tr>");
       html.append("</table>");

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

    @Scheduled(cron="${jobs.autoSendEmailLiableLongTime}")
   // @Scheduled(fixedDelay = 5000)
    public  void autoSendEmailLiableLongTime() throws Exception{
        this.logger.info("autoSendEmailLiableLongTime executed");
        List<Map<String,Object>> emails=meetingDao.selectLiableEmailForNotice();
        for( Map<String,Object> e:emails) {
            List<Map<String,Object>> tasks=this.tpaDao.selectTaskAByUserId((String)e.get("userId"));
            if(CommonUtils.isNotEmpty(tasks)&&tasks.size()>0){
                Map<String,Object> model=new HashMap<String,Object>();
                model.put("tasklist", this.getTableContent(tasks));
                model.put("sysDate", CommonUtils.formatDate(new Date()));
                model.put("to", e.get("email"));
                this.sendEmail(model);
            }
        }

    }

    @Scheduled(cron="${jobs.autoSendEmailLiableShotTime}")
    public  void autoSendEmailLiableShotTime() throws Exception{
        this.logger.info("autoSendEmailLiableShotTime executed");
        List<Map<String,Object>> emails=meetingDao.selectLiableEmailForNotice();
        for( Map<String,Object> e:emails) {
            List<Map<String,Object>> tasks=this.tpaDao.selectTaskBByUserId((String)e.get("userId"),this.tasktime);
            if(CommonUtils.isNotEmpty(tasks)&&tasks.size()>0){
                Map<String,Object> model=new HashMap<String,Object>();
                model.put("tasklist", this.getTableContent(tasks));
                model.put("sysDate", CommonUtils.formatDate(new Date()));
                model.put("to", e.get("email"));
                this.sendEmail(model);
            }
        }
    }

    private void sendEmail( Map<String,Object> model) throws Exception{
        this.logger.info("{}",model);
        String subject =CommonUtils.getStringByTemplate("email.subject.liabletask.vm", model);
        String content=CommonUtils.getStringByTemplate("email.liabletask.vm", model);
        Map<String, String> data = new HashMap<String, String>();
        data.put("subject", subject);
        data.put("content", content);
        data.put("to", (String) model.get("to"));
        this.logger.info("{}",data);
        this.kafkaProducerService.sendMsg("GESP_SYS_INFO", data);
    }

    private String getTableContent(List<Map<String,Object>> tasks ){
        StringBuffer html=new StringBuffer("<table class='hovertable'>");

        html.append("<tr>");
        html.append("<td colspan='9' style='text-align:center'>");
        html.append("电装会议纪要进度跟踪报告");
        html.append("</td>");
        html.append("</tr>");

        html.append("<tr style='font-weight:800'>");
        html.append("<th>");
        html.append("序号");
        html.append("</th>");
        html.append("<th>");
        html.append("会议名称");
        html.append("</th>");

        html.append("<th>");
        html.append("产品编码");
        html.append("</th>");

        html.append("<th>");
        html.append("问题描述");
        html.append("</th>");

        html.append("<th>");
        html.append("原因分析");
        html.append("</th>");

        html.append("<th>");
        html.append("改善措施");
        html.append("</th>");
        html.append("<th>");
        html.append("责任人");
        html.append("</th>");

        html.append("<th>");
        html.append("计划完成时间");
        html.append("</th>");

        html.append("<th>");
        html.append("状态");
        html.append("</th>");


        html.append("</tr>");


        int i=1;
        for(Map<String,Object> e:tasks){
            html.append("<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#fff';\">");
            html.append("<td>");
            html.append(i);
            html.append("</td>");

            html.append("<td>");
            html.append(e.get("meetingName"));
            html.append("</td>");

            html.append("<td>");
            html.append(e.get("productId"));
            html.append("</td>");

            html.append("<td>");
            html.append(e.get("probDiscri"));
            html.append("</td>");

            html.append("<td>");
            html.append(e.get("probAnsys"));
            html.append("</td>");

            html.append("<td>");
            html.append(e.get("actions"));
            html.append("</td>");

            html.append("<td>");
            html.append(e.get("userName"));
            html.append("</td>");

            html.append("<td>");
            html.append(e.get("DateEnd"));
            html.append("</td>");


            html.append("<td>");
            html.append(e.get("statusName"));
            html.append("</td>");

            html.append("</tr>");
            i++;
        }
        html.append("</table>");
        return  html.toString();
    }


}
