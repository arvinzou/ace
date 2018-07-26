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
                start, start + limit, orderBy);
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

}
