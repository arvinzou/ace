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
    private int totalpage;
    /**
     * 总页数
     */
    private int total;
    /**
     * 当前页
     */
    private int page;


    public int getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
