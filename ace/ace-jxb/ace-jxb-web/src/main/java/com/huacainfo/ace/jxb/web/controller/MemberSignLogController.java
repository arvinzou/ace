package com.huacainfo.ace.jxb.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.MemberSignLog;
import com.huacainfo.ace.jxb.service.MemberSignLogService;
import com.huacainfo.ace.jxb.vo.MemberSignLogQVo;
import com.huacainfo.ace.jxb.vo.MemberSignLogVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/memberSignLog")
/**
 * @author: Arvin
 * @version: 2018-08-02
 * @Description: TODO(会员签到日志)
 */
public class MemberSignLogController extends JxbBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MemberSignLogService memberSignLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(会员签到日志分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <MemberSignLogVo>
     * @author: Arvin
     * @version: 2018-08-02
     */
    @RequestMapping(value = "/findMemberSignLogList")
    @ResponseBody
    public PageResult<MemberSignLogVo> findMemberSignLogList(MemberSignLogQVo condition,
                                                             PageParamNoChangeSord page) throws Exception {
        PageResult<MemberSignLogVo> rst = this.memberSignLogService
                .findMemberSignLogList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertMemberSignLog
     * @Description: TODO(添加会员签到日志)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-02
     */
    @RequestMapping(value = "/insertMemberSignLog")
    @ResponseBody
    public MessageResponse insertMemberSignLog(String jsons) throws Exception {
        MemberSignLog obj = JSON.parseObject(jsons, MemberSignLog.class);
        return this.memberSignLogService.insertMemberSignLog(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateMemberSignLog
     * @Description: TODO(更新会员签到日志)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-02
     */
    @RequestMapping(value = "/updateMemberSignLog")
    @ResponseBody
    public MessageResponse updateMemberSignLog(String jsons) throws Exception {
        MemberSignLog obj = JSON.parseObject(jsons, MemberSignLog.class);
        return this.memberSignLogService.updateMemberSignLog(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectMemberSignLogByPrimaryKey
     * @Description: TODO(获取会员签到日志)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<MemberSignLog>
     * @author: Arvin
     * @version: 2018-08-02
     */
    @RequestMapping(value = "/selectMemberSignLogByPrimaryKey")
    @ResponseBody
    public SingleResult<MemberSignLogVo> selectMemberSignLogByPrimaryKey(String id) throws Exception {
        return this.memberSignLogService.selectMemberSignLogByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteMemberSignLogByMemberSignLogId
     * @Description: TODO(删除会员签到日志)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-02
     */
    @RequestMapping(value = "/deleteMemberSignLogByMemberSignLogId")
    @ResponseBody
    public MessageResponse deleteMemberSignLogByMemberSignLogId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.memberSignLogService.deleteMemberSignLogByMemberSignLogId(id, this.getCurUserProp());
    }
}
