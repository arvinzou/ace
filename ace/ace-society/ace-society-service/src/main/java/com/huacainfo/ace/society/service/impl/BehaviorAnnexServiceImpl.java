package com.huacainfo.ace.society.service.impl;


import java.util.Date;
import java.util.List;
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
import com.huacainfo.ace.society.dao.BehaviorAnnexDao;
import com.huacainfo.ace.society.model.BehaviorAnnex;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.society.service.BehaviorAnnexService;
import com.huacainfo.ace.society.vo.BehaviorAnnexVo;
import com.huacainfo.ace.society.vo.BehaviorAnnexQVo;
@Service("behaviorAnnexService")
/**
* @author: lcan
* @version: 2018-09-12
* @Description:  TODO(市民文明随手拍附件)
*/
public class BehaviorAnnexServiceImpl implements BehaviorAnnexService {
Logger logger = LoggerFactory.getLogger(this.getClass());
@Autowired
private BehaviorAnnexDao behaviorAnnexDao;
@Autowired
private DataBaseLogService dataBaseLogService;

/**
*
* @Title:find!{bean.name}List
* @Description:  TODO(市民文明随手拍附件分页查询)
* @param:        @param condition
* @param:        @param start
* @param:        @param limit
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       PageResult
<BehaviorAnnexVo>
    * @throws
    * @author: lcan
    * @version: 2018-09-12
    */
    @Override
    public PageResult
    <BehaviorAnnexVo> findBehaviorAnnexList(BehaviorAnnexQVo condition, int start,
        int limit, String orderBy) throws Exception {
        PageResult
        <BehaviorAnnexVo> rst = new PageResult<>();
            List
            <BehaviorAnnexVo> list = this.behaviorAnnexDao.findList(condition,
                start, limit, orderBy);
                rst.setRows(list);
                if (start <= 1) {
                int allRows = this.behaviorAnnexDao.findCount(condition);
                rst.setTotal(allRows);
                }
                return rst;
                }

                /**
                *
                * @Title:insertBehaviorAnnex
                * @Description: TODO(添加市民文明随手拍附件)
                * @param: @param o
                * @param: @param userProp
                * @param: @throws Exception
                * @return: MessageResponse
                * @throws
                * @author: lcan
                * @version: 2018-09-12
                */
                @Override
                public MessageResponse insertBehaviorAnnex(BehaviorAnnex o, UserProp userProp) throws Exception {

                if (CommonUtils.isBlank(o.getId())) {
                    return new MessageResponse(1, "主键-GUID不能为空！");
                }
                if (CommonUtils.isBlank(o.getBehaviorId())) {
                    return new MessageResponse(1, "市民行为记录编码不能为空！");
                }


                int temp = this.behaviorAnnexDao.isExit(o);
                if (temp > 0) {
                return new MessageResponse(1, "市民文明随手拍附件名称重复！");
                }

                o.setId(GUIDUtil.getGUID());
                o.setCreateDate(new Date());
                o.setStatus("1");
                o.setCreateUserName(userProp.getName());
                o.setCreateUserId(userProp.getUserId());
                this.behaviorAnnexDao.insertSelective(o);
                this.dataBaseLogService.log("添加市民文明随手拍附件", "市民文明随手拍附件", "",
                o.getId(),o.getId(), userProp);

                return new MessageResponse(0, "添加市民文明随手拍附件完成！");
                }

                /**
                *
                * @Title:updateBehaviorAnnex
                * @Description: TODO(更新市民文明随手拍附件)
                * @param: @param o
                * @param: @param userProp
                * @param: @throws Exception
                * @return: MessageResponse
                * @throws
                * @author: lcan
                * @version: 2018-09-12
                */
                @Override
                public MessageResponse updateBehaviorAnnex(BehaviorAnnex o, UserProp userProp) throws Exception {
                if (CommonUtils.isBlank(o.getId())) {
                    return new MessageResponse(1, "主键-GUID不能为空！");
                }
                if (CommonUtils.isBlank(o.getBehaviorId())) {
                    return new MessageResponse(1, "市民行为记录编码不能为空！");
                }


                o.setLastModifyDate(new Date());
                o.setLastModifyUserName(userProp.getName());
                o.setLastModifyUserId(userProp.getUserId());
                this.behaviorAnnexDao.updateByPrimaryKeySelective(o.getId());
                this.dataBaseLogService.log("变更市民文明随手拍附件", "市民文明随手拍附件", "",
                o.getId(), o.getId(), userProp);

                return new MessageResponse(0, "变更市民文明随手拍附件完成！");
                }

                /**
                *
                * @Title:selectBehaviorAnnexByPrimaryKey
                * @Description: TODO(获取市民文明随手拍附件)
                * @param: @param id
                * @param: @throws Exception
                * @return: SingleResult<BehaviorAnnex>
                * @throws
                * @author: lcan
                * @version: 2018-09-12
                */
                @Override
                public SingleResult
                <BehaviorAnnexVo> selectBehaviorAnnexByPrimaryKey(String id) throws Exception {
                    SingleResult
                    <BehaviorAnnexVo> rst = new SingleResult<>();
                        rst.setValue(this.behaviorAnnexDao.selectVoByPrimaryKey(id));
                        return rst;
                        }

                        /**
                        *
                        * @Title:deleteBehaviorAnnexByBehaviorAnnexId
                        * @Description: TODO(删除市民文明随手拍附件)
                        * @param: @param id
                        * @param: @param userProp
                        * @param: @throws Exception
                        * @return: MessageResponse
                        * @throws
                        * @author: lcan
                        * @version: 2018-09-12
                        */
                        @Override
                        public MessageResponse deleteBehaviorAnnexByBehaviorAnnexId(String id, UserProp userProp) throws
                        Exception {
                        this.behaviorAnnexDao.deleteByPrimaryKey(id);
                        this.dataBaseLogService.log("删除市民文明随手拍附件", "市民文明随手拍附件",
                        String.valueOf(id),
                        String.valueOf(id), "市民文明随手拍附件", userProp);
                        return new MessageResponse(0, "市民文明随手拍附件删除完成！");
                        }


                        /**
                        *
                        * @Title:audit
                        * @Description: TODO(审核市民文明随手拍附件)
                        * @param: @param id bean.id
                        * @param: @param rst 审核结果 3-通过 4-拒绝
                        * @param: @param remark 审核备注
                        * @param: @throws Exception
                        * @return: MessageResponse
                        * @throws
                        * @author: lcan
                        * @version: 2018-09-12
                        */
                        @Override
                        public MessageResponse audit(String id,String rst, String remark,
                        UserProp userProp) throws Exception {

                        behaviorAnnexDao.updateByPrimaryKeySelective(id);


                        dataBaseLogService.log("审核市民文明随手拍附件", "市民文明随手拍附件",
                        String.valueOf(id), String.valueOf(id), "市民文明随手拍附件", userProp);
                        return new MessageResponse(0, "市民文明随手拍附件审核完成！");
                        }

                        }
