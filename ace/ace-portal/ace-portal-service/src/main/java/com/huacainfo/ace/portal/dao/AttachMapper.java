package com.huacainfo.ace.portal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huacainfo.ace.portal.model.Attach;
import com.huacainfo.ace.portal.vo.AttachQVo;
import com.huacainfo.ace.portal.vo.AttachVo;

public interface AttachMapper {
	
	int deleteByPrimaryKey(Integer attachId);

    int insert(Attach record);

    int insertSelective(Attach record);

    Attach selectByPrimaryKey(Integer attachId);

    int updateByPrimaryKeySelective(Attach record);

    int updateByPrimaryKey(Attach record);
    
    List<AttachVo> findList(@Param("condition") AttachQVo condition);
    
    int deleteByFileUrl(String fileUrl);
}