package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;

public interface FilesService {
	/**
	 * 
	    * @Title:insertFiles 
	    * @Description:  TODO(保存文件队列) 
	 		* @param:        @param filePath
	 		* @param:        @param userProp
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 上午11:32:09
	 */
	public abstract MessageResponse insertFiles(String filePath, UserProp userProp)
			throws Exception;
	/**
	 * 
	    * @Title:updateFiles 
	    * @Description:  TODO(更新文件队列) 
	 		* @param:        @param filePath
	 		* @param:        @param userProp
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 上午11:32:26
	 */
	public abstract MessageResponse updateFiles(String filePath, UserProp userProp)
			throws Exception;
	/**
	 * 
	    * @Title:deleteFiles 
	    * @Description:  TODO(删除上传文件队列) 
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 上午11:32:40
	 */
	public abstract MessageResponse deleteFiles() throws Exception;
	
	/**
	 * 
	    * @Title:deleteFileTimer 
	    * @Description:  TODO(定时调度删除临时文件) 
	 		* @param:            
	 		* @return:       void    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月26日 下午5:19:06
	 */
	public void deleteFileTimer();
}
