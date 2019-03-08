package com.huacainfo.ace.society.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.fastdfs.IFile;
import com.huacainfo.ace.common.kafka.KafkaProducerService;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.tools.HttpUtils;
import com.huacainfo.ace.portal.model.WxCfg;
import com.huacainfo.ace.portal.service.WxCfgService;
import com.huacainfo.ace.society.service.AuditNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2018/11/22 17:07
 * @Description:
 */
@RestController
@RequestMapping("/www/util")
public class WUtilController extends SocietyBaseController {
    @Autowired
    private WxCfgService wxCfgService;
    @Autowired
    private IFile fileSaver;
    @Autowired
    private AuditNoticeService auditNoticeService;
    @Autowired
    private KafkaProducerService kafkaProducerService;

    @RequestMapping("/synUserList")
    public ResultResponse synUserList() throws Exception {
        Map<String, Object> data = new HashMap<>();
        data.put("sysId", "society");
        data.put("service", "weChatApiService");
        kafkaProducerService.sendMsg("topic.sys.msg", data);

        return new ResultResponse(ResultCode.SUCCESS, "SUCCESS");
    }

    @RequestMapping("/sendToAdmin")
    public ResultResponse sendToAdmin(String content) throws Exception {
        if (StringUtil.isEmpty(content)) {
            return new ResultResponse(ResultCode.FAIL, "输入参数有误");
        }

        return auditNoticeService.sendToAdmin(content);
    }

    @RequestMapping("/sendToCustomer")
    public ResultResponse sendToCustomer(String userId, String content) throws Exception {
        if (!StringUtil.areNotEmpty(userId, content)) {
            return new ResultResponse(ResultCode.FAIL, "输入参数有误");
        }

        return auditNoticeService.sendToCustomer(userId, content);
    }


    @RequestMapping(value = "/getwxacodeunlimit")
    public Map<String, Object> getwxacodeunlimit(String sysId, String page, String scene) throws Exception {

//        Map<String, Object> rst = new HashMap<>();
//        WxCfg wxCfg = wxCfgService.findBySysId(sysId);
//        //失败可能原因：1、授权地址配置失败；2、IP白名单未配置
//        if (null == wxCfg || StringUtil.isEmpty(wxCfg.getAccessToken())) {
//            rst.put("success", false);
//            rst.put("msg", "微信配置获取异常");
//        }
//        String access_token =wxCfg.getAccessToken();

        String access_token = "19_xUEemA2Z03XrhF42ak291Yv2kbTBuFmwgvsb1UZxki8sR6USFoY0Fhe9Uchy1zV1Bfn7mmz9GFs1IM_xDcEaRCPAg8m_cN4wYe1NXVel_jvfxFj0DFk1Tyax3-eQlVf9RwhI7tdLplAcrs3wYQKgAEAFXR";


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
//            filesService.insertFiles(path, this.getCurUserProp());
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
