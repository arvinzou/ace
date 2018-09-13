package com.huacainfo.ace.society.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.PersonInfo;
import com.huacainfo.ace.society.service.PersonInfoService;
import com.huacainfo.ace.society.vo.PersonInfoQVo;
import com.huacainfo.ace.society.vo.PersonInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/personInfo")
/**
 * @author: Arvin
 * @version: 2018-09-11
 * @Description: TODO(个人信息)
 */
public class PersonInfoController extends SocietyBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PersonInfoService personInfoService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(个人信息分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<PersonInfoVo>
     * @author: Arvin
     * @version: 2018-09-11
     */
    @RequestMapping(value = "/findPersonInfoList")
    @ResponseBody
    public PageResult<PersonInfoVo> findPersonInfoList(PersonInfoQVo condition,
                                                       PageParamNoChangeSord page) throws Exception {

        PageResult<PersonInfoVo> rst = this.personInfoService
                .findPersonInfoList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertPersonInfo
     * @Description: TODO(添加个人信息)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-11
     */
    @RequestMapping(value = "/insertPersonInfo")
    @ResponseBody
    public MessageResponse insertPersonInfo(String jsons) throws Exception {
        PersonInfo obj = JSON.parseObject(jsons, PersonInfo.class);
        return this.personInfoService.insertPersonInfo(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updatePersonInfo
     * @Description: TODO(更新个人信息)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-11
     */
    @RequestMapping(value = "/updatePersonInfo")
    @ResponseBody
    public MessageResponse updatePersonInfo(String jsons) throws Exception {
        PersonInfo obj = JSON.parseObject(jsons, PersonInfo.class);
        return this.personInfoService.updatePersonInfo(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectPersonInfoByPrimaryKey
     * @Description: TODO(获取个人信息)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<PersonInfo>
     * @author: Arvin
     * @version: 2018-09-11
     */
    @RequestMapping(value = "/selectPersonInfoByPrimaryKey")
    @ResponseBody
    public SingleResult<PersonInfoVo> selectPersonInfoByPrimaryKey(String id) throws Exception {
        return this.personInfoService.selectPersonInfoByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deletePersonInfoByPersonInfoId
     * @Description: TODO(删除个人信息)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-11
     */
    @RequestMapping(value = "/deletePersonInfoByPersonInfoId")
    @ResponseBody
    public MessageResponse deletePersonInfoByPersonInfoId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.personInfoService.deletePersonInfoByPersonInfoId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核个人信息)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-11
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.personInfoService.audit(id, rst, message, this.getCurUserProp());
    }
}
