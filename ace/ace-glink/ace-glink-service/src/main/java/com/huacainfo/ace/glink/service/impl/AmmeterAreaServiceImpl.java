package com.huacainfo.ace.glink.service.impl;


import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.glink.dao.AmmeterAreaDao;
import com.huacainfo.ace.glink.model.AmmeterArea;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.glink.service.AmmeterAreaService;
import com.huacainfo.ace.glink.vo.AmmeterAreaVo;
import com.huacainfo.ace.glink.vo.AmmeterAreaQVo;

@Service("ammeterAreaService")
/**
 * @author: Arvin
 * @version: 2019-04-15
 * @Description: TODO(故障报警 - 短信 - 调度映射关系)
 */
public class AmmeterAreaServiceImpl implements AmmeterAreaService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AmmeterAreaDao ammeterAreaDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(故障报警 - 短信 - 调度映射关系分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <AmmeterAreaVo>
     * @author: Arvin
     * @version: 2019-04-15
     */
    @Override
    public PageResult
            <AmmeterAreaVo> findAmmeterAreaList(AmmeterAreaQVo condition,
                                                int start, int limit, String orderBy) throws Exception {
        PageResult
                <AmmeterAreaVo> rst = new PageResult<>();
        List
                <AmmeterAreaVo> list = ammeterAreaDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.ammeterAreaDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertAmmeterArea
     * @Description: TODO(添加故障报警 - 短信 - 调度映射关系)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-15
     */
    @Override
    public MessageResponse insertAmmeterArea(AmmeterArea o, UserProp userProp) throws Exception {
        String guid = StringUtil.isEmpty(o.getId()) ? GUIDUtil.getGUID() : o.getId();
        o.setId(guid);

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getAreacode())) {
            return new MessageResponse(1, "不能为空！");
        }
        if (CommonUtils.isBlank(o.getPrice())) {
            return new MessageResponse(1, "电费单价不能为空！");
        }
        if (CommonUtils.isBlank(o.getPower())) {
            return new MessageResponse(1, "总耗电量不能为空！");
        }
        if (CommonUtils.isBlank(o.getPayment())) {
            return new MessageResponse(1, "电费不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态 不能为空！");
        }


        int temp = this.ammeterAreaDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "故障报警-短信-调度映射关系名称重复！");
        }


        o.setCreateDate(new Date());
        o.setStatus("1");
        this.ammeterAreaDao.insert(o);
        this.dataBaseLogService.log("添加故障报警-短信-调度映射关系", "故障报警-短信-调度映射关系", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateAmmeterArea
     * @Description: TODO(更新故障报警 - 短信 - 调度映射关系)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-15
     */
    @Override
    public MessageResponse updateAmmeterArea(AmmeterArea o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getAreacode())) {
            return new MessageResponse(1, "不能为空！");
        }
        if (CommonUtils.isBlank(o.getPrice())) {
            return new MessageResponse(1, "电费单价不能为空！");
        }
        if (CommonUtils.isBlank(o.getPower())) {
            return new MessageResponse(1, "总耗电量不能为空！");
        }
        if (CommonUtils.isBlank(o.getPayment())) {
            return new MessageResponse(1, "电费不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态 不能为空！");
        }

        this.ammeterAreaDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更故障报警-短信-调度映射关系", "故障报警-短信-调度映射关系", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectAmmeterAreaByPrimaryKey
     * @Description: TODO(获取故障报警 - 短信 - 调度映射关系)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<AmmeterArea>
     * @author: Arvin
     * @version: 2019-04-15
     */
    @Override
    public SingleResult<AmmeterAreaVo> selectAmmeterAreaByPrimaryKey(String id) throws Exception {
        SingleResult<AmmeterAreaVo> rst = new SingleResult<>();
        rst.setValue(this.ammeterAreaDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteAmmeterAreaByAmmeterAreaId
     * @Description: TODO(删除故障报警 - 短信 - 调度映射关系)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-15
     */
    @Override
    public MessageResponse deleteAmmeterAreaByAmmeterAreaId(String id, UserProp userProp) throws
            Exception {
        this.ammeterAreaDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除故障报警-短信-调度映射关系", "故障报警-短信-调度映射关系", id, id,
                "故障报警-短信-调度映射关系", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核故障报警 - 短信 - 调度映射关系)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-15
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {


        dataBaseLogService.log("审核故障报警-短信-调度映射关系", "故障报警-短信-调度映射关系", id, id,
                "故障报警-短信-调度映射关系", userProp);
        return new MessageResponse(0, "审核成功！");
    }


    /**
     * @throws
     * @Title:importXls
     * @Description: TODO(Excel导入资源数据)
     * @param: @param list
     * @param: @param userProp
     * @param: @return
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-15
     */

    @Override
    public MessageResponse importXls(List
                                             <Map
                                                     <String
                                                             , Object>> list, UserProp userProp) throws Exception {
        int i = 1;
        for (Map
                <String
                        , Object> row : list) {
            AmmeterArea o = new AmmeterArea();
            CommonBeanUtils.copyMap2Bean(o, row);
            o.setCreateDate(new Date());
            o.setStatus("1");

            this.logger.info(o.toString());
            if (true) {
                return new MessageResponse(1, "行" + i + ",编号不能为空！");
            }
            if (CommonUtils.isBlank(o.getId())) {
                return new MessageResponse(1, "主键不能为空！");
            }
            if (CommonUtils.isBlank(o.getAreacode())) {
                return new MessageResponse(1, "不能为空！");
            }
            if (CommonUtils.isBlank(o.getPrice())) {
                return new MessageResponse(1, "电费单价不能为空！");
            }
            if (CommonUtils.isBlank(o.getPower())) {
                return new MessageResponse(1, "总耗电量不能为空！");
            }
            if (CommonUtils.isBlank(o.getPayment())) {
                return new MessageResponse(1, "电费不能为空！");
            }
            if (CommonUtils.isBlank(o.getStatus())) {
                return new MessageResponse(1, "状态 不能为空！");
            }

            int t = this.ammeterAreaDao.isExit(o);
            if (t > 0) {
                this.ammeterAreaDao.updateByPrimaryKey(o);

            } else {
                this.ammeterAreaDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("故障报警-短信-调度映射关系导入", "故障报警-短信-调度映射关系", "",
                "rs.xls", "rs.xls", userProp);
        return new MessageResponse(0, "导入成功！");
    }

}
