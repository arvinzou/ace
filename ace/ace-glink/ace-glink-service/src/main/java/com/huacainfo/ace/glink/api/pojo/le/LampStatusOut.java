package com.huacainfo.ace.glink.api.pojo.le;

import com.huacainfo.ace.glink.api.pojo.base.LeBaseOut;

/**
 * @ClassName LampStatusOut
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/4/22 16:11
 */
public class LampStatusOut extends LeBaseOut {
//{"code":200,"message":"查询成功！","data":{"LampCount":0,"BrokenLampCount":0}}


    private LampStatus data;

    public LampStatus getData() {
        return data;
    }

    public void setData(LampStatus data) {
        this.data = data;
    }

    /**
     *
     */

    public static class LampStatus {
        private int LampCount;  //总设备数
        private int BrokenLampCount;//故障设备数

        public int getLampCount() {
            return LampCount;
        }

        public void setLampCount(int lampCount) {
            LampCount = lampCount;
        }

        public int getBrokenLampCount() {
            return BrokenLampCount;
        }

        public void setBrokenLampCount(int brokenLampCount) {
            BrokenLampCount = brokenLampCount;
        }
    }
}
