package com.huacainfo.ace.glink.api.pojo.fe;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.List;

/**
 * @ClassName CustomAreaOut
 * @Description 逻辑区定义数据
 * @Author Arvin Zou
 * @Date 2019/4/17 15:08
 */
public class CustomAreaOut extends BaseModel {

    private int AreaCount;              //逻辑区数量

    private List<AreaData> AreaData;    //逻辑区数据集合

    public int getAreaCount() {
        return AreaCount;
    }

    public void setAreaCount(int areaCount) {
        AreaCount = areaCount;
    }

    public List<CustomAreaOut.AreaData> getAreaData() {
        return AreaData;
    }

    public void setAreaData(List<CustomAreaOut.AreaData> areaData) {
        AreaData = areaData;
    }

    /**
     * static class
     */
    public static class AreaData extends BaseModel {
        private String AreaNodeID;          //区域编号
        private int AreaNo;              //区号
        private String AreaName;         //区名称

        public String getAreaNodeID() {
            return AreaNodeID;
        }

        public void setAreaNodeID(String areaNodeID) {
            AreaNodeID = areaNodeID;
        }

        public int getAreaNo() {
            return AreaNo;
        }

        public void setAreaNo(int areaNo) {
            AreaNo = areaNo;
        }

        public String getAreaName() {
            return AreaName;
        }

        public void setAreaName(String areaName) {
            AreaName = areaName;
        }
    }
}
