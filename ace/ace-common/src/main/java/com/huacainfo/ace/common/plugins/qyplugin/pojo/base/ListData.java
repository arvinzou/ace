package com.huacainfo.ace.common.plugins.qyplugin.pojo.base;

/**
 * @ClassName ListData
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/3/18 11:01
 */
public class ListData extends Data {
    /**
     * 总记录数
     */
    private String totalpage;
    /**
     * 总页数
     */
    private String total;
    /**
     * 当前页
     */
    private String page;

    public String getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(String totalpage) {
        this.totalpage = totalpage;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
