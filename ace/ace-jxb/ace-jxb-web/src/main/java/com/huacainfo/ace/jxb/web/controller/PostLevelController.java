package com.huacainfo.ace.jxb.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.PostLevel;
import com.huacainfo.ace.jxb.service.PostLevelService;
import com.huacainfo.ace.jxb.vo.PostLevelQVo;
import com.huacainfo.ace.jxb.vo.PostLevelVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/postLevel")
/**
 * @author: Arvin
 * @version: 2018-08-08
 * @Description: TODO(咨询师岗位级别配置)
 */
public class PostLevelController extends JxbBaseController {

    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PostLevelService postLevelService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(咨询师岗位级别配置分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <PostLevelVo>
     * @author: Arvin
     * @version: 2018-08-08
     */
    @RequestMapping(value = "/findPostLevelList")
    @ResponseBody
    public PageResult<PostLevelVo> findPostLevelList(PostLevelQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<PostLevelVo> rst = this.postLevelService.findPostLevelList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertPostLevel
     * @Description: TODO(添加咨询师岗位级别配置)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-08
     */
    @RequestMapping(value = "/insertPostLevel")
    @ResponseBody
    public MessageResponse insertPostLevel(String jsons) throws Exception {
        PostLevel obj = JSON.parseObject(jsons, PostLevel.class);
        return this.postLevelService.insertPostLevel(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updatePostLevel
     * @Description: TODO(更新咨询师岗位级别配置)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-08
     */
    @RequestMapping(value = "/updatePostLevel")
    @ResponseBody
    public MessageResponse updatePostLevel(String jsons) throws Exception {
        PostLevel obj = JSON.parseObject(jsons, PostLevel.class);
        return this.postLevelService.updatePostLevel(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectPostLevelByPrimaryKey
     * @Description: TODO(获取咨询师岗位级别配置)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<PostLevel>
     * @author: Arvin
     * @version: 2018-08-08
     */
    @RequestMapping(value = "/selectPostLevelByPrimaryKey")
    @ResponseBody
    public SingleResult<PostLevelVo> selectPostLevelByPrimaryKey(String id) throws Exception {
        return this.postLevelService.selectPostLevelByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deletePostLevelByPostLevelId
     * @Description: TODO(删除咨询师岗位级别配置)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-08
     */
    @RequestMapping(value = "/deletePostLevelByPostLevelId")
    @ResponseBody
    public MessageResponse deletePostLevelByPostLevelId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.postLevelService.deletePostLevelByPostLevelId(id, this.getCurUserProp());
    }
}
