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
import com.huacainfo.ace.society.model.Subject;
import com.huacainfo.ace.society.service.SubjectService;
import com.huacainfo.ace.society.vo.SubjectVo;
import com.huacainfo.ace.society.vo.SubjectQVo;

@Controller
@RequestMapping("/subject")
/**
* @author: lcan
* @version: 2018-09-12
* @Description:  TODO(方案提议)
*/
public class SubjectController extends SocietyBaseController {


private static final long serialVersionUID = 1L;
Logger logger = LoggerFactory.getLogger(this.getClass());
@Autowired
private SubjectService subjectService;

/**
*
* @Title:find!{bean.name}List
* @Description:  TODO(方案提议分页查询)
* @param:        @param condition
* @param:        @param page
* @param:        @return
* @param:        @throws Exception
* @return:       PageResult
<SubjectVo>
    * @throws
    * @author: lcan
    * @version: 2018-09-12
    */
    @RequestMapping(value = "/findSubjectList")
    @ResponseBody
    public PageResult
    <SubjectVo> findSubjectList(SubjectQVo condition,
        PageParamNoChangeSord page) throws Exception {

        PageResult
        <SubjectVo> rst = this.subjectService
            .findSubjectList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
            if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
            }

            return rst;
            }

            /**
            *
            * @Title:insertSubject
            * @Description: TODO(添加方案提议)
            * @param: @param jsons
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: lcan
            * @version: 2018-09-12
            */
            @RequestMapping(value = "/insertSubject")
            @ResponseBody
            public MessageResponse insertSubject(String jsons) throws Exception {
            Subject obj = JSON.parseObject(jsons, Subject.class);
            return this.subjectService.insertSubject(obj, this.getCurUserProp());
            }

            /**
            *
            * @Title:updateSubject
            * @Description: TODO(更新方案提议)
            * @param: @param jsons
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: lcan
            * @version: 2018-09-12
            */
            @RequestMapping(value = "/updateSubject")
            @ResponseBody
            public MessageResponse updateSubject(String jsons) throws Exception {
            Subject obj = JSON.parseObject(jsons, Subject.class);
            return this.subjectService.updateSubject(obj, this.getCurUserProp());
            }

            /**
            *
            * @Title:selectSubjectByPrimaryKey
            * @Description: TODO(获取方案提议)
            * @param: @param id
            * @param: @throws Exception
            * @return: SingleResult<Subject>
            * @throws
            * @author: lcan
            * @version: 2018-09-12
            */
            @RequestMapping(value = "/selectSubjectByPrimaryKey")
            @ResponseBody
            public SingleResult
            <SubjectVo> selectSubjectByPrimaryKey(String id)throws Exception {
                return this.subjectService.selectSubjectByPrimaryKey(id);
                }

                /**
                *
                * @Title:deleteSubjectBySubjectId
                * @Description: TODO(删除方案提议)
                * @param: @param jsons
                * @param: @throws Exception
                * @return: MessageResponse
                * @throws
                * @author: lcan
                * @version: 2018-09-12
                */
                @RequestMapping(value = "/deleteSubjectBySubjectId")
                @ResponseBody
                public MessageResponse deleteSubjectBySubjectId(String jsons) throws Exception {
                JSONObject json = JSON.parseObject(jsons);
                String id = json.getString("id");
                return this.subjectService.deleteSubjectBySubjectId(id, this.getCurUserProp());
                }

                /**
                *
                * @Title:audit
                * @Description: TODO(审核方案提议)
                * @param: @param id bean.id
                * @param: @param rst 审核结果 3-通过 4-拒绝
                * @param: @param remark 审核备注
                * @param: @throws Exception
                * @return: MessageResponse
                * @throws
                * @author: lcan
                * @version: 2018-09-12
                */
                @RequestMapping(value = "/audit")
                @ResponseBody
                public MessageResponse audit(String id,String rst, String remark) throws Exception {

                return this.subjectService.audit(id, rst, remark, this.getCurUserProp());
                }
                }
