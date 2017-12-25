package com.huacainfo.ace.rvc.vo;

import com.huacainfo.ace.common.tools.JsonUtil;

/**
 * Created by Arvin on 2017/12/25.
 */
public class SearchCondition {

    /**
     * 搜索域 ,all-全部,related-与我相关的
     */
    private String searchRegion;

    /**
     * 当前用户id
     */
    private String userId;

    /**
     * 搜索标题
     */
    private String title;

    /**
     * 分页开始位置
     */
    private int start;

    /**
     * 分页条数
     */
    private int limit;

    /**
     * 市编码
     */
    private String city;
    /**
     * 区编码
     */
    private String district;
    /**
     * 乡镇编码
     */
    private String town;

    public String getSearchRegion() {
        return searchRegion;
    }

    public void setSearchRegion(String searchRegion) {
        this.searchRegion = searchRegion;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        if (limit <= 1)
            return 10;
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
