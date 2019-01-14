package com.huacainfo.ace.partyschool.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.partyschool.service.SclNoticeService;
import com.huacainfo.ace.partyschool.vo.NoticeQVo;
import com.huacainfo.ace.partyschool.vo.NoticeStatusVo;
import com.huacainfo.ace.partyschool.vo.NoticeVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/www/notice")
/**
 * @author: Arvin
 * @version: 2019-01-06
 * @Description: TODO(通知公告)
 */
public class WNoticeController extends BisBaseController {


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

    @RequestMapping(value = "/findNoticeLists")
    @ResponseBody
    public ResultResponse findNoticeLists(NoticeQVo condition, PageParamNoChangeSord page) throws Exception {
        UserProp userProp = this.getCurUserProp();
        if (userProp == null) {
            return new ResultResponse(ResultCode.FAIL, "请先跳转登录");
        }
        return this.sclNoticeService.findNoticeLists(condition, page.getStart(), page.getLimit(), page.getOrderBy(),userProp);
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
    @RequestMapping(value = "/getDetails")
    @ResponseBody
    public ResultResponse selectNoticeByPrimaryKey(String id) throws Exception {
        UserProp userProp = this.getCurUserProp();
        if (userProp == null) {
            return new ResultResponse(ResultCode.FAIL, "请先跳转登录");
        }
        return this.sclNoticeService.selectNoticeById(id);
    }

}
