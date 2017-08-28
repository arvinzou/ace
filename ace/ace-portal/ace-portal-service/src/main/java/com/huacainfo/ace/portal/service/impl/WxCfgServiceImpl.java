package com.huacainfo.ace.portal.service.impl;


import java.util.Date;
import java.util.List;
import java.util.Map;

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
}
