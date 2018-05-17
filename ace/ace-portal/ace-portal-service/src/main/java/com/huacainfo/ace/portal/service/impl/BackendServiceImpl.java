package com.huacainfo.ace.portal.service.impl;

import com.huacainfo.ace.common.fastdfs.IFile;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.web.tools.WebUtils;
import com.huacainfo.ace.portal.dao.FileRecordDao;
import com.huacainfo.ace.portal.model.FileRecord;
import com.huacainfo.ace.portal.service.BackendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by chenxiaoke on 2018/3/22.
 */
@Service("backendService")
public class BackendServiceImpl implements BackendService{
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public MessageResponse service(Map<String, Object> data){
        logger.info("BackendServiceImpl ===============>{}",data);


        return new MessageResponse(0,"OK");
    }
}
