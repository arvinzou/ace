package com.huacainfo.ace.operana.service;

/**
 * Created by chenxiaoke on 2017/10/9.
 */
public interface TaskOperanaService {

    public abstract void autoSendEmailLeader() throws Exception;
    public abstract void autoSendEmailLiableLongTime() throws Exception;
    public abstract void autoSendEmailLiableShotTime() throws Exception;
}
