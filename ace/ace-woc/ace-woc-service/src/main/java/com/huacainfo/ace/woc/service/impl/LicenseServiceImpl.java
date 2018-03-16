package com.huacainfo.ace.woc.service.impl;


import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.tools.DateUtil;
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
import com.huacainfo.ace.woc.dao.LicenseDao;
import com.huacainfo.ace.woc.model.License;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.woc.service.LicenseService;
import com.huacainfo.ace.woc.vo.LicenseVo;
import com.huacainfo.ace.woc.vo.LicenseQVo;

@Service("licenseService")
/**
 * @author: Arvin
 * @version: 2018-03-12
 * @Description: TODO(证件档案)
 */
public class LicenseServiceImpl implements LicenseService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LicenseDao licenseDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(证件档案分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<LicenseVo>
     * @author: Arvin
     * @version: 2018-03-12
     */
    @Override
    public PageResult<LicenseVo> findLicenseList(LicenseQVo condition, int start,
                                                 int limit, String orderBy) throws Exception {
        PageResult<LicenseVo> rst = new PageResult<LicenseVo>();
        List<LicenseVo> list = this.licenseDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.licenseDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertLicense
     * @Description: TODO(添加证件档案)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-03-12
     */
    @Override
    public MessageResponse insertLicense(License o, UserProp userProp)
            throws Exception {
        o.setId(GUIDUtil.getGUID());
        o.setLastModifyDate(DateUtil.getNowDate());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getLicenseType())) {
            return new MessageResponse(1, "证件类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getLicenseNo())) {
            return new MessageResponse(1, "证件号码不能为空！");
        }
        if (CommonUtils.isBlank(o.getLicenseImg1())) {
            return new MessageResponse(1, "证件照片1不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "更新时间不能为空！");
        }

        int temp = this.licenseDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "证件档案名称重复！");
        }
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.licenseDao.insert(o);
        this.dataBaseLogService.log("添加证件档案", "证件档案", "", "insertLicense",
                "insertLicense", userProp);
        return new MessageResponse(0, "添加证件档案完成！");
    }

    /**
     * @throws
     * @Title:updateLicense
     * @Description: TODO(更新证件档案)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-03-12
     */
    @Override
    public MessageResponse updateLicense(License o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getLicenseType())) {
            return new MessageResponse(1, "证件类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getLicenseNo())) {
            return new MessageResponse(1, "证件号码不能为空！");
        }
        if (CommonUtils.isBlank(o.getLicenseImg1())) {
            return new MessageResponse(1, "证件照片1不能为空！");
        }


        o.setLastModifyDate(DateUtil.getNowDate());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.licenseDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更证件档案", "证件档案", "", "insertLicense",
                "insertLicense", userProp);
        return new MessageResponse(0, "变更证件档案完成！");
    }

    /**
     * @throws
     * @Title:selectLicenseByPrimaryKey
     * @Description: TODO(获取证件档案)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<License>
     * @author: Arvin
     * @version: 2018-03-12
     */
    @Override
    public SingleResult<LicenseVo> selectLicenseByPrimaryKey(String id) throws Exception {
        SingleResult<LicenseVo> rst = new SingleResult<LicenseVo>();
        rst.setValue(this.licenseDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteLicenseByLicenseId
     * @Description: TODO(删除证件档案)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-03-12
     */
    @Override
    public MessageResponse deleteLicenseByLicenseId(String id,
                                                    UserProp userProp) throws Exception {
        this.licenseDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除证件档案", "证件档案", String.valueOf(id),
                String.valueOf(id), "证件档案", userProp);
        return new MessageResponse(0, "证件档案删除完成！");
    }
}
