package com.huacainfo.ace.common.tools.canvas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2018/7/5 16:09
 * @Description:
 */
public class CanvasKit {

    /**
     * 绘图工具
     *
     * @param baseImageUrl 作画地图
     * @param tempPath     完成后，输出目录
     * @param fileName     输出文件名
     * @param drawItemMap  绘图项 -- 配置参数
     * @param dataMap      绘图数据
     * @return true-绘图成功，false-绘图失败
     */
    public static boolean drawImage(String baseImageUrl, String tempPath, String fileName,
                                    Map<String, DrawItem> drawItemMap,
                                    Map<String, Object> dataMap) {
        String imageSuffix = baseImageUrl.substring(baseImageUrl.lastIndexOf(".") + 1);//图片后缀
        try {
            BufferedImage imageBase = ImageKit.getImageURL(baseImageUrl);//底图
            Graphics graphics = imageBase.getGraphics();
            String itemKey;
            DrawItem itemValue;
            Color color;//0,0,0
            Font font;//宋体,1,36
            BufferedImage drawItemImage;
            for (Map.Entry<String, DrawItem> entry : drawItemMap.entrySet()) {
                itemKey = entry.getKey();
                itemValue = entry.getValue();
                //绘制类型 0-图片；1-字体
                switch (itemValue.getDrawType()) {
                    case DrawItem.DRAW_TYPE_IMAGE:
                        drawItemImage = (BufferedImage) dataMap.get(itemKey);//ImageKit.getImageURL(dataMap.get(itemKey));
//                        drawItemImage = convertCircular(drawItemImage);
                        graphics.drawImage(
                                drawItemImage, itemValue.getX(), itemValue.getY(),
                                itemValue.getWidth(), itemValue.getHeight(), null
                        );
                        break;
                    case DrawItem.DRAW_TYPE_TEXT:
                        String[] colors = (itemValue.getColor()).split(",");
                        color = new Color(Integer.valueOf(colors[0]), Integer.valueOf(colors[1]), Integer.valueOf(colors[2]));
                        font = new Font(itemValue.getFontName(), itemValue.getFontStyle(), itemValue.getFontSize());
                        graphics.setColor(color);
                        graphics.setFont(font);
                        graphics.drawString(
                                (String) dataMap.get(itemKey),
                                itemValue.getX(),
                                itemValue.getY()
                        );
                        break;
                    default:
                        break;
                }
            }
            //输出文件到指定目录
            String saveFileName = tempPath + fileName + "." + imageSuffix;
            FileKit.mkDir(saveFileName);
            OutputStream outImage = new FileOutputStream(saveFileName);
            ImageIO.write(imageBase, "jpeg", outImage);
            outImage.close();

            return true;
        } catch (Exception e) {
            System.out.println("CanvasKit.drawImage.error:\n" + e);
            return false;
        }
    }

    /**
     * 传入的图像必须是正方形的 才会 圆形 如果是长方形的比例则会变成椭圆的
     *
     * @param bi1 用户头像地址
     * @return
     * @throws IOException
     */
    public static BufferedImage convertCircular(BufferedImage bi1) throws IOException {

//		BufferedImage bi1 = ImageIO.read(new File(url));

        // 这种是黑色底的
//		BufferedImage bi2 = new BufferedImage(bi1.getWidth(), bi1.getHeight(), BufferedImage.TYPE_INT_RGB);

        // 透明底的图片
        BufferedImage bi2 = new BufferedImage(bi1.getWidth(), bi1.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, bi1.getWidth(), bi1.getHeight());
        Graphics2D g2 = bi2.createGraphics();
        g2.setClip(shape);
        // 使用 setRenderingHint 设置抗锯齿
        g2.drawImage(bi1, 0, 0, null);
        // 设置颜色
        g2.setBackground(Color.green);
        g2.dispose();
        return bi2;
    }

    /**
     * 调用示例
     *
     * @param args
     */
    public static void main(String[] args) {
        String base = "http://zx.huacainfo.com/group1/M00/00/30/i-AA41s94--AA74AAAFYonkxuM0256.png?filename=base.png";
        String itemImage = "http://zx.huacainfo.com/group1/M00/00/30/i-AA41s95yeAQ5O_AAAH8vvAuEI436.png?filename=QQ%E5%9B%BE%E7%89%8720180705173819.png";
        BufferedImage item1Data = null;
        try {
            item1Data = ImageKit.getImageURL(itemImage);
            item1Data = convertCircular(item1Data);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, DrawItem> itemMap = new HashMap<>();
        DrawItem item1 = new DrawItem(150, 150, 100, 100);
        itemMap.put("item1", item1);
        DrawItem item2 = new DrawItem(80, 430, "234,68,54,1", "PingFang-SC-Medium", Font.BOLD, 30);
        itemMap.put("item2", item2);
        DrawItem item3 = new DrawItem(200, 430, "234,68,54,1", "PingFang-SC-Medium", Font.BOLD, 30);
        itemMap.put("item3", item3);

//        DrawItem item4 = new DrawItem(50, 10, "255,0,0,", "宋体", Font.BOLD, 36);
//        itemMap.put("item4", item4);
//        DrawItem item5 = new DrawItem(50, 10, "255,0,0,", "宋体", Font.BOLD, 36);
//        itemMap.put("item5", item5);


        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("item1", item1Data);//图片需放置 BufferedImage 类型对象
        dataMap.put("item2", "12");
        dataMap.put("item3", "2480");

//        dataMap.put("item4", null);
//        dataMap.put("item5", null);


        boolean b = CanvasKit.drawImage(base, "F:\\", "drawDemo", itemMap, dataMap);
        System.out.println(b);
    }
}
