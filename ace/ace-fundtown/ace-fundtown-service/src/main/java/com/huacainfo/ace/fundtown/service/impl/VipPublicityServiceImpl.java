package com.huacainfo.ace.fundtown.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.fundtown.dao.VipPublicityDao;
import com.huacainfo.ace.fundtown.model.VipPublicity;
import com.huacainfo.ace.fundtown.service.VipPublicityService;
import com.huacainfo.ace.fundtown.vo.VipPublicityQVo;
import com.huacainfo.ace.fundtown.vo.VipPublicityVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("vipPublicityService")
/**
 * @author: Arvin
 * @version: 2018-07-03
 * @Description: TODO(入驻成员公示列表)
 */
public class VipPublicityServiceImpl implements VipPublicityService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private VipPublicityDao vipPublicityDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(入驻成员公示列表分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <VipPublicityVo>
     * @author: Arvin
     * @version: 2018-07-03
     */
    @Override
    public PageResult<VipPublicityVo> findVipPublicityList(VipPublicityQVo condition, int start,
                                                           int limit, String orderBy) throws Exception {
        PageResult<VipPublicityVo> rst = new PageResult<>();
        List<VipPublicityVo> list = this.vipPublicityDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.vipPublicityDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertVipPublicity
     * @Description: TODO(添加入驻成员公示列表)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-03
     */
    @Override
    public MessageResponse insertVipPublicity(VipPublicity o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getDeptId())) {
            return new MessageResponse(1, "入驻企业id不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }


        int temp = this.vipPublicityDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "入驻成员公示列表名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.vipPublicityDao.insertSelective(o);
        this.dataBaseLogService.log("添加入驻成员公示列表", "入驻成员公示列表", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加入驻成员公示列表完成！");
    }

    /**
     * @throws
     * @Title:updateVipPublicity
     * @Description: TODO(更新入驻成员公示列表)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-03
     */
    @Override
    public MessageResponse updateVipPublicity(VipPublicity o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getDeptId())) {
            return new MessageResponse(1, "入驻企业id不能为空！");
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
        this.vipPublicityDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更入驻成员公示列表", "入驻成员公示列表", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更入驻成员公示列表完成！");
    }

    /**
     * @throws
     * @Title:selectVipPublicityByPrimaryKey
     * @Description: TODO(获取入驻成员公示列表)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<VipPublicity>
     * @author: Arvin
     * @version: 2018-07-03
     */
    @Override
    public SingleResult<VipPublicityVo> selectVipPublicityByPrimaryKey(String id) throws Exception {
        SingleResult<VipPublicityVo> rst = new SingleResult<>();
        rst.setValue(this.vipPublicityDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteVipPublicityByVipPublicityId
     * @Description: TODO(删除入驻成员公示列表)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-03
     */
    @Override
    public MessageResponse deleteVipPublicityByVipPublicityId(String id, UserProp userProp) throws
            Exception {
        this.vipPublicityDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除入驻成员公示列表", "入驻成员公示列表",
                String.valueOf(id),
                String.valueOf(id), "入驻成员公示列表", userProp);
        return new MessageResponse(0, "入驻成员公示列表删除完成！");
    }

}
