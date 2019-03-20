package com.huacainfo.ace.policeschool.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.ccb.util.MD5;
import com.huacainfo.ace.common.plugins.qyplugin.QYApiKit;
import com.huacainfo.ace.common.plugins.qyplugin.pojo.DeviceRst;
import com.huacainfo.ace.common.plugins.qyplugin.pojo.NewEmployeeRst;
import com.huacainfo.ace.common.plugins.qyplugin.pojo.base.ApiRst;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.*;
import com.huacainfo.ace.policeschool.constant.CommConstant;
import com.huacainfo.ace.policeschool.dao.QyCrmDao;
import com.huacainfo.ace.policeschool.model.QyCrm;
import com.huacainfo.ace.policeschool.service.QyCrmService;
import com.huacainfo.ace.policeschool.vo.QyCrmQVo;
import com.huacainfo.ace.policeschool.vo.QyCrmVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("qyCrmService")
/**
 * @author: ArvinZou
 * @version: 2019-03-19
 * @Description: TODO(数据上传)
 */
public class QyCrmServiceImpl implements QyCrmService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private QyCrmDao qyCrmDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(数据上传分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <QyCrmVo>
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    @Override
    public PageResult<QyCrmVo> findQyCrmList(QyCrmQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<QyCrmVo> rst = new PageResult<>();
        List<QyCrmVo> list = this.qyCrmDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.qyCrmDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertQyCrm
     * @Description: TODO(添加数据上传)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    @Override
    public MessageResponse insertQyCrm(QyCrm o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "系统用户编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getCcNum())) {
            return new MessageResponse(1, "群英cc号不能为空！");
        }


        int temp = this.qyCrmDao.isExist(o);
        if (temp > 0) {
            return new MessageResponse(1, "数据上传名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        this.qyCrmDao.insert(o);
        this.dataBaseLogService.log("添加数据上传", "数据上传", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateQyCrm
     * @Description: TODO(更新数据上传)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    @Override
    public MessageResponse updateQyCrm(QyCrm o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "系统用户编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getCcNum())) {
            return new MessageResponse(1, "群英cc号不能为空！");
        }


        this.qyCrmDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更数据上传", "数据上传", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectQyCrmByPrimaryKey
     * @Description: TODO(获取数据上传)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<QyCrm>
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    @Override
    public SingleResult<QyCrmVo> selectQyCrmByPrimaryKey(String id) throws Exception {
        SingleResult<QyCrmVo> rst = new SingleResult<>();
        rst.setValue(this.qyCrmDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteQyCrmByQyCrmId
     * @Description: TODO(删除数据上传)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    @Override
    public MessageResponse deleteQyCrmByQyCrmId(String id, UserProp userProp) throws
            Exception {
        this.qyCrmDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除数据上传", "数据上传", id, id,
                "数据上传", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核数据上传)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {


        dataBaseLogService.log("审核数据上传", "数据上传", id, id,
                "数据上传", userProp);
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
     * @author: ArvinZou
     * @version: 2019-03-19
     */

    @Override
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
        int i = 1;
        for (Map<String, Object> row : list) {
            QyCrm o = new QyCrm();
            CommonBeanUtils.copyMap2Bean(o, row);
            o.setCreateDate(new Date());

            this.logger.info(o.toString());
            if (true) {
                return new MessageResponse(1, "行" + i + ",编号不能为空！");
            }
            if (CommonUtils.isBlank(o.getId())) {
                return new MessageResponse(1, "主键不能为空！");
            }
            if (CommonUtils.isBlank(o.getUserId())) {
                return new MessageResponse(1, "系统用户编码不能为空！");
            }
            if (CommonUtils.isBlank(o.getCcNum())) {
                return new MessageResponse(1, "群英cc号不能为空！");
            }

            int t = this.qyCrmDao.isExist(o);
            if (t > 0) {
                this.qyCrmDao.updateByPrimaryKey(o);

            } else {
                this.qyCrmDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("数据上传导入", "数据上传", "", "rs.xls", "rs.xls", userProp);
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
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    @Override
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception {
        ListResult<Map<String, Object>> rst = new ListResult<>();
        rst.setValue(this.qyCrmDao.getList(p));

        return rst;

    }


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String, Object>
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    @Override
    public Map<String, Object> getListByCondition(Map<String, Object> params) {
        Map<String, Object> rst = new HashMap<String, Object>();
        List<Map<String, Object>> list = this.qyCrmDao.getListByCondition(params);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除数据上传 ）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    @Override
    public MessageResponse deleteQyCrmByQyCrmIds(String[] id, UserProp userProp) throws Exception {

        this.qyCrmDao.deleteByPrimaryKeys(id);
        this.dataBaseLogService.log("批量删除数据上传", "数据上传", id[0], id[0], "数据上传", userProp);
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
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    @Override
    public MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception {
        this.qyCrmDao.updateStatus(id, status);
        this.dataBaseLogService.log("跟新状态", "数据上传", id, id, "数据上传", userProp);
        return new MessageResponse(0, "成功！");
    }

    private QYApiKit getApi() {
        String apiAcct = PropertyUtil.getProperty("qy_api_acct");
        String apiKey = PropertyUtil.getProperty("qy_api_key");
        return QYApiKit.getInstance(apiAcct, apiKey);
    }

    /**
     * 获取群英已介入设备列表
     *
     * @param sn 可选参数
     * @return DeviceRst
     */
    @Override
    public DeviceRst getDevice(String sn) {
        QYApiKit api = getApi();
        String json = api.getDevice("");
        return JsonUtil.toObject(json, DeviceRst.class);
    }

    /**
     * 调用群英接口
     * 上传员工数据&同步数据到设备
     *
     * @param userId 用户ID
     * @param idStr  设备SN串
     * @return Map
     * @throws Exception
     */
    @Override
    public MessageResponse syncData(String userId, String idStr) {
        QyCrmVo vo = qyCrmDao.selectVoByPrimaryKey(userId);
        if (vo == null) {
            return new MessageResponse(ResultCode.FAIL, "数据丢失");
        }
        //流程分支
        switch (vo.getStatus()) {
            /**
             *  新增员工数据 && 上传员工数据至设备
             */
            case CommConstant.QY_SYN_STATE_UNDO:
                return addEmployee(vo, idStr);
            /**
             * 更新员工数据 && 上传员工数据至设备
             */
            case CommConstant.QY_SYN_STATE_SYNC_FAIL:
                return addEmployee(vo, idStr);
            /**
             * 上传员工数据至设备
             */
            case CommConstant.QY_SYN_STATE_SYNC_OK:
                return syncEmployee(vo, idStr);
            default:
                return new MessageResponse(ResultCode.FAIL, "数据状态有误");
        }
    }

    /***
     * 同步员工数据到设备
     */
    private MessageResponse syncEmployee(QyCrmVo vo, String idStr) {
        if (StringUtil.isEmpty(vo.getCcNum())) {
            return new MessageResponse(ResultCode.FAIL, "学员数据未上传");
        }
        QYApiKit api = getApi();
        String txt = api.syncEmployee(vo.getCcNum(), idStr);
        ApiRst rst = JsonUtil.toObject(txt, ApiRst.class);
        if (rst.getStatus() == 1) {
            vo.setRemark("数据同步完成");
            vo.setStatus(CommConstant.QY_SYN_STATE_SYNC_OK);
            return new MessageResponse(ResultCode.SUCCESS, "数据同步成功");
        } else {
            vo.setRemark("数据同步失败【" + rst.getError() + "】");
            vo.setStatus(CommConstant.QY_SYN_STATE_SYNC_FAIL);
            qyCrmDao.updateByPrimaryKey(vo);
            return new MessageResponse(ResultCode.SUCCESS, "数据同步失败【" + rst.getError() + "】");
        }
    }

    /***
     * 新增/更新员工数据 && 同步员工数据到设备
     */
    private MessageResponse addEmployee(QyCrmVo vo, String idStr) {
        //新增员工数据
        QYApiKit api = getApi();
        String idCard = vo.getIdCard();
        String pwd = idCard.substring(idCard.length() - 7, idCard.length() - 1);
        String txt = api.addEmployee(vo.getName(), MD5.md5Str(pwd), vo.getMobile(),
                "", "", "247630", "", idStr);
        NewEmployeeRst rst = JsonUtil.toObject(txt, NewEmployeeRst.class);
        //构建DB对象
        QyCrm item;
        boolean isNew = false;
        if (StringUtil.isEmpty(vo.getId())) {
            isNew = true;
            item = new QyCrm();
            item.setId(GUIDUtil.getGUID());
            item.setUserId(vo.getUserId());
            item.setCreateDate(DateUtil.getNowDate());
        } else {
            item = vo;
            item.setCreateDate(DateUtil.getNowDate());
        }
        //API结果分支导向
        if (rst.getStatus() == 1) {
            item.setCcNum(rst.getData().getAccount());
            item.setStatus(CommConstant.QY_SYN_STATE_SYNC_OK);
            if (isNew) {
                this.qyCrmDao.insert(item);
            } else {
                this.qyCrmDao.updateByPrimaryKey(item);
            }
            return new MessageResponse(ResultCode.SUCCESS, "数据同步成功");
        } else {
            item.setCcNum("");
            item.setStatus(CommConstant.QY_SYN_STATE_SYNC_FAIL);
            item.setRemark("数据同步失败【" + rst.getError() + "】");
            if (isNew) {
                this.qyCrmDao.insert(item);
            } else {
                this.qyCrmDao.updateByPrimaryKey(item);
            }
            return new MessageResponse(ResultCode.FAIL, "数据同步失败【" + rst.getError() + "】");
        }


    }

}
