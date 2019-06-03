package com.huacainfo.ace.partyschool.service.impl;


import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.partyschool.dao.EvaluationIndexDao;
import com.huacainfo.ace.partyschool.model.Course;
import com.huacainfo.ace.partyschool.model.EvaluationIndex;
import com.huacainfo.ace.partyschool.service.EvaluationIndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.partyschool.dao.EvaluatingDao;
import com.huacainfo.ace.partyschool.model.Evaluating;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.partyschool.service.EvaluatingService;
import com.huacainfo.ace.partyschool.vo.EvaluatingVo;
import com.huacainfo.ace.partyschool.vo.EvaluatingQVo;

@Service("evaluatingService")
/**
 * @author: 王恩
 * @version: 2019-01-03
 * @Description: TODO(评测管理)
 */
public class EvaluatingServiceImpl implements EvaluatingService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EvaluatingDao evaluatingDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private EvaluationIndexService evaluationIndexService;
    @Autowired
    private EvaluationIndexDao evaluationIndexDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(评测管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <EvaluatingVo>
     * @author: 王恩
     * @version: 2019-01-03
     */
    @Override
    public PageResult<EvaluatingVo> findEvaluatingList(EvaluatingQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult
                <EvaluatingVo> rst = new PageResult<>();
        List
                <EvaluatingVo> list = this.evaluatingDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.evaluatingDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertEvaluating
     * @Description: TODO(添加评测管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-03
     */
    @Override
    public MessageResponse insertEvaluating(Evaluating o, UserProp userProp) throws Exception {
        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getTimeout())) {
            return new MessageResponse(1, "超时设定不能为空！");
        }
        int temp = this.evaluatingDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "评测管理名称重复！");
        }
        o.setStatus("1");
        o.setCreateDate(new Date());
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.evaluatingDao.insert(o);
        MessageResponse messageResponse=insetevaluationIndexServices(o.getEvaluationIndexList(), o.getId(), userProp);
        if(messageResponse!=null){
            throw new Exception();
        }
        this.dataBaseLogService.log("添加评测管理", "评测管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加评测管理完成！");
    }


    private MessageResponse insetevaluationIndexServices(List<EvaluationIndex> evaluationIndexService, String id, UserProp userProp) throws Exception {
        for (EvaluationIndex item : evaluationIndexService) {
            item.setEvaluatingId(id);
            MessageResponse messageResponse=this.evaluationIndexService.insertEvaluationIndex(item, userProp);
            if(messageResponse.getStatus()!=0){
                return messageResponse;
            }
        }
        return null;
    }


    /**
     * @throws
     * @Title:updateEvaluating
     * @Description: TODO(更新评测管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-03
     */
    @Override
    public MessageResponse updateEvaluating(Evaluating o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        Evaluating evaluating = this.evaluatingDao.selectByPrimaryKey(o.getId());
        if (evaluating == null) {
            return new MessageResponse(1, "评测数据丢失！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getTimeout())) {
            return new MessageResponse(1, "超时设定不能为空！");
        }
        evaluating.setLastModifyDate(new Date());
        evaluating.setLastModifyUserName(userProp.getName());
        evaluating.setLastModifyUserId(userProp.getUserId());
        evaluating.setIntroduce(o.getIntroduce());
        evaluating.setName(o.getName());
        evaluating.setQuestion(o.getQuestion());
        evaluating.setTimeout(o.getTimeout());
        this.evaluatingDao.updateByPrimaryKey(evaluating);
        this.evaluationIndexDao.deleteByEvaluatingId(o.getId());
        for(EvaluationIndex item : o.getEvaluationIndexList()){
            if(CommonUtils.isBlank(item.getId())){
                item.setEvaluatingId(o.getId());
                this.evaluationIndexService.insertEvaluationIndex(item, userProp);
            }else{
                item.setEvaluatingId(o.getId());
                this.evaluationIndexDao.insert(item);
            }
        }
        this.dataBaseLogService.log("变更评测管理", "评测管理", "",
                o.getId(), o.getId(), userProp);
        return new MessageResponse(0, "变更评测管理完成！");
    }

    /**
     * @throws
     * @Title:selectEvaluatingByPrimaryKey
     * @Description: TODO(获取评测管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Evaluating>
     * @author: 王恩
     * @version: 2019-01-03
     */
    @Override
    public SingleResult<EvaluatingVo> selectEvaluatingByPrimaryKey(String id) throws Exception {
        SingleResult<EvaluatingVo> rst = new SingleResult<>();
        EvaluatingVo evaluatingVo=this.evaluatingDao.selectVoByPrimaryKey(id);
        evaluatingVo.setEvaluationIndexList(this.evaluationIndexDao.selectByEvaluatingId(id));
        rst.setValue(evaluatingVo);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteEvaluatingByEvaluatingId
     * @Description: TODO(删除评测管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-03
     */
    @Override
    public MessageResponse deleteEvaluatingByEvaluatingId(String id, UserProp userProp) throws
            Exception {
        this.evaluatingDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除评测管理", "评测管理", id, id,
                "评测管理", userProp);
        return new MessageResponse(0, "评测管理删除完成！");
    }

    @Override
    public MessageResponse softdel(String id,UserProp userProp) throws Exception {

        Evaluating obj = evaluatingDao.selectByPrimaryKey(id);
        if (obj == null) {
            return new MessageResponse(ResultCode.FAIL, "评测数据丢失");
        }
        obj.setStatus("0");
        obj.setLastModifyDate(DateUtil.getNowDate());
        obj.setLastModifyUserId(userProp.getUserId());
        obj.setLastModifyUserName(userProp.getName());
        evaluatingDao.updateByPrimaryKey(obj);
        dataBaseLogService.log("软删除评测管理", "评测管理", id, id,
                "评测管理", userProp);
        return new MessageResponse(0, "删除成功！");
    }
}
