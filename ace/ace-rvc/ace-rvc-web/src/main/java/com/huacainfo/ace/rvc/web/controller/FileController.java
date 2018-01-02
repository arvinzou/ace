package com.huacainfo.ace.rvc.web.controller;

import com.huacainfo.ace.common.tools.StringUtils;
import com.huacainfo.ace.rvc.service.FileService;
import com.huacainfo.ace.rvc.util.ResultUtil;
import com.huacainfo.ace.rvc.web.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by Arvin on 2018/1/2.
 */
@Controller("fileController")
@RequestMapping("/file")
public class FileController extends BaseController {

    @Autowired
    private FileService fileService;


    /**
     * 文件上传接口- MultipartFile 多文件上传
     *
     * @param userId 用户ID -- 浪潮ID
     * @return user
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Map<String, Object> upload(@RequestParam("files") MultipartFile[] files,
                                      String conferenceId, String userId) {
        if (StringUtils.isEmpty(userId) ||
                StringUtils.isEmpty(conferenceId) ||
                (null == files || files.length == 0)) {
            return ResultUtil.fail(-1, "缺少必备参数");
        }

        try {
            return fileService.upload(files, conferenceId, userId);
        } catch (Exception e) {
            logger.error("FileController.upload.error:{}", e);

            return ResultUtil.fail(-1, "系统错误");
        }
    }

    /***
     * 文件上传接口 -- base64字符串 ，单个上传
     * @param fileName 文件名 --  不带后缀
     * @param suffix  文件后缀
     * @param base64Str base64字符串
     * @return 上传结果
     */
    @ResponseBody
    @RequestMapping(value = "/uploadBase64Str", method = RequestMethod.POST)
    public Map<String, Object> upload(String base64Str, String fileName, String suffix) {
        if (StringUtils.isEmpty(base64Str) ||
                StringUtils.isEmpty(fileName) ||
                StringUtils.isEmpty(suffix)) {
            return ResultUtil.fail(-1, "缺少必备参数");
        }

        try {
            String uri = fileService.upload(base64Str, fileName, suffix, "", "");
            return ResultUtil.success(uri);

        } catch (Exception e) {
            logger.error("FileController.uploadBase64Str.error:{}", e);
            return ResultUtil.fail(-1, "系统错误");
        }
    }
}
