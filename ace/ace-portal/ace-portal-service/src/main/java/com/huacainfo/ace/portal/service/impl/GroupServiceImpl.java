package com.huacainfo.ace.portal.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import org.apache.ibatis.jdbc.SqlRunner;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.portal.dao.GroupMapper;
import com.huacainfo.ace.portal.model.Group;
import com.huacainfo.ace.portal.service.GroupService;
import com.huacainfo.ace.portal.vo.GroupQVo;
import com.huacainfo.ace.portal.vo.GroupVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.common.tools.CommonTreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.model.UserProp;
@Service("groupService")
public class GroupServiceImpl implements GroupService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private GroupMapper groupMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	@Autowired
	SqlSessionFactory sqlSessionFactory;
	public PageResult<GroupVo> findGroupList(GroupQVo condition, int start, int limit, String orderBy)
			throws Exception {
		PageResult<GroupVo> rst = new PageResult<GroupVo>();
		List<GroupVo> list = this.groupMapper.findList(condition, start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.groupMapper.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public MessageResponse insertGroup(Group o, UserProp userProp) throws Exception {
		if (CommonUtils.isBlank(o.getGroupId())) {
			return new MessageResponse(1, "分组编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getGroupName())) {
			return new MessageResponse(1, "组名不能为空！");
		}
		if (CommonUtils.isBlank(o.getSqlText())) {
			// return new MessageResponse(1, "语句不能为空！");
		}
		o.setCreateTime(new Date());
		int temp = this.groupMapper.isExitByName(o.getGroupName());
		if (temp > 0) {
			return new MessageResponse(1, "已存在此组名的数据！");
		}
		this.groupMapper.insert(o);
		this.dataBaseLogService.log("添加分组", "分组", "", o.getGroupName(), o.getGroupName(), userProp);
		return new MessageResponse(0, "添加分组完成！");
	}

	public MessageResponse updateGroup(Group o, UserProp userProp) throws Exception {

		if (CommonUtils.isBlank(o.getGroupId())) {
			return new MessageResponse(1, "分组编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getGroupName())) {
			return new MessageResponse(1, "组名不能为空！");
		}
		if (CommonUtils.isBlank(o.getSqlText())) {
			// return new MessageResponse(1, "语句不能为空！");
		}
		o.setCreateTime(new Date());
		this.groupMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更分组", "分组", "", o.getGroupName(), o.getGroupName(), userProp);
		return new MessageResponse(0, "变更分组完成！");
	}

	public MessageResponse deleteGroupByGroupId(String id, UserProp userProp) throws Exception {
		MessageResponse rst = new MessageResponse();
		this.groupMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除分组", "分组", String.valueOf(id), String.valueOf(id), "分组", userProp);
		return rst;
	}

	public List<Tree> selectGroupDepTreeByPid(String pid) {
		CommonTreeUtils commonTreeUtils = new CommonTreeUtils(this.groupMapper.selectGroupDepTreeByPid(pid));
		return commonTreeUtils.getTreeList(pid);
	}
	public List<Tree> selectGroupGradeTreeByPid(String pid) {
		CommonTreeUtils commonTreeUtils = new CommonTreeUtils(this.groupMapper.selectGroupGradeTreeByPid(pid));
		return commonTreeUtils.getTreeList(pid);
	}
	public List<Tree> selectGroupDiscriblineTreeByPid(String pid) {
		CommonTreeUtils commonTreeUtils = new CommonTreeUtils(this.groupMapper.selectGroupDiscriblineTreeByPid(pid));
		return commonTreeUtils.getTreeList(pid);
	}
	public List<Tree> selectFreeGroupTreeRoot(String syid) {
		CommonTreeUtils commonTreeUtils = new CommonTreeUtils(this.groupMapper.selectFreeGroupTreeRoot(syid));
		return commonTreeUtils.getTreeList("0");
	}
	public List<Tree> selectFreeGroupTree(String syid) {
		List<Map<String,Object>> e=this.groupMapper.selectFreeGroupTreeRoot(syid);
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		list.addAll(e);
		for(Map<String,Object> o:e){
			list.addAll(this.selectFreeGroupChildByPid((String) o.get("ID")));
		}
		CommonTreeUtils commonTreeUtils = new CommonTreeUtils(list);
		return commonTreeUtils.getTreeList("0");
	}

	public List<Map<String,Object>> selectFreeGroupChildByPid(String pid) {
		Connection conn = null;
		SqlRunner sqlRunner = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			conn = sqlSessionFactory.getConfiguration().getEnvironment().getDataSource().getConnection();
			sqlRunner = new SqlRunner(conn);
			Group o = this.groupMapper.selectByPrimaryKey(pid);
			if (CommonUtils.isNotBlank(o.getSqlText())) {
				List<Map<String, Object>> items = sqlRunner.selectAll(o.getSqlText());
				for (Map<String, Object> p : items) {
					Map<String, Object> tmp = new HashMap<String, Object>();
					CommonBeanUtils.copyMap2Bean(tmp, p);
					tmp.put("PID", pid);
					this.logger.info(tmp);
					list.add(tmp);
				}
			} else {
				List<Map<String, Object>> items = this.groupMapper.selectFreeGroupUsersTreeByGorupId(pid);
				for (Map<String, Object> p : items) {
					p.put("PID", pid);
					this.logger.info(p);
					list.add(p);
				}
			}

		} catch (SQLException e) {
			this.logger.info(e);
			return null;
		} finally {
			if (sqlRunner != null) {
				sqlRunner.closeConnection();
			}
		}
		return list;
	}
	public List<Tree> selectFreeGroupTreeByPid(String pid) {
		Connection conn = null;
		SqlRunner sqlRunner = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			conn = sqlSessionFactory.getConfiguration().getEnvironment().getDataSource().getConnection();
			sqlRunner = new SqlRunner(conn);
			Group o = this.groupMapper.selectByPrimaryKey(pid);
			if (CommonUtils.isNotBlank(o.getSqlText())) {
				List<Map<String, Object>> items = sqlRunner.selectAll(o.getSqlText());
				for (Map<String, Object> p : items) {
					Map<String, Object> tmp = new HashMap<String, Object>();
					CommonBeanUtils.copyMap2Bean(tmp, p);
					tmp.put("PID", pid);
					this.logger.info(tmp);
					list.add(tmp);
				}
			} else {
				List<Map<String, Object>> items = this.groupMapper.selectFreeGroupUsersTreeByGorupId(pid);
				for (Map<String, Object> p : items) {
					p.put("PID", pid);
					this.logger.info(p);
					list.add(p);
				}
			}

		} catch (SQLException e) {
			this.logger.info(e);
			return null;
		} finally {
			if (sqlRunner != null) {
				sqlRunner.closeConnection();
			}
		}

		CommonTreeUtils commonTreeUtils = new CommonTreeUtils(list);
		return commonTreeUtils.getTreeList(pid);
	}
	public ListResult selectFreeGroupUsersListByGorupId(String groupId) throws Exception {
		ListResult rst = new ListResult();
		if (CommonUtils.isBlank(groupId)) {
			return new ListResult(1, "组编号不能为空！");
		}
		List<Map<String, Object>> list = this.groupMapper.selectFreeGroupUsersListByGorupId(groupId);
		List<Map<String, Object>> items = this.selectFreeGroupListByPid(groupId);
		for (Map<String, Object> p : items) {
			Map<String, Object> tmp = new HashMap<String, Object>();
			tmp.put("USER_ID", p.get("ID"));
			tmp.put("NAME", p.get("TEXT"));
			this.logger.info(tmp);
			list.add(tmp);
		}
		rst.setValue(list);
		return rst;
	}
	public MessageResponse batchSaveGroupUsersByUserIds(Map<String, Object> json, UserProp userProp) throws Exception {
		String groupId = (String) json.get("groupId");
		if (CommonUtils.isBlank(groupId)) {
			return new MessageResponse(1, "自由组编号不能为空！");
		}
		Group o = this.groupMapper.selectByPrimaryKey(groupId);
		if (CommonUtils.isBlank(o.getSqlText())) {
			List<String> list = new ArrayList<String>();
			List<String> jsons = (List<String>) json.get("list");
			for (Object obj : jsons) {
				list.add(obj.toString());
			}
			this.logger.info(groupId);
			this.groupMapper.batchSaveGroupUsersByUserIds(list, groupId);
			this.dataBaseLogService.log("自由组分配人员", "自由组", "", "", groupId, userProp);
		}

		return new MessageResponse(0, "自由组分配人员完成！");
	}
	private List<Map<String, Object>> selectFreeGroupListByPid(String pid) {
		Connection conn = null;
		SqlRunner sqlRunner = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			conn = sqlSessionFactory.getConfiguration().getEnvironment().getDataSource().getConnection();
			sqlRunner = new SqlRunner(conn);
			Group o = this.groupMapper.selectByPrimaryKey(pid);
			if (CommonUtils.isNotBlank(o.getSqlText())) {
				List<Map<String, Object>> items = sqlRunner.selectAll(o.getSqlText());
				for (Map<String, Object> p : items) {
					Map<String, Object> tmp = new HashMap<String, Object>();
					CommonBeanUtils.copyMap2Bean(tmp, p);
					tmp.put("PID", pid);
					this.logger.info(tmp);
					list.add(tmp);
				}
			}

		} catch (SQLException e) {
			this.logger.info(e);
			return null;
		} finally {
			if (sqlRunner != null) {
				sqlRunner.closeConnection();
			}
		}
		return list;
	}
}
