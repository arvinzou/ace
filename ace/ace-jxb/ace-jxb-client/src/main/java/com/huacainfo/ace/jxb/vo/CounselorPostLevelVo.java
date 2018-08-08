package com.huacainfo.ace.jxb.vo;

import com.huacainfo.ace.jxb.model.CounselorPostLevel;

import java.math.BigDecimal;

/**
 * @Auther: Arvin
 * @Date: 2018/8/7 15:31
 * @Description:
 */
public class CounselorPostLevelVo extends CounselorPostLevel {

    /**
     * 岗位名称
     */
    private String postName;

    /**
     * 分成比例
     */
    private BigDecimal ratio;

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }
}
