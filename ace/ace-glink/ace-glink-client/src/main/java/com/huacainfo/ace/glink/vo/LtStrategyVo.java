package com.huacainfo.ace.glink.vo;

import com.huacainfo.ace.glink.model.LtStrategy;


public class LtStrategyVo extends LtStrategy {
    private static final long serialVersionUID = 1L;
    /**
     * 场景名称
     */
    private String sceneName;
    /**
     * 场景描述
     */
    private String sceneDepict;

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public String getSceneDepict() {
        return sceneDepict;
    }

    public void setSceneDepict(String sceneDepict) {
        this.sceneDepict = sceneDepict;
    }
}
