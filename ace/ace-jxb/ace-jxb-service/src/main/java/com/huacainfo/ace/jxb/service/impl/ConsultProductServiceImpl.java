package com.huacainfo.ace.jxb.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.jxb.dao.ConsultProductDao;
import com.huacainfo.ace.jxb.model.ConsultProduct;
import com.huacainfo.ace.jxb.service.ConsultProductService;
import com.huacainfo.ace.jxb.vo.ConsultProductQVo;
import com.huacainfo.ace.jxb.vo.ConsultProductVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("consultProductService")
/**
 * @author: Arvin
 * @version: 2018-07-25
 * @Description: TODO(咨询预约产品)
 */
public class ConsultProductServiceImpl implements ConsultProductService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ConsultProductDao consultProductDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(咨询预约产品分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <ConsultProductVo>
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public PageResult<ConsultProductVo> findConsultProductList(ConsultProductQVo condition, int start,
                                                               int limit, String orderBy) throws Exception {
        PageResult<ConsultProductVo> rst = new PageResult<>();
        List<ConsultProductVo> list = this.consultProductDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.consultProductDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertConsultProduct
     * @Description: TODO(添加咨询预约产品)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public MessageResponse insertConsultProduct(ConsultProduct o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCounselorId())) {
            return new MessageResponse(1, "所属咨询师不能为空！");
        }
        if (CommonUtils.isBlank(o.getType())) {
            return new MessageResponse(1, "咨询类型(1语音2视频3面对面)不能为空！");
        }
        if (CommonUtils.isBlank(o.getPrice())) {
            return new MessageResponse(1, "价格不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "是否接收咨询不能为空！");
        }


        int temp = this.consultProductDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "咨询预约产品名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        this.consultProductDao.insertSelective(o);
        this.dataBaseLogService.log("添加咨询预约产品", "咨询预约产品", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加咨询预约产品完成！");
    }

    /**
     * @throws
     * @Title:updateConsultProduct
     * @Description: TODO(更新咨询预约产品)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public MessageResponse updateConsultProduct(ConsultProduct o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCounselorId())) {
            return new MessageResponse(1, "所属咨询师不能为空！");
        }
        if (CommonUtils.isBlank(o.getType())) {
            return new MessageResponse(1, "咨询类型(1语音2视频3面对面)不能为空！");
        }
        if (CommonUtils.isBlank(o.getPrice())) {
            return new MessageResponse(1, "价格不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "是否接收咨询不能为空！");
        }


        this.consultProductDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更咨询预约产品", "咨询预约产品", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更咨询预约产品完成！");
    }

    /**
     * @throws
     * @Title:selectConsultProductByPrimaryKey
     * @Description: TODO(获取咨询预约产品)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<ConsultProduct>
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public SingleResult<ConsultProductVo> selectConsultProductByPrimaryKey(String id) throws Exception {
        SingleResult<ConsultProductVo> rst = new SingleResult<>();
        rst.setValue(this.consultProductDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteConsultProductByConsultProductId
     * @Description: TODO(删除咨询预约产品)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public MessageResponse deleteConsultProductByConsultProductId(String id, UserProp userProp) throws
            Exception {
        this.consultProductDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除咨询预约产品", "咨询预约产品",
                String.valueOf(id),
                String.valueOf(id), "咨询预约产品", userProp);
        return new MessageResponse(0, "咨询预约产品删除完成！");
    }

}
