package com.huacainfo.ace.jxb.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.jxb.dao.CounselorCheckResultDao;
import com.huacainfo.ace.jxb.dao.CounselorPostLevelDao;
import com.huacainfo.ace.jxb.dao.PostLevelDao;
import com.huacainfo.ace.jxb.model.CounselorCheckResult;
import com.huacainfo.ace.jxb.model.CounselorPostLevel;
import com.huacainfo.ace.jxb.model.PostLevel;
import com.huacainfo.ace.jxb.service.PostLevelService;
import com.huacainfo.ace.jxb.vo.PostLevelQVo;
import com.huacainfo.ace.jxb.vo.PostLevelVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("postLevelService")
/**
 * @author: Arvin
 * @version: 2018-08-08
 * @Description: (咨询师岗位级别配置)
 */
public class PostLevelServiceImpl implements PostLevelService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PostLevelDao postLevelDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private CounselorCheckResultDao counselorCheckResultDao;
    @Autowired
    private CounselorPostLevelDao counselorPostLevelDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(咨询师岗位级别配置分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <PostLevelVo>
     * @author: Arvin
     * @version: 2018-08-08
     */
    @Override
    public PageResult<PostLevelVo> findPostLevelList(PostLevelQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<PostLevelVo> rst = new PageResult<>();
        List<PostLevelVo> list = this.postLevelDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.postLevelDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertPostLevel
     * @Description: TODO(添加咨询师岗位级别配置)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-08
     */
    @Override
    public MessageResponse insertPostLevel(PostLevel o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getPostName())) {
            return new MessageResponse(1, "岗位名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getRatio())) {
            return new MessageResponse(1, "分配比例(分给老师的比例demo:0.5)不能为空！");
        }

        o.setPostIndex("1");
        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        postLevelDao.insertSelective(o);
        dataBaseLogService.log("添加咨询师岗位级别配置", "咨询师岗位级别配置", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加咨询师岗位级别配置完成！");
    }

    /**
     * @throws
     * @Title:updatePostLevel
     * @Description: TODO(更新咨询师岗位级别配置)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-08
     */
    @Override
    public MessageResponse updatePostLevel(PostLevel o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getPostName())) {
            return new MessageResponse(1, "岗位名称不能为空！");
        }
//        if (CommonUtils.isBlank(o.getPostIndex())) {
//            return new MessageResponse(1, "岗位序号不能为空！");
//        }
        if (CommonUtils.isBlank(o.getRatio())) {
            return new MessageResponse(1, "分配比例 (分给老师的比例demo:0.5)不能为空！");
        }

        this.postLevelDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更咨询师岗位级别配置", "咨询师岗位级别配置", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更咨询师岗位级别配置完成！");
    }

    /**
     * @throws
     * @Title:selectPostLevelByPrimaryKey
     * @Description: TODO(获取咨询师岗位级别配置)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<PostLevel>
     * @author: Arvin
     * @version: 2018-08-08
     */
    @Override
    public SingleResult<PostLevelVo> selectPostLevelByPrimaryKey(String id) throws Exception {
        SingleResult<PostLevelVo> rst = new SingleResult<>();
        rst.setValue(this.postLevelDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deletePostLevelByPostLevelId
     * @Description: TODO(删除咨询师岗位级别配置)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-08
     */
    @Override
    public MessageResponse deletePostLevelByPostLevelId(String id, UserProp userProp) throws
            Exception {
        this.postLevelDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除咨询师岗位级别配置", "咨询师岗位级别配置",
                String.valueOf(id), String.valueOf(id), "咨询师岗位级别配置", userProp);
        return new MessageResponse(0, "咨询师岗位级别配置删除完成！");
    }

    /**
     * 获取绩效考核数据
     *
     * @param quarter     1-第一季度，2-第二季度，3-第三季度，4-第四季度 必传
     * @param counselorId 咨询师id 可选
     * @return ResultResponse
     */
    @Override
    public ResultResponse examine(String quarter, String counselorId) {
        Map<String, Object> params = new HashMap<>();
        if (StringUtil.isNotEmpty(counselorId)) {
            params.put("counselorId", counselorId);
        }
        String nowTime = DateUtil.getNow();
        String nowYear = nowTime.substring(0, 4);
        String nowMonth = nowTime.substring(5, 7);
        String nowDay = nowTime.substring(8, 10);
        String quarterStr;
        switch (quarter) {
            case "1":
                quarterStr = "第一季度";
                params.put("startDt", nowYear + " 01-01 00:00:00");
                params.put("endDt", nowYear + " 03-31 23:59:59");
                break;
            case "2":
                quarterStr = "第二季度";
                params.put("startDt", nowYear + " 04-01 00:00:00");
                params.put("endDt", nowYear + " 06-30 23:59:59");
                break;
            case "3":
                quarterStr = "第三季度";
                params.put("startDt", nowYear + " 07-01 00:00:00");
                params.put("endDt", nowYear + " 09-30 23:59:59");
                break;
            case "4":
                quarterStr = "第四季度";
                params.put("startDt", nowYear + " 10-01 00:00:00");
                params.put("endDt", nowYear + " 12-31 23:59:59");
                break;
            default:
                quarterStr = "第一季度";
                params.put("startDt", nowYear + " 01-01 00:00:00");
                params.put("endDt", nowYear + " 03-31 23:59:59");
                break;
        }
        //获取考核数据
        List<Map<String, Object>> reportData = postLevelDao.examine(params);
        if (!CollectionUtils.isEmpty(reportData)) {
            counselorCheckResultDao.cleanData(nowYear, quarterStr, counselorId);
        }
        CounselorCheckResult checkResult;
        for (Map<String, Object> data : reportData) {
            checkResult = new CounselorCheckResult();
            checkResult.setCounselorId((String) data.get("id"));
            checkResult.setCounselorNum(Integer.parseInt(String.valueOf(data.get("cNum"))));
            checkResult.setTurnover(new BigDecimal(String.valueOf(data.get("cTurnover"))));
            checkResult.setCheckQuarter(quarterStr);
            //固定数值
            checkResult.setId(GUIDUtil.getGUID());
            checkResult.setCheckYear(nowYear);
            checkResult.setCheckMonth(nowMonth);
            checkResult.setCheckDay(nowDay);
            checkResult.setStatus("1");
            checkResult.setCreateDate(DateUtil.getNowDate());
            counselorCheckResultDao.insertSelective(checkResult);
        }
        //定岗
        determinePosts(nowYear, quarterStr);

        return new ResultResponse(ResultCode.SUCCESS, "考核完成");
    }

    /**
     * 咨询师定岗
     */
    public void determinePosts(String year, String quarter) {
        List<PostLevel> config = postLevelDao.findConfig();
        if (CollectionUtils.isEmpty(config)) {
            logger.error("============无咨询师岗位配置数据 [年]:[{}]，[季度]：[{}]===================", year, quarter);
            return;
        }

        List<CounselorCheckResult> dataList = counselorCheckResultDao.findByQuarter(year, quarter);
        CounselorPostLevel cPost;
        PostLevel nowPost;
        for (CounselorCheckResult data : dataList) {
            nowPost = getPostLevel(data, config);
            //更新岗位内容
            cPost = counselorPostLevelDao.findByCounselorId(data.getCounselorId());
            if (null == cPost) {
                cPost = new CounselorPostLevel();
                cPost.setCounselorId(data.getCounselorId());
                cPost.setPostId(nowPost.getId());

                cPost.setId(GUIDUtil.getGUID());
                cPost.setStatus("1");
                cPost.setCreateDate(DateUtil.getNowDate());
                counselorPostLevelDao.insertSelective(cPost);
            } else {
                cPost.setPostId(nowPost.getId());
                cPost.setUpdateDate(DateUtil.getNowDate());
                counselorPostLevelDao.updateByPrimaryKeySelective(cPost);
            }
        }
    }

    /***
     * 优先返回满足岗位标准的；没有，则分配最低岗位给他
     * @param data
     * @param config
     * @return String
     */
    private PostLevel getPostLevel(CounselorCheckResult data, List<PostLevel> config) {
        int num = data.getCounselorNum();
        BigDecimal turnover = data.getTurnover();

        for (PostLevel postLevel : config) {
            if (postLevel.getCounselorNum() <= num && postLevel.getTurnover().compareTo(turnover) <= 0) {
                return postLevel;
            }
        }

        return config.get(config.size() - 1);
    }
}
