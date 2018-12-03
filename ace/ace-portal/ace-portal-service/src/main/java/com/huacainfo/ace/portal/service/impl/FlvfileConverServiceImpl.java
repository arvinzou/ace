package com.huacainfo.ace.portal.service.impl;

import com.huacainfo.ace.common.fastdfs.IFile;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.portal.dao.FileRecordDao;
import com.huacainfo.ace.portal.model.FileRecord;
import com.huacainfo.ace.portal.service.BackendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by chenxiaoke on 2018/3/22.
 */
@Service("flvfileConverService")
public class FlvfileConverServiceImpl implements BackendService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private  FileRecordDao fileRecordDao;

    @Autowired
    private IFile fileSaver;



    @Override
    public MessageResponse service(Map<String, Object> data){
        logger.info("FlvfileConverServiceImpl ===============>{}",data);
        String hls_server=(String) data.get("hls_server");
        FileRecord o=new FileRecord();
        CommonBeanUtils.copyMap2Bean(o,data);
        o.setCreateTime(new java.util.Date());
        o.setStatus("1");
        String path=(String) data.get("path");
        String name=path.substring(path.lastIndexOf("/")+1);
        o.setMp4(path+".mp4");
        o.setUrl(hls_server+name+".mp4");
        if(this.fileRecordDao.isExit(o)>0){
            o.setUpdateTime(new java.util.Date());
            this.fileRecordDao.updateByPrimaryKey(o);
        }else{
            this.fileRecordDao.insert(o);
        }

        return new MessageResponse(0,"OK");
    }



    public static void main(String args[]){
        String path="/usr/local/nginx-rtmp/html/hls/13922861673-2018-12-02.flv";
        String name=path.substring(path.lastIndexOf("/")+1);
        System.out.println(name);

    }
}
