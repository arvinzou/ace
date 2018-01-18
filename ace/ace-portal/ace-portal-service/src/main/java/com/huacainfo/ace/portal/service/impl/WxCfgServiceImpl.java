package com.huacainfo.ace.portal.service.impl;


import java.util.Date;
import java.util.List;
import java.util.Map;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.InputStream;
import java.util.UUID;
import java.security.MessageDigest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.huacainfo.ace.portal.dao.RoleDao;
import com.huacainfo.ace.portal.model.WxFormid;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.dao.WxCfgDao;
import com.huacainfo.ace.portal.dao.WxFormIdDao;
import com.huacainfo.ace.portal.model.WxCfg;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.WxCfgService;
import com.huacainfo.ace.portal.vo.WxCfgQVo;
import com.huacainfo.ace.portal.vo.WxCfgVo;
@Service("wxCfgService")
public class WxCfgServiceImpl implements WxCfgService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private WxCfgDao wxCfgDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	@Autowired
	private SqlSessionTemplate sqlSession;

	public PageResult<WxCfgVo> findWxCfgList(WxCfgQVo condition, int start,
			int limit, String orderBy) throws Exception {
		PageResult<WxCfgVo> rst = new PageResult<WxCfgVo>();
		List<WxCfgVo> list = this.wxCfgDao.findList(condition,
				start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.wxCfgDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public MessageResponse insertWxCfg(WxCfg o, UserProp userProp)
			throws Exception {
		
		int temp = this.wxCfgDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "微信小程序名称重复！");
		}
		this.wxCfgDao.insert(o);
		this.dataBaseLogService.log("添加微信小程序", "微信小程序", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "添加微信小程序完成！");
	}

	public MessageResponse updateWxCfg(WxCfg o, UserProp userProp)
			throws Exception {
		this.wxCfgDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更微信小程序", "微信小程序", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "变更微信小程序完成！");
	}

	public SingleResult<WxCfgVo> selectWxCfgByPrimaryKey(String id) throws Exception {
		SingleResult<WxCfgVo> rst = new SingleResult<WxCfgVo>();
		rst.setValue(this.wxCfgDao.selectByPrimaryKey(id));
		return rst;
	}

	public MessageResponse deleteWxCfgByWxCfgId(String id,
			UserProp userProp) throws Exception {
		this.wxCfgDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除微信小程序", "微信小程序", String.valueOf(id),
				String.valueOf(id), "微信小程序", userProp);
		return new MessageResponse(0, "微信小程序删除完成！");
	}

	public  void updateAccessToken(String appid,String accessToken,int expiresIn){
		this.wxCfgDao.updateAccessToken(appid,accessToken,expiresIn);

	}

	public  List<Map<String,Object>> selectAppList(){
		return this.wxCfgDao.selectAppList();
	}

	public  MessageResponse insertFormIds(List<WxFormid> list){
		SqlSession session = this.sqlSession.getSqlSessionFactory()
				.openSession(ExecutorType.BATCH, false);

		try {
			WxFormIdDao wxFormIdDao = session.getMapper(WxFormIdDao.class);
			int i=0;
			for (WxFormid o : list) {
				o.setStatus("0");
				o.setCreateDate(new Date());
				wxFormIdDao.insert(o);
				i++;
				if(i%200==0){
					session.commit();
				}
			}
			session.commit();
		} catch (Exception e) {
			session.rollback();
			return new MessageResponse(1, "保存失败！");
		} finally {
			session.clearCache();
			session.close();
		}
		return new MessageResponse(0, "保存完成！");
	}

	@Override
	public SingleResult<String> selectAccessTokenByDeptId(String deptId) throws Exception {
		SingleResult<String> rst = new SingleResult<>();
		rst.setValue(this.wxCfgDao.selectAccessTokenByDeptId(deptId));
		return rst;
	}

	@Override
	public SingleResult<String> getTicket(String access_token) {
		String ticket = null;
		String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi";//这个url链接和参数不能变
		try {
			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
			http.setRequestMethod("GET"); // 必须是get方式请求
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			JSONObject demoJson = JSON.parseObject(message);
			logger.info("JSON字符串:", demoJson);
			ticket = demoJson.getString("ticket");
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		SingleResult<String> rst = new SingleResult<>();
		rst.setValue(ticket);
		return rst;
	}

	@Override
	public SingleResult<String> getSignature(String url, String accessToken) throws Exception {
		SingleResult<String> rst = new SingleResult<>();
		//2、获取Ticket
		String jsapi_ticket = this.getTicket(accessToken).getValue();
		//3、时间戳和随机字符串
		String noncestr = UUID.randomUUID().toString().replace("-", "").substring(0, 16);//随机字符串
		String timestamp = String.valueOf(System.currentTimeMillis() / 1000);//时间戳
		this.logger.info("accessToken:" + accessToken + "\njsapi_ticket:" + jsapi_ticket + "\n时间戳：" + timestamp + "\n随机字符串：" + noncestr);
		//5、将参数排序并拼接字符串
		String str = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + url;
		//6、将字符串进行sha1加密
		String signature = SHA1(str);
		rst.setValue(signature);
		return rst;
	}

	private String SHA1(String decript) throws Exception {
		try {
			MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (Exception e) {
			this.logger.error(e.getMessage());
		}
		return "";
	}
}
