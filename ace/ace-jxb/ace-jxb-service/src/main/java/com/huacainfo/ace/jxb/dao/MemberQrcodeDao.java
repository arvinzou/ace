package com.huacainfo.ace.jxb.dao;

import com.huacainfo.ace.jxb.model.MemberQrcode;
import com.huacainfo.ace.jxb.vo.MemberQrcodeQVo;
import com.huacainfo.ace.jxb.vo.MemberQrcodeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberQrcodeDao {

    MemberQrcode selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(MemberQrcode record);

    int insertSelective(MemberQrcode record);

    int updateByPrimaryKey(MemberQrcode record);

    int updateByPrimaryKeySelective(MemberQrcode record);

    MemberQrcodeVo selectVoByPrimaryKey(String id);

    List
            <MemberQrcodeVo> findList(@Param("condition") MemberQrcodeQVo condition,
                                      @Param("start") int start,
                                      @Param("limit") int limit,
                                      @Param("orderBy") String orderBy);

    int findCount(@Param("condition") MemberQrcodeQVo condition);

    int isExit(MemberQrcode record);

}