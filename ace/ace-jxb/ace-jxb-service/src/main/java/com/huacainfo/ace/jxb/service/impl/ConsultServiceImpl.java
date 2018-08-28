package com.huacainfo.ace.jxb.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.jxb.dao.ConsultDao;
import com.huacainfo.ace.jxb.dao.ConsultProductDao;
import com.huacainfo.ace.jxb.model.Consult;
import com.huacainfo.ace.jxb.model.ConsultProduct;
import com.huacainfo.ace.jxb.service.ConsultService;
import com.huacainfo.ace.jxb.service.CounselorService;
import com.huacainfo.ace.jxb.vo.ConsultQVo;
import com.huacainfo.ace.jxb.vo.ConsultVo;
import com.huacainfo.ace.jxb.vo.CounselorVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("consultService")
/**
 * @author: Arvin
 * @version: 2018-07-25
 * @Description: TODO(咨询师-咨询预约介绍)
 */
public class ConsultServiceImpl implements ConsultService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ConsultDao consultDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    @Autowired
    private CounselorService counselorService;
    @Autowired
    private ConsultProductDao consultProductDao;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(咨询师-咨询预约介绍分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <ConsultVo>
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public PageResult<ConsultVo> findConsultList(ConsultQVo condition, int start,
                                                 int limit, String orderBy) throws Exception {
        PageResult<ConsultVo> rst = new PageResult<>();
        List<ConsultVo> list = this.consultDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.consultDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertConsult
     * @Description: TODO(添加咨询师-咨询预约介绍)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public MessageResponse insertConsult(Consult o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getObjects())) {
            return new MessageResponse(1, "咨询对象不能为空！");
        }
        if (CommonUtils.isBlank(o.getField())) {
            return new MessageResponse(1, "擅长领域不能为空！");
        }
        if (CommonUtils.isBlank(o.getProcTime())) {
            return new MessageResponse(1, "每次咨询时长（分钟）不能为空！");
        }
        if (CommonUtils.isBlank(o.getCity())) {
            return new MessageResponse(1, "面对面咨询仅限城市不能为空！");
        }
        if (CommonUtils.isBlank(o.getInfo())) {
            return new MessageResponse(1, "预约须知不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "是否接收咨询不能为空！");
        }


        int temp = this.consultDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "咨询师-咨询预约介绍名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        this.consultDao.insertSelective(o);
        this.dataBaseLogService.log("添加咨询师-咨询预约介绍", "咨询师-咨询预约介绍", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加咨询师-咨询预约介绍完成！");
    }

    /**
     * @throws
     * @Title:insertConsult
     * @Description: TODO(添加咨询师-咨询预约介绍)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public MessageResponse modifyConsult(ConsultQVo consultQVo, UserProp userProp) throws Exception {

        consultQVo.setId(userProp.getUserId());
        if (CommonUtils.isBlank(consultQVo.getObjects())) {
            return new MessageResponse(1, "咨询对象不能为空！");
        }
        if (CommonUtils.isBlank(consultQVo.getField())) {
            return new MessageResponse(1, "擅长领域不能为空！");
        }
        if (CommonUtils.isBlank(consultQVo.getInfo())) {
            return new MessageResponse(1, "预约须知不能为空！");
        }
        Consult consult = consultDao.selectByPrimaryKey(consultQVo.getId());
        if (CommonUtils.isBlank(consult)) {
            consultQVo.setCreateDate(new Date());
            consultQVo.setStatus("1");
            this.consultDao.insertSelective(consultQVo);
            if (CommonUtils.isBlank(consultQVo.getTelephoneCon()) || CommonUtils.isBlank(consultQVo.getWecharCon()) || CommonUtils.isBlank(consultQVo.getFacetofaceCon())) {
                return new MessageResponse(1, "咨询方式必须填写一项！");
            }

            insertconsultProductList(consultQVo);
        } else {
            this.consultDao.updateByPrimaryKeySelective(consultQVo);
            this.consultProductDao.deleteByCounselorId(consultQVo.getId());
            insertconsultProductList(consultQVo);

        }
        this.dataBaseLogService.log("添加咨询师-咨询预约介绍", "咨询师-咨询预约介绍", "",
                consultQVo.getId(), consultQVo.getId(), userProp);

        return new MessageResponse(0, "添加咨询师-咨询预约介绍完成！");
    }


    private void insertconsultProductList(ConsultQVo consultQVo) throws Exception {
        ConsultProduct consultProduct = new ConsultProduct();
        consultProduct.setCounselorId(consultQVo.getId());
        consultProduct.setCreateDate(new Date());
        consultProduct.setStatus("1");
        if (!CommonUtils.isBlank(consultQVo.getTelephoneCon())) {
            consultProduct.setType("1");
            consultProduct.setPrice(consultQVo.getTelephoneCon());
            consultProduct.setId(GUIDUtil.getGUID());
            this.consultProductDao.insertSelective(consultProduct);
        }

        if (!CommonUtils.isBlank(consultQVo.getWecharCon())) {
            consultProduct.setType("2");
            consultProduct.setPrice(consultQVo.getWecharCon());
            consultProduct.setId(GUIDUtil.getGUID());
            this.consultProductDao.insertSelective(consultProduct);
        }

        if (!CommonUtils.isBlank(consultQVo.getFacetofaceCon())) {
            consultProduct.setType("3");
            consultProduct.setPrice(consultQVo.getFacetofaceCon());
            consultProduct.setId(GUIDUtil.getGUID());
            this.consultProductDao.insertSelective(consultProduct);
        }
    }


    /**
     * @throws
     * @Title:updateConsult
     * @Description: TODO(更新咨询师-咨询预约介绍)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public MessageResponse updateConsult(Consult o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getObjects())) {
            return new MessageResponse(1, "咨询对象不能为空！");
        }
        if (CommonUtils.isBlank(o.getField())) {
            return new MessageResponse(1, "擅长领域不能为空！");
        }
        if (CommonUtils.isBlank(o.getProcTime())) {
            return new MessageResponse(1, "每次咨询时长（分钟）不能为空！");
        }
        if (CommonUtils.isBlank(o.getCity())) {
            return new MessageResponse(1, "面对面咨询仅限城市不能为空！");
        }
        if (CommonUtils.isBlank(o.getInfo())) {
            return new MessageResponse(1, "预约须知不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "是否接收咨询不能为空！");
        }


        this.consultDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更咨询师-咨询预约介绍", "咨询师-咨询预约介绍", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更咨询师-咨询预约介绍完成！");
    }

    /**
     * @throws
     * @Title:selectConsultByPrimaryKey
     * @Description: TODO(获取咨询师-咨询预约介绍)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Consult>
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public SingleResult<ConsultVo> selectConsultByPrimaryKey(String id) throws Exception {
        SingleResult<ConsultVo> rst = new SingleResult<>();
        rst.setValue(this.consultDao.selectVoByPrimaryKey(id));
        return rst;
    }


    @Override
    public ResultResponse getMyConsultInfo(String id) throws Exception {
        ConsultVo consultVo = this.consultDao.selectVoByPrimaryKey(id);
        if (CommonUtils.isBlank(consultVo)) {
            return new ResultResponse(ResultCode.SUCCESS, "预约信息", null);
        }
        List<ConsultProduct> list = this.consultProductDao.findListByCounselorId(id);
        consultVo.setProductList(list);
        return new ResultResponse(ResultCode.SUCCESS, "预约信息", consultVo);
    }

    /**
     * @throws
     * @Title:deleteConsultByConsultId
     * @Description: TODO(删除咨询师-咨询预约介绍)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public MessageResponse deleteConsultByConsultId(String id, UserProp userProp) throws
            Exception {
        this.consultDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除咨询师-咨询预约介绍", "咨询师-咨询预约介绍",
                String.valueOf(id),
                String.valueOf(id), "咨询师-咨询预约介绍", userProp);
        return new MessageResponse(0, "咨询师-咨询预约介绍删除完成！");
    }

    /**
     * 获取咨询师主页
     *
     * @param counselorId 咨询师id
     * @return ResultResponse
     */
    @Override
    public ResultResponse getCounselorDetail(String counselorId) throws Exception {
        CounselorVo counselorVo = counselorService.selectCounselorByPrimaryKey(counselorId).getValue();
        if (null == counselorVo) {
            return new ResultResponse(ResultCode.FAIL, "咨询师资料丢失");
        }
        ConsultVo consultVo = selectConsultByPrimaryKey(counselorId).getValue();
        if (null == consultVo) {
            return new ResultResponse(ResultCode.FAIL, "未设置咨询产品信息");
        }
        consultVo.setProductList(consultProductDao.findPList(counselorId));

        Map<String, Object> rtnMap = new HashMap<>();
        rtnMap.put("counselorVo", counselorVo);
        rtnMap.put("consultVo", consultVo);
        return new ResultResponse(ResultCode.SUCCESS, "咨询成功", rtnMap);
    }

    /**
     * 咨询师 在线/离线
     *
     * @param counselorId  咨询师id
     * @param onlineStatus 在线状态 0-离线 1-在线
     * @return ResultResponse
     */
    @Override
    public ResultResponse onOff(String counselorId, String onlineStatus) {
        Consult vo = consultDao.selectByPrimaryKey(counselorId);
        if (null == vo) {
            return new ResultResponse(ResultCode.FAIL, "咨询师资料丢失");
        }

        vo.setOnlineStatus(onlineStatus);
        consultDao.updateByPrimaryKeySelective(vo);

        return new ResultResponse(ResultCode.SUCCESS, "操作成功");
    }

    /**
     * 调整咨询师 - 是否接受咨询
     *
     * @param counselorId 咨询师ID
     * @param state       是否接收咨询 0-否 1-是
     * @param curUserProp
     * @return
     * @throws Exception
     */
    @Override
    public MessageResponse updateState(String counselorId, String state, UserProp curUserProp) {
        Consult vo = consultDao.selectByPrimaryKey(counselorId);
        if (null == vo) {
            return new MessageResponse(ResultCode.FAIL, "没有设置预约资料");
        }
        vo.setStatus(state);
        consultDao.updateByPrimaryKeySelective(vo);

        return new MessageResponse(ResultCode.SUCCESS, "操作成功");
    }

}
