package com.huacainfo.ace.woc.service.impl;


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
import com.huacainfo.ace.woc.dao.PersonDao;
import com.huacainfo.ace.woc.model.Person;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.woc.service.PersonService;
import com.huacainfo.ace.woc.vo.PersonVo;
import com.huacainfo.ace.woc.vo.PersonQVo;
@Service("personService")
/**
 * @author: 王恩
 * @version: 2018-03-09
 * @Description:  TODO(人员信息)
 */
public class PersonServiceImpl implements PersonService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PersonDao personDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(人员信息分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<PersonVo>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-09
	 */
    @Override
	public PageResult<PersonVo> findPersonList(PersonQVo condition, int start,
			int limit, String orderBy) throws Exception {
		PageResult<PersonVo> rst = new PageResult<PersonVo>();
		List<PersonVo> list = this.personDao.findList(condition,
				start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.personDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}
    /**
	 *
	    * @Title:insertPerson
	    * @Description:  TODO(添加人员信息)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-09
	 */
    @Override
	public MessageResponse insertPerson(Person o, UserProp userProp)
			throws Exception {
		o.setId(UUID.randomUUID().toString());
		//o.setId(String.valueOf(new Date().getTime()));
		
		int temp = this.personDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "人员信息名称重复！");
		}
		o.setCreateDate(new Date());
		o.setStatus("1");
		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		this.personDao.insert(o);
		this.dataBaseLogService.log("添加人员信息", "人员信息", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "添加人员信息完成！");
	}
    /**
	 *
	    * @Title:updatePerson
	    * @Description:  TODO(更新人员信息)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-09
	 */
    @Override
	public MessageResponse updatePerson(Person o, UserProp userProp)
			throws Exception {
		
		
		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		this.personDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更人员信息", "人员信息", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "变更人员信息完成！");
	}

    /**
	 *
	    * @Title:selectPersonByPrimaryKey
	    * @Description:  TODO(获取人员信息)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Person>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-09
	 */
    @Override
	public SingleResult<PersonVo> selectPersonByPrimaryKey(String id) throws Exception {
		SingleResult<PersonVo> rst = new SingleResult<PersonVo>();
		rst.setValue(this.personDao.selectByPrimaryKey(id));
		return rst;
	}
    /**
	 *
	    * @Title:deletePersonByPersonId
	    * @Description:  TODO(删除人员信息)
	 		* @param:        @param id
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-09
	 */
    @Override
	public MessageResponse deletePersonByPersonId(String id,
			UserProp userProp) throws Exception {
		this.personDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除人员信息", "人员信息", String.valueOf(id),
				String.valueOf(id), "人员信息", userProp);
		return new MessageResponse(0, "人员信息删除完成！");
	}
}
