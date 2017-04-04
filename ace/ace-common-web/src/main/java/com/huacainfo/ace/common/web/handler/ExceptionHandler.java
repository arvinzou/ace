package com.huacainfo.ace.common.web.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.tools.CommonUtils;

public class ExceptionHandler implements HandlerExceptionResolver {
	private Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exception) {
		logger.error("内部处理出错", exception);
		dealException(request, response, handler, exception);
		return null;
	}

	private void dealException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exception) {
		String exceptionStack = CommonUtils.getExceptionStack(exception);
		MessageResponse messageResponse = new MessageResponse(1, "内部错误",
				exceptionStack);
		String rString = JSONObject.toJSONString(messageResponse);
		outJsonString(response, rString);
	}

	protected void outJsonString(HttpServletResponse response, String str) {
		response.setContentType("application/json;charset=UTF-8");
		outString(response, str);
	}

	protected void outString(HttpServletResponse response, String str) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(str);
		} catch (IOException e) {
			logger.error("输出错误信息出错", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}
}
