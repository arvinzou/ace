package com.huacainfo.ace.live.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.fastdfs.IFile;
import com.huacainfo.ace.common.kafka.KafkaProducerService;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.common.tools.PropertyUtil;
import com.huacainfo.ace.common.tools.WaterMarkUtils;
import com.huacainfo.ace.live.model.Live;
import com.huacainfo.ace.live.model.LiveImg;
import com.huacainfo.ace.live.model.LiveMsg;
import com.huacainfo.ace.live.model.LiveRpt;
import com.huacainfo.ace.live.service.LiveMsgService;
import com.huacainfo.ace.live.service.LiveRptService;
import com.huacainfo.ace.live.service.LiveService;
import com.huacainfo.ace.live.service.WWWService;
import com.huacainfo.ace.live.web.websocket.WebSocketSub;
import com.huacainfo.ace.live.web.websocket.MyWebSocket;
import com.huacainfo.ace.portal.service.FilesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.huacainfo.ace.portal.service.WxCfgService;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/www/live/")
/**
 * @author: 陈晓克
 * @version: 2017-12-27
 * @Description: TODO(直播)
 */
public class WWWController extends LiveBaseController {
    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LiveService liveService;

    @Autowired
    private WWWService wwwService;

    @Autowired
    private LiveMsgService liveMsgService;

    @Autowired
    private LiveRptService liveRptService;

    @Autowired
    private RedisOperations<String, Object> redisTemplate;
    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private IFile fileSaver;
    @Autowired
    private WxCfgService WxCfgService;

    @Autowired
    private FilesService filesService;

    private WaterMarkUtils waterMarkUtils = new WaterMarkUtils();


    /**
     * @throws
     * @Title:getLiveList
     * @Description: TODO(微网页获取直播列表)
     * @param: @param p
     * @param: @throws Exception
     * @return: List<Map<String,Object>>
     * @author: 陈晓克
     * @version: 2018-01-01
     */
    @RequestMapping(value = "/getListByCompany.do")
    @ResponseBody
    public Map<String, Object> getListByCompany(int page, String companyId) throws Exception {
        Map<String, Object> p = this.getPageParam(page, this.getParams());
        return this.wwwService.getLiveList(companyId, page, p);
    }

    @RequestMapping(value = "/getTotalNumAndOrgInfo.do")
    @ResponseBody
    public Map<String, Object> getTotalNumAndOrgInfo(String companyId, String id) throws Exception {
        Map<String, Object> rst = new HashMap<>();
        rst.put("data", this.wwwService.getTotalNumAndOrgInfo(companyId, id));
        rst.put("errCode", "get_info_succeed");
        rst.put("errMsg", "获取现场总数和组织信息成功");
        rst.put("status", 0);
        return rst;
    }

    @RequestMapping(value = "/getShareContent.do")
    @ResponseBody
    public Map<String, Object> getShareContent(String companyId) throws Exception {
        Map<String, Object> rst = new HashMap<>();
        rst.put("data", this.wwwService.getShareContent(companyId,PropertyUtil.getProperty("fastdfs_server")));
        rst.put("status", 0);
        return rst;
    }

    @RequestMapping(value = "/getWxJsSign.do")
    @ResponseBody
    public Map<String, Object> getWxJsSign(String companyId) throws Exception {
        String url = this.getRequest().getHeader("referer");
        this.logger.info("url->{}", url);
        Map<String, Object> rst = new HashMap<>();
        Map<String, String> o = WxCfgService.selectAccessTokenAndTicketByDeptId(companyId).getValue();
        this.logger.info("accessToken->{}", o.get("accessToken"));
        this.logger.info("ticket->{}", o.get("ticket"));
        Map<String, Object> signature = WxCfgService.getSignature(url, o.get("appId"), o.get("accessToken"), o.get("ticket")).getValue();
        this.logger.info("signature->{}", signature);
        rst.put("data", signature);
        rst.put("status", 0);
        return rst;
    }

    /**
     * @throws
     * @Title:getLive
     * @Description: TODO(微网页根据直播获取ID，直播信息信息)
     * @param: @param id
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: 陈晓克
     * @version: 2018-01-01
     */
    @RequestMapping(value = "/getInfo.do")
    @ResponseBody
    public Map<String, Object> getInfo() throws Exception {
        Map<String, Object> p = this.getParams();
        p.put("fastdfs_server", PropertyUtil.getProperty("fastdfs_server"));
        Map<String, Object> rst = new HashMap<>();
        rst.put("data", this.wwwService.getLive(p));
        rst.put("status", "0");
        return rst;
    }

    /**
     * @throws
     * @Title:insertLiveMsg
     * @Description: TODO(添加图文直播)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    @RequestMapping(value = "/insertLiveMsg.do")
    @ResponseBody
    public MessageResponse insertLiveMsg(String jsons) throws Exception {
        LiveMsg obj = JSON.parseObject(jsons, LiveMsg.class);
        return this.liveMsgService
                .insertLiveMsg(obj);
    }

    @RequestMapping(value = "/sendMsg.do")
    @ResponseBody
    public MessageResponse sendMsg(String message, String rid, String uid) throws Exception {
        logger.debug("{} {}", rid, message);

        //群发消息
        for (WebSocketSub item : WebSocketSub.rooms.get(rid)) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                logger.error(e.getMessage());
                continue;
            }
        }
        return new MessageResponse(0, "OK");
    }


    /**
     * @throws
     * @Title:getLiveSubList
     * @Description: TODO(微网页根据直播间RID获取图文直播内容)
     * @param:
     * @param: @throws Exception
     * @return: List<Map<String,Object>>
     * @author: 陈晓克
     * @version: 2018-01-07
     */
    @RequestMapping(value = "/getLiveRptList.do")
    @ResponseBody
    public Map<String, Object> getLiveRptList(int page, String rid) {
        Map<String, Object> p = this.getPageParam(page, this.getParams());
        p.put("fastdfs_server", PropertyUtil.getProperty("fastdfs_server"));
        return this.wwwService.getLiveRptList(rid, page, p);
    }

    /**
     * @throws
     * @Title:getLiveMsgList
     * @Description: TODO(微网页根据直播间RID获取互动内容)
     * @param:
     * @param: @throws Exception
     * @return: List<Map<String,Object>>
     * @author: 陈晓克
     * @version: 2018-01-07
     */
    @RequestMapping(value = "/getLiveMsgList.do")
    @ResponseBody
    public List<Map<String, Object>> getLiveMsgList() {
        return this.wwwService.getLiveMsgList(this.getParams());
    }


    @RequestMapping(value = "/cmt.do")
    @ResponseBody
    public MessageResponse cmt(String message, String rid, String uid, String rptId, String topic) throws Exception {
        logger.debug("{} {}", rid, message);
        Map<String, String> data = new HashMap<String, String>();
        data.put("rid", rid);
        data.put("rptId", rptId);
        data.put("uid", uid);
        data.put("message", message);
        this.logger.info("{}", data);

        this.kafkaProducerService.sendMsg(topic, data);
        //群发消息
        for (MyWebSocket item : MyWebSocket.rooms.get(rid)) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                logger.error(e.getMessage());
                continue;
            }
        }
        return new MessageResponse(0, "OK");
    }

    @RequestMapping(value = "/addLike.do")
    @ResponseBody
    public MessageResponse addLike(String id, String type) throws Exception {
        Map<String, String> data = new HashMap<String, String>();
        data.put("id", id);
        data.put("type", type);
        this.logger.info("{}", data);
        String topic = "liker";
        this.kafkaProducerService.sendMsg(topic, data);
        return new MessageResponse(0, "OK");
    }

    @RequestMapping(value = "/insertLiveRpt.do")
    @ResponseBody
    public MessageResponse insertLiveRpt(String jsons) throws Exception {

        Map<String, Object> params = JsonUtil.toMap(jsons);
        String openid = (String) params.get("openid");
        MessageResponse checked = liveService.checkIsBandUsers(openid);

        //验证通过
        if (0 == checked.getStatus()) {
            Map<String, String> data = new HashMap<String, String>();
            data.put("jsons", jsons);
            this.kafkaProducerService.sendMsg("rpt", data);
            return new MessageResponse(0, "OK");

        } else {
            return checked;
        }
    }

    @RequestMapping(value = "/upload.do")
    @ResponseBody
    public Map<String, Object> upload(@RequestParam MultipartFile[] file, String collectionName, String marktext, String companyId)
            throws Exception {
        logger.info("=========================");
        logger.info(marktext);
        logger.info(companyId);
        if (CommonUtils.isBlank(companyId)) {
            companyId = "00010001";
        }
        Map<String, Object> rst = new HashMap<String, Object>();
        String[] fileNames = new String[file.length];
        String dir = this.getRequest().getSession().getServletContext().getRealPath(File.separator) + "tmp";
        File tmp = new File(dir);
        if (!tmp.exists()) {
            tmp.mkdirs();
        }
        int i = 0;
        waterMarkUtils.BASE_PATH = dir + File.separator;
        //String logoDir = this.getRequest().getSession().getServletContext().getRealPath(File.separator) + "content" + File.separator + "www" + File.separator + "img" + File.separator;
        String logoDir = System.getProperty("user.home")+File.separator+"files"+File.separator;
        waterMarkUtils.MARK_LOGO_IMAGE_01 = logoDir + companyId + "-watermark1.png";
        waterMarkUtils.MARK_LOGO_IMAGE_02 = logoDir + companyId + "-watermark2.png";
        if (marktext != null) {
            waterMarkUtils.MARK_LOGO_IMAGE_04 = logoDir + "watermarktitle.png";
            waterMarkUtils.MARK_TEXT = marktext;
        } else {
            waterMarkUtils.MARK_LOGO_IMAGE_04 = null;
            waterMarkUtils.MARK_TEXT = null;
        }
        for (MultipartFile o : file) {
            File dest = new File(dir + File.separator + o.getName());
            String fileName = o.getOriginalFilename().toLowerCase();

            o.transferTo(dest);
            this.logger.info("=================>{}", fileName);
            if (fileName.endsWith("jpg") || fileName.endsWith("jpeg") || fileName.endsWith("bmp") || fileName.endsWith("png")) {
                String f = waterMarkUtils.waterMarkBySingleImage(dest);
                this.logger.info("=================>{}", f);
                File mark = new File(waterMarkUtils.BASE_PATH + f);
                fileNames[i] = this.fileSaver.saveFile(mark,
                        o.getOriginalFilename());
                mark.delete();
            } else {
                fileNames[i] = this.fileSaver.saveFile(dest,
                        o.getOriginalFilename());
            }
            dest.delete();
            filesService.insertFiles(fileNames[i], this.getCurUserProp());
            rst.put("success", true);
            rst.put("msg", "成功");
            rst.put("file_path", fileNames[i]);
            i++;
        }
        return rst;
    }

    @RequestMapping(value = "/uploadAmr.do")
    @ResponseBody
    public SingleResult<Map<String, String>> uploadAmr(String deptId, String serverId) throws Exception {
        return this.WxCfgService.getRecordFile(deptId, serverId);
    }

    @RequestMapping(value = "/visit.do")
    @ResponseBody
    public MessageResponse visit(String id) throws Exception {
        Map<String, String> data = new HashMap<String, String>();
        data.put("id", id);
        this.logger.info("{}", data);
        String topic = "visit";
        this.kafkaProducerService.sendMsg(topic, data);
        return new MessageResponse(0, "OK");
    }


    @RequestMapping(value = "/insertLive.do")
    @ResponseBody
    public MessageResponse insertLive(String jsons) throws Exception {
        Map<String, Object> data = JsonUtil.toMap(jsons);
        String openid = (String) data.get("openid");
        MessageResponse checked = liveService.checkIsBandUsers(openid);
        //验证通过
        if (0 == checked.getStatus()) {
//            Map<String, String> params = new HashMap<>();
//            params.put("jsons", jsons);
//            this.kafkaProducerService.sendMsg("live", data);

            Live live = JsonUtil.toObject(jsons, Live.class);

            return liveService.insertLive(openid, live);
        } else {

            return checked;

        }
    }
}
