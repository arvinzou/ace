package com.huacainfo.ace.policeschool.web.controller;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.policeschool.model.Notice;
import com.huacainfo.ace.policeschool.service.ClassesService;
import com.huacainfo.ace.policeschool.service.ClsFilesService;
import com.huacainfo.ace.policeschool.service.SclNoticeService;
import com.huacainfo.ace.policeschool.vo.ClassesVo;
import com.huacainfo.ace.policeschool.vo.FilesVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

@Controller
@RequestMapping("/www/download")
public class WWWDownloadController extends BisBaseController {


    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SclNoticeService sclNoticeService;
    @Autowired
    private ClsFilesService clsFilesService;
    @Autowired
    private ClassesService classesService;

    @RequestMapping("/file")
    @ResponseBody
    public String downloadAttachment(String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SingleResult<FilesVo> rs = clsFilesService.selectFilesByPrimaryKey(id);
        if (rs.getStatus() == ResultCode.FAIL) {
            return null;
        }

        //这里获取的下载链接 http://sk.sit.fosuntech.cn/group1/M00/00/72/CqYKHVn69wyAMl6YAAVf953sp4Y075.pdf
        FilesVo filesVo = rs.getValue();
        String downLoadPath = filesVo.getUrl();
        return CommentResponse(downLoadPath, response);
    }

    @RequestMapping("/byUrl")
    @ResponseBody
    public String byUrl(String url, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (CommonUtils.isBlank(url)) {
            return null;
        }
        return CommentResponse(url, response);
    }

    @RequestMapping("/notice")
    @ResponseBody
    public String notice(String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResultResponse rs = sclNoticeService.findNoticeById(id);
        if (rs.getStatus() == ResultCode.FAIL) {
            return null;
        }

        //这里获取的下载链接 http://sk.sit.fosuntech.cn/group1/M00/00/72/CqYKHVn69wyAMl6YAAVf953sp4Y075.pdf
        Notice notice = (Notice) rs.getData();
        String downLoadPath = notice.getFileUrl();
        return CommentResponse(downLoadPath, response);
    }

    @RequestMapping("/classFile")
    @ResponseBody
    public String classFile(String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SingleResult<ClassesVo> rs = classesService.selectClassesByPrimaryKey(id);
        if (rs.getStatus() == ResultCode.FAIL) {
            return null;
        }
        //这里获取的下载链接 http://sk.sit.fosuntech.cn/group1/M00/00/72/CqYKHVn69wyAMl6YAAVf953sp4Y075.pdf
        ClassesVo classesVo = rs.getValue();
        String downLoadPath = classesVo.getFileUrl();
        return CommentResponse(downLoadPath, response);
    }


    private String CommentResponse(String downLoadPath, HttpServletResponse response) {
        downLoadPath=downLoadPath.replace("http://","https://");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {

            String fileName = downLoadPath.substring(downLoadPath.indexOf("filename=") + 9);
            logger.info("-------------------------fileName--------------------------------");
            logger.info(fileName);

            // fileName = new String(fileName.getBytes(), "ISO-8859-1");

            fileName = URLEncoder.encode(fileName, "UTF-8");
            //response.setHeader("Content-Disposition","attachment;filename="+fileName);
            //判断服务器操作系统，本地开发使用windows

            //响应二进制流
            response.setContentType("application/octet-stream");
            response.reset();//清除response中的缓存
            //根据网络文件地址创建URL
            URL url = new URL(downLoadPath);
            //获取此路径的连接
            URLConnection conn = url.openConnection();
            //获取文件大小
            Long fileLength = conn.getContentLengthLong();
            //设置reponse响应头，真实文件名重命名，就是在这里设置，设置编码
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + fileName);
            response.setHeader("Content-Length", String.valueOf(fileLength));
            //构造读取流
            bis = new BufferedInputStream(conn.getInputStream());
            //构造输出流
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[1024];
            int bytesRead;
            //每次读取缓存大小的流，写到输出流
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            response.flushBuffer();//将所有的读取的流返回给客户端
        } catch (IOException e) {

        } finally {
            try {
                if (null != bis) {
                    bis.close();
                }
                if (null != bos) {
                    bos.close();
                }
            } catch (IOException e) {

            }
        }
        return null;
    }
}
