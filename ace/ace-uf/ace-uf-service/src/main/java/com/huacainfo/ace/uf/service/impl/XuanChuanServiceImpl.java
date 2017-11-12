package com.huacainfo.ace.uf.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.uf.dao.XuanChuanDao;
import com.huacainfo.ace.uf.model.XuanChuan;
import com.huacainfo.ace.uf.service.XuanChuanService;
import com.huacainfo.ace.uf.vo.XuanChuanQVo;
import com.huacainfo.ace.uf.vo.XuanChuanVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("xuanChuanService")
public class XuanChuanServiceImpl implements XuanChuanService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private XuanChuanDao xuanChuanDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	@Override
	public PageResult<XuanChuanVo> findXuanChuanList(XuanChuanQVo condition, int start,
			int limit, String orderBy) throws Exception {
		PageResult<XuanChuanVo> rst = new PageResult<XuanChuanVo>();
		List<XuanChuanVo> list = this.xuanChuanDao.findList(condition,
				start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.xuanChuanDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}


	@Override
	public MessageResponse insertXuanChuan(XuanChuan o, UserProp userProp)
			throws Exception {
		o.setId(UUID.randomUUID().toString());
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "主键不能为空！");
		}
		if (CommonUtils.isBlank(o.getName())) {
			return new MessageResponse(1, "名称不能为空！");
		}
		if (CommonUtils.isBlank(o.getMedia())) {
			return new MessageResponse(1, "媒体不能为空！");
		}
		if (CommonUtils.isBlank(o.getPublished())) {
			return new MessageResponse(1, "发布日期不能为空！");
		}
		if (CommonUtils.isBlank(o.getPhoto())) {
			return new MessageResponse(1, "封面不能为空！");
		}
		String str=o.getUrl();
		if (str.indexOf("http://")==-1&&str.indexOf("https://")==-1){
			if(str.indexOf("www.")!=-1){
				str="http://"+str;
			}
			else{
				return new MessageResponse(1, "Url格式不正确或不能为空！");
			}
		}
		o.setUrl(str);
		o.setCreateDate(new Date());
		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		this.xuanChuanDao.insert(o);
		this.dataBaseLogService.log("添加推文", "推文", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "添加推文完成！");
	}


	@Override
	public MessageResponse updateXuanChuan(XuanChuan o, UserProp userProp)
			throws Exception {
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "主键不能为空！");
		}
		if (CommonUtils.isBlank(o.getName())) {
			return new MessageResponse(1, "名称不能为空！");
		}
		if (CommonUtils.isBlank(o.getMedia())) {
			return new MessageResponse(1, "媒体不能为空！");
		}
		if (CommonUtils.isBlank(o.getPublished())) {
			return new MessageResponse(1, "发布日期不能为空！");
		}
		if (CommonUtils.isBlank(o.getPhoto())) {
			return new MessageResponse(1, "封面不能为空！");
		}
		String str=o.getUrl();
		if (str.indexOf("http://")==-1&&str.indexOf("https://")==-1){
			if(str.indexOf("www.")!=-1){
				str="http://"+str;
			}
			else{
				System.out.println("Url格式不正确或不能为空");
			}
		}
		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		this.xuanChuanDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更推文", "推文", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "变更推文完成！");
	}

	@Override
	public SingleResult<XuanChuanVo> selectXuanChuanByPrimaryKey(String id) throws Exception {
		SingleResult<XuanChuanVo> rst = new SingleResult<XuanChuanVo>();
		rst.setValue(this.xuanChuanDao.selectByPrimaryKey(id));
		return rst;
	}


	@Override
	public MessageResponse deleteXuanChuanByXuanChuanId(String id,
			UserProp userProp) throws Exception {
		this.xuanChuanDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除推文", "推文", String.valueOf(id),
				String.valueOf(id), "推文", userProp);
		return new MessageResponse(0, "推文删除完成！");
	}
}
