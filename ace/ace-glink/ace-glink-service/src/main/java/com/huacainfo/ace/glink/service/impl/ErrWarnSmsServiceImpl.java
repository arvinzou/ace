package com.huacainfo.ace.glink.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.glink.dao.ErrWarnSmsDao;
import com.huacainfo.ace.glink.model.ErrWarnSms;
import com.huacainfo.ace.glink.service.ErrWarnSmsService;
import com.huacainfo.ace.glink.vo.ErrWarnSmsQVo;
import com.huacainfo.ace.glink.vo.ErrWarnSmsVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("errWarnSmsService")
/**
 * @author: Arvin
 * @version: 2019-04-11
 * @Description: TODO(故障报警)
 */
public class ErrWarnSmsServiceImpl implements ErrWarnSmsService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ErrWarnSmsDao errWarnSmsDao;
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
     * @return: PageResult<ErrWarnSmsVo>
     * @author: Arvin
     * @version: 2019-04-11
     */
    @Override
    public PageResult<ErrWarnSmsVo> findErrWarnSmsList(ErrWarnSmsQVo condition,
                                                       int start, int limit, String orderBy) throws Exception {
        PageResult<ErrWarnSmsVo> rst = new PageResult<>();
        List<ErrWarnSmsVo> list = errWarnSmsDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.errWarnSmsDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertErrWarnSms
     * @Description: TODO(添加故障报警)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    @Override
    public MessageResponse insertErrWarnSms(ErrWarnSms o, UserProp userProp) throws Exception {
        String guid = StringUtil.isEmpty(o.getId()) ? GUIDUtil.getGUID() : o.getId();
        o.setId(guid);

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getSubareaCode())) {
            return new MessageResponse(1, "分区代码不能为空！");
        }
        if (CommonUtils.isBlank(o.getSmsName())) {
            return new MessageResponse(1, "模板名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getSmsContent())) {
            return new MessageResponse(1, "模板内容不能为空！");
        }

        int temp = this.errWarnSmsDao.isExist(o);
        if (temp > 0) {
            return new MessageResponse(1, "该分区短信模板已存在！");
        }


        o.setCreateDate(new Date());
        o.setStatus("1");
        this.errWarnSmsDao.insert(o);
        this.dataBaseLogService.log("添加故障报警", "故障报警", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateErrWarnSms
     * @Description: TODO(更新故障报警)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    @Override
    public MessageResponse updateErrWarnSms(ErrWarnSms o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getSubareaCode())) {
            return new MessageResponse(1, "分区代码不能为空！");
        }
        if (CommonUtils.isBlank(o.getSmsName())) {
            return new MessageResponse(1, "模板名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getSmsContent())) {
            return new MessageResponse(1, "模板内容不能为空！");
        }
        ErrWarnSmsVo vo = errWarnSmsDao.selectVoByPrimaryKey(o.getId());
        if (vo == null) {
            return new MessageResponse(1, "数据丢失！");
        }
        int temp = this.errWarnSmsDao.isExist(o);
        if (temp > 0) {
            return new MessageResponse(1, "该分区短信模板已存在！");
        }

        o.setStatus(vo.getStatus());
        o.setCreateDate(vo.getCreateDate());
        o.setUpdateDate(DateUtil.getNowDate());
        errWarnSmsDao.updateByPrimaryKey(o);
        dataBaseLogService.log("变更故障报警", "故障报警", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectErrWarnSmsByPrimaryKey
     * @Description: TODO(获取故障报警)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<ErrWarnSms>
     * @author: Arvin
     * @version: 2019-04-11
     */
    @Override
    public SingleResult<ErrWarnSmsVo> selectErrWarnSmsByPrimaryKey(String id) throws Exception {
        SingleResult<ErrWarnSmsVo> rst = new SingleResult<>();
        rst.setValue(this.errWarnSmsDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteErrWarnSmsByErrWarnSmsId
     * @Description: TODO(删除故障报警)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    @Override
    public MessageResponse deleteErrWarnSmsByErrWarnSmsId(String id, UserProp userProp) throws
            Exception {
        this.errWarnSmsDao.deleteByPrimaryKey(id);
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
     * @version: 2019-04-11
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
     * @version: 2019-04-11
     */

    @Override
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
        int i = 1;
        for (Map<String, Object> row : list) {
            ErrWarnSms o = new ErrWarnSms();
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
            if (CommonUtils.isBlank(o.getSubareaCode())) {
                return new MessageResponse(1, "分区代码不能为空！");
            }
            if (CommonUtils.isBlank(o.getSmsName())) {
                return new MessageResponse(1, "模板名称不能为空！");
            }
            if (CommonUtils.isBlank(o.getSmsContent())) {
                return new MessageResponse(1, "模板内容不能为空！");
            }

            int t = this.errWarnSmsDao.isExist(o);
            if (t > 0) {
                this.errWarnSmsDao.updateByPrimaryKey(o);

            } else {
                this.errWarnSmsDao.insert(o);
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
     * @return: ListResult<Map < String, Object>>
     * @author: Arvin
     * @version: 2019-04-11
     */
    @Override
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception {
        ListResult<Map<String, Object>> rst = new ListResult<>();
        rst.setValue(this.errWarnSmsDao.getList(p));

        return rst;

    }


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String, Object>
     * @author: Arvin
     * @version: 2019-04-11
     */
    @Override
    public Map<String, Object> getListByCondition(Map<String, Object> params) {
        Map<String, Object> rst = new HashMap<>();
        List<Map<String, Object>> list = this.errWarnSmsDao.getListByCondition(params);
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
     * @version: 2019-04-11
     */
    @Override
    public MessageResponse deleteErrWarnSmsByErrWarnSmsIds(String[] id, UserProp userProp)
            throws Exception {

        this.errWarnSmsDao.deleteByPrimaryKeys(id);
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
     * @version: 2019-04-11
     */
    @Override
    public MessageResponse updateStatus(String id, String status, UserProp userProp) throws
            Exception {
        this.errWarnSmsDao.updateStatus(id, status);
        this.dataBaseLogService.log("跟新状态", "故障报警", id, id,
                "故障报警", userProp);
        return new MessageResponse(0, "成功！");
    }

}
