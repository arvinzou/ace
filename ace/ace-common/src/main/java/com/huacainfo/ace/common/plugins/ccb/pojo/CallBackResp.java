package com.huacainfo.ace.common.plugins.ccb.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.huacainfo.ace.common.model.BaseModel;

import java.math.BigDecimal;

/**
 * @Auther: Arvin
 * @Date: 2018/9/7 09:22
 * @Description:
 */
public class CallBackResp extends BaseModel {
    /**
     * 商户柜台代码  - 从商户传送的信息中获得
     */
    @JSONField(name = "POSID")
    private String POSID;
    /**
     * 分行代码 -   从商户传送的信息中获得
     */
    @JSONField(name = "BRANCHID")
    private String BRANCHID;
    /**
     * 定单号  -   从商户传送的信息中获得
     */
    @JSONField(name = "ORDERID")
    private String ORDERID;
    /**
     * 付款金额 -   从商户传送的信息中获得
     */
    @JSONField(name = "PAYMENT")
    private BigDecimal PAYMENT;
    /**
     * 币种   -   从商户传送的信息中获得
     */
    @JSONField(name = "CURCODE")
    private String CURCODE;
    /**
     * 备注一  -   从商户传送的信息中获得
     */
    @JSONField(name = "REMARK1")
    private String REMARK1;
    /**
     * 备注二  -   从商户传送的信息中获得
     */
    @JSONField(name = "REMARK2")
    private String REMARK2;
    /**
     * 账户类型 -   服务器通知中有此字段返回且参与验签
     */
    @JSONField(name = "ACC_TYPE")
    private String ACC_TYPE;
    /**
     * 成功标志 -   成功－Y，失败－N
     */
    @JSONField(name = "SUCCESS")
    private String SUCCESS;

    /**
     * 接口类型
     * <p>
     * 分行业务人员在P2员工渠道后台设置防钓鱼的开关。
     * 1.开关关闭时，无此字段返回且不参与验签。
     * 2.开关打开时，有此字段返回且参与验签。参数值为
     * 1-	防钓鱼接口
     */
    @JSONField(name = "TYPE")
    private String TYPE;
    /**
     * Referer信息
     * <p>
     * 分行业务人员在P2员工渠道后台设置防钓鱼开关。
     * 1.开关关闭时，无此字段返回且不参与验签。
     * 2.开关打开时，有此字段返回且参与验签。
     */
    @JSONField(name = "REFERER")
    private String REFERER;
    /**
     * 客户端IP
     * <p>
     * 分行业务人员在P2员工渠道后台设置防钓鱼的开关。
     * 1.开关关闭时，无此字段返回且不参与验签。
     * 2.开关打开时，有此字段返回且参与验签。参数值为
     * 客户在建行系统中的IP
     */
    @JSONField(name = "CLIENTIP")
    private String CLIENTIP;
    /**
     * 系统记账日期
     * <p>
     * 商户登陆商户后台设置返回记账日期的开关
     * 1.开关关闭时，无此字段返回且不参与验签。
     * 2.开关打开时，有此字段返回且参与验签。参数值格式为YYYYMMDD（如20100907）。
     */
    @JSONField(name = "ACCDATE")
    private String ACCDATE;
    /**
     * 支付账户信息
     * <p>
     * 分行业务人员在P2员工渠道后台设置防钓鱼开关和返回账户信息的开关。
     * 1.开关关闭时，无此字段返回且不参与验签。
     * 2.开关打开但支付失败时，无此字段返回且不参与验签。
     * 3.开关打开且支付成功时，有此字段返回且参与验签。无PAYTYPE返回时，
     * 参数值格式如下：“姓名|账号加密后的密文”。有PAYTYPE返回时，该参数值为空。
     */
    @JSONField(name = "USRMSG")
    private String USRMSG;
    /**
     * 客户加密信息
     * <p>
     * 分行业务人员在P2员工渠道后台设置防钓鱼开关和客户信息加密返回的开关。
     * 1.开关关闭时，无此字段返回且不参与验签
     * 2.开关打开时，有此字段返回且参数验签。无PAYTYPE返回时，
     * 参数值格式如下：“证件号密文|手机号密文”。该字段不可解密。有PAYTYPE返回时，该参数值为空。
     */
    @JSONField(name = "USRINFO")
    private String USRINFO;
    /**
     * 支付方式
     * <p>
     * ALIPAY:支付宝
     * WEIXIN：微信
     * 为空：建行龙支付
     * 该字段有返回时参与验签，无此字段返回时不参与验签。
     */
    @JSONField(name = "PAYTYPE")
    private String PAYTYPE;
    /**
     * 数字签名
     * <p>
     * 签名字段
     */
    @JSONField(name = "SIGN")
    private String SIGN;

    public String getPOSID() {
        return POSID;
    }

    public void setPOSID(String POSID) {
        this.POSID = POSID;
    }

    public String getBRANCHID() {
        return BRANCHID;
    }

    public void setBRANCHID(String BRANCHID) {
        this.BRANCHID = BRANCHID;
    }

    public String getORDERID() {
        return ORDERID;
    }

    public void setORDERID(String ORDERID) {
        this.ORDERID = ORDERID;
    }

    public BigDecimal getPAYMENT() {
        return PAYMENT;
    }

    public void setPAYMENT(BigDecimal PAYMENT) {
        this.PAYMENT = PAYMENT;
    }

    public String getCURCODE() {
        return CURCODE;
    }

    public void setCURCODE(String CURCODE) {
        this.CURCODE = CURCODE;
    }

    public String getREMARK1() {
        return REMARK1;
    }

    public void setREMARK1(String REMARK1) {
        this.REMARK1 = REMARK1;
    }

    public String getREMARK2() {
        return REMARK2;
    }

    public void setREMARK2(String REMARK2) {
        this.REMARK2 = REMARK2;
    }

    public String getACC_TYPE() {
        return ACC_TYPE;
    }

    public void setACC_TYPE(String ACC_TYPE) {
        this.ACC_TYPE = ACC_TYPE;
    }

    public String getSUCCESS() {
        return SUCCESS;
    }

    public void setSUCCESS(String SUCCESS) {
        this.SUCCESS = SUCCESS;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getREFERER() {
        return REFERER;
    }

    public void setREFERER(String REFERER) {
        this.REFERER = REFERER;
    }

    public String getCLIENTIP() {
        return CLIENTIP;
    }

    public void setCLIENTIP(String CLIENTIP) {
        this.CLIENTIP = CLIENTIP;
    }

    public String getACCDATE() {
        return ACCDATE;
    }

    public void setACCDATE(String ACCDATE) {
        this.ACCDATE = ACCDATE;
    }

    public String getUSRMSG() {
        return USRMSG;
    }

    public void setUSRMSG(String USRMSG) {
        this.USRMSG = USRMSG;
    }

    public String getUSRINFO() {
        return USRINFO;
    }

    public void setUSRINFO(String USRINFO) {
        this.USRINFO = USRINFO;
    }

    public String getPAYTYPE() {
        return PAYTYPE;
    }

    public void setPAYTYPE(String PAYTYPE) {
        this.PAYTYPE = PAYTYPE;
    }

    public String getSIGN() {
        return SIGN;
    }

    public void setSIGN(String SIGN) {
        this.SIGN = SIGN;
    }

}
