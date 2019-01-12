package com.huacainfo.ace.partyschool.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.math.BigDecimal;

/**
 * @Auther: Arvin
 * @Date: 2019/1/11 11:08
 * @Description:
 */
public class TeacherFinRsVo extends BaseModel {
    /**
     * 交易时间
     */
    private String dealTime;
    /**
     * 采集时间
     */
    private String receiveTime;
    /**
     * 卡号
     */
    private String cardNo;
    /**
     * 借阅证号
     */
    private String lCardNo;
    /**
     * 用户名称
     */
    private String customerName;
    /**
     * 用户状态值
     */
    private String customerState;
    /**
     * 用户状态值-名称
     */
    private String customerStateName;
    /**
     * 分区名称
     */
    private String partitionName;
    /**
     * 用户组织
     * 0001	中共常德市委党校
     * 00010001	教职员工
     * 00010004	退调临客
     * 00010005	新华书店
     * 00010002	物业工勤
     * 00010003	培训学员
     */
    private String customerDept;
    /**
     * 用户组织名称
     */
    private String orgName;
    /**
     * 当前消费金额
     */
    private BigDecimal consumption;
    /**
     * 消费前余额
     */
    private BigDecimal preBalance;
    /**
     * 当前账户余额(消费后余额)
     */
    private BigDecimal curBalance;
    /**
     * 科目名称
     */
    private String subjectName;
    /**
     * 终端名称
     */
    private String termName;
    /**
     * 交易名称
     */
    private String bisCodeName;
    /**
     * 应用名称
     */
    private String appSysName;

    public String getDealTime() {
        return dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getlCardNo() {
        return lCardNo;
    }

    public void setlCardNo(String lCardNo) {
        this.lCardNo = lCardNo;
    }

    public String getCustomerState() {
        return customerState;
    }

    public void setCustomerState(String customerState) {
        this.customerState = customerState;
    }

    public String getCustomerStateName() {
        return customerStateName;
    }

    public void setCustomerStateName(String customerStateName) {
        this.customerStateName = customerStateName;
    }

    public String getPartitionName() {
        return partitionName;
    }

    public void setPartitionName(String partitionName) {
        this.partitionName = partitionName;
    }

    public String getCustomerDept() {
        return customerDept;
    }

    public void setCustomerDept(String customerDept) {
        this.customerDept = customerDept;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }


    public BigDecimal getConsumption() {
        return null != consumption ? consumption.setScale(2) : consumption;
    }

    public void setConsumption(BigDecimal consumption) {
        this.consumption = consumption;
    }

    public BigDecimal getPreBalance() {
        return null != preBalance ? preBalance.setScale(2) : preBalance;
    }

    public void setPreBalance(BigDecimal preBalance) {
        this.preBalance = preBalance;
    }

    public BigDecimal getCurBalance() {
        return null != curBalance ? curBalance.setScale(2) : curBalance;
    }

    public void setCurBalance(BigDecimal curBalance) {
        this.curBalance = curBalance;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getBisCodeName() {
        return bisCodeName;
    }

    public void setBisCodeName(String bisCodeName) {
        this.bisCodeName = bisCodeName;
    }

    public String getAppSysName() {
        return appSysName;
    }

    public void setAppSysName(String appSysName) {
        this.appSysName = appSysName;
    }
}
