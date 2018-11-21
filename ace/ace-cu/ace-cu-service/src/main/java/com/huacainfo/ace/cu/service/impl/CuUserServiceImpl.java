package com.huacainfo.ace.cu.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.tools.PropertyUtil;
import com.huacainfo.ace.cu.dao.CuUserDao;
import com.huacainfo.ace.cu.model.CuUser;
import com.huacainfo.ace.cu.service.CuDonateListService;
import com.huacainfo.ace.cu.service.CuProjectApplyService;
import com.huacainfo.ace.cu.service.CuUserService;
import com.huacainfo.ace.cu.vo.*;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.UserinfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service("cuUserService")
/**
 * @author: Arvin
 * @version: 2018-06-13
 * @Description: TODO(会员资料)
 */
public class CuUserServiceImpl implements CuUserService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CuUserDao cuUserDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private UserinfoService userinfoService;
    @Autowired
    private CuDonateListService cuDonateListService;
    @Autowired
    private CuProjectApplyService cuProjectApplyService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(会员资料分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CuUserVo>
     * @author: Arvin
     * @version: 2018-06-13
     */
    @Override
    public PageResult<CuUserVo> findCuUserList(CuUserQVo condition, int start,
                                               int limit, String orderBy) throws Exception {
        PageResult<CuUserVo> rst = new PageResult<>();
        List<CuUserVo> list = this.cuUserDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.cuUserDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertCuUser
     * @Description: TODO(添加会员资料)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-13
     */
    @Override
    public MessageResponse insertCuUser(CuUser o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getOpenId())) {
            return new MessageResponse(1, "微信openId不能为空！");
        }
        if (CommonUtils.isBlank(o.getRealName())) {
            return new MessageResponse(1, "真实姓名不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }


        int temp = this.cuUserDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "会员资料名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.cuUserDao.insertSelective(o);
        this.dataBaseLogService.log("添加会员资料", "会员资料", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加会员资料完成！");
    }

    /**
     * @throws
     * @Title:updateCuUser
     * @Description: TODO(更新会员资料)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-13
     */
    @Override
    public MessageResponse updateCuUser(CuUser o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getOpenId())) {
            return new MessageResponse(1, "微信openId不能为空！");
        }
        if (CommonUtils.isBlank(o.getRealName())) {
            return new MessageResponse(1, "真实姓名不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.cuUserDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更会员资料", "会员资料", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更会员资料完成！");
    }

    /**
     * @throws
     * @Title:selectCuUserByPrimaryKey
     * @Description: TODO(获取会员资料)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CuUser>
     * @author: Arvin
     * @version: 2018-06-13
     */
    @Override
    public SingleResult<CuUserVo> selectCuUserByPrimaryKey(String id) throws Exception {
        SingleResult<CuUserVo> rst = new SingleResult<>();
        rst.setValue(this.cuUserDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteCuUserByCuUserId
     * @Description: TODO(删除会员资料)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-13
     */
    @Override
    public MessageResponse deleteCuUserByCuUserId(String id, UserProp userProp) throws Exception {
        this.cuUserDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除会员资料", "会员资料",
                String.valueOf(id),
                String.valueOf(id), "会员资料", userProp);
        return new MessageResponse(0, "会员资料删除完成！");
    }

    /**
     * 查询会员信息
     *
     * @param openId 微信openid
     * @return
     * @throws Exception
     */
    @Override
    public CuUserVo findByOpenId(String openId) {
        String appid = PropertyUtil.getProperty("appid");
        CuUserVo vo = cuUserDao.findByOpenId(openId, appid);
        if (null == vo) {
            //查询userinfo
            Userinfo userinfo = userinfoService.findByOpenId(openId, appid);
            if (null == userinfo) {
                return null;
            }
            //构建CuUser
            vo = new CuUserVo();
            vo.setId(GUIDUtil.getGUID());
            vo.setOpenId(openId);
            vo.setStatus("1");
            vo.setCreateUserId("0000-0000");
            vo.setCreateUserName("system");
            vo.setCreateDate(DateUtil.getNowDate());
            vo.setLastModifyDate(DateUtil.getNowDate());
            int count = cuUserDao.insertSelective(vo);
            if (count == 0) {
                return null;
            }
            vo = cuUserDao.findByOpenId(openId, appid);
        }
        //统计数据
        BigDecimal accDonateAmount = cuDonateListService.getAccDonateAmount(vo.getOpenId());
        int accDonateCount = cuDonateListService.getAccDonateCount(vo.getOpenId());
        vo.setAccDonateAmount(accDonateAmount);
        vo.setAccDonateCount(accDonateCount);

        return vo;
    }


    /**
     * 查询会员爱心记录
     *
     * @param openId 微信openid
     * @return
     * @throws Exception
     */
    @Override
    public PageResult<CuDonateListVo> findDonateList(String openId,
                                                     int start, int limit, String orderBy) throws Exception {
        CuDonateListQVo condition = new CuDonateListQVo();
        condition.setOpenId(openId);

        return cuDonateListService.findCuDonateListList(condition, start, limit, orderBy);
    }

    /**
     * 查询会员 -- 我的求助
     *
     * @param openId  微信openid
     * @param start
     * @param limit
     * @param orderBy @return
     * @throws Exception
     */
    @Override
    public PageResult<CuProjectApplyVo> findMyProject(String openId, int start, int limit, String orderBy) throws Exception {
        CuProjectApplyQVo condition = new CuProjectApplyQVo();
        condition.setApplyOpenId(openId);

        PageResult<CuProjectApplyVo> data = cuProjectApplyService.findCuProjectApplyList(condition, start, limit, orderBy);
        List<CuProjectApplyVo> list = data.getRows();
        long balanceDays = 0;
        for (CuProjectApplyVo vo : list) {
            if (null != vo.getEndDate()) {
                balanceDays = getDiffDays(DateUtil.getNowDate(), vo.getEndDate());
            }
            vo.setBalanceDays(balanceDays < 0 ? 0 : balanceDays);
        }

        return data;
    }


    /**
     * 计算项目剩余天数
     *
     * @param projectVo
     * @return
     */
    private CuProjectVo setBalanceDays(CuProjectVo projectVo) {
        long balanceDays = 0;
        if (null != projectVo.getEndDate()) {
            balanceDays = getDiffDays(DateUtil.getNowDate(), projectVo.getEndDate());
        }
        projectVo.setBalanceDays(balanceDays < 0 ? 0 : balanceDays);

        return projectVo;
    }

    /**
     * 计算2个date时间的 差距天数
     *
     * @param begin
     * @param end
     * @return
     */
    private long getDiffDays(Date begin, Date end) {
        long between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
        return between / (24 * 3600);
    }

}
