package com.huacainfo.ace.portal.web.tools;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.icepdf.core.pobjects.Document;
import org.icepdf.core.util.GraphicsRenderingHints;
/**
 * Created by chenxiaoke on 2017/9/28.
 */
public class Icepdf {
    public static void pdf2Pic(String pdfPath) throws Exception{
        Document document = new Document();
        document.setFile(pdfPath);
        float scale = 1f;//缩放比例
        float rotation = 0f;//旋转角度

        for (int i = 0; i < document.getNumberOfPages(); i++) {
            BufferedImage image = (BufferedImage)document.getPageImage(i, GraphicsRenderingHints.SCREEN, org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, scale);
            RenderedImage rendImage = image;
            try {
                String imgName=pdfPath.replace("pdf","png");
                File file = new File(imgName);
                ImageIO.write(rendImage, "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            image.flush();
        }
        document.dispose();
    }
    public static void main(String[] args) {
        String filePath = "/Users/chenxiaoke/Desktop/CDRB20170928A01.pdf";
        try{
            pdf2Pic(filePath);
        }catch (Exception e){

        }

    }
}
