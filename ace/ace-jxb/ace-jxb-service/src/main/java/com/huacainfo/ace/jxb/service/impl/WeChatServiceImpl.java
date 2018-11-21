package com.huacainfo.ace.jxb.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.jxb.dao.MemberRelationDao;
import com.huacainfo.ace.jxb.model.MemberRelation;
import com.huacainfo.ace.jxb.service.WeChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Arvin
 * @Date: 2018/7/24 11:37
 * @Description:
 */
@Service("weChatService")
public class WeChatServiceImpl implements WeChatService {

    @Autowired
    private MemberRelationDao memberRelationDao;

    private ResultResponse updateMemberRelation(String scene, String openid) {

        MemberRelation relation = memberRelationDao.selectByPrimaryKey(openid);
        //
        if (null == relation) {
            relation = new MemberRelation();
            relation.setId(GUIDUtil.getGUID());
            relation.setOpenid(openid);
            relation.setUnionid(openid);
            relation.setStudioId(scene);
            relation.setStatus("1");
            relation.setCreateDate(DateUtil.getNowDate());
            memberRelationDao.insertSelective(relation);

            return new ResultResponse(ResultCode.SUCCESS, "插入关系完成");
        } else {
            relation.setStudioId(scene);
            relation.setUpdateDate(DateUtil.getNowDate());
            memberRelationDao.updateByPrimaryKeySelective(relation);

            return new ResultResponse(ResultCode.SUCCESS, "更新关系完成");
        }
    }

    /**
     * 二维码扫描 - 关注事件
     *
     * @param scene  二维码携带场景值
     * @param openid 扫描人openid
     * @return
     */
    @Override
    public ResultResponse qrCodeSubscribe(String scene, String openid) {


        return updateMemberRelation(scene, openid);

    }

    /**
     * 二维码扫描 - 扫描事件
     *
     * @param scene  二维码携带场景值
     * @param openid 扫描人openid
     * @return
     */
    @Override
    public ResultResponse qrCodeScan(String scene, String openid) {

        return updateMemberRelation(scene, openid);
    }
}
