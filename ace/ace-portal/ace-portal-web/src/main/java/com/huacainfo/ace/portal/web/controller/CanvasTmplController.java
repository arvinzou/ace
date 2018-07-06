package com.huacainfo.ace.portal.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.CanvasTmpl;
import com.huacainfo.ace.portal.service.CanvasTmplService;
import com.huacainfo.ace.portal.vo.CanvasTmplQVo;
import com.huacainfo.ace.portal.vo.CanvasTmplVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/canvasTmpl")
/**
 * @author: Arvin
 * @version: 2018-07-05
 * @Description: TODO(绘图模板)
 */
public class CanvasTmplController extends PortalBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CanvasTmplService canvasTmplService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(绘图模板分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<CanvasTmplVo>
     * @author: Arvin
     * @version: 2018-07-05
     */
    @RequestMapping(value = "/findCanvasTmplList.do")
    @ResponseBody
    public PageResult<CanvasTmplVo> findCanvasTmplList(CanvasTmplQVo condition,
                                                       PageParamNoChangeSord page) throws Exception {
        PageResult<CanvasTmplVo> rst = this.canvasTmplService
                .findCanvasTmplList(condition, page.getStart(), page.getLimit(),
                        page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertCanvasTmpl
     * @Description: TODO(添加绘图模板)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-05
     */
    @RequestMapping(value = "/insertCanvasTmpl.do")
    @ResponseBody
    public MessageResponse insertCanvasTmpl(String jsons) throws Exception {
        CanvasTmpl obj = JSON.parseObject(jsons, CanvasTmpl.class);
        return this.canvasTmplService
                .insertCanvasTmpl(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateCanvasTmpl
     * @Description: TODO(更新绘图模板)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-05
     */
    @RequestMapping(value = "/updateCanvasTmpl.do")
    @ResponseBody
    public MessageResponse updateCanvasTmpl(String jsons) throws Exception {
        CanvasTmpl obj = JSON.parseObject(jsons, CanvasTmpl.class);
        return this.canvasTmplService
                .updateCanvasTmpl(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectCanvasTmplByPrimaryKey
     * @Description: TODO(获取绘图模板)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CanvasTmpl>
     * @author: Arvin
     * @version: 2018-07-05
     */
    @RequestMapping(value = "/selectCanvasTmplByPrimaryKey.do")
    @ResponseBody
    public SingleResult<CanvasTmplVo> selectCanvasTmplByPrimaryKey(String id)
            throws Exception {
        return this.canvasTmplService.selectCanvasTmplByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteCanvasTmplByCanvasTmplId
     * @Description: TODO(删除绘图模板)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-05
     */
    @RequestMapping(value = "/deleteCanvasTmplByCanvasTmplId.do")
    @ResponseBody
    public MessageResponse deleteCanvasTmplByCanvasTmplId(String jsons)
            throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.canvasTmplService.deleteCanvasTmplByCanvasTmplId(id,
                this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getList
     * @Description: TODO(获取绘图模板列表)
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: Arvin
     * @version: 2018-07-05
     */
    @RequestMapping(value = "/getList.do")
    @ResponseBody
    public Map<String, Object> getList() throws Exception {
        Map<String, Object> params = this.getParams();
        params.put("userId", this.getCurUserProp().getUserId());
        return this.canvasTmplService.getList(params);
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取绘图模板列表)
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: Arvin
     * @version: 2018-07-05
     */
    @RequestMapping(value = "/getById.do")
    @ResponseBody
    public Map<String, Object> getById(String id) throws Exception {
        return this.canvasTmplService.getById(id);
    }
}
