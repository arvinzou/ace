package com.huacainfo.ace.common.tools.canvas;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

/**
 * @Auther: Arvin
 * @Date: 2018/7/5 16:10
 * @Description:
 */
public class ImageKit {
    private ImageKit() {
    }

    public static BufferedImage getImageURL(String imageURL) throws MalformedURLException {
        Image src = Toolkit.getDefaultToolkit().getImage(new URL(imageURL));
        BufferedImage image = toBufferedImage(src);
        return image;
    }

    public static BufferedImage getImagePath(String imagePath) {
        Image src = Toolkit.getDefaultToolkit().getImage(imagePath);
        BufferedImage image = toBufferedImage(src);
        return image;
    }

    public static void compressImage(BufferedImage src, String outImgPath, int maxLength) throws IOException {
        resize(src, outImgPath, maxLength);
    }

    public static void compressImage(InputStream is, String outImgPath, int maxLength) throws IOException {
        BufferedImage src = ImageIO.read(is);
        resize(src, outImgPath, maxLength);
    }

    public static void compressImage(String srcImgPath, String outImgPath, int maxLength) {
        BufferedImage src = getBufferedImage(srcImgPath);
        resize(src, outImgPath, maxLength);
    }

    private static void resize(BufferedImage src, String outImgPath, int maxLength) {
        if (null != src) {
            int old_w = src.getWidth();
            int old_h = src.getHeight();
            int new_w;
            int new_h;
            if (old_w > old_h) {
                new_w = maxLength;
                new_h = Math.round((float) old_h * ((float) maxLength / (float) old_w));
            } else {
                new_w = Math.round((float) old_w * ((float) maxLength / (float) old_h));
                new_h = maxLength;
            }

            disposeImage(src, outImgPath, new_w, new_h);
        }

    }

    public static void compress(String srcImgPath, String outImgPath, int new_w, int new_h) {
        BufferedImage src = getBufferedImage(srcImgPath);
        disposeImage(src, outImgPath, new_w, new_h);
    }

    private static BufferedImage getBufferedImage(String srcImgPath) {
        BufferedImage srcImage = null;

        try {
            FileInputStream in = new FileInputStream(srcImgPath);
            srcImage = ImageIO.read(in);
        } catch (IOException var3) {
            System.out.println("读取图片文件出错！" + var3.getMessage());
        }

        return srcImage;
    }

    public static synchronized void disposeImage(BufferedImage src, String outImgPath, int new_w, int new_h) {
        int old_w = src.getWidth();
        int old_h = src.getHeight();
        BufferedImage newImg = new BufferedImage(new_w, new_h, 1);
        Graphics2D g = newImg.createGraphics();
        g.drawImage(src, 0, 0, old_w, old_h, (ImageObserver) null);
        g.dispose();
        newImg.getGraphics().drawImage(src.getScaledInstance(new_w, new_h, 4), 0, 0, (ImageObserver) null);
        outImage(outImgPath, newImg);
    }

    private static void outImage(String outImgPath, BufferedImage newImg) {
        File file = new File(outImgPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        try {
            ImageIO.write(newImg, outImgPath.substring(outImgPath.lastIndexOf(".") + 1), new File(outImgPath));
        } catch (FileNotFoundException var4) {
            System.out.println("ERROR:" + var4.getMessage());
        } catch (IOException var5) {
            System.out.println("ERROR:" + var5.getMessage());
        }

    }

    public static void draw(BufferedImage baseImage, BufferedImage addImage, int x, int y, int width, int height, String outPath) {
        try {
            Graphics g = baseImage.getGraphics();
            g.drawImage(addImage, x, y, width, height, (ImageObserver) null);
            OutputStream outImage = new FileOutputStream(outPath);
            String format = outPath.substring(outPath.lastIndexOf(".") + 1, outPath.length());
            ImageIO.write(baseImage, format, outImage);
        } catch (IOException var10) {
            System.out.println("Exception:" + var10.getMessage());
        }

    }

    public static void outputImage(BufferedImage image, String imagePath) {
        try {
            OutputStream outImage = new FileOutputStream(imagePath);
            ImageIO.write(image, "jpeg", outImage);
        } catch (IOException var3) {
            System.out.println("Exception:" + var3.getMessage());
        }

    }

    public static void cut(int x, int y, int width, int height, InputStream srcFis, String srcSuffix, String descpath) {
        ImageInputStream iis = null;

        try {
            Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(srcSuffix);
            ImageReader reader = (ImageReader) it.next();
            iis = ImageIO.createImageInputStream(srcFis);
            reader.setInput(iis, true);
            ImageReadParam param = reader.getDefaultReadParam();
            Rectangle rect = new Rectangle(x, y, width, height);
            param.setSourceRegion(rect);
            BufferedImage bi = reader.read(0, param);
            ImageIO.write(bi, srcSuffix, new File(descpath));
        } catch (Exception var25) {
            var25.printStackTrace();
        } finally {
            if (srcFis != null) {
                try {
                    srcFis.close();
                } catch (IOException var24) {
                    var24.printStackTrace();
                }

                srcFis = null;
            }

            if (iis != null) {
                try {
                    iis.close();
                } catch (IOException var23) {
                    var23.printStackTrace();
                }

                iis = null;
            }

        }

    }

    public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        } else {
            image = (new ImageIcon(image)).getImage();
            BufferedImage bimage = null;
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

            byte type;
            try {
                type = 1;
                GraphicsDevice gs = ge.getDefaultScreenDevice();
                GraphicsConfiguration gc = gs.getDefaultConfiguration();
                bimage = gc.createCompatibleImage(image.getWidth((ImageObserver) null), image.getHeight((ImageObserver) null), type);
            } catch (HeadlessException var6) {
                ;
            }

            if (bimage == null) {
                type = 1;
                bimage = new BufferedImage(image.getWidth((ImageObserver) null), image.getHeight((ImageObserver) null), type);
            }

            Graphics g = bimage.createGraphics();
            g.drawImage(image, 0, 0, (ImageObserver) null);
            g.dispose();
            return bimage;
        }
    }

    public static void graphics(Color color, Font font, int x, int y, String text, String baseUrl, String tempPath) {
        String imageSuffix = baseUrl.substring(baseUrl.lastIndexOf("."));
        BufferedImage imageBase = null;

        try {
            imageBase = getImageURL(baseUrl);
            Graphics graphics = imageBase.getGraphics();
            graphics.setColor(color);
            graphics.setFont(font);
            graphics.drawString(text, x, y);
            FileKit.mkDir(tempPath);
            OutputStream outImage = new FileOutputStream(tempPath);
            ImageIO.write(imageBase, "jpeg", outImage);
            outImage.close();
        } catch (Exception var11) {
            var11.printStackTrace();
        }

    }
}
