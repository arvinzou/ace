package com.huacainfo.ace.fop.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.fop.dao.FopCompanyOrgDao;
import com.huacainfo.ace.fop.model.FopCompanyOrg;
import com.huacainfo.ace.fop.service.FopCompanyOrgService;
import com.huacainfo.ace.fop.vo.FopCompanyOrgQVo;
import com.huacainfo.ace.fop.vo.FopCompanyOrgVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("fopCompanyOrgService")
/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: TODO(企业管理)
 */
public class FopCompanyOrgServiceImpl implements FopCompanyOrgService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopCompanyOrgDao fopCompanyOrgDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(企业管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<FopCompanyOrgVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public PageResult<FopCompanyOrgVo> findFopCompanyOrgList(FopCompanyOrgQVo condition, int start,
                                                             int limit, String orderBy) throws Exception {
        PageResult<FopCompanyOrgVo> rst = new PageResult<FopCompanyOrgVo>();
        List<FopCompanyOrgVo> list = this.fopCompanyOrgDao.findList(condition, start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.fopCompanyOrgDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    @Override
    public List<FopCompanyOrgVo> findFopCompanyOrgListByCID(String companyId) throws Exception {
        return this.fopCompanyOrgDao.findListByCID(companyId);
    }

    /**
     * @throws
     * @Title:insertFopCompanyOrg
     * @Description: TODO(添加企业管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse insertFopCompanyOrg(FopCompanyOrg o, UserProp userProp)
            throws Exception {
        o.setId(GUIDUtil.getGUID());
        //o.setId(String.valueOf(new Date().getTime()));
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCompanyId())) {
            return new MessageResponse(1, "企业ID不能为空！");
        }


//        int temp = this.fopCompanyOrgDao.isExit(o);
//        if (temp > 0) {
//            return new MessageResponse(1, "企业管理名称重复！");
//        }
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.fopCompanyOrgDao.insertSelective(o);
        this.dataBaseLogService.log("添加企业管理", "企业管理", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "添加企业管理完成！");
    }

    /**
     * @throws
     * @Title:updateFopCompanyOrg
     * @Description: TODO(更新企业管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse updateFopCompanyOrg(FopCompanyOrg o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCompanyId())) {
            return new MessageResponse(1, "企业ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getCompanyOrgType())) {
            return new MessageResponse(1, "企业组织类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.fopCompanyOrgDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更企业管理", "企业管理", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "变更企业管理完成！");
    }

    /**
     * @throws
     * @Title:selectFopCompanyOrgByPrimaryKey
     * @Description: TODO(获取企业管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopCompanyOrg>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public SingleResult<FopCompanyOrgVo> selectFopCompanyOrgByPrimaryKey(String id) throws Exception {
        SingleResult<FopCompanyOrgVo> rst = new SingleResult<FopCompanyOrgVo>();
        rst.setValue(this.fopCompanyOrgDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteFopCompanyOrgByFopCompanyOrgId
     * @Description: TODO(删除企业管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse deleteFopCompanyOrgByFopCompanyOrgId(String id,
                                                                UserProp userProp) throws Exception {
        this.fopCompanyOrgDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除企业管理", "企业管理", String.valueOf(id),
                String.valueOf(id), "企业管理", userProp);
        return new MessageResponse(0, "企业管理删除完成！");
    }

    @Override
    public MessageResponse deleteFopCompanyOrgByCID(String cid, UserProp userProp) throws Exception {
        this.fopCompanyOrgDao.deleteByCID(cid);
        this.dataBaseLogService.log("删除企业管理", "企业管理", String.valueOf(cid),
                String.valueOf(cid), "企业管理", userProp);
        return new MessageResponse(0, "企业管理删除完成！");
    }
}
