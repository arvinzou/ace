package com.huacainfo.ace.weuiluohua.service;

import java.util.Map;

import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.weuiluohua.model.Users;

/**
 * Created by chenxiaoke on 2017/5/16.
 */
public interface RegService {
    /**
     * 注册
     *
     * @param obj
     * @return MessageResponse
     * @throws Exception
     */
    public MessageResponse reg(Users obj) throws Exception;
    public SingleResult<Users> login(Map<String,Object> obj) throws Exception;
}
