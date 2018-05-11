package com.huacainfo.ace.fop.service.impl;


import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.result.ResultResponse;
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
import com.huacainfo.ace.fop.dao.FopQuestionDao;
import com.huacainfo.ace.fop.model.FopQuestion;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.fop.service.FopQuestionService;
import com.huacainfo.ace.fop.vo.FopQuestionVo;
import com.huacainfo.ace.fop.vo.FopQuestionQVo;

@Service("fopQuestionService")
/**
 * @author: Arvin
 * @version: 2018-05-07
 * @Description: TODO(法律帮助/政府诉求)
 */
public class FopQuestionServiceImpl implements FopQuestionService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopQuestionDao fopQuestionDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(法律帮助/政府诉求分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <FopQuestionVo>
     * @author: Arvin
     * @version: 2018-05-07
     */
    @Override
    public PageResult<FopQuestionVo> findFopQuestionList(FopQuestionQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<FopQuestionVo> rst = new PageResult<FopQuestionVo>();
        List<FopQuestionVo> list = this.fopQuestionDao.findList(condition, start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.fopQuestionDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    @Override
    public ResultResponse findQuestionList(FopQuestionQVo condition, int page, int limit, String orderBy) throws Exception {
        List<FopQuestionVo> list = this.fopQuestionDao.findList(condition, (page - 1) * limit, limit, orderBy);
        ResultResponse rst = new ResultResponse(ResultCode.SUCCESS, "获取法律帮助或诉求列表", list);
        return rst;
    }

    /**
     * @throws
     * @Title:insertFopQuestion
     * @Description: TODO(添加法律帮助/政府诉求)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-07
     */
    @Override
    public MessageResponse insertFopQuestion(FopQuestion o, UserProp userProp)
            throws Exception {
        o.setId(GUIDUtil.getGUID());
        //o.setId(String.valueOf(new Date().getTime()));

        int temp = this.fopQuestionDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "法律帮助/政府诉求名称重复！");
        }
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.fopQuestionDao.insertSelective(o);
//        this.dataBaseLogService.log("添加法律帮助/政府诉求", "法律帮助/政府诉求", "",
//                o.getTitle(), o.getTitle(), userProp);
        return new MessageResponse(0, "添加法律帮助/政府诉求完成！");
    }

    /**
     * @throws
     * @Title:updateFopQuestion
     * @Description: TODO(更新法律帮助/政府诉求)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-07
     */
    @Override
    public MessageResponse updateFopQuestion(FopQuestion o, UserProp userProp)
            throws Exception {


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.fopQuestionDao.updateByPrimaryKeySelective(o);
//        this.dataBaseLogService.log("变更法律帮助/政府诉求", "法律帮助/政府诉求", "",
//                o.getTitle(),
//                o.getTitle(), userProp);
        return new MessageResponse(0, "变更法律帮助/政府诉求完成！");
    }

    /**
     * @throws
     * @Title:selectFopQuestionByPrimaryKey
     * @Description: TODO(获取法律帮助/政府诉求)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopQuestion>
     * @author: Arvin
     * @version: 2018-05-07
     */
    @Override
    public SingleResult
            <FopQuestionVo> selectFopQuestionByPrimaryKey(String id) throws Exception {
        SingleResult
                <FopQuestionVo> rst = new SingleResult
                <FopQuestionVo>();
        rst.setValue(this.fopQuestionDao.selectByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteFopQuestionByFopQuestionId
     * @Description: TODO(删除法律帮助/政府诉求)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-07
     */
    @Override
    public MessageResponse deleteFopQuestionByFopQuestionId(String id,
                                                            UserProp userProp) throws Exception {
        this.fopQuestionDao.deleteByPrimaryKey(id);
//        this.dataBaseLogService.log("删除法律帮助/政府诉求", "法律帮助/政府诉求",
//                String.valueOf(id),
//                String.valueOf(id), "法律帮助/政府诉求", userProp);
        return new MessageResponse(0, "法律帮助/政府诉求删除完成！");
    }


    @Override
    public List<FopQuestionVo> findCommentList(String parentId) throws Exception {
        List<FopQuestionVo> list = this.fopQuestionDao.findCommentList(parentId);
        if (list.size() == 0) {
            return null;
        }
        for (FopQuestionVo fp : list) {
            String parent = fp.getId();
            List<FopQuestionVo> list1 = findCommentList(parent);
            fp.setChildren(list1);
        }
        return list;
    }
}
