
package com.huacainfo.ace.live.web.websocket;

import com.huacainfo.ace.common.kafka.KafkaProducerService;
import com.huacainfo.ace.common.tools.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisOperations;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/www/websocket/{rid}/{uid}/{service}")
public class MyWebSocket {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static int onlineCount = 0;

    public static ConcurrentHashMap<String, CopyOnWriteArraySet<MyWebSocket>> rooms = new ConcurrentHashMap();

    private Session session;


    private RedisOperations<String, Object> redisTemplate;

    private KafkaProducerService kafkaProducerService;

    private String roomId;
    private String uid;


    public MyWebSocket() {
        this.redisTemplate = (RedisOperations) SpringUtils.getBean("redisTemplate");
        this.kafkaProducerService = (KafkaProducerService) SpringUtils.getBean("kafkaProducerService");
    }

    public synchronized void addSession(MyWebSocket webSocket,String rid) {
        if (!rooms.containsKey(rid)) {
            CopyOnWriteArraySet<MyWebSocket> room = new CopyOnWriteArraySet();
            rooms.put(rid, room);
            synchroRoomKeys();
            logger.debug("=====================创建新的房间{} 用户 {}============", rid, uid);
        }else {

            logger.debug("=====================进入房间{} 用户 {}============", rid, uid);
        }
        rooms.get(rid).add(webSocket);
    }

    public synchronized void removeSession(MyWebSocket webSocket) {
        rooms.get(webSocket.getRoomId()).remove(webSocket);
    }


    @OnOpen
    public void onOpen(Session session,@PathParam("rid") String rid, @PathParam("uid") String uid, @PathParam("service") String service) {
        this.session = session;
        this.roomId=rid;
        this.uid=uid;
        addSession(this,rid);
        addOnlineCount();

    }

    @OnClose
    public void onClose(@PathParam("rid") String rid, @PathParam("uid") String uid, @PathParam("service") String service) {
        removeSession(this);
        subOnlineCount();
        logger.debug("========================有一连接关闭！rid:{} uid:{}" , rid, uid);
    }

    @OnMessage
    public void onMessage(String message, Session session, @PathParam("rid") String rid, @PathParam("uid") String uid, @PathParam("service") String service) {
        logger.debug("=====================rid:{} uid:{} 来自客户端的消息:{}", rid, uid, message);
        Map<String, String> data = new HashMap<String, String>();
        data.put("rid", rid);
        data.put("uid", uid);
        data.put("message", message);
        data.put("service",service);
        this.logger.info("{}", data);
        //群发消息
        for (MyWebSocket item : rooms.get(rid)) {
            try {
                item.sendMessage(message);
                this.logger.info("================发送消息RID {}=====================",item.getRoomId());
            } catch (IOException e) {
                logger.error(e.getMessage());
                continue;
            }
        }
        try {
            this.kafkaProducerService.sendMsg("topic.sys.msg.live", data);
        }catch (Exception e){
            this.logger.error("{}",e);
            this.logger.error("================kafka 服务连接错误=====================");
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        logger.debug("发生错误");
        logger.error(error.getMessage());
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public synchronized void addOnlineCount() {
        onlineCount++;
        String keynop = uid + ".nop";
        String keypop = this.roomId + ".pop";
        Long nop = (Long) this.redisTemplate.opsForValue().get(keynop);
        Long pop = (Long) this.redisTemplate.opsForValue().get(keypop);
        if (nop == null) {
            nop = new Long(0);
        }
        if (pop == null) {
            pop = new Long(0);
            this.redisTemplate.opsForValue().set(keypop, pop);
        }
        this.redisTemplate.opsForValue().set(keynop, new Long(nop + 1));

    }

    public static synchronized void subOnlineCount() {

    }

    private void synchroRoomKeys() {
        /*String[] keys = new String[rooms.keySet().size()];
        int i = 0;
        Iterator<String> it = null;
        while (it.hasNext()) {
            keys[i] = it.next();
            i++;
        }
        logger.debug("synchroRoomKeys ->{}", keys);
        redisTemplate.opsForValue().set("live.rooms.keys", keys);
        */

    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
