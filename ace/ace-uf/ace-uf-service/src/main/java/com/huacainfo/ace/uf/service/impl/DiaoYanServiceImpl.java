package com.huacainfo.ace.uf.service.impl;


import java.util.Date;
import java.util.List;
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
import com.huacainfo.ace.uf.dao.DiaoYanDao;
import com.huacainfo.ace.uf.model.DiaoYan;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.uf.service.DiaoYanService;
import com.huacainfo.ace.uf.vo.DiaoYanVo;
import com.huacainfo.ace.uf.vo.DiaoYanQVo;
@Service("diaoYanService")
public class DiaoYanServiceImpl implements DiaoYanService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DiaoYanDao diaoYanDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	@Override
	public PageResult<DiaoYanVo> findDiaoYanList(DiaoYanQVo condition, int start,
			int limit, String orderBy) throws Exception {
		PageResult<DiaoYanVo> rst = new PageResult<DiaoYanVo>();
		List<DiaoYanVo> list = this.diaoYanDao.findList(condition,
				start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.diaoYanDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}


	@Override
	public MessageResponse insertDiaoYan(DiaoYan o, UserProp userProp)
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
		if (CommonUtils.isBlank(o.getContent())) {
			return new MessageResponse(1, "调研内容不能为空！");
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
		this.diaoYanDao.insert(o);
		this.dataBaseLogService.log("添加推文", "推文", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "添加推文完成！");
	}


	@Override
	public MessageResponse updateDiaoYan(DiaoYan o, UserProp userProp)
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
		if (CommonUtils.isBlank(o.getContent())) {
			return new MessageResponse(1, "调研内容不能为空！");
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
		this.diaoYanDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更推文", "推文", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "变更推文完成！");
	}

	@Override
	public SingleResult<DiaoYanVo> selectDiaoYanByPrimaryKey(String id) throws Exception {
		SingleResult<DiaoYanVo> rst = new SingleResult<DiaoYanVo>();
		rst.setValue(this.diaoYanDao.selectByPrimaryKey(id));
		return rst;
	}


	@Override
	public MessageResponse deleteDiaoYanByDiaoYanId(String id,
			UserProp userProp) throws Exception {
		this.diaoYanDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除推文", "推文", String.valueOf(id),
				String.valueOf(id), "推文", userProp);
		return new MessageResponse(0, "推文删除完成！");
	}
}
