package com.huacainfo.ace.partyschool.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.fastdfs.IFile;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.PropertyUtil;
import com.huacainfo.ace.partyschool.model.Files;
import com.huacainfo.ace.partyschool.service.ClsFilesService;
import com.huacainfo.ace.partyschool.vo.FilesQVo;
import com.huacainfo.ace.partyschool.vo.FilesVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

@Controller
@RequestMapping("/files")
/**
 * @author: Arvin
 * @version: 2019-01-04
 * @Description: TODO(班级文件)
 */
public class FilesController extends BisBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ClsFilesService clsFilesService;
    @Autowired
    private IFile fileSaver;

    private String getServerHttp() {
        return ((Map) this.getRequest().getSession().getServletContext().getAttribute("cfg")).get("fastdfs_server").toString();
    }


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(班级文件分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <FilesVo>
     * @author: Arvin
     * @version: 2019-01-04
     */
    @RequestMapping(value = "/findFilesList")
    @ResponseBody
    public PageResult<FilesVo> findFilesList(FilesQVo condition,
                                             PageParamNoChangeSord page) throws Exception {

        PageResult<FilesVo> rst = this.clsFilesService.findFilesList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertFiles
     * @Description: TODO(添加班级文件)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-04
     */
    //@RequestMapping(value = "/insertFiles")
    //@ResponseBody
    //public MessageResponse insertFiles(String jsons) throws Exception {
    //    Files obj = JSON.parseObject(jsons, Files.class);
    //    return this.clsFilesService.insertFiles(o, this.getCurUserProp());
    //}

    /**
     * 批量导入文件
     *
     * @param file  上载文件
     * @param classesId 班级ID
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/uploadFile")
    @ResponseBody
    public SingleResult<String[]> uploadFile(@RequestParam MultipartFile[] file, String classesId, String category) throws Exception {

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
            fileNames[i] = PropertyUtil.getProperty("fastdfs_server") + this.fileSaver.saveFile(dest, o.getOriginalFilename());
            dest.delete();
            clsFilesService.insertFiles(fileNames[i] ,classesId ,category,this.getCurUserProp());
            i++;
        }

        SingleResult<String[]> rst = new SingleResult<String[]>(0, "上传成功！");
        rst.setValue(fileNames);
        return rst;

    }

    /**
     * @throws
     * @Title:updateFiles
     * @Description: TODO(更新班级文件)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-04
     */
    @RequestMapping(value = "/updateFiles")
    @ResponseBody
    public MessageResponse updateFiles(String jsons) throws Exception {
        Files obj = JSON.parseObject(jsons, Files.class);
        return this.clsFilesService.updateFiles(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectFilesByPrimaryKey
     * @Description: TODO(获取班级文件)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Files>
     * @author: Arvin
     * @version: 2019-01-04
     */
    @RequestMapping(value = "/selectFilesByPrimaryKey")
    @ResponseBody
    public SingleResult
            <FilesVo> selectFilesByPrimaryKey(String id) throws Exception {
        return this.clsFilesService.selectFilesByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteFilesByFilesId
     * @Description: TODO(删除班级文件)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-04
     */
    @RequestMapping(value = "/deleteFilesByFilesId")
    @ResponseBody
    public MessageResponse deleteFilesByFilesId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.clsFilesService.deleteFilesByFilesId(id, this.getCurUserProp());
    }

}
