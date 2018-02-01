package com.huacainfo.ace.portal.web.listener;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huacainfo.ace.common.redis.AspireRedisTemplate;
import com.huacainfo.ace.common.service.WebContextDictService;
import com.huacainfo.ace.common.service.WebContextParamService;
import com.huacainfo.ace.common.tools.CommonKeys;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DictUtils;
import com.huacainfo.ace.common.tools.SpringUtils;
import com.huacainfo.ace.common.web.tools.WebUtils;
import com.huacainfo.ace.portal.service.CacheService;
import com.huacainfo.ace.portal.service.FilesService;
import com.huacainfo.ace.portal.service.ResourcesService;

import com.huacainfo.ace.portal.service.impl.TokenThread;

public class WebContextParamListener implements ServletContextListener {
	private ServletContext servletContext;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private WebContextParamService webContextParamService;
	private WebContextDictService webContextDictService;
	private CacheService cacheService;
	private ReflushDictFileThread reflushDictFileThread = new ReflushDictFileThread();
	private ResourcesService resourcesService;
	private FilesService filesService;
	private CleanFileThread cleanFileThread=new CleanFileThread();
	@Override
	public void contextInitialized(ServletContextEvent event) { // 上下文初始化时触发
		servletContext = event.getServletContext();
		initWebContextParamService();
		initWebContextDictService();
		initCacheService();
		intResourcesService();
		loadSysConfigMap();
		flushStaticDictList();
		flushRoleResoure();
		reflushDictFileThread.start();
		initFilesService();
		cleanFileThread.start();
		new Thread(new TokenThread()).start();
		
	}

	private void flushRoleResoure() {
		AspireRedisTemplate redisTemplateString = (AspireRedisTemplate) SpringUtils
				.getBean("redisTemplateString");
		List<Map<String, String>> list = resourcesService.loadResourceDefine();
		WebUtils.flushRoleResourceCache(redisTemplateString, list);
	}

	private void intResourcesService() {
		if (resourcesService == null) {
			resourcesService = (ResourcesService) SpringUtils
					.getBean("resourcesService");
		}
	}

	private void initWebContextParamService() {
		if (webContextParamService == null) {
			webContextParamService = (WebContextParamService) SpringUtils
					.getBean("systemService");
		}
	}

	private void initWebContextDictService() {
		if (webContextDictService == null) {
			webContextDictService = (WebContextDictService) SpringUtils
					.getBean("dictService");
		}
	}

	private void loadSysConfigMap() {
		Map<String, String> cfg = webContextParamService.loadConfig("sys");
		servletContext.setAttribute(CommonKeys.cfg, cfg);
	}

	private void initCacheService() {
		if (cacheService == null) {
			cacheService = (CacheService) SpringUtils.getBean("cacheService");
			cacheService.init();
		}
	}
	private void initFilesService(){
		if (filesService == null) {
			filesService = (FilesService) SpringUtils.getBean("filesService");
		}
	}
	
	
	private void flushStaticDictList() {
		String path = servletContext.getRealPath("/");
		String fileName = "content/common/js/dict.js";
		String syid="sys";
		List<Map<String, String>> list=null;
		try {
			list = this.webContextDictService.selectSyidBydc();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(Map<String, String> e:list){
			syid=e.get("syid");
			fileName = "content/common/js/dict_"+syid+".js";
			Map<String, List<Map<String, Object>>> dictMap = webContextDictService
					.flushJavaScriptFile(syid);
			servletContext.setAttribute(CommonKeys.dictAppKey+syid, dictMap);
			String dictJsonListString = DictUtils.toJsonString(dictMap,
					new String[] { "TABLE_NAME" });
			CommonUtils.writeStringToFile(path + fileName,
					preDealDictJSONString(dictJsonListString));
			this.logger.info("create dict file at ",path +fileName);
		}
		try {
			String areafileName = "content/common/js/areaCode.js";
			List<Map<String, String>> e = webContextDictService.selectAreaCode();
			StringBuffer area = new StringBuffer("var area={};\r");
			for (Map<String, String> o : e) {
				area.append("area['");
				area.append(o.get("code"));
				area.append("']=");
				area.append("'");
				area.append(o.get("name"));
				area.append("'");
				area.append(";\r");
			}
			CommonUtils.writeStringToFile(path + areafileName,
					area.toString());
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	private String preDealDictJSONString(String dictJsonListString) {
		dictJsonListString = dictJsonListString.replaceAll(" ", "");
		dictJsonListString = dictJsonListString.replaceAll("\n", "");
		dictJsonListString = dictJsonListString.replaceAll("\t", "");
		dictJsonListString = "var staticDictObject=" + dictJsonListString;
		return dictJsonListString;
	}
	@Override
	public void contextDestroyed(ServletContextEvent event) { // 上下文销毁时触发

	}

	/**
	 * 每10分钟刷新一次配置参数和字典文件
	 * 
	 * @author leishaojie
	 *
	 */
	class ReflushDictFileThread extends Thread {
		@Override
		public void run() {
			try {
				flushStaticDictList();
				loadSysConfigMap();
				flushRoleResoure();
				synchronized (this) {
					this.wait(10 * 60 * 1000);
				}
				logger.info("刷新字典文件和系统参数成功");
			} catch (Exception e) {
				 logger.error("刷新字典文件和系统参数失败", e);
			}

		}
	}
	
	/**
	 * 
	     * 此类描述的是：  删除临时文件  
	     * @author: chenxiaoke
	
	     * @version: 2016年11月26日 下午5:42:33
	 */
	class CleanFileThread extends Thread {
		@Override
		public void run() {
			try {
				filesService.deleteFileTimer();
				synchronized (this) {
					this.wait(10 * 60 * 1000);
				}
				logger.info("timer clean files  start");
			} catch (Exception e) {
				logger.error(e.toString());
			
			}

		}
	}
}
