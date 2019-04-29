package com.huacainfo.ace.glink.web.controller;

import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.glink.constant.PortalKey;
import com.huacainfo.ace.glink.model.PagePortal;
import com.huacainfo.ace.glink.service.PagePortalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/pagePortal")
/**
 * @author: huanglong
 * @version: 2019-04-128
 * @Description: TODO(弱电运行状态)
 *  */

public class PagePortalController extends GLinkBaseController {
    private static final long serialVersionUID = 1L;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PagePortalService pagePortalService;

    /**
     * @return PagePortal状态集合
     * @throws Exception
     */
    @RequestMapping(value = "/findList")
    @ResponseBody
    public List<PagePortal> findWeakCurrentStatusList(){

        List<PagePortal> vList = pagePortalService.findListBySysId(this.getCurUserProp().getActiveSyId());
        List<PagePortal> tempList = new ArrayList<>();
        String str1 = PortalKey.LE_sceneControlState;
        String str2 = PortalKey.LE_playbackStatus;
        for (PagePortal item : vList) {
            if(item.getItemKey().equals(str1) || item.getItemKey().equals(str2)){
                tempList.add(item);
            }
        }
        return tempList;
    }

    /**
     * 插入数据后返回的状态
     * @param key 主键key
     * @param val
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updatePagePortalData")
    @ResponseBody
    public MessageResponse updatePagePortalConfig(String key,String val) throws Exception {
        return this.pagePortalService.updatePagePortalData(key,val);
    }
}
