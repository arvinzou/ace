package com.huacainfo.ace.fop.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.fop.dao.FopCompanyContributionDao;
import com.huacainfo.ace.fop.model.FopCompanyContribution;
import com.huacainfo.ace.fop.service.FopCompanyContributionService;
import com.huacainfo.ace.fop.vo.FopCompanyContributionQVo;
import com.huacainfo.ace.fop.vo.FopCompanyContributionVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("fopCompanyContributionService")
/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: TODO(企业管理)
 */
public class FopCompanyContributionServiceImpl implements FopCompanyContributionService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopCompanyContributionDao fopCompanyContributionDao;
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
     * @return: PageResult<FopCompanyContributionVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public PageResult<FopCompanyContributionVo> findFopCompanyContributionList(FopCompanyContributionQVo condition, int start,
                                                                               int limit, String orderBy) throws Exception {
        PageResult<FopCompanyContributionVo> rst = new PageResult<FopCompanyContributionVo>();
        List<FopCompanyContributionVo> list = this.fopCompanyContributionDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.fopCompanyContributionDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    @Override
    public List<FopCompanyContributionVo> findFopCompanyContributionListByCID(String companyId) throws Exception {
        return this.fopCompanyContributionDao.findListCID(companyId);
    }

    /**
     * @throws
     * @Title:insertFopCompanyContribution
     * @Description: TODO(添加企业管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse insertFopCompanyContribution(FopCompanyContribution o, UserProp userProp)
            throws Exception {
        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCompanyId())) {
            return new MessageResponse(1, "企业ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getItemCode())) {
            return new MessageResponse(1, "属性编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getItemName())) {
            return new MessageResponse(1, "属性名称不能为空！");
        }

        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.fopCompanyContributionDao.insertSelective(o);
        this.dataBaseLogService.log("添加企业管理", "企业管理", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "添加企业管理完成！");
    }

    /**
     * @throws
     * @Title:updateFopCompanyContribution
     * @Description: TODO(更新企业管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse updateFopCompanyContribution(FopCompanyContribution o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCompanyId())) {
            return new MessageResponse(1, "企业ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getContributionType())) {
            return new MessageResponse(1, "贡献类别不能为空！");
        }
        if (CommonUtils.isBlank(o.getItemCode())) {
            return new MessageResponse(1, "属性编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getItemName())) {
            return new MessageResponse(1, "属性名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getSort())) {
            return new MessageResponse(1, "显示排序不能为空！");
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
        this.fopCompanyContributionDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更企业管理", "企业管理", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "变更企业管理完成！");
    }

    /**
     * @throws
     * @Title:selectFopCompanyContributionByPrimaryKey
     * @Description: TODO(获取企业管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopCompanyContribution>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public SingleResult<FopCompanyContributionVo> selectFopCompanyContributionByPrimaryKey(String id) throws Exception {
        SingleResult<FopCompanyContributionVo> rst = new SingleResult<FopCompanyContributionVo>();
        rst.setValue(this.fopCompanyContributionDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteFopCompanyContributionByFopCompanyContributionId
     * @Description: TODO(删除企业管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse deleteFopCompanyContributionByFopCompanyContributionId(String id,
                                                                                  UserProp userProp) throws Exception {
        this.fopCompanyContributionDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除企业管理", "企业管理", String.valueOf(id),
                String.valueOf(id), "企业管理", userProp);
        return new MessageResponse(0, "企业管理删除完成！");
    }

    @Override
    public MessageResponse deleteFopCompanyContributionByCID(String cid, UserProp userProp) throws Exception {
        this.fopCompanyContributionDao.deleteByCID(cid);
        this.dataBaseLogService.log("删除企业管理", "企业管理", String.valueOf(cid),
                String.valueOf(cid), "企业管理", userProp);
        return new MessageResponse(0, "企业管理删除完成！");
    }
}
