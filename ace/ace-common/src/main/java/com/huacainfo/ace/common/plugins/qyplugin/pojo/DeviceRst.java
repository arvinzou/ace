package com.huacainfo.ace.common.plugins.qyplugin.pojo;

import com.huacainfo.ace.common.plugins.qyplugin.pojo.base.ApiRst;
import com.huacainfo.ace.common.plugins.qyplugin.pojo.base.Data;

import java.util.List;

/**
 * @ClassName DeviceRst
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/3/19 16:42
 */
public class DeviceRst extends ApiRst {

    private List<Device> data;

    public List<Device> getData() {
        return data;
    }

    public void setData(List<Device> data) {
        this.data = data;
    }

    //    {"expired":"0","remark":"警官培训中心01","sn":"A3MA184660202","updatetime":1552902155}
    public static class Device extends Data {
        private String remark;//设备名称
        private String sn;//设备编码
        private int expired;
        private long updatetime;

        public int getExpired() {
            return expired;
        }

        public void setExpired(int expired) {
            this.expired = expired;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public long getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(long updatetime) {
            this.updatetime = updatetime;
        }
    }
}
