package com.huacainfo.ace.portal.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.dao.ConfigMapper;
import com.huacainfo.ace.portal.dao.RoleDao;
import com.huacainfo.ace.portal.dao.SysInitDao;
import com.huacainfo.ace.portal.model.*;
import com.huacainfo.ace.portal.service.DepartmentService;
import com.huacainfo.ace.portal.service.RoleService;
import com.huacainfo.ace.portal.service.SyCfgService;
import com.huacainfo.ace.portal.service.SysInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SysInitServiceImpl
 * @Description TODO
 * @Author HuaCai008
 * @Date 2019/3/5 9:14
 */
@Service("sysInitService")
public class SysInitServiceImpl implements SysInitService {

    @Autowired
    private SysInitDao sysInitDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private ConfigMapper configMapper;

    @Autowired
    private RoleService roleService;
    @Autowired
    private SyCfgService syCfgService;
    @Autowired
    private DepartmentService departmentService;

    private UserProp rootUser() {
        UserProp userProp = new UserProp();
        userProp.setUserId("init");
        userProp.setName("init");
        return userProp;
    }


    /**
     * 初始化新系统
     *
     * @param sysId   系统识别ID
     * @param sysName 系统名称
     * @param acct    管理员账号
     * @param pwd     管理员密码
     * @return ResultResponse 处理结果
     */
    @Override
    public ResultResponse addNewSystem(String sysId, String sysName, String acct, String pwd) throws Exception {
        //1、增加系统配置
        SyCfg syCfg = new SyCfg();
        syCfg.setId(sysId);
        syCfg.setName(sysName);
        int icfg = sysInitDao.insertSyCfg(syCfg);
        if (icfg < 1) {
            throw new CustomException("初始化系统失败");
        }
        //2、增加部门
        String deptId = GUIDUtil.getGUID();
        Department department = new Department();
        department.setDepartmentId(deptId);
        department.setDepartmentName(sysName);
        department.setAreaCode("430702");
        department.setParentDepartmentId("0");
        department.setStatus("1");
        MessageResponse dept = departmentService.insertDepartmentWithDepId(department, rootUser());
        if (ResultCode.FAIL == dept.getStatus()) {
            throw new CustomException(dept.getErrorMessage());
        }
        //3、预设系统参数
        addConfig(deptId, sysId, sysName);
        //4、预设管理员角色
        String roleId = GUIDUtil.getGUID();
        String userId = GUIDUtil.getGUID();//系统管理员ID
        int ir = initRole(roleId, userId, sysId, sysName);
        if (ir != 1) {
            throw new CustomException("初始化系统角色失败");
        }
        //5、预设系统管理员权限
        MessageResponse ra = initRoleAuthority(roleId);
        if (ResultCode.FAIL == ra.getStatus()) {
            throw new CustomException(ra.getErrorMessage());
        }
        //6、创建系统管理员账号
        int iu = initUser(sysId, roleId, userId, acct, pwd, deptId);
        if (iu < 1) {
            throw new CustomException("初始化系统用户失败");
        }

        return new ResultResponse(ResultCode.SUCCESS, "初始化系统成功");
    }

    private int initUser(String sysId, String roleId, String userId,
                         String account, String pwd, String deptId) {

        Users o = new Users();
        o.setUserId(userId);
        o.setAccount(account);
        o.setPassword(CommonUtils.getMd5(pwd));
        o.setSex("1");
        o.setName("系统管理员");
        o.setDepartmentId(deptId);
        o.setCurSyid(sysId);

        o.setUserLevel("admin");//身份标识 1-学生 2-教职工 3-管理员
        o.setStauts("1");/**状态（0停用正常1）*/
        o.setCreateTime(new java.util.Date());
        return sysInitDao.insertUsers(o, roleId);
    }

    private MessageResponse initRoleAuthority(String roleId) throws Exception {
        List<Resources> list = sysInitDao.findResList();
        List<String> ids = new ArrayList<>();
        for (Resources item : list) {
            ids.add(item.getResourcesId());
        }

        String[] resArray = ids.toArray(new String[ids.size()]);
        return roleService.insertRoleResources(roleId, resArray, rootUser());
    }

    private int initRole(String roleId, String userId, String sysId, String sysName) {
        Role r = new Role();
        r.setRoleId(roleId);
        r.setSyid(sysId);
        r.setCreateUserId(userId);
        r.setType("1");
        r.setRoleName("系统管理员");
        r.setRemark(sysName + "-系统管理员");
        r.setCreateTime(DateUtil.getNowDate());

        return roleDao.insertRole(r);
    }

    /**
     * 插入系统默认配置
     *
     * @return ResultResponse
     */
    private void addConfig(String deptId, String sysId, String sysName) {
        //1.menuRootId
        insertConfig(deptId, sysId, "menuRootId", "1", "默认菜单编号");
        //2.sys_name
        insertConfig(deptId, sysId, "sys_name", sysName, "系统名称");
        //3、fastdfs_server
        insertConfig(deptId, sysId, "fastdfs_server", "", "文档服务");
        //4、default_page_list
        insertConfig(deptId, sysId, "default_page_list", "20, 30, 50, 100, 200", "默认分页");
        //5、sys_login_bg_img
        insertConfig(deptId, sysId, "sys_login_bg_img", "/portal/content/portal/images/LOGIN2880-760.png", "登录背景图片");
        //6、sys_unit
        insertConfig(deptId, sysId, "sys_unit", "企业", "使用单位名称");
        //7、version
        insertConfig(deptId, sysId, "version", "V1.0.3", "系统版本");
        //8、logo
        insertConfig(deptId, sysId, "logo", "/portal/content/common/assets/layouts/layout/img/logo.png", "系统logo");
        //9、admin_mobile
        insertConfig(deptId, sysId, "admin_mobile", "root", "管理员手机号");
        //10、admin_email
        insertConfig(deptId, sysId, "admin_email", "root@qq.com", "管理员邮箱");
    }

    private void insertConfig(String deptId, String sysId,
                              String key, String value, String description) {
        Config config = new Config();
        config.setId(GUIDUtil.getGUID());
        config.setSyid(sysId);
        config.setDeptId(deptId);
        config.setConfigKey(key);
        config.setConfigValue(value);
        config.setConfigName(description);
        config.setCategory("01");
        config.setCreateTime(DateUtil.getNowDate());
        configMapper.insert(config);
    }
}
