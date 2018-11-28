package com.huacainfo.ace.society.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.SpaceOccupyInfo;
import com.huacainfo.ace.society.service.SpaceOccupyInfoService;
import com.huacainfo.ace.society.vo.SpaceOccupyInfoQVo;
import com.huacainfo.ace.society.vo.SpaceOccupyInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/spaceOccupyInfo")
/**
 * @author: Arvin
 * @version: 2018-09-14
 * @Description: TODO(场地占用情况)
 */
public class SpaceOccupyInfoController extends SocietyBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SpaceOccupyInfoService spaceOccupyInfoService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(场地占用情况分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <SpaceOccupyInfoVo>
     * @author: Arvin
     * @version: 2018-09-14
     */
    @RequestMapping(value = "/findSpaceOccupyInfoList")
    @ResponseBody
    public PageResult<SpaceOccupyInfoVo> findSpaceOccupyInfoList(SpaceOccupyInfoQVo condition,
                                                                 PageParamNoChangeSord page) throws Exception {

        PageResult<SpaceOccupyInfoVo> rst = spaceOccupyInfoService.findSpaceOccupyInfoList(condition,
                page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertSpaceOccupyInfo
     * @Description: TODO(添加场地占用情况)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-14
     */
    @RequestMapping(value = "/insertSpaceOccupyInfo")
    @ResponseBody
    public MessageResponse insertSpaceOccupyInfo(String jsons) throws Exception {
        SpaceOccupyInfo obj = JSON.parseObject(jsons, SpaceOccupyInfo.class);
        return this.spaceOccupyInfoService.insertSpaceOccupyInfo(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateSpaceOccupyInfo
     * @Description: TODO(更新场地占用情况)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-14
     */
    @RequestMapping(value = "/updateSpaceOccupyInfo")
    @ResponseBody
    public MessageResponse updateSpaceOccupyInfo(String jsons) throws Exception {
        SpaceOccupyInfo obj = JSON.parseObject(jsons, SpaceOccupyInfo.class);
        return this.spaceOccupyInfoService.updateSpaceOccupyInfo(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectSpaceOccupyInfoByPrimaryKey
     * @Description: TODO(获取场地占用情况)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SpaceOccupyInfo>
     * @author: Arvin
     * @version: 2018-09-14
     */
    @RequestMapping(value = "/selectSpaceOccupyInfoByPrimaryKey")
    @ResponseBody
    public SingleResult<SpaceOccupyInfoVo> selectSpaceOccupyInfoByPrimaryKey(String id) throws Exception {
        return this.spaceOccupyInfoService.selectSpaceOccupyInfoByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteSpaceOccupyInfoBySpaceOccupyInfoId
     * @Description: TODO(删除场地占用情况)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-14
     */
    @RequestMapping(value = "/deleteSpaceOccupyInfoBySpaceOccupyInfoId")
    @ResponseBody
    public MessageResponse deleteSpaceOccupyInfoBySpaceOccupyInfoId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.spaceOccupyInfoService.deleteSpaceOccupyInfoBySpaceOccupyInfoId(id, this.getCurUserProp());
    }

    /***
     * 管理员场地锁定
     * @param spaceId 场地ID
     * @param dateTime 锁定日期
     * @param interval 锁定时间段
     * @return MessageResponse
     * @throws Exception
     */
    @RequestMapping(value = "/spaceLock")
    @ResponseBody
    public MessageResponse spaceLock(String spaceId, String dateTime, String interval) throws Exception {

        return this.spaceOccupyInfoService.spaceLock(spaceId, dateTime, interval, this.getCurUserProp());
    }


    /***
     * 管理员 移除场地锁定
     * @param id 数据ID
     * @return MessageResponse
     * @throws Exception
     */
    @RequestMapping(value = "/removeLock")
    @ResponseBody
    public MessageResponse removeLock(String id) throws Exception {

        return this.spaceOccupyInfoService.removeLock(id, this.getCurUserProp());
    }


}
