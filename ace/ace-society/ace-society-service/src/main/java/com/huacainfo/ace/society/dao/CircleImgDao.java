package com.huacainfo.ace.society.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.society.model.CircleImg;
import com.huacainfo.ace.society.vo.CircleImgQVo;
import com.huacainfo.ace.society.vo.CircleImgVo;

public interface CircleImgDao {

    int deleteByCircleId(String id);

    int insert(CircleImg record);

}