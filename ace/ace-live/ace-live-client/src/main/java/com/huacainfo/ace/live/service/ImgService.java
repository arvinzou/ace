package com.huacainfo.ace.live.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.live.model.Img;
import com.huacainfo.ace.live.vo.ImgVo;
import com.huacainfo.ace.live.vo.ImgQVo;
/**
 * @author: 王恩
 * @version: 2018-01-30
 * @Description:  TODO(图片)
 */
public interface ImgService {
    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(图片分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<ImgVo>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-01-30
	 */
	public abstract PageResult<ImgVo> findImgList(ImgQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
	 *
	    * @Title:insertImg
	    * @Description:  TODO(添加图片)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-01-30
	 */
	public abstract MessageResponse insertImg(Img obj,UserProp userProp) throws Exception;

    /**
	 *
	    * @Title:updateImg
	    * @Description:  TODO(更新图片)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-01-30
	 */
	public abstract MessageResponse updateImg(Img obj,UserProp userProp) throws Exception;
    /**
	 *
	    * @Title:selectImgByPrimaryKey
	    * @Description:  TODO(获取图片)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Img>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-01-30
	 */
	public abstract SingleResult<ImgVo> selectImgByPrimaryKey(String id) throws Exception;
    /**
	 *
	    * @Title:deleteImgByImgId
	    * @Description:  TODO(删除图片)
	 		* @param:        @param id
	 		* @param:        @param  userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-01-30
	 */
	public abstract MessageResponse deleteImgByImgId(String id,UserProp userProp) throws Exception;

	
}
