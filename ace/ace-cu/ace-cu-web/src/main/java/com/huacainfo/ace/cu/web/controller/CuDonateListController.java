package com.huacainfo.ace.cu.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.cu.model.CuDonateList;
import com.huacainfo.ace.cu.service.CuDonateListService;
import com.huacainfo.ace.cu.vo.CuDonateListQVo;
import com.huacainfo.ace.cu.vo.CuDonateListVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cuDonateList")
/**
 * @author: Arvin
 * @version: 2018-07-12
 * @Description: TODO(捐献记录)
 */
public class CuDonateListController extends CuBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CuDonateListService cuDonateListService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(捐献记录分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <CuDonateListVo>
     * @author: Arvin
     * @version: 2018-07-12
     */
    @RequestMapping(value = "/findCuDonateListList")
    @ResponseBody
    public PageResult<CuDonateListVo> findCuDonateListList(CuDonateListQVo condition,
                                                           PageParamNoChangeSord page) throws Exception {
        PageResult<CuDonateListVo> rst = this.cuDonateListService
                .findCuDonateListList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertCuDonateList
     * @Description: TODO(添加捐献记录)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-12
     */
    @RequestMapping(value = "/insertCuDonateList")
    @ResponseBody
    public MessageResponse insertCuDonateList(String jsons) throws Exception {
        CuDonateList obj = JSON.parseObject(jsons, CuDonateList.class);
        return this.cuDonateListService.insertCuDonateList(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateCuDonateList
     * @Description: TODO(更新捐献记录)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-12
     */
    @RequestMapping(value = "/updateCuDonateList")
    @ResponseBody
    public MessageResponse updateCuDonateList(String jsons) throws Exception {
        CuDonateList obj = JSON.parseObject(jsons, CuDonateList.class);
        return this.cuDonateListService.updateCuDonateList(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectCuDonateListByPrimaryKey
     * @Description: TODO(获取捐献记录)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CuDonateList>
     * @author: Arvin
     * @version: 2018-07-12
     */
    @RequestMapping(value = "/selectCuDonateListByPrimaryKey")
    @ResponseBody
    public SingleResult<CuDonateListVo> selectCuDonateListByPrimaryKey(String id) throws Exception {
        return this.cuDonateListService.selectCuDonateListByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteCuDonateListByCuDonateListId
     * @Description: TODO(删除捐献记录)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-12
     */
    @RequestMapping(value = "/deleteCuDonateListByCuDonateListId")
    @ResponseBody
    public MessageResponse deleteCuDonateListByCuDonateListId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.cuDonateListService.deleteCuDonateListByCuDonateListId(id, this.getCurUserProp());
    }
}
