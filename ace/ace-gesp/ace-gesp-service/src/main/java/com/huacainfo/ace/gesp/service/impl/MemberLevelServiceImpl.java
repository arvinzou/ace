package com.huacainfo.ace.gesp.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.huacainfo.ace.gesp.dao.MemberInfoDao;
import com.huacainfo.ace.gesp.dao.MemberLevelDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.gesp.model.MemberLevel;
import com.huacainfo.ace.gesp.service.MemberLevelService;
import com.huacainfo.ace.gesp.vo.MemberLevelQVo;
import com.huacainfo.ace.gesp.vo.MemberLevelVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;

@Service("memberLevelService")
public class MemberLevelServiceImpl implements MemberLevelService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MemberLevelDao memberLevelDao;
	@Autowired
	private MemberInfoDao memberInfoDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	public PageResult<MemberLevelVo> findMemberLevelList(
			MemberLevelQVo condition, int start, int limit, String orderBy)
			throws Exception {
		PageResult<MemberLevelVo> rst = new PageResult<MemberLevelVo>();
		List<MemberLevelVo> list = this.memberLevelDao.findList(condition,
				start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.memberLevelDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public MessageResponse insertMemberLevel(MemberLevel o, UserProp userProp)
			throws Exception {
		o.setId(UUID.randomUUID().toString());
		o.setMemberCode(userProp.getCorpId());
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "不能为空！");
		}
		if (CommonUtils.isBlank(o.getMemberCode())) {
			return new MessageResponse(1, "会员编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getName())) {
			return new MessageResponse(1, "会员级别名称不能为空！");
		}
		int temp = this.memberLevelDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "会员级别名称重复！");
		}
		o.setStatus("1");
		o.setCreateDate(new Date());
		o.setCreateUserName(userProp.getCorpName());
		o.setCreateUserId(userProp.getUserId());
		this.memberLevelDao.insert(o);
		this.dataBaseLogService.log("添加会员级别", "会员级别", "", "会员级别名称:"+o.getName(), o.getName(),
				userProp);
		return new MessageResponse(0, "添加会员级别完成！");
	}

	public MessageResponse updateMemberLevel(MemberLevel o, UserProp userProp)
			throws Exception {
		o.setMemberCode(userProp.getCorpId());
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "不能为空！");
		}
		if (CommonUtils.isBlank(o.getMemberCode())) {
			return new MessageResponse(1, "会员编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getName())) {
			return new MessageResponse(1, "会员级别名称不能为空！");
		}
		o.setStatus("1");
		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		this.memberLevelDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更会员级别", "会员级别", "", "会员级别名称"+o.getName()+",编号"+o.getMemberCode(), o.getName(),
				userProp);
		return new MessageResponse(0, "变更会员级别完成！");
	}

	public SingleResult<MemberLevel> selectMemberLevelByPrimaryKey(String id)
			throws Exception {
		SingleResult<MemberLevel> rst = new SingleResult<MemberLevel>();
		rst.setValue(this.memberLevelDao.selectByPrimaryKey(id));
		return rst;
	}

	public MessageResponse deleteMemberLevelByMemberLevelId(String id,
			UserProp userProp) throws Exception {
		int me = this.memberInfoDao.isExitMemberLevelId(id,userProp.getCorpId());
		if(me>0){
			return new MessageResponse(0, "会员级别已引用,不可删除！");
		}
		MemberLevel ml = this.memberLevelDao.selectByPrimaryKey(id);
		this.memberLevelDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除会员级别", "会员级别", "会员级别名称:"+ml.getName()+",编号："+ml.getMemberCode(),
				"", ml.getName(), userProp);
		return new MessageResponse(0, "会员级别删除完成！");
	}
	
	/**
	 * 根据协会编号查询会员级别
	 * 
	 * 引用(会员级别下拉框,收费处理模块)
	 * @param memberCode
	 * @return List<Map<String, Object>>
	 * @throws Exception
	 */
	public ListResult<Map<String,Object>> selectListByDeptId(String deptId,String selected) throws Exception {
		ListResult<Map<String,Object>> rst = new ListResult<Map<String,Object>>();
		rst.setValue(this.memberLevelDao.selectListByDeptId( deptId));
		
		if(CommonUtils.isNotBlank(selected)&&selected.equals("true")){
			for (Map<String,Object> dict : rst.getValue()) {
				dict.put("selected",true);
				dict.put("code",dict.get("code"));
				break;
			}
		}else if(CommonUtils.isNotBlank(selected)&&selected.equals("false")){
			Map<String,Object> e=new HashMap<String,Object>();
			e.put("code","");
			e.put("name","--全部--");
			//e.put("selected",true);
			rst.getValue().add(0, e);
		}
		return rst;
	}

}
