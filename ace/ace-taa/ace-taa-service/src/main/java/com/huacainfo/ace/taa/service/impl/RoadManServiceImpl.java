package com.huacainfo.ace.taa.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.*;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.taa.dao.RoadDao;
import com.huacainfo.ace.taa.dao.RoadManDao;
import com.huacainfo.ace.taa.dao.RoadSectionDao;
import com.huacainfo.ace.taa.dao.TraAccDao;
import com.huacainfo.ace.taa.model.RoadMan;
import com.huacainfo.ace.taa.service.RoadManService;
import com.huacainfo.ace.taa.vo.RoadManQVo;
import com.huacainfo.ace.taa.vo.RoadManVo;
import com.huacainfo.ace.taa.vo.RoadSectionQVo;
import com.huacainfo.ace.taa.vo.TraAccQVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("roadManService")
/**
 * @author: 陈晓克
 * @version: 2019-01-04
 * @Description: TODO(路长)
 */
public class RoadManServiceImpl implements RoadManService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RoadManDao roadManDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private RoadSectionDao roadSectionDao;
    @Autowired
    private RoadDao roadDao;
    @Autowired
    private TraAccDao traAccDao;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(路长分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <RoadManVo>
     * @author: 陈晓克
     * @version: 2019-01-04
     */
    @Override
    public PageResult<RoadManVo> findRoadManList(RoadManQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<RoadManVo> rst = new PageResult<>();
        List<RoadManVo> list = this.roadManDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.roadManDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertRoadMan
     * @Description: TODO(添加路长)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-04
     */
    @Override
    public MessageResponse insertRoadMan(RoadMan o, UserProp userProp) throws Exception {


        if (CommonUtils.isBlank(o.getAreaCode())) {
            return new MessageResponse(1, "行政区划不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "姓名不能为空！");
        }
        if (CommonUtils.isBlank(o.getMobile())) {
            return new MessageResponse(1, "手机号不能为空！");
        }
        if (!CommonUtils.isValidMobile(o.getMobile())) {
            return new MessageResponse(1, "手机号格式非法！");
        }
        if (CommonUtils.isBlank(o.getStartName())) {
            return new MessageResponse(1, "管辖路段开始不能为空！");
        }
        if (CommonUtils.isBlank(o.getEndName())) {
            return new MessageResponse(1, "管辖路段截止不能为空！");
        }


        int temp = this.roadManDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "路长名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.roadManDao.insert(o);
        this.dataBaseLogService.log("添加路长", "路长", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateRoadMan
     * @Description: TODO(更新路长)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-04
     */
    @Override
    public MessageResponse updateRoadMan(RoadMan o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getAreaCode())) {
            return new MessageResponse(1, "行政区划不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "姓名不能为空！");
        }
        if (CommonUtils.isBlank(o.getMobile())) {
            return new MessageResponse(1, "手机号不能为空！");
        }
        if (!CommonUtils.isValidMobile(o.getMobile())) {
            return new MessageResponse(1, "手机号格式非法！");
        }
        if (CommonUtils.isBlank(o.getStartName())) {
            return new MessageResponse(1, "管辖路段开始不能为空！");
        }
        if (CommonUtils.isBlank(o.getEndName())) {
            return new MessageResponse(1, "管辖路段截止不能为空！");
        }

        o.setStatus(StringUtil.isEmpty(o.getStatus()) ? "1" : o.getStatus());
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.roadManDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更路长", "路长", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectRoadManByPrimaryKey
     * @Description: TODO(获取路长)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<RoadMan>
     * @author: 陈晓克
     * @version: 2019-01-04
     */
    @Override
    public SingleResult<RoadManVo> selectRoadManByPrimaryKey(String id) throws Exception {
        SingleResult<RoadManVo> rst = new SingleResult<>();
        rst.setValue(this.roadManDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRoadManByRoadManId
     * @Description: TODO(删除路长)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-04
     */
    @Override
    public MessageResponse deleteRoadManByRoadManId(String id, UserProp userProp) throws Exception {

        TraAccQVo c = new TraAccQVo();
        c.setRoadManId(id);
        int iTraAcc = traAccDao.findCount(c);
        if (iTraAcc > 0) {
            return new MessageResponse(ResultCode.FAIL, "存在事故绑定定关系，删除失败！");
        }
        RoadSectionQVo rs = new RoadSectionQVo();
        rs.setRoadMan(id);
        int iSection = roadSectionDao.findCount(rs);
        if (iSection > 0) {
            return new MessageResponse(ResultCode.FAIL, "存在路段绑定关系，删除失败！");
        }

        this.roadManDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除路长", "路长", id, id, "路长", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核路长)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-04
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {


        dataBaseLogService.log("审核路长", "路长", id, id,
                "路长", userProp);
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
     * @version: 2019-01-04
     */

    @Override
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
        int i = 1;
        for (Map<String, Object> row : list) {
            RoadMan o = new RoadMan();
            CommonBeanUtils.copyMap2Bean(o, row);
            o.setCreateDate(new Date());
            o.setCreateUserId(userProp.getUserId());
            o.setStatus("1");
            o.setId(GUIDUtil.getGUID());

            this.logger.info(o.toString());
            if (CommonUtils.isBlank(o.getAreaCode())) {
                return new MessageResponse(1, "行" + i + ",行政区划不能为空！");
            }
            if (CommonUtils.isBlank(o.getName())) {
                return new MessageResponse(1, "行" + i + ",姓名不能为空！");
            }
            if (CommonUtils.isBlank(o.getMobile())) {
                return new MessageResponse(1, "行" + i + ",手机号不能为空！");
            }
            if (CommonUtils.isBlank(o.getStartName())) {
                return new MessageResponse(1, "行" + i + ",管辖路段开始不能为空！");
            }
            if (CommonUtils.isBlank(o.getEndName())) {
                return new MessageResponse(1, "行" + i + ",管辖路段截止不能为空！");
            }

            int t = this.roadManDao.isExit(o);
            if (t > 0) {
                this.roadManDao.updateByPrimaryKey(o);

            } else {
                this.roadManDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("路长导入", "路长", "", "rs.xls", "rs.xls", userProp);
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
     * @version: 2019-01-04
     */
    @Override
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception {
        ListResult<Map<String, Object>> rst = new ListResult<>();
        rst.setValue(this.roadManDao.getList(p));
        return rst;
    }

    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(路长查询，用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String,Object>
     * @author: chenxiaoke
     * @version: 2019年1月04日 下午1:24:14
     */
    @Override
    public Map<String, Object> getListByCondition(Map<String, Object> params) {
        Map<String, Object> rst = new HashMap<String, Object>();
        List<Map<String, Object>> list = this.roadManDao.getListByCondition(params);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }

    /**
     * 获取所有路长花名册 - 通讯录形式
     *
     * @return ResultResponse
     * @throws Exception
     */
    @Override
    public ResultResponse findRoster(String roadManName) {
        //库中所有路长信息
        List<Map<String, Object>> list = roadManDao.findRoster(roadManName);
        //解析转换数据
        String name;
        String letter;
        for (Map<String, Object> item : list) {
            if (!CommonUtils.isBlank(item.get("name"))) {
                name = String.valueOf(item.get("name"));
                letter = CommonUtils.getPinYinHeadChar(name).substring(0, 1).toUpperCase();//A, B, C etc.
                item.put("index", letter);
            } else {
                continue;
            }
        }

        return new ResultResponse(ResultCode.SUCCESS, "success", list);
    }

}
