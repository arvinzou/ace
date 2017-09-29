package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.portal.model.Attach;
import com.huacainfo.ace.portal.vo.AttachQVo;
import com.huacainfo.ace.portal.vo.AttachVo;

public interface AttachService {
	/**
	 * 
	    * @Title:findAttachList 
	    * @Description:  附件分页查询
	 		* @param:        @param condition
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       ListResult<AttachVo>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月16日 下午3:10:05
	 */
	public abstract ListResult<AttachVo> findAttachList(AttachQVo condition) throws Exception;
	/**
	 * 
	    * @Title:deleteAttachByFileName 
	    * @Description:  附件删除
	 		* @param:        @param id
	 		* @param:        @param userProp
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月16日 下午3:11:00
	 */
	public abstract MessageResponse deleteAttachByFileName(String id,UserProp userProp) throws Exception;
	/**
	 * 
	    * @Title:upload 
	    * @Description:  附件文件信息保存
	 		* @param:        @param file
	 		* @param:        @param noticeId
	 		* @param:        @param userProp
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       ListResult<Attach>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月16日 下午3:11:21
	 */
	public abstract ListResult<Attach> upload(Attach[] file,String noticeId,UserProp userProp) throws Exception;
}
