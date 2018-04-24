package com.huacainfo.ace.woc.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.model.TaskCmcc;
import com.huacainfo.ace.portal.service.TaskCmccService;
import com.huacainfo.ace.woc.dao.PersonDao;
import com.huacainfo.ace.woc.dao.TrafficDao;
import com.huacainfo.ace.woc.service.WWWApiService;
import com.huacainfo.ace.woc.vo.TrafficVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HuaCai008 on 2018/4/23.
 */
@Service("WWWApiService")
public class WWWApiServiceImpl implements WWWApiService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisOperations<String, Object> redisTemplate;

    @Autowired
    private TaskCmccService taskCmccService;

    @Autowired
    private PersonDao personDao;

    @Autowired
    private TrafficDao trafficDao;


    /**
     * 手机号码注册前，发送手机验证码
     *
     * @param mobile      手机号码
     * @param wxSessionId
     * @param curWxUser   当前微信用户信息  @return  @throws Exception
     */
    @Override
    public ResultResponse sendCmccByMobile(String mobile, String wxSessionId, WxUser curWxUser) throws Exception {
        if (CommonUtils.isEmpty(wxSessionId)) {
            return new ResultResponse(ResultCode.FAIL, "非法登录用户");
        }
        if (CommonUtils.isBlank(mobile)) {
            return new ResultResponse(ResultCode.FAIL, "手机号不能为空");
        }
        if (!CommonUtils.isValidMobile(mobile)) {
            return new ResultResponse(ResultCode.FAIL, "手机号格式错误");
        }

        Map<String, Object> wxInfo = personDao.findWxInfoByMobile(mobile);
        String unionid = null == wxInfo ? "" : (String) wxInfo.get("unionid");
        //待  unionId 获取成功后，开发此处代码
//        if (null != wxInfo
//                && CommonUtils.isNotEmpty(unionid)
//                && !curWxUser.getUnionId().equals(unionid)) {
//            return new ResultResponse(ResultCode.FAIL, "该手机号码已存在其它绑定关系");
//        }
        int t = personDao.isExitByMobile(mobile);
        if (t == 0) {
            return new ResultResponse(ResultCode.FAIL, "该手机号码不存在于人员档案中，请联系管理员添加");
        }

        String j_captcha_cmcc = CommonUtils.getIdentifyCode(4, CommonUtils.IDENTIFY_CODE_TYPE_NUMBER);
        TaskCmcc o = new TaskCmcc();
        Map<String, Object> msg = new HashMap<String, Object>();
        msg.put("taskName", "验证码" + mobile);
        msg.put("msg", "本次提交验证码为" + j_captcha_cmcc + "，请及时输入。【智慧治超】");
        msg.put("tel", mobile + "," + mobile);
        CommonBeanUtils.copyMap2Bean(o, msg);

        String redisKey = wxSessionId + "-" + mobile;
        redisTemplate.opsForValue().set(redisKey, j_captcha_cmcc);
        logger.debug("sendCmccByMobile.redis.put.key=" + redisKey + ",value=" + j_captcha_cmcc);

        return new ResultResponse(taskCmccService.insertTaskCmcc(o));
    }

    /**
     * 手机号码注册
     *
     * @param mobile      手机号码
     * @param captcha     验证码
     * @param wxSessionId
     * @param curWxUser
     * @return
     * @throws Exception
     */
    @Override
    public ResultResponse mobileRegister(String mobile, String captcha, String wxSessionId, WxUser curWxUser) {

        //数据校验
        if (CommonUtils.isEmpty(wxSessionId)) {
            return new ResultResponse(ResultCode.FAIL, "非法登录用户");
        }
        if (CommonUtils.isBlank(mobile)) {
            return new ResultResponse(ResultCode.FAIL, "手机号不能为空");
        }
        if (!CommonUtils.isValidMobile(mobile)) {
            return new ResultResponse(ResultCode.FAIL, "手机号格式错误");
        }

        //验证码
        String redisKey = wxSessionId + "-" + mobile;
        String j_captcha = (String) redisTemplate.opsForValue().get(redisKey);
        logger.debug("mobileRegister.redis.get.key=" + redisKey + ",value=" + j_captcha);
        if (CommonUtils.isEmpty(captcha) || !captcha.equals(j_captcha)) {
            return new ResultResponse(ResultCode.FAIL, "验证码错误");
        }

        Map<String, Object> wxInfo = personDao.findWxInfoByMobile(mobile);
        String unionid = null == wxInfo ? "" : (String) wxInfo.get("unionid");
        //待  unionId 获取成功后，开发此处代码
//        if (null != wxInfo
//                && CommonUtils.isNotEmpty(unionid)
//                && !curWxUser.getUnionId().equals(unionid)) {
//            return new ResultResponse(ResultCode.FAIL, "该手机号码已存在其它绑定关系");
//        }
        int t = personDao.isExitByMobile(mobile);
        if (t == 0) {
            return new ResultResponse(ResultCode.FAIL, "该手机号码不存在于人员档案中，请联系管理员添加");
        }

        //TODO 待开发平台认证成功后，换成此代码： curWxUser.getOpenId() --> curWxUser.getUnionId()
        int updateCount = personDao.updateWxInfoByOpenId(mobile, curWxUser.getOpenId());
        if (updateCount <= 0) {
            return new ResultResponse(ResultCode.FAIL, "数据更新失败");
        }

        return new ResultResponse(ResultCode.SUCCESS, "注册成功");
    }

    /**
     * 查询车牌对应违章记s录
     *
     * @param captcha     验证码
     * @param mobile      车主手机号码
     * @param plateNo     车牌号
     * @param wxSessionId
     * @param curWxUser
     * @return
     */
    @Override
    public ResultResponse findIllegalTraffic(String captcha, String mobile, String plateNo,
                                             String wxSessionId, WxUser curWxUser) {
        if (CommonUtils.isBlank(mobile)) {
            return new ResultResponse(ResultCode.FAIL, "手机号不能为空");
        }
        if (!CommonUtils.isValidMobile(mobile)) {
            return new ResultResponse(ResultCode.FAIL, "手机号格式错误");
        }
        //验证码
        String redisKey = wxSessionId + "-" + mobile;
        String j_captcha = (String) redisTemplate.opsForValue().get(redisKey);
        logger.debug("mobileRegister.redis.get.key=" + redisKey + ",value=" + j_captcha);
        if (CommonUtils.isEmpty(captcha) || !captcha.equals(j_captcha)) {
            return new ResultResponse(ResultCode.FAIL, "验证码错误");
        }

        Map<String, Object> condition = new HashMap<>();
        condition.put("plateNo", plateNo);
        condition.put("status", new String[]{"0"});
        condition.put("mobile", mobile);
        List<TrafficVo> list = trafficDao.selectTrafficList(condition, 0, 0 + 5);

        return new ResultResponse(ResultCode.SUCCESS, "查询成功", list);
    }
}
