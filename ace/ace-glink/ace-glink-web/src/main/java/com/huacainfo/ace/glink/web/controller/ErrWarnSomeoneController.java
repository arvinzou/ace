package com.huacainfo.ace.glink.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.glink.model.ErrWarnSomeone;
import com.huacainfo.ace.glink.service.ErrWarnSomeoneService;
import com.huacainfo.ace.glink.vo.ErrWarnSomeoneQVo;
import com.huacainfo.ace.glink.vo.ErrWarnSomeoneVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/errWarnSomeone")
/**
 * @author: Arvin
 * @version: 2019-04-11
 * @Description: TODO(故障报警 - 送报人)
 */
public class ErrWarnSomeoneController extends GLinkBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ErrWarnSomeoneService errWarnSomeoneService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(故障报警 - 送报人分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<ErrWarnSomeoneVo>
     * @author: Arvin
     * @version: 2019-04-11
     */
    @RequestMapping(value = "/findErrWarnSomeoneList")
    @ResponseBody
    public PageResult<ErrWarnSomeoneVo> findErrWarnSomeoneList(ErrWarnSomeoneQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult<ErrWarnSomeoneVo> rst = this.errWarnSomeoneService.findErrWarnSomeoneList(condition, page.getStart(),
                page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertErrWarnSomeone
     * @Description: TODO(添加故障报警 - 送报人)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    @RequestMapping(value = "/insertErrWarnSomeone")
    @ResponseBody
    public MessageResponse insertErrWarnSomeone(String jsons) throws Exception {
        ErrWarnSomeone obj = JSON.parseObject(jsons, ErrWarnSomeone.class);
        return this.errWarnSomeoneService.insertErrWarnSomeone(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateErrWarnSomeone
     * @Description: TODO(更新故障报警 - 送报人)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    @RequestMapping(value = "/updateErrWarnSomeone")
    @ResponseBody
    public MessageResponse updateErrWarnSomeone(String jsons) throws Exception {
        ErrWarnSomeone obj = JSON.parseObject(jsons, ErrWarnSomeone.class);
        return this.errWarnSomeoneService.updateErrWarnSomeone(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectErrWarnSomeoneByPrimaryKey
     * @Description: TODO(获取故障报警 - 送报人)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<ErrWarnSomeone>
     * @author: Arvin
     * @version: 2019-04-11
     */
    @RequestMapping(value = "/selectErrWarnSomeoneByPrimaryKey")
    @ResponseBody
    public SingleResult<ErrWarnSomeoneVo> selectErrWarnSomeoneByPrimaryKey(String id) throws Exception {
        return this.errWarnSomeoneService.selectErrWarnSomeoneByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteErrWarnSomeoneByErrWarnSomeoneId
     * @Description: TODO(删除故障报警 - 送报人)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    @RequestMapping(value = "/deleteErrWarnSomeoneByErrWarnSomeoneId")
    @ResponseBody
    public MessageResponse deleteErrWarnSomeoneByErrWarnSomeoneId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.errWarnSomeoneService.deleteErrWarnSomeoneByErrWarnSomeoneId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param id
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version:2019-04-11
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public MessageResponse updateStatus(String id, String status) throws Exception {
        return this.errWarnSomeoneService.updateStatus(id, status, this.getCurUserProp());
    }
}
