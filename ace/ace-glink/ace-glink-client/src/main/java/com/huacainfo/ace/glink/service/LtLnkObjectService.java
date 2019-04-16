package com.huacainfo.ace.glink.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.glink.model.LtLnkObject;

/**
 * @author: huacai003
 * @version: 2019-04-16
 * @Description: TODO(策略下发对象)
 */
public interface LtLnkObjectService {

    /**
     * @throws
     * @Title:insertLtLnkObject
     * @Description: TODO(添加策略下发对象)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-16
     */
    MessageResponse insertLtLnkObject(LtLnkObject obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateLtLnkObject
     * @Description: TODO(更新策略下发对象)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-16
     */
    MessageResponse updateLtLnkObject(LtLnkObject obj, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:deleteLtLnkObjectByLtLnkObjectId
     * @Description: TODO(删除策略下发对象)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-16
     */
    MessageResponse deleteLtLnkObjectByLtLnkObjectId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核策略下发对象)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-16
     */
    MessageResponse audit(String id, String rst, String remark, UserProp userProp) throws Exception;

}
