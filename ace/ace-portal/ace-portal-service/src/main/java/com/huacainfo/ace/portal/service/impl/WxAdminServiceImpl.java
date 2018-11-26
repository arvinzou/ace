package com.huacainfo.ace.portal.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.dao.WxAdminDao;
import com.huacainfo.ace.portal.model.WxAdmin;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.WxAdminService;
import com.huacainfo.ace.portal.vo.WxAdminQVo;
import com.huacainfo.ace.portal.vo.WxAdminVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("wxAdminService")
/**
 * @author: 陈晓克
 * @version: 2018-11-26
 * @Description: TODO(管理员列表)
 */
public class WxAdminServiceImpl implements WxAdminService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private WxAdminDao wxAdminDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(管理员列表分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <WxAdminVo>
     * @author: 陈晓克
     * @version: 2018-11-26
     */
    @Override
    public PageResult<WxAdminVo> findWxAdminList(WxAdminQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<WxAdminVo> rst = new PageResult<>();
        List<WxAdminVo> list = this.wxAdminDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.wxAdminDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertWxAdmin
     * @Description: TODO(添加管理员列表)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-11-26
     */
    @Override
    public MessageResponse insertWxAdmin(WxAdmin o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getUnionid())) {
            return new MessageResponse(1, "unionid不能为空！");
        }
        if (CommonUtils.isBlank(o.getNickname())) {
            return new MessageResponse(1, "昵称不能为空！");
        }
        if (CommonUtils.isBlank(o.getHeadimg())) {
            return new MessageResponse(1, "头像不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }

        int temp = this.wxAdminDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "管理员列表名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.wxAdminDao.insert(o);
        this.dataBaseLogService.log("添加管理员列表", "管理员列表", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateWxAdmin
     * @Description: TODO(更新管理员列表)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-11-26
     */
    @Override
    public MessageResponse updateWxAdmin(WxAdmin o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getUnionid())) {
            return new MessageResponse(1, "unionid不能为空！");
        }
        if (CommonUtils.isBlank(o.getNickname())) {
            return new MessageResponse(1, "昵称不能为空！");
        }
        if (CommonUtils.isBlank(o.getHeadimg())) {
            return new MessageResponse(1, "头像不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }

        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.wxAdminDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更管理员列表", "管理员列表", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectWxAdminByPrimaryKey
     * @Description: TODO(获取管理员列表)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<WxAdmin>
     * @author: 陈晓克
     * @version: 2018-11-26
     */
    @Override
    public SingleResult<WxAdminVo> selectWxAdminByPrimaryKey(String id) throws Exception {
        SingleResult<WxAdminVo> rst = new SingleResult<>();
        rst.setValue(this.wxAdminDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteWxAdminByWxAdminId
     * @Description: TODO(删除管理员列表)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-11-26
     */
    @Override
    public MessageResponse deleteWxAdminByWxAdminId(String id, UserProp userProp) throws Exception {
        this.wxAdminDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除管理员列表", "管理员列表", id, id,
                "管理员列表", userProp);
        return new MessageResponse(0, "成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(查询微信用户)
     * @param: @param nickname
     * @param: @throws Exception
     * @return: List<Map<String,Object>>
     * @author: 陈晓克
     * @version: 2018-11-26
     */
    @Override
    public List<Map<String, Object>> selectList(String nickname) throws Exception {
        return this.wxAdminDao.selectList(nickname);
    }

}
