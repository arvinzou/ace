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
import com.huacainfo.ace.society.model.SubjectIdeaAnnex;
import com.huacainfo.ace.society.service.SubjectIdeaAnnexService;
import com.huacainfo.ace.society.vo.SubjectIdeaAnnexVo;
import com.huacainfo.ace.society.vo.SubjectIdeaAnnexQVo;

@Controller
@RequestMapping("/subjectIdeaAnnex")
/**
* @author: Arvin
* @version: 2018-09-13
* @Description:  TODO(议题点子附件)
*/
public class SubjectIdeaAnnexController extends SocietyBaseController {


private static final long serialVersionUID = 1L;
Logger logger = LoggerFactory.getLogger(this.getClass());
@Autowired
private SubjectIdeaAnnexService subjectIdeaAnnexService;

/**
*
* @Title:find!{bean.name}List
* @Description:  TODO(议题点子附件分页查询)
* @param:        @param condition
* @param:        @param page
* @param:        @return
* @param:        @throws Exception
* @return:       PageResult
<SubjectIdeaAnnexVo>
    * @throws
    * @author: Arvin
    * @version: 2018-09-13
    */
    @RequestMapping(value = "/findSubjectIdeaAnnexList")
    @ResponseBody
    public PageResult
    <SubjectIdeaAnnexVo> findSubjectIdeaAnnexList(SubjectIdeaAnnexQVo condition,
        PageParamNoChangeSord page) throws Exception {

        PageResult
        <SubjectIdeaAnnexVo> rst = this.subjectIdeaAnnexService
            .findSubjectIdeaAnnexList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
            if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
            }

            return rst;
            }

            /**
            *
            * @Title:insertSubjectIdeaAnnex
            * @Description: TODO(添加议题点子附件)
            * @param: @param jsons
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: Arvin
            * @version: 2018-09-13
            */
            @RequestMapping(value = "/insertSubjectIdeaAnnex")
            @ResponseBody
            public MessageResponse insertSubjectIdeaAnnex(String jsons) throws Exception {
            SubjectIdeaAnnex obj = JSON.parseObject(jsons, SubjectIdeaAnnex.class);
            return this.subjectIdeaAnnexService.insertSubjectIdeaAnnex(obj, this.getCurUserProp());
            }

            /**
            *
            * @Title:updateSubjectIdeaAnnex
            * @Description: TODO(更新议题点子附件)
            * @param: @param jsons
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: Arvin
            * @version: 2018-09-13
            */
            @RequestMapping(value = "/updateSubjectIdeaAnnex")
            @ResponseBody
            public MessageResponse updateSubjectIdeaAnnex(String jsons) throws Exception {
            SubjectIdeaAnnex obj = JSON.parseObject(jsons, SubjectIdeaAnnex.class);
            return this.subjectIdeaAnnexService.updateSubjectIdeaAnnex(obj, this.getCurUserProp());
            }

            /**
            *
            * @Title:selectSubjectIdeaAnnexByPrimaryKey
            * @Description: TODO(获取议题点子附件)
            * @param: @param id
            * @param: @throws Exception
            * @return: SingleResult<SubjectIdeaAnnex>
            * @throws
            * @author: Arvin
            * @version: 2018-09-13
            */
            @RequestMapping(value = "/selectSubjectIdeaAnnexByPrimaryKey")
            @ResponseBody
            public SingleResult
            <SubjectIdeaAnnexVo> selectSubjectIdeaAnnexByPrimaryKey(String id)throws Exception {
                return this.subjectIdeaAnnexService.selectSubjectIdeaAnnexByPrimaryKey(id);
                }

                /**
                *
                * @Title:deleteSubjectIdeaAnnexBySubjectIdeaAnnexId
                * @Description: TODO(删除议题点子附件)
                * @param: @param jsons
                * @param: @throws Exception
                * @return: MessageResponse
                * @throws
                * @author: Arvin
                * @version: 2018-09-13
                */
                @RequestMapping(value = "/deleteSubjectIdeaAnnexBySubjectIdeaAnnexId")
                @ResponseBody
                public MessageResponse deleteSubjectIdeaAnnexBySubjectIdeaAnnexId(String jsons) throws Exception {
                JSONObject json = JSON.parseObject(jsons);
                String id = json.getString("id");
                return this.subjectIdeaAnnexService.deleteSubjectIdeaAnnexBySubjectIdeaAnnexId(id, this.getCurUserProp());
                }

                /**
                *
                * @Title:audit
                * @Description: TODO(审核议题点子附件)
                * @param: @param id bean.id
                * @param: @param rst 审核结果 3-通过 4-拒绝
                * @param: @param message 审核说明
                * @param: @throws Exception
                * @return: MessageResponse
                * @throws
                * @author: Arvin
                * @version: 2018-09-13
                */
                @RequestMapping(value = "/audit")
                @ResponseBody
                public MessageResponse audit(String id,String rst, String message) throws Exception {

                return this.subjectIdeaAnnexService.audit(id, rst, message, this.getCurUserProp());
                }
                }
