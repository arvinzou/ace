package com.huacainfo.ace.fundtown.web.controller;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.pushmsg.CommonUtil;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.fundtown.service.ProcessNodeResService;
import com.huacainfo.ace.fundtown.service.ProcessNodeService;
import com.huacainfo.ace.fundtown.service.VipDepartmentService;
import com.huacainfo.ace.fundtown.vo.ProcessNodeResVo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

import org.slf4j.Logger;

@Controller
@RequestMapping("/www/download")
public class WWWDownloadController extends BisBaseController {

    @Autowired
    private VipDepartmentService vipDepartmentService;
    @Autowired
    private ProcessNodeResService processNodeResService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/file")
    @ResponseBody
    public String downloadAttachment(String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (CommonUtils.isBlank(id)) {
            return null;
        }
        SingleResult<ProcessNodeResVo> rs = processNodeResService.selectProcessNodeResByPrimaryKey(id);
        if (rs.getStatus() != ResultCode.SUCCESS) {
            return null;
        }

        //这里获取的下载链接 http://sk.sit.fosuntech.cn/group1/M00/00/72/CqYKHVn69wyAMl6YAAVf953sp4Y075.pdf
        ProcessNodeResVo processNodeResVo = rs.getValue();
        String downLoadPath = processNodeResVo.getResUrl();
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
