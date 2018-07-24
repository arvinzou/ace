package com.huacainfo.ace.jxb.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.jxb.dao.CounselorDao;
import com.huacainfo.ace.jxb.model.Counselor;
import com.huacainfo.ace.jxb.service.CounselorService;
import com.huacainfo.ace.jxb.vo.CounselorQVo;
import com.huacainfo.ace.jxb.vo.CounselorVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("counselorService")
/**
 * @author: Arvin
 * @version: 2018-07-20
 * @Description: TODO(咨询师)
 */
public class CounselorServiceImpl implements CounselorService {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CounselorDao counselorDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(咨询师分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CounselorVo>
     * @author: Arvin
     * @version: 2018-07-20
     */
    @Override
    public PageResult<CounselorVo> findCounselorList(CounselorQVo condition, int start,
                                                     int limit, String orderBy) throws Exception {
        PageResult<CounselorVo> rst = new PageResult<>();
        List<CounselorVo> list = counselorDao.findList(condition, start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = counselorDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertCounselor
     * @Description: TODO(添加咨询师)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-20
     */
    @Override
    public MessageResponse insertCounselor(Counselor o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "姓名不能为空！");
        }
        if (CommonUtils.isBlank(o.getMobile())) {
            return new MessageResponse(1, "手机号不能为空！");
        }
        if (CommonUtils.isBlank(o.getImagePhotoUrl())) {
            return new MessageResponse(1, "形象照不能为空！");
        }
        if (CommonUtils.isBlank(o.getIdCard())) {
            return new MessageResponse(1, "身份证号不能为空！");
        }
        if (CommonUtils.isBlank(o.getIdCardImgUrl())) {
            return new MessageResponse(1, "身份证件电子档不能为空！");
        }
        if (CommonUtils.isBlank(o.getCertificateNo())) {
            return new MessageResponse(1, "资格从业证书号不能为空！");
        }
        if (CommonUtils.isBlank(o.getCertificateImgUrl())) {
            return new MessageResponse(1, "资格从业证书电子档不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvidenceImgUrl())) {
            return new MessageResponse(1, "身份证持胸前自拍照不能为空！");
        }
        if (CommonUtils.isBlank(o.getLevel())) {
            return new MessageResponse(1, "级别不能为空！");
        }


        int temp = counselorDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "咨询师名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        counselorDao.insertSelective(o);
        dataBaseLogService.log("添加咨询师", "咨询师", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加咨询师完成！");
    }

    /**
     * @throws
     * @Title:updateCounselor
     * @Description: TODO(更新咨询师)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-20
     */
    @Override
    public MessageResponse updateCounselor(Counselor o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "姓名不能为空！");
        }
        if (CommonUtils.isBlank(o.getMobile())) {
            return new MessageResponse(1, "手机号不能为空！");
        }
        if (CommonUtils.isBlank(o.getImagePhotoUrl())) {
            return new MessageResponse(1, "形象照不能为空！");
        }
        if (CommonUtils.isBlank(o.getIdCard())) {
            return new MessageResponse(1, "身份证号不能为空！");
        }
        if (CommonUtils.isBlank(o.getIdCardImgUrl())) {
            return new MessageResponse(1, "身份证件电子档不能为空！");
        }
        if (CommonUtils.isBlank(o.getCertificateNo())) {
            return new MessageResponse(1, "资格从业证书号不能为空！");
        }
        if (CommonUtils.isBlank(o.getCertificateImgUrl())) {
            return new MessageResponse(1, "资格从业证书电子档不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvidenceImgUrl())) {
            return new MessageResponse(1, "身份证持胸前自拍照不能为空！");
        }
        if (CommonUtils.isBlank(o.getLevel())) {
            return new MessageResponse(1, "级别不能为空！");
        }


        counselorDao.updateByPrimaryKeySelective(o);
        dataBaseLogService.log("变更咨询师", "咨询师", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更咨询师完成！");
    }

    /**
     * @throws
     * @Title:selectCounselorByPrimaryKey
     * @Description: TODO(获取咨询师)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Counselor>
     * @author: Arvin
     * @version: 2018-07-20
     */
    @Override
    public SingleResult<CounselorVo> selectCounselorByPrimaryKey(String id) throws Exception {
        SingleResult<CounselorVo> rst = new SingleResult<>();
        rst.setValue(counselorDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteCounselorByCounselorId
     * @Description: TODO(删除咨询师)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-20
     */
    @Override
    public MessageResponse deleteCounselorByCounselorId(String id, UserProp userProp)
            throws Exception {

        counselorDao.deleteByPrimaryKey(id);
        dataBaseLogService.log("删除咨询师", "咨询师",
                String.valueOf(id), String.valueOf(id), "咨询师", userProp);
        return new MessageResponse(0, "咨询师删除完成！");
    }

    /**
     * 注册老师资料
     *
     * @param mobile   手机号码
     * @param studioId 推荐人userid （portal.users.id）
     * @param userinfo 微信基本信息
     * @return
     */
    @Override
    public ResultResponse register(String mobile, String studioId, Userinfo userinfo) {
        Counselor counselor = counselorDao.selectByPrimaryKey(userinfo.getOpenid());
        if (null == counselor) {
            counselor = new Counselor();
            counselor.setId(userinfo.getUnionid());
            counselor.setStudioId(StringUtil.isEmpty(studioId) ? "0" : studioId);
            counselor.setName(userinfo.getNickname());
            counselor.setMobile(mobile);
            counselor.setLevel("0");
            counselor.setCreateDate(DateUtil.getNowDate());
            counselorDao.insertSelective(counselor);
        } else if (StringUtil.isEmpty(counselor.getMobile())) {
            counselor.setMobile(mobile);
            counselorDao.updateByPrimaryKeySelective(counselor);
        }

        return new ResultResponse(ResultCode.SUCCESS, "注册完成");
    }

}
