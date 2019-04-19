package com.huacainfo.ace.glink.api.pojo.fe;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.List;

/**
 * @ClassName PresetDataOut
 * @Description 场景定义列表
 * @Author Arvin Zou
 * @Date 2019/4/17 15:27
 */
public class PresetDataOut extends BaseModel {

    private int PresetCount;                //集合数量

    private List<PresetData> PresetData;    //集合对象列表

    public int getPresetCount() {
        return PresetCount;
    }

    public void setPresetCount(int presetCount) {
        PresetCount = presetCount;
    }

    public List<PresetData> getPresetData() {
        return PresetData;
    }

    public void setPresetData(List<PresetData> presetData) {
        PresetData = presetData;
    }

    /**
     * static class
     */
    public static class PresetData extends BaseModel {
        public PresetData(int presetNo, String presetName) {
            super();
            PresetNo = presetNo;
            PresetName = presetName;
        }

        public PresetData() {
            super();
        }

        private int PresetNo;//场景号
        private String PresetName;//场景名称

        public int getPresetNo() {
            return PresetNo;
        }

        public void setPresetNo(int presetNo) {
            PresetNo = presetNo;
        }

        public String getPresetName() {
            return PresetName;
        }

        public void setPresetName(String presetName) {
            PresetName = presetName;
        }
    }
}
