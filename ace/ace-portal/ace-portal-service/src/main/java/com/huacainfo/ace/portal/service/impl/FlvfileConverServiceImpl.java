package com.huacainfo.ace.portal.service.impl;

import com.huacainfo.ace.common.fastdfs.IFile;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.web.tools.WebUtils;
import com.huacainfo.ace.portal.model.FileRecord;
import com.huacainfo.ace.portal.service.BackendService;
import com.huacainfo.ace.portal.dao.FileRecordDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by chenxiaoke on 2018/3/22.
 */
@Service("flvfileConverService")
public class FlvfileConverServiceImpl implements BackendService{
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private  FileRecordDao fileRecordDao;

    @Autowired
    private IFile fileSaver;
    @Override
    public MessageResponse service(Map<String, Object> data){
        logger.info("FlvfileConverServiceImpl ===============>{}",data);
        FileRecord o=new FileRecord();
        CommonBeanUtils.copyMap2Bean(o,data);
        o.setCreateTime(new java.util.Date());
        o.setStatus("1");
        String targetPath=o.getPath()+".mp4";
        WebUtils.changeToMp4(o.getPath(),targetPath);
        java.io.File file =new java.io.File(targetPath);
        if(file.exists()){
            o.setMp4(targetPath);
            try{
                String fileName=fileSaver.saveFile(file,file.getName());
                o.setUrl(fileName);
                file.deleteOnExit();
                java.io.File t =new java.io.File(o.getPath());
                t.deleteOnExit();
            }catch (Exception e){
                this.logger.error("{}",e);
            }
        }
        if(this.fileRecordDao.isExit(o)>0){
            o.setUpdateTime(new java.util.Date());
            this.fileRecordDao.updateByPrimaryKey(o);
        }else{
            this.fileRecordDao.insert(o);
        }

        return new MessageResponse(0,"OK");
    }
}
