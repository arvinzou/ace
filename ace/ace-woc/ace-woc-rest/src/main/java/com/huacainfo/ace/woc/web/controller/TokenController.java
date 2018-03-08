package com.huacainfo.ace.woc.web.controller;

import java.util.HashMap;
import java.util.Map;

import com.huacainfo.ace.portal.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping("/v1/token")
public class TokenController extends WocBaseController {
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/getToken")
	public ResponseEntity getToken(@RequestParam String username, @RequestParam String password,@RequestParam String timestamp)
			throws Exception {
		Map<String,Object> rst=new HashMap<>();
		rst.put("access_token","2YotnFZFEjr1zCsicMWpAA");
		rst.put("token_type","example");
		rst.put("expires_in",7200);
		rst.put("refresh_token","tGzv3JOkF0XG5Qx2TlKWIA");
		rst.put("scope","scope");
		return new ResponseEntity<>(rst, HttpStatus.OK);
	}

	@RequestMapping(value="/pathvariable/{access_token}/{scope}",method=RequestMethod.GET)
	public ResponseEntity pathvariable(@PathVariable String access_token,@PathVariable String scope) {
		Map<String,Object> rst=new HashMap<>();
		rst.put("access_token",access_token);
		rst.put("scope",scope);
		return new ResponseEntity<>(rst, HttpStatus.OK);
	}
	@RequestMapping("/requestJson")
	@ResponseBody
	public java.util.List<Users> requestJson(@RequestBody java.util.List<Users> users){
		//@ResponseBody将itemsCustom转成json格式输出
		/*
		<script>
			jQuery(function($) {
			$.ajax({
					type:'post',
					url:'${pageContext.request.contextPath }/v1/token/requestJson',
					contentType:'application/json;charset=utf-8',
					data:'{"name":"王五","createTime":"2018-03-08"}',
					success:function(data){
						console.log(data);
					}
				});
				});
		</script>*/
		return users;
	}
}
