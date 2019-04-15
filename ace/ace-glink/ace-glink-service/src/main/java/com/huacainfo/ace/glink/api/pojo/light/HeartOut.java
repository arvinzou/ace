package com.huacainfo.ace.glink.api.pojo.light;

import com.huacainfo.ace.common.model.BaseModel;
import com.huacainfo.ace.glink.api.pojo.base.BaseOut;

import java.util.List;

/**
 * @ClassName HeartOut
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/4/15 10:31
 */
public class HeartOut extends BaseOut {

    private HeartData data;//返回数据

    public HeartData getData() {
        return data;
    }

    public void setData(HeartData data) {
        this.data = data;
    }

    public static class HeartData extends BaseModel {
        private String area;//分区编号
        private int status;//平台状态(1:正常 2:异常)
        private List<HeartError> infos;//平台异常数据集合

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<HeartError> getInfos() {
            return infos;
        }

        public void setInfos(List<HeartError> infos) {
            this.infos = infos;
        }
    }

    public static class HeartError extends BaseModel {
        /**
         * 异常类型（1:服务器异常 2：网络异常 3：其它异常）
         */
        private int type;
        /**
         * 异常的具体说明
         */
        private String reason;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }

}

