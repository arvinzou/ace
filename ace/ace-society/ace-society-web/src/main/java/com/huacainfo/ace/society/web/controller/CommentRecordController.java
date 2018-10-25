package com.huacainfo.ace.society.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.CommentRecord;
import com.huacainfo.ace.society.service.CommentRecordService;
import com.huacainfo.ace.society.vo.CommentRecordQVo;
import com.huacainfo.ace.society.vo.CommentRecordVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/commentRecord")
/**
 * @author: ArvinZou
 * @version: 2018-10-24
 * @Description: TODO(评论管理)
 */
public class CommentRecordController extends SocietyBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CommentRecordService commentRecordService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(评论管理分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <CommentRecordVo>
     * @author: ArvinZou
     * @version: 2018-10-24
     */
    @RequestMapping(value = "/findCommentRecordList")
    @ResponseBody
    public PageResult<CommentRecordVo> findCommentRecordList(CommentRecordQVo condition,
                                                             PageParamNoChangeSord page) throws Exception {

        PageResult<CommentRecordVo> rst = this.commentRecordService
                .findCommentRecordList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertCommentRecord
     * @Description: TODO(添加评论管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2018-10-24
     */
    @RequestMapping(value = "/insertCommentRecord")
    @ResponseBody
    public MessageResponse insertCommentRecord(String jsons) throws Exception {
        CommentRecord obj = JSON.parseObject(jsons, CommentRecord.class);
        return this.commentRecordService.insertCommentRecord(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateCommentRecord
     * @Description: TODO(更新评论管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2018-10-24
     */
    @RequestMapping(value = "/updateCommentRecord")
    @ResponseBody
    public MessageResponse updateCommentRecord(String jsons) throws Exception {
        CommentRecord obj = JSON.parseObject(jsons, CommentRecord.class);
        return this.commentRecordService.updateCommentRecord(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectCommentRecordByPrimaryKey
     * @Description: TODO(获取评论管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CommentRecord>
     * @author: ArvinZou
     * @version: 2018-10-24
     */
    @RequestMapping(value = "/selectCommentRecordByPrimaryKey")
    @ResponseBody
    public SingleResult<CommentRecordVo> selectCommentRecordByPrimaryKey(String id) throws Exception {
        return this.commentRecordService.selectCommentRecordByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteCommentRecordByCommentRecordId
     * @Description: TODO(删除评论管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2018-10-24
     */
    @RequestMapping(value = "/deleteCommentRecordByCommentRecordId")
    @ResponseBody
    public MessageResponse deleteCommentRecordByCommentRecordId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.commentRecordService.deleteCommentRecordByCommentRecordId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核评论管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2018-10-24
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.commentRecordService.audit(id, rst, message, this.getCurUserProp());
    }
}
