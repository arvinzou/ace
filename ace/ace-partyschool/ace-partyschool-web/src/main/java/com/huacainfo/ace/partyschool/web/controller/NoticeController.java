package com.huacainfo.ace.partyschool.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.partyschool.model.Notice;
import com.huacainfo.ace.partyschool.service.SclNoticeService;
import com.huacainfo.ace.partyschool.vo.NoticeVo;
import com.huacainfo.ace.partyschool.vo.NoticeQVo;

import java.util.Map;

@Controller
@RequestMapping("/notice")
/**
 * @author: Arvin
 * @version: 2019-01-06
 * @Description: TODO(通知公告)
 */
public class NoticeController extends BisBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SclNoticeService sclNoticeService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(通知公告分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <NoticeVo>
     * @author: Arvin
     * @version: 2019-01-06
     */
    @RequestMapping(value = "/findNoticeList")
    @ResponseBody
    public PageResult<NoticeVo> findNoticeList(NoticeQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<NoticeVo> rst = this.sclNoticeService.findNoticeList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertNotice
     * @Description: TODO(添加通知公告)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-06
     */
    @RequestMapping(value = "/insertNotice")
    @ResponseBody
    public MessageResponse insertNotice(String jsons) throws Exception {
        Notice obj = JSON.parseObject(jsons, Notice.class);
        return this.sclNoticeService.insertNotice(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateNotice
     * @Description: TODO(更新通知公告)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-06
     */
    @RequestMapping(value = "/updateNotice")
    @ResponseBody
    public MessageResponse updateNotice(String jsons) throws Exception {
        Notice obj = JSON.parseObject(jsons, Notice.class);
        return this.sclNoticeService.updateNotice(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectNoticeByPrimaryKey
     * @Description: TODO(获取通知公告)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Notice>
     * @author: Arvin
     * @version: 2019-01-06
     */
    @RequestMapping(value = "/selectNoticeByPrimaryKey")
    @ResponseBody
    public SingleResult<NoticeVo> selectNoticeByPrimaryKey(String id) throws Exception {
        return this.sclNoticeService.updateAndSelectNoticeVoById(id,getServerHttp(),this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:deleteNoticeByNoticeId
     * @Description: TODO(删除通知公告)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-06
     */
    @RequestMapping(value = "/deleteNoticeByNoticeId")
    @ResponseBody
    public MessageResponse deleteNoticeByNoticeId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.sclNoticeService.deleteNoticeByNoticeId(id, this.getCurUserProp());
    }

    private String getServerHttp() {
        return ((Map) this.getRequest().getSession().getServletContext().getAttribute("cfg")).get("fastdfs_server").toString();
    }
}
