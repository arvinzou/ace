package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.InformationService;
import com.huacainfo.ace.fop.vo.InformationServiceQVo;
import com.huacainfo.ace.fop.vo.InformationServiceVo;

/**
 * @author: huacai003
 * @version: 2018-05-17
 * @Description: TODO(信息服务)
 */
public interface InformationServiceService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(信息服务分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <InformationServiceVo>
     * @author: huacai003
     * @version: 2018-05-17
     */
    PageResult<InformationServiceVo> findInformationServiceList(InformationServiceQVo condition, int start, int limit, String orderBy) throws Exception;

    ResultResponse InformationServiceList(InformationServiceQVo condition, int page, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertInformationService
     * @Description: TODO(添加信息服务)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    MessageResponse insertInformationService(InformationService obj, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:updateInformationService
     * @Description: TODO(更新信息服务)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    MessageResponse updateInformationService(InformationService obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectInformationServiceByPrimaryKey
     * @Description: TODO(获取信息服务)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<InformationService>
     * @author: huacai003
     * @version: 2018-05-17
     */
    SingleResult<InformationServiceVo> selectInformationServiceByPrimaryKey(String id) throws Exception;

    ResultResponse InformationServiceByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteInformationServiceByInformationServiceId
     * @Description: TODO(删除信息服务)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    MessageResponse deleteInformationServiceByInformationServiceId(String id, UserProp userProp) throws Exception;


    /**
     * 功能描述:  审核
     *
     * @param auditOpinion 审核备注
     * @param auditResult  审核结果 -- 0-通过，1-不通过
     * @param: id Fop_Finance_Project.id
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/8 18:19
     */
    MessageResponse audit(String id, String auditResult, String auditOpinion, UserProp curUserProp) throws Exception;
}
