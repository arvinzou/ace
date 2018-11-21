package com.huacainfo.ace.generator.cdrb;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.icepdf.core.util.GraphicsRenderingHints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chenxiaoke on 2017/9/29.
 */
public class HtmlGenerateService {
	private static final Logger logger = LoggerFactory.getLogger(HtmlGenerateService.class);
	public String generateText(String fileVMPath, Map<String, Object> data) throws Exception {
		VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.setProperty("input.encoding", "UTF-8");
		velocityEngine.setProperty("output.encoding", "UTF-8");
		velocityEngine.setProperty("resource.loader", "class");
		velocityEngine.setProperty("class.resource.loader.class",
				"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		velocityEngine.init();
		Template template = velocityEngine.getTemplate(fileVMPath);
		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("o", data);
		StringWriter stringWriter = new StringWriter();
		template.merge(velocityContext, stringWriter);
		return stringWriter.toString();
	}
	public String getPage(int i) {
		return "A" + i;
	}
	public void createFilePath(File file) {
		if (!file.exists()) {
			logger.info("创建目录{}：", file.getAbsolutePath());
		}
	}
	public void writeFile(String path, String fileName, String text) throws Exception {
		File filePath = new File(path);
		createFilePath(filePath);
		File file = new File(filePath + File.separator + fileName);
		FileWriter fw = new FileWriter(file);
		fw.write(text);
		fw.flush();
		fw.close();
		logger.info("writing {}  {} bytes", file.getAbsolutePath(), text.length());
	}


	public static void download(String urlString, String filename, String savePath) throws Exception {
		logger.error(" geting {}", urlString);
		// 构造URL
		URL url = new URL(urlString);
		// 打开连接
		URLConnection con = url.openConnection();
		// 设置请求超时为5s
		con.setConnectTimeout(30 * 1000);
		// 输入流
		InputStream is = con.getInputStream();
		// 1K的数据缓冲
		byte[] bs = new byte[1024];
		// 读取到的数据长度
		int len;
		// 输出的文件流
		File sf = new File(savePath);
		if (!sf.exists()) {
			sf.mkdirs();
		}

		OutputStream os = new FileOutputStream(sf.getPath() + File.separator + filename);
		// 开始读取
		long size=0;
		while ((len = is.read(bs)) != -1) {
			size=size+len;
			os.write(bs, 0, len);
		}
		logger.error("saving {} {} bytes", sf.getPath() + File.separator + filename,size);
		// 完毕，关闭所有链接
		os.close();
		is.close();
	}
	public void pdf2Pic(String pdfPath) throws Exception {
		org.icepdf.core.pobjects.Document document = new org.icepdf.core.pobjects.Document();
		document.setFile(pdfPath);
		float scale = 1f;// 缩放比例
		float rotation = 0f;// 旋转角度

		for (int i = 0; i < document.getNumberOfPages(); i++) {
			BufferedImage image = (BufferedImage) document.getPageImage(i, GraphicsRenderingHints.SCREEN,
					org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, scale);
			RenderedImage rendImage = image;
			try {
				String imgName = pdfPath.replace("pdf", "png");
				File file = new File(imgName);
				ImageIO.write(rendImage, "png", file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			image.flush();
		}
		document.dispose();
	}
	public void downPdf(String dowUrl, String path, String page) {
		try {
			download(dowUrl, page + ".pdf", path);
		} catch (Exception e) {
			logger.error("{}", e);
		}
	}
}
