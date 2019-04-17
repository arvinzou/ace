package com.huacainfo.ace.glink.api.pojo.le;

import com.huacainfo.ace.common.model.BaseModel;
import com.huacainfo.ace.glink.api.pojo.base.LeBaseOut;

import java.util.List;

/**
 * @ClassName LightBrokenSumOut
 * @Description 武汉设备故障数量接口 - 回参
 * @Author Arvin Zou
 * @Date 2019/4/15 18:06
 */
public class LightBrokenSumOut extends LeBaseOut {

    private List<LightBroken> data;

    public List<LightBroken> getData() {
        return data;
    }

    public void setData(List<LightBroken> data) {
        this.data = data;
    }

//**************************static classes**************************************

    public static class LightBroken extends BaseModel {
        private String date;
        private int brokenNum;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getBrokenNum() {
            return brokenNum;
        }

        public void setBrokenNum(int brokenNum) {
            this.brokenNum = brokenNum;
        }
    }
}
