package com.huacainfo.ace.taa.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.service.AuthorityService;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.taa.dao.OfficeAdminDao;
import com.huacainfo.ace.taa.model.OfficeAdmin;
import com.huacainfo.ace.taa.service.OfficeAdminService;
import com.huacainfo.ace.taa.vo.OfficeAdminQVo;
import com.huacainfo.ace.taa.vo.OfficeAdminVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("officeAdminService")
/**
 * @author: Arvin
 * @version: 2019-02-28
 * @Description: TODO(内勤人员)
 */
public class OfficeAdminServiceImpl implements OfficeAdminService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OfficeAdminDao officeAdminDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private AuthorityService authorityService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(内勤人员分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <OfficeAdminVo>
     * @author: Arvin
     * @version: 2019-02-28
     */
    @Override
    public PageResult<OfficeAdminVo> findOfficeAdminList(OfficeAdminQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<OfficeAdminVo> rst = new PageResult<>();
        List<OfficeAdminVo> list = this.officeAdminDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.officeAdminDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertOfficeAdmin
     * @Description: TODO(添加内勤人员)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-28
     */
    @Override
    public MessageResponse insertOfficeAdmin(OfficeAdmin o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "微信unionid不能为空！");
        }
        //unionid 转化为userId
        SingleResult<UserProp> rst = authorityService.getCurUserPropByOpenId(o.getUserId());
        if (rst.getStatus() == 1) {
            return rst;
        }
        o.setUserId(rst.getValue().getUserId());
        //限制重复
        int temp = this.officeAdminDao.isExist(o);
        if (temp > 0) {
            return new MessageResponse(1, "内勤人员名称重复！");
        }
        //组装数据
        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.officeAdminDao.insert(o);
        this.dataBaseLogService.log("添加内勤人员", "内勤人员", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateOfficeAdmin
     * @Description: TODO(更新内勤人员)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-28
     */
    @Override
    public MessageResponse updateOfficeAdmin(OfficeAdmin o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "微信unionid不能为空！");
        }
        if (CommonUtils.isBlank(o.getNickName())) {
            return new MessageResponse(1, "微信昵称不能为空！");
        }
        if (CommonUtils.isBlank(o.getHeaderImage())) {
            return new MessageResponse(1, "微信头像不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态 不能为空！");
        }


        this.officeAdminDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更内勤人员", "内勤人员", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectOfficeAdminByPrimaryKey
     * @Description: TODO(获取内勤人员)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<OfficeAdmin>
     * @author: Arvin
     * @version: 2019-02-28
     */
    @Override
    public SingleResult<OfficeAdminVo> selectOfficeAdminByPrimaryKey(String id) throws Exception {
        SingleResult<OfficeAdminVo> rst = new SingleResult<>();
        rst.setValue(this.officeAdminDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteOfficeAdminByOfficeAdminId
     * @Description: TODO(删除内勤人员)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-28
     */
    @Override
    public MessageResponse deleteByPrimaryKey(String id, UserProp userProp) throws
            Exception {
        this.officeAdminDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除内勤人员", "内勤人员", id, id,
                "内勤人员", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核内勤人员)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-28
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {


        dataBaseLogService.log("审核内勤人员", "内勤人员", id, id,
                "内勤人员", userProp);
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
     * @version: 2019-02-28
     */

    @Override
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
        int i = 1;
        for (Map<String, Object> row : list) {
            OfficeAdmin o = new OfficeAdmin();
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
            if (CommonUtils.isBlank(o.getUserId())) {
                return new MessageResponse(1, "微信unionid不能为空！");
            }
            if (CommonUtils.isBlank(o.getNickName())) {
                return new MessageResponse(1, "微信昵称不能为空！");
            }
            if (CommonUtils.isBlank(o.getHeaderImage())) {
                return new MessageResponse(1, "微信头像不能为空！");
            }
            if (CommonUtils.isBlank(o.getStatus())) {
                return new MessageResponse(1, "状态 不能为空！");
            }

            int t = this.officeAdminDao.isExist(o);
            if (t > 0) {
                this.officeAdminDao.updateByPrimaryKey(o);

            } else {
                this.officeAdminDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("内勤人员导入", "内勤人员", "", "rs.xls", "rs.xls", userProp);
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
     * @version: 2019-02-28
     */
    @Override
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception {
        ListResult<Map<String, Object>> rst = new ListResult<>();
        rst.setValue(this.officeAdminDao.getList(p));

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
     * @version: 2019-02-28
     */
    @Override
    public Map<String, Object> getListByCondition(Map<String, Object> params) {
        Map<String, Object> rst = new HashMap<String, Object>();
        List<Map<String, Object>> list = this.officeAdminDao.getListByCondition(params);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除内勤人员 ）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-28
     */
    @Override
    public MessageResponse deleteOfficeAdminByOfficeAdminIds(String[] id, UserProp userProp) throws Exception {

        this.officeAdminDao.deleteByPrimaryKeys(id);
        this.dataBaseLogService.log("批量删除内勤人员", "内勤人员", id[0], id[0], "内勤人员", userProp);
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
     * @version: 2019-02-28
     */
    @Override
    public MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception {
        this.officeAdminDao.updateStatus(id, status);
        this.dataBaseLogService.log("跟新状态", "内勤人员", id, id, "内勤人员", userProp);
        return new MessageResponse(0, "成功！");
    }

    /**
     * @param nickname 微信昵称
     * @Description: 根据昵称查询微信用户
     */
    @Override
    public List<Map<String, Object>> selectList(String nickname) {
        return officeAdminDao.selectList(nickname);
    }

}
