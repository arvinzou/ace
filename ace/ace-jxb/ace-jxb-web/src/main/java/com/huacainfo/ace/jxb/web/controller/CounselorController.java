package com.huacainfo.ace.jxb.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.jxb.model.Counselor;
import com.huacainfo.ace.jxb.model.TeacherAudit;
import com.huacainfo.ace.jxb.service.CounselorService;
import com.huacainfo.ace.jxb.service.TeacherAuditService;
import com.huacainfo.ace.jxb.vo.CounselorQVo;
import com.huacainfo.ace.jxb.vo.CounselorVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/counselor")
/**
 * @author: Arvin
 * @version: 2018-07-20
 * @Description: TODO(咨询师)
 */
public class CounselorController extends JxbBaseController {

    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CounselorService counselorService;
    @Autowired
    private TeacherAuditService teacherAuditService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(咨询师分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <CounselorVo>
     * @author: Arvin
     * @version: 2018-07-20
     */
    @RequestMapping(value = "/findCounselorList")
    @ResponseBody
    public PageResult<CounselorVo> findCounselorList(CounselorQVo condition,
                                                     PageParamNoChangeSord page) throws Exception {
        PageResult<CounselorVo> rst = counselorService
                .findCounselorList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertCounselor
     * @Description: TODO(添加咨询师)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-20
     */
    @RequestMapping(value = "/insertCounselor")
    @ResponseBody
    public MessageResponse insertCounselor(String jsons) throws Exception {
        Counselor obj = JSON.parseObject(jsons, Counselor.class);
        return counselorService.insertCounselor(obj, getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateCounselor
     * @Description: TODO(更新咨询师)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-20
     */
    @RequestMapping(value = "/updateCounselor")
    @ResponseBody
    public MessageResponse updateCounselor(String jsons) throws Exception {
        Counselor obj = JSON.parseObject(jsons, Counselor.class);
        return counselorService.updateCounselor(obj, getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectCounselorByPrimaryKey
     * @Description: TODO(获取咨询师)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Counselor>
     * @author: Arvin
     * @version: 2018-07-20
     */
    @RequestMapping(value = "/selectCounselorByPrimaryKey")
    @ResponseBody
    public SingleResult<CounselorVo> selectCounselorByPrimaryKey(String id) throws Exception {
        return counselorService.selectCounselorByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteCounselorByCounselorId
     * @Description: TODO(删除咨询师)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-20
     */
    @RequestMapping(value = "/deleteCounselorByCounselorId")
    @ResponseBody
    public MessageResponse deleteCounselorByCounselorId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return counselorService.deleteCounselorByCounselorId(id, getCurUserProp());
    }

    /**
     * 咨询师资格审核
     *
     * @param data
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String data) throws Exception {
        if (StringUtil.isEmpty(data)) {
            return new MessageResponse(ResultCode.FAIL, "缺少必要参数");
        }
        TeacherAudit record = JsonUtil.toObject(data, TeacherAudit.class);

        return teacherAuditService.insertTeacherAudit(record, getCurUserProp());
    }
}
