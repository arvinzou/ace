package com.huacainfo.ace.jxb.service.impl;


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
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.jxb.constant.OrderCategory;
import com.huacainfo.ace.jxb.constant.OrderPayStatus;
import com.huacainfo.ace.jxb.constant.OrderPayType;
import com.huacainfo.ace.jxb.constant.PayStatus;
import com.huacainfo.ace.jxb.dao.*;
import com.huacainfo.ace.jxb.model.BaseOrder;
import com.huacainfo.ace.jxb.model.ConsultOrder;
import com.huacainfo.ace.jxb.model.JxbCallBackLog;
import com.huacainfo.ace.jxb.service.BaseOrderService;
import com.huacainfo.ace.jxb.service.BisMsgNoticeService;
import com.huacainfo.ace.jxb.service.OrderCalculationService;
import com.huacainfo.ace.jxb.vo.BaseOrderQVo;
import com.huacainfo.ace.jxb.vo.BaseOrderVo;
import com.huacainfo.ace.jxb.vo.ConsultProductVo;
import com.huacainfo.ace.jxb.vo.CourseVo;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.EvaluatTplService;
import com.huacainfo.ace.portal.service.UsersService;
import com.huacainfo.ace.portal.vo.EvaluatTplVo;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("baseOrderService")
/**
 * @author: Arvin
 * @version: 2018-07-25
 * @Description: TODO(统一订单)
 */
public class BaseOrderServiceImpl implements BaseOrderService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BaseOrderDao baseOrderDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private ConsultProductDao consultProductDao;
    @Autowired
    private ConsultOrderDao consultOrderDao;

    @Autowired
    private CounselorDao counselorDao;
    @Autowired
    private SqlSessionTemplate sqlSession;
    @Autowired
    private JxbCallBackLogDao jxbCallBackLogDao;
    @Autowired
    private OrderCalculationService orderCalculationService;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private EvaluatTplService evaluatTplService;
    @Autowired
    private BisMsgNoticeService bisMsgNoticeService;

    private SqlSession getSqlSession() {
        SqlSession session = sqlSession.getSqlSessionFactory().openSession(ExecutorType.REUSE);
        Configuration configuration = session.getConfiguration();
        configuration.setSafeResultHandlerEnabled(false);

        return session;
    }


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(统一订单分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <BaseOrderVo>
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override

    public PageResult<BaseOrderVo> findBaseOrderList(BaseOrderQVo condition, int start, int limit, String orderBy) throws Exception {
        //sql
        SqlSession session = getSqlSession();
        BaseOrderDao dao = session.getMapper(BaseOrderDao.class);
        //query
        PageResult<BaseOrderVo> rst = new PageResult<>();
        try {
            List<BaseOrderVo> list = dao.findList(condition, start, limit, orderBy);
            rst.setRows(list);
            if (start <= 1) {
                int allRows = baseOrderDao.findCount(condition);
                rst.setTotal(allRows);
            }
            return rst;
        } catch (Exception e) {
            logger.error("{}", e);
            if (session != null) {
                session.close();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return rst;
    }

    @Override
    public ResultResponse findBaseOrderListSencond(BaseOrderQVo condition, int page, int limit, String orderBy) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        //sql
        SqlSession session = getSqlSession();
        BaseOrderDao dao = session.getMapper(BaseOrderDao.class);
        List<BaseOrderVo> list = null;
        try {
            list = dao.findList(condition, (page - 1) * limit, limit, orderBy);

        } catch (Exception e) {
            if (session != null) {
                session.close();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        if (page < 2) {
            int allRows = this.baseOrderDao.findCount(condition);
            map.put("total", allRows);
        }
        map.put("list", list);
        return new ResultResponse(ResultCode.SUCCESS, "订单列表", map);
    }

    /**
     * @throws
     * @Title:insertBaseOrder
     * @Description: TODO(添加统一订单)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public MessageResponse insertBaseOrder(BaseOrder o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getConsumerId())) {
            return new MessageResponse(1, "客户主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCommodityId())) {
            return new MessageResponse(1, "商品主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "订单类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getBusinessId())) {
            return new MessageResponse(1, "商家主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCommodityName())) {
            return new MessageResponse(1, "商品名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getBusinessName())) {
            return new MessageResponse(1, "商家名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getAmount())) {
            return new MessageResponse(1, "数量不能为空！");
        }
        if (CommonUtils.isBlank(o.getPrice())) {
            return new MessageResponse(1, "单价不能为空！");
        }
        if (CommonUtils.isBlank(o.getPayMoney())) {
            return new MessageResponse(1, "付款金额不能为空！");
        }
        if (CommonUtils.isBlank(o.getPayStatus())) {
            return new MessageResponse(1, "支付状态不能为空！");
        }


        int temp = this.baseOrderDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "统一订单名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        this.baseOrderDao.insertSelective(o);
        this.dataBaseLogService.log("添加统一订单", "统一订单", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加统一订单完成！");
    }

    /**
     * @throws
     * @Title:updateBaseOrder
     * @Description: TODO(更新统一订单)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public MessageResponse updateBaseOrder(BaseOrder o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getConsumerId())) {
            return new MessageResponse(1, "客户主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCommodityId())) {
            return new MessageResponse(1, "商品主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "订单类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getBusinessId())) {
            return new MessageResponse(1, "商家主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCommodityName())) {
            return new MessageResponse(1, "商品名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getBusinessName())) {
            return new MessageResponse(1, "商家名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getAmount())) {
            return new MessageResponse(1, "数量不能为空！");
        }
        if (CommonUtils.isBlank(o.getPrice())) {
            return new MessageResponse(1, "单价不能为空！");
        }
        if (CommonUtils.isBlank(o.getPayMoney())) {
            return new MessageResponse(1, "付款金额不能为空！");
        }
        if (CommonUtils.isBlank(o.getPayStatus())) {
            return new MessageResponse(1, "支付状态不能为空！");
        }


        this.baseOrderDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更统一订单", "统一订单", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更统一订单完成！");
    }

    /**
     * @throws
     * @Title:selectBaseOrderByPrimaryKey
     * @Description: TODO(获取统一订单)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<BaseOrder>
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public SingleResult<BaseOrderVo> selectBaseOrderByPrimaryKey(String id) throws Exception {
        //sql
        SqlSession session = getSqlSession();
        BaseOrderDao dao = session.getMapper(BaseOrderDao.class);

        SingleResult<BaseOrderVo> rst = new SingleResult<>();
        try {
            BaseOrderVo vo = dao.selectVoByPrimaryKey(id);
            rst.setValue(vo);

            return rst;
        } catch (Exception e) {
            if (session != null) {
                session.close();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return rst;
    }

    /**
     * @throws
     * @Title:selectBaseOrderByPrimaryKey
     * @Description: TODO(获取统一订单)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<BaseOrder>
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public ResultResponse orderInfoByPrimaryKey(String id) throws Exception {
        BaseOrderVo order = this.baseOrderDao.selectVoByPrimaryKey(id);
//        Users user = usersService.selectUsersByPrimaryKey(order.getConsumerId()).getValue();
//        order.setConsumerName(user.getName());
        return new ResultResponse(ResultCode.SUCCESS, "订单详情", order);
    }

    /**
     * @throws
     * @Title:deleteBaseOrderByBaseOrderId
     * @Description: TODO(删除统一订单)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public MessageResponse deleteBaseOrderByBaseOrderId(String id, UserProp userProp) throws
            Exception {
        this.baseOrderDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除统一订单", "统一订单",
                String.valueOf(id),
                String.valueOf(id), "统一订单", userProp);
        return new MessageResponse(0, "统一订单删除完成！");
    }

    /**
     * 创建订单
     *
     * @param unionid
     * @param data    数据示例：
     *                {
     *                --订单基本情况  参考 BaseOrder.java
     *                "base": {
     *                "businessId": "businessId",
     *                "category": "1",
     *                "commodityId": "commodityId",
     *                "consumerId": "consumerId"
     *                },
     *                --预约详情 参考 ConsultOrder.java
     *                "consult": {
     *                "age": 1,
     *                "info": "Info",
     *                "name": "Name",
     *                "relationship": "Relationship",
     *                "secName": "SecName",
     *                "secTel": "SecTel",
     *                "sex": "Sex",
     *                "tel": "Tel18000"
     *                }
     *                }
     * @return ResultResponse
     */
    @Override
    public ResultResponse create(String unionid, String data) throws Exception {
        Map<String, Object> params = JsonUtil.toMap(data);
        BaseOrder base = JsonUtil.toObject(params.get("base").toString(), BaseOrder.class);
        base.setConsumerId(unionid);
        String orderCategory = base.getCategory();
        ResultResponse baseCheck = baseOrderCheck(base);
        if (baseCheck.getStatus() == ResultCode.FAIL) {
            return baseCheck;
        }
        //客户资料
        Users user = usersService.selectUsersByPrimaryKey(base.getConsumerId()).getValue();
        if (null == user) {
            return new ResultResponse(ResultCode.FAIL, "没有注册账号。");
        }
        //咨询订单
        if (orderCategory.equals(OrderCategory.CATEGORY_CONSULT)) {
            return createConsultOrder(user, base, params);
        }
        //课程订单
        else if (orderCategory.equals(OrderCategory.CATEGORY_COURSE)) {
            return createCourseOrder(user, base, params);
        }
        //心理评测订单
        else if (orderCategory.equals(OrderCategory.CATEGORY_EVALUATING)) {
            return createEvaluatingOrder(user, base, params);
        } else {
            return new ResultResponse(ResultCode.FAIL, "未知订单类型");
        }
    }

    /**
     * 创建 - 心理评测订单
     *
     * @param user
     * @param base
     * @param params
     * @return
     */
    private ResultResponse createEvaluatingOrder(Users user, BaseOrder base, Map<String, Object> params) throws Exception {
        EvaluatTplVo evaluat =
                (EvaluatTplVo) evaluatTplService.getEvaluatTplByPrimaryKey(base.getCommodityId()).getData();
        if (null == evaluat) {
            return new ResultResponse(ResultCode.FAIL, "非法商品资料");
        }
        //订单ID
        String orderId = GUIDUtil.getGUID();
        //支付金额保留2位小数，超出两位小数，则使用四舍五入
        BigDecimal price = evaluat.getDiscountCost();
        BigDecimal payMoney = price.multiply(new BigDecimal(base.getAmount())).setScale(2, BigDecimal.ROUND_HALF_UP);
        //入库主订单
        base.setId(orderId);
        base.setCommodityName(evaluat.getName());
        base.setBusinessName("近心帮");//卖家名称
        base.setBusinessId("1522029730650");//卖家ID
        base.setPrice(price);
        base.setPayMoney(payMoney);
        base.setPayStatus(PayStatus.NEW_ORDER);
        base.setCreateDate(DateUtil.getNowDate());
        baseOrderDao.insertSelective(base);

        //订单创建完成
        Map<String, Object> rtnMap = new HashMap<>();
        rtnMap.put("orderId", orderId);
        rtnMap.put("payMoney", payMoney);
        return new ResultResponse(ResultCode.SUCCESS, "订单创建成功", rtnMap);

    }

    /**
     * 创建课程订单
     *
     * @param user
     * @param base
     * @param params
     * @return
     */
    private ResultResponse createCourseOrder(Users user, BaseOrder base, Map<String, Object> params) {
        //
        CourseVo course = courseDao.selectVoByPrimaryKey(base.getCommodityId());
        if (null == course) {
            return new ResultResponse(ResultCode.FAIL, "非法商品资料");
        }
        //订单ID
        String orderId = GUIDUtil.getGUID();
        //支付金额保留2位小数，超出两位小数，则使用四舍五入
        BigDecimal price = course.getCost();
        BigDecimal payMoney = price.multiply(new BigDecimal(base.getAmount())).setScale(2, BigDecimal.ROUND_HALF_UP);
        //入库主订单
        base.setId(orderId);
        base.setBusinessId(course.getCreateUserId());//卖家ID
        base.setCommodityName(course.getName());
        base.setBusinessName(course.getCounselorName());
        base.setPrice(price);
        base.setPayMoney(payMoney);
        base.setPayStatus(PayStatus.NEW_ORDER);
        base.setCreateDate(DateUtil.getNowDate());
        baseOrderDao.insertSelective(base);

        //订单创建完成
        Map<String, Object> rtnMap = new HashMap<>();
        rtnMap.put("orderId", orderId);
        rtnMap.put("payMoney", payMoney);
        return new ResultResponse(ResultCode.SUCCESS, "订单创建成功", rtnMap);
    }

    /**
     * 记录回调日志
     *
     * @param callBackLog
     * @return
     */
    @Override
    public int insertCallBackLog(JxbCallBackLog callBackLog) {
        callBackLog.setId(GUIDUtil.getGUID());
        callBackLog.setCreateDate(DateUtil.getNowDate());
        return jxbCallBackLogDao.insertSelective(callBackLog);
    }

    /**
     * 付款完成逻辑
     *
     * @param callBackLog 支付日志
     * @param payType     支付方式
     * @return 处理结果
     */
    @Override
    public ResultResponse pay(JxbCallBackLog callBackLog, int payType) {
        if (null == callBackLog || StringUtil.isEmpty(callBackLog.getAttach())) {
            return new ResultResponse(ResultCode.FAIL, "回调日志信息异常");
        }
        String orderId = callBackLog.getAttach();
        BaseOrder order = baseOrderDao.selectByPrimaryKey(orderId);
        if (null == order) {
            return new ResultResponse(ResultCode.FAIL, "订单信息丢失");
        } else if (!OrderPayStatus.NEW_ORDER.equals(order.getPayStatus())) {
            return new ResultResponse(ResultCode.FAIL, "订单状态异常");
        }
        if (OrderPayType.WX_PAY == payType) {

        } else if (OrderPayType.OFF_LINE == payType) {

        }
        //完成支付
        order.setPayStatus(OrderPayStatus.PAID);
        order.setPayTime(DateUtil.getNowDate());
        baseOrderDao.updateByPrimaryKeySelective(order);
        //插入运算记录
        orderCalculationService.insertOrderCalculation(orderId, order.getBusinessId());
        //付款成功消息通知
        try {
            bisMsgNoticeService.paySuccess(order.getId());
        } catch (Exception e) {
            logger.error("付款成功消息发送失败[{}],Exception => \n{}", order.getId(), e);
        }


        return new ResultResponse(ResultCode.SUCCESS, "订单付款完成");
    }

    /**
     * 订单金额校验
     *
     * @param orderId   订单ID
     * @param payAmount 订单金额
     * @return
     */
    @Override
    public ResultResponse checkAmount(String orderId, String payAmount) {
        BaseOrder order = baseOrderDao.selectByPrimaryKey(orderId);
        if (null == order) {
            return new ResultResponse(ResultCode.FAIL, "订单数据丢失");
        }
        BigDecimal payMoney = order.getPayMoney();
        BigDecimal checkAmount = new BigDecimal(payAmount).setScale(2, BigDecimal.ROUND_HALF_UP);

        if (payMoney.compareTo(checkAmount) == 0) {
            return new ResultResponse(ResultCode.SUCCESS, "校验通过");
        }

        return new ResultResponse(ResultCode.FAIL, "付款金额不符");
    }

    /**
     * 商品购买查询
     *
     * @param commodityId 商品ID
     * @param consumerId  用户ID，可选
     * @return ResultResponse
     * @throws Exception
     */
    @Override
    public ResultResponse paidQuery(String commodityId, String consumerId) {
        BaseOrder params = new BaseOrder();
        params.setConsumerId(consumerId);
        params.setCommodityId(commodityId);

        int isExit = baseOrderDao.isExit(params);
        if (isExit <= 0) {
            return new ResultResponse(ResultCode.SUCCESS, "无购买记录", false);
        }

        return new ResultResponse(ResultCode.SUCCESS, "校验通过", true);
    }


    /**
     * 主订单数据校验
     *
     * @param base
     * @return
     */
    private ResultResponse baseOrderCheck(BaseOrder base) {
        if (StringUtil.isEmpty(base.getCategory())) {
            return new ResultResponse(ResultCode.FAIL, "缺少" + "订单类型");
        }
        if (StringUtil.isEmpty(base.getConsumerId())) {
            return new ResultResponse(ResultCode.FAIL, "缺少" + "客户主键");
        }
        if (StringUtil.isEmpty(base.getCommodityId())) {
            return new ResultResponse(ResultCode.FAIL, "缺少" + "商品主键");
        }
        if ((null == base.getAmount() ? 0 : base.getAmount()) <= 0) {
            return new ResultResponse(ResultCode.FAIL, "购买数量不合法");
        }

        return new ResultResponse(ResultCode.SUCCESS, "参数合法");

    }

    /**
     * 创建咨询预约订单
     *
     * @param user
     * @param base   订单基本信息
     * @param params 预约详情信息
     * @return ResultResponse {"orderId":"oid","payMoney":"99"}
     */
    private ResultResponse createConsultOrder(Users user, BaseOrder base, Map<String, Object> params) throws Exception {
        //预约详情
        ConsultOrder consult = null ==
                params.get("consult") ? null : JsonUtil.toObject(params.get("consult").toString(), ConsultOrder.class);
        if (null == consult) {
            return new ResultResponse(ResultCode.FAIL, "缺少预约详情资料");
        }
        //咨询产品资料
        ConsultProductVo product = consultProductDao.selectVoByPrimaryKey(base.getCommodityId());
        if (null == product) {
            return new ResultResponse(ResultCode.FAIL, "非法商品资料");
        }
        String orderId = GUIDUtil.getGUID();
        //入库预约订单
        ResultResponse check1 = consultOrderCheck(consult);
        if (check1.getStatus() == ResultCode.FAIL) {
            return check1;
        }
        consult.setId(orderId);
        consult.setStatus("1");
        consult.setCreateDate(DateUtil.getNowDate());
        consultOrderDao.insertSelective(consult);

        //支付金额保留2位小数，超出两位小数，则使用四舍五入
        BigDecimal price = product.getPrice();
        BigDecimal payMoney = price.multiply(new BigDecimal(base.getAmount())).setScale(2, BigDecimal.ROUND_HALF_UP);
        //入库主订单
        base.setId(orderId);
        base.setBusinessId(product.getCounselorId());//卖家ID
        base.setCommodityName("顾问在线产品");
        base.setBusinessName(product.getCounselorName());
        base.setPrice(price);
        base.setPayMoney(payMoney);
        base.setPayStatus(PayStatus.NEW_ORDER);
        base.setCreateDate(DateUtil.getNowDate());
        baseOrderDao.insertSelective(base);

        //订单创建完成
        Map<String, Object> rtnMap = new HashMap<>();
        rtnMap.put("orderId", orderId);
        rtnMap.put("payMoney", payMoney);
        return new ResultResponse(ResultCode.SUCCESS, "订单创建成功", rtnMap);
    }

    /**
     * 咨询订单数据校验
     *
     * @param consult
     * @return
     */
    private ResultResponse consultOrderCheck(ConsultOrder consult) {
        if (StringUtil.isEmpty(consult.getTel())) {
            return new ResultResponse(ResultCode.FAIL, "缺少" + "联系方式");
        }
        if (StringUtil.isEmpty(consult.getName())) {
            return new ResultResponse(ResultCode.FAIL, "缺少" + "姓名");
        }
        if (StringUtil.isEmpty(consult.getSex())) {
            return new ResultResponse(ResultCode.FAIL, "缺少" + "性别");
        }
        if (StringUtil.isEmpty(consult.getInfo())) {
            return new ResultResponse(ResultCode.FAIL, "缺少" + "问题类型及描述");
        }
        if (StringUtil.isEmpty(consult.getSecName())) {
            return new ResultResponse(ResultCode.FAIL, "缺少" + "紧急联系人");
        }
        if (StringUtil.isEmpty(consult.getRelationship())) {
            return new ResultResponse(ResultCode.FAIL, "缺少" + "与咨询人关系");
        }
        if (StringUtil.isEmpty(consult.getSecTel())) {
            return new ResultResponse(ResultCode.FAIL, "缺少" + "紧急联系电话");
        }

        return new ResultResponse(ResultCode.SUCCESS, "参数合法");
    }

}
