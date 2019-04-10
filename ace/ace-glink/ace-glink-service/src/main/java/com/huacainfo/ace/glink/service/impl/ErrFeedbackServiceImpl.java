package com.huacainfo.ace.glink.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.glink.dao.ErrFeedbackDao;
import com.huacainfo.ace.glink.model.ErrFeedback;
import com.huacainfo.ace.glink.service.ErrFeedbackService;
import com.huacainfo.ace.glink.vo.ErrFeedbackQVo;
import com.huacainfo.ace.glink.vo.ErrFeedbackVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("errFeedbackService")
/**
 * @author: Arvin
 * @version: 2019-04-10
 * @Description: TODO(故障报警)
 */
public class ErrFeedbackServiceImpl implements ErrFeedbackService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ErrFeedbackDao errFeedbackDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(故障报警分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <ErrFeedbackVo>
     * @author: Arvin
     * @version: 2019-04-10
     */
    @Override
    public PageResult<ErrFeedbackVo> findErrFeedbackList(ErrFeedbackQVo condition,
                                                         int start, int limit, String orderBy) throws Exception {
        PageResult<ErrFeedbackVo> rst = new PageResult<>();
        List<ErrFeedbackVo> list = this.errFeedbackDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.errFeedbackDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertErrFeedback
     * @Description: TODO(添加故障报警)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-10
     */
    @Override
    public MessageResponse insertErrFeedback(ErrFeedback o, UserProp userProp) throws Exception {
        String guid = StringUtil.isEmpty(o.getId()) ? GUIDUtil.getGUID() : o.getId();
        o.setId(guid);
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getDeviceCode())) {
            return new MessageResponse(1, "设备编号不能为空！");
        }

//        int temp = this.errFeedbackDao.isExist(o);
//        if (temp > 0) {
//            return new MessageResponse(1, "故障报警名称重复！");
//        }

        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.errFeedbackDao.insert(o);
        this.dataBaseLogService.log("添加故障报警", "故障报警", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateErrFeedback
     * @Description: TODO(更新故障报警)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-10
     */
    @Override
    public MessageResponse updateErrFeedback(ErrFeedback o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getDeviceCode())) {
            return new MessageResponse(1, "设备编号不能为空！");
        }

        o.setStatus(StringUtil.isEmpty(o.getStatus()) ? "1" : o.getStatus());
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.errFeedbackDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更故障报警", "故障报警", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectErrFeedbackByPrimaryKey
     * @Description: TODO(获取故障报警)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<ErrFeedback>
     * @author: Arvin
     * @version: 2019-04-10
     */
    @Override
    public SingleResult<ErrFeedbackVo> selectErrFeedbackByPrimaryKey(String id) throws Exception {
        SingleResult<ErrFeedbackVo> rst = new SingleResult<>();
        rst.setValue(this.errFeedbackDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteErrFeedbackByErrFeedbackId
     * @Description: TODO(删除故障报警)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-10
     */
    @Override
    public MessageResponse deleteErrFeedbackByErrFeedbackId(String id, UserProp userProp) throws
            Exception {
        this.errFeedbackDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除故障报警", "故障报警", id, id,
                "故障报警", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核故障报警)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-10
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {


        dataBaseLogService.log("审核故障报警", "故障报警", id, id,
                "故障报警", userProp);
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
     * @version: 2019-04-10
     */

    @Override
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
        int i = 1;
        for (Map<String, Object> row : list) {
            ErrFeedback o = new ErrFeedback();
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
            if (CommonUtils.isBlank(o.getDeviceCode())) {
                return new MessageResponse(1, "设备编号不能为空！");
            }
            if (CommonUtils.isBlank(o.getStatus())) {
                return new MessageResponse(1, "状态 不能为空！");
            }

            int t = this.errFeedbackDao.isExist(o);
            if (t > 0) {
                this.errFeedbackDao.updateByPrimaryKey(o);

            } else {
                this.errFeedbackDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("故障报警导入", "故障报警", "",
                "rs.xls", "rs.xls", userProp);
        return new MessageResponse(0, "导入成功！");
    }


    /**
     * @throws
     * @Title:getList
     * @Description: TODO(条件查询数据)
     * @param: @param p
     * @param: @return
     * @param: @throws Exception
     * @return: ListResult
     * <Map
     * <String
     * ,Object>>
     * @author: Arvin
     * @version: 2019-04-10
     */
    @Override
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception {
        ListResult<Map<String, Object>> rst = new ListResult<>();
        rst.setValue(this.errFeedbackDao.getList(p));

        return rst;

    }


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map
     * <String
     * ,Object>
     * @author: Arvin
     * @version: 2019-04-10
     */
    @Override
    public Map<String, Object> getListByCondition(Map<String, Object> params) {
        Map<String, Object> rst = new HashMap<>();
        List<Map<String, Object>> list = this.errFeedbackDao.getListByCondition(params);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除故障报警 ）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-10
     */
    @Override
    public MessageResponse deleteErrFeedbackByErrFeedbackIds(String[] id, UserProp userProp)
            throws Exception {

        this.errFeedbackDao.deleteByPrimaryKeys(id);
        this.dataBaseLogService.log("批量删除故障报警", "故障报警", id[0],
                id[0], "故障报警", userProp);
        return new MessageResponse(0, "删除成功！");

    }


    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-10
     */
    @Override
    public MessageResponse updateStatus(String id, String status, UserProp userProp) throws
            Exception {
        this.errFeedbackDao.updateStatus(id, status);
        this.dataBaseLogService.log("跟新状态", "故障报警", id, id,
                "故障报警", userProp);
        return new MessageResponse(0, "成功！");
    }

}
