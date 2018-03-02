package com.huacainfo.ace.common.tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;
import javax.imageio.ImageIO;
/**
 * @ClassName:WaterMarkUtils.java
 * @Description:图片水印<br>
 *  图片水印实现思路：<br>
 *  1.创建缓存图片对象--BufferedImage<br>
 *  2.创建java绘图工具对象--Graphics2D<br>
 *  3.使用绘图工具对象将原图绘制到缓存图片对象<br>
 *  4.使用绘图工具将水印（文字或图片）绘制到缓存图片对象<br>
 *  5.创建图片编码工具类--JPEGImageEncoder(由于java7以后jdk默认读取不到JPEGImageEncoder的rt.jar使用ImageIO实现编码输出)<br>
 *  6.使用图片编码工具类数据缓存图像到目标图片文件<br>
 * @author chenxiaoke
 * @date 2018年2月1日 上午11:30:12
 */
public class WaterMarkUtils {

    //水印文字
    public  String MARK_TEXT="使用绘图工具对象将原图绘制到缓存图片对象";
    //字体
    public  String FONT_NAME="宋体";
    //字体样式
    public  int FONT_STYLE=Font.TYPE1_FONT;
    //字体大小 单位像素
    public  int FONT_SIZE=20;
    //文字颜色
    public  Color FONT_COLOR=Color.white;
    //文字水印透明度 30%
    public  float ALPHA=0.9F;

    //绘制位置横坐标
    public  int X=10;
    //绘制位置纵坐标
    public  int Y=15;
    public  String  BASE_PATH="/Users/chenxiaoke/Documents/tmp/";
    public  String  MARK_LOGO_IMAGE_01="/Users/chenxiaoke/Documents/tmp/logo1.png";
    public  String  MARK_LOGO_IMAGE_02="/Users/chenxiaoke/Documents/tmp/logo2.png";
    public  String  MARK_LOGO_IMAGE_04="/Users/chenxiaoke/Documents/tmp/logo4.png";
    public  String  FORMAT_NAME="jpg";
    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 文字水印
     * @param image
     * @param imageFileName
     * @return
     */
    public String waterMarkBySingleText(File image,String imageFileName){

        String logoFileName="2_logo_single_text_"+imageFileName;
        OutputStream outs=null;
        try {
            //通过ImageIO获取图像文件的像素大小 即宽/高
            Image imageTemp=ImageIO.read(image);
            int width=imageTemp.getWidth(null);
            int height=imageTemp.getHeight(null);
            //1.创建缓存图片对象--BufferedImage
            /**
             * new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
             * width width of the created image
             * height height of the created image
             * imageType type of the created image
             */
            BufferedImage bufferedImage=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            //2.创建java绘图工具对象--Graphics2D
            Graphics2D graphics2D=bufferedImage.createGraphics();
            //3.使用绘图工具对象将原图绘制到缓存图片对象
            /**
             * img the specified image to be drawn. This method does nothing if img is null.
             * x the x coordinate.
             * y the y coordinate.
             * width the width of the rectangle.
             * height the height of the rectangle.
             * observer object to be notified as more of the image is converted.
             */
            graphics2D.drawImage(imageTemp, 0, 0, width,height,null);
            //设置水印文字 字体 样式 大小
            graphics2D.setFont(new Font(FONT_NAME, FONT_STYLE, FONT_SIZE));
            ///设置水印文字颜色
            graphics2D.setColor(FONT_COLOR);


            /**
             * 获取水印文本的高和宽度
             * 高度：即文字的像素大小 -->FONT_SIZE
             * 宽度：中文字符 1:1 即中文字符-->FONT_SIZE 英文字符1:2-->FONT_SIZE/2
             */
            int waterMarkWidth=FONT_SIZE*getTextLength(MARK_TEXT);
            int waterMarkHeight=FONT_SIZE;
            //计算图片尺寸和水印尺寸差异
            int widthDiff=width-waterMarkWidth;
            int heightDiff=height-waterMarkHeight;
            int x=X;
            int y=Y;
            if(x>widthDiff){
                x=widthDiff;
            }
            if(y>heightDiff){
                y=heightDiff;
            }
            //设置水印透明度 0.3
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, ALPHA));
            //4.使用绘图工具将水印（文字或图片）绘制到缓存图片对象
            graphics2D.drawString(MARK_TEXT, x, y+FONT_SIZE);
            //释放工具
            graphics2D.dispose();
            outs=new FileOutputStream(BASE_PATH+logoFileName);

            //5.创建图片编码工具类--JPEGImageEncoder
            ImageIO.write(bufferedImage, FORMAT_NAME, outs);

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(outs!=null){
                try {
                    outs.close();
                } catch (Exception e) {
                    logger.error("{}",e);
                }
            }
        }
        return logoFileName;
    }

    /**
     * 获取文本宽度
     * @param text
     * @return
     */
    public static int getTextLength(String text){
        if(text==null || "".equals(text)){
            return 0;
        }
        int length=text.length();
        return length;
    }


    public String waterMarkBySingleImage(File image){
        String logoFileName= UUID.randomUUID().toString().replaceAll("-","")+".jpg";
        OutputStream outs=null;
        try {
            //通过ImageIO获取图像文件的像素大小 即宽/高
            Image imageTemp=ImageIO.read(image);
            int width=imageTemp.getWidth(null);
            int height=imageTemp.getHeight(null);
            //1.创建缓存图片对象--BufferedImage
            /**
             * new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
             * width width of the created image
             * height height of the created image
             * imageType type of the created image
             */
            BufferedImage bufferedImage=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            //2.创建java绘图工具对象--Graphics2D
            Graphics2D graphics2D=bufferedImage.createGraphics();
            //3.使用绘图工具对象将原图绘制到缓存图片对象
            /**
             * img the specified image to be drawn. This method does nothing if img is null.
             * x the x coordinate.
             * y the y coordinate.
             * width the width of the rectangle.
             * height the height of the rectangle.
             * observer object to be notified as more of the image is converted.
             */
            graphics2D.drawImage(imageTemp, 0, 0, width,height,null);
            /********************************************/
            if(MARK_LOGO_IMAGE_01!=null) {
                File logo1 = new File(MARK_LOGO_IMAGE_01);
                if(logo1.exists()) {
                    Image logoImage1 = ImageIO.read(logo1);
                    /**
                     * 获取水印图片的高和宽度
                     */
                    int waterMarkWidth1 = logoImage1.getWidth(null);
                    int waterMarkHeight1 = logoImage1.getHeight(null);

                    //计算图片尺寸和水印尺寸差异
                    int widthDiff1 = width - waterMarkWidth1;
                    int heightDiff1 = height - waterMarkHeight1;
                    int x1 = X;
                    int y1 = Y;
                    if (x1 > widthDiff1) {
                        x1 = widthDiff1;
                    }
                    if (y1 > heightDiff1) {
                        y1 = heightDiff1;
                    }
                    //设置水印透明度 0.3
                    graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, ALPHA));
                    //4.使用绘图工具将水印（文字或图片）绘制到缓存图片对象
                    graphics2D.drawImage(logoImage1, x1, y1, null);
                }
            }
                /**************************************************/
            if(MARK_LOGO_IMAGE_02!=null){
                //添加第二个水印
                File logo2 = new File(MARK_LOGO_IMAGE_02);
                if(logo2.exists()) {
                    Image logoImage2 = ImageIO.read(logo2);
                    /**
                     * 获取水印图片的高和宽度
                     */
                    int waterMarkWidth2 = logoImage2.getWidth(null);
                    int waterMarkHeight2 = logoImage2.getHeight(null);

                    //计算图片尺寸和水印尺寸差异
                    int widthDiff2 = width - waterMarkWidth2;
                    int heightDiff2 = height - waterMarkHeight2;
                    int x2 = 0;
                    int y2 = 0;
                    if (widthDiff2 > 0 && heightDiff2 > 0) {
                        x2 = widthDiff2 - 10;
                        y2 = heightDiff2 - 10;
                        //设置水印透明度 0.3
                        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, ALPHA));
                        //4.使用绘图工具将水印（文字或图片）绘制到缓存图片对象
                        graphics2D.drawImage(logoImage2, x2, y2, null);
                    }
                }
            }

            /**************************************************/
            if(MARK_TEXT!=null) {

                //添加水印3

                //设置水印文字 字体 样式 大小
                graphics2D.setFont(new Font(FONT_NAME, FONT_STYLE, FONT_SIZE));
                ///设置水印文字颜色
                graphics2D.setColor(FONT_COLOR);
                graphics2D.setBackground(Color.black);


                /**
                 * 获取水印文本的高和宽度
                 * 高度：即文字的像素大小 -->FONT_SIZE
                 * 宽度：中文字符 1:1 即中文字符-->FONT_SIZE 英文字符1:2-->FONT_SIZE/2
                 */
                int waterMarkWidth3 = FONT_SIZE * getTextLength(MARK_TEXT);
                int waterMarkHeight3 = FONT_SIZE;
                //计算图片尺寸和水印尺寸差异
                int widthDiff3 = width - waterMarkWidth3;
                int heightDiff3 = height - waterMarkHeight3;
                int x3 = X;
                int y3 = Y;
                if (widthDiff3 > 0 && heightDiff3 > 0) {
                    x3 = widthDiff3 - 10;
                    //设置水印透明度 0.3
                    graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, ALPHA));

                    //4.使用绘图工具将水印（文字或图片）绘制到缓存图片对象

                    graphics2D.setColor(Color.DARK_GRAY);
                    graphics2D.fillRoundRect(x3, Y, (waterMarkWidth3), 28, 10, 10);
                    graphics2D.setColor(Color.white);
                    graphics2D.drawString(MARK_TEXT, x3, y3 + FONT_SIZE);
                }
            }

            /********************************************************/
            if(MARK_LOGO_IMAGE_04!=null&&MARK_TEXT!=null) {
                int waterMarkWidth3 = FONT_SIZE * getTextLength(MARK_TEXT);
                //添加第四个个水印
                File logo4 = new File(MARK_LOGO_IMAGE_04);
                if(logo4.exists()){
                    Image logoImage4 = ImageIO.read(logo4);
                    /**
                     * 获取水印图片的高和宽度
                     */
                    int waterMarkWidth4 = logoImage4.getWidth(null);
                    int waterMarkHeight4 = logoImage4.getHeight(null);

                    //计算图片尺寸和水印尺寸差异
                    int widthDiff4 = width - waterMarkWidth4;
                    int heightDiff4 = height - waterMarkHeight4;
                    int x4 = 0;
                    int y4 = Y;
                    if (widthDiff4 > 0 && heightDiff4 > 0) {
                        x4 = widthDiff4 - 10 - waterMarkWidth3;
                        //4.使用绘图工具将水印（文字或图片）绘制到缓存图片对象
                        graphics2D.drawImage(logoImage4, x4, y4, null);
                    }
                }
            }
            /**************************************************/

            //释放工具
            graphics2D.dispose();
            outs=new FileOutputStream(BASE_PATH+logoFileName);
            //5.创建图片编码工具类--JPEGImageEncoder
            ImageIO.write(bufferedImage, FORMAT_NAME, outs);
        } catch (Exception e) {
            logger.error("{}",e);
        }finally{
            if(outs!=null){
                try {
                    outs.close();
                } catch (Exception e) {
                    logger.error("{}",e);
                }
            }
        }
        return logoFileName;
    }

    public static void main(String args[]){
        WaterMarkUtils o=new WaterMarkUtils();

        File orginal=new File("/Users/chenxiaoke/Documents/tmp/1.png");
        o.waterMarkBySingleImage(orginal);
        //o.waterMarkBySingleText(orginal,"2.jpg");

    }

}
