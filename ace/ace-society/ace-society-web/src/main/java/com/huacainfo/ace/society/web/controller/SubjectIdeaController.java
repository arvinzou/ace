package com.huacainfo.ace.society.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.SubjectIdea;
import com.huacainfo.ace.society.service.SubjectIdeaService;
import com.huacainfo.ace.society.vo.SubjectIdeaQVo;
import com.huacainfo.ace.society.vo.SubjectIdeaVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/subjectIdea")
/**
 * @author: Arvin
 * @version: 2018-09-13
 * @Description: TODO(议题点子)
 */
public class SubjectIdeaController extends SocietyBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SubjectIdeaService subjectIdeaService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(议题点子分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <SubjectIdeaVo>
     * @author: Arvin
     * @version: 2018-09-13
     */
    @RequestMapping(value = "/findSubjectIdeaList")
    @ResponseBody
    public PageResult<SubjectIdeaVo> findSubjectIdeaList(SubjectIdeaQVo condition,
                                                         PageParamNoChangeSord page) throws Exception {

        PageResult<SubjectIdeaVo> rst =
                this.subjectIdeaService.findSubjectIdeaList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertSubjectIdea
     * @Description: TODO(添加议题点子)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-13
     */
    @RequestMapping(value = "/insertSubjectIdea")
    @ResponseBody
    public MessageResponse insertSubjectIdea(String jsons) throws Exception {
        SubjectIdea obj = JSON.parseObject(jsons, SubjectIdea.class);
        return this.subjectIdeaService.insertSubjectIdea(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateSubjectIdea
     * @Description: TODO(更新议题点子)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-13
     */
    @RequestMapping(value = "/updateSubjectIdea")
    @ResponseBody
    public MessageResponse updateSubjectIdea(String jsons) throws Exception {
        SubjectIdea obj = JSON.parseObject(jsons, SubjectIdea.class);
        return this.subjectIdeaService.updateSubjectIdea(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectSubjectIdeaByPrimaryKey
     * @Description: TODO(获取议题点子)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SubjectIdea>
     * @author: Arvin
     * @version: 2018-09-13
     */
    @RequestMapping(value = "/selectSubjectIdeaByPrimaryKey")
    @ResponseBody
    public SingleResult<SubjectIdeaVo> selectSubjectIdeaByPrimaryKey(String id) throws Exception {
        return this.subjectIdeaService.selectSubjectIdeaByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteSubjectIdeaBySubjectIdeaId
     * @Description: TODO(删除议题点子)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-13
     */
    @RequestMapping(value = "/deleteSubjectIdeaBySubjectIdeaId")
    @ResponseBody
    public MessageResponse deleteSubjectIdeaBySubjectIdeaId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.subjectIdeaService.deleteSubjectIdeaBySubjectIdeaId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核议题点子)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-13
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.subjectIdeaService.audit(id, rst, message, this.getCurUserProp());
    }
}
