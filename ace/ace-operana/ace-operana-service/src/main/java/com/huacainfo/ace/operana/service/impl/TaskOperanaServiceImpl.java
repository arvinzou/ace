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
    @Override
    public  void autoSendEmailLeader() throws Exception{
        this.logger.info("autoSendEmail executed");
        /**分会议发送邮件*/
        List<Map<String,Object>> list=meetingDao.selectMeetingListForNotice();
        for(Map<String,Object> o:list){
            this.sendLeaderByMeetingId((String) o.get("id"),(String) o.get("name"));
        }

    }

    private void sendLeaderByMeetingId(String meetingId,String meetingName) throws Exception{

        List<Map<String,Object>> emails=meetingDao.selectEmailForNotice();
        List<Map<String,Object>> tasks=meetingDao.selectTaskForEmail(this.tasktime,meetingId);
        if(tasks==null||tasks.size()==0){
            this.logger.info("{}","没有符合条件的任务检查");
            return;
        }
        StringBuffer html=new StringBuffer("<table  style='border:1px solid #000000;' border='1' cellspacing='0'>");

        html.append("<tr>");
        html.append("<td colspan='7' style='text-align:center;font-weight:800;border:1px solid #000000;'>");
        html.append("电装会议纪要进度跟踪报告("+meetingName+")");
        html.append("</td>");
        html.append("</tr>");

        html.append("<tr style='font-weight:800'>");
        html.append("<th style='border:1px solid #000000;'>");
        html.append("序号");
        html.append("</th>");

        html.append("<th style='border:1px solid #000000;'>");
        html.append("部门");
        html.append("</th>");

        html.append("<th style='border:1px solid #000000;'>");
        html.append("会议纪要总数");
        html.append("</th>");

        html.append("<th style='border:1px solid #000000;'>");
        html.append("已关闭数量");
        html.append("</th>");

        html.append("<th style='border:1px solid #000000;'>");
        html.append("未关闭数量");
        html.append("</th>");

        html.append("<th style='border:1px solid #000000;'>");
        html.append("KPI");
        html.append("</th>");

        html.append("<th style='border:1px solid #000000;'>");
        html.append("关闭率");
        html.append("</th>");

        html.append("</tr>");


        int i=1;
        for(Map<String,Object> e:tasks){
            html.append("<tr>");
            html.append("<td style='border:1px solid #000000;'>");
            html.append(i);
            html.append("</td>");

            html.append("<td style='border:1px solid #000000;'>");
            html.append(e.get("name"));
            html.append("</td>");

            html.append("<td style='border:1px solid #000000;'>");
            html.append(e.get("total"));
            html.append("</td>");

            html.append("<td style='border:1px solid #000000;'>");
            html.append(e.get("closed"));
            html.append("</td>");

            html.append("<td style='border:1px solid #000000;'> ");
            html.append(e.get("opened"));
            html.append("</td>");

            html.append("<td style='border:1px solid #000000;'>");
            html.append(e.get("normName"));
            html.append("</td>");

            html.append("<td style='border:1px solid #000000;'>");
            html.append(e.get("pt"));
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
        html.append("<td colspan='7' style='text-align:left;font-weight:800';border:1px solid #000000;>");
        html.append("抄送：");
        html.append(userList.toString());
        html.append("</td>");
        html.append("</tr>");
        html.append("</table>");




        Map<String,Object> model=new HashMap<String,Object>();
        model.put("tasklist", html.toString());
        model.put("userlist", userList.toString());
        model.put("sysDate", CommonUtils.formatDate(new Date()));
        model.put("meetingName", meetingName);

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
        /**分会议发送邮件*/
        List<Map<String,Object>> list=meetingDao.selectMeetingListForNotice();
        for(Map<String,Object> o:list){
            this.sendEmailLiableLongTime((String) o.get("id"),(String) o.get("name"));
        }

    }

    private void sendEmailLiableLongTime(String meetingId,String meetingName) throws Exception{
        List<Map<String,Object>> emails=meetingDao.selectLiableEmailForNotice();
        for( Map<String,Object> e:emails) {
            List<Map<String,Object>> tasks=this.tpaDao.selectTaskAByUserId((String)e.get("userId"),meetingId);
            if(CommonUtils.isNotEmpty(tasks)&&tasks.size()>0){
                Map<String,Object> model=new HashMap<String,Object>();
                model.put("tasklist", this.getTableContent(tasks,meetingName));
                model.put("sysDate", CommonUtils.formatDate(new Date()));
                model.put("to", e.get("email"));
                model.put("meetingName", meetingName);
                this.sendEmail(model);
            }
        }
    }

    @Scheduled(cron="${jobs.autoSendEmailLiableShotTime}")
    public  void autoSendEmailLiableShotTime() throws Exception{
        this.logger.info("autoSendEmailLiableShotTime executed");
        /**分会议发送邮件*/
        List<Map<String,Object>> list=meetingDao.selectMeetingListForNotice();
        for(Map<String,Object> o:list){
            this.sendEmailLiableShotTime((String) o.get("id"),(String) o.get("name"));
        }
    }

    public  void sendEmailLiableShotTime(String meetingId,String meetingName) throws Exception{

        List<Map<String,Object>> emails=meetingDao.selectLiableEmailForNotice();
        for( Map<String,Object> e:emails) {
            List<Map<String,Object>> tasks=this.tpaDao.selectTaskBByUserId((String)e.get("userId"),this.tasktime,meetingId);
            if(CommonUtils.isNotEmpty(tasks)&&tasks.size()>0){
                Map<String,Object> model=new HashMap<String,Object>();
                model.put("tasklist", this.getTableContent(tasks,meetingName));
                model.put("sysDate", CommonUtils.formatDate(new Date()));
                model.put("to", e.get("email"));
                model.put("meetingName", meetingName);
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

    private String getTableContent(List<Map<String,Object>> tasks ,String meetingName){
        StringBuffer html=new StringBuffer("<table  style='border:1px solid #000000;' border='1' cellspacing='0'>");

        html.append("<tr>");
        html.append("<td colspan='8' style='text-align:center;border:1px solid #000000;'>");
        html.append("电装会议纪要进度跟踪报告("+meetingName+")");
        html.append("</td>");
        html.append("</tr>");

        html.append("<tr style='font-weight:800'>");
        html.append("<th style='border:1px solid #000000;'>");
        html.append("序号");
        html.append("</th>");
       // html.append("<th>");
       // html.append("会议名称");
       // html.append("</th>");

        //html.append("<th>");
        //html.append("产品编码");
       // html.append("</th>");

        html.append("<th style='border:1px solid #000000;'>");
        html.append("问题描述");
        html.append("</th>");

        //html.append("<th>");
       // html.append("原因分析");
       // html.append("</th>");

        html.append("<th style='border:1px solid #000000;'>");
        html.append("改善措施");
        html.append("</th>");

        html.append("<th style='border:1px solid #000000;'>");
        html.append("责任人");
        html.append("</th>");


        html.append("<th style='border:1px solid #000000;'>");
        html.append("所属部门");
        html.append("</th>");

        html.append("<th style='border:1px solid #000000;'>");
        html.append("任务类型");
        html.append("</th>");

        html.append("<th style='border:1px solid #000000;'>");
        html.append("完成日期");
        html.append("</th>");

        html.append("<th style='border:1px solid #000000;'>");
        html.append("任务状态");
        html.append("</th>");


        html.append("</tr>");


        int i=1;


        for(Map<String,Object> e:tasks){
            html.append("<tr>");
            html.append("<td style='border:1px solid #000000;'>");
            html.append(i);
            html.append("</td>");

            //html.append("<td>");
           // html.append(e.get("meetingName"));
           // html.append("</td>");

           // html.append("<td>");
           // html.append(e.get("productId"));
           // html.append("</td>");

            html.append("<td style='border:1px solid #000000;'>");
            html.append(e.get("probDiscri"));
            html.append("</td>");

           // html.append("<td>");
           // html.append(e.get("probAnsys"));
           // html.append("</td>");

            html.append("<td style='border:1px solid #000000;'>");
            html.append(e.get("actions"));
            html.append("</td>");

            html.append("<td style='border:1px solid #000000;'>");
            html.append(e.get("userName"));
            html.append("</td>");

            html.append("<td style='border:1px solid #000000;'>");
            html.append(e.get("deptName"));
            html.append("</td>");


            html.append("<td style='border:1px solid #000000;'>");
            html.append(e.get("typeName"));
            html.append("</td>");

            html.append("<td style='border:1px solid #000000;'>");
            html.append(e.get("DateEnd"));
            html.append("</td>");


            html.append("<td style='border:1px solid #000000;'>");
            html.append(e.get("statusName"));
            html.append("</td>");

            html.append("</tr>");
            i++;
        }
        html.append("</table>");
        return  html.toString();
    }


}
