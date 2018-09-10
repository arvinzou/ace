package com.huacainfo.ace.common.web.handler;

import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.kafka.KafkaProducerService;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.tools.CommonKeys;
import com.huacainfo.ace.common.tools.CommonUtils;
import es.moki.ratelimitj.core.limiter.request.RequestLimitRule;
import es.moki.ratelimitj.core.limiter.request.RequestRateLimiter;
import es.moki.ratelimitj.inmemory.request.InMemorySlidingWindowRequestRateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ExceptionHandler implements HandlerExceptionResolver {
	private Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);


	@Autowired
	private KafkaProducerService kafkaProducerService;



	private Set<RequestLimitRule> rules = Collections.singleton(RequestLimitRule.of(30, TimeUnit.MINUTES,2));
	private RequestRateLimiter limiter = new InMemorySlidingWindowRequestRateLimiter(rules);

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

		MessageResponse messageResponse = new MessageResponse(1, "内部错误",	exceptionStack);
		if(exception instanceof CustomException){
			messageResponse = new MessageResponse(1, "内部错误",((CustomException) exception).getMsg());
		}
		boolean reachLimit = limiter.overLimitWhenIncremented("dealException");
		if(!reachLimit){
			Map<String, String> cfg=(Map<String, String>)request.getSession().getServletContext().getAttribute(CommonKeys.cfg);
			if(request.getSession().getAttribute(CommonKeys.cfg)!=null){
				cfg=(Map<String, String>)request.getSession().getAttribute(CommonKeys.cfg);
			}
			logger.info("获取系统参数:{}",cfg);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("service", "cmccBackendService");
			data.put("mobile", cfg.get("admin_mobile"));
			data.put("email", cfg.get("admin_email"));
			data.put("emailTitle",cfg.get("sys_name")+"系统运行异常报告");
			data.put("cmccContent",cfg.get("sys_name")+"发生了异常，详细信息已经发送邮箱"+cfg.get("admin_email")+"，请查收。【"+cfg.get("sys_unit")+"】");
			data.put("emailContent",messageResponse.getDetail());
			logger.info("========================开始发送异常消息===============================");
			this.kafkaProducerService.sendMsg("topic.sys.msg", data);
			logger.info("========================完成发送异常消息===============================");
		}

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
