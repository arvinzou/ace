package com.huacainfo.ace.portal.service.impl;


import java.util.*;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.InputStream;
import java.security.MessageDigest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.huacainfo.ace.common.fastdfs.IFile;
import com.huacainfo.ace.common.web.tools.WebUtils;
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


	@Autowired
	private IFile fileSaver;

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

	public void updateAccessTokenTicket(String appid, String accessToken, String ticket, int expiresIn) {
		this.wxCfgDao.updateAccessTokenTicket(appid, accessToken, expiresIn, ticket);

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
	public SingleResult<Map<String, String>> selectAccessTokenAndTicketByDeptId(String deptId) throws Exception {
		SingleResult<Map<String, String>> rst = new SingleResult<>();
		rst.setValue(this.wxCfgDao.selectAccessTokenAndTicketByDeptId(deptId));
		return rst;
	}


	@Override
	public SingleResult<Map<String, Object>> getSignature(String url, String appId, String accessToken, String jsapi_ticket) throws Exception {
		SingleResult<Map<String, Object>> rst = new SingleResult<>();
		Map<String, Object> o = new HashMap<>();
		//2、获取Ticket
		//3、时间戳和随机字符串
		String noncestr = UUID.randomUUID().toString().replace("-", "").substring(0, 16);//随机字符串
		String timestamp = String.valueOf(System.currentTimeMillis() / 1000);//时间戳
		this.logger.info("accessToken:" + accessToken + "\njsapi_ticket:" + jsapi_ticket + "\n时间戳：" + timestamp + "\n随机字符串：" + noncestr);
		//5、将参数排序并拼接字符串
		String str = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + url;
		//6、将字符串进行sha1加密
		String signature = SHA1(str);
		o.put("appId", appId);
		o.put("signature", signature);
		o.put("nonceStr", noncestr);
		o.put("timestamp", timestamp);
		rst.setValue(o);
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

	@Override
	public SingleResult<Map<String, String>> getRecordFile(String deptId, String serverId) throws Exception {
		SingleResult<Map<String, String>> rst = new SingleResult<>();
		Map<String, String> o = new HashMap<>();


		String accessToken = this.selectAccessTokenAndTicketByDeptId(deptId).getValue().get("accessToken");
		this.logger.info("accessToken->{}", accessToken);
		String url = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=" + accessToken + "&media_id=" + serverId;
		this.logger.info("url->{}", url);
		String saveDir = "/tmp/";
		String id = UUID.randomUUID().toString();
		String sourcePath = saveDir + id + ".amr";
		String targetPath = saveDir + id + ".mp3";
		WebUtils.downloadByApacheCommonIO(url, saveDir, id + ".amr");
		WebUtils.changeToMp3(sourcePath, targetPath);
		java.io.File mp3 = new java.io.File(targetPath);
		String filePath = fileSaver.saveFile(mp3, id + ".mp3");
		o.put("filePath", filePath);
		this.logger.info("===============>{}", filePath);
		rst.setValue(o);
		return rst;
	}
}
