package com.huacainfo.ace.woc.common.utils;

import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Arvin on 2017/12/25.
 */
public class SessionUtils {
    public static Map<String, Session> clients = new ConcurrentHashMap<>();


    public static void put(String rid, String uid, Session session) {

        clients.put(getKey(rid, uid), session);

    }


    public static Session get(String rid, String uid) {

        return clients.get(getKey(rid, uid));

    }


    public static void remove(String rid, String uid) {

        clients.remove(getKey(rid, uid));

    }


    /**
     * 判断是否有连接
     *
     * @param rid
     * @param uid
     * @return
     */
    public static boolean hasConnection(String rid, String uid) {

        return clients.containsKey(getKey(rid, uid));

    }


    /**
     * 组装唯一识别的key
     *
     * @param rid
     * @param uid
     * @return
     */
    public static String getKey(String rid, String uid) {

        return rid + "_" + uid;

    }
}
