package com.huacainfo.ace.society.service.impl;


import java.util.Date;
import java.util.List;

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
import com.huacainfo.ace.society.dao.OrgAdminDao;
import com.huacainfo.ace.society.model.OrgAdmin;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.society.service.AuditRecordService;
import com.huacainfo.ace.society.service.OrgAdminService;
import com.huacainfo.ace.society.vo.OrgAdminVo;
import com.huacainfo.ace.society.vo.OrgAdminQVo;

@Service("orgAdminService")
/**
 * @author: Arvin
 * @version: 2018-11-19
 * @Description: TODO(组织管理者列表)
 */
public class OrgAdminServiceImpl implements OrgAdminService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OrgAdminDao orgAdminDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private AuditRecordService auditRecordService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(组织管理者列表分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <OrgAdminVo>
     * @author: Arvin
     * @version: 2018-11-19
     */
    @Override
    public PageResult
            <OrgAdminVo> findOrgAdminList(OrgAdminQVo condition, int start,
                                          int limit, String orderBy) throws Exception {
        PageResult
                <OrgAdminVo> rst = new PageResult<>();
        List
                <OrgAdminVo> list = this.orgAdminDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.orgAdminDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertOrgAdmin
     * @Description: TODO(添加组织管理者列表)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-19
     */
    @Override
    public MessageResponse insertOrgAdmin(OrgAdmin o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "用户ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getOrgId())) {
            return new MessageResponse(1, "组织ID不能为空！");
        }


        int temp = this.orgAdminDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "组织管理者列表名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        this.orgAdminDao.insert(o);
        this.dataBaseLogService.log("添加组织管理者列表", "组织管理者列表", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加组织管理者列表完成！");
    }

    /**
     * @throws
     * @Title:updateOrgAdmin
     * @Description: TODO(更新组织管理者列表)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-19
     */
    @Override
    public MessageResponse updateOrgAdmin(OrgAdmin o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "用户ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getOrgId())) {
            return new MessageResponse(1, "组织ID不能为空！");
        }


        this.orgAdminDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更组织管理者列表", "组织管理者列表", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更组织管理者列表完成！");
    }

    /**
     * @throws
     * @Title:selectOrgAdminByPrimaryKey
     * @Description: TODO(获取组织管理者列表)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<OrgAdmin>
     * @author: Arvin
     * @version: 2018-11-19
     */
    @Override
    public SingleResult
            <OrgAdminVo> selectOrgAdminByPrimaryKey(String id) throws Exception {
        SingleResult
                <OrgAdminVo> rst = new SingleResult<>();
        rst.setValue(this.orgAdminDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteOrgAdminByOrgAdminId
     * @Description: TODO(删除组织管理者列表)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-19
     */
    @Override
    public MessageResponse deleteOrgAdminByOrgAdminId(String id, UserProp userProp) throws
            Exception {
        this.orgAdminDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除组织管理者列表", "组织管理者列表", id, id,
                "组织管理者列表", userProp);
        return new MessageResponse(0, "组织管理者列表删除完成！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核组织管理者列表)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-19
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {


        dataBaseLogService.log("审核组织管理者列表", "组织管理者列表", id, id,
                "组织管理者列表", userProp);
        return new MessageResponse(0, "组织管理者列表审核完成！");
    }

}
