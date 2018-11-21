package com.huacainfo.ace.jxb.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.Consult;
import com.huacainfo.ace.jxb.vo.ConsultQVo;
import com.huacainfo.ace.jxb.vo.ConsultVo;

/**
 * @author: Arvin
 * @version: 2018-07-25
 * @Description: TODO(咨询师-咨询预约介绍)
 */
public interface ConsultService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(咨询师-咨询预约介绍分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <ConsultVo>
     * @author: Arvin
     * @version: 2018-07-25
     */
    PageResult<ConsultVo> findConsultList(ConsultQVo condition,
                                          int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertConsult
     * @Description: TODO(添加咨询师-咨询预约介绍)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    MessageResponse insertConsult(Consult obj, UserProp userProp) throws Exception;

    MessageResponse modifyConsult(ConsultQVo consult, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateConsult
     * @Description: TODO(更新咨询师-咨询预约介绍)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    MessageResponse updateConsult(Consult obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectConsultByPrimaryKey
     * @Description: TODO(获取咨询师-咨询预约介绍)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Consult>
     * @author: Arvin
     * @version: 2018-07-25
     */
    SingleResult<ConsultVo> selectConsultByPrimaryKey(String id) throws Exception;

    ResultResponse getMyConsultInfo(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteConsultByConsultId
     * @Description: TODO(删除咨询师-咨询预约介绍)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    MessageResponse deleteConsultByConsultId(String id, UserProp userProp) throws Exception;

    /**
     * 获取咨询师主页
     *
     * @param counselorId 咨询师id
     * @return ResultResponse
     */
    ResultResponse getCounselorDetail(String counselorId) throws Exception;

    /**
     * 咨询师 在线/离线
     *
     * @param counselorId  咨询师id
     * @param onlineStatus 在线状态 0-离线 1-在线
     * @return ResultResponse
     */
    ResultResponse onOff(String counselorId, String onlineStatus);

    /**
     * 调整咨询师 - 是否接受咨询
     *
     * @param counselorId 咨询师ID
     * @param state       是否接收咨询 0-否 1-是
     * @return
     * @throws Exception
     */
    MessageResponse updateState(String counselorId, String state, UserProp curUserProp);
}
