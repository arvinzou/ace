package com.huacainfo.ace.woc.wechat.entity;


public class DataItem {
    public DataItem() {
    }
    private Object value;
    private String color;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public DataItem(Object value, String color) {
        this.value = value;
        this.color = color;
    }
}
