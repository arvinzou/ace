package com.huacainfo.ace.rvc.service.impl;

import com.huacainfo.ace.common.fastdfs.IFile;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.FileUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.rvc.base.RvcBaseService;
import com.huacainfo.ace.rvc.dao.RvcConferenceResDao;
import com.huacainfo.ace.rvc.model.RvcConferenceRes;
import com.huacainfo.ace.rvc.service.FileService;
import com.huacainfo.ace.rvc.util.ResultUtil;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

/**
 * Created by Arvin on 2018/1/2.
 */
@Service("fileServiceImpl")
public class FileServiceImpl extends RvcBaseService implements FileService {

    private static final String tempDir = "\\upload\\temp\\rvc";

    public static final String http = "http://139.224.0.227/";

    @Autowired
    private IFile fileSaver;

    @Autowired
    private RvcConferenceResDao rvcConferenceResDao;

    /**
     * 初始化临时路径
     */
    private void init() {
        File tempFile = new File(tempDir);
        if (!tempFile.exists()) {
            tempFile.mkdirs();
        }
    }

    /**
     * 文件上传
     *
     * @param files        文件数组
     * @param conferenceId 会议ID
     * @param userId       用户ID
     * @return 上传结果
     */
    @Override
    public Map<String, Object> upload(MultipartFile[] files, String conferenceId, String userId) {
        // 初始化临时路径
        init();

        String fileUri;
        RvcConferenceRes res;
        for (MultipartFile o : files) {
            File dest = new File(tempDir + File.separator + o.getName());
            try {
                o.transferTo(dest);
                fileUri = fileSaver.saveFile(dest, o.getOriginalFilename());
                dest.delete();
                //入库
                res = new RvcConferenceRes();
                res.setId(GUIDUtil.getGUID());
                res.setConferenceId(conferenceId);
                res.setResName(o.getOriginalFilename());
                res.setResSize((int) o.getSize());
                res.setResType(3);//1-文本内容，2-图片，3-文件，4-视频，5-会议纪要
                res.setResURL(http + fileUri);
                res.setCreateUserId("system");
                res.setCreateUserName("system");
                res.setCreateDate(DateUtil.getNowDate());
                rvcConferenceResDao.insertSelective(res);
            } catch (Exception e) {
                logger.error("FileServiceImpl.upload.error:{}", e);
                continue;
            }
        }

        return ResultUtil.success("上传成功");
    }

    /***
     * 文件上传
     * @param fileName 文件名 --  不带后缀
     * @param suffix  文件后缀
     * @param base64Str base64字符串
     * @param conferenceId 会议ID -- rvc_conference.id
     * @param userId 用户ID -- 浪潮ID
     * @return 上传结果
     */
    @Override
    public String upload(String base64Str, String fileName, String suffix,
                         String conferenceId, String userId) {
        // 初始化临时路径
        init();
        // 转存本地
        byte[] bytes = Base64.decodeBase64(base64Str);
        String filePath = tempDir + File.separator + fileName + "." + suffix;
        File file = new File(filePath);
        FileUtil.byte2File(bytes, tempDir, fileName + "." + suffix);
        // 上传至服务器
        String fileUri;
        try {
            fileUri = fileSaver.saveFile(file, fileName);
            file.delete();

            return fileUri;
        } catch (Exception e) {
            logger.error("FileServiceImpl.upload.error:{}", e);
            return "";
        }
    }

}
