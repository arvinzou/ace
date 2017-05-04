package com.huacainfo.ace.operana.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.operana.model.NormData;
import com.huacainfo.ace.operana.vo.NormDataQVo;
import com.huacainfo.ace.operana.vo.NormDataVo;

public interface NormDataDao {

	int deleteByPrimaryKey(String NormDataId);

	int insert(NormData record);

	NormData selectByPrimaryKey(String NormDataId);

	int updateByPrimaryKey(NormData record);

	int isExit(NormData record);

	List<Map<String,Object>> selectNormByMeetingAndTopicId(@Param("meetingId") String meetingId,@Param("topicId") String topicId);

}