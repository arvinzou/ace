package com.huacainfo.ace.fundtown.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.fundtown.dao.VipMemberResDao;
import com.huacainfo.ace.fundtown.model.VipMemberRes;
import com.huacainfo.ace.fundtown.service.VipMemberResService;
import com.huacainfo.ace.fundtown.vo.VipMemberResQVo;
import com.huacainfo.ace.fundtown.vo.VipMemberResVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("vipMemberResService")
/**
 * @author: Arvin
 * @version: 2018-07-03
 * @Description: TODO(入驻成员-资源/附件)
 */
public class VipMemberResServiceImpl implements VipMemberResService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private VipMemberResDao vipMemberResDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(入驻成员-资源/附件分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <VipMemberResVo>
     * @author: Arvin
     * @version: 2018-07-03
     */
    @Override
    public PageResult<VipMemberResVo> findVipMemberResList(VipMemberResQVo condition, int start,
                                                           int limit, String orderBy) throws Exception {
        PageResult<VipMemberResVo> rst = new PageResult<>();
        List<VipMemberResVo> list = this.vipMemberResDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.vipMemberResDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertVipMemberRes
     * @Description: TODO(添加入驻成员-资源/附件)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-03
     */
    @Override
    public MessageResponse insertVipMemberRes(VipMemberRes o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getDeptId())) {
            return new MessageResponse(1, "入驻企业id不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "资源分类不能为空！");
        }
        if (CommonUtils.isBlank(o.getResName())) {
            return new MessageResponse(1, "资源名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getResUrl())) {
            return new MessageResponse(1, "资源地址不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }


        int temp = this.vipMemberResDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "入驻成员-资源/附件名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.vipMemberResDao.insertSelective(o);
        this.dataBaseLogService.log("添加入驻成员-资源/附件", "入驻成员-资源/附件", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加入驻成员-资源/附件完成！");
    }

    /**
     * @throws
     * @Title:updateVipMemberRes
     * @Description: TODO(更新入驻成员-资源/附件)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-03
     */
    @Override
    public MessageResponse updateVipMemberRes(VipMemberRes o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getDeptId())) {
            return new MessageResponse(1, "入驻企业id不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "资源分类不能为空！");
        }
        if (CommonUtils.isBlank(o.getResName())) {
            return new MessageResponse(1, "资源名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getResUrl())) {
            return new MessageResponse(1, "资源地址不能为空！");
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
        this.vipMemberResDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更入驻成员-资源/附件", "入驻成员-资源/附件", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更入驻成员-资源/附件完成！");
    }

    /**
     * @throws
     * @Title:selectVipMemberResByPrimaryKey
     * @Description: TODO(获取入驻成员-资源/附件)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<VipMemberRes>
     * @author: Arvin
     * @version: 2018-07-03
     */
    @Override
    public SingleResult<VipMemberResVo> selectVipMemberResByPrimaryKey(String id) throws Exception {
        SingleResult<VipMemberResVo> rst = new SingleResult<>();
        rst.setValue(this.vipMemberResDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteVipMemberResByVipMemberResId
     * @Description: TODO(删除入驻成员-资源/附件)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-03
     */
    @Override
    public MessageResponse deleteVipMemberResByVipMemberResId(String id, UserProp userProp) throws
            Exception {
        this.vipMemberResDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除入驻成员-资源/附件", "入驻成员-资源/附件",
                String.valueOf(id),
                String.valueOf(id), "入驻成员-资源/附件", userProp);
        return new MessageResponse(0, "入驻成员-资源/附件删除完成！");
    }

    @Override
    public List<VipMemberRes> findByDeptId(String deptId) {
        return vipMemberResDao.findByDeptId(deptId);
    }

}
