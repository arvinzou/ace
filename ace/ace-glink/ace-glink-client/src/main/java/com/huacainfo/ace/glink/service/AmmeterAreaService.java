package com.huacainfo.ace.glink.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.glink.model.AmmeterArea;
import com.huacainfo.ace.glink.vo.AmmeterAreaVo;
import com.huacainfo.ace.glink.vo.AmmeterAreaQVo;
import java.util.Map;
import java.util.List;
/**
* @author: Arvin
* @version: 2019-04-15
* @Description:  TODO(故障报警-短信-调度映射关系)
*/
public interface AmmeterAreaService {
/**
*
* @Title:find!{bean.name}List
* @Description:  TODO(故障报警-短信-调度映射关系分页查询)
* @param:        @param condition
* @param:        @param start
* @param:        @param limit
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       PageResult
<AmmeterAreaVo>
    * @throws
    * @author: Arvin
    * @version: 2019-04-15
    */
    PageResult
    <AmmeterAreaVo> findAmmeterAreaList(AmmeterAreaQVo condition,
        int start, int limit, String orderBy) throws Exception;

        /**
        *
        * @Title:insertAmmeterArea
        * @Description: TODO(添加故障报警-短信-调度映射关系)
        * @param: @param obj
        * @param: @param userProp
        * @param: @throws Exception
        * @return: MessageResponse
        * @throws
        * @author: Arvin
        * @version: 2019-04-15
        */
        MessageResponse insertAmmeterArea(AmmeterArea obj,UserProp userProp) throws Exception;

        /**
        *
        * @Title:updateAmmeterArea
        * @Description: TODO(更新故障报警-短信-调度映射关系)
        * @param: @param obj
        * @param: @param userProp
        * @param: @throws Exception
        * @return: MessageResponse
        * @throws
        * @author: Arvin
        * @version: 2019-04-15
        */
        MessageResponse updateAmmeterArea(AmmeterArea obj,UserProp userProp) throws Exception;

        /**
        *
        * @Title:selectAmmeterAreaByPrimaryKey
        * @Description: TODO(获取故障报警-短信-调度映射关系)
        * @param: @param id
        * @param: @throws Exception
        * @return: SingleResult<AmmeterArea>
        * @throws
        * @author: Arvin
        * @version: 2019-04-15
        */
        SingleResult
        <AmmeterAreaVo> selectAmmeterAreaByPrimaryKey(String id) throws Exception;

            /**
            *
            * @Title:deleteAmmeterAreaByAmmeterAreaId
            * @Description: TODO(删除故障报警-短信-调度映射关系)
            * @param: @param id
            * @param: @param userProp
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: Arvin
            * @version: 2019-04-15
            */
            MessageResponse deleteAmmeterAreaByAmmeterAreaId(String id,UserProp userProp) throws Exception;

            /**
            *
            * @Title:audit
            * @Description: TODO(审核故障报警-短信-调度映射关系)
            * @param: @param id bean.id
            * @param: @param rst 审核结果 3-通过 4-拒绝
            * @param: @param remark 审核备注
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: Arvin
            * @version: 2019-04-15
            */
            MessageResponse audit(String id,String rst, String remark, UserProp userProp) throws Exception;


            /**
            *
            * @Title:importXls
            * @Description: TODO(Excel导入资源数据)
            * @param: @param list
            * @param: @param userProp
            * @param: @return
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: Arvin
            * @version: 2019-04-15
            */
            MessageResponse importXls(List
            <Map
            <String
            , Object>> list, UserProp userProp)throws Exception;
}

