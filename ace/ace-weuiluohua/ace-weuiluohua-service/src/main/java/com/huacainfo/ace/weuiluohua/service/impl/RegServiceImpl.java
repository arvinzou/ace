package com.huacainfo.ace.weuiluohua.service.impl;

import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.vo.DepartmentVo;
import com.huacainfo.ace.weuiluohua.dao.RegDao;
import com.huacainfo.ace.weuiluohua.model.Users;
import com.huacainfo.ace.weuiluohua.service.RegService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * Created by chenxiaoke on 2017/5/16.
 */
@Service("regService")
public class RegServiceImpl implements RegService {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RegDao regDao;

	public MessageResponse reg(Users users) throws Exception {
		if (CommonUtils.isBlank(users.getAccount())) {

			return new MessageResponse(1, "账户不能为空!");
		}
		if (this.regDao.isExitUsersByAccount(users.getAccount()) > 0) {

			return new MessageResponse(1, "账户已存在!");
		}
		if (CommonUtils.isBlank(users.getPassword())) {
			return new MessageResponse(1, "密码不能为空!");
		}
		if (CommonUtils.isBlank(users.getSex())) {
			return new MessageResponse(1, "性别不能为空!");
		}
		if (CommonUtils.isBlank(users.getName())) {
			return new MessageResponse(1, "姓名不能为空!");
		}
		users.setStauts("1");
		users.setPassword(CommonUtils.getMd5(users.getPassword()));
		users.setUserId(String.valueOf(new Date().getTime()));
		users.setCreateTime(new Date());
		return new MessageResponse(0, "注册成功.");
	}
	public SingleResult<Users> login(Map<String, Object> obj) throws Exception {
		SingleResult<Users> rst = new SingleResult<Users>();
		Users o = this.regDao.selectUsersByAccount((String)obj.get("username"));
		String pwd=(String)obj.get("password");
		if(CommonUtils.isBlank(o)){
           return new SingleResult(1, "账号不存在.");
        }
        if(!o.getPassword().equals(CommonUtils.getMd5(pwd))){
            return new SingleResult(1, "密码错误.");
        }
		rst.setValue(o);
		return rst;
	}

}
