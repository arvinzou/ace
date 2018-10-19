package com.huacainfo.ace.live.web.controller;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.service.AuthorityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.live.model.Live;
import com.huacainfo.ace.live.service.LiveService;
import com.huacainfo.ace.live.vo.LiveVo;
import com.huacainfo.ace.live.vo.LiveQVo;

@Controller
@RequestMapping("/live")
/**
 * @author: 陈晓克
 * @version: 2017-12-27
 * @Description: TODO(直播)
 */
public class LiveController extends LiveBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LiveService liveService;
    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private RedisOperations<String, Object> redisTemplate;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(直播分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<LiveVo>
     * @author: 陈晓克
     * @version: 2017-12-27
     */
    @RequestMapping(value = "/findLiveLists")
    @ResponseBody
    public PageResult<LiveVo> findLiveList(LiveQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<LiveVo> rst = this.liveService.findLiveList(condition, page.getStart(), page.getLimit(),page.getOrderBy());
        condition.setDeptId(this.getCurUserProp().getCorpId());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertLive
     * @Description: TODO(添加直播)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2017-12-27
     */
    @RequestMapping(value = "/insertLive")
    @ResponseBody
    public MessageResponse insertLive(String jsons) throws Exception {
        Live obj = JSON.parseObject(jsons, Live.class);
        obj.setAuditStatus("1");
        return this.liveService
                .insertLive(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateLive
     * @Description: TODO(更新直播)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2017-12-27
     */
    @RequestMapping(value = "/updateLive")
    @ResponseBody
    public MessageResponse updateLive(String jsons) throws Exception {
        Live obj = JSON.parseObject(jsons, Live.class);
        return this.liveService.updateLive(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectLiveByPrimaryKey
     * @Description: TODO(获取直播)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Live>
     * @author: 陈晓克
     * @version: 2017-12-27
     */
    @RequestMapping(value = "/selectLiveByPrimaryKey")
    @ResponseBody
    public SingleResult<LiveVo> selectLiveByPrimaryKey(String id)
            throws Exception {
        return this.liveService.selectLiveByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteLiveByLiveId
     * @Description: TODO(删除直播)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2017-12-27
     */
    @RequestMapping(value = "/deleteLiveByLiveId")
    @ResponseBody
    public MessageResponse deleteLiveByLiveId(String jsons)
            throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.liveService.deleteLiveByLiveId(id,
                this.getCurUserProp());
    }



    /**
     * @throws
     * @Title:updateLiveAuditByLiveId
     * @Description: TODO(审核)
     * @param: @param id
     * @param rst
     * @param text
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-18
     */
    @RequestMapping(value = "/updateLiveAuditByLiveId")
    @ResponseBody
    public MessageResponse updateLiveAuditByLiveId(String id,String rst,String text) throws Exception {

        return this.liveService.updateLiveAuditByLiveId(id,rst,text,this.getCurUserProp());
    }
    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(设置直播状态1预告2直播中3结束)
     * @param: @param id
     * @param status
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-18
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public MessageResponse updateStatus(String id,String status) throws Exception {
        return this.liveService.updateStatus(id,status,this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:insertLive
     * @Description: TODO(添加直播)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2017-12-27
     */
    @RequestMapping(value = "/www/insertLive")
    @ResponseBody
    public MessageResponse insertLiveWww(String jsons) throws Exception {

        JSONObject o=JSON.parseObject(jsons);
        String captcha=o.getString("captcha");
        String _3rd_session=this.getRequest().getHeader("WX-SESSION-ID");
        String j_captcha_weui=(String) this.redisTemplate.opsForValue().get(_3rd_session + "j_captcha_weui");
        this.logger.info("captcha->{}",captcha);
        this.logger.info("j_captcha_weui->{}",j_captcha_weui);
        if(CommonUtils.isBlank(captcha)){
            return new MessageResponse(1,"验证码不能为空！");
        }
        if(!captcha.equals(j_captcha_weui)){
            return new MessageResponse(1,"验证码错误！");
        }
        SingleResult<UserProp> rst=authorityService.getCurUserPropByOpenId(this.getCurWxUser().getUnionId());
        if(rst.getStatus()==0){
            Live obj = JSON.parseObject(jsons, Live.class);
            obj.setAuditStatus("0");
            return this.liveService.insertLive(obj, rst.getValue());
        }
       return rst;
    }


    /**
     * @throws
     * @Title:updateLive
     * @Description: TODO(更新直播)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2017-12-27
     */
    @RequestMapping(value = "/www/updateLive")
    @ResponseBody
    public MessageResponse updateLiveWww(String jsons) throws Exception {
        JSONObject o=JSON.parseObject(jsons);
        String captcha=o.getString("captcha");
        String _3rd_session=this.getRequest().getHeader("WX-SESSION-ID");
        String j_captcha_weui=(String) this.redisTemplate.opsForValue().get(_3rd_session + "j_captcha_weui");
        this.logger.info("captcha->{}",captcha);
        this.logger.info("j_captcha_weui->{}",j_captcha_weui);
        if(CommonUtils.isBlank(captcha)){
            return new MessageResponse(1,"验证码不能为空！");
        }
        if(!captcha.equals(j_captcha_weui)){
            return new MessageResponse(1,"验证码错误！");
        }
        SingleResult<UserProp> rst=authorityService.getCurUserPropByOpenId(this.getCurWxUser().getUnionId());
        if(rst.getStatus()==0){
            Live obj = JSON.parseObject(jsons, Live.class);
            return this.liveService.updateLive(obj, rst.getValue());
        }
        return rst;
    }

    /**
     * @throws
     * @Title:deleteLiveByLiveId
     * @Description: TODO(删除直播)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2017-12-27
     */
    @RequestMapping(value = "/www/deleteLiveByLiveId")
    @ResponseBody
    public MessageResponse deleteLiveByLiveIdWww(String id) throws Exception {
        SingleResult<UserProp> rst=authorityService.getCurUserPropByOpenId(this.getCurWxUser().getUnionId());
        if(rst.getStatus()==0){
            return this.liveService.deleteLiveByLiveId(id,rst.getValue());
        }
        return rst;
    }

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(直播分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<LiveVo>
     * @author: 陈晓克
     * @version: 2017-12-27
     */
    @RequestMapping(value = "/www/findLiveList")
    @ResponseBody
    public PageResult<LiveVo> findLiveListWww(LiveQVo condition, PageParamNoChangeSord page) throws Exception {
        SingleResult<UserProp> user=authorityService.getCurUserPropByOpenId(this.getCurWxUser().getUnionId());
        if(user.getStatus()==0){
            condition.setDeptId(user.getValue().getCorpId());
            condition.setCreateUserId(user.getValue().getUserId());
            PageResult<LiveVo> rst = this.liveService.findLiveList(condition, page.getStart(), page.getLimit(),page.getOrderBy());
            if (rst.getTotal() == 0) {
                rst.setTotal(page.getTotalRecord());
            }
            return rst;
        }
        PageResult<LiveVo> prt=new PageResult();
        prt.setStatus(1);
        prt.setErrorMessage(user.getErrorMessage());
        return prt;
    }

    @RequestMapping(value = "/www/getliveListByCorpId")
    @ResponseBody
    public PageResult<LiveVo> getliveListByCorpId(LiveQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<LiveVo> rst = this.liveService.findLiveList(condition, page.getStart(), page.getLimit(),page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(设置直播状态1预告2直播中3结束)
     * @param: @param id
     * @param status
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-18
     */
    @RequestMapping(value = "/www/updateAuditStatus")
    @ResponseBody
    public MessageResponse updateAuditStatus(String id,String status) throws Exception {
        SingleResult<UserProp> rst=authorityService.getCurUserPropByOpenId(this.getCurWxUser().getUnionId());
        if(rst.getStatus()==0){
            return this.liveService.updateAuditStatus(id,status,rst.getValue());
        }
        return rst;
    }

    /**
     * @throws
     * @Title:selectLiveByPrimaryKey
     * @Description: TODO(获取直播)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Live>
     * @author: 陈晓克
     * @version: 2017-12-27
     */
    @RequestMapping(value = "/www/selectLiveByPrimaryKey")
    @ResponseBody
    public SingleResult<LiveVo> selectLiveByPrimaryKeyWww(String id)
            throws Exception {
        return this.liveService.selectLiveByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:selectLiveByPrimaryKey
     * @Description: TODO(获取直播)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Live>
     * @author: 陈晓克
     * @version: 2017-12-27
     */
    @RequestMapping(value = "/www/loadLive")
    @ResponseBody
    public LiveVo loadLive(String id)
            throws Exception {
        return this.liveService.selectLiveByPrimaryKey(id).getValue();
    }
}
