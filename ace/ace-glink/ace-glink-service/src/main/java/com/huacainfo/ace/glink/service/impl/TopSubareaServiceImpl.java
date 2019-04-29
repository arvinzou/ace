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
import com.huacainfo.ace.glink.dao.TopStationDao;
import com.huacainfo.ace.glink.dao.TopSubareaDao;
import com.huacainfo.ace.glink.model.TopSubarea;
import com.huacainfo.ace.glink.service.TopSubareaService;
import com.huacainfo.ace.glink.vo.TopStationQVo;
import com.huacainfo.ace.glink.vo.TopSubareaQVo;
import com.huacainfo.ace.glink.vo.TopSubareaVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("topSubareaService")
/**
 * @author: Arvin
 * @version: 2019-04-09
 * @Description: TODO(分区管理)
 */
public class TopSubareaServiceImpl implements TopSubareaService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TopSubareaDao topSubareaDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    @Autowired
    private TopStationDao topStationDao;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(分区管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<TopSubareaVo>
     * @author: Arvin
     * @version: 2019-04-09
     */
    @Override
    public PageResult<TopSubareaVo> findTopSubareaList(TopSubareaQVo condition,
                                                       int start, int limit, String orderBy) throws Exception {
        PageResult<TopSubareaVo> rst = new PageResult<>();
        List<TopSubareaVo> list = this.topSubareaDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.topSubareaDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertTopSubarea
     * @Description: TODO(添加分区管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-09
     */
    @Override
    public MessageResponse insertTopSubarea(TopSubarea o, UserProp userProp) throws Exception {
        String guid = StringUtil.isEmpty(o.getId()) ? GUIDUtil.getGUID() : o.getId();
        o.setId(guid);
        o.setCode(String.valueOf(GUIDUtil.getGUID().hashCode() & Integer.MAX_VALUE));
        if (CommonUtils.isBlank(o.getDistrict())) {
            return new MessageResponse(1, "行政区划不能为空！");
        }
        if (CommonUtils.isBlank(o.getCode())) {
            return new MessageResponse(1, "分区编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "分区名称不能为空！");
        }
        //分区编码重复
        int temp = this.topSubareaDao.isExist(o);
        if (temp > 0) {
            return new MessageResponse(1, "分区编码重复！");
        }


        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.topSubareaDao.insert(o);
        this.dataBaseLogService.log("添加分区管理", "分区管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateTopSubarea
     * @Description: TODO(更新分区管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-09
     */
    @Override
    public MessageResponse updateTopSubarea(TopSubarea o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getDistrict())) {
            return new MessageResponse(1, "行政区划不能为空！");
        }
        if (CommonUtils.isBlank(o.getCode())) {
            return new MessageResponse(1, "分区编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "分区名称不能为空！");
        }
        //分区编码重复
        int temp = this.topSubareaDao.isExist(o);
        if (temp > 0) {
            return new MessageResponse(1, "分区编码重复！");
        }
        TopSubareaVo obj = topSubareaDao.selectVoByPrimaryKey(o.getId());
        if (obj == null) {
            return new MessageResponse(1, "数据记录丢失！");
        }

        o.setStatus(obj.getStatus());
        o.setCreateDate(obj.getCreateDate());
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.topSubareaDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更分区管理", "分区管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectTopSubareaByPrimaryKey
     * @Description: TODO(获取分区管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<TopSubarea>
     * @author: Arvin
     * @version: 2019-04-09
     */
    @Override
    public SingleResult<TopSubareaVo> selectTopSubareaByPrimaryKey(String id) throws Exception {

        TopSubareaVo vo = topSubareaDao.selectVoByPrimaryKey(id);
        //setStationList
        TopStationQVo condition = new TopStationQVo();
        condition.setSubareaCode(vo.getCode());
        vo.setStationList(topStationDao.findList(condition, 0, 500, "t.name"));

        SingleResult<TopSubareaVo> rst = new SingleResult<>();
        rst.setValue(vo);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteTopSubareaByTopSubareaId
     * @Description: TODO(删除分区管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-09
     */
    @Override
    public MessageResponse deleteTopSubareaByTopSubareaId(String id, UserProp userProp) throws
            Exception {
        this.topSubareaDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除分区管理", "分区管理", id, id,
                "分区管理", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核分区管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-09
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {


        dataBaseLogService.log("审核分区管理", "分区管理", id, id,
                "分区管理", userProp);
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
     * @version: 2019-04-09
     */

    @Override
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
        int i = 1;
        for (Map<String, Object> row : list) {
            TopSubarea o = new TopSubarea();
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
            if (CommonUtils.isBlank(o.getDistrict())) {
                return new MessageResponse(1, "行政区划不能为空！");
            }
            if (CommonUtils.isBlank(o.getCode())) {
                return new MessageResponse(1, "分区编号不能为空！");
            }
            if (CommonUtils.isBlank(o.getName())) {
                return new MessageResponse(1, "分区名称不能为空！");
            }
            if (CommonUtils.isBlank(o.getStatus())) {
                return new MessageResponse(1, "状态 不能为空！");
            }

            int t = this.topSubareaDao.isExist(o);
            if (t > 0) {
                this.topSubareaDao.updateByPrimaryKey(o);

            } else {
                this.topSubareaDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("分区管理导入", "分区管理", "",
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
     * @version: 2019-04-09
     */
    @Override
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception {
        ListResult<Map<String, Object>> rst = new ListResult<>();
        rst.setValue(this.topSubareaDao.getList(p));

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
     * @version: 2019-04-09
     */
    @Override
    public Map<String, Object> getListByCondition(Map<String, Object> params) {
        Map<String, Object> rst = new HashMap<>();
        List<Map<String, Object>> list = this.topSubareaDao.getListByCondition(params);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除分区管理 ）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-09
     */
    @Override
    public MessageResponse deleteTopSubareaByTopSubareaIds(String[] id, UserProp userProp)
            throws Exception {

        this.topSubareaDao.deleteByPrimaryKeys(id);
        this.dataBaseLogService.log("批量删除分区管理", "分区管理", id[0],
                id[0], "分区管理", userProp);
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
     * @version: 2019-04-09
     */
    @Override
    public MessageResponse updateStatus(String id, String status, UserProp userProp) throws
            Exception {
        this.topSubareaDao.updateStatus(id, status);
        this.dataBaseLogService.log("跟新状态", "分区管理", id, id,
                "分区管理", userProp);
        return new MessageResponse(0, "成功！");
    }

}
