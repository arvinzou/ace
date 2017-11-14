package com.huacainfo.ace.rvc.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.uf.service.FileService;
import com.huacainfo.ace.uf.vo.FileQVo;
import com.huacainfo.ace.uf.vo.FileVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/file")
public class FileController extends UfBaseController {
    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FileService fileService;


    /**
     * 查找全部file列表。
     * @param
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findFileList.do")
    @ResponseBody
    public PageResult<FileVo> findFileList(FileQVo condition,PageParamNoChangeSord page) throws Exception {
        this.logger.info(condition.toString());
        this.logger.info("===============================file=============================");
        PageResult<FileVo> rst = this.fileService.findFileList(condition,page.getStart(), page.getLimit(),page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    /**
     * 更新统战文件列表。
     * @param jsons
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateFile.do")
    @ResponseBody
    public MessageResponse updateFile(String jsons) throws Exception {
        this.logger.info(jsons.toString());
        this.logger.info("===============================file=============================");
        FileQVo obj = JSON.parseObject(jsons, FileQVo.class);
        return this.fileService.updateFileById(obj, this.getCurUserProp());
    }
    /**
     * 删除一条统战文件
     */
    @RequestMapping(value = "/deleteFileByFileId.do")
    @ResponseBody
    public MessageResponse deleteFileByFileId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.fileService.deleteFileByFileId(id, this.getCurUserProp());
    }

    /**
     * 添加统战文件
     */
    @RequestMapping(value = "/insertFile.do")
    @ResponseBody
    public MessageResponse insertFile(String jsons) throws Exception {
        this.logger.info(jsons.toString());
        this.logger.info("===============================insert file =============================");
        FileQVo obj = JSON.parseObject(jsons, FileQVo.class);
        return this.fileService.insertFile(obj, this.getCurUserProp());

    }
}
