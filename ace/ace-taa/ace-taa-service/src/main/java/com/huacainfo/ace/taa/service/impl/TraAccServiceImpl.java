package com.huacainfo.ace.taa.service.impl;


import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import com.huacainfo.ace.common.result.ListResult;
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
import com.huacainfo.ace.taa.dao.TraAccDao;
import com.huacainfo.ace.taa.model.TraAcc;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.taa.service.TraAccService;
import com.huacainfo.ace.taa.vo.TraAccVo;
import com.huacainfo.ace.taa.vo.TraAccQVo;

@Service("traAccService")
/**
 * @author: 陈晓克
 * @version: 2019-01-10
 * @Description: TODO(事故)
 */
public class TraAccServiceImpl implements TraAccService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TraAccDao traAccDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(事故分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <TraAccVo>
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    @Override
    public PageResult<TraAccVo> findTraAccList(TraAccQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<TraAccVo> rst = new PageResult<>();
        List<TraAccVo> list = this.traAccDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.traAccDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertTraAcc
     * @Description: TODO(添加事故)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    @Override
    public MessageResponse insertTraAcc(TraAcc o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getLatitude())) {
            return new MessageResponse(1, "纬度不能为空！");
        }
        if (CommonUtils.isBlank(o.getLongitude())) {
            return new MessageResponse(1, "经度不能为空！");
        }

        if (CommonUtils.isBlank(o.getWeather())) {
            return new MessageResponse(1, "天气不能为空！");
        }
        if (CommonUtils.isBlank(o.getVehicleType())) {
            return new MessageResponse(1, "车型不能为空！");
        }
        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        if(CommonUtils.isBlank(o.getAreaCode())){
            o.setAreaCode(userProp.getAreaCode());
        }
        this.traAccDao.insert(o);
        this.dataBaseLogService.log("添加事故", "事故", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateTraAcc
     * @Description: TODO(更新事故)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    @Override
    public MessageResponse updateTraAcc(TraAcc o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getLatitude())) {
            return new MessageResponse(1, "纬度不能为空！");
        }
        if (CommonUtils.isBlank(o.getLongitude())) {
            return new MessageResponse(1, "经度不能为空！");
        }
        if (CommonUtils.isBlank(o.getWeather())) {
            return new MessageResponse(1, "天气不能为空！");
        }
        if (CommonUtils.isBlank(o.getVehicleType())) {
            return new MessageResponse(1, "车型不能为空！");
        }
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.traAccDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更事故", "事故", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectTraAccByPrimaryKey
     * @Description: TODO(获取事故)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<TraAcc>
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    @Override
    public SingleResult<TraAccVo> selectTraAccByPrimaryKey(String id) throws Exception {
        SingleResult<TraAccVo> rst = new SingleResult<>();
        rst.setValue(this.traAccDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteTraAccByTraAccId
     * @Description: TODO(删除事故)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    @Override
    public MessageResponse deleteTraAccByTraAccId(String id, UserProp userProp) throws
            Exception {
        this.traAccDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除事故", "事故", id, id,
                "事故", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核事故)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {


        dataBaseLogService.log("审核事故", "事故", id, id,
                "事故", userProp);
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
     * @author: 陈晓克
     * @version: 2019-01-10
     */

    @Override
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
        int i = 1;
        for (Map<String, Object> row : list) {
            TraAcc o = new TraAcc();
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
            if (CommonUtils.isBlank(o.getLatitude())) {
                return new MessageResponse(1, "纬度不能为空！");
            }
            if (CommonUtils.isBlank(o.getLongitude())) {
                return new MessageResponse(1, "经度不能为空！");
            }
            if (CommonUtils.isBlank(o.getAreaCode())) {
                return new MessageResponse(1, "行政区划不能为空！");
            }
            if (CommonUtils.isBlank(o.getWeather())) {
                return new MessageResponse(1, "天气不能为空！");
            }
            if (CommonUtils.isBlank(o.getVehicleType())) {
                return new MessageResponse(1, "车型不能为空！");
            }
            if (CommonUtils.isBlank(o.getStatus())) {
                return new MessageResponse(1, "状态 不能为空！");
            }
            int t = this.traAccDao.isExit(o);
            if (t > 0) {
                this.traAccDao.updateByPrimaryKey(o);

            } else {
                this.traAccDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("事故导入", "事故", "", "rs.xls", "rs.xls", userProp);
        return new MessageResponse(0, "导入成功！");
    }


    /**
     * @throws
     * @Title:getList
     * @Description: TODO(条件查询数据)
     * @param: @param p
     * @param: @return
     * @param: @throws Exception
     * @return: ListResult<Map<String,Object>>
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    @Override
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception {
        ListResult<Map<String, Object>> rst = new ListResult<>();
        rst.setValue(this.traAccDao.getList(p));

        return rst;

    }


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String,Object>
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    @Override
    public Map<String, Object> getListByCondition(Map<String, Object> params) {
        Map<String, Object> rst = new HashMap<String, Object>();
        List<Map<String, Object>> list = this.traAccDao.getListByCondition(params);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除事故
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    @Override
    public MessageResponse deleteTraAccByTraAccIds(String[] id, UserProp userProp) throws Exception {
        this.traAccDao.deleteByPrimaryKeys(id);
        this.dataBaseLogService.log("批量删除事故", "事故", id[0], id[0], "事故", userProp);
        return new MessageResponse(0, "删除成功！");

    }

}
