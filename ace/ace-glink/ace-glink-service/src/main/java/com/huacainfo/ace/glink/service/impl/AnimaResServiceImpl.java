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

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.glink.dao.AnimaResDao;
import com.huacainfo.ace.glink.model.AnimaRes;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.glink.service.AnimaResService;
import com.huacainfo.ace.glink.vo.AnimaResVo;
import com.huacainfo.ace.glink.vo.AnimaResQVo;

@Service("animaResService")
/**
 * @author: luocan
 * @version: 2019-04-10
 * @Description: TODO(节目管理)
 */
public class AnimaResServiceImpl implements AnimaResService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AnimaResDao animaResDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(节目管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <AnimaResVo>
     * @author: luocan
     * @version: 2019-04-10
     */
    @Override
    public PageResult<AnimaResVo> findAnimaResList(AnimaResQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<AnimaResVo> rst = new PageResult<>();
        List<AnimaResVo> list = this.animaResDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.animaResDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertAnimaRes
     * @Description: TODO(添加节目管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-10
     */
    @Override
    public MessageResponse insertAnimaRes(AnimaRes o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "节目名称不能为空！");
        }
        int temp = this.animaResDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "节目管理名称重复！");
        }
        o.setId(GUIDUtil.getGUID());
        o.setCode(String.valueOf(GUIDUtil.getGUID().hashCode() & Integer.MAX_VALUE));
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.animaResDao.insert(o);
        this.dataBaseLogService.log("添加节目管理", "节目管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateAnimaRes
     * @Description: TODO(更新节目管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-10
     */
    @Override
    public MessageResponse updateAnimaRes(AnimaRes o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "节目名称不能为空！");
        }
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.animaResDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更节目管理", "节目管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectAnimaResByPrimaryKey
     * @Description: TODO(获取节目管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<AnimaRes>
     * @author: luocan
     * @version: 2019-04-10
     */
    @Override
    public SingleResult<AnimaResVo> selectAnimaResByPrimaryKey(String id) throws Exception {
        SingleResult<AnimaResVo> rst = new SingleResult<>();
        rst.setValue(this.animaResDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteAnimaResByAnimaResId
     * @Description: TODO(删除节目管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-10
     */
    @Override
    public MessageResponse deleteAnimaResByAnimaResId(String id, UserProp userProp) throws
            Exception {
        this.animaResDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除节目管理", "节目管理", id, id,
                "节目管理", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核节目管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-10
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {


        dataBaseLogService.log("审核节目管理", "节目管理", id, id,
                "节目管理", userProp);
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
     * @author: luocan
     * @version: 2019-04-10
     */

    @Override
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
        int i = 1;
        for (Map<String, Object> row : list) {
            AnimaRes o = new AnimaRes();
            CommonBeanUtils.copyMap2Bean(o, row);
            o.setCreateDate(new Date());
            o.setCreateUserId(userProp.getUserId());
            o.setCreateUserName(userProp.getName());
            o.setStatus("1");

            this.logger.info(o.toString());
            if (true) {
                return new MessageResponse(1, "行" + i + ",编号不能为空！");
            }
            if (CommonUtils.isBlank(o.getId())) {
                return new MessageResponse(1, "主键不能为空！");
            }
            if (CommonUtils.isBlank(o.getName())) {
                return new MessageResponse(1, "节目名称不能为空！");
            }
            if (CommonUtils.isBlank(o.getStatus())) {
                return new MessageResponse(1, "状态 不能为空！");
            }

            int t = this.animaResDao.isExit(o);
            if (t > 0) {
                this.animaResDao.updateByPrimaryKey(o);

            } else {
                this.animaResDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("节目管理导入", "节目管理", "",
                "rs.xls", "rs.xls", userProp);
        return new MessageResponse(0, "导入成功！");
    }


    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-10
     */
    @Override
    public MessageResponse updateStatus(String id, String status, UserProp userProp) throws
            Exception {
        this.animaResDao.updateStatus(id, status);
        this.dataBaseLogService.log("跟新状态", "节目管理", id, id,
                "节目管理", userProp);
        return new MessageResponse(0, "成功！");
    }

}
