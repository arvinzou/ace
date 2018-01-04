package com.huacainfo.ace.live.service.impl;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

import com.huacainfo.ace.common.tools.CommonUtils;

import com.huacainfo.ace.live.dao.LiveDao;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.huacainfo.ace.common.kafka.KafkaProducerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import com.huacainfo.ace.live.service.TaskService;

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
        this.logger.info("jobs updateNopPop executed ");
        String[] roomkeys = (String[]) this.redisTemplate.opsForValue().get("live.rooms.keys");
        if (roomkeys == null) {
            logger.info("redisTemplate opsForValue roomkeys empty");
            return;
        }
        for (String rid : roomkeys) {
            logger.info("redisTemplate opsForValue rid -> {}", rid);
            Long nop = (Long) this.redisTemplate.opsForValue().get(rid + ".nop");
            Long pop = (Long) this.redisTemplate.opsForValue().get(rid + ".pop");
            logger.info("nop-> {}", nop);
            logger.info("pop-> {}", pop);
            if (nop != null && pop != null) {
                this.liveDao.updateNopPop(rid, nop, pop);
            }
        }
    }
}
