package com.huacainfo.ace.glink.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.glink.dao.ErrWarnSomeoneDao;
import com.huacainfo.ace.glink.model.ErrWarnSomeone;
import com.huacainfo.ace.glink.service.ErrWarnSomeoneService;
import com.huacainfo.ace.glink.vo.ErrWarnSomeoneQVo;
import com.huacainfo.ace.glink.vo.ErrWarnSomeoneVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("errWarnSomeoneService")
/**
 * @author: Arvin
 * @version: 2019-04-11
 * @Description: TODO(故障报警 - 送报人)
 */
public class ErrWarnSomeoneServiceImpl implements ErrWarnSomeoneService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ErrWarnSomeoneDao errWarnSomeoneDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(故障报警 - 送报人分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<ErrWarnSomeoneVo>
     * @author: Arvin
     * @version: 2019-04-11
     */
    @Override
    public PageResult<ErrWarnSomeoneVo> findErrWarnSomeoneList(ErrWarnSomeoneQVo condition,
                                                               int start, int limit, String orderBy) throws Exception {
        PageResult<ErrWarnSomeoneVo> rst = new PageResult<>();
        List<ErrWarnSomeoneVo> list = errWarnSomeoneDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.errWarnSomeoneDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertErrWarnSomeone
     * @Description: TODO(添加故障报警 - 送报人)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    @Override
    public MessageResponse insertErrWarnSomeone(ErrWarnSomeone o, UserProp userProp) throws Exception {
        String guid = StringUtil.isEmpty(o.getId()) ? GUIDUtil.getGUID() : o.getId();
        o.setId(guid);

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getSubareaCode())) {
            return new MessageResponse(1, "分区代码不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "姓名不能为空！");
        }
        if (CommonUtils.isBlank(o.getMobile())) {
            return new MessageResponse(1, "手机号不能为空！");
        }
        int temp = this.errWarnSomeoneDao.isExist(o);
        if (temp > 0) {
            return new MessageResponse(1, "手机号已存在！");
        }


        o.setCreateDate(new Date());
        o.setStatus(StringUtil.isEmpty(o.getStatus()) ? "0" : o.getStatus());
        this.errWarnSomeoneDao.insert(o);
        this.dataBaseLogService.log("添加故障报警-送报人",
                "故障报警-送报人", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateErrWarnSomeone
     * @Description: TODO(更新故障报警 - 送报人)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    @Override
    public MessageResponse updateErrWarnSomeone(ErrWarnSomeone o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getSubareaCode())) {
            return new MessageResponse(1, "分区代码不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "姓名不能为空！");
        }
        if (CommonUtils.isBlank(o.getMobile())) {
            return new MessageResponse(1, "手机号不能为空！");
        }
        ErrWarnSomeoneVo vo = errWarnSomeoneDao.selectVoByPrimaryKey(o.getId());
        if (vo == null) {
            return new MessageResponse(1, "数据丢失！");
        }
        int temp = this.errWarnSomeoneDao.isExist(o);
        if (temp > 0) {
            return new MessageResponse(1, "手机号已存在！");
        }

        o.setStatus(vo.getStatus());
        o.setCreateDate(vo.getCreateDate());
        errWarnSomeoneDao.updateByPrimaryKey(o);
        dataBaseLogService.log("变更故障报警-送报人",
                "故障报警-送报人", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectErrWarnSomeoneByPrimaryKey
     * @Description: TODO(获取故障报警 - 送报人)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<ErrWarnSomeone>
     * @author: Arvin
     * @version: 2019-04-11
     */
    @Override
    public SingleResult<ErrWarnSomeoneVo> selectErrWarnSomeoneByPrimaryKey(String id) throws Exception {
        SingleResult<ErrWarnSomeoneVo> rst = new SingleResult<>();
        rst.setValue(this.errWarnSomeoneDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteErrWarnSomeoneByErrWarnSomeoneId
     * @Description: TODO(删除故障报警 - 送报人)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    @Override
    public MessageResponse deleteErrWarnSomeoneByErrWarnSomeoneId(String id, UserProp userProp) throws
            Exception {
        this.errWarnSomeoneDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除故障报警-送报人", "故障报警-送报人", id, id,
                "故障报警-送报人", userProp);
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
     * @version: 2019-04-11
     */
    @Override
    public MessageResponse updateStatus(String id, String status, UserProp userProp) throws
            Exception {
        this.errWarnSomeoneDao.updateStatus(id, status);
        this.dataBaseLogService.log("跟新状态", "故障报警-送报人", id, id,
                "故障报警-送报人", userProp);
        return new MessageResponse(0, "成功！");
    }

}
