package com.huacainfo.ace.partyschool.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.partyschool.model.TestRst;
import com.huacainfo.ace.partyschool.vo.TestRstVo;
import com.huacainfo.ace.partyschool.vo.TestRstQVo;
import java.util.Map;
import java.util.List;
/**
* @author: 王恩
* @version: 2019-03-06
* @Description:  TODO(测试答案管理)
*/
public interface TestRstService {
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
<TestRstVo>
    * @throws
    * @author: 王恩
    * @version: 2019-03-06
    */
    PageResult<TestRstVo> findTestRstList(TestRstQVo condition,int start, int limit, String orderBy) throws Exception;

        /**
        *
        * @Title:insertTestRst
        * @Description: TODO(添加测试答案管理)
        * @param: @param obj
        * @param: @param userProp
        * @param: @throws Exception
        * @return: MessageResponse
        * @throws
        * @author: 王恩
        * @version: 2019-03-06
        */
        MessageResponse insertTestRst(TestRst obj,UserProp userProp) throws Exception;

        /**
        *
        * @Title:updateTestRst
        * @Description: TODO(更新测试答案管理)
        * @param: @param obj
        * @param: @param userProp
        * @param: @throws Exception
        * @return: MessageResponse
        * @throws
        * @author: 王恩
        * @version: 2019-03-06
        */
        MessageResponse updateTestRst(TestRst obj,UserProp userProp) throws Exception;

        /**
        *
        * @Title:selectTestRstByPrimaryKey
        * @Description: TODO(获取测试答案管理)
        * @param: @param id
        * @param: @throws Exception
        * @return: SingleResult<TestRst>
        * @throws
        * @author: 王恩
        * @version: 2019-03-06
        */
        SingleResult<TestRstVo> selectTestRstByPrimaryKey(String id) throws Exception;

            /**
            *
            * @Title:deleteTestRstByTestRstId
            * @Description: TODO(删除测试答案管理)
            * @param: @param id
            * @param: @param userProp
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: 王恩
            * @version: 2019-03-06
            */
            MessageResponse deleteTestRstByTestRstId(String id,UserProp userProp) throws Exception;

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
            * @version: 2019-03-06
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
            	    * @version: 2019-03-06
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
            	    * @version: 2019-03-06
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
                     * @version: 2019-03-06
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
                         * @version: 2019-03-06
                         */
                       public MessageResponse deleteTestRstByTestRstIds(String [] id, UserProp userProp) throws Exception;


                        /**
                            * @throws
                            * @Title:updateStatus
                            * @Description: TODO(更新状态)
                            * @param: @param obj
                            * @param: @param userProp
                            * @param: @throws Exception
                            * @return: MessageResponse
                         * @author: 王恩
                         * @version: 2019-03-06
                            */
                           MessageResponse updateStatus(String id,String status, UserProp userProp) throws Exception;
 }
