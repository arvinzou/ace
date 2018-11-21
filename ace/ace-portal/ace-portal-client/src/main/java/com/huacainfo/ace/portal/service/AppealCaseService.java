package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.AppealCase;
import com.huacainfo.ace.portal.model.AppealCaseFile;
import com.huacainfo.ace.portal.vo.AppealCaseQVo;
import com.huacainfo.ace.portal.vo.AppealCaseVo;

import java.util.List;
import java.util.Map;

/**
 * @author: 陈晓克
 * @version: 2018-05-14
 * @Description: TODO(诉求)
 */
public interface AppealCaseService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(诉求分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<AppealCaseVo>
     * @author: 陈晓克
     * @version: 2018-05-14
     */
    public abstract PageResult<AppealCaseVo> findAppealCaseList(AppealCaseQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertAppealCase
     * @Description: TODO(添加诉求)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-05-14
     */
    public abstract MessageResponse insertAppealCase(AppealCase obj, List<AppealCaseFile> list) throws Exception;

    /**
     * @throws
     * @Title:updateAppealCase
     * @Description: TODO(诉求答复)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-05-14
     */
    public abstract MessageResponse updateAppealCase(AppealCase obj, List<AppealCaseFile> list, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectAppealCaseByPrimaryKey
     * @Description: TODO(获取诉求)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<AppealCase>
     * @author: 陈晓克
     * @version: 2018-05-14
     */
    public abstract SingleResult<AppealCaseVo> selectAppealCaseByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteAppealCaseByAppealCaseId
     * @Description: TODO(删除诉求)
     * @param: @param id
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-05-14
     */
    public abstract MessageResponse deleteAppealCaseByAppealCaseId(String id, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:getList
     * @Description: TODO(获取列表)
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: 陈晓克
     * @version: 2018-05-15
     */
    public Map<String, Object> getList(Map<String, Object> params) throws Exception;


    /**
     * @throws
     * @Title:updateAccept
     * @Description: TODO(接受处理诉求)
     * @param: @param id
     * @param: @param answerDept
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-05-16
     */
    public abstract MessageResponse updateAccept(String id, String answerDept, String operator, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:updateDetailsOfProgress
     * @Description: TODO(诉求进展情况更新)
     * @param: @param id
     * @param: @param detailsOfProgress
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-05-16
     */
    public abstract MessageResponse updateDetailsOfProgress(String id, String detailsOfProgress, UserProp userProp) throws Exception;


    /**
     * 获取附件列表
     *
     * @param appealCaseId
     * @param type         类型（1诉求2答复）
     * @return
     */
    ResultResponse findFileList(String appealCaseId, String type, UserProp curUserProp);

    /**
     * 删除附件
     *
     * @param id
     * @param userProp
     * @return
     */
    ResultResponse deleteAppealCaseFile(String id, UserProp userProp);

    AppealCaseFile insertAppealCaseFile(String appealCaseId, String name, String mediType, String url);

    /**
     * pc端，诉求答复
     *
     * @param obj
     * @param userProp
     * @return
     * @throws Exception
     */
    MessageResponse updateAppealCase(AppealCase obj, UserProp userProp) throws Exception;

    ResultResponse acMsgTest(String appealCaseId, String sendType) throws Exception;
}
