package com.huacainfo.ace.partyschool.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.*;
import com.huacainfo.ace.partyschool.model.TopicOpt;
import com.huacainfo.ace.partyschool.vo.TopicOptVo;
import com.huacainfo.ace.partyschool.vo.TopicOptQVo;
import java.util.Map;
import java.util.List;
/**
* @author: 王恩
* @version: 2019-02-28
* @Description:  TODO(试题选项管理)
*/
public interface TopicOptService {
/**
*
* @Title:find!{bean.name}List
* @Description:  TODO(试题选项管理分页查询)
* @param:        @param condition
* @param:        @param start
* @param:        @param limit
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       PageResult
<TopicOptVo>
    * @throws
    * @author: 王恩
    * @version: 2019-02-28
    */
    PageResult<TopicOptVo> findTopicOptList(TopicOptQVo condition,int start, int limit, String orderBy) throws Exception;
	MessageResponse insertTopicOptList(List<TopicOpt> options,String topicId, UserProp userProp)throws  Exception;

        /**
        *
        * @Title:insertTopicOpt
        * @Description: TODO(添加试题选项管理)
        * @param: @param obj
        * @param: @param userProp
        * @param: @throws Exception
        * @return: MessageResponse
        * @throws
        * @author: 王恩
        * @version: 2019-02-28
        */
        MessageResponse insertTopicOpt(TopicOpt obj,UserProp userProp) throws Exception;

        /**
        *
        * @Title:updateTopicOpt
        * @Description: TODO(更新试题选项管理)
        * @param: @param obj
        * @param: @param userProp
        * @param: @throws Exception
        * @return: MessageResponse
        * @throws
        * @author: 王恩
        * @version: 2019-02-28
        */
        MessageResponse updateTopicOpt(TopicOpt obj,UserProp userProp) throws Exception;

        /**
        *
        * @Title:selectTopicOptByPrimaryKey
        * @Description: TODO(获取试题选项管理)
        * @param: @param id
        * @param: @throws Exception
        * @return: SingleResult<TopicOpt>
        * @throws
        * @author: 王恩
        * @version: 2019-02-28
        */
        SingleResult<TopicOptVo> selectTopicOptByPrimaryKey(String id) throws Exception;

            /**
            *
            * @Title:deleteTopicOptByTopicOptId
            * @Description: TODO(删除试题选项管理)
            * @param: @param id
            * @param: @param userProp
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: 王恩
            * @version: 2019-02-28
            */
            MessageResponse deleteTopicOptByTopicOptId(String id,UserProp userProp) throws Exception;

            /**
            *
            * @Title:audit
            * @Description: TODO(审核试题选项管理)
            * @param: @param id bean.id
            * @param: @param rst 审核结果 3-通过 4-拒绝
            * @param: @param remark 审核备注
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: 王恩
            * @version: 2019-02-28
            */
            MessageResponse audit(String id,String rst, String remark, UserProp userProp) throws Exception;


            /**
            	 *
            	    * @Title:importXls
            	    * @Description:  TODO(Excel导入资源数据)
            	 		* @param:        @param list
            	 		* @param:        @param userProp
            	 		* @param:        @return
            	 		* @param:        @throws Exception
            	 		* @return:       MessageResponse
            	 		* @throws
            	    * @author: 王恩
            	    * @version: 2019-02-28
            	 */
            	public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp)throws Exception;


 /**
            	 *
            	    * @Title:getList
            	    * @Description:  TODO(条件查询数据)
            	 		* @param:        @param p
            	 		* @param:        @return
            	 		* @param:        @throws Exception
            	 		* @return:       ListResult<Map<String,Object>>
            	 		* @throws
            	    * @author: 王恩
            	    * @version: 2019-02-28
            	 */
            	public ListResult<Map<String,Object>> getList(Map<String, Object> p) throws Exception;


            	/**
                     * @throws
                     * @Title:getListByCondition
                     * @Description: TODO(用于控件数据获取)
                     * @param: @param params
                     * @param: @return
                     * @return: Map<String,Object>
                     * @author: 王恩
                     * @version: 2019-02-28
                     */
                    public Map<String, Object> getListByCondition(Map<String, Object> params);



                    /**
                         * @throws
                         * @Title:deleteRoadSectionByRoadSectionIds
                         * @Description: TODO(批量删除试题选项管理）
                         * @param: @param ids
                         * @param: @param userProp
                         * @param: @throws Exception
                         * @return: MessageResponse
                         * @author: 王恩
                         * @version: 2019-02-28
                         */
                       public MessageResponse deleteTopicOptByTopicOptIds(String [] id, UserProp userProp) throws Exception;


                        /**
                            * @throws
                            * @Title:updateStatus
                            * @Description: TODO(更新状态)
                            * @param: @param obj
                            * @param: @param userProp
                            * @param: @throws Exception
                            * @return: MessageResponse
                         * @author: 王恩
                         * @version: 2019-02-28
                            */
                           MessageResponse updateStatus(String id,String status, UserProp userProp) throws Exception;
 }
