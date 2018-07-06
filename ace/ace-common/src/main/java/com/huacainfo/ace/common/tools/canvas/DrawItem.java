package com.huacainfo.ace.common.tools.canvas;

import java.io.Serializable;

/**
 * @Auther: Arvin
 * @Date: 2018/7/5 16:34
 * @Description:
 */
public class DrawItem implements Serializable {
    public static final int DRAW_TYPE_IMAGE = 0;
    public static final int DRAW_TYPE_TEXT = 1;
    private static final long serialVersionUID = 1L;
    //绘制类型
    private int drawType;//0-图片；1-字体
    //绘制位置设置
    private int x;//左上角坐标 - x
    private int y;//左上角坐标 - y
    private int width;//绘制宽度 -- 绘制图片时必传
    private int height;//绘制高度-- 绘制图片时必传
    //字体设置
    private String color;//rgb颜色，Demo:255,255,255,0
    private String fontName;//字体名称- Demo: 宋体,
    private int fontStyle;//字体风格  0-PLAIN, 1-BOLD, 2-ITALIC 可选
    private int fontSize;//字体大小

    public DrawItem() {
    }

    /**
     * 绘制图片数据
     *
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public DrawItem(int x, int y, int width, int height) {
        this.drawType = DRAW_TYPE_IMAGE;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * 绘制字体
     *
     * @param x
     * @param y
     * @param color
     * @param fontName
     * @param fontStyle
     * @param fontSize
     */
    public DrawItem(int x, int y, String color, String fontName, int fontStyle, int fontSize) {
        this.drawType = DRAW_TYPE_TEXT;
        this.x = x;
        this.y = y;
        this.color = color;
        this.fontName = fontName;
        this.fontStyle = fontStyle;
        this.fontSize = fontSize;
    }

    public int getDrawType() {
        return drawType;
    }

    public void setDrawType(int drawType) {
        drawType = drawType;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public int getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(int fontStyle) {
        this.fontStyle = fontStyle;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }
}
