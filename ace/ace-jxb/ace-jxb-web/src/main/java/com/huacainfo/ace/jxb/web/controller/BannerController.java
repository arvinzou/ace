package com.huacainfo.ace.jxb.web.controller;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.Banner;
import com.huacainfo.ace.jxb.service.BannerService;
import com.huacainfo.ace.jxb.vo.BannerQVo;
import com.huacainfo.ace.jxb.vo.BannerVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/banner")
/**
 * @author: 陈晓克
 * @version: 2018-09-26
 * @Description: TODO(轮播图)
 */
public class BannerController extends JxbBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BannerService bannerService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(轮播图分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <BannerVo>
     * @author: 陈晓克
     * @version: 2018-09-26
     */
    @RequestMapping(value = "/findBannerList")
    @ResponseBody
    public PageResult<BannerVo> findBannerList(BannerQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<BannerVo> rst = this.bannerService.findBannerList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertBanner
     * @Description: TODO(添加轮播图)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-26
     */
    @RequestMapping(value = "/insertBanner")
    @ResponseBody
    public MessageResponse insertBanner(String jsons) throws Exception {
        Banner obj = JSON.parseObject(jsons, Banner.class);
        return this.bannerService.insertBanner(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateBanner
     * @Description: TODO(更新轮播图)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-26
     */
    @RequestMapping(value = "/updateBanner")
    @ResponseBody
    public MessageResponse updateBanner(String jsons) throws Exception {
        Banner obj = JSON.parseObject(jsons, Banner.class);
        return this.bannerService.updateBanner(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectBannerByPrimaryKey
     * @Description: TODO(获取轮播图)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Banner>
     * @author: 陈晓克
     * @version: 2018-09-26
     */
    @RequestMapping(value = "/selectBannerByPrimaryKey")
    @ResponseBody
    public SingleResult<BannerVo> selectBannerByPrimaryKey(String id) throws Exception {
        return this.bannerService.selectBannerByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteBannerByBannerId
     * @Description: TODO(删除轮播图)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-26
     */
    @RequestMapping(value = "/deleteBannerByBannerId")
    @ResponseBody
    public MessageResponse deleteBannerByBannerId(String id) throws Exception {
        return this.bannerService.deleteBannerByBannerId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核轮播图)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-26
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.bannerService.audit(id, rst, message, this.getCurUserProp());
    }
}
