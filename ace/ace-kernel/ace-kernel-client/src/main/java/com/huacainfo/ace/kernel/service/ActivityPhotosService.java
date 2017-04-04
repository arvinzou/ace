package com.huacainfo.ace.kernel.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.kernel.model.ActivityPhotos;
import com.huacainfo.ace.kernel.vo.ActivityPhotosVo;
import com.huacainfo.ace.kernel.vo.ActivityPhotosQVo;
public interface ActivityPhotosService {
	
	public abstract PageResult<ActivityPhotosVo> findActivityPhotosList(ActivityPhotosQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertActivityPhotos(ActivityPhotos obj,UserProp userProp) throws Exception;
	public abstract MessageResponse updateActivityPhotos(ActivityPhotos obj,UserProp userProp) throws Exception;
	public abstract SingleResult<ActivityPhotos> selectActivityPhotosByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deleteActivityPhotosByActivityPhotosId(String id,UserProp userProp) throws Exception;

	
}
