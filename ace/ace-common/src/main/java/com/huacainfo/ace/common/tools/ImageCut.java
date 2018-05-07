package com.huacainfo.ace.common.tools;

/**
 * Created by chenxiaoke on 2018/5/6.
 */
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageCut {
    /**
     * 截取图片
     *
     * @param srcImageFile
     *            原图片地址
     * @param x
     *            截取时的x坐标
     * @param y
     *            截取时的y坐标
     * @param desWidth
     *            截取的宽度
     * @param desHeight
     *            截取的高度
     * @param srcWidth
     *            页面图片的宽度
     * @param srcHeight
     *            页面图片的高度
     *
     */
    public static byte[] imgCut(InputStream input, int x, int y, int desWidth, int desHeight, int srcWidth,
                                int srcHeight) {
        try {
            Image img;
            ImageFilter cropFilter;
            BufferedImage bi = ImageIO.read(input);
            if (srcWidth >= desWidth && srcHeight >= desHeight) {
                Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
                cropFilter = new CropImageFilter(x, y, desWidth, desHeight);
                img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
                BufferedImage tag = new BufferedImage(desWidth, desHeight, BufferedImage.TYPE_INT_RGB);
                Graphics g = tag.getGraphics();
                g.drawImage(img, 0, 0, null);
                g.dispose();
                // 输出文件
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                ImageIO.write(tag, "JPEG", out);
                return out.toByteArray();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String args[]) throws Exception{
        try{
            java.io.InputStream is =new java.io.FileInputStream(new java.io.File("/Users/chenxiaoke/Pictures/left_pic_two.jpg"));

            FileUtil.byte2File(ImageCut.imgCut(is,64,114,326,326,500,500),"/Users/chenxiaoke/Pictures/",GUIDUtil.getGUID()+".jpg");

        }catch (Exception e){
            e.printStackTrace();;
        }


    }
}
