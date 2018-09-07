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

    public CCBConfig() {
    }

    public CCBConfig(String merchantId, String posId, String branchId, String pub32Str) {
        this.merchantId = merchantId;
        this.posId = posId;
        this.branchId = branchId;
        this.pub30Str = pub32Str;
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
