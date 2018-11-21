package com.huacainfo.ace.fop.web.controller;

import com.huacainfo.ace.common.fastdfs.IFile;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.PropertyUtil;
import com.huacainfo.ace.fop.service.FopCompanyService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Arvin
 * @Date: 2018/11/8 09:15
 * @Description:
 */
@RestController
@RequestMapping("/www/company")
public class WCompanyController extends FopBaseController {

    @Autowired
    private IFile fileSaver;
    @Autowired
    private FopCompanyService fopCompanyService;

    /**
     * 汉王文件上传
     */
    @RequestMapping("/upload")
    public ResultResponse uploadFile(String base64Str, String category, String companyId) throws Exception {

        byte[] bates = Base64.decodeBase64(base64Str);
        String tempFileName = DateUtil.getTimestamp(DateUtil.getNow()) + ".jpg";
        String url = PropertyUtil.getProperty("fastdfs_server") + fileSaver.saveFile(bates, tempFileName);

        return fopCompanyService.uploadAnnex(url, category, companyId);
    }


}
