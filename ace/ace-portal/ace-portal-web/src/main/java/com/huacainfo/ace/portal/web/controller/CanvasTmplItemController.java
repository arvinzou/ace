package com.huacainfo.ace.portal.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.CanvasTmplItem;
import com.huacainfo.ace.portal.service.CanvasTmplItemService;
import com.huacainfo.ace.portal.vo.CanvasTmplItemQVo;
import com.huacainfo.ace.portal.vo.CanvasTmplItemVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/canvasTmplItem")
/**
 * @author: Arvin
 * @version: 2018-07-05
 * @Description: TODO(绘图模板-子项)
 */
public class CanvasTmplItemController extends PortalBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CanvasTmplItemService canvasTmplItemService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(绘图模板-子项分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<CanvasTmplItemVo>
     * @author: Arvin
     * @version: 2018-07-05
     */
    @RequestMapping(value = "/findCanvasTmplItemList.do")
    @ResponseBody
    public PageResult<CanvasTmplItemVo> findCanvasTmplItemList(CanvasTmplItemQVo condition,
                                                               PageParamNoChangeSord page) throws Exception {
        PageResult<CanvasTmplItemVo> rst = this.canvasTmplItemService
                .findCanvasTmplItemList(condition, page.getStart(), page.getLimit(),
                        page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertCanvasTmplItem
     * @Description: TODO(添加绘图模板-子项)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-05
     */
    @RequestMapping(value = "/insertCanvasTmplItem.do")
    @ResponseBody
    public MessageResponse insertCanvasTmplItem(String jsons) throws Exception {
        CanvasTmplItem obj = JSON.parseObject(jsons, CanvasTmplItem.class);
        return this.canvasTmplItemService
                .insertCanvasTmplItem(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateCanvasTmplItem
     * @Description: TODO(更新绘图模板-子项)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-05
     */
    @RequestMapping(value = "/updateCanvasTmplItem.do")
    @ResponseBody
    public MessageResponse updateCanvasTmplItem(String jsons) throws Exception {
        CanvasTmplItem obj = JSON.parseObject(jsons, CanvasTmplItem.class);
        return this.canvasTmplItemService
                .updateCanvasTmplItem(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectCanvasTmplItemByPrimaryKey
     * @Description: TODO(获取绘图模板-子项)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CanvasTmplItem>
     * @author: Arvin
     * @version: 2018-07-05
     */
    @RequestMapping(value = "/selectCanvasTmplItemByPrimaryKey.do")
    @ResponseBody
    public SingleResult<CanvasTmplItemVo> selectCanvasTmplItemByPrimaryKey(String id)
            throws Exception {
        return this.canvasTmplItemService.selectCanvasTmplItemByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteCanvasTmplItemByCanvasTmplItemId
     * @Description: TODO(删除绘图模板-子项)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-05
     */
    @RequestMapping(value = "/deleteCanvasTmplItemByCanvasTmplItemId.do")
    @ResponseBody
    public MessageResponse deleteCanvasTmplItemByCanvasTmplItemId(String jsons)
            throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.canvasTmplItemService.deleteCanvasTmplItemByCanvasTmplItemId(id,
                this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getList
     * @Description: TODO(获取绘图模板-子项列表)
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
        return this.canvasTmplItemService.getList(params);
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取绘图模板-子项列表)
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: Arvin
     * @version: 2018-07-05
     */
    @RequestMapping(value = "/getById.do")
    @ResponseBody
    public Map<String, Object> getById(String id) throws Exception {
        return this.canvasTmplItemService.getById(id);
    }
}
