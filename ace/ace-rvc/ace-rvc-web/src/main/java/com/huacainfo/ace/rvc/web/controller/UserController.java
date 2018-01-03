package com.huacainfo.ace.rvc.web.controller;

import com.huacainfo.ace.common.tools.StringUtils;
import com.huacainfo.ace.rvc.model.RvcBaseUser;
import com.huacainfo.ace.rvc.service.UserService;
import com.huacainfo.ace.rvc.util.ResultUtil;
import com.huacainfo.ace.rvc.web.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Arvin on 2017/11/23.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录接口
     * ，
     *
     * @param userId 用户ID -- 浪潮ID
     * @return user
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> login(String userId) throws Exception {
        if (StringUtils.isEmpty(userId)) {
            return ResultUtil.fail(-1, "用户ID不能为空");
        }

        try {
            RvcBaseUser user = userService.login(userId);
            return ResultUtil.success(user);
        } catch (Exception e) {
            logger.error("UserController.login.error:{}", e);

            return ResultUtil.fail(-1, "系统错误");
        }
    }

    /***
     * 获取所有人员列表
     * @param userId 操作用户ID
     * @return list
     */
    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Map<String, Object> getAll(String userId) {

        try {
            List<RvcBaseUser> userList = userService.getAll(userId);

            return ResultUtil.success(userList);
        } catch (Exception e) {
            logger.error("UserController.getAll.error:{}", e);

            return ResultUtil.fail(-1, "系统错误");
        }
    }
}
