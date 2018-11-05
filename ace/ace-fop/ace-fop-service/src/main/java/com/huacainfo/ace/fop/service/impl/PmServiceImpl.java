package com.huacainfo.ace.fop.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.fop.dao.PmDao;
import com.huacainfo.ace.fop.model.Pm;
import com.huacainfo.ace.fop.service.PmService;
import com.huacainfo.ace.fop.vo.PmQVo;
import com.huacainfo.ace.fop.vo.PmVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("pmService")
/**
 * @author: 陈晓克
 * @version: 2018-11-05
 * @Description: TODO(党员信息)
 */
public class PmServiceImpl implements PmService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PmDao pmDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(党员信息分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <PmVo>
     * @author: 陈晓克
     * @version: 2018-11-05
     */
    @Override
    public PageResult<PmVo> findPmList(PmQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<PmVo> rst = new PageResult<>();
        List<PmVo> list = this.pmDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.pmDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertPm
     * @Description: TODO(添加党员信息)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-11-05
     */
    @Override
    public MessageResponse insertPm(Pm o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getCompanyId())) {
            return new MessageResponse(1, "所属企业不能为空!");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "姓名不能为空!");
        }
        if (CommonUtils.isBlank(o.getSex())) {
            return new MessageResponse(1, "性别不能为空!");
        }
        int temp = this.pmDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "党员姓名重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.pmDao.insert(o);
        this.dataBaseLogService.log("添加党员信息", "党员信息", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updatePm
     * @Description: TODO(更新党员信息)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-11-05
     */
    @Override
    public MessageResponse updatePm(Pm o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "党员编号不能为空!");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "姓名不能为空!");
        }
        if (CommonUtils.isBlank(o.getSex())) {
            return new MessageResponse(1, "性别不能为空!");
        }
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.pmDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更党员信息", "党员信息", "", o.getId(), o.getId(), userProp);
        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectPmByPrimaryKey
     * @Description: TODO(获取党员信息)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Pm>
     * @author: 陈晓克
     * @version: 2018-11-05
     */
    @Override
    public SingleResult<PmVo> selectPmByPrimaryKey(String id) throws Exception {
        SingleResult<PmVo> rst = new SingleResult<>();
        rst.setValue(this.pmDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deletePmByPmId
     * @Description: TODO(删除党员信息)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-11-05
     */
    @Override
    public MessageResponse deletePmByPmId(String id, UserProp userProp) throws
            Exception {
        this.pmDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除党员信息", "党员信息", id, id,
                "党员信息", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核党员信息)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-11-05
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {

        return new MessageResponse(0, "党员信息审核完成！");
    }

}
