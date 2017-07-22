package com.huacainfo.ace.portal.service.impl;

import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.model.WxUser;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.Encryptor;
import com.huacainfo.ace.common.tools.HttpUtils;
import com.huacainfo.ace.portal.service.AuthorityService;
import com.huacainfo.ace.portal.dao.WxUserDao;

/**
 * Created by chenxiaoke on 2017/5/18.
 */

@Service("authorityService")
public class AuthorityServiceImpl implements AuthorityService {
	private static final Logger logger = LoggerFactory.getLogger(AuthorityServiceImpl.class);

	@Autowired
	private RedisOperations<String, Object> redisTemplate;
	@Autowired
	private WxUserDao wxUserDao;

	public SingleResult<Map<String, String>> authority(String appid, String appsecret, String code,
			String encryptedData, String iv,String latitude,String longitude) throws Exception {
		// appid wxa09a5be5fd228680
		// appsecret d520d29f8c26c7e3885d80b1812a8d91
		SingleResult<Map<String, String>> rst = new SingleResult<Map<String, String>>(0, "OK");
		StringBuffer url = new StringBuffer("");
		url.append("https://api.weixin.qq.com");
		url.append("/sns/jscode2session?");
		url.append("appid=");
		url.append(appid);
		url.append("&secret=");
		url.append(appsecret);
		url.append("&js_code=");
		url.append(code);
		url.append("&grant_type=authorization_code");
		//logger.info("url -> {}", url.toString());
		String res = HttpUtils.httpGet(url.toString());
		logger.info("res -> {}", res);
		JSONObject json = JSON.parseObject(res);
		if (CommonUtils.isNotBlank(json.getString("errcode"))) {
			return new SingleResult<Map<String, String>>(1, json.getString("errmsg"));
		}
		String session_key = json.getString("session_key");
		String openid = json.getString("openid");
		String expires_in = json.getString("expires_in");
        JSONObject userinfo = this.getUserInfo(encryptedData,session_key,iv);
		logger.info("session_key -> {} openid -> {} expires_in -> {} userinfo ->{}", session_key, openid, expires_in,
				userinfo);
        String _3rd_session =userinfo.getString("unionId");
        if(CommonUtils.isBlank(_3rd_session)){
			_3rd_session=userinfo.getString("openId");
			userinfo.put("unionId",_3rd_session);
		}
        Map<String, String> o = new HashMap<String, String>();
        o.put("session_key", session_key);
        o.put("openid", openid);
        o.put("expires_in", expires_in);
        o.put("3rd_session", _3rd_session);
        redisTemplate.opsForValue().set(_3rd_session + "openid", openid);
        redisTemplate.opsForValue().set(_3rd_session + "session_key", session_key);
		redisTemplate.opsForValue().set(_3rd_session, userinfo);
		WxUser wxUser= JSON.parseObject(userinfo.toString(),WxUser.class);
		if(CommonUtils.isNotEmpty(latitude)){
			wxUser.setLatitude(new java.math.BigDecimal(latitude));
		}
		if(CommonUtils.isNotEmpty(longitude)){
			wxUser.setLongitude(new java.math.BigDecimal(longitude));
		}
		this.saveOrUpdateWxUser(wxUser);
		rst.setValue(o);
		return rst;
	}
	private void saveOrUpdateWxUser(WxUser o){
		int t=this.wxUserDao.isExit(o);
		if(t>0){
			wxUserDao.updateByPrimaryKey(o);
		}else{
			wxUserDao.insert(o);
		}
	}
	public JSONObject getUserInfo(String encryptedData, String sessionKey, String iv) throws Exception {
        logger.info("encryptedData: {} sessionKey: {} iv:{}",encryptedData,sessionKey,iv);
		// 被加密的数据
		byte[] dataByte = Base64.decodeBase64(encryptedData);
		// 加密秘钥
		byte[] keyByte = Base64.decodeBase64(sessionKey);
		// 偏移量
		byte[] ivByte = Base64.decodeBase64(iv);
		// 如果密钥不足16位，那么就补足. 这个if 中的内容很重要
		int base = 16;
		if (keyByte.length % base != 0) {
			int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
			byte[] temp = new byte[groups * base];
			Arrays.fill(temp, (byte) 0);
			System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
			keyByte = temp;
		}
		// 初始化
		Security.addProvider(new BouncyCastleProvider());
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
		SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
		AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
		parameters.init(new IvParameterSpec(ivByte));
		cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
		byte[] resultByte = cipher.doFinal(dataByte);
		if (null != resultByte && resultByte.length > 0) {
			String result = new String(resultByte, "UTF-8");
			return JSON.parseObject(result);
		}

		return null;
	}

	public  MessageResponse reg(com.huacainfo.ace.common.model.WxUser wxUser) throws Exception{
		int t=this.wxUserDao.updateReg(wxUser);
		if(CommonUtils.isBlank(wxUser.getMobile())){
			return new MessageResponse(1,"手机号不能为空。");
		}
		if(CommonUtils.isBlank(wxUser.getName())){
			return new MessageResponse(1,"姓名不能为空。");
		}
		return new MessageResponse(0,"成功。");
	}

	public static void main(String args[]) throws Exception {
		String  encryptedData="SYAdGMhP/fhE3Y34qegb8JhhXzF1T6wI4Ot/FMSS5Q89YFR2I4kxo/VYreX5sseluKHuCIT/V6LFV+HiOpwMB7pIIqxKEQNvcePIjvlgQ8hCsU5eiFKJZUGGOiXSLqlAvQw5X4HUHXrKni3584NgeAfsKTPBgHPcGQNNzOslUNxceGiNCrf2lk/gJmUwptxqh71+xtRQnmSl0fh8rlS0TtrGFkAL6chbp7j0IKtu/UiQY0BhzWYsoRYxzFFVKmjdEDmq2Y1J9UhDEtkzNdQi4aVXrmiJLzaLiPUlIM1NpB5jsyWzGLj8v5c5Fbt3kvO/yRGyTrJDgdUfK6+qBooJuG6HStV/lg+mExqjLhC9VMqqgFox+XerpZY+tmlfLGDJ+t9yVDyoFyK4hQv50JnwupCLi+4yN6bARyWVJ5TZvmeQU6g/SGhyIzhlTbyXEHRuUK6979ok5wxkWaSiqHo2eAzTYV6jKCh59b6SUmbILzc=";
		String sessionKey="cmpPe9ZcdB7PxNC9p/OoMQ==";
        String iv="pzHZnbzIMfdfgI87K3DtIw==";
        AuthorityServiceImpl o=new AuthorityServiceImpl();
        JSONObject userinfo= o.getUserInfo(encryptedData,sessionKey,iv);


		System.out.println(userinfo.toString());
	}
}
