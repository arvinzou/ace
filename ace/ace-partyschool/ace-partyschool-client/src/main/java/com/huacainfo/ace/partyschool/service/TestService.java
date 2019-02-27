package com.huacainfo.ace.partyschool.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.partyschool.model.Test;
import com.huacainfo.ace.partyschool.vo.TestVo;
import com.huacainfo.ace.partyschool.vo.TestQVo;
import java.util.Map;
import java.util.List;
/**
* @author: 王恩
* @version: 2019-02-27
* @Description:  TODO(测评结果管理)
*/
public interface TestService {
/**
*
* @Title:find!{bean.name}List
* @Description:  TODO(测评结果管理分页查询)
* @param:        @param condition
* @param:        @param start
* @param:        @param limit
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       PageResult
<TestVo>
    * @throws
    * @author: 王恩
    * @version: 2019-02-27
    */
    PageResult<TestVo> findTestList(TestQVo condition,int start, int limit, String orderBy) throws Exception;

        /**
        *
        * @Title:insertTest
        * @Description: TODO(添加测评结果管理)
        * @param: @param obj
        * @param: @param userProp
        * @param: @throws Exception
        * @return: MessageResponse
        * @throws
        * @author: 王恩
        * @version: 2019-02-27
        */
        MessageResponse insertTest(Test obj,UserProp userProp) throws Exception;

        /**
        *
        * @Title:updateTest
        * @Description: TODO(更新测评结果管理)
        * @param: @param obj
        * @param: @param userProp
        * @param: @throws Exception
        * @return: MessageResponse
        * @throws
        * @author: 王恩
        * @version: 2019-02-27
        */
        MessageResponse updateTest(Test obj,UserProp userProp) throws Exception;

        /**
        *
        * @Title:selectTestByPrimaryKey
        * @Description: TODO(获取测评结果管理)
        * @param: @param id
        * @param: @throws Exception
        * @return: SingleResult<Test>
        * @throws
        * @author: 王恩
        * @version: 2019-02-27
        */
        SingleResult<TestVo> selectTestByPrimaryKey(String id) throws Exception;

            /**
            *
            * @Title:deleteTestByTestId
            * @Description: TODO(删除测评结果管理)
            * @param: @param id
            * @param: @param userProp
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: 王恩
            * @version: 2019-02-27
            */
            MessageResponse deleteTestByTestId(String id,UserProp userProp) throws Exception;

            /**
            *
            * @Title:audit
            * @Description: TODO(审核测评结果管理)
            * @param: @param id bean.id
            * @param: @param rst 审核结果 3-通过 4-拒绝
            * @param: @param remark 审核备注
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: 王恩
            * @version: 2019-02-27
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
            	    * @version: 2019-02-27
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
            	    * @version: 2019-02-27
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
                     * @version: 2019-02-27
                     */
                    public Map<String, Object> getListByCondition(Map<String, Object> params);



                    /**
                         * @throws
                         * @Title:deleteRoadSectionByRoadSectionIds
                         * @Description: TODO(批量删除测评结果管理）
                         * @param: @param ids
                         * @param: @param userProp
                         * @param: @throws Exception
                         * @return: MessageResponse
                         * @author: 王恩
                         * @version: 2019-02-27
                         */
                       public MessageResponse deleteTestByTestIds(String [] id, UserProp userProp) throws Exception;


                        /**
                            * @throws
                            * @Title:updateStatus
                            * @Description: TODO(更新状态)
                            * @param: @param obj
                            * @param: @param userProp
                            * @param: @throws Exception
                            * @return: MessageResponse
                         * @author: 王恩
                         * @version: 2019-02-27
                            */
                           MessageResponse updateStatus(String id,String status, UserProp userProp) throws Exception;
 }
