package com.huacainfo.ace.society.vo;

import com.huacainfo.ace.society.model.Behavior;
import com.huacainfo.ace.society.model.BehaviorAnnex;

import java.util.List;


public class BehaviorVo extends Behavior {
    private static final long serialVersionUID = 1L;

    private List<BehaviorAnnexVo> behaviorAnnexList;

    public List<BehaviorAnnexVo> getBehaviorAnnexList() {
        return behaviorAnnexList;
    }

    public void setBehaviorAnnexList(List<BehaviorAnnexVo> behaviorAnnexList) {
        this.behaviorAnnexList = behaviorAnnexList;
    }
}
