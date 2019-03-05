package com.huacainfo.ace.partyschool.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.partyschool.model.TopicRst;
import com.huacainfo.ace.partyschool.vo.TopicRstVo;
import com.huacainfo.ace.partyschool.vo.TopicRstQVo;
import java.util.Map;
import java.util.List;
/**
* @author: 王恩
* @version: 2019-03-05
* @Description:  TODO(测试答案管理)
*/
public interface TopicRstService {
/**
*
* @Title:find!{bean.name}List
* @Description:  TODO(测试答案管理分页查询)
* @param:        @param condition
* @param:        @param start
* @param:        @param limit
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       PageResult
<TopicRstVo>
    * @throws
    * @author: 王恩
    * @version: 2019-03-05
    */
    PageResult<TopicRstVo> findTopicRstList(TopicRstQVo condition,int start, int limit, String orderBy) throws Exception;

        /**
        *
        * @Title:insertTopicRst
        * @Description: TODO(添加测试答案管理)
        * @param: @param obj
        * @param: @param userProp
        * @param: @throws Exception
        * @return: MessageResponse
        * @throws
        * @author: 王恩
        * @version: 2019-03-05
        */
        MessageResponse insertTopicRst(TopicRst obj,UserProp userProp) throws Exception;
        MessageResponse insertTopicRstList( List<TopicRstQVo> listPram,UserProp userProp) throws Exception;

        /**
        *
        * @Title:updateTopicRst
        * @Description: TODO(更新测试答案管理)
        * @param: @param obj
        * @param: @param userProp
        * @param: @throws Exception
        * @return: MessageResponse
        * @throws
        * @author: 王恩
        * @version: 2019-03-05
        */
        MessageResponse updateTopicRst(TopicRst obj,UserProp userProp) throws Exception;

        /**
        *
        * @Title:selectTopicRstByPrimaryKey
        * @Description: TODO(获取测试答案管理)
        * @param: @param id
        * @param: @throws Exception
        * @return: SingleResult<TopicRst>
        * @throws
        * @author: 王恩
        * @version: 2019-03-05
        */
        SingleResult<TopicRstVo> selectTopicRstByPrimaryKey(String id) throws Exception;

            /**
            *
            * @Title:deleteTopicRstByTopicRstId
            * @Description: TODO(删除测试答案管理)
            * @param: @param id
            * @param: @param userProp
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: 王恩
            * @version: 2019-03-05
            */
            MessageResponse deleteTopicRstByTopicRstId(String id,UserProp userProp) throws Exception;

            /**
            *
            * @Title:audit
            * @Description: TODO(审核测试答案管理)
            * @param: @param id bean.id
            * @param: @param rst 审核结果 3-通过 4-拒绝
            * @param: @param remark 审核备注
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: 王恩
            * @version: 2019-03-05
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
            	    * @version: 2019-03-05
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
            	    * @version: 2019-03-05
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
                     * @version: 2019-03-05
                     */
                    public Map<String, Object> getListByCondition(Map<String, Object> params);



                    /**
                         * @throws
                         * @Title:deleteRoadSectionByRoadSectionIds
                         * @Description: TODO(批量删除测试答案管理）
                         * @param: @param ids
                         * @param: @param userProp
                         * @param: @throws Exception
                         * @return: MessageResponse
                         * @author: 王恩
                         * @version: 2019-03-05
                         */
                       public MessageResponse deleteTopicRstByTopicRstIds(String [] id, UserProp userProp) throws Exception;


                        /**
                            * @throws
                            * @Title:updateStatus
                            * @Description: TODO(更新状态)
                            * @param: @param obj
                            * @param: @param userProp
                            * @param: @throws Exception
                            * @return: MessageResponse
                         * @author: 王恩
                         * @version: 2019-03-05
                            */
                           MessageResponse updateStatus(String id,String status, UserProp userProp) throws Exception;
 }
