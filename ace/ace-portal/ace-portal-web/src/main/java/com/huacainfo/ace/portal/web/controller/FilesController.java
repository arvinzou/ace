package com.huacainfo.ace.portal.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.fastdfs.IFile;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.tools.HttpUtils;
import com.huacainfo.ace.common.tools.ImageCut;
import com.huacainfo.ace.portal.service.FilesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/files")
public class FilesController extends PortalBaseController {
    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IFile fileSaver;
    @Autowired
    private FilesService filesService;

    /**
     * @throws
     * @Title:uploadFile
     * @Description: TODO(上传文件)
     * @param: @param file
     * @param: @param collectionName
     * @param: @return
     * @param: @throws Exception
     * @return: SingleResult<String[]>
     * @author: chenxiaoke
     * @version: 2016年11月17日 下午1:54:38
     */
    @RequestMapping(value = "/uploadFile.do")
    @ResponseBody
    public SingleResult<String[]> uploadFile(@RequestParam MultipartFile[] file, String collectionName) throws Exception {
        SingleResult<String[]> rst = new SingleResult<String[]>(0, "上传成功！");
        String[] fileNames = new String[file.length];
        String dir = this.getRequest().getSession().getServletContext().getRealPath(File.separator) + "tmp";
        File tmp = new File(dir);
        if (!tmp.exists()) {
            tmp.mkdirs();
        }
        int i = 0;
        for (MultipartFile o : file) {
            File dest = new File(dir + File.separator + o.getName());
            o.transferTo(dest);
            fileNames[i] = this.fileSaver.saveFile(dest, o.getOriginalFilename());
            dest.delete();
            filesService.insertFiles(fileNames[i], this.getCurUserProp());
            i++;
        }
        rst.setValue(fileNames);
        return rst;
    }


    /**
     * @throws
     * @Title:uploadFile
     * @Description: TODO(上传文件)
     * @param: @param file
     * @param: @param collectionName
     * @param: @return
     * @param: @throws Exception
     * @return: SingleResult<String[]>
     * @author: chenxiaoke
     * @version: 2016年11月17日 下午1:54:38
     */
    @RequestMapping(value = "/uploadFilePlus.do")
    @ResponseBody
    public SingleResult<Map<String, Object>> uploadFilePlus(@RequestParam MultipartFile[] file, String collectionName) throws Exception {
        SingleResult<Map<String, Object>> rst = new SingleResult<Map<String, Object>>(0, "上传成功！");
        String[] fileNames = new String[file.length];
        String dir = this.getRequest().getSession().getServletContext().getRealPath(File.separator) + "tmp";
        File tmp = new File(dir);
        if (!tmp.exists()) {
            tmp.mkdirs();
        }
        File dest = new File(dir + File.separator + file[0].getName());
        file[0].transferTo(dest);
        fileNames[0] = this.fileSaver.saveFile(dest, file[0].getOriginalFilename());
        dest.delete();
        filesService.insertFiles(fileNames[0], this.getCurUserProp());
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("fileName", file[0].getOriginalFilename());
        values.put("fileSize", file[0].getSize());
        values.put("fileNames", fileNames[0]);
        rst.setValue(values);
        return rst;
    }


    @RequestMapping(value = "/uploadImage.do")
    @ResponseBody
    public Map<String, Object> uploadImage(@RequestParam MultipartFile[] file, String collectionName,
                                           String x, String y, String desWidth, String desHeight, String srcWidth,
                                           String srcHeight) throws Exception {

        Map<String, Object> rst = new HashMap<String, Object>();
        String fastdfs_server = ((Map) this.getRequest().getSession().getServletContext().getAttribute("cfg")).get("fastdfs_server").toString();
        String[] fileNames = new String[file.length];
        String dir = this.getRequest().getSession().getServletContext().getRealPath(File.separator) + "tmp";
        File tmp = new File(dir);
        if (!tmp.exists()) {
            tmp.mkdirs();
        }
        int i = 0;
        for (MultipartFile o : file) {
            File dest = new File(dir + File.separator + o.getName());
            if (CommonUtils.isNotEmpty(x)) {
                ImageCut.imgCut(o.getInputStream(), Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(desWidth), Integer.valueOf(desHeight), Integer.valueOf(srcWidth), Integer.valueOf(srcHeight), dest);
            } else {
                o.transferTo(dest);
            }
            fileNames[i] = this.fileSaver.saveFile(dest, o.getOriginalFilename());
            dest.delete();
            filesService.insertFiles(fileNames[i], this.getCurUserProp());
            rst.put("success", true);
            rst.put("msg", "成功");
            rst.put("file_path", fastdfs_server + fileNames[i]);
            i++;
        }
        return rst;
    }

    @RequestMapping(value = "/getwxacodeunlimit.do")
    @ResponseBody
    public Map<String, Object> getwxacodeunlimit(String access_token, String page, String scene) throws Exception {
        Map<String, Object> rst = new HashMap<String, Object>();
        String fastdfs_server = ((Map) this.getRequest().getSession().getServletContext().getAttribute("cfg")).get("fastdfs_server").toString();
        String dir = this.getRequest().getSession().getServletContext().getRealPath(File.separator) + "tmp";
        File tmp = new File(dir);
        if (!tmp.exists()) {
            tmp.mkdirs();
        }
        String fileName = GUIDUtil.getGUID() + ".jpg";
        File dest = new File(dir + File.separator + fileName);
        this.logger.info("{}", dest.getAbsolutePath());
        OutputStream out = new FileOutputStream(dest);
        String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + access_token;
        JSONObject p = new JSONObject();
        p.put("scene", scene);
        p.put("page", page);
        int status = HttpUtils.getwxacodeunlimit(url, p, out);
        out.flush();
        out.close();
        if (status == 200) {
            String path = this.fileSaver.saveFile(dest, fileName);
            filesService.insertFiles(path, this.getCurUserProp());
            rst.put("success", true);
            rst.put("msg", "成功");
            rst.put("file_path", fastdfs_server + path);
        } else {
            rst.put("success", false);
            rst.put("msg", "失败");
        }
        dest.delete();
        return rst;
    }
}
