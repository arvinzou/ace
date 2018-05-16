package com.huacainfo.ace.portal.service.impl;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.redis.AspireRedisTemplate;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.SpringUtils;
import com.huacainfo.ace.common.web.tools.WebUtils;
import com.huacainfo.ace.portal.dao.DepartmentDao;
import com.huacainfo.ace.portal.dao.UsersDao;
import com.huacainfo.ace.portal.model.Role;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.ResourcesService;
import com.huacainfo.ace.portal.service.UsersService;
import com.huacainfo.ace.portal.vo.DepartmentVo;
import com.huacainfo.ace.portal.vo.UsersVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("usersService")
public class UsersServiceImpl implements UsersService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UsersDao usersDao;

    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private ResourcesService resourceService;

    @Override
    public PageResult<Map<String, String>> findUsersSearchList(Users condition, int start,
                                                               int limit, String orderBy) throws Exception {
        PageResult<Map<String, String>> rst = new PageResult<Map<String, String>>();
        List<Map<String, String>> list = this.usersDao.findUsersSearchList(
                condition, start, limit, orderBy);
        rst.setRows(list);
        int allRows = this.usersDao.findUsersSearchCount(condition);
        rst.setTotal(allRows);
        return rst;
    }

    @Override
    public PageResult<UsersVo> findUsersList(Users condition, int start, int limit,
                                             String orderBy) throws Exception {
        PageResult<UsersVo> rst = new PageResult<UsersVo>();
        List<UsersVo> list = this.usersDao.findUsersList(condition, start,
                start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.usersDao.findUsersCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    @Override
    public MessageResponse insertUsers(Users users, UserProp userProp, String flag)
            throws Exception {
        if (CommonUtils.isBlank(users.getDepartmentId())) {
            return new MessageResponse(1, "归属部门不能为空!");
        }
        if (CommonUtils.isBlank(users.getAccount())) {

            return new MessageResponse(1, "账户不能为空!");
        }
        if (this.usersDao.isExitUsersAccount(users.getAccount()) > 0) {

            return new MessageResponse(1, "账户已存在!");
        }
        if (CommonUtils.isBlank(users.getPassword())) {

            return new MessageResponse(1, "密码不能为空!");
        }
        if (CommonUtils.isBlank(users.getSex())) {

            return new MessageResponse(1, "性别不能为空!");
        }
        DepartmentVo dept = departmentDao.selectDepartmentVoByPrimaryKey(users.getDepartmentId());
        if (CommonUtils.isBlank(dept)) {
            return new MessageResponse(1, "归属部门不存在!");
        }
        if (CommonUtils.isBlank(users.getName())) {
            return new MessageResponse(1, "姓名不能为空!");
        }
        users.setAreaCode(dept.getAreaCode());
        users.setStauts("1");
        users.setPassword(CommonUtils.getMd5(users.getPassword()));
        String id = String.valueOf(System.currentTimeMillis());
        users.setUserId(id);
        users.setCreateTime(new Date());
        users.setCurSyid(userProp.getActiveSyId());
        this.usersDao.insertUsers(users);
        this.logger.debug("", users.toString());
        this.dataBaseLogService.log("员工添加成功", flag, "",
                "账号:" + users.getAccount() + ",姓名:" + users.getName(), "01", userProp);
        return new MessageResponse(0, "添加员工户完成！");
    }

    @Override
    public MessageResponse insertReg(Users users)
            throws Exception {
        if (CommonUtils.isBlank(users.getDepartmentId())) {
            return new MessageResponse(1, "归属部门不能为空!");
        }
        if (CommonUtils.isBlank(users.getAccount())) {

            return new MessageResponse(1, "账户不能为空!");
        }
        if (this.usersDao.isExitUsersAccount(users.getAccount()) > 0) {

            return new MessageResponse(1, "账户已存在!");
        }
        if (CommonUtils.isBlank(users.getPassword())) {

            return new MessageResponse(1, "密码不能为空!");
        }
        if (CommonUtils.isBlank(users.getSex())) {

            return new MessageResponse(1, "性别不能为空!");
        }
        DepartmentVo dept = departmentDao.selectDepartmentVoByPrimaryKey(users.getDepartmentId());
        if (CommonUtils.isBlank(dept)) {
            return new MessageResponse(1, "归属部门不存在!");
        }
        if (CommonUtils.isBlank(users.getName())) {
            return new MessageResponse(1, "姓名不能为空!");
        }
        users.setAreaCode(dept.getAreaCode());
        users.setStauts("1");
        users.setPassword(CommonUtils.getMd5(users.getPassword()));
        String id = String.valueOf(System.currentTimeMillis());
        users.setUserId(id);
        users.setCreateTime(new Date());
        users.setCurSyid("uf");
        this.usersDao.insertUsers(users);
        return new MessageResponse(0, "注册成功！");
    }

    @Override
    public MessageResponse updateUsers(Users users, UserProp userProp, String flag)
            throws Exception {
        if (CommonUtils.isBlank(users.getUserId())) {
            return new MessageResponse(1, "员工编号不能为空!");
        }
        if (CommonUtils.isBlank(users.getDepartmentId())) {
            return new MessageResponse(1, "归属部门不能为空!");
        }
        if (CommonUtils.isBlank(users.getAccount())) {

            return new MessageResponse(1, "账户不能为空!");
        }

        if (CommonUtils.isBlank(users.getSex())) {

            return new MessageResponse(1, "性别不能为空!");
        }
        DepartmentVo dept = departmentDao.selectDepartmentVoByPrimaryKey(users.getDepartmentId());
        if (CommonUtils.isBlank(dept)) {
            return new MessageResponse(1, "归属部门不存在!");
        }
        if (CommonUtils.isBlank(users.getName())) {
            return new MessageResponse(1, "姓名不能为空!");
        }
        if (users.getPassword().length() < 20) {
            users.setPassword(CommonUtils.getMd5(users.getPassword()));
        }
        Users u = this.selectUsersByPrimaryKey(users.getUserId()).getValue();
        this.usersDao.updateUsersByPrimaryKey(users);
        this.dataBaseLogService.log(flag, "联系人", users.getUserId() + ",姓名:" + u.getName() + ",手机:" + u.getMobile() + ",电话号码:" + u.getTelphone() + ",QQ:" + u.getQq() + ",传真:" + u.getFax(),
                users.getName() + ",姓名:" + users.getName() + ",手机:" + users.getMobile() + ",电话号码:" + users.getTelphone() + ",QQ:" + users.getQq() + ",传真:" + users.getFax(), users.getUserId(), userProp);
        return new MessageResponse(0, "变更员工户完成！");
    }

    @Override
    public MessageResponse updateUsersStautsByPrimaryKey(String usersId,
                                                         String struts, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(usersId)) {

            return new MessageResponse(1, "员工编号不能为空！");
        }
        if (CommonUtils.isBlank(usersId)) {

            return new MessageResponse(1, "员工状态不能为空！");
        }
        this.usersDao.updateUsersStautsByPrimaryKey(usersId, struts);
        this.dataBaseLogService.log("员工状态变更", "员工状态", "", struts, usersId,
                userProp);
        return new MessageResponse(0, "员工状态变更完成！");
    }

    @Override
    public MessageResponse updateUsersForInitPassword(String usersId,
                                                      String password, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(usersId)) {

            return new MessageResponse(1, "员工编号不能为空！");
        }
        if (CommonUtils.isBlank(password)) {

            return new MessageResponse(1, "密码不能为空！");
        }
        password = CommonUtils.getMd5(password);
        this.usersDao.updateUsersForInitPassword(usersId, password);
        this.dataBaseLogService.log("员工初始化密码", "员工密码", "", "新密码为：" + password, usersId,
                userProp);
        return new MessageResponse(0, "员工初始化密码完成！");
    }

    @Override
    public SingleResult<UsersVo> selectUsersByPrimaryKey(String usersId)
            throws Exception {
        SingleResult<UsersVo> rst = new SingleResult<UsersVo>();
        UsersVo usersVo = this.usersDao.selectUsersVoByPrimaryKey(usersId);
        rst.setValue(usersVo);
        return rst;
    }

    @Override
    public MessageResponse insertUsersRole(String userId, String[] roleId,
                                           UserProp userProp) throws Exception {
        this.usersDao.insertUsersRole(userId, roleId);
        List<Map<String, String>> list = resourceService.loadResourceDefine();
        AspireRedisTemplate redisTemplateString = (AspireRedisTemplate) SpringUtils
                .getBean("redisTemplateString");
        WebUtils.flushRoleResourceCache(redisTemplateString, list);
        this.dataBaseLogService.log("员工分配角色", "分配角色", "", "", userId, userProp);
        return new MessageResponse(0, "角色分配完成！");
    }

    @Override
    public PageResult<Role> selectRoleList(UserProp userProp) throws Exception {
        PageResult<Role> rst = new PageResult<Role>();
        List<Role> list = this.usersDao.selectRoleList(userProp.getActiveSyId());
        rst.setRows(list);
        rst.setTotal(list.size());
        return rst;
    }

    @Override
    public PageResult<Role> selectRoleListByUserId(String userId) throws Exception {
        PageResult<Role> rst = new PageResult<Role>();
        List<Role> list = this.usersDao.selectRoleListByUserId(userId);
        rst.setRows(list);
        rst.setTotal(list.size());
        return rst;
    }


    /**
     * 删除联系人信息
     *
     * @param id
     * @param userProp
     * @return MessageResponse
     * @version: 2017年02月28日 下午16:47
     */
    @Override
    public MessageResponse deleteUsers(String id, UserProp userProp) {

        if (CommonUtils.isBlank(id)) {
            return new MessageResponse(1, "编号不能为空！");
        }
        Users u = this.usersDao.selectUsersVoByPrimaryKey(id);
        this.usersDao.deleteUsersById(id);
        this.dataBaseLogService.log("删除联系人信息", u.getName(),
                "姓名:" + u.getName() + ",手机号码:" + u.getMobile() + ",电话:" + u.getTelphone() + ",邮箱:" + u.getEmail() + ",QQ:" + u.getQq() + ",所属公司:" + u.getDepartmentId() + ",数据状态:" + u.getStauts(),
                "删除编号是" + id, id,
                userProp);
        return new MessageResponse(0, "删除成功！");
    }

    /**
     * @throws
     * @Title:updateUsersById
     * @Description: TODO(修改企业联系人信息)
     * @param: @param jsons
     * @param: @return
     * @param: @throws Exception
     * @return: MessageResponse
     * @version: 2017年02月28日 下午2:43:34
     */
    @Override
    public MessageResponse updateUsersById(Users obj, UserProp curUserProp) {
        if (CommonUtils.isBlank(obj.getUserId())) {

            return new MessageResponse(1, "联系人编号不能为空！");
        }
        this.usersDao.updateUsersByPrimaryKey(obj);
        this.dataBaseLogService.log("修改联系人信息", "联系人信息", "", "", obj.getUserId(),
                curUserProp);
        return new MessageResponse(0, "联系人信息修改成功！");
    }

    /**
     * 根据企业编号查询所有联系人
     *
     * @param condition
     * @param start
     * @param limit
     * @param orderBy
     * @return PageResult<Map<String,String>>
     */
    @Override
    public PageResult<Map<String, String>> findDeIdByUsersList(Users condition, int start, int limit, String orderBy) {
        PageResult<Map<String, String>> rst = new PageResult<Map<String, String>>();
        List<Map<String, String>> list = this.usersDao.findDeIdByUsersList(condition, start, limit, orderBy);
        rst.setRows(list);
        int allRows = this.usersDao.findDeIdByUsersCount(condition);
        logger.debug("联系人总数为：{},记录条数为：{}", allRows, list.size());
        rst.setTotal(allRows);
        return rst;
    }


    @Override
    public MessageResponse deleteConUsers(String id, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(id)) {
            return new MessageResponse(1, "编号不能为空！");
        }
        Users u = this.usersDao.selectUsersVoByPrimaryKey(id);
        this.usersDao.updateUsersIdByStatus(id);
        this.dataBaseLogService.log("删除联系人信息", u.getName(),
                "姓名:" + u.getName() + ",手机号码:" + u.getMobile() + ",电话:" + u.getTelphone() + ",邮箱:" + u.getEmail() + ",QQ:" + u.getQq() + ",所属公司:" + u.getDepartmentId() + ",数据状态:" + u.getStauts(),
                "删除编号是" + id, id,
                userProp);
        return new MessageResponse(0, "删除成功！");
    }


    @Override
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
        int i = 1;
        for (Map<String, Object> row : list) {
            Users o = new Users();
            CommonBeanUtils.copyMap2Bean(o, row);
            o.setCreateTime(new Date());
            o.setStauts("1");
            this.logger.info(o.toString());
            if (CommonUtils.isBlank(o.getUserId())) {
                return new MessageResponse(1, "行" + i + ",员工编号不能为空！");
            }
            if (CommonUtils.isBlank(o.getDepartmentId())) {
                return new MessageResponse(1, "行" + i + ",归属部门不能为空！");
            }
            if (CommonUtils.isBlank(o.getName())) {
                return new MessageResponse(1, "行" + i + ",姓名不能为空！");
            }
            if (CommonUtils.isBlank(o.getAccount())) {
                o.setAccount(o.getUserId());
            }
            if (CommonUtils.isBlank(o.getSex())) {
                o.setSex("1");
            }
            if (CommonUtils.isBlank(o.getPassword())) {
                o.setPassword(CommonUtils.getMd5("2017$Abc"));
            } else {
                o.setPassword(CommonUtils.getMd5(o.getPassword()));
            }

            int t = usersDao.isExitUsersAccount(o.getAccount());
            if (t > 0) {
                this.usersDao.updateUsersByPrimaryKey(o);
            } else {
                this.usersDao.insertUsers(o);
            }
            i++;
        }
        this.dataBaseLogService.log("员工导入", "员工管理", "", "rs.xls",
                "rs.xls", userProp);
        return new MessageResponse(0, "导入完成！");
    }

    /**
     * @throws
     * @Title:deleteRoleById
     * @Description: TODO(删除微信用户的角色)
     * @param: @param id
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-02-04
     */
    @Override
    public MessageResponse deleteOpenIdById(String userId, UserProp userProp) throws Exception {
        this.usersDao.updateUserOpenId(userId, null);
        this.dataBaseLogService.log("绑定微信", "用户", String.valueOf(userId),
                String.valueOf(userId), "微信用户", userProp);
        return new MessageResponse(0, "解除绑定完成！");
    }

    /**
     * @throws
     * @Title:updateRoleById
     * @Description: TODO(更新微信用户的角色)
     * @param: @param id
     * @param: @param role
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-02-04
     */
    @Override
    public MessageResponse updateOpenIdById(String userId, String openId, UserProp userProp) throws Exception {
        this.usersDao.updateUserOpenId(userId, openId);
        this.dataBaseLogService.log("绑定微信", "用户", String.valueOf(userId),
                String.valueOf(userId), "微信用户", userProp);
        return new MessageResponse(0, "绑定完成！");
    }

    /**
     * @throws
     * @Title:selectWxUser
     * @Description: TODO(组合查询微信用户)
     * @param: @param id
     * @param: @param role
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-02-04
     */
    @Override
    public List<Map<String, Object>> selectWxUser(Map<String, Object> condition) throws Exception {
        return this.usersDao.selectWxUser(condition);
    }

    /**
     * @throws
     * @Title:updateUserAppOpenId
     * @Description: TODO(绑定小程序用户)
     * @param: @param userId
     * @param: @param appOpenId
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-04-25
     */
    @Override
    public MessageResponse updateUserAppOpenId(String userId, String appOpenId, UserProp userProp) throws Exception {
        this.usersDao.updateUserAppOpenId(userId, appOpenId);
        this.dataBaseLogService.log("绑定微信", "用户", String.valueOf(userId),
                String.valueOf(userId), "微信用户", userProp);
        return new MessageResponse(0, "绑定完成！");
    }

    /**
     * @throws
     * @Title:selectAppWxUser
     * @Description: TODO(查询已绑定的小程序用户)
     * @param: @param Map<String,Object> userId
     * @param: @throws Exception
     * @return: List<Map<String,Object>>
     * @author: 陈晓克
     * @version: 2018-04-25
     */
    @Override
    public List<Map<String, Object>> selectAppWxUser(Map<String, Object> condition) throws Exception {
        return this.usersDao.selectAppWxUser(condition);
    }

    /**
     * @throws
     * @Title:selectAllAppWxUserList
     * @Description: TODO(查询appId下所有小程序用户)
     * @param: @param userProp
     * @param: @throws Exception
     * @return: List<Map<String,Object>>
     * @author: 陈晓克
     * @version: 2018-04-25
     */
    @Override
    public List<Map<String, Object>> selectAllAppWxUserList(Map<String, Object> p) throws Exception {

        return this.usersDao.selectAllAppWxUserList(p);
    }

    /**
     * @throws
     * @Title:selectAllWxUserList
     * @Description: TODO(查询appId下所有公众号用户)
     * @param: @param userProp
     * @param: @throws Exception
     * @return: List<Map<String,Object>>
     * @author: 陈晓克
     * @version: 2018-04-25
     */
    @Override
    public List<Map<String, Object>> selectAllWxUserList(Map<String, Object> p) throws Exception {
        return this.usersDao.selectAllWxUserList(p);
    }


    /**
     * @throws
     * @Title:deleteAppOpenIdById
     * @Description: TODO(删除微信用户的角色)
     * @param: @param id
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-04-26
     */
    @Override
    public MessageResponse deleteAppOpenIdById(String userId, UserProp userProp) throws Exception {
        this.usersDao.updateUserAppOpenId(userId, null);
        this.dataBaseLogService.log("绑定微信", "用户", String.valueOf(userId),
                String.valueOf(userId), "微信用户", userProp);
        return new MessageResponse(0, "解除绑定完成！");
    }


    @Override
    public ResultResponse insertUsersRecord(Users users, UserProp userProp, String logTag) throws Exception {
        if (CommonUtils.isBlank(users.getDepartmentId())) {
            return new ResultResponse(1, "归属部门不能为空!");
        }
        if (CommonUtils.isBlank(users.getAccount())) {

            return new ResultResponse(1, "账户不能为空!");
        }
        if (this.usersDao.isExitUsersAccount(users.getAccount()) > 0) {

            return new ResultResponse(1, "账户已存在!");
        }
        if (CommonUtils.isBlank(users.getPassword())) {

            return new ResultResponse(1, "密码不能为空!");
        }
        if (CommonUtils.isBlank(users.getSex())) {

            return new ResultResponse(1, "性别不能为空!");
        }
        DepartmentVo dept = departmentDao.selectDepartmentVoByPrimaryKey(users.getDepartmentId());
        if (CommonUtils.isBlank(dept)) {
            return new ResultResponse(1, "归属部门不存在!");
        }
        if (CommonUtils.isBlank(users.getName())) {
            return new ResultResponse(1, "姓名不能为空!");
        }
        users.setAreaCode(dept.getAreaCode());
        users.setStauts("1");
        users.setPassword(CommonUtils.getMd5(users.getPassword()));
        String id = String.valueOf(System.currentTimeMillis());
        users.setUserId(id);
        users.setCreateTime(new Date());
        users.setCurSyid(userProp.getActiveSyId());
        this.usersDao.insertUsers(users);
        this.dataBaseLogService.log("员工添加成功", logTag, "",
                "账号:" + users.getAccount() + ",姓名:" + users.getName(), "01", userProp);

        return new ResultResponse(0, "添加员工户完成！", users);
    }
}
