package com.huacainfo.ace.glink.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.glink.dao.LtLnkObjectDao;
import com.huacainfo.ace.glink.model.LtLnkObject;
import com.huacainfo.ace.glink.service.LtLnkObjectService;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("ltLnkObjectService")
/**
 * @author: huacai003
 * @version: 2019-04-16
 * @Description: TODO(策略下发对象)
 */
public class LtLnkObjectServiceImpl implements LtLnkObjectService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LtLnkObjectDao ltLnkObjectDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;



    /**
     * @throws
     * @Title:insertLtLnkObject
     * @Description: TODO(添加策略下发对象)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-16
     */
    @Override
    public MessageResponse insertLtLnkObject(LtLnkObject o, UserProp userProp) throws Exception {
        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getAiCode())) {
            return new MessageResponse(1, "策略编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getLnkCode())) {
            return new MessageResponse(1, "建筑/节点/站点编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getLnkType())) {
            return new MessageResponse(1, "建筑/节点/站点类型不能为空！");
        }
        o.setSendDate(new Date());
        o.setCreateDate(new Date());
        o.setApiState("1");
        o.setApiRst("下发成功");
        o.setStatus("1");
        this.ltLnkObjectDao.insert(o);
        this.dataBaseLogService.log("添加策略下发对象", "策略下发对象", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateLtLnkObject
     * @Description: TODO(更新策略下发对象)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-16
     */
    @Override
    public MessageResponse updateLtLnkObject(LtLnkObject o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getAiCode())) {
            return new MessageResponse(1, "策略编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getLnkCode())) {
            return new MessageResponse(1, "建筑/节点/站点编码不能为空！");
        }
        this.ltLnkObjectDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更策略下发对象", "策略下发对象", "",
                o.getId(), o.getId(), userProp);
        return new MessageResponse(0, "保存成功！");
    }


    /**
     * @throws
     * @Title:deleteLtLnkObjectByLtLnkObjectId
     * @Description: TODO(删除策略下发对象)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-16
     */
    @Override
    public MessageResponse deleteLtLnkObjectByLtLnkObjectId(String id, UserProp userProp) throws
            Exception {
        this.ltLnkObjectDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除策略下发对象", "策略下发对象", id, id,
                "策略下发对象", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核策略下发对象)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-16
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {


        dataBaseLogService.log("审核策略下发对象", "策略下发对象", id, id,
                "策略下发对象", userProp);
        return new MessageResponse(0, "审核成功！");
    }


    /**
     * @throws
     * @Title:importXls
     * @Description: TODO(Excel导入资源数据)
     * @param: @param list
     * @param: @param userProp
     * @param: @return
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-16
     */

}
