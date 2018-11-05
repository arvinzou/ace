package com.huacainfo.ace.fop.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.Pm;
import com.huacainfo.ace.fop.vo.PmVo;
import com.huacainfo.ace.fop.vo.PmQVo;
/**
* @author: 陈晓克
* @version: 2018-11-05
* @Description:  TODO(党员信息)
*/
public interface PmService {
/**
*
* @Title:find!{bean.name}List
* @Description:  TODO(党员信息分页查询)
* @param:        @param condition
* @param:        @param start
* @param:        @param limit
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       PageResult
<PmVo>
    * @throws
    * @author: 陈晓克
    * @version: 2018-11-05
    */
    PageResult
    <PmVo> findPmList(PmQVo condition,
        int start, int limit, String orderBy) throws Exception;

        /**
        *
        * @Title:insertPm
        * @Description: TODO(添加党员信息)
        * @param: @param obj
        * @param: @param userProp
        * @param: @throws Exception
        * @return: MessageResponse
        * @throws
        * @author: 陈晓克
        * @version: 2018-11-05
        */
        MessageResponse insertPm(Pm obj,UserProp userProp) throws Exception;

        /**
        *
        * @Title:updatePm
        * @Description: TODO(更新党员信息)
        * @param: @param obj
        * @param: @param userProp
        * @param: @throws Exception
        * @return: MessageResponse
        * @throws
        * @author: 陈晓克
        * @version: 2018-11-05
        */
        MessageResponse updatePm(Pm obj,UserProp userProp) throws Exception;

        /**
        *
        * @Title:selectPmByPrimaryKey
        * @Description: TODO(获取党员信息)
        * @param: @param id
        * @param: @throws Exception
        * @return: SingleResult<Pm>
        * @throws
        * @author: 陈晓克
        * @version: 2018-11-05
        */
        SingleResult
        <PmVo> selectPmByPrimaryKey(String id) throws Exception;

            /**
            *
            * @Title:deletePmByPmId
            * @Description: TODO(删除党员信息)
            * @param: @param id
            * @param: @param userProp
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: 陈晓克
            * @version: 2018-11-05
            */
            MessageResponse deletePmByPmId(String id,UserProp userProp) throws Exception;

            /**
            *
            * @Title:audit
            * @Description: TODO(审核党员信息)
            * @param: @param id bean.id
            * @param: @param rst 审核结果 3-通过 4-拒绝
            * @param: @param remark 审核备注
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: 陈晓克
            * @version: 2018-11-05
            */
            MessageResponse audit(String id,String rst, String remark, UserProp userProp) throws Exception;
            }
