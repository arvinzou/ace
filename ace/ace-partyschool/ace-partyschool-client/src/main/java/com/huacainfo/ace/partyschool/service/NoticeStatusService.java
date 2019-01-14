package com.huacainfo.ace.partyschool.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.partyschool.model.NoticeStatus;
import com.huacainfo.ace.partyschool.vo.NoticeStatusVo;
import com.huacainfo.ace.partyschool.vo.NoticeStatusQVo;
import java.util.Map;
import java.util.List;
/**
* @author: 陈晓克
* @version: 2019-01-12
* @Description:  TODO(公告通知)
*/
public interface NoticeStatusService {

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-12
     */
    MessageResponse updateStatus(String id, UserProp userProp) throws Exception;
}
