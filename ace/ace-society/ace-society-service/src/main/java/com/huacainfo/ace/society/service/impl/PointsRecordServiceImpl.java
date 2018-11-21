package com.huacainfo.ace.society.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.society.constant.BisType;
import com.huacainfo.ace.society.constant.CoinConfigType;
import com.huacainfo.ace.society.constant.RegType;
import com.huacainfo.ace.society.dao.CoinConfigDao;
import com.huacainfo.ace.society.dao.PersonInfoDao;
import com.huacainfo.ace.society.dao.PointsRecordDao;
import com.huacainfo.ace.society.dao.SocietyOrgInfoDao;
import com.huacainfo.ace.society.model.PointsRecord;
import com.huacainfo.ace.society.service.PointsRecordService;
import com.huacainfo.ace.society.service.RegService;
import com.huacainfo.ace.society.vo.CoinConfigVo;
import com.huacainfo.ace.society.vo.CustomerVo;
import com.huacainfo.ace.society.vo.PointsRecordQVo;
import com.huacainfo.ace.society.vo.PointsRecordVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("pointsRecordService")
/**
 * @author: arvin
 * @version: 2018-09-28
 * @Description: TODO(积分流水)
 */
public class PointsRecordServiceImpl implements PointsRecordService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PointsRecordDao pointsRecordDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private SocietyOrgInfoDao societyOrgInfoDao;
    @Autowired
    private PersonInfoDao personInfoDao;
    @Autowired
    private CoinConfigDao coinConfigDao;
    @Autowired
    private RegService regService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(积分流水分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <PointsRecordVo>
     * @author: arvin
     * @version: 2018-09-28
     */
    @Override
    public PageResult<PointsRecordVo> findPointsRecordList(PointsRecordQVo condition, int start,
                                                           int limit, String orderBy) throws Exception {
        PageResult<PointsRecordVo> rst = new PageResult<>();
        List<PointsRecordVo> list = this.pointsRecordDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.pointsRecordDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertPointsRecord
     * @Description: TODO(添加积分流水)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: arvin
     * @version: 2018-09-28
     */
    @Override
    public MessageResponse insertPointsRecord(PointsRecord o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "用户ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getBisType())) {
            return new MessageResponse(1, "业务类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getPoints())) {
            return new MessageResponse(1, "变动量不能为空！");
        }


        int temp = this.pointsRecordDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "积分流水名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.pointsRecordDao.insert(o);
        this.dataBaseLogService.log("添加积分流水", "积分流水", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加积分流水完成！");
    }

    /**
     * @throws
     * @Title:updatePointsRecord
     * @Description: TODO(更新积分流水)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: arvin
     * @version: 2018-09-28
     */
    @Override
    public MessageResponse updatePointsRecord(PointsRecord o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "用户ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getBisType())) {
            return new MessageResponse(1, "业务类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getPoints())) {
            return new MessageResponse(1, "变动量不能为空！");
        }


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.pointsRecordDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更积分流水", "积分流水", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更积分流水完成！");
    }

    /**
     * @throws
     * @Title:selectPointsRecordByPrimaryKey
     * @Description: TODO(获取积分流水)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<PointsRecord>
     * @author: arvin
     * @version: 2018-09-28
     */
    @Override
    public SingleResult<PointsRecordVo> selectPointsRecordByPrimaryKey(String id) throws Exception {
        SingleResult<PointsRecordVo> rst = new SingleResult<>();
        rst.setValue(this.pointsRecordDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deletePointsRecordByPointsRecordId
     * @Description: TODO(删除积分流水)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: arvin
     * @version: 2018-09-28
     */
    @Override
    public MessageResponse deletePointsRecordByPointsRecordId(String id, UserProp userProp) throws Exception {
        this.pointsRecordDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除积分流水", "积分流水", id, id,
                "积分流水", userProp);
        return new MessageResponse(0, "积分流水删除完成！");
    }

    /**
     * 增加积分变化流水
     *
     * @param userId  用户ID
     * @param bisType 业务类型
     * @param bisId   业务单据id
     * @param points  变化量
     * @return ResultResponse
     */
    @Override
    public ResultResponse addPointsRecord(String userId, String bisType, String bisId, int points) {
        switch (bisType) {
            case BisType.POINTS_ORDER_CONSUME:
                points = -Math.abs(points);
                break;
            case BisType.POINTS_WELFARE_SPONSOR:
                points = Math.abs(points);
                break;
            case BisType.POINTS_WELFARE_PARTER_ADD:
                points = Math.abs(points);
                break;
            case BisType.POINTS_WELFARE_PARTER_SUB:
                points = -Math.abs(points);
                break;
            case BisType.POINTS_ORDINARY_SPONSOR:
                points = Math.abs(points);
                break;
            case BisType.POINTS_ORDINARY_PARTER_ADD:
                points = Math.abs(points);
                break;
            case BisType.POINTS_ORDINARY_PARTER_SUB:
                points = -Math.abs(points);
                break;
            case BisType.POINTS_CREATIVE_SPONSOR:
                points = Math.abs(points);
                break;
            case BisType.POINTS_CREATIVE_PARTER_ADD:
                points = Math.abs(points);
                break;
            case BisType.POINTS_CREATIVE_PARTER_SUB:
                points = -Math.abs(points);
                break;
            case BisType.POINTS_PARTY_SPONSOR:
                points = Math.abs(points);
                break;
            case BisType.POINTS_PARTY_PARTER_ADD:
                points = Math.abs(points);
                break;
            case BisType.POINTS_PARTY_PARTER_SUB:
                points = -Math.abs(points);
                break;
            case BisType.POINTS_BEHAVIOR:
                points = Math.abs(points);
                break;
            case BisType.POINTS_IDEA:
                points = Math.abs(points);
                break;
            case BisType.POINTS_LIVE:
                points = Math.abs(points);
                break;
            case BisType.POINTS_GROUP:
                points = Math.abs(points);
                break;
            default:
                break;
        }

        PointsRecord record = new PointsRecord();
        record.setId(GUIDUtil.getGUID());
        record.setUserId(userId);
        record.setBisType(bisType);
        record.setBisId(bisId);
        record.setPoints(points);
        record.setStatus("1");
        record.setCreateDate(DateUtil.getNowDate());
        record.setCreateUserId("system");
        record.setCreateUserName("system");
        pointsRecordDao.insert(record);

        return new ResultResponse(ResultCode.SUCCESS, "记录成功");
    }

    /**
     * 加积分
     *
     * @param userId      用户unionId
     * @param coinCfgType 积分配置类型
     * @param bisId       业务单据ID
     * @return 变动结果
     */
    @Override
    public ResultResponse addPoints(String userId, String coinCfgType, String bisId) {
        //参数校验
        if (StringUtil.isEmpty(userId)) {
            return new ResultResponse(ResultCode.FAIL,
                    "积分变动失败[userId=" + userId + ", bisId=" + bisId + "]" +
                            " - " + "缺少用户ID");
        }
        if (StringUtil.isEmpty(coinCfgType)) {
            return new ResultResponse(ResultCode.FAIL,
                    "积分变动失败[userId=" + userId + ", bisId=" + bisId + "]" +
                            " - " + "缺少积分配置类型");
        }
        if (StringUtil.isEmpty(bisId)) {
            return new ResultResponse(ResultCode.FAIL,
                    "积分变动失败[userId=" + userId + ", bisId=" + bisId + "]" +
                            " - " + "缺少业务单据ID");
        }
        //解析业务类型
        String bisType = parseBisType(coinCfgType);
        if (StringUtil.isEmpty(bisType)) {
            return new ResultResponse(ResultCode.FAIL,
                    "积分变动失败[userId=" + userId + ", bisId=" + bisId + "]" + " - " + "解析业务类型失败");
        }
        //获取用户信息
        CustomerVo customer = regService.findByUserId(userId);
        if (customer == null) {
            return new ResultResponse(ResultCode.FAIL,
                    "积分变动失败[userId=" + userId + ", bisId=" + bisId + "]" + " - " + "用户信息不存在");
        }
        //获取金币配置信息
        CoinConfigVo coinConfigVo = coinConfigDao.selectVoByPrimaryKey(coinCfgType);
        int points = coinConfigVo.getHost();
        if (RegType.PERSON.equals(customer.getRegType())) {
            personInfoDao.addCoinSingle(userId, points);
        } else {
            societyOrgInfoDao.addCoin(userId, points);
        }
        //补增积分流水
        addPointsRecord(userId, BisType.POINTS_BEHAVIOR, bisId, points);

        return new ResultResponse(ResultCode.SUCCESS,
                "积分变动成功[userId=" + userId + ", bisId=" + bisId + "]");
    }

    /**
     * //解析业务类型
     *
     * @param coinCfgType 金币配置类型
     */
    private String parseBisType(String coinCfgType) {
        switch (coinCfgType) {
            /**
             * 公益活动
             */
            case CoinConfigType.WELFARE:
                return BisType.POINTS_WELFARE_SPONSOR;
            /**
             * 普及活动
             */
            case CoinConfigType.ORDINARY:
                return BisType.POINTS_ORDINARY_SPONSOR;
            /**
             * 创意活动
             */
            case CoinConfigType.CREATIVE:
                return BisType.POINTS_CREATIVE_SPONSOR;
            /**
             * 党建活动
             */
            case CoinConfigType.PARTY_BUILDING:
                return BisType.POINTS_PARTY_SPONSOR;
            /**
             * 随手拍
             */
            case CoinConfigType.TAKE_A_PHOTO:
                return BisType.POINTS_BEHAVIOR;
            /**
             * 我有点子
             */
            case CoinConfigType.IDEA:
                return BisType.POINTS_IDEA;
            /**
             * 直播
             */
            case CoinConfigType.LIVE:
                return BisType.POINTS_LIVE;
            /**
             * 邻里圈子
             */
            case CoinConfigType.GROUP:
                return BisType.POINTS_GROUP;
            default:
                return "";

        }
    }

}
