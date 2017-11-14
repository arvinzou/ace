package com.huacainfo.ace.uf.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

@Controller
@RequestMapping("/www/captcha")
public class CaptchaController extends UfBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());


	@Autowired
	private RedisOperations<String, Object> redisTemplate;

	/**
	 * 
	 * @Title:findConfigList
	 * @Description: TODO(系统参数分页查询)
	 * @param: @param condition
	 * @param: @param page
	 * @param: @return
	 * @param: @throws Exception
	 * @return: PageResult<ConfigVo>
	 * @throws
	 * @author: chenxiaoke
	 * @version: 2016年11月17日 下午1:36:28
	 */
	@RequestMapping(value = "/image.do")
	public void image(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//清除缓存，每次访问该页面时都从服务器端读取
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
		String id=request.getParameter("id");

		// 定义显示图片的宽度和高度
		int width=140, height=44;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		// 画图画板
		Graphics g = image.getGraphics();

		//定义一个随机数
		Random random = new Random();

		// 设置画板背景颜色
		g.setColor(getRandColor(200,250));
		//设置画板的填充范围
		g.fillRect(0, 0, width, height);

		//设置字体
		g.setFont(new Font("Times New Roman",Font.PLAIN,30));

		// 设置线条颜色并画线，155条
		g.setColor(getRandColor(160,200));
		for (int i=0;i<155;i++)
		{
		 int x = random.nextInt(width);
		 int y = random.nextInt(height);
		        int xl = random.nextInt(12);
		        int yl = random.nextInt(12);
		 g.drawLine(x,y,x+xl,y+yl);
		}

		// 显示数字，4位长度
		String sRand="";
		for (int i=0;i<4;i++){
		    String rand=String.valueOf(random.nextInt(10));
		    sRand+=rand;
		    // 设置每个数字的颜色
		    g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
		//在画板上写数字，起始位置
		    g.drawString(rand,25*i+30,32);
		}
		// 保存进session
		this.getRequest().getSession().setAttribute("j_captcha_weui",sRand);
		redisTemplate.opsForValue().set(id+"j_captcha_weui", sRand);
		logger.debug("j_captcha_weui:{}",this.getRequest().getSession().getAttribute("j_captcha_weui"));
		// 显示图片
		g.dispose();
		//转换成一张图片，格式为JPEG
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}

	private Color getRandColor(int fc, int bc) {// 随机获得颜色，RGB格式
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

}
