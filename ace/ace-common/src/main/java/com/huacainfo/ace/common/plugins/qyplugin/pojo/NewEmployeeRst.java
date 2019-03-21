package com.huacainfo.ace.common.plugins.qyplugin.pojo;

import com.huacainfo.ace.common.plugins.qyplugin.pojo.base.ApiRst;

/**
 * @ClassName NewEmployeeRst
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/3/20 11:34
 */
public class NewEmployeeRst extends ApiRst {

    private Employee data;

    public Employee getData() {
        return data;
    }

    public void setData(Employee data) {
        this.data = data;
    }

    public static class Employee {
        private String account;//考勤编号 --员工CC号

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }
    }
}
