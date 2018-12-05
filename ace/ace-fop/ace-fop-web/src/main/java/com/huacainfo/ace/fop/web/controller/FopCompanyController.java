package com.huacainfo.ace.fop.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.fop.service.FopCompanyService;
import com.huacainfo.ace.fop.vo.FopCompanyQVo;
import com.huacainfo.ace.fop.vo.FopCompanyVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/fopCompany")
/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: 企业管理
 */
public class FopCompanyController extends FopBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopCompanyService fopCompanyService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(企业管理分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<FopCompanyVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/findFopCompanyList")
    @ResponseBody
    public PageResult<FopCompanyVo> findFopCompanyList(FopCompanyQVo condition,
                                                       PageParamNoChangeSord page) throws Exception {
        if (StringUtil.isNotEmpty(condition.getCompanyTypeStr())) {
            condition.setCompanyTypeArray(condition.getCompanyTypeStr().split(","));
        }
        PageResult<FopCompanyVo> rst =
                fopCompanyService.findFopCompanyList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertFopCompany
     * @Description: TODO(添加企业管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/insertFopCompany")
    @ResponseBody
    public MessageResponse insertFopCompany(String jsons) throws Exception {
        try {
            FopCompanyVo obj = JSON.parseObject(jsons, FopCompanyVo.class);

            return fopCompanyService.insertFopCompany(obj, this.getCurUserProp());
        } catch (CustomException e) {
            return new MessageResponse(ResultCode.FAIL, e.getMsg());
        } catch (Exception e) {
            return new MessageResponse(ResultCode.FAIL, "添加失败", CommonUtils.getExceptionStack(e));
        }
    }

    /**
     * @throws
     * @Title:updateFopCompany
     * @Description: TODO(更新企业管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/updateFopCompany")
    @ResponseBody
    public MessageResponse updateFopCompany(String jsons) throws Exception {
        FopCompanyVo obj = JSON.parseObject(jsons, FopCompanyVo.class);
        return this.fopCompanyService.updateFopCompany(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectFopCompanyByPrimaryKey
     * @Description: TODO(获取企业管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopCompany>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/selectFopCompanyByPrimaryKey")
    @ResponseBody
    public SingleResult<FopCompanyVo> selectFopCompanyByPrimaryKey(String id)
            throws Exception {
        return this.fopCompanyService.selectFopCompanyByPrimaryKey(id);
    }

    @RequestMapping(value = "/findByPK")
    @ResponseBody
    public SingleResult<FopCompanyVo> findByPK(String id)
            throws Exception {
        return this.fopCompanyService.findByPK(id);
    }

    /**
     * @throws
     * @Title:deleteFopCompanyByFopCompanyId
     * @Description: TODO(删除企业管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/deleteFopCompanyByFopCompanyId")
    @ResponseBody
    public MessageResponse deleteFopCompanyByFopCompanyId(String jsons)
            throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.fopCompanyService.deleteFopCompanyByFopCompanyId(id,
                this.getCurUserProp());
    }


    @RequestMapping(value = "/selectCompany")
    @ResponseBody
    public Map<String, Object> selectAuthor(String q, String id) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("q", id);
        if (!CommonUtils.isBlank(q)) {
            params.put("q", q);
        }
        this.logger.info("", params);

        return this.fopCompanyService.selectCompany(params);
    }

    /**
     * 账号恢复
     *
     * @param id   唯一主键
     * @param type 会员类型 0-企业/个人/银行 1-团体
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/recoverData")
    @ResponseBody
    public MessageResponse recoverData(String id, String type) throws Exception {
        if (!StringUtil.areNotEmpty(id, type)) {
            return new MessageResponse(ResultCode.FAIL, "缺少必要参数");
        }

        return fopCompanyService.recoverData(id, type, this.getCurUserProp());
    }

    /**
     * 恢复会员身份
     *
     * @param id 唯一主键
     */
    @RequestMapping(value = "/reJoin")
    @ResponseBody
    public MessageResponse reJoin(String id) throws Exception {
        if (!StringUtil.areNotEmpty(id)) {
            return new MessageResponse(ResultCode.FAIL, "缺少必要参数");
        }

        return fopCompanyService.reJoin(id, this.getCurUserProp());
    }

    /**
     * 删除电子附件
     *
     * @param id 主键ID
     * @return MessageResponse
     */
    @RequestMapping(value = "/delAnnex")
    @ResponseBody
    public MessageResponse delAnnex(String id)
            throws Exception {
        return this.fopCompanyService.delAnnex(id);
    }
}
