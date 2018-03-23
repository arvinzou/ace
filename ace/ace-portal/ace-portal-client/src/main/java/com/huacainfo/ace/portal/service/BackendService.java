package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;

import java.util.Map;

public interface BackendService {
    public abstract MessageResponse service(Map<String, Object> data) throws Exception;
}
