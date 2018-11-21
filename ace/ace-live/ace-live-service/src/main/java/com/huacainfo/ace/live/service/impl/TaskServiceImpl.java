package com.huacainfo.ace.live.service.impl;

import com.huacainfo.ace.common.kafka.KafkaProducerService;
import com.huacainfo.ace.live.dao.LiveDao;
import com.huacainfo.ace.live.service.TaskService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Created by chenxiaoke on 2017/10/9.
 */

@PropertySource("classpath:config.properties")
@Service("taskService")
public class TaskServiceImpl implements TaskService {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private RedisOperations<String, Object> redisTemplate;

    @Autowired
    private LiveDao liveDao;

    @Scheduled(cron = "${jobs.updateNopPop}")
    @Override
    public void updateNopPop() throws Exception {

    }
}
