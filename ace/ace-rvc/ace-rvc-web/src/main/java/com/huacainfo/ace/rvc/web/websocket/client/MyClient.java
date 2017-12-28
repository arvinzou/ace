package com.huacainfo.ace.rvc.web.websocket.client;

import javax.websocket.*;
import java.io.IOException;


@ClientEndpoint

public class MyClient {

    @OnOpen
    public void onOpen(Session session) {

        System.out.println("Connected to endpoint:" + session.getBasicRemote());

        try {

            session.getBasicRemote().sendText("Hello");

        } catch (IOException ex) {

        }

    }

    @OnMessage
    public void onMessage(String message) {

        System.out.println("MyClient.onMessage: "+message);

    }

    @OnError
    public void onError(Throwable t) {

        System.out.println("MyClient.error: " + t.getMessage());
        t.printStackTrace();

    }
}