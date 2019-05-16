package com.huacainfo.ace.glink.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.glink.constant.CommConstant;
import com.huacainfo.ace.glink.model.LeBrokenLamp;
import com.huacainfo.ace.glink.service.LeBrokenLampService;
import com.huacainfo.ace.glink.vo.LeBrokenLampQVo;
import com.huacainfo.ace.glink.vo.LeBrokenLampVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/leBrokenLamp")
/**
 * @author: Arvin
 * @version: 2019-04-22
 * @Description: TODO(弱电 - 故障设备情况)
 */
public class LeBrokenLampController extends GLinkBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LeBrokenLampService leBrokenLampService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(弱电 - 故障设备情况分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<LeBrokenLampVo>
     * @author: Arvin
     * @version: 2019-04-22
     */
    @RequestMapping(value = "/findLeBrokenLampList")
    @ResponseBody
    public PageResult<LeBrokenLampVo> findLeBrokenLampList(LeBrokenLampQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult<LeBrokenLampVo> rst = this.leBrokenLampService.findLeBrokenLampList(condition, page.getStart(),
                page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertLeBrokenLamp
     * @Description: TODO(添加弱电 - 故障设备情况)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-22
     */
    @RequestMapping(value = "/insertLeBrokenLamp")
    @ResponseBody
    public MessageResponse insertLeBrokenLamp(String jsons) throws Exception {
        LeBrokenLamp obj = JSON.parseObject(jsons, LeBrokenLamp.class);
        return this.leBrokenLampService.insertLeBrokenLamp(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateLeBrokenLamp
     * @Description: TODO(更新弱电 - 故障设备情况)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-22
     */
    @RequestMapping(value = "/updateLeBrokenLamp")
    @ResponseBody
    public MessageResponse updateLeBrokenLamp(String jsons) throws Exception {
        LeBrokenLamp obj = JSON.parseObject(jsons, LeBrokenLamp.class);
        return this.leBrokenLampService.updateLeBrokenLamp(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectLeBrokenLampByPrimaryKey
     * @Description: TODO(获取弱电 - 故障设备情况)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<LeBrokenLamp>
     * @author: Arvin
     * @version: 2019-04-22
     */
    @RequestMapping(value = "/selectLeBrokenLampByPrimaryKey")
    @ResponseBody
    public SingleResult<LeBrokenLampVo> selectLeBrokenLampByPrimaryKey(String id) throws Exception {
        return this.leBrokenLampService.selectLeBrokenLampByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteLeBrokenLampByLeBrokenLampId
     * @Description: TODO(删除弱电 - 故障设备情况)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-22
     */
    @RequestMapping(value = "/deleteLeBrokenLampByLeBrokenLampId")
    @ResponseBody
    public MessageResponse deleteLeBrokenLampByLeBrokenLampId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.leBrokenLampService.deleteLeBrokenLampByLeBrokenLampId(id, this.getCurUserProp());
    }

    /**
     * 同步弱电故障数据
     *
     * @param date 2019-04-16
     * @return MessageResponse
     */
    @ResponseBody
    @RequestMapping(value = "/syncData")
    public MessageResponse syncData(String date) {
        if (StringUtil.isEmpty(date)) {
            date = DateUtil.toStr(DateUtil.getNowDate(), CommConstant.DATE_REGEX_LE);
        } else {
            date = DateUtil.toStr(DateUtil.toDate(date, DateUtil.DEFAULT_DATE_REGEX), CommConstant.DATE_REGEX_LE);
        }

        return leBrokenLampService.getBrokenLampDetail(date);
    }

}
