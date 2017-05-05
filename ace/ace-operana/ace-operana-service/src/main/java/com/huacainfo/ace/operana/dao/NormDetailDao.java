package com.huacainfo.ace.operana.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.operana.model.NormDetail;
import com.huacainfo.ace.operana.vo.NormDetailQVo;
import com.huacainfo.ace.operana.vo.NormDetailVo;

public interface NormDetailDao {
    int deleteByPrimaryKey(String NormDetailId);

    int insert(NormDetail record);


    NormDetail selectByPrimaryKey(String NormDetailId);


    int updateByPrimaryKey(NormDetail record);
    


	int isExit(NormDetail record);

    List<Map<String,Object>> selectNormDetailByMeetingAndTopicIdAndNormId(@Param("meetingId") String meetingId, @Param("topicId") String topicId, @Param("normId") String normId);

}