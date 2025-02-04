package com.huacainfo.ace.portal.web.controller;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.fastdfs.IFile;
import com.huacainfo.ace.common.kafka.KafkaProducerService;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.ImageCut;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.portal.model.Attach;
import com.huacainfo.ace.portal.model.TaskCmcc;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.model.WxFormid;
import com.huacainfo.ace.portal.service.*;
import com.huacainfo.ace.portal.vo.AttachVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@Controller
@RequestMapping("/www")
public class WWWController extends PortalBaseController {
    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IFile fileSaver;
    @Autowired
    private FilesService filesService;
    @Autowired
    private AttachService attachService;

    @Autowired
    private UsersService usersService;


    @Autowired
    private SystemService systemService;
    @Autowired
    private WxCfgService wxCfgService;


    @Autowired
    private WxUserService wxUserService;

    @Autowired
    private RedisOperations<String, Object> redisTemplate;

    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private MessageTemplateService messageTemplateService;

    @Autowired
    private TaskCmccService taskCmccService;
    @Autowired
    private AppealCaseService appealCaseService;

    /**
     * @throws @author: chenxiaoke
     * @Title:uploadFile
     * @Description: TODO(删除附件)
     * @param: @param
     * file
     * @param: @param
     * noticeId
     * @param: @param
     * collectionName
     * @param: @return
     * @param: @throws
     * Exception
     * @return: ListResult<Attach>
     * @version: 2016年11月17日 下午1:35:41
     */
    @RequestMapping(value = "/uploadFile.do")
    @ResponseBody
    public ListResult<AttachVo> uploadFile(@RequestParam MultipartFile[] file, String id, String collectionName)
            throws Exception {

        String[] fileNames = new String[file.length];
        Attach[] files = new Attach[file.length];
        int i = 0;
        String dir = this.getRequest().getSession().getServletContext().getRealPath(File.separator) + "tmp";
        File tmp = new File(dir);
        if (!tmp.exists()) {
            tmp.mkdirs();
        }
        for (MultipartFile o : file) {
            File dest = new File(dir + File.separator + o.getName());
            o.transferTo(dest);
            fileNames[i] = this.fileSaver.saveFile(dest, o.getOriginalFilename());
            dest.delete();
            filesService.insertFiles(fileNames[i], this.getCurUserProp());
            Attach obj = new Attach();
            obj.setNoticeId(id);
            obj.setCategory(collectionName);
            obj.setCreateTime(new Date());
            obj.setFileName(file[i].getOriginalFilename());
            obj.setFileUrl(fileNames[i]);
            obj.setFileSize(String.valueOf(file[i].getSize()));
            files[i] = obj;
            i++;

        }
        //return this.attachService.upload(files, id, this.getCurUserProp());
        return null;
    }

    @RequestMapping(value = "/regUser.do")
    @ResponseBody
    public MessageResponse insertUsers(Users o) throws Exception {


        return this.usersService.insertReg(o);
    }

    @RequestMapping(value = "/selectDepartmentTreeList.do")
    @ResponseBody
    public List<Tree> selectDepartmentTreeList(String deptId) throws Exception {
        List<Tree> list = this.systemService.selectDepartmentTreeList(deptId, "sys");
        return list;
    }

    @RequestMapping(value = "/saveFormId.do")
    @ResponseBody
    public MessageResponse saveFormId(String text) throws Exception {
        WxUser user = this.getCurWxUser();
        List<WxFormid> list = JSON.parseArray(text, WxFormid.class);
        for (WxFormid o : list) {
            if (CommonUtils.isNotEmpty(user)) {
                o.setOpenId(user.getOpenId());
            }
        }
        return this.wxCfgService.insertFormIds(list);
    }


    @RequestMapping(value = "/selectWxUser.do")
    @ResponseBody
    public List<Map<String, Object>> selectWxUser() throws Exception {
        return this.wxUserService.selectWxUser(this.getParams());
    }

    @RequestMapping(value = "/selectWxUserByPrimaryKey.do")
    @ResponseBody
    public SingleResult<WxUser> selectWxUserByPrimaryKey(String id) throws Exception {
        return this.wxUserService.selectWxUserByPrimaryKey(id);
    }


    private String getRandCode() {
        Random random = new Random();
        String sRand = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
        }
        // 保存进session
        this.getRequest().getSession().setAttribute("j_captcha_cmcc", sRand);
        return sRand;
    }

    @RequestMapping(value = "/on_publish.do")
    @ResponseBody
    public ResponseEntity on_publish() throws Exception {
        Map<String, Object> p = this.getParams();
        this.logger.info("on_publish->{}", p);
        String swfurl = (String) p.get("swfurl");
        Map<String, String> o = CommonUtils.urlSplit(swfurl);
        if (o.containsKey("appid")) {
            p.put("appid", o.get("appid"));
        }
        MessageResponse rst = authorityService.authority(p);
        if (!rst.getState()) {
            return new ResponseEntity<>(rst, HttpStatus.NOT_ACCEPTABLE);
        }
        this.logger.info("{}", rst);
        if (p.containsKey("id")) {
            String id = (String) p.get("id");
            if (CommonUtils.isNotEmpty(id)) {
                this.redisTemplate.opsForValue().set((String) p.get("name"), id);
            }
        }

        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @RequestMapping(value = "/record_done.do")
    @ResponseBody
    public ResponseEntity record_done() throws Exception {
        String hls_server = ((Map) this.getRequest().getSession().getServletContext().getAttribute("cfg")).get("hls_server").toString();
        Map<String, Object> p = this.getParams();
        this.logger.info("record_done->{}", p);
        p.put("service", "flvfileConverService");
        p.put("hls_server",hls_server);
        Object id = this.redisTemplate.opsForValue().get((String) p.get("name"));
        if (CommonUtils.isNotEmpty(id)) {
            p.put("id", id);
        }
        this.logger.info("record_done_aftersetparam->{}", p);
        /**为了防止磁盘空间不足停止系统进行录制文件的转换存储，需要时再打开*/
        this.kafkaProducerService.sendMsg("topic.sys.msg", p);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @RequestMapping(value = "/kafka.do")
    @ResponseBody
    public ResponseEntity kafkatest() throws Exception {
        Map<String, Object> p = this.getParams();
        p.put("service", "backendService");
        this.logger.info("backendService->{}", p);
        this.kafkaProducerService.sendMsg("topic.sys.msg", p);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @RequestMapping(value = "/msgTmplMq")
    @ResponseBody
    public String msgMqTest(String topicName, String jsonData) {
        Map<String, Object> params = JsonUtil.toMap(jsonData);

//        //todo 获取管理者openid
//        String openid = "oFvIjw7bU8IN-GYgxYCwwf_fOKZY";//武琼
//        String tmplCode = "APPEAL_RESULT_NOTICE";
//        Map<String, Object> params = new HashMap<>();
//        //kafka所需内容
//        params.put("service", "messageTemplateService");
//        params.put("sysId", "fop");
//        params.put("tmplCode", tmplCode);
//        //发送消息内容
//        params.put("openid", openid);
//        params.put("url", "www.baidu.com");
////        params.put("first", "哈哈哈哈哈");
//        //data
//        params.put("appealContent", "I'm a tester !~ 我是搞测试滴!~");
//        params.put("dealResult", "待处理");
//        params.put("dealDate", "待处理");
//        params.put("consultingTel", "400-12345678");

        kafkaProducerService.sendMsg(topicName, params);//"topic.sys.msg"

        return "success";
    }

    @RequestMapping(value = "/sendTmplMsg")
    @ResponseBody
    public ResultResponse msgTest(String sysId, String tmplCode, String jsonData) throws Exception {
        Map<String, Object> params = JsonUtil.toMap(jsonData);

//        String openid = "oFvIjw7bU8IN-GYgxYCwwf_fOKZY";//武琼
//        String tmplCode = "APPEAL_RESULT_NOTICE";
//        Map<String, Object> params = new HashMap<>();
//        //kafka所需内容
//        params.put("sysId", "fop");
//        params.put("tmplCode", tmplCode);
//        //发送消息内容
//        params.put("openid", openid);
//        params.put("url", "www.baidu.com");
////        params.put("first", "哈哈哈哈哈");
//        //data
//        params.put("appealContent", "I'm a tester !~ 我是搞测试滴!~");
//        params.put("dealResult", "待处理");
//        params.put("dealDate", "待处理");
//        params.put("consultingTel", "400-12345678");

        return messageTemplateService.send(sysId, tmplCode, params);
    }

    @RequestMapping(value = "/upload.do")
    @ResponseBody
    public Map<String, Object> upload(@RequestParam MultipartFile[] file, String collectionName,
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
            fileNames[i] = this.fileSaver.saveFile(dest,
                    o.getOriginalFilename());
            dest.delete();
            filesService.insertFiles(fileNames[i], this.getCurUserProp());
            rst.put("success", true);
            rst.put("msg", "成功");
            rst.put("file_path", fastdfs_server + fileNames[i]);
            i++;
        }
        return rst;
    }

    @RequestMapping(value = "/sendCmccByMobile.do")
    @ResponseBody
    public MessageResponse sendCmccByMobile(String mobile) throws Exception {
        String _3rd_session = this.getRequest().getHeader("WX-SESSION-ID");
        String j_captcha_cmcc = this.getRandCode();
        TaskCmcc o = new TaskCmcc();
        if (CommonUtils.isBlank(mobile)) {
            return new MessageResponse(1, "手机号不能为空");
        }
        if (!CommonUtils.isValidMobile(mobile)) {
            return new MessageResponse(1, "手机号格式错误");
        }
        Map<String, Object> msg = new HashMap<String, Object>();
        msg.put("taskName", "验证码" + mobile);
        msg.put("msg", "本次提交验证码为" + j_captcha_cmcc + "，请及时输入。【华彩鉴权平台】");
        msg.put("tel", mobile + "," + mobile);
        CommonBeanUtils.copyMap2Bean(o, msg);
        redisTemplate.opsForValue().set(_3rd_session + "j_captcha_weui", j_captcha_cmcc);
        return this.taskCmccService.insertTaskCmcc(o);
    }

    /**
     * @param appealCaseId
     * @param sendType     发送方式 0-kafka 1-直接发
     */
    @RequestMapping(value = "/acMsgTest.do")
    @ResponseBody
    public ResultResponse acMsgTest(String appealCaseId, String sendType) throws Exception {

        return appealCaseService.acMsgTest(appealCaseId, sendType);
    }

    @RequestMapping(value = "/testMsg.do")
    @ResponseBody
    public ResultResponse testMsg() throws Exception {
        String o=null;
        o.equals("1");
        return null;
    }

}
