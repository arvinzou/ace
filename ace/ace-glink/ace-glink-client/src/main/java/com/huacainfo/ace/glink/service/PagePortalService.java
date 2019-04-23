package com.huacainfo.ace.glink.service;

import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.glink.model.PagePortal;

import java.util.List;

/**
 * @ClassName PagePortalService
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/4/22 15:54
 */
public interface PagePortalService {

    /**
     * 根据key索引
     *
     * @param itemKey key值
     * @param sysId   系统ID
     * @return PagePortal
     */
    PagePortal findByKey(String itemKey, String sysId);

    /**
     * 根据系统ID，获取所有key-value
     *
     * @param sysId 系统ID
     * @return List<PagePortal>
     */
    List<PagePortal> findListBySysId(String sysId);

//    int insertOrUpdate(PagePortal obj);

    /**
     * 每隔[5]分钟,调用一次弱电接口，获取设备总数&故障设备总数
     */
    MessageResponse getLampStatus();

}
