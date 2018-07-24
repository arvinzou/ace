package com.huacainfo.ace.jxb.service;

import com.huacainfo.ace.common.result.ResultResponse;

/**
 * @Auther: Arvin
 * @Date: 2018/7/24 11:20
 * @Description:
 */
public interface WeChatService {
    /**
     * 二维码扫描 - 关注事件
     *
     * @param scene  二维码携带场景值
     * @param openid 扫描人openid
     * @return
     */
    ResultResponse qrCodeSubscribe(String scene, String openid);

    /**
     * 二维码扫描 - 扫描事件
     *
     * @param scene  二维码携带场景值
     * @param openid 扫描人openid
     * @return
     */
    ResultResponse qrCodeScan(String scene, String openid);
}
