package com.huacainfo.ace.glink.vo;

import com.huacainfo.ace.glink.model.LeBrokenLamp;


public class LeBrokenLampQVo extends LeBrokenLamp {

    /**
     * 搜索关键字
     */
    private String keyword;

/**查询标志*/
    private String today;

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
