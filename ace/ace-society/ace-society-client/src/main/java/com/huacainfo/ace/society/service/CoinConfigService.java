package com.huacainfo.ace.society.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.CoinConfig;
import com.huacainfo.ace.society.vo.CoinConfigVo;
import com.huacainfo.ace.society.vo.CoinConfigQVo;
/**
* @author: huacai003
* @version: 2018-09-17
* @Description:  TODO(爱心币配置)
*/
public interface CoinConfigService {
/**
*
* @Title:find!{bean.name}List
* @Description:  TODO(爱心币配置分页查询)
* @param:        @param condition
* @param:        @param start
* @param:        @param limit
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       PageResult
<CoinConfigVo>
    * @throws
    * @author: huacai003
    * @version: 2018-09-17
    */
    PageResult
    <CoinConfigVo> findCoinConfigList(CoinConfigQVo condition,
        int start, int limit, String orderBy) throws Exception;

        /**
        *
        * @Title:insertCoinConfig
        * @Description: TODO(添加爱心币配置)
        * @param: @param obj
        * @param: @param userProp
        * @param: @throws Exception
        * @return: MessageResponse
        * @throws
        * @author: huacai003
        * @version: 2018-09-17
        */
        MessageResponse insertCoinConfig(CoinConfig obj,UserProp userProp) throws Exception;

        /**
        *
        * @Title:updateCoinConfig
        * @Description: TODO(更新爱心币配置)
        * @param: @param obj
        * @param: @param userProp
        * @param: @throws Exception
        * @return: MessageResponse
        * @throws
        * @author: huacai003
        * @version: 2018-09-17
        */
        MessageResponse updateCoinConfig(CoinConfig obj,UserProp userProp) throws Exception;
        MessageResponse softDel(String id,UserProp userProp) throws Exception;

        /**
        *
        * @Title:selectCoinConfigByPrimaryKey
        * @Description: TODO(获取爱心币配置)
        * @param: @param id
        * @param: @throws Exception
        * @return: SingleResult<CoinConfig>
        * @throws
        * @author: huacai003
        * @version: 2018-09-17
        */
        SingleResult
        <CoinConfigVo> selectCoinConfigByPrimaryKey(String id) throws Exception;

            /**
            *
            * @Title:deleteCoinConfigByCoinConfigId
            * @Description: TODO(删除爱心币配置)
            * @param: @param id
            * @param: @param userProp
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: huacai003
            * @version: 2018-09-17
            */
            MessageResponse deleteCoinConfigByCoinConfigId(String id,UserProp userProp) throws Exception;

            /**
            *
            * @Title:audit
            * @Description: TODO(审核爱心币配置)
            * @param: @param id bean.id
            * @param: @param rst 审核结果 3-通过 4-拒绝
            * @param: @param remark 审核备注
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: huacai003
            * @version: 2018-09-17
            */
            MessageResponse audit(String id,String rst, String remark, UserProp userProp) throws Exception;
            }
