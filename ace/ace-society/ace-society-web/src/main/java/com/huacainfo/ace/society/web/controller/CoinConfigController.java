package com.huacainfo.ace.society.web.controller;

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
import com.huacainfo.ace.society.model.CoinConfig;
import com.huacainfo.ace.society.service.CoinConfigService;
import com.huacainfo.ace.society.vo.CoinConfigVo;
import com.huacainfo.ace.society.vo.CoinConfigQVo;

@Controller
@RequestMapping("/coinConfig")
/**
 * @author: huacai003
 * @version: 2018-09-17
 * @Description: TODO(爱心币配置)
 */
public class CoinConfigController extends SocietyBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CoinConfigService coinConfigService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(爱心币配置分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <CoinConfigVo>
     * @author: huacai003
     * @version: 2018-09-17
     */
    @RequestMapping(value = "/findCoinConfigList")
    @ResponseBody
    public PageResult<CoinConfigVo> findCoinConfigList(CoinConfigQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<CoinConfigVo> rst = this.coinConfigService.findCoinConfigList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertCoinConfig
     * @Description: TODO(添加爱心币配置)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-17
     */
    @RequestMapping(value = "/insertCoinConfig")
    @ResponseBody
    public MessageResponse insertCoinConfig(String jsons) throws Exception {
        CoinConfig obj = JSON.parseObject(jsons, CoinConfig.class);
        return this.coinConfigService.insertCoinConfig(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateCoinConfig
     * @Description: TODO(更新爱心币配置)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-17
     */
    @RequestMapping(value = "/updateCoinConfig")
    @ResponseBody
    public MessageResponse updateCoinConfig(String jsons) throws Exception {
        CoinConfig obj = JSON.parseObject(jsons, CoinConfig.class);
        return this.coinConfigService.updateCoinConfig(obj, this.getCurUserProp());
    }

    @RequestMapping(value = "/softDel")
    @ResponseBody
    public MessageResponse softDel(String id) throws Exception {
        return this.coinConfigService.softDel(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectCoinConfigByPrimaryKey
     * @Description: TODO(获取爱心币配置)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CoinConfig>
     * @author: huacai003
     * @version: 2018-09-17
     */
    @RequestMapping(value = "/selectCoinConfigByPrimaryKey")
    @ResponseBody
    public SingleResult
            <CoinConfigVo> selectCoinConfigByPrimaryKey(String id) throws Exception {
        return this.coinConfigService.selectCoinConfigByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteCoinConfigByCoinConfigId
     * @Description: TODO(删除爱心币配置)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-17
     */
    @RequestMapping(value = "/deleteCoinConfigByCoinConfigId")
    @ResponseBody
    public MessageResponse deleteCoinConfigByCoinConfigId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.coinConfigService.deleteCoinConfigByCoinConfigId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核爱心币配置)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-17
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.coinConfigService.audit(id, rst, message, this.getCurUserProp());
    }
}
