package com.huacainfo.ace.jxb.web.websocket;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class RequestListener implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        //将所有request请求都携带上httpSession
        ((HttpServletRequest) sre.getServletRequest()).getSession();

    }

    public RequestListener() {
    }

    @Override
    public void requestDestroyed(ServletRequestEvent arg0) {
    }
}