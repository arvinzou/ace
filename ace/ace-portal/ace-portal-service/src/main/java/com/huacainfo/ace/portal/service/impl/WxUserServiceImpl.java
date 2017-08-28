package com.huacainfo.ace.portal.service.impl;


import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.dao.WxUserDao;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.WxUserService;
import com.huacainfo.ace.portal.vo.WxUserVo;
import com.huacainfo.ace.portal.vo.WxUserQVo;
@Service("wxUserService")
public class WxUserServiceImpl implements WxUserService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private WxUserDao wxUserDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	public PageResult<WxUserVo> findWxUserList(WxUserQVo condition, int start,
			int limit, String orderBy) throws Exception {
		PageResult<WxUserVo> rst = new PageResult<WxUserVo>();
		List<WxUserVo> list = this.wxUserDao.findList(condition,
				start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.wxUserDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public MessageResponse insertWxUser(WxUser o, UserProp userProp)
			throws Exception {
		int temp = this.wxUserDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "微信用户名称重复！");
		}
		this.wxUserDao.insert(o);
		this.dataBaseLogService.log("添加微信用户", "微信用户", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "添加微信用户完成！");
	}

	public MessageResponse updateWxUser(WxUser o, UserProp userProp)
			throws Exception {
		this.wxUserDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更微信用户", "微信用户", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "变更微信用户完成！");
	}

	public SingleResult<WxUser> selectWxUserByPrimaryKey(String id) throws Exception {
		SingleResult<WxUser> rst = new SingleResult<WxUser>();
		rst.setValue(this.wxUserDao.selectByPrimaryKey(id));
		return rst;
	}

	public MessageResponse deleteWxUserByWxUserId(String id,
			UserProp userProp) throws Exception {
		this.wxUserDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除微信用户", "微信用户", String.valueOf(id),
				String.valueOf(id), "微信用户", userProp);
		return new MessageResponse(0, "微信用户删除完成！");
	}
	public MessageResponse updateRoleById(String id,String role,
												  UserProp userProp) throws Exception {
		this.wxUserDao.updateRole(id,role);
		this.dataBaseLogService.log("绑定管理员", "微信用户", String.valueOf(id),
				String.valueOf(id), "微信用户", userProp);
		return new MessageResponse(0, "微信用户绑定管理员完成！");
	}
	public MessageResponse deleteRoleById(String id,
										  UserProp userProp) throws Exception {
		this.wxUserDao.updateRole(id,null);
		this.dataBaseLogService.log("解绑管理员", "微信用户", String.valueOf(id),
				String.valueOf(id), "微信用户", userProp);
		return new MessageResponse(0, "微信用户解绑管理员完成！");
	}
	public List<Map<String,Object>> selectWxUser(Map<String,Object> condition)throws Exception{
		return this.wxUserDao.selectWxUser(condition);
	}
}
