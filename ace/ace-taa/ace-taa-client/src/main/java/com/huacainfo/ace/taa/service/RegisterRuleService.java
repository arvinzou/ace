package com.huacainfo.ace.taa.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.taa.model.RegisterRule;
import com.huacainfo.ace.taa.vo.RegisterRuleQVo;
import com.huacainfo.ace.taa.vo.RegisterRuleVo;

import java.util.List;
import java.util.Map;

/**
 * @author: Arvin
 * @version: 2019-03-08
 * @Description: TODO(注册规则)
 */
public interface RegisterRuleService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(注册规则分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <RegisterRuleVo>
     * @author: Arvin
     * @version: 2019-03-08
     */
    PageResult<RegisterRuleVo> findRegisterRuleList(RegisterRuleQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertRegisterRule
     * @Description: TODO(添加注册规则)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-03-08
     */
    MessageResponse insertRegisterRule(RegisterRule obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateRegisterRule
     * @Description: TODO(更新注册规则)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-03-08
     */
    MessageResponse updateRegisterRule(RegisterRule obj, UserProp userProp);

    /**
     * @throws
     * @Title:selectRegisterRuleByPrimaryKey
     * @Description: TODO(获取注册规则)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<RegisterRule>
     * @author: Arvin
     * @version: 2019-03-08
     */
    SingleResult<RegisterRuleVo> selectRegisterRuleByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteRegisterRuleByRegisterRuleId
     * @Description: TODO(删除注册规则)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-03-08
     */
    MessageResponse deleteRegisterRuleByRegisterRuleId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核注册规则)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-03-08
     */
    MessageResponse audit(String id, String rst, String remark, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:importXls
     * @Description: TODO(Excel导入资源数据)
     * @param: @param list
     * @param: @param userProp
     * @param: @return
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-03-08
     */
    MessageResponse importXls(Map<String, Object> params, List<Map<String, Object>> list, UserProp userProp);


    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-03-08
     */
    MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception;
}
