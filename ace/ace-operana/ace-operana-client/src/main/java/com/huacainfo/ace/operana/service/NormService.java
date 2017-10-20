package com.huacainfo.ace.operana.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.operana.model.Norm;
import com.huacainfo.ace.operana.vo.NormVo;
import com.huacainfo.ace.operana.vo.NormQVo;
import java.util.Map;
import java.util.List;
public interface NormService {

	public abstract PageResult<NormVo> findNormList(NormQVo condition, int start, int limit, String orderBy)
			throws Exception;
	public abstract MessageResponse insertNorm(Norm obj, UserProp userProp) throws Exception;
	public abstract MessageResponse updateNorm(Norm obj, UserProp userProp) throws Exception;
	public abstract SingleResult<NormVo> selectNormByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deleteNormByNormId(String id, UserProp userProp) throws Exception;

	public abstract List<Map<String,Object>> selectAllNorm(String topicId,String name);

	public abstract Map<String, Object> selectNormByTopicId(String topicId) throws Exception;

}
