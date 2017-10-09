package com.huacainfo.ace.operana.service.impl;
import com.huacainfo.ace.operana.service.TaskOperanaService;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Created by chenxiaoke on 2017/10/9.
 */
@Service("taskOperanaService")
public class TaskOperanaServiceImpl implements TaskOperanaService{

    private Logger logger = Logger.getLogger(this.getClass());
    @Scheduled(fixedDelay = 5000)
    public  void autoSendEmail() throws Exception{
        this.logger.info("autoSendEmail executed");

    }
}
