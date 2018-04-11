package com.huacainfo.ace.woc.service.impl;


import java.util.Date;
import java.util.List;
import com.huacainfo.ace.common.tools.GUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.jxb.dao.MemberDao;
import com.huacainfo.ace.jxb.model.Member;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.jxb.service.MemberService;
import com.huacainfo.ace.jxb.vo.MemberVo;
import com.huacainfo.ace.jxb.vo.MemberQVo;
@Service("memberService")
/**
 * @author: lcan
 * @version: 2018-04-08
 * @Description:  TODO(会员)
 */
public class MemberServiceImpl implements MemberService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(会员分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<MemberVo>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public PageResult<MemberVo> findMemberList(MemberQVo condition, int start,
			int limit, String orderBy) throws Exception {
		PageResult<MemberVo> rst = new PageResult<MemberVo>();
		List<MemberVo> list = this.memberDao.findList(condition,
				start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.memberDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}
    /**
	 *
	    * @Title:insertMember
	    * @Description:  TODO(添加会员)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public MessageResponse insertMember(Member o, UserProp userProp)
			throws Exception {
		o.setId(GUIDUtil.getGUID());
		//o.setId(String.valueOf(new Date().getTime()));
		if (CommonUtils.isBlank(o.getId())) {
return new MessageResponse(1, "主键不能为空！");
}
if (CommonUtils.isBlank(o.getLevel())) {
return new MessageResponse(1, "会员级别不能为空！");
}
if (CommonUtils.isBlank(o.getStatus())) {
return new MessageResponse(1, "会员状态不能为空！");
}
		int temp = this.memberDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "会员名称重复！");
		}
		o.setCreateDate(new Date());
		o.setStatus("1");
		//o.setCreateUserName(userProp.getName());
		//.setCreateUserId(userProp.getUserId());
		this.memberDao.insert(o);
		this.dataBaseLogService.log("添加会员", "会员", "", o.getId(),
				o.getId(), userProp);
		return new MessageResponse(0, "添加会员完成！");
	}
    /**
	 *
	    * @Title:updateMember
	    * @Description:  TODO(更新会员)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public MessageResponse updateMember(Member o, UserProp userProp)
			throws Exception {
		if (CommonUtils.isBlank(o.getId())) {
return new MessageResponse(1, "主键不能为空！");
}
if (CommonUtils.isBlank(o.getLevel())) {
return new MessageResponse(1, "会员级别不能为空！");
}
if (CommonUtils.isBlank(o.getStatus())) {
return new MessageResponse(1, "会员状态不能为空！");
}
		
		o.setCreateDate(new Date());
		//o.setLastModifyUserName(userProp.getName());
		//o.setLastModifyUserId(userProp.getUserId());
		this.memberDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更会员", "会员", "", o.getId(),
				o.getId(), userProp);
		return new MessageResponse(0, "变更会员完成！");
	}

    /**
	 *
	    * @Title:selectMemberByPrimaryKey
	    * @Description:  TODO(获取会员)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Member>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public SingleResult<MemberVo> selectMemberByPrimaryKey(String id) throws Exception {
		SingleResult<MemberVo> rst = new SingleResult<MemberVo>();
		rst.setValue(this.memberDao.selectByPrimaryKey(id));
		return rst;
	}
    /**
	 *
	    * @Title:deleteMemberByMemberId
	    * @Description:  TODO(删除会员)
	 		* @param:        @param id
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public MessageResponse deleteMemberByMemberId(String id,
			UserProp userProp) throws Exception {
		this.memberDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除会员", "会员", String.valueOf(id),
				String.valueOf(id), "会员", userProp);
		return new MessageResponse(0, "会员删除完成！");
	}
}
