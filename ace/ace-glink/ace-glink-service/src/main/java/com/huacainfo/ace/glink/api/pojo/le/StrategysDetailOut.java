package com.huacainfo.ace.glink.api.pojo.le;

import com.huacainfo.ace.glink.api.pojo.base.LeBaseOut;

import java.util.List;

/**
 * @ClassName StrategysDetailOut
 * @Description 武汉策略信息详情接口 -回参
 * @Author Arvin Zou
 * @Date 2019/4/15 18:24
 */
public class StrategysDetailOut extends LeBaseOut {

    private List<Strategy> data;

    public List<Strategy> getData() {
        return data;
    }

    public void setData(List<Strategy> data) {
        this.data = data;
    }

    //**************************static classes**************************************

    public static class Strategy {
        private String StrategyNum;//策略编号
        private String StrategyName;//策略名称
        private String StrategyExplain;//简要说明
        private int IsPlaying;//0：未播放，1：正在播放

        public String getStrategyNum() {
            return StrategyNum;
        }

        public void setStrategyNum(String strategyNum) {
            StrategyNum = strategyNum;
        }

        public String getStrategyName() {
            return StrategyName;
        }

        public void setStrategyName(String strategyName) {
            StrategyName = strategyName;
        }

        public String getStrategyExplain() {
            return StrategyExplain;
        }

        public void setStrategyExplain(String strategyExplain) {
            StrategyExplain = strategyExplain;
        }

        public int getIsPlaying() {
            return IsPlaying;
        }

        public void setIsPlaying(int isPlaying) {
            IsPlaying = isPlaying;
        }
    }
}
