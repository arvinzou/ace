package com.huacainfo.ace.jxb.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.Studio;
import com.huacainfo.ace.jxb.service.StudioService;
import com.huacainfo.ace.jxb.vo.StudioQVo;
import com.huacainfo.ace.jxb.vo.StudioVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/studio")
/**
 * @author: Arvin
 * @version: 2018-07-28
 * @Description: (工作室)
 */
public class StudioController extends JxbBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private StudioService studioService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(工作室分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <StudioVo>
     * @author: Arvin
     * @version: 2018-07-28
     */
    @RequestMapping(value = "/findStudioList")
    @ResponseBody
    public PageResult<StudioVo> findStudioList(StudioQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<StudioVo> rst = this.studioService
                .findStudioList(condition, (page.getPage()-1)*page.getLimit(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertStudio
     * @Description: TODO(添加工作室)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-28
     */
    @RequestMapping(value = "/insertStudio")
    @ResponseBody
    public MessageResponse insertStudio(Studio obj) throws Exception {
        return this.studioService.insertStudio(obj, this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:insertStudio
     * @Description: TODO(添加工作室)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-28
     */
    @RequestMapping(value = "/modifyStudio")
    @ResponseBody
    public MessageResponse modifyStudio(String json) throws Exception {
        JSONObject jsonObj = JSON.parseObject(json);
        Studio obj = JSON.parseObject(jsonObj.getString("object"), Studio.class);
        List<String> list = JSON.parseArray(jsonObj.getString("imgUrl"), String.class);
        return this.studioService.modifyStudio(obj, list, this.getCurUserProp());
    }




    /**
     * @throws
     * @Title:updateStudio
     * @Description: TODO(更新工作室)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-28
     */
    @RequestMapping(value = "/updateStudio")
    @ResponseBody
    public MessageResponse updateStudio(String jsons) throws Exception {
        Studio obj = JSON.parseObject(jsons, Studio.class);
        return this.studioService.updateStudio(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectStudioByPrimaryKey
     * @Description: TODO(获取工作室)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Studio>
     * @author: Arvin
     * @version: 2018-07-28
     */
    @RequestMapping(value = "/selectStudioByPrimaryKey")
    @ResponseBody
    public SingleResult<StudioVo> selectStudioByPrimaryKey(String id) throws Exception {
        return this.studioService.selectStudioByPrimaryKey(id);
    }


    /**
     * 获取我的工作室列表
     */
    @RequestMapping(value = "/getMyStudioList")
    @ResponseBody
    public ResultResponse getStudioList() throws Exception {
        String counselorId = this.getCurUserProp().getUserId();
        Map<String, Object> voList = null;
        try {
            voList = studioService.getStudioList(counselorId);
        } catch (CustomException e) {
            return new ResultResponse(ResultCode.FAIL, e.getMsg());
        }

        return new ResultResponse(ResultCode.SUCCESS, "获取成功", voList);
    }


    /**
     * @throws
     * @Title:selectStudioByPrimaryKey
     * @Description: TODO(获取工作室)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Studio>
     * @author: Arvin
     * @version: 2018-07-28
     */
    @RequestMapping(value = "/getMyStudioInfo")
    @ResponseBody
    public ResultResponse getMyStudioInfo() throws Exception {
        return this.studioService.getMyStudioInfo(this.getCurUserProp().getUserId());
    }

    /**
     * @throws
     * @Title:deleteStudioByStudioId
     * @Description: TODO(删除工作室)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-28
     */
    @RequestMapping(value = "/deleteStudioByStudioId")
    @ResponseBody
    public MessageResponse deleteStudioByStudioId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.studioService.deleteStudioByStudioId(id, this.getCurUserProp());
    }


    /***
     *
     * @param studioId 工作室ID
     * @param auditRs 审核结果  0- 待审核 1-审核通过 2-审核拒绝
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String studioId, String auditRs) throws Exception {
        return this.studioService.audit(studioId, auditRs, this.getCurUserProp());
    }
}
