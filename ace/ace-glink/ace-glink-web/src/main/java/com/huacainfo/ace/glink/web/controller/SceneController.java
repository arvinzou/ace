package com.huacainfo.ace.glink.web.controller;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.glink.service.TopStationService;
import com.huacainfo.ace.glink.service.TopSubareaService;
import com.huacainfo.ace.glink.vo.TopSubareaQVo;
import com.huacainfo.ace.glink.vo.TopSubareaVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/scene")
/**
 * @author: heshuang
 * @version: 2019-04-11
 * @Description: TODO(场景控制)
 *  */
public class SceneController extends GLinkBaseController {
    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TopStationService topStationService;
    @Autowired
    private TopSubareaService topSubareaService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(分区管理分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<TopSubareaVo>
     * @author: Arvin
     * @version: 2019-04-09
     */
    @RequestMapping(value = "/findTopSubareaList")
    @ResponseBody
    public PageResult<TopSubareaVo> findTopSubareaList(TopSubareaQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult<TopSubareaVo> rst =
                topSubareaService.findTopSubareaList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }


}
