/**
 * DateV.java
 * com.equip.web.utils
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2009-10-6 		lj
 *
 * Copyright (c) 2009, LSJ All Rights Reserved.
 */

package com.huacainfo.ace.common.fastjson;

import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * ClassName:DateJsonValueProcessor JSON 日期转换类 Function: TODO ADD FUNCTION
 * Reason: TODO ADD REASON
 * 
 * @author LSJ
 * @version
 * @since Ver 1.1
 * @Date 2009-10-6 下午06:20:42
 * 
 */
public class FullDateJsonValueProcessor implements JsonValueProcessor {
	org.apache.log4j.Logger logger = null;
	public static String format = "yyyy-MM-dd  HH:mm:ss";

	public FullDateJsonValueProcessor() {
		logger = org.apache.log4j.Logger.getLogger(FullDateJsonValueProcessor.class);
	}

	public Object processArrayValue(Object target, JsonConfig config) {
		return process(target);

	}

	public Object processObjectValue(String key, Object target, JsonConfig config) {

		return process(target);

	}

	private String process(Object target) {
		String rst = "";
		if (target != null) {
			if (target instanceof Date) {
				Date p = (Date) target;
				try {
					java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format);
					rst = formatter.format(p);
				} catch (Exception ex) {
					logger.error("格式化日期失败：" + ex.getMessage() + "\tformatter:" + format);
				}
			}
		}
		return rst;

	}

}
