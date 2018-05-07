package com.huacainfo.ace.fop.web.controller;


import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.fop.service.FopCompanyService;
import com.huacainfo.ace.fop.service.FopNoticeService;
import com.huacainfo.ace.fop.vo.FopCompanyQVo;
import com.huacainfo.ace.fop.vo.FopCompanyVo;
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
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findNoticeList")
    @ResponseBody
    public PageResult<FopNoticeVo> findFopNoticeList(int page, int limit) throws Exception {
        PageResult<FopNoticeVo> rst = null;// this.fopNoticeService.findNoticeList((page - 1) * limit, limit);
        return rst;
    }
}
