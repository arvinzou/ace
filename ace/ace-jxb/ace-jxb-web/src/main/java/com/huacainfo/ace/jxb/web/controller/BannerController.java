package com.huacainfo.ace.jxb.web.controller;

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
import com.huacainfo.ace.jxb.model.Banner;
import com.huacainfo.ace.jxb.service.BannerService;
import com.huacainfo.ace.jxb.vo.BannerVo;
import com.huacainfo.ace.jxb.vo.BannerQVo;

@Controller
@RequestMapping("/banner")
/**
* @author: huacai003
* @version: 2018-09-26
* @Description:  TODO(banner)
*/
public class BannerController extends JxbBaseController {


private static final long serialVersionUID = 1L;
Logger logger = LoggerFactory.getLogger(this.getClass());
@Autowired
private BannerService bannerService;

/**
*
* @Title:find!{bean.name}List
* @Description:  TODO(banner分页查询)
* @param:        @param condition
* @param:        @param page
* @param:        @return
* @param:        @throws Exception
* @return:       PageResult
<BannerVo>
    * @throws
    * @author: huacai003
    * @version: 2018-09-26
    */
    @RequestMapping(value = "/findBannerList")
    @ResponseBody
    public PageResult
    <BannerVo> findBannerList(BannerQVo condition,
        PageParamNoChangeSord page) throws Exception {

        PageResult
        <BannerVo> rst = this.bannerService
            .findBannerList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
            if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
            }

            return rst;
            }

            /**
            *
            * @Title:insertBanner
            * @Description: TODO(添加banner)
            * @param: @param jsons
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: huacai003
            * @version: 2018-09-26
            */
            @RequestMapping(value = "/insertBanner")
            @ResponseBody
            public MessageResponse insertBanner(String jsons) throws Exception {
            Banner obj = JSON.parseObject(jsons, Banner.class);
            return this.bannerService.insertBanner(obj, this.getCurUserProp());
            }

            /**
            *
            * @Title:updateBanner
            * @Description: TODO(更新banner)
            * @param: @param jsons
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: huacai003
            * @version: 2018-09-26
            */
            @RequestMapping(value = "/updateBanner")
            @ResponseBody
            public MessageResponse updateBanner(String jsons) throws Exception {
            Banner obj = JSON.parseObject(jsons, Banner.class);
            return this.bannerService.updateBanner(obj, this.getCurUserProp());
            }

            /**
            *
            * @Title:selectBannerByPrimaryKey
            * @Description: TODO(获取banner)
            * @param: @param id
            * @param: @throws Exception
            * @return: SingleResult<Banner>
            * @throws
            * @author: huacai003
            * @version: 2018-09-26
            */
            @RequestMapping(value = "/selectBannerByPrimaryKey")
            @ResponseBody
            public SingleResult
            <BannerVo> selectBannerByPrimaryKey(String id)throws Exception {
                return this.bannerService.selectBannerByPrimaryKey(id);
                }

                /**
                *
                * @Title:deleteBannerByBannerId
                * @Description: TODO(删除banner)
                * @param: @param jsons
                * @param: @throws Exception
                * @return: MessageResponse
                * @throws
                * @author: huacai003
                * @version: 2018-09-26
                */
                @RequestMapping(value = "/deleteBannerByBannerId")
                @ResponseBody
                public MessageResponse deleteBannerByBannerId(String jsons) throws Exception {
                JSONObject json = JSON.parseObject(jsons);
                String id = json.getString("id");
                return this.bannerService.deleteBannerByBannerId(id, this.getCurUserProp());
                }

}
