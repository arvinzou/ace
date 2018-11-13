package com.huacainfo.ace.common.plugins.ccb.pojo;

import java.io.Serializable;

/**
 * @Auther: Arvin
 * @Date: 2018/9/5 09:22
 * @Description:
 */
public class CCBConfig implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 商户代码 Y   由建行统一分配
     */
    private String merchantId;
    /**
     * 商户柜台代码    Y   由建行统一分配
     */
    private String posId;
    /**
     * 分行代码    Y   由建行统一指定
     */
    private String branchId;
    /**
     * 公钥 -- 商户平台下载:后30位
     */
    private String pub30Str;

    /**
     * 查询密码 -- 商户平台登录密码
     */
    private String qupwd;

    public CCBConfig() {
    }

    /**
     * 支付下单配置
     */
    public CCBConfig(String merchantId, String posId, String branchId, String pub32Str) {
        this.merchantId = merchantId;
        this.posId = posId;
        this.branchId = branchId;
        this.pub30Str = pub32Str;
    }

    /**
     * 订单查询配置
     */
    public static CCBConfig getQueryCfg(String merchantId, String branchId, String posId, String qupwd) {
        CCBConfig cfg = new CCBConfig();
        cfg.merchantId = merchantId;
        cfg.posId = posId;
        cfg.branchId = branchId;
        cfg.qupwd = qupwd;
        return cfg;
    }

    public String getQupwd() {
        return qupwd;
    }

    public void setQupwd(String qupwd) {
        this.qupwd = qupwd;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getPosId() {
        return posId;
    }

    public void setPosId(String posId) {
        this.posId = posId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getPub30Str() {
        return pub30Str;
    }

    public void setPub30Str(String pub30Str) {
        this.pub30Str = pub30Str;
    }
}
