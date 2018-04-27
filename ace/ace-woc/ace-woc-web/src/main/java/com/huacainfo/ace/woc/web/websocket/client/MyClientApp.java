package com.huacainfo.ace.woc.web.controller.websocket.client;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;


public class MyClientApp {

    public Session session;

    public static void main(String[] args) {
        MyClientApp client = new MyClientApp();
        client.start();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = "";

        try {

            do {
                input = br.readLine();

                if (!input.equals("exit"))

                    client.session.getBasicRemote().sendText(input);
            } while (!input.equals("exit"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void start() {

        WebSocketContainer container = ContainerProvider.getWebSocketContainer();

        String uri = "ws://192.168.2.253:6001/rvc/live/websocket/r001/u123456";

        System.out.println("Connecting to" + uri);

        try {

            session = container.connectToServer(MyClient.class, new URI(uri));

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}