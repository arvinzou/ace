package com.huacainfo.ace.jxb.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.jxb.dao.StudioDao;
import com.huacainfo.ace.jxb.dao.StudioImgDao;
import com.huacainfo.ace.jxb.model.Counselor;
import com.huacainfo.ace.jxb.model.Studio;
import com.huacainfo.ace.jxb.model.StudioImg;
import com.huacainfo.ace.jxb.service.BisMsgNoticeService;
import com.huacainfo.ace.jxb.service.CounselorService;
import com.huacainfo.ace.jxb.service.StudioService;
import com.huacainfo.ace.jxb.vo.CounselorQVo;
import com.huacainfo.ace.jxb.vo.CounselorVo;
import com.huacainfo.ace.jxb.vo.StudioQVo;
import com.huacainfo.ace.jxb.vo.StudioVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("studioService")
/**
 * @author: Arvin
 * @version: 2018-07-25
 * @Description: TODO(工作室)
 */
public class StudioServiceImpl implements StudioService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private StudioDao studioDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private StudioImgDao studioImgDao;
    @Autowired
    private CounselorService counselorService;
    @Autowired
    private BisMsgNoticeService bisMsgNoticeService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(工作室分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <StudioVo>
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public PageResult<StudioVo> findStudioList(StudioQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<StudioVo> rst = new PageResult<>();
        List<StudioVo> list = studioDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = studioDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    @Override
    public PageResult<StudioVo> findTopStudioList(StudioQVo condition, int start, int limit, String orderBy) throws Exception{
        PageResult<StudioVo> rst = new PageResult<>();
        List<StudioVo> list = studioDao.findTopList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = studioDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertStudio
     * @Description: TODO(添加工作室)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public MessageResponse insertStudio(Studio o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        o.setCounselorId(userProp.getUserId());
        if (CommonUtils.isBlank(o.getCounselorId())) {
            return new MessageResponse(1, "负责人不能为空！");
        }
        if (CommonUtils.isBlank(o.getIntroduce())) {
            return new MessageResponse(1, "介绍不能为空！");
        }
        List<Studio> temp = this.studioDao.isExit(o);
        if (temp.size() == 0) {
            o.setId(GUIDUtil.getGUID());
            o.setCreateDate(new Date());
            o.setStatus("0");
            this.studioDao.insertSelective(o);
        } else if (temp.size() == 1) {
            if (!o.getCounselorId().equals(temp.get(0).getCounselorId())) {
                return new MessageResponse(1, "工作室名字重复！");
            }
            this.studioDao.updateByCounselorIdSelective(o);

        } else if (temp.size() > 1) {
            return new MessageResponse(1, "工作室名字重复！");
        }


        this.dataBaseLogService.log("添加工作室", "工作室", "",
                o.getId(), o.getId(), userProp);
        return new MessageResponse(0, "添加工作室完成！");
    }


    /**
     * @throws
     * @Title:insertStudio
     * @Description: TODO(添加工作室)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public MessageResponse modifyStudio(Studio o, List<String> list, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getIntroduce())) {
            return new MessageResponse(1, "介绍不能为空！");
        }
        this.studioDao.updateByPrimaryKeySelective(o);
        this.studioImgDao.deleteByStudioId(o.getId());
        StudioImg si = new StudioImg();
        for (String item : list) {
            si.setId(GUIDUtil.getGUID());
            si.setCreateDate(new Date());
            si.setStudioId(o.getId());
            si.setImgUrl(item);
            this.studioImgDao.insert(si);
        }
//        this.dataBaseLogService.log("修改工作室", "工作室", "",
//                o.getId(), o.getId(), userProp);
        return new MessageResponse(0, "修改工作室完成！");
    }

    /**
     * @throws
     * @Title:insertStudio
     * @Description: TODO(添加工作室)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public MessageResponse insertStudioVo(Studio o, List<String> list, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getIntroduce())) {
            return new MessageResponse(1, "介绍不能为空！");
        }
        String studioId = GUIDUtil.getGUID();
        o.setId(studioId);
        o.setCounselorId(userProp.getUserId());
        o.setCreateDate(new Date());
        o.setStatus("0");
        this.studioDao.insertSelective(o);
        StudioImg si = new StudioImg();
        for (String item : list) {
            si.setId(GUIDUtil.getGUID());
            si.setCreateDate(new Date());
            si.setStudioId(studioId);
            si.setImgUrl(item);
            this.studioImgDao.insert(si);
        }
//        this.dataBaseLogService.log("添加工作室", "工作室", "",
//                o.getId(), o.getId(), userProp);
        return new MessageResponse(0, "添加工作室完成！");
    }

    /**
     * @throws
     * @Title:updateStudio
     * @Description: TODO(更新工作室)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public MessageResponse updateStudio(Studio o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getCounselorId())) {
            return new MessageResponse(1, "负责人不能为空！");
        }
        if (CommonUtils.isBlank(o.getIntroduce())) {
            return new MessageResponse(1, "介绍不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }

        studioDao.updateByPrimaryKeySelective(o);
        dataBaseLogService.log("变更工作室", "工作室", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更工作室完成！");
    }

    /**
     * @throws
     * @Title:selectStudioByPrimaryKey
     * @Description: TODO(获取工作室)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Studio>
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public SingleResult<StudioVo> selectStudioByPrimaryKey(String id) throws Exception {
        SingleResult<StudioVo> rst = new SingleResult<>();
        rst.setValue(this.studioDao.selectVoByPrimaryKey(id));
        return rst;
    }


    /**
     * @throws
     * @Title:selectStudioByPrimaryKey
     * @Description: TODO(获取工作室)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Studio>
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public SingleResult<StudioVo> selectStudioInfo(String id) throws Exception {
        SingleResult<StudioVo> rst = new SingleResult<>();

        StudioVo studioVo = this.studioDao.selectVoByPrimaryKey(id);
        studioVo.setImgList(studioImgDao.findImgList(studioVo.getId()));
        rst.setValue(studioVo);
        return rst;
    }


    /**
     * @throws
     * @Title:selectStudioByPrimaryKey
     * @Description: TODO(获取工作室)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Studio>
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public ResultResponse getMyStudioInfo(String counselorId) throws Exception {

        Studio studio = this.studioDao.selectVoByCounselorId(counselorId);
        return new ResultResponse(ResultCode.SUCCESS, "工作室信息", studio);
    }

    /**
     * @throws
     * @Title:deleteStudioByStudioId
     * @Description: TODO(删除工作室)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public MessageResponse deleteStudioByStudioId(String id, UserProp userProp) throws Exception {
        this.studioDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除工作室", "工作室",
                String.valueOf(id),
                String.valueOf(id), "工作室", userProp);
        return new MessageResponse(0, "工作室删除完成！");
    }

    /**
     * 工作室审核
     *
     * @param studioId
     * @param auditRs     审核结果 1-审核通过 2-审核拒绝
     * @param curUserProp
     * @return
     */
    @Override
    public MessageResponse audit(String studioId, String auditRs, UserProp curUserProp) throws Exception {
        StudioVo studioVo = studioDao.selectVoByPrimaryKey(studioId);
        if (null == studioVo) {
            return new MessageResponse(ResultCode.FAIL, "工作室资料丢失");
        }

        studioVo.setStatus(auditRs);
        MessageResponse rs = updateStudio(studioVo, curUserProp);
        if (rs.getStatus() == ResultCode.SUCCESS) {
            // 2018/8/23 工作室审核消息发放
            try {
                ResultResponse msgRs = bisMsgNoticeService.studioAuditMsg(studioVo, auditRs);
                logger.debug("工作室审核消息结果：{}", msgRs);
            } catch (Exception e) {
                logger.error("工作室审核消息发送异常：{}", e);
            }

            return new MessageResponse(ResultCode.SUCCESS, "审核成功");
        }

        return rs;
    }

    /**
     * 获取我的工作室列表
     *
     * @param counselorId 咨询师主键id
     * @return List<StudioVo>
     */
    @Override
    public Map<String, Object> getStudioList(String counselorId) throws Exception {

        Counselor counselor = counselorService.selectCounselorByPrimaryKey(counselorId).getValue();
        if (null == counselor) {
            throw new CustomException("咨询师资料丢失");
        }
        //StudioVo
        StudioVo join = null;
        if (StringUtil.isNotEmpty(counselor.getStudioId())) {
            join = studioDao.selectVoByPrimaryKey(counselor.getStudioId());
        }

        //我的工作室
        StudioQVo condition = new StudioQVo();
        condition.setCounselorId(counselorId);
        condition.setStatus("1");
        List<StudioVo> my = studioDao.findList(condition, 0, 0 + 100, "");


        Map<String, Object> rtnMap = new HashMap<>();
        rtnMap.put("my", my);
        rtnMap.put("join", join);

        return rtnMap;
    }

    /**
     * 获取工作室详情
     *
     * @param studioId 工作室ID
     * @return StudioVo
     */
    @Override
    public StudioVo getStudioDetail(String studioId) throws Exception {
        StudioVo studioVo = selectStudioByPrimaryKey(studioId).getValue();
        if (null == studioVo) {
            return null;
        }
        studioVo.setImgList(studioImgDao.findImgList(studioVo.getId()));
        //查询成员列表
        CounselorQVo condition = new CounselorQVo();//, int start, int limit, String orderBy
        condition.setStudioId(studioId);
        List<CounselorVo> memberList = counselorService.findCounselorList(condition, 0, 0 + 1000, "").getRows();

        studioVo.setMemberList(memberList);

        return studioVo;
    }

    @Override
    public Map<String, Object> findUserInfoByStudioId(String studioId) {
        return studioDao.findUserInfoByStudioId(studioId);
    }

}
