package com.huacainfo.ace.society.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.service.AuthorityService;
import com.huacainfo.ace.society.model.Circle;
import com.huacainfo.ace.society.model.CircleImg;
import com.huacainfo.ace.society.model.Img;
import com.huacainfo.ace.society.service.CircleService;
import com.huacainfo.ace.society.vo.CircleQVo;
import com.huacainfo.ace.society.vo.CircleVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/circle")
/**
 * @author: 陈晓克
 * @version: 2018-09-20
 * @Description: TODO(圈子)
 */
public class CircleController extends SocietyBaseController {
    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CircleService circleService;

    @Autowired
    private AuthorityService authorityService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(圈子分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <CircleVo>
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    @RequestMapping(value = "/findCircleList")
    @ResponseBody
    public PageResult<CircleVo> findCircleList(CircleQVo condition, PageParamNoChangeSord page) throws Exception {
        condition.setCorpId(this.getCurUserProp().getCorpId());
        PageResult<CircleVo> rst = this.circleService.findCircleList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertCircle
     * @Description: TODO(添加圈子)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    @RequestMapping(value = "/insertCircle")
    @ResponseBody
    public MessageResponse insertCircle(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        Circle obj = JSON.parseObject(((JSONObject) json.get("circle")).toJSONString(), Circle.class);
        List<CircleImg> imgs = JSON.parseArray(((JSONArray) json.get("imgs")).toJSONString(), CircleImg.class);
        return this.circleService.insertCircle(obj,imgs);
    }

    /**
     * @throws
     * @Title:updateCircle
     * @Description: TODO(更新圈子)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    @RequestMapping(value = "/updateCircle")
    @ResponseBody
    public MessageResponse updateCircle(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        Circle obj = JSON.parseObject(((JSONObject) json.get("circle")).toJSONString(), Circle.class);
        List<CircleImg> imgs = JSON.parseArray(((JSONArray) json.get("imgs")).toJSONString(), CircleImg.class);
        return this.circleService.updateCircle(obj,imgs);
    }

    /**
     * @throws
     * @Title:selectCircleByPrimaryKey
     * @Description: TODO(获取圈子)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Circle>
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    @RequestMapping(value = "/selectCircleByPrimaryKey")
    @ResponseBody
    public SingleResult<CircleVo> selectCircleByPrimaryKey(String id) throws Exception {
        return this.circleService.selectCircleByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteCircleByCircleId
     * @Description: TODO(删除圈子)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    @RequestMapping(value = "/deleteCircleByCircleId")
    @ResponseBody
    public MessageResponse deleteCircleByCircleId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.circleService.deleteCircleByCircleId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核圈子)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String text) throws Exception {
        return this.circleService.audit(id, rst, text, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:insertCircle
     * @Description: TODO(添加圈子)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    @RequestMapping(value = "/www/insertCircle")
    @ResponseBody
    public MessageResponse insertCircleWww(String jsons) throws Exception {
        SingleResult<UserProp> rst=authorityService.getCurUserPropByOpenId(this.getCurWxUser().getUnionId());
        if(rst.getStatus()==0){
            JSONObject json = JSON.parseObject(jsons);
            json.put("uid",this.getCurWxUser().getUnionId());
            Circle obj = JSON.parseObject(((JSONObject) json.get("circle")).toJSONString(), Circle.class);
            List<CircleImg> imgs = JSON.parseArray(((JSONArray) json.get("imgs")).toJSONString(), CircleImg.class);
            return this.circleService.insertCircle(obj,imgs);
        }
        return rst;
    }
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(圈子分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <CircleVo>
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    @RequestMapping(value = "/www/findCircleList")
    @ResponseBody
    public PageResult<CircleVo> findCircleListWww(CircleQVo condition, PageParamNoChangeSord page) throws Exception {
        condition.setCorpId(this.getCurUserProp().getCorpId());
        PageResult<CircleVo> rst = this.circleService.findCircleList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }
}
