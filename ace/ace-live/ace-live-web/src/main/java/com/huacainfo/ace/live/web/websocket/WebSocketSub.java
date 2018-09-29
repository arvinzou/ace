
package com.huacainfo.ace.live.web.websocket;

import com.huacainfo.ace.common.kafka.KafkaProducerService;
import com.huacainfo.ace.common.tools.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisOperations;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by chenxiaoke on 2017/12/19.
 */
//该注解用来指定一个URI，客户端可以通过这个URI来连接到WebSocket。类似Servlet的注解mapping。无需在web.xml中配置。
@ServerEndpoint(value = "/www/websocketsub/{rid}/{uid}")
public class WebSocketSub {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    // private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();

    public static Map<String, CopyOnWriteArraySet<WebSocketSub>> rooms = new ConcurrentHashMap();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    private HttpSession httpSession;
    private String rid;
    private String uid;

    private RedisOperations<String, Object> redisTemplate;

    private KafkaProducerService kafkaProducerService;


    public WebSocketSub() {
        this.redisTemplate = (RedisOperations) SpringUtils.getBean("redisTemplate");
        this.kafkaProducerService = (KafkaProducerService) SpringUtils.getBean("kafkaProducerService");
    }

    public synchronized void addSession(WebSocketSub webSocket) {
        if (WebSocketSub.rooms.get(rid) == null) {
            CopyOnWriteArraySet<WebSocketSub> webSocketSet = new CopyOnWriteArraySet<WebSocketSub>();
            WebSocketSub.rooms.put(rid, webSocketSet);
            logger.debug("create new room rid:{} uid:{}", rid, uid);
        }
        WebSocketSub.rooms.get(rid).add(webSocket);


    }

    public synchronized void removeSession(WebSocketSub webSocket) {
        WebSocketSub.rooms.get(rid).remove(webSocket);
    }

    /**
     * 连接建立成功调用的方法
     *
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("rid") String rid, @PathParam("uid") String uid) {
        //this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        this.session = session;
        this.rid = rid;
        this.uid = uid;
        addSession(this);     //加入set中
        addOnlineCount();           //在线数加1
        logger.debug("有新连接加入！当前在线人数为{}", getOnlineCount());
        logger.debug("rid:{} uid:{}", rid, uid);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam("rid") String rid, @PathParam("uid") String uid) {
        removeSession(this);  //从set中删除
        subOnlineCount();           //在线数减1
        logger.debug("有一连接关闭！当前在线人数为{}", getOnlineCount());
        logger.debug("rid:{} uid:{}", rid, uid);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("rid") String rid, @PathParam("uid") String uid) {
        logger.debug("rid:{} uid:{} 来自客户端的消息:{}", rid, uid, message);

        //群发消息
        for (WebSocketSub item : WebSocketSub.rooms.get(rid)) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                logger.error(e.getMessage());
                continue;
            }
        }
    }

    /**
     * 发生错误时调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.debug("发生错误");
        logger.error(error.getMessage());
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     *
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public synchronized void addOnlineCount() {
        WebSocketSub.onlineCount++;
    }

    public static synchronized void subOnlineCount() {

    }

}
