package com.huacainfo.ace.fop.web.controller;


import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.fop.service.FopCompanyService;
import com.huacainfo.ace.fop.service.FopNoticeService;
import com.huacainfo.ace.fop.vo.FopCompanyQVo;
import com.huacainfo.ace.fop.vo.FopCompanyVo;
import com.huacainfo.ace.fop.vo.FopNoticeQVo;
import com.huacainfo.ace.fop.vo.FopNoticeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/www")
public class WWWController {
    @Autowired
    private FopCompanyService fopCompanyService;

    @Autowired
    private FopNoticeService fopNoticeService;

    /**
     * gis地图
     *
     * @param condition
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/companyGis")
    @ResponseBody
    public PageResult<FopCompanyVo> findFopCompanyList(FopCompanyQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<FopCompanyVo> rst = this.fopCompanyService
                .findFopCompanyList(condition, page.getStart(), page.getLimit(),
                        page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }


    /**
     * 查询公告列表
     *
     * @param page  页码
     * @param limit 每页数目
     * @param title 搜索关键字
     * @param sort  排序 null：降序，不为null：升序
     * @param noticeType  信息类型 1、市场信息 2、产品信息 3、项目信息 4 、招商信息
     * @return return rst;
     *
     * @throws Exception
     */
    @RequestMapping(value = "/findNoticeList")
    @ResponseBody
    public PageResult<FopNoticeVo> findFopNoticeList(FopNoticeQVo condition, int page, int limit, String sort) throws Exception {
        PageResult<FopNoticeVo> rst = this.fopNoticeService.findNoticeList(condition, sort, page, limit);
        return rst;
    }


    /**
     * 查询公告列表
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/homepageNoticeList")
    @ResponseBody
    public PageResult<FopNoticeVo> homepageNoticeList() throws Exception {
        PageResult<FopNoticeVo> rst = this.fopNoticeService.homepageNoticeList();
        return rst;
    }
}
