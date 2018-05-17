package com.huacainfo.ace.fop.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.fop.common.constant.FopConstant;
import com.huacainfo.ace.fop.dao.FopAssociationDao;
import com.huacainfo.ace.fop.dao.FopCompanyDao;
import com.huacainfo.ace.fop.model.FopAssociation;
import com.huacainfo.ace.fop.model.FopCompany;
import com.huacainfo.ace.portal.service.UsersService;
import com.huacainfo.ace.portal.vo.UsersVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.fop.dao.InformationServiceDao;
import com.huacainfo.ace.fop.model.InformationService;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.fop.service.InformationServiceService;
import com.huacainfo.ace.fop.vo.InformationServiceVo;
import com.huacainfo.ace.fop.vo.InformationServiceQVo;

@Service("informationServiceService")
/**
 * @author: huacai003
 * @version: 2018-05-17
 * @Description: TODO(信息服务)
 */
public class InformationServiceServiceImpl implements InformationServiceService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private InformationServiceDao informationServiceDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    @Autowired
    private UsersService usersService;
    @Autowired
    private FopCompanyDao fopCompanyDao;

    @Autowired
    private FopAssociationDao fopAssociationDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(信息服务分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <InformationServiceVo>
     * @author: huacai003
     * @version: 2018-05-17
     */
    @Override
    public PageResult<InformationServiceVo> findInformationServiceList(InformationServiceQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<InformationServiceVo> rst = new PageResult<InformationServiceVo>();
        List<InformationServiceVo> list = this.informationServiceDao.findList(condition, start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.informationServiceDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @param condition
     * @param page
     * @param limit
     * @param orderBy
     * @return
     * @throws Exception
     */
    @Override
    public ResultResponse InformationServiceList(InformationServiceQVo condition, int page, int limit, String orderBy) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", this.informationServiceDao.findList(condition, (page - 1) * limit, limit, orderBy));
        map.put("total", this.informationServiceDao.findCount(condition));
        ResultResponse rst = new ResultResponse(ResultCode.SUCCESS, "信息服务列表", map);
        return rst;
    }

    /**
     * @throws
     * @Title:insertInformationService
     * @Description: TODO(添加信息服务)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @Override
    public MessageResponse insertInformationService(InformationService o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getContent())) {
            return new MessageResponse(1, "内容不能为空！");
        }
        if (CommonUtils.isBlank(o.getModules())) {
            return new MessageResponse(1, "模块不能为空！");
        }
        int temp = this.informationServiceDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "信息服务名称重复！");
        }

        SingleResult<UsersVo> singleResult = usersService.selectUsersByPrimaryKey(userProp.getUserId());
        UsersVo user = singleResult.getValue();
        if (null == user) {
            return new MessageResponse(1, "没有注册");
        }
        if (CommonUtils.isBlank(user.getDepartmentId())) {
            return new MessageResponse(1, "账户没有绑定企业！");
        }
        FopAssociation fa = fopAssociationDao.selectByDepartmentId(user.getDepartmentId());
        FopCompany fc = fopCompanyDao.selectByDepartmentId(user.getDepartmentId());
        if (null == fc) {
            if (null == fa) {
                return new MessageResponse(1, "账户没有绑定企业！");
            }
            o.setRelationId(fa.getId());
            o.setRelationType(FopConstant.ASSOCIATION);
        } else {
            o.setRelationId(fc.getId());
            o.setRelationType(FopConstant.COMPANY);
        }

        o.setReleaseDate(new Date());
        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.informationServiceDao.insertSelective(o);
        this.dataBaseLogService.log("添加信息服务", "信息服务", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加信息服务完成！");
    }

    /**
     * @throws
     * @Title:updateInformationService
     * @Description: TODO(更新信息服务)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @Override
    public MessageResponse updateInformationService(InformationService o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }

        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getContent())) {
            return new MessageResponse(1, "内容不能为空！");
        }
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.informationServiceDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更信息服务", "信息服务", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更信息服务完成！");
    }

    /**
     * @throws
     * @Title:selectInformationServiceByPrimaryKey
     * @Description: TODO(获取信息服务)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<InformationService>
     * @author: huacai003
     * @version: 2018-05-17
     */
    @Override
    public SingleResult<InformationServiceVo> selectInformationServiceByPrimaryKey(String id) throws Exception {
        SingleResult<InformationServiceVo> rst = new SingleResult<InformationServiceVo>();
        rst.setValue(this.informationServiceDao.selectVoByPrimaryKey(id));
        return rst;
    }

    @Override
    public ResultResponse InformationServiceByPrimaryKey(String id) throws Exception {
        if (CommonUtils.isBlank(id)) {
            return new ResultResponse(1, "主键不能为空！");
        }
        ResultResponse rst = new ResultResponse(ResultCode.SUCCESS, "信息服务详情", this.informationServiceDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteInformationServiceByInformationServiceId
     * @Description: TODO(删除信息服务)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @Override
    public MessageResponse deleteInformationServiceByInformationServiceId(String id, UserProp
            userProp) throws Exception {
        this.informationServiceDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除信息服务", "信息服务",
                String.valueOf(id),
                String.valueOf(id), "信息服务", userProp);
        return new MessageResponse(0, "信息服务删除完成！");
    }

}
