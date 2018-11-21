package com.huacainfo.ace.jxb.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.jxb.dao.MemberSignLogDao;
import com.huacainfo.ace.jxb.model.MemberSignLog;
import com.huacainfo.ace.jxb.service.CounselorService;
import com.huacainfo.ace.jxb.service.MemberSignLogService;
import com.huacainfo.ace.jxb.vo.CounselorVo;
import com.huacainfo.ace.jxb.vo.MemberSignLogQVo;
import com.huacainfo.ace.jxb.vo.MemberSignLogVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("memberSignLogService")
/**
 * @author: Arvin
 * @version: 2018-08-02
 * @Description: TODO(会员签到日志)
 */
public class MemberSignLogServiceImpl implements MemberSignLogService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MemberSignLogDao memberSignLogDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private CounselorService counselorService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(会员签到日志分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <MemberSignLogVo>
     * @author: Arvin
     * @version: 2018-08-02
     */
    @Override
    public PageResult<MemberSignLogVo> findMemberSignLogList(MemberSignLogQVo condition, int start,
                                                             int limit, String orderBy) throws Exception {
        PageResult<MemberSignLogVo> rst = new PageResult<>();
        List<MemberSignLogVo> list = this.memberSignLogDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.memberSignLogDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertMemberSignLog
     * @Description: TODO(添加会员签到日志)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-02
     */
    @Override
    public MessageResponse insertMemberSignLog(MemberSignLog o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "用户id不能为空！");
        }
        if (CommonUtils.isBlank(o.getSignYear())) {
            return new MessageResponse(1, "签到年不能为空！");
        }
        if (CommonUtils.isBlank(o.getSignMonth())) {
            return new MessageResponse(1, "签到月不能为空！");
        }
        if (CommonUtils.isBlank(o.getSignDay())) {
            return new MessageResponse(1, "签到日不能为空！");
        }
        if (CommonUtils.isBlank(o.getSignInDate())) {
            return new MessageResponse(1, "签到日期不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态 1-未签到 2-已签到不能为空！");
        }


        int temp = this.memberSignLogDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "会员签到日志名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
//                o.setCreateUserName(userProp.getName());
//                o.setCreateUserId(userProp.getUserId());
        this.memberSignLogDao.insertSelective(o);
        this.dataBaseLogService.log("添加会员签到日志", "会员签到日志", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加会员签到日志完成！");
    }

    /**
     * @throws
     * @Title:updateMemberSignLog
     * @Description: TODO(更新会员签到日志)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-02
     */
    @Override
    public MessageResponse updateMemberSignLog(MemberSignLog o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "用户id不能为空！");
        }
        if (CommonUtils.isBlank(o.getSignYear())) {
            return new MessageResponse(1, "签到年不能为空！");
        }
        if (CommonUtils.isBlank(o.getSignMonth())) {
            return new MessageResponse(1, "签到月不能为空！");
        }
        if (CommonUtils.isBlank(o.getSignDay())) {
            return new MessageResponse(1, "签到日不能为空！");
        }
        if (CommonUtils.isBlank(o.getSignInDate())) {
            return new MessageResponse(1, "签到日期不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态 1-未签到 2-已签到不能为空！");
        }


//                o.setLastModifyDate(new Date());
//                o.setLastModifyUserName(userProp.getName());
//                o.setLastModifyUserId(userProp.getUserId());
        this.memberSignLogDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更会员签到日志", "会员签到日志", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更会员签到日志完成！");
    }

    /**
     * @throws
     * @Title:selectMemberSignLogByPrimaryKey
     * @Description: TODO(获取会员签到日志)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<MemberSignLog>
     * @author: Arvin
     * @version: 2018-08-02
     */
    @Override
    public SingleResult<MemberSignLogVo> selectMemberSignLogByPrimaryKey(String id) throws Exception {
        SingleResult<MemberSignLogVo> rst = new SingleResult<>();
        rst.setValue(this.memberSignLogDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteMemberSignLogByMemberSignLogId
     * @Description: TODO(删除会员签到日志)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-02
     */
    @Override
    public MessageResponse deleteMemberSignLogByMemberSignLogId(String id, UserProp userProp) throws
            Exception {
        this.memberSignLogDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除会员签到日志", "会员签到日志",
                String.valueOf(id),
                String.valueOf(id), "会员签到日志", userProp);
        return new MessageResponse(0, "会员签到日志删除完成！");
    }

    /**
     * 每日签到
     *
     * @param counselorId 咨询师id
     * @return ResultResponse
     */
    @Override
    public ResultResponse signIn(String counselorId) throws Exception {
        CounselorVo counselorVo = counselorService.selectCounselorByPrimaryKey(counselorId).getValue();
        if (null == counselorVo) {
            return new ResultResponse(ResultCode.FAIL, "咨询师资料异常");
        }
        //当天日期
        String nowDate = DateUtil.getNow();
        String nowYear = nowDate.substring(0, 4);
        String nowMonth = nowDate.substring(5, 7);
        String nowDay = nowDate.substring(8, 10);
        String todayDate = nowDate.substring(0, 10);//只含年月日 demo：2018-08-02

        MemberSignLog signLog = memberSignLogDao.findToday(todayDate, counselorId);
        if (null == signLog) {
            signLog = new MemberSignLog();
            signLog.setId(GUIDUtil.getGUID());
            signLog.setUserId(counselorId);
            signLog.setSignYear(nowYear);
            signLog.setSignMonth(nowMonth);
            signLog.setSignDay(nowDay);
            signLog.setSignInDate(todayDate);
            signLog.setStatus("2");
            signLog.setCreateDate(DateUtil.getNowDate());
            memberSignLogDao.insertSelective(signLog);
        } else if (signLog.getStatus().equals("1")) {
            signLog.setSignInDate(todayDate);
            signLog.setStatus("2");
            signLog.setUpdateDate(DateUtil.getNowDate());
            memberSignLogDao.updateByPrimaryKeySelective(signLog);
        } else {
            return new ResultResponse(ResultCode.FAIL, "今日已签到，请明天再来");
        }

        return new ResultResponse(ResultCode.SUCCESS, "签到成功");
    }

    /**
     * 获取用户签到信息
     *
     * @param userId   portal.users.user_id
     * @param dateTime 查询日期 demo:2018-08-02 20:43:43
     * @return map
     */
    @Override
    public Map<String, Object> getSignInfo(String userId, String dateTime) {
        String monthDate = dateTime.substring(0, 7);//demo:2018-08

        MemberSignLogQVo condition = new MemberSignLogQVo();
        condition.setMonthDate(monthDate);
        condition.setUserId(userId);
        int signCount = memberSignLogDao.findCount(condition);

        String todayDate = dateTime.substring(0, 10);
        MemberSignLog signLog = memberSignLogDao.findToday(todayDate, userId);

        Map<String, Object> rtnMap = new HashMap<>();
        rtnMap.put("signCount", signCount);//本月已签到天数
        rtnMap.put("status", null == signLog ? "1" : signLog.getStatus());//签到状态 1-未签到 2-已签到
        return rtnMap;
    }

}
