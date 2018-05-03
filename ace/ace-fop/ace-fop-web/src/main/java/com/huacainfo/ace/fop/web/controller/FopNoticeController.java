package com.huacainfo.ace.fop.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopNotice;
import com.huacainfo.ace.fop.service.FopNoticeService;
import com.huacainfo.ace.fop.vo.FopNoticeQVo;
import com.huacainfo.ace.fop.vo.FopNoticeVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/fopNotice")
/**
 * @author: Arvin
 * @version: 2018-05-03
 * @Description: 通知公告
 */
public class FopNoticeController extends FopBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopNoticeService fopNoticeService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(通知公告分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <FopNoticeVo>
     * @author: Arvin
     * @version: 2018-05-03
     */
    @RequestMapping(value = "/findFopNoticeList")
    @ResponseBody
    public PageResult
            <FopNoticeVo> findFopNoticeList(FopNoticeQVo condition,
                                            PageParamNoChangeSord page) throws Exception {
        PageResult
                <FopNoticeVo> rst = this.fopNoticeService
                .findFopNoticeList(condition, page.getStart(), page.getLimit(),
                        page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertFopNotice
     * @Description: TODO(添加通知公告)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-03
     */
    @RequestMapping(value = "/insertFopNotice")
    @ResponseBody
    public MessageResponse insertFopNotice(String jsons) throws Exception {
        FopNotice obj = JSON.parseObject(jsons, FopNotice.class);
        return this.fopNoticeService
                .insertFopNotice(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateFopNotice
     * @Description: TODO(更新通知公告)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-03
     */
    @RequestMapping(value = "/updateFopNotice")
    @ResponseBody
    public MessageResponse updateFopNotice(String jsons) throws Exception {
        FopNotice obj = JSON.parseObject(jsons, FopNotice.class);
        return this.fopNoticeService
                .updateFopNotice(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectFopNoticeByPrimaryKey
     * @Description: TODO(获取通知公告)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopNotice>
     * @author: Arvin
     * @version: 2018-05-03
     */
    @RequestMapping(value = "/selectFopNoticeByPrimaryKey")
    @ResponseBody
    public SingleResult
            <FopNoticeVo> selectFopNoticeByPrimaryKey(String id)
            throws Exception {
        return this.fopNoticeService.selectFopNoticeByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteFopNoticeByFopNoticeId
     * @Description: TODO(删除通知公告)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-03
     */
    @RequestMapping(value = "/deleteFopNoticeByFopNoticeId")
    @ResponseBody
    public MessageResponse deleteFopNoticeByFopNoticeId(String jsons)
            throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.fopNoticeService.deleteFopNoticeByFopNoticeId(id,
                this.getCurUserProp());
                }
                }
