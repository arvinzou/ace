package com.huacainfo.ace.live.web.websocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

/**
 * 从websocket中获取用户session
 *
 *
 */
public class HttpSessionConfigurator extends Configurator {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {

        HttpSession httpSession = (HttpSession) request.getHttpSession();

        sec.getUserProperties().put(HttpSession.class.getName(), httpSession);
    }
}