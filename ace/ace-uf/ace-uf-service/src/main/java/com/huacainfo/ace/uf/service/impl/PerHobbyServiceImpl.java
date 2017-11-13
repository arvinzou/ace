package com.huacainfo.ace.uf.service.impl;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.uf.dao.PerHobbyDao;
import com.huacainfo.ace.uf.model.PerHobby;
import com.huacainfo.ace.uf.service.PerHobbyService;
import com.huacainfo.ace.uf.vo.PerHobbyQVo;
import com.huacainfo.ace.uf.vo.PerHobbyVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("perHobbyService")
public class PerHobbyServiceImpl implements PerHobbyService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PerHobbyDao perHobbyDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    public PageResult<PerHobbyVo> findPerHobbyList(PerHobbyQVo condition, int start, int limit, String orderBy)
            throws Exception {
        PageResult<PerHobbyVo> rst = new PageResult<PerHobbyVo>();
        List<PerHobbyVo> list = this.perHobbyDao.findList(condition, start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.perHobbyDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }
    @Override
    public MessageResponse insertPerHobby(PerHobby o, UserProp userProp) throws Exception {
        o.setId(UUID.randomUUID().toString());
        // o.setId(String.valueOf(new Date().getTime()));

        int temp = this.perHobbyDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "兴趣爱好名称重复！");
        }
        o.setCreateDate(new Date());
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.perHobbyDao.insert(o);
        this.dataBaseLogService.log("添加兴趣爱好", "兴趣爱好", "", o.getHobbyId(), o.getHobbyId(), userProp);
        return new MessageResponse(0, "添加兴趣爱好完成！");
    }

    @Override
    public MessageResponse updatePerHobby(PerHobby o, UserProp userProp) throws Exception {

        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.perHobbyDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更兴趣爱好", "兴趣爱好", "", o.getHobbyId(), o.getHobbyId(), userProp);
        return new MessageResponse(0, "变更兴趣爱好完成！");
    }

    public SingleResult<PerHobbyVo> selectPerHobbyByPrimaryKey(String id) throws Exception {
        SingleResult<PerHobbyVo> rst = new SingleResult<PerHobbyVo>();
        rst.setValue(this.perHobbyDao.selectByPrimaryKey(id));
        return rst;
    }

    public MessageResponse deletePerHobbyByPerHobbyId(String id, UserProp userProp) throws Exception {
        this.perHobbyDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除兴趣爱好", "兴趣爱好", String.valueOf(id), String.valueOf(id), "兴趣爱好", userProp);
        return new MessageResponse(0, "兴趣爱好删除完成！");
    }
}
