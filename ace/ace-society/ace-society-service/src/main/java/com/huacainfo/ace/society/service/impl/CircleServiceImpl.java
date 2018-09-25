package com.huacainfo.ace.society.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.tools.StringUtils;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.society.dao.CircleDao;
import com.huacainfo.ace.society.dao.CircleImgDao;
import com.huacainfo.ace.society.dao.CircleLogDao;
import com.huacainfo.ace.society.model.Circle;
import com.huacainfo.ace.society.model.CircleImg;
import com.huacainfo.ace.society.model.CircleLog;
import com.huacainfo.ace.society.service.CircleService;
import com.huacainfo.ace.society.vo.CircleQVo;
import com.huacainfo.ace.society.vo.CircleVo;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("circleService")
public class CircleServiceImpl implements CircleService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CircleDao circleDao;
    @Autowired
    private CircleImgDao circleImgDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    @Autowired
    private CircleLogDao circleLogDao;


    @Autowired
    private SqlSessionTemplate sqlSession;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(圈子分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CircleVo>
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    @Override
    public PageResult<CircleVo> findCircleList(CircleQVo condition, int start, int limit, String orderBy) throws Exception {
        SqlSession session = this.sqlSession.getSqlSessionFactory().openSession(ExecutorType.REUSE);
        Configuration configuration = session.getConfiguration();
        configuration.setSafeResultHandlerEnabled(false);
        CircleDao dao = session.getMapper(CircleDao.class);
        PageResult<CircleVo> rst = new PageResult<>();
        try {
            List<CircleVo> list = dao.findList(condition, start, limit, orderBy);
            rst.setRows(list);
            if (start <= 1) {
                int allRows = dao.findCount(condition);
                rst.setTotal(allRows);
            }
        }catch (Exception e){
            session.close();
        }finally {
            session.close();
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertCircle
     * @Description: TODO(添加圈子)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    @Override
    public MessageResponse insertCircle(Circle o, List<CircleImg> imgs) throws Exception {
        if (CommonUtils.isBlank(o.getCorpId())) {
            return new MessageResponse(1, "单位编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getUid())) {
            return new MessageResponse(1, "用户编号不能为空！");
        }
        o.setId(GUIDUtil.getGUID());
        o.setStatus("1");
        o.setCreateTime(new Date());
        for (CircleImg img : imgs) {
            img.setId(GUIDUtil.getGUID());
            img.setCircleId(o.getId());
            img.setCreateTime(new Date());
            this.circleImgDao.insert(img);
        }
        this.circleDao.insert(o);
        return new MessageResponse(0, "提交成功！");
    }

    /**
     * @throws
     * @Title:updateCircle
     * @Description: TODO(更新圈子)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    @Override
    public MessageResponse updateCircle(Circle o, List<CircleImg> imgs) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "单位编号不能为空！");
        }
        o.setStatus("1");
        o.setCreateTime(new Date());
        this.circleImgDao.deleteByCircleId(o.getId());
        for (CircleImg img : imgs) {
            img.setId(GUIDUtil.getGUID());
            img.setCircleId(o.getId());
            img.setCreateTime(new Date());
            this.circleImgDao.insert(img);
        }
        this.circleDao.updateByPrimaryKey(o);
        return new MessageResponse(0, "提交成功！");
    }

    /**
     * @throws
     * @Title:selectCircleByPrimaryKey
     * @Description: TODO(获取圈子)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Circle>
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    @Override
    public SingleResult
            <CircleVo> selectCircleByPrimaryKey(String id) throws Exception {
        SingleResult<CircleVo> rst = new SingleResult<>();
        SqlSession session = this.sqlSession.getSqlSessionFactory().openSession(ExecutorType.REUSE);
        Configuration configuration = session.getConfiguration();
        configuration.setSafeResultHandlerEnabled(false);
        CircleDao dao = session.getMapper(CircleDao.class);
        try {
            rst.setValue(dao.selectVoByPrimaryKey(id));
        }catch (Exception e){
            session.close();
        }finally {
            session.close();
        }

        return rst;
    }

    /**
     * @throws
     * @Title:deleteCircleByCircleId
     * @Description: TODO(删除圈子)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    @Override
    public MessageResponse deleteCircleByCircleId(String id, UserProp userProp) throws
            Exception {
        this.circleDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除圈子", "圈子", id, id,
                "圈子", userProp);
        return new MessageResponse(0, "圈子删除完成！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核圈子)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    @Override
    public MessageResponse audit(String id,String rst,String text, UserProp userProp) throws Exception {
        if (StringUtils.isEmpty(id)) {
            return new MessageResponse(1, "报道ID不能为空!");
        }
        if (StringUtils.isEmpty(rst)) {
            return new MessageResponse(1, "审核结果不能为空!");
        }
        if (StringUtils.isEmpty(text)) {
            return new MessageResponse(1, "审核说明不能为空!");
        }
        String logId=GUIDUtil.getGUID();
        CircleLog log=new CircleLog();
        log.setId(logId);
        log.setPid(id);
        log.setAuditor(userProp.getName());
        log.setRst(rst);
        log.setStatement(text);
        log.setCreateDate(new Date());
        this.circleLogDao.insert(log);
        this.circleDao.updateStatus(id,rst,logId);
        dataBaseLogService.log("审核圈子", "圈子", id, id,"圈子", userProp);
        return new MessageResponse(0, "审核成功！");
    }

}
