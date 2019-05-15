package com.huacainfo.ace.glink.model;

import com.huacainfo.ace.common.model.BaseModel;

/**
 * @ClassName BMap
 * @Description 百度地图下载瓦片使用
 * @Author Arvin Zou
 * @Date 2019/5/15 10:14
 */
public class BMap extends BaseModel {
    /**
     * 图片网络地址
     */
    private String image;
    /**
     * titles文件路径
     */
    private String path;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
